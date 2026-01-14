#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
基于 check.py 检查结果自动修复问题
支持修复以下类型的问题：
1. bind_url 不一致问题
2. real case 不一致问题
3. 修复 level 和 config 中的 level 不一致问题
4. 修复 evaluation item 和 config 中的 evaluation_item 不一致问题
5. scene 和 config 中的 scene 不一致问题
6. config 字段和所在文件夹名不一致问题

使用方法:
    python fix.py [目录路径] [文件后缀]

示例:
    python fix.py sast-python3 py
    python fix.py sast-go go
"""

import json
import os
import re
import sys
from pathlib import Path
from typing import List, Dict
import subprocess
from common import ERROR_LIST, BIND_URL_PATTERN, REAL_CASE_PATTERN, LEVEL_PATTERN, EVALUATION_ITEM_PATTERN, \
    SCENE_INTRODUCTION_PATTERN, COMMENT_START_PATTERN


class AutoFixerFromCheck(object):

    def __init__(self, base_path: str, file_extensions: str = 'py'):
        self.base_path = Path(base_path).resolve()
        self.file_extensions = file_extensions
        self.fixed_files = 0
        self.fixed_fail_files = 0

    def _common_fix(self, err_type: str, fix_error_dict: Dict[str, List[str]], callback):
        """通用修复方法"""

        error_list = fix_error_dict.get(err_type, [])
        fixed_count = 0
        fixed_fail_count = 0

        if len(error_list) > 0:
            print(f'\n正在修复 {err_type} 问题...')

            for index, file_path in enumerate(error_list):
                if index % 2 == 1:
                    continue
                else:
                    try:
                        full_path = self.base_path.parent / file_path
                        with open(full_path, 'r', encoding='utf-8') as f:
                            content = f.read()

                        expected_match = re.search(r'期望:\s*(.+)', error_list[index + 1])
                        if expected_match:
                            expected_value = expected_match.group(1).strip()
                            new_content = callback(content, expected_value)

                            # 写入替换后的内容
                            with open(full_path, 'w', encoding='utf-8') as f:
                                f.write(new_content)
                            fixed_count += 1
                            self.fixed_files += 1

                    except Exception as e:
                        fixed_fail_count += 1
                        self.fixed_fail_files += 1
                        print(f'❌ 修复 {file_path} 时出错, {e}')

            print(f'✅ {err_type} 问题修复完成，成功修复 {fixed_count} 个文件, 失败修复 {fixed_fail_count} 个文件')

    def _fix_bind_url(self, content: str, expected_value: str):
        """修复 bind_url 不一致"""
        return re.sub(BIND_URL_PATTERN, f'bind_url = {expected_value}\n', content)

    def _fix_level_has_plus(self, error_list: List[str]):
        """修复 level 含有 + 号"""
        pass

    def _fix_config_filed(self, content: str, expected_value: str):
        """修复 config 字段和所在文件夹名不一致"""

        config_json = json.loads(content)

        new_config_json = {expected_value: []}

        for old_filed, value in config_json.items():
            if isinstance(value, list):
                new_config_json[expected_value] = value
            else:
                new_config_json[expected_value] = [value]

        return json.dumps(new_config_json, ensure_ascii=False, indent=2)

    def _fix_level_not_same(self, content: str, expected_value) -> str:
        """修复 level 和 config 中的 level 不一致"""
        new_content = content
        if LEVEL_PATTERN.search(content) is None:
            new_content = self._fix_missing_comment(content)

        return re.sub(LEVEL_PATTERN, f'level = {expected_value}\n', new_content)

    def _fix_scene_not_same(self, content: str, expected_value: str):
        """修复 scene 和 config 中的 scene 不一致"""
        return re.sub(SCENE_INTRODUCTION_PATTERN, f'scene introduction = {expected_value}\n', content)

    def _fix_real_case(self, content: str, expected_value: str) -> str:
        """修复 real case 错误"""
        return re.sub(REAL_CASE_PATTERN, f'real case = {expected_value}\n', content)

    def _fix_evaluation_item(self, content: str, expected_value: str):
        """修复 evaluation item 和 config 中的 evaluation_item 不一致"""
        return re.sub(EVALUATION_ITEM_PATTERN, f'evaluation item = {expected_value}\n',
                      content)

    def _fix_missing_comment(self, content: str) -> str:
        """修复缺失注释"""
        start_match = COMMENT_START_PATTERN.search(content)
        comment_icon = '//'
        if self.file_extensions == 'py':
            comment_icon = '#'
        if start_match and start_match.group(0) != '':
            real_case =  REAL_CASE_PATTERN.search(content)
            evaluation_item = EVALUATION_ITEM_PATTERN.search(content)
            scene = SCENE_INTRODUCTION_PATTERN.search(content)
            level = LEVEL_PATTERN.search(content)
            bind_url = BIND_URL_PATTERN.search(content)

            # 清空之前的注释
            other_content = re.sub(f'^.*?{re.escape('evaluation information end')}', '', content, flags=re.DOTALL)

            return f"""{comment_icon} evaluation information start
{comment_icon} real case = {real_case.group(1) if real_case else ''}
{comment_icon} evaluation item = {evaluation_item.group(1) if evaluation_item else ''}
{comment_icon} scene introduction = {scene.group(1) if scene else ''}
{comment_icon} level = {level.group(1) if level else ''}
{comment_icon} bind_url = {bind_url.group(1) if bind_url else ''}
{comment_icon} evaluation information end
{other_content}
"""
        else:
            return f"""{comment_icon} evaluation information start
{comment_icon} real case = 
{comment_icon} evaluation item = 
{comment_icon} scene introduction = 
{comment_icon} level = 
{comment_icon} bind_url = 
{comment_icon} evaluation information end
{content}
"""

    def _manual_repair(self, err_type: str, fix_error_dict: Dict[str, List[str]]):
        """提示手动修复问题"""
        error_list = fix_error_dict.get(err_type, [])

        if len(error_list) > 0:
            print(f'\n请手动修复 {err_type} 问题:')
            for file_path in error_list:
                print(f'  {file_path}')

    def run_script(self):
        """运行脚本"""
        print("运行 check.py 检查问题...")

        # 获取当前脚本所在目录
        script_dir = Path(__file__).parent
        check_script = script_dir / "check.py"

        try:
            cmd = [
                sys.executable,
                str(check_script),
                str(self.base_path),
                self.file_extensions,
            ]
            result = subprocess.run(cmd, capture_output=True, text=True, cwd=script_dir.parent.parent)
            check_output = result.stdout

            if result.returncode != 0:
                print(f"运行 check.py 失败: {result.stderr}")
                return {'fixed_files': [], 'skipped_files': [], 'errors': [result.stderr]}

            lines = check_output.split('\n')

            start_flag = False
            fix_error_dict: Dict[str, List[str]] = {}
            error_type = ''
            error_list = list(ERROR_LIST.values())

            # 将检查结果按问题分类
            for line in lines:
                if start_flag:
                    if line.strip() == '':
                        start_flag = False
                    else:
                        fix_error_dict[error_type].append(line.strip())
                else:
                    for index, error_item in enumerate(error_list):
                        if error_item in line.strip():
                            start_flag = True
                            error_type = error_item
                            fix_error_dict[error_type] = []
                            del error_list[index]

            return fix_error_dict

        except Exception as e:
            print(f"运行 check.py 时出错: {e}")

    def run_fix(self, fix_error_dict: Dict[str, List[str]]):

        # 修复 bind_url 不一致问题
        self._common_fix(ERROR_LIST['BIND_URL_ERROR'], fix_error_dict, self._fix_bind_url)
        # 修复 real case 不一致问题
        self._common_fix(ERROR_LIST['REAL_CASE_ERROR'], fix_error_dict, self._fix_real_case)
        # 修复 level 和 config 中的 level 不一致问题
        self._common_fix(ERROR_LIST['LEVEL_AND_CONFIG_LEVEL_NOT_SAME'], fix_error_dict, self._fix_level_not_same)
        # 修复 evaluation item 和 config 中的 evaluation_item 不一致问题
        self._common_fix(ERROR_LIST['EVALUATION_ITEM_AND_CONFIG_EVALUATION_ITEM_NOT_SAME'], fix_error_dict,
                         self._fix_evaluation_item)
        # 修复 scene 和 config 中的 scene 不一致问题
        self._common_fix(ERROR_LIST['SCENE_AND_CONFIG_SCENE_NOT_SAME'], fix_error_dict, self._fix_scene_not_same)
        # 修复 config 字段和所在文件夹名不一致问题
        self._common_fix(ERROR_LIST['CONFIG_FILED_AND_DIR_NAME_NOT_SAME'], fix_error_dict, self._fix_config_filed)
        # 提示手动修复 缺少同名方法 问题
        self._manual_repair(ERROR_LIST['MISSING_METHOD_WITH_THE_SAME_NAME'], fix_error_dict)
        # 提示手动修复 文件缺失 问题
        self._manual_repair(ERROR_LIST['FILE_MISSING'], fix_error_dict)


def main():
    # 获取命令行参数
    base_path = "sast-python3"  # 默认使用相对路径
    file_extensions = 'py'

    # 如果提供了参数，使用提供的参数
    if len(sys.argv) > 1:
        base_path = sys.argv[1]

    # 如果路径是相对路径，转换为绝对路径
    if not os.path.isabs(base_path):
        # 获取工作空间根目录（脚本目录的父级的父级目录）
        workspace_root = Path(__file__).parent.parent.parent
        base_path = str(workspace_root / base_path)

    if len(sys.argv) > 2:
        # 解析文件扩展名参数
        file_extensions = sys.argv[2]

    if not os.path.exists(base_path):
        print(f"错误: 路径 {base_path} 不存在")
        sys.exit(1)

    fixer = AutoFixerFromCheck(base_path, file_extensions)
    fix_error_dict = fixer.run_script()

    error_count = 0
    for key, value in fix_error_dict.items():
        error_count += len(value)

    if error_count == 0:
        print("\n✅ 没有发现问题，无需修复")
    else:
        print(f"🚀 发现 {error_count} 个问题，开始修复...")
        fixer.run_fix(fix_error_dict)

    if fixer.fixed_files > 0:
        print(f"\n✅ 修复完成，总共成功修复 {fixer.fixed_files} 个文件")
    if fixer.fixed_fail_files > 0:
        print(f"❌ 总共修复失败 {fixer.fixed_fail_files} 个文件")


if __name__ == '__main__':
    main()
