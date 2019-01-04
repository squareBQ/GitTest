# 本地git仓库关联第N个远程仓库

### 1.命令行添加
```
git remote add {仓库名} {仓库地址}

例如：git remote add develop http://git.xxxxxx.net/****.git
```
完成之后可使用`git remote -v`查看远程仓库关联情况

如下：

```
agent_origin    http://git.devops.net/test.git (fetch)
agent_origin    http://git.devops.net/test.git (push)
origin  http://xxx.xx.xx.xx:8010/Android/test.git (fetch)
origin  http://xxx.xx.xx.xx:8010/Android/test.git (push)
```

### 2.修改config文件添加
修改.git/config文件，加入另一个远程仓库，并为其命名，比如称为agent：

```
[remote "origin"]
	url = https://git.oschina.net/***/****.git
	fetch = +refs/heads/*:refs/remotes/origin/*
[remote "agent"]
	url = https://github.com/****/****.git
	fetch = +refs/heads/*:refs/remotes/origin/*
[branch "master"]
	remote = origin
	remote = agent
	merge = refs/heads/master
```

### 3.操作

#### 3.1 Pull操作
使用以下命令，可以分别从两个远程仓库pull：

```
git pull origin master
git pull agent master
```

#### 3.2 Push操作
使用以下命令，可以分别push到两个远程仓库：

```
git push origin master
git push agent master
```

#### 3.3 fetch操作
使用fetch操作，获取远程仓库的相关信息：  
（主要用于添加新的远程仓库之后，获取远程仓库信息到本地）

```
git fetch agent
```

![git常用命令速查表](https://7n.w3cschool.cn/attachments/image/20170206/1486348362884912.jpg)
