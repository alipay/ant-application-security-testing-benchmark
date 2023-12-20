### 如何提交verify代码(macbook)
在GitHub上提交代码时，如果你希望你的提交(commit)都显示为已验证 (verified)，你需要为你的Git提交设置GPG签名。下面是在MacBook上为Git提交设置GPG签名的详细流程：

检查GPG是否已安装：
打开你的终端，运行以下命令来检查GPG是否已安装：

gpg --version
如果没有安装，你可以使用Homebrew来安装GPG：

brew install gnupg
生成GPG密钥对：
如果你还没有GPG密钥，使用以下命令生成一个：

gpg --full-generate-key
按照提示填写你的姓名和电子邮箱地址，并设置一个密码。

获取GPG密钥ID：
生成密钥后，运行以下命令来获取你的GPG密钥ID：

gpg --list-secret-keys --keyid-format LONG
输出将包含一个 sec 字样，后面跟着的16位字符就是你的密钥ID。

将GPG密钥添加到Git配置：
将你的密钥ID告诉Git：

git config --global user.signingkey YOUR_GPG_KEY_ID
请将 YOUR_GPG_KEY_ID 替换为你的密钥ID。

设置Git自动签名提交：
设置Git在每次提交时自动使用你的GPG密钥来进行签名：

git config --global commit.gpgsign true
将GPG公钥添加到GitHub账户：
首先，导出你的GPG公钥：

gpg --armor --export YOUR_GPG_KEY_ID
将输出的公钥文本复制到剪贴板。然后，登录到你的GitHub账户，进入“Settings（设置）”-> “SSH and GPG keys（SSH和GPG密钥）”-> “New GPG key（新GPG密钥）”，将公钥粘贴到文本框中，并保存。

测试签名提交：
在本地仓库中做一次提交来测试GPG签名是否生效：

git commit -m "Your commit message"
由于你设置了自动签名，不需要添加 -S 参数。

推送到GitHub：
将更改推送到GitHub：

git push
在GitHub上查看你的提交，应该会看到一个 "Verified" 标签，表明你的提交是经过GPG签名的。

如果你之前已经有过提交，但是没有签名，你可以重新签名那些提交并强制推送到GitHub。但请注意，这会改变你的提交历史，如果你是在一个与他人一起工作的共享仓库中，这样做可能会对其他贡献者造成问题。通常，这种情况下最好是从现在开始签名新的提交。如果仍需重新签名，请使用 git commit --amend --no-edit -S 为最近的提交添加签名，或使用 git rebase 来修改更早的提交。再次提醒，这些操作会改变git历史，请谨慎操作。

