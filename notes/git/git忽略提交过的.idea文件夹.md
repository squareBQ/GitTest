# Git 忽略提交过的.idea文件夹

对于已经提交到远程仓库的`.idea/`文件夹，即使后面设置了忽略也不起作用了。这时，需要使用git操作
删除远程仓库中的文件夹。具体步骤如下：

 1. 打开 `terminal` 终端输入框
 2. 键入 `git rm -r --cached .idea` (如果报错可以尝试 `git rm --cached .idea -r`)
 3. 键入 `git commit -m "delete ignored idea/ dir"`
 4. push到远程仓库即可