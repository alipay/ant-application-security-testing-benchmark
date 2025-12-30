#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
检查config.json中的level与对应文件注释中的level是否一致
并检测level格式是否包含类似"2+"、"3+"、"4+"这种格式
支持通过命令行参数指定文件后缀，默认使用.go
新增功能：
1. 检测文件路径是否和注释中的 bind_url 一致
2. 检测go、js、py2、py3检测文件内容中是否有文件同名方法/类
3. 支持同名方法检测白名单，跳过指定文件的同名方法/类检测（使用完整文件名匹配，包含扩展名）

使用方法:
    python check.py [目录路径] [文件后缀1,文件后缀2,...] [同名方法检测白名单文件1,同名方法检测白名单文件2,...]

示例:
    python check.py sast-go go,java,py
    python check.py sast-go go main.go,test.py
    python check.py sast-python3 py example.py,helper.js
    python check.py sast-go go,py,js main.go,test.py,example.java

如果不提供路径，则默认使用当前目录下的sast-go
如果不提供后缀，则默认使用.go后缀
如果不提供同名方法检测白名单，则检测所有文件

同名方法检测白名单说明:
- 支持完整文件名匹配（包含扩展名）
- 可以指定不同语言的同名文件，如：main.go, main.py, main.java
- 不会影响不同语言的同名文件检测

功能说明:
1. 检查config.json中的level值与文件注释中的level值是否一致
2. 检测level格式是否包含"+"号等特殊标记
3. 检查文件路径与注释中的bind_url是否一致
4. 检查文件内容中是否有与文件名同名的方法/类
5. 支持同名方法检测白名单跳过特定文件的同名方法/类检测
6. 检测config.json中声明的case文件是否实际存在（文件缺失检测）
"""

import json
import os
import re
import sys
from pathlib import Path
from typing import Dict, List, Tuple, Optional


class ConfigLevelChecker:
    def __init__(self, base_path: str, file_extensions: List[str] = None):
        self.base_path = Path(base_path).resolve()
        self.file_extensions = file_extensions or ['go']
        self.inconsistencies = []
        self.checked_files = 0
        
        # 同名方法检测白名单：跳过检测的完整文件名（包含扩展名）
        self.method_name_check_whitelist = [
            # 可以在这里添加需要跳过同名方法检测的完整文件名（包含扩展名）
            # 例如：'main.go', 'test.py', 'helper.js', 'Example.java'
            'cross_directory_021_T_a.go',
            'cross_directory_021_T_b.go',
            'public_var_cross_package_002_F_a.go',
            'public_var_cross_package_001_T_a.go',
            'cross_directory_022_F_a.go',
            'cross_directory_022_F_b.go',
            'cross_directory_023_T_a.go',
            'cross_directory_024_F_a.go',
            'cross_directory_025_T_a.go',
            'cross_directory_026_F_a.go',
            'cross_same_name_027_T.go',
            'cross_same_name_028_F.go',
            'cross_directory_029_T_a.go',
            'cross_directory_030_F_a.go'
        ]
    def find_config_files(self) -> List[Path]:
        """查找所有config.json文件，忽略target文件夹"""
        config_files = []
        for root, dirs, files in os.walk(self.base_path):
            # 忽略target文件夹
            dirs[:] = [d for d in dirs if d.lower() != 'target']
            
            if 'config.json' in files:
                config_files.append(Path(root) / 'config.json')
        return config_files
    
    def extract_level_from_file(self, file_path: Path) -> Optional[str]:
        """从文件中提取注释中的level值"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
                
            # 查找level = 数字的模式（包括2+、3+、4+等格式）
            level_pattern = r'level\s*=\s*(\d+\+?)'
            match = re.search(level_pattern, content)
            if match:
                return match.group(1)
            return None
        except Exception as e:
            print(f"读取文件失败: {file_path} - {e}")
            return None
    
    def extract_bind_url_from_file(self, file_path: Path) -> Optional[str]:
        """从文件中提取注释中的bind_url值"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
                
            # 查找bind_url = 路径的模式
            bind_url_pattern = r'bind_url\s*=\s*([^\n\r]+)'
            match = re.search(bind_url_pattern, content)
            if match:
                return match.group(1).strip()
            return None
        except Exception as e:
            print(f"读取文件失败: {file_path} - {e}")
            return None
    
    def check_method_name_consistency(self, file_path: Path) -> List[str]:
        """检查文件内容中是否有与文件名同名的方法或类"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 获取文件名（不含扩展名）
            filename_without_ext = file_path.stem
            
            # 根据文件扩展名选择不同的匹配模式
            file_extension = file_path.suffix.lower()
            
            if file_extension == '.go':
                # Go语言：func 函数名( 或 func 首字母大写的函数名(
                filename_upper = filename_without_ext[0].upper() + filename_without_ext[1:] if filename_without_ext else filename_without_ext
                
                method_patterns = [
                    rf'func\s+{re.escape(filename_without_ext)}\s*\(',
                    rf'func\s+{re.escape(filename_upper)}\s*\('
                ]
                
                for pattern in method_patterns:
                    if re.search(pattern, content):
                        return [filename_without_ext]
            elif file_extension in ['.py', '.py2', '.py3']:
                # Python：def 函数名( 或 async def 函数名(
                method_patterns = [
                    rf'def\s+{re.escape(filename_without_ext)}\s*\(',
                    rf'async\s+def\s+{re.escape(filename_without_ext)}\s*\('
                ]
                for pattern in method_patterns:
                    if re.search(pattern, content):
                        return [filename_without_ext]
            elif file_extension == '.js':
                # JavaScript：function 函数名( 或 const 函数名 = 或 函数名 = function(
                method_patterns = [
                    rf'function\s+{re.escape(filename_without_ext)}\s*\(',
                    rf'(?:const|let|var)\s+{re.escape(filename_without_ext)}\s*=',
                    rf'{re.escape(filename_without_ext)}\s*=\s*function\s*\('
                ]
                for pattern in method_patterns:
                    if re.search(pattern, content):
                        return [filename_without_ext]
            elif file_extension == '.java':
                # Java：public class 类名 或 class 类名
                class_patterns = [
                    rf'(?:public\s+)?class\s+{re.escape(filename_without_ext)}\s*[\{{\s]',
                    rf'(?:public\s+)?(?:final\s+)?class\s+{re.escape(filename_without_ext)}\s*[\{{\s]'
                ]
                for pattern in class_patterns:
                    if re.search(pattern, content):
                        return [filename_without_ext]
            
            return []
        except Exception as e:
            print(f"检查方法名/类名失败: {file_path} - {e}")
            return []
    
    def parse_compose_files(self, compose_str: str, config_dir: Path) -> List[Path]:
        """解析compose字段中的文件路径，支持通过参数指定的文件类型"""
        result_files = []
        
        # 移除!符号和括号
        clean_str = compose_str.replace('!', '').replace('(', '').replace(')', '')
        
        # 按&&分割多个条件，然后按||分割多个文件
        conditions = [part.strip() for part in clean_str.split('&&')]
        
        # 将扩展名转换为带点的格式
        supported_extensions = {f'.{ext.lstrip(".")}' for ext in self.file_extensions}
        
        for condition in conditions:
            # 处理||分隔的多个文件
            file_parts = [part.strip() for part in condition.split('||')]
            
            for part in file_parts:
                # 检查是否以支持的扩展名结尾
                file_ext = Path(part).suffix.lower()
                
                # 构建可能的文件路径
                found_file = None
                
                if file_ext in supported_extensions:
                    # 1. 直接路径（相对于config.json所在目录）
                    direct_path = config_dir / part
                    if direct_path.exists():
                        found_file = direct_path
                    else:
                        # 2. 在子目录中查找（递归查找所有匹配的子目录）
                        for root, dirs, found_files in os.walk(config_dir):
                            # 忽略target文件夹
                            dirs[:] = [d for d in dirs if d.lower() != 'target']
                            
                            potential_file = Path(root) / part
                            if potential_file.exists():
                                found_file = potential_file
                                break
                        
                        # 3. 如果没有找到，尝试在子目录中查找匹配的文件名
                        if not found_file:
                            for root, dirs, found_files in os.walk(config_dir):
                                dirs[:] = [d for d in dirs if d.lower() != 'target']
                                for file in found_files:
                                    if file == part:
                                        found_file = Path(root) / file
                                        break
                                if found_file:
                                    break
                
                elif '.' in part:  # 有扩展名但不在支持列表中
                    # 1. 直接路径
                    direct_path = config_dir / part
                    if direct_path.exists():
                        found_file = direct_path
                    else:
                        # 2. 在子目录中查找
                        for root, dirs, found_files in os.walk(config_dir):
                            dirs[:] = [d for d in dirs if d.lower() != 'target']
                            
                            potential_file = Path(root) / part
                            if potential_file.exists():
                                found_file = potential_file
                                break
                
                elif part:  # 没有扩展名，使用第一个扩展名作为默认
                    default_ext = self.file_extensions[0] if self.file_extensions else 'go'
                    filename_with_ext = f"{part}.{default_ext.lstrip('.')}"
                    
                    # 1. 直接路径
                    direct_path = config_dir / filename_with_ext
                    if direct_path.exists():
                        found_file = direct_path
                    else:
                        # 2. 在子目录中查找
                        for root, dirs, found_files in os.walk(config_dir):
                            dirs[:] = [d for d in dirs if d.lower() != 'target']
                            
                            potential_file = Path(root) / filename_with_ext
                            if potential_file.exists():
                                found_file = potential_file
                                break
                
                # 添加找到的文件
                if found_file:
                    result_files.append(found_file)
                else:
                    # 如果没有找到，仍然添加原始路径以便后续报告缺失
                    if file_ext in supported_extensions or '.' in part:
                        result_files.append(config_dir / part)
                    else:
                        default_ext = self.file_extensions[0] if self.file_extensions else 'go'
                        result_files.append(config_dir / f"{part}.{default_ext.lstrip('.')}")
                
        return result_files
    

    
    def check_config_file(self, config_path: Path) -> None:
        """检查单个config.json文件"""
        try:
            # 检查文件是否为空
            if config_path.stat().st_size == 0:
                print(f"跳过空文件: {config_path}")
                return
                
            with open(config_path, 'r', encoding='utf-8') as f:
                content = f.read().strip()
                if not content:
                    print(f"跳过空文件: {config_path}")
                    return
                    
                config = json.loads(content)  # 使用json.loads而不是json.load
            
            # 获取config文件所在目录
            config_dir = config_path.parent
            
            # 遍历config中的所有测试项
            for test_category, test_items in config.items():
                for test_item in test_items:
                    if 'scene_levels' not in test_item:
                        continue
                    
                    for scene_level in test_item['scene_levels']:
                        config_level = scene_level.get('level')
                        if not config_level:
                            continue
                        
                        # 检查config中的level是否包含+号
                        if config_level and '+' in str(config_level):
                            self.inconsistencies.append({
                                'config_file': str(config_path),
                                'config_level': config_level,
                                'go_file': str(config_path),
                                'go_level': str(config_level),
                                'status': 'config level格式包含+号'
                            })
                        
                        scene_list = scene_level.get('scene_list', [])
                        
                        for scene in scene_list:
                            compose_str = scene.get('compose', '')
                            if not compose_str:
                                continue
                            
                            # 解析出所有相关的文件
                            files = self.parse_compose_files(compose_str, config_dir)
                            
                            for file_path in files:
                                if not file_path.exists():
                                    self.inconsistencies.append({
                                        'config_file': str(config_path),
                                        'config_level': config_level,
                                        'go_file': str(file_path),
                                        'go_level': '文件不存在',
                                        'status': '文件缺失'
                                    })
                                    continue
                                
                                file_level = self.extract_level_from_file(file_path)
                                file_bind_url = self.extract_bind_url_from_file(file_path)
                                file_methods = self.check_method_name_consistency(file_path)
                                self.checked_files += 1
                                
                                # 检查文件中的level是否包含+号
                                if file_level and '+' in file_level:
                                    self.inconsistencies.append({
                                        'config_file': str(config_path),
                                        'config_level': config_level,
                                        'go_file': str(file_path),
                                        'go_level': file_level,
                                        'status': '文件level格式包含+号'
                                    })
                                elif file_level is None:
                                    self.inconsistencies.append({
                                        'config_file': str(config_path),
                                        'config_level': config_level,
                                        'go_file': str(file_path),
                                        'go_level': '未找到level',
                                        'status': '注释缺失'
                                    })
                                elif file_level != str(config_level):
                                    self.inconsistencies.append({
                                        'config_file': str(config_path),
                                        'config_level': config_level,
                                        'go_file': str(file_path),
                                        'go_level': file_level,
                                        'status': 'level不一致'
                                    })
                                
                                # 检查bind_url与文件路径一致性
                                if file_bind_url:
                                    # 构建期望的bind_url路径
                                    expected_bind_url = self._build_expected_bind_url(file_path)
                                    if file_bind_url != expected_bind_url:
                                        self.inconsistencies.append({
                                            'config_file': str(config_path),
                                            'config_level': config_level,
                                            'go_file': str(file_path),
                                            'go_level': file_bind_url,
                                            'status': f'bind_url不一致(期望: {expected_bind_url})'
                                        })
                                else:
                                    self.inconsistencies.append({
                                        'config_file': str(config_path),
                                        'config_level': config_level,
                                        'go_file': str(file_path),
                                        'go_level': '未找到bind_url',
                                        'status': 'bind_url注释缺失'
                                    })
                                
                                # 检查是否有同名方法 - 现在检测"缺少同名方法"才是问题
                                # 跳过同名方法检测白名单中的文件（使用完整文件名匹配）
                                filename_with_ext = file_path.name  # 获取完整文件名
                                if filename_with_ext not in self.method_name_check_whitelist and not file_methods:
                                    self.inconsistencies.append({
                                        'config_file': str(config_path),
                                        'config_level': config_level,
                                        'go_file': str(file_path),
                                        'go_level': '未找到同名方法',
                                        'status': '缺少同名方法'
                                    })
        
        except json.JSONDecodeError as e:
            print(f"跳过无效的JSON文件: {config_path} - {e}")
        except Exception as e:
            print(f"处理配置文件失败: {config_path} - {e}")
    
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
    
    def run_check(self) -> Dict:
        """运行完整的检查"""
        print("开始检查config.json中的level一致性...")
        
        config_files = self.find_config_files()
        print(f"找到 {len(config_files)} 个config.json文件")
        
        for config_file in config_files:
            # print(f"检查: {config_file}")
            # print(f"Config dir: {config_file.parent}")
            self.check_config_file(config_file)
            # print(f"当前已检查文件数: {self.checked_files}")
        
        return {
            'total_config_files': len(config_files),
            'total_checked_files': self.checked_files,
            'inconsistencies': self.inconsistencies
        }
    
    def generate_report(self, results: Dict) -> None:
        """生成检查报告"""
        print("\n" + "="*60)
        print("检查结果报告")
        print("="*60)
        
        inconsistencies = results['inconsistencies']
        
        if not inconsistencies:
            print("✅ 所有检查都通过！")
        else:
            print(f"❌ 发现 {len(inconsistencies)} 处不一致：")
            print()
            
            # 按config文件分组，然后按状态分组
            config_groups = {}
            for item in inconsistencies:
                config_file = item['config_file']
                if config_file not in config_groups:
                    config_groups[config_file] = {}
                
                status = item['status']
                if status not in config_groups[config_file]:
                    config_groups[config_file][status] = []
                config_groups[config_file][status].append(item)
            
            for config_file, status_groups in config_groups.items():
                # 显示相对于输入路径的路径，并加上基准路径前缀
                config_rel_path = os.path.relpath(config_file, self.base_path)
                config_display_path = os.path.join(self.base_path.name, config_rel_path)
                print(f"📁 Config: {config_display_path}")
                
                for status, items in status_groups.items():
                    print(f"  {status} ({len(items)} 处):")
                    for item in items:
                        if item['go_file'] != config_file:  # 避免显示config文件自身
                            # 显示相对于输入路径的路径，并加上基准路径前缀
                            file_rel_path = os.path.relpath(item['go_file'], self.base_path)
                            file_display_path = os.path.join(self.base_path.name, file_rel_path)
                            print(f"    📄 文件: {file_display_path}")
                            
                            # 统一输出问题描述，不再区分level和状态
                            status = item['status']
                            if status == 'level不一致':
                                print(f"       问题: {status} (Config: {item['config_level']}, 文件: {item['go_level']})")
                            elif status in ['缺少同名方法', '文件缺失', '注释缺失', 'bind_url注释缺失']:
                                print(f"       问题: {status}")
                            elif status.startswith('bind_url不一致'):
                                print(f"       问题: {status}")
                            elif status.startswith('config level格式包含+号') or status.startswith('文件level格式包含+号'):
                                print(f"       问题: {status}")
                            else:
                                print(f"       问题: {status}")
                        else:
                            config_display_path = os.path.join(self.base_path.name, config_rel_path)
                            print(f"    ⚠️  问题: {item['status']} (Config Level: {item['config_level']})")
                print()
        
        print(f"总计检查配置文件: {results['total_config_files']}")
        print(f"总计检查文件: {results['total_checked_files']}")


def main():
    # 获取命令行参数
    base_path = "../../sast-go"  # 默认使用相对路径
    file_extensions = ['go']
    method_name_check_whitelist_files = []  # 同名方法检测白名单文件列表
    
    # 如果提供了参数，使用提供的参数
    if len(sys.argv) > 1:
        base_path = sys.argv[1]
    
    # 如果路径是相对路径，转换为绝对路径
    if not os.path.isabs(base_path):
        base_path = os.path.join(os.getcwd(), base_path)
    
    if len(sys.argv) > 2:
        # 解析文件扩展名参数
        extensions_str = sys.argv[2]
        file_extensions = [ext.strip() for ext in extensions_str.split(',')]
    
    if len(sys.argv) > 3:
        # 解析同名方法检测白名单文件参数
        whitelist_str = sys.argv[3]
        method_name_check_whitelist_files = [name.strip() for name in whitelist_str.split(',')]
    
    if not os.path.exists(base_path):
        print(f"错误: 路径 {base_path} 不存在")
        sys.exit(1)
    
    print(f"使用文件扩展名: {', '.join(file_extensions)}")
    if method_name_check_whitelist_files:
        print(f"使用同名方法检测白名单: {', '.join(method_name_check_whitelist_files)}")
    
    checker = ConfigLevelChecker(base_path, file_extensions)
    # 合并硬编码白名单和命令行参数白名单
    if method_name_check_whitelist_files:
        checker.method_name_check_whitelist.extend(method_name_check_whitelist_files)
    # 去重避免重复
    checker.method_name_check_whitelist = list(set(checker.method_name_check_whitelist))
    
    results = checker.run_check()
    checker.generate_report(results)


if __name__ == "__main__":
    main()