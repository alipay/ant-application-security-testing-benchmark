import re

# 错误集合，用于初始化错误集
ERROR_LIST = {
    # 支持自动修复的问题
    'LEVEL_HAS_PLUS': 'level 含有 + 号',
    'CONFIG_FILED_AND_DIR_NAME_NOT_SAME': 'config 字段和所在文件夹名不一致',
    'LEVEL_AND_CONFIG_LEVEL_NOT_SAME': 'level 和 config 中的 level 不一致',
    'SCENE_AND_CONFIG_SCENE_NOT_SAME': 'scene 和 config 中的 scene 不一致',
    'BIND_URL_ERROR': 'bind_url 错误',
    'REAL_CASE_ERROR': 'real case 错误',
    'EVALUATION_ITEM_AND_CONFIG_EVALUATION_ITEM_NOT_SAME': 'evaluation item 和 config 中的 evaluation_item 不一致',
    # 不支持自动修复的问题
    'MISSING_METHOD_WITH_THE_SAME_NAME': '缺少同名方法',
    'FILE_MISSING': '文件缺失',
}

# 编译正则表达式以提高性能
COMMENT_START_PATTERN = re.compile(r'evaluation information start\n?', re.IGNORECASE | re.MULTILINE)
LEVEL_PATTERN = re.compile(r'level\s*=\s*(\d*\+?)\n?', re.IGNORECASE | re.MULTILINE)
BIND_URL_PATTERN = re.compile(r'bind_url\s*=\s*([^#\n\r]*)\n?', re.IGNORECASE | re.MULTILINE)
REAL_CASE_PATTERN = re.compile(r'real case\s*=\s*([^#\n\r]*)\n?', re.IGNORECASE | re.MULTILINE)
EVALUATION_ITEM_PATTERN = re.compile(r'evaluation item\s*=\s*([^#\n\r]*)\n?', re.IGNORECASE | re.MULTILINE)
SCENE_INTRODUCTION_PATTERN = re.compile(r'scene introduction\s*=\s*([^#\n\r]*)\n?', re.IGNORECASE | re.MULTILINE)
