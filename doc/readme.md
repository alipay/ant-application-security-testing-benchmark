## Mermind线上编辑器-  [ https://mermaid.live/edit](https://mermaid.live/edit)
### 评价体系格式

```mermind
graph LR
L1H1["引擎能力评估（Java）"]==>L2H1["完整度"]
L2H1["完整度"]==>L3H1["基础跟踪能力"]
L1H1["引擎能力评估（Java）"]==>L4H1["精准度"]
L1H1["引擎能力评估（Java）"]==>L5H1["性能"]
```

1.评价体系的编写语法非常简单，父节点==>子节点

2.节点使用'LxHx'进行命名，一般指列和行,当然在您手动编辑脑图是可能很难数清楚自己编辑的列行；您只要保证您所加的节点不重复即可，例如您可以可以加一个'MaHb'节点
```mermind
graph LR
L1H1["引擎能力评估（Java）"]==>L2H1["完整度"]
L2H1["完整度"]==>L3H1["基础跟踪能力"]
L1H1["引擎能力评估（Java）"]==>L4H1["精准度"]
L1H1["引擎能力评估（Java）"]==>L5H1["性能"]
L5H1["性能"]==>MaHb["我的新节点"]
```
3.节点的文字描述通常使用中文/英文/()/-这些字符，尽量不要使用复杂的符号；使用复杂符号需要在自己的仓库中充分测试，无异常展示再提交



### 常见问题
1.节点的文字描述中如需要使用单引号,例如:MaHb["描述'引号'文本"]

[mermind官方文档](https://mermaid.js.org/intro/getting-started.html#_1-using-the-mermaid-live-editor)