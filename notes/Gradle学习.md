## Gradle学习  

#### 1.安装Gradle

Gradle需要运行在一个Java环境  

* 安装一个Java JDK 或者 JRE。Java版本需要6以上
* Gradle自带Groovy库，不需要重新安装

#### 2.构建脚本基础

Gradle里的任何东西都基于这两个基础概念：

* projects (项目)
* tasks (任务)

每一个构建都是由一个或多个projects构成的，一个project到底代表什么取决于你想用Gradle做什么。（一个project可以代表一个JAR或者一个网页应用；也可以代表一个发布的ZIP压缩包，而这个压缩包可能是由许多其他项目的JARs构成的。） 
 
每一个project是由一个或多个tasks构成的。一个task代表一些更加细化的构建。  

#### 3.Hello world

可以通过gradle命令运行一个Gradle构建。

gradle命令会再当前目录中查找一个叫`build.gradle`的文件，称这个文件为构建脚本(build script)，但是严格来说它是一个构建配置脚本(build configuration script)，这个脚本定义了一个`project`和它的`tasks`。  

##### 第一个构建脚本

build.gradle

```
task hello {
	doLast {
		println 'Hello world!'
	}
}
```
在命令行里，进入脚本所在的文件夹然后输入`gradle -q hello`来执行构建脚本：

输出：

```
> gradle -q hello
Hello world!
```

这里发生了什么？

这个构建脚本定义了一个独立的task，叫做hello，并且加入了一个action，当你运行`gradle hello`，Gradle执行叫做hello的task，也就是执行了你所提供的action。这个action是一个包含了一些Groovy代码的闭包(closure)。

> __补充：命令里的 -q 是干什么的？__

> __-q__ 代表quiet模式。不会生成Gradle的日志信息，只会看到tasks的输出，使输出更加清晰。

##### 快捷的任务定义

build.gradle

```
task hello << {
	println 'Hello world!'
}
```
与第一个构建比较，doLast被替换成了 <<。它有一样的功能，但看上去更加简洁了。

##### 在Gradle任务里使用Groovy

build.gradle

```
task upper << {
	String someString = 'mY_nAmE'
	println "Original: " + someString
	println "Upper case: " + someString.toUpperCase()
}
```

gradle -q upper 命令输出：

```
> gradle -q upper
Original: mY_nAmE
Upper case: MY_NAME
```

#### 任务依赖

##### 申明任务之间的依赖关系

build.gradle

```
task hello << {
	println 'Hello world!'
}

task intro(dependsOn: 'hello') << {
	println "I'm Gradle"
}
```

gradle -q intro 命令输出：

```
> gradle -q intro
Hello world!
I'm Gradle
```

intro依赖于hello，所以执行intro的时候hello命令会被优先执行来作为启动intro任务的条件。<font color="#ff0000">在加入一个依赖之前，这个依赖的任务不需要提前定义。</font>

##### Lazy dependsOn - 其他任务还没有存在

build.gradle

```
task taskX(dependsOn: 'taskY') << {
	println 'taskX'
}

task taskY << {
	println 'taskY'
}
```

gradle -q taskX 命令输出：

```
> gradle -q taskX 
taksY
taskX
```

taskX到taskY的依赖在taskY被定义之前就已经声明了。这一点对于多任务构建是非常重要的。

#### 动态任务

Groovy不仅仅被用来定义一个人物可以做什么，也可以使用它来动态的创建任务。

##### 动态的创建一个任务

build.gradle

```
4.times { counter ->
	task "task$counter" << {
		println "I'm task number $counter"
	}
}
```

这里动态创建了task0,task1,task2,task3

gradle -q task1 命令输出：

```
> gradle -q task1
I'm task number 1
```

#### 使用已经存在的任务

当任务创建之后，它可以通过API来访问。

##### 通过API访问一个任务 - 加入一个依赖

build.gradle

```
4.times { counter ->
	task "task$counter" << {
		println "I'm task number $counter"
	}
}

task0.dependsOn task2, task3
```

gradle -q task0 命令的输出：

```
> gradle -q task0
I'm task number 2
I'm task number 3
I'm task number 0
```
或者你可以给一个已经存在的任务加入行为(action)

##### 通过API访问一个任务 - 加入行为

build.gradle

```
task hello << {
	println 'Hello Earth!'
}

hello.doFirst {
	println 'Hello Venus'
}

hello.doLast {
	println 'Hello Mars'
}

hello << {
	println 'Hello Jupiter'
}
```

gradle -q hello 命令输出：

```
> gradle -q hello
Hello Venus
Hello Earth!
Hello Mars
Hello Jupiter
```

`doFirst`和`doLast`可以被执行许多次，分别可以在任务动作列表的开始和结束加入动作。当任务执行的时候，在动作列表里的动作将被按顺序执行。在这里第四个行为中 `<<` 操作符是 `doLast`的简单别称。

#### 短标记法

之前的示例，短标记 `$` 可以访问一个存在的任务。也就是说每个任务都可以作为构建脚本的属性：

##### 当成构建脚本的属性来访问一个任务

build.gradle

```
task hello << {
	println "Hello world!"
}

hello.doLast {
	println "Greetings from the $hello.name task."
}
```

gradle -q hello 命令输出：

```
> gradle -q hello
Hello world!
Greetings from the hello task.
```

这里的name是任务的默认属性，代表当前任务的名称，这里是hello。
这样使得代码易于读取，特别是当使用了由插件提供的任务时尤其如此。

#### 自定义任务属性

可以给任务加入自定义的属性。如加入一个叫做 myPorperty 属性，设置一个初始值给 ext.myPorperty。然后，该属性就可以像一个预定义的任务属性那样被读取和设置了。

##### 给任务加入自定义属性

build.gradle

```
task myTask {
	ext.myProperty = "myValue"
}

task printTaskProperties << {
	println myTask.myProperty
}
```

gradle -q printTaskProperties 命令输出：

```
> gradle -q printTaskPreperties
myValue
```

#### 默认任务

Gradle允许在脚本中定义一个或多个默认任务

##### 定义默认任务

build.gradle

```
defaultTasks 'clean', 'run'

task clean << {
	println 'Default Cleaning!'
}

task run << {
	println 'Default Running!'
}

task other << {
	println "I'm ont a default task!"
}
```

gradle -q 命令输出：

```
> gradle -q
Default Cleaning!
Default Running!
```
等价于 `gradle -q clean run`，在一个多项目构建中，每一个子项目都可以有它特别的默认任务，如果一个子项目没有特别的默认任务，父项目的默认任务将会被执行。

#### 一个基础的Java项目

##### 使用Java插件

build.gradle

```
apply plugin: 'java'
```
它会把Java插件加入到你的项目中，这意味着许多预定制的任务会被自动加入到你的项目里。

都加入了什么可以执行的任务呢？

可以在项目目录下使用`gralde tasks`来列出该项目的所有任务。

```
zahideMacBook-Pro:GradlePot zahi$ gradle tasks

> Task :tasks

------------------------------------------------------------
All tasks runnable from root project
------------------------------------------------------------

Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'GradlePot'.
components - Displays the components produced by root project 'GradlePot'. [incubating]
dependencies - Displays all dependencies declared in root project 'GradlePot'.
dependencyInsight - Displays the insight into a specific dependency in root project 'GradlePot'.
dependentComponents - Displays the dependent components of components in root project 'GradlePot'. [incubating]
help - Displays a help message.
model - Displays the configuration model of root project 'GradlePot'. [incubating]
projects - Displays the sub-projects of root project 'GradlePot'.
properties - Displays the properties of root project 'GradlePot'.
tasks - Displays the tasks runnable from root project 'GradlePot'.

Verification tasks
------------------
check - Runs all checks.
test - Runs the unit tests.

Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.
Pattern: upload<ConfigurationName>: Assembles and uploads the artifacts belonging to a configuration.

To see all tasks and more detail, run gradle tasks --all

To see more detail about a task, run gradle help --task <task>


BUILD SUCCESSFUL in 0s
1 actionable task: 1 executed
```

#### 示例

```
// 建立项目&外部依赖

apply plugin: 'java'

repositories { // 仓库
	mavenCentral()
}

dependencies {
	compile group: 'commons-collections', name: 'commons-collections', version: '3.2'
	testCompile group: 'junit', name: 'junit', version: '4.+'
}
// compile 'group:name:version'
```