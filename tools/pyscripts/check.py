#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
使用方法:
    python check.py [目录路径] [文件后缀]

示例:
    python check.py sast-go go
    python check.py sast-python3 py

如果不提供目录路径，则默认使用当前目录下的sast-python3
如果不提供文件后缀，则默认使用.py后缀

功能说明:
1. 检查config.json中的level值与文件注释中的level值是否一致
2. 检测level格式是否包含+号等特殊标记
3. 检查文件路径与注释中的bind_url是否一致
4. 检查文件内容中是否有与文件名同名的方法/类
5. 支持同名方法检测白名单跳过特定文件的同名方法/类检测（使用完整文件名匹配，包含扩展名）
6. 检测config.json中声明的case文件是否实际存在（文件缺失检测）
7. 检测config.json中的字段名称是否与config.json所在文件夹名称一致
8. 检测文件重名情况，支持白名单机制
9. 检测文件命名与注释中real case值的一致性（T结尾应为true，F结尾应为false）
10. 检测case文件注释中的evaluation item是否与config中的evaluation_item保持一致
"""

import json
import os
import re
import sys
from pathlib import Path
from typing import List, Set, Dict, Optional, TypedDict
from common import ERROR_LIST, LEVEL_PATTERN, REAL_CASE_PATTERN, BIND_URL_PATTERN, EVALUATION_ITEM_PATTERN, \
    SCENE_INTRODUCTION_PATTERN


# 不一致类型
class Inconsistent(TypedDict):
    file_path: str
    problem: str
    expectation: str | None


# 文件注释信息
class FileCommentInfo(TypedDict):
    level: str | None
    bind_url: str | None
    real_case: str | None
    evaluation_item: str | None
    scene_introduction: str | None


class ConfigLevelChecker(object):
    # 语言特定的模式
    LANGUAGE_PATTERNS = {
        '.go': [
            r'func\s+{name}\s*\(',
            r'func\s+{name_upper}\s*\('
        ],
        '.py': [
            r'def\s+{name}\s*\(',
            r'async\s+def\s+{name}\s*\('
        ],
        '.js': [
            r'function\s+{name}\s*\(',
            r'(?:const|let|var)\s+{name}\s*=',
            r'{name}\s*=\s*function\s*\('
        ],
        '.java': [
            r'(?:public\s+)?class\s+{name}\s*[\{{\s]',
            r'(?:public\s+)?(?:final\s+)?class\s+{name}\s*[\{{\s]'
        ]
    }

    def __init__(self, base_path: str, file_extensions: str = 'py'):
        self.base_path = Path(base_path).resolve()
        self.file_extensions = file_extensions
        self.inconsistencies: Dict[str, List[Inconsistent]] = {}
        self.checked_files = 0
        self.checked_fail_files = 0
        self.checked_config_files = 0
        self.checked_fail_config_files = 0

        # 初始化 inconsistencies
        for error in ERROR_LIST.values():
            self.inconsistencies[error] = []

        # 预编译正则表达式
        self._compile_patterns()

        # 同名方法检测白名单
        self.method_name_check_whitelist = self._get_method_name_check_whitelist()

        # 文件重名检测白名单
        self.duplicate_files_whitelist = self._get_duplicate_files_whitelist()

    def _get_method_name_check_whitelist(self) -> Set[str]:
        """获取同名方法检测的白名单（根据目录动态返回对应的白名单）"""
        # 按目录分类的白名单
        whitelist_by_dir = {
            'sast-go': {
                'cross_directory_021_T_a.go', 'cross_directory_021_T_b.go',
                'public_var_cross_package_002_F_a.go', 'public_var_cross_package_001_T_a.go',
                'cross_directory_022_F_a.go', 'cross_directory_022_F_b.go',
                'cross_directory_023_T_a.go', 'cross_directory_024_F_a.go',
                'cross_directory_025_T_a.go', 'cross_directory_026_F_a.go',
                'cross_same_name_027_T.go', 'cross_same_name_028_F.go',
                'cross_directory_029_T_a.go', 'cross_directory_030_F_a.go',
            },
            'sast-js': {
                'cross_module_007_T_a.js', 'cross_module_008_F_a.js', 'cross_module_011_T_a.js',
                'cross_module_012_F_a.js'
            },
            'sast-python2': {
                'cross_file_003_T_a.py', 'cross_file_004_F_a.py', 'cross_file_005_T_a.py',
                'cross_file_006_F_a.py', 'cross_file_007_T_a.py', 'cross_file_008_F_a.py',
                'cross_module_003_T_a.py', 'cross_module_004_F_a.py', 'cross_module_005_T_a.py',
                'cross_module_006_F_a.py', 'cross_module_007_T_a.py', 'cross_module_008_F_a.py',
                'cross_module_011_T_a.py', 'cross_module_012_F_a.py', 'cross_module_013_T_a.py',
                'cross_module_014_F_a.py', 'cross_module_015_T_a.py', 'cross_module_016_F_a.py'
            },
            'sast-python3': {
                'cross_file_003_T_a.py', 'cross_file_004_F_a.py', 'cross_file_005_T_a.py',
                'cross_file_006_F_a.py', 'cross_file_007_T_a.py', 'cross_file_008_F_a.py',
                'cross_file_009_T_a.py', 'cross_file_010_F_a.py', 'cross_module_003_T_a.py',
                'cross_module_004_F_a.py', 'cross_module_005_T_a.py', 'cross_module_006_F_a.py',
                'cross_module_007_T_a.py', 'cross_module_008_F_a.py', 'cross_module_009_T.py',
                'cross_module_010_F.py', 'cross_module_011_T.py', 'cross_module_011_T_a.py',
                'cross_module_012_F.py', 'cross_module_012_F_a.py', 'cross_module_013_T.py',
                'cross_module_013_T_a.py', 'cross_module_014_F.py', 'cross_module_014_F_a.py',
                'cross_module_015_T.py', 'cross_module_015_T_a.py', 'cross_module_016_F.py',
                'cross_module_016_F_a.py', 'cross_module_017_T_a.py',
            },
        }

        # 根据当前base_path确定目录名
        current_dir_name = self.base_path.name.lower()

        # 返回对应目录的白名单，如果没有匹配的则返回空集合
        return whitelist_by_dir.get(current_dir_name, set())

    def _get_duplicate_files_whitelist(self) -> Set[str]:
        """获取文件重名检测的白名单（按目录分类）"""
        # 按目录分类的重名文件白名单
        whitelist_by_dir = {
            'sast-go': {
                'main.go', 'test.go', 'helper.go', 'utils.go', 'config.go',
                'main_test.go', 'helper_test.go',
                'example.go', 'sample.go'
            },
            'sast-js': {
                'index.js', 'main.js', 'test.js', 'helper.js', 'utils.js',
                'config.js', 'main.test.js', 'helper.test.js',
                'example.js', 'sample.js'
            },
            'sast-python2': {
                'main.py', 'test.py', 'helper.py', 'utils.py', 'config.py',
                '__init__.py', 'setup.py', 'example.py', 'sample.py'
            },
            'sast-python3': {
                'main.py', 'test.py', 'helper.py', 'utils.py', 'config.py',
                '__init__.py', 'setup.py', 'example.py', 'sample.py'
            },
        }

        # 根据当前base_path确定目录名
        current_dir_name = self.base_path.name.lower()

        # 返回对应目录的白名单，如果没有匹配的则返回空集合
        return whitelist_by_dir.get(current_dir_name, set())

    def _compile_patterns(self):
        """预编译语言特定的正则表达式"""
        self.compiled_patterns = {}
        for ext, patterns in self.LANGUAGE_PATTERNS.items():
            self.compiled_patterns[ext] = [re.compile(p) for p in patterns]

    def _find_config_files(self) -> List[Path]:
        """查找所有config.json文件，忽略target文件夹"""
        return [
            Path(root) / 'config.json'
            for root, dirs, files in os.walk(self.base_path)
            if 'target' not in [d.lower() for d in dirs] and
            'target' not in root.lower() and
            'config.json' in files
        ]

    def _build_expected_bind_url(self, file_path: Path) -> str:
        """根据文件路径构建期望的bind_url"""
        # 获取相对于基准路径的路径
        try:
            relative_path = file_path.relative_to(self.base_path)
        except ValueError:
            # 如果无法相对化，使用绝对路径
            return str(file_path)

        # 将路径转换为字符串并分割
        path_str = str(relative_path)

        # 查找completeness或accuracy的起始位置
        completeness_pos = path_str.find('completeness/')
        accuracy_pos = path_str.find('accuracy/')

        if completeness_pos != -1:
            # 从completeness开始
            relevant_path = path_str[completeness_pos:]
        elif accuracy_pos != -1:
            # 从accuracy开始
            relevant_path = path_str[accuracy_pos:]
        else:
            # 如果没有找到completeness或accuracy，使用完整路径
            relevant_path = path_str

        # 移除文件扩展名
        if '.' in relevant_path:
            relevant_path = relevant_path.rsplit('.', 1)[0]

        return relevant_path

    def _build_expected_real_case(self, file_path: Path) -> str:
        """根据文件名构建期望的 real case"""
        filename = file_path.stem

        # 判断文件名是否以 T 结尾或 T_[任意字符] 结尾
        is_positive_case = (filename.endswith('T') or
                            (filename.endswith('T') and not filename.endswith('F')) or
                            re.search(r'T_[a-zA-Z0-9_]+$', filename) is not None)

        # 判断文件名是否以 F 结尾或 F_[任意字符] 结尾
        is_negative_case = (filename.endswith('F') or
                            (filename.endswith('F') and not filename.endswith('T')) or
                            re.search(r'F_[a-zA-Z0-9_]+$', filename) is not None)

        expected_real_case = None
        if is_positive_case:
            expected_real_case = 'true'
        elif is_negative_case:
            expected_real_case = 'false'

        return expected_real_case

    def _resolve_file_path(self, part: str, config_dir: Path, supported_extensions: Set[str]) -> Optional[Path]:
        """解析单个文件路径"""
        file_ext = Path(part).suffix.lower()

        # 直接路径检查
        direct_path = config_dir / part
        if direct_path.exists() and 'target' not in str(direct_path).lower():
            return direct_path

        # 在子目录中查找
        for root, dirs, files in os.walk(config_dir):
            dirs[:] = [d for d in dirs if 'target' not in d.lower()]

            if 'target' in root.lower():
                continue

            potential_file = Path(root) / part
            if potential_file.exists() and 'target' not in str(potential_file).lower():
                return potential_file

        # 如果没有扩展名，添加默认扩展名
        if not file_ext:
            default_ext = self.file_extensions[0] if self.file_extensions else 'go'
            filename_with_ext = f"{part}.{default_ext.lstrip('.')}"

            for root, dirs, files in os.walk(config_dir):
                dirs[:] = [d for d in dirs if 'target' not in d.lower()]

                if 'target' in root.lower():
                    continue

                potential_file = Path(root) / filename_with_ext
                if potential_file.exists() and 'target' not in str(potential_file).lower():
                    return potential_file

        # 返回原始路径用于报告缺失，但排除target路径
        if 'target' in str(config_dir / part).lower():
            return None

        if file_ext in supported_extensions or '.' in part:
            return config_dir / part
        else:
            default_ext = self.file_extensions[0] if self.file_extensions else 'go'
            result_path = config_dir / f"{part}.{default_ext.lstrip('.')}"
            if 'target' in str(result_path).lower():
                return None
            return result_path

    def _parse_compose_files(self, compose_str: str, config_dir: Path) -> List[Path]:
        """解析compose字段中的文件路径"""
        if not compose_str:
            return []

        # 清理字符串
        clean_str = compose_str.replace('!', '').replace('(', '').replace(')', '')

        # 支持的扩展名集合
        supported_extensions = {f'.{ext.lstrip(".")}' for ext in self.file_extensions}

        result_files = []
        for condition in clean_str.split('&&'):
            for part in condition.split('||'):
                part = part.strip()
                if not part:
                    continue

                file_path = self._resolve_file_path(part, config_dir, supported_extensions)
                if file_path:
                    result_files.append(file_path)

        return result_files

    def _check_method_name_consistency(self, file_path: Path) -> List[str]:
        """检查文件内容中是否有与文件名同名的方法或类"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()

            filename_without_ext = file_path.stem
            file_extension = file_path.suffix.lower()

            if file_extension not in self.compiled_patterns:
                return []

            # 根据语言类型获取模式
            patterns = self.compiled_patterns[file_extension]
            name_upper = filename_without_ext[0].upper() + filename_without_ext[
                1:] if filename_without_ext else filename_without_ext

            # 检查所有模式
            for pattern in patterns:
                formatted_pattern = pattern.pattern.format(name=re.escape(filename_without_ext),
                                                           name_upper=re.escape(name_upper))
                if re.search(formatted_pattern, content):
                    return [filename_without_ext]

            return []
        except Exception as e:
            print(f"检查方法名/类名失败: {file_path} - {e}")
            return []

    def _set_inconsistencies(self, file_path, problem, expectation=None):
        self.inconsistencies[problem].append({
            'file_path': str(file_path),
            'problem': problem,
            'expectation': expectation,
        })

    def get_file_comment_info(self, file_path: Path) -> FileCommentInfo:
        """从文件中提取注释信息"""

        def get_match_value(match):
            return match.group(1) if match else None

        result: FileCommentInfo = {
            'level': None,
            'real_case': None,
            'bind_url': None,
            'evaluation_item': None,
            'scene_introduction': None,
        }

        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                file_content = f.read()
                level_match = LEVEL_PATTERN.search(file_content)
                real_case_match = REAL_CASE_PATTERN.search(file_content)
                bind_url_match = BIND_URL_PATTERN.search(file_content)
                evaluation_item = EVALUATION_ITEM_PATTERN.search(file_content)
                scene_introduction = SCENE_INTRODUCTION_PATTERN.search(file_content)

                result['level'] = get_match_value(level_match)
                result['real_case'] = get_match_value(real_case_match)
                result['bind_url'] = get_match_value(bind_url_match)
                result['evaluation_item'] = get_match_value(evaluation_item)
                result['scene_introduction'] = get_match_value(scene_introduction)

        except Exception as e:
            print(f"读取文件失败: {file_path} - {e}")

        return result

    def check_config_file(self, config_path: Path):
        """检查单个config.json文件"""
        # 检查配置文件数量增加
        self.checked_config_files += 1

        try:
            # 检查 config 是否为空
            with open(config_path, 'r', encoding='utf-8') as f:
                content = f.read().strip()
                config = json.loads(content)

            config_dir = config_path.parent

            # 检查config中的字段名称是否与所在文件夹名称一致
            expected_field_name = config_dir.name
            actual_field_names = list(config.keys())

            if expected_field_name not in actual_field_names:
                self._set_inconsistencies(config_path, ERROR_LIST['CONFIG_FILED_AND_DIR_NAME_NOT_SAME'],
                                          expected_field_name)

            for config_filed, evaluation_item_list in config.items():
                for evaluation_item in evaluation_item_list:
                    config_evaluation_item = evaluation_item['evaluation_item']

                    for scene_level in evaluation_item['scene_levels']:
                        config_level = scene_level['level']

                        if '+' in str(config_level):
                            self._set_inconsistencies(config_path, ERROR_LIST['LEVEL_HAS_PLUS'])

                        scene_list = scene_level.get('scene_list', [])

                        for scene in scene_list:
                            compose_str = scene.get('compose', '')
                            scene_str = scene.get('scene', '')
                            if not compose_str:
                                continue

                            # 解析出所有相关的文件
                            files = self._parse_compose_files(compose_str, config_dir)

                            for file_path in files:
                                try:
                                    if not file_path.exists():
                                        self._set_inconsistencies(file_path, ERROR_LIST['FILE_MISSING'])
                                        continue

                                    # 检查文件数量增加
                                    self.checked_files += 1

                                    # 获取注释信息
                                    file_comment_info = self.get_file_comment_info(file_path)

                                    # 检查 level 与 config 中是否一致
                                    if file_comment_info['level'] != config_level:
                                        self._set_inconsistencies(file_path,
                                                                  ERROR_LIST['LEVEL_AND_CONFIG_LEVEL_NOT_SAME'],
                                                                  config_level)

                                    # 检查 bind_url 是否符合预期
                                    expected_bind_url = self._build_expected_bind_url(file_path)
                                    if file_comment_info['bind_url'] != expected_bind_url:
                                        self._set_inconsistencies(file_path, ERROR_LIST['BIND_URL_ERROR'],
                                                                  expected_bind_url)

                                    # 检查 evaluation_item 与 config 中是否一致
                                    if file_comment_info['evaluation_item'] != config_evaluation_item:
                                        self._set_inconsistencies(file_path,
                                                                  ERROR_LIST[
                                                                      'EVALUATION_ITEM_AND_CONFIG_EVALUATION_ITEM_NOT_SAME'],
                                                                  config_evaluation_item)

                                    # 检查 real case 是否符合预期
                                    expected_real_case = self._build_expected_real_case(file_path)
                                    if file_comment_info['real_case'] != expected_real_case:
                                        self._set_inconsistencies(file_path, ERROR_LIST['REAL_CASE_ERROR'],
                                                                  expected_real_case)

                                    # 检查 scene introduction 与 config 中是否一致
                                    if file_comment_info['scene_introduction'] != scene_str:
                                        self._set_inconsistencies(file_path,
                                                                  ERROR_LIST['SCENE_AND_CONFIG_SCENE_NOT_SAME'],
                                                                  scene_str)

                                    # 检查是否有同名方法
                                    # 跳过同名方法检测白名单中的文件（使用完整文件名匹配）
                                    file_methods = self._check_method_name_consistency(file_path)
                                    filename_with_ext = file_path.name
                                    if filename_with_ext not in self.method_name_check_whitelist and not file_methods:
                                        self._set_inconsistencies(file_path,
                                                                  ERROR_LIST['MISSING_METHOD_WITH_THE_SAME_NAME'])
                                except Exception as e:
                                    self.checked_fail_files += 1
                                    print(f"❌ 处理文件失败: {self.handle_file_path(str(file_path))}")


        except json.JSONDecodeError as e:
            self.checked_fail_config_files += 1
            print(f"❌ 跳过无效的JSON文件: {self.handle_file_path(str(config_path))}")
        except Exception as e:
            self.checked_fail_config_files += 1
            print(f"❌ 处理配置文件失败: {self.handle_file_path(str(config_path))}")

    def find_duplicate_files(self) -> Dict[str, List[Path]]:
        """查找重名文件"""
        filename_map = {}

        # 遍历所有支持的文件类型
        for ext in self.file_extensions:
            pattern = f"**/*.{ext.lstrip('.')}"
            for file_path in self.base_path.rglob(pattern):
                if ('target' not in str(file_path).lower() and
                        file_path.name != 'config.json' and
                        file_path.name not in self.duplicate_files_whitelist):
                    filename = file_path.name
                    if filename not in filename_map:
                        filename_map[filename] = []
                    filename_map[filename].append(file_path)

        # 只保留重名的文件
        duplicate_files = {name: paths for name, paths in filename_map.items() if len(paths) > 1}
        return duplicate_files

    def handle_file_path(self, file_path: str) -> str:
        """将绝对路径替换为相对路径"""
        file_rel_path = os.path.relpath(file_path, self.base_path)
        file_display_path = os.path.join(self.base_path.name, file_rel_path)
        return file_display_path

    def run_check(self):
        config_files = self._find_config_files()
        print(f"找到 {len(config_files)} 个config.json文件")

        for config_file in config_files:
            self.check_config_file(config_file)

    def generate_report(self):
        """生成检查报告"""
        print("\n" + "=" * 60)
        print("🚀 检查结果报告")
        print("=" * 60)

        # 先报告重名文件
        duplicate_files = self.find_duplicate_files()
        if duplicate_files:
            print(f"\n[文件重名检测] ({len(duplicate_files)} 个文件名重复):")
            for file_name, paths in duplicate_files.items():
                print(f"  {file_name}:")
                for path in paths:
                    try:
                        rel_path = path.relative_to(self.base_path)
                        print(f"    {rel_path}")
                    except ValueError:
                        print(f"    {path}")

        # 报告检测出的问题
        for key in self.inconsistencies:
            if len(self.inconsistencies[key]) > 0:
                print(f"\n[{key}] ({len(self.inconsistencies[key])}处):")
                for item in self.inconsistencies[key]:
                    print(f"  {self.handle_file_path(item['file_path'])}")
                    if item['expectation'] is not None:
                        print(f"  期望: {item['expectation']}")


def main():
    # 获取命令行参数
    base_path = "sast-python3"  # 默认使用相对路径
    file_extensions = 'py'

    # 如果提供了参数，使用提供的参数
    if len(sys.argv) > 1:
        base_path = sys.argv[1]

    # 如果路径是相对路径，转换为绝对路径
    if not os.path.isabs(base_path):
        base_path = os.path.join(os.getcwd(), base_path)

    if len(sys.argv) > 2:
        # 解析文件扩展名参数
        file_extensions = sys.argv[2]

    if not os.path.exists(base_path):
        print(f"错误: 路径 {base_path} 不存在")
        sys.exit(1)

    checker = ConfigLevelChecker(base_path, file_extensions)

    checker.run_check()
    checker.generate_report()

    print(f"\n✅ 总计检查配置文件: {checker.checked_config_files}")
    print(f"❌ 其中检查失败: {checker.checked_fail_config_files}")
    print(f"✅ 总计检查文件: {checker.checked_files}")
    print(f"❌ 其中检查失败: {checker.checked_fail_files}")


if __name__ == "__main__":
    main()
