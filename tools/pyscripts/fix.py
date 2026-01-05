#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
基于 check.py 检查结果自动修复问题

使用方法:
    python fix.py [目录路径] [文件后缀]

示例:
    python fix.py sast-python3 py
    python fix.py sast-go go
"""

import os
import re
import sys
from pathlib import Path
from typing import Dict, List


class AutoFixerFromCheck:
    def __init__(self, base_path: str, file_extensions: List[str] = None):
        self.base_path = Path(base_path).resolve()
        self.file_extensions = file_extensions or ['go']
        self.fixed_files = []
        self.skipped_files = []
        self.errors = []
    
    def run_check_and_fix(self) -> Dict:
        """运行 check.py 并基于结果自动修复问题"""
        print("运行 check.py 检查问题...")
        
        # 获取当前脚本所在目录
        script_dir = Path(__file__).parent
        check_script = script_dir / "check.py"
        
        # 运行 check.py 获取检查结果
        import subprocess
        try:
            cmd = [
                sys.executable, 
                str(check_script), 
                str(self.base_path),
                ','.join(self.file_extensions)
            ]
            result = subprocess.run(cmd, capture_output=True, text=True, cwd=script_dir.parent.parent)
            check_output = result.stdout
            
            if result.returncode != 0:
                print(f"运行 check.py 失败: {result.stderr}")
                return {'fixed_files': [], 'skipped_files': [], 'errors': [result.stderr]}
            
        except Exception as e:
            print(f"运行 check.py 时出错: {e}")
            return {'fixed_files': [], 'skipped_files': [], 'errors': [str(e)]}
        
        # 解析 check.py 输出，提取需要修复的问题
        bind_url_issues = self._parse_check_output(check_output)
        
        if not bind_url_issues:
            print("✅ 未发现需要修复的问题！")
            return {'fixed_files': [], 'skipped_files': [], 'errors': []}
        
        print(f"发现 {len(bind_url_issues)} 个需要修复的问题")
        
        # 修复发现的问题
        for issue in bind_url_issues:
            file_path = Path(issue['file'])
            expected_bind_url = issue['expected']
            
            if self.fix_file_issue(file_path, expected_bind_url):
                print(f"✅ 已修复: {file_path}")
                self.fixed_files.append(str(file_path))
            else:
                print(f"⚠️ 跳过: {file_path}")
                self.skipped_files.append(str(file_path))
        
        return {
            'fixed_files': self.fixed_files,
            'skipped_files': self.skipped_files,
            'errors': self.errors
        }
    
    def _parse_check_output(self, check_output: str) -> List[Dict]:
        """解析 check.py 输出，提取 bind_url 不一致的文件"""
        issues = []
        lines = check_output.split('\n')
        
        current_file = None
        expected_path = None
        
        for line in lines:
            line = line.strip()
            
            # 查找文件路径 - 支持多种格式
            if line.startswith(str(self.base_path.name)) or 'sast-' in line:
                # 提取文件路径
                file_match = re.search(r'(.+\.py|.+\.go|.+\.js)', line)
                if file_match:
                    file_path = file_match.group(1)
                    # 构建完整文件路径
                    if file_path.startswith(str(self.base_path.name)):
                        full_path = self.base_path.parent / file_path
                    else:
                        full_path = self.base_path / file_path
                    current_file = str(full_path)
                    expected_path = None
            
            # 查找期望路径
            elif '期望:' in line and current_file:
                expected_match = re.search(r'期望:\s*(.+)', line)
                if expected_match:
                    expected_path = expected_match.group(1).strip()
                    
                    if current_file and expected_path:
                        issues.append({
                            'file': current_file,
                            'expected': expected_path
                        })
                        current_file = None
                        expected_path = None
        
        return issues
    
    def fix_file_issue(self, file_path: Path, expected_bind_url: str) -> bool:
        """修复文件中的问题"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 查找并替换bind_url
            bind_url_pattern = r'(bind_url\s*=\s*)([^\n\r]+)'
            new_content = re.sub(bind_url_pattern, f'\\1{expected_bind_url}', content)
            
            if new_content != content:
                # 直接写入修复后的内容
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.write(new_content)
                return True
            else:
                return False
                
        except Exception as e:
            self.errors.append(f"修复文件失败: {file_path} - {e}")
            return False
    
    def generate_report(self, results: Dict) -> None:
        """生成修复报告"""
        print("\n" + "="*60)
        print("修复结果报告")
        print("="*60)
        
        fixed = results['fixed_files']
        skipped = results['skipped_files']
        errors = results['errors']
        
        if not fixed and not skipped and not errors:
            print("✅ 未发现需要修复的问题！")
            return
        
        print(f"已修复 {len(fixed)} 个文件")
        print(f"跳过 {len(skipped)} 个文件")
        
        if fixed:
            print("\n📋 已修复的文件:")
            for file_path in fixed:
                print(f"  ✅ {file_path}")
        
        if skipped:
            print("\n⚠️  跳过的文件:")
            for file_path in skipped:
                print(f"  ⚠️  {file_path}")
        
        if errors:
            print("\n❌ 错误:")
            for error in errors:
                print(f"  ❌ {error}")
        
        if fixed:
            print(f"\n✅ 修复完成！")
            print("所有问题已修复")


def main():
    # 获取命令行参数
    base_path = "../../sast-python3"  # 默认使用相对路径
    file_extensions = ['py']
    
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
        extensions_str = sys.argv[2]
        file_extensions = [ext.strip() for ext in extensions_str.split(',')]
    
    if not os.path.exists(base_path):
        print(f"错误: 路径 {base_path} 不存在")
        sys.exit(1)
    
    print(f"使用文件扩展名: {', '.join(file_extensions)}")
    
    fixer = AutoFixerFromCheck(base_path, file_extensions)
    results = fixer.run_check_and_fix()
    fixer.generate_report(results)


if __name__ == "__main__":
    main()