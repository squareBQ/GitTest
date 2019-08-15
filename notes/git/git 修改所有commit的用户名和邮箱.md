# git 修改所有commit的用户名和邮箱

* 先修改本地用户名，邮箱

```
git config user.name '${new_name}'
git config user.email '${new_email}'
```

* 随意提交一个`commit`

* 项目根目录下新建一个`email.sh`脚本文件：

email.sh
```
#!/bin/sh

git filter-branch --env-filter '

OLD_EMAIL="old_email_address"
CORRECT_NAME="${new_name}"
CORRECT_EMAIL="${new_email_address}"

if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_COMMITTER_NAME="$CORRECT_NAME"
    export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
fi
if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_AUTHOR_NAME="$CORRECT_NAME"
    export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
fi
' --tag-name-filter cat -- --branches --tags
```

把 OLD_EMAIL 、CORRECT_NAME 、 CORRECT_EMAIL 改成自己的新旧邮箱用户名即可；

* 修改脚本文件权限

```
chmod +x email.sh
```

* 执行脚本

```
./email.sh
```
---

如果没有效果就多执行几次；
再不行就添加新的`commit`再执行；

如果遇到以下问题：

```
Cannot create a new backup. A previous backup already exists in refs/original/ Force overwriting the backup with -f
Bash
```

则执行如下命令后再执行`./email.sh`脚本命令：

```
git filter-branch -f --index-filter 'git rm --cached --ignore-unmatch Rakefile' HEAD
```

此时，虽然本地修改成功了，但是还需要覆盖github或gitlab，执行以下命令即可。

```
git push origin --force --all
git push origin --force --tags
```
