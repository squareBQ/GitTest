![MacDown logo](http://goo.gl/5HBRdm)
# Hello, I'm MacDown

#####I think we are going to be friends.

## 基本语法

### 换行符
可以通过两个空格和一个回车键在某一行的结尾，来进行强制换行。

	There lines
	won't break
	
	There lines
	will break

### 强调突出显示
加粗 **Strong** `**Strong**` or `__Strong__` (Command-B)  
斜体 *Emphasize* `*Emphasize*` or `_Emphasize_`[^emphasize] (Command-I)  
删除线 ~删除线~


### 标题样式  
Header 1  
=========
Header 2
-----------
或者  
# Header 1  
## Header 2  
### Header 3  
#### Header 4  
##### Header 5  
###### Header 6
  
	Header 1
	========  
	
	Header 2
	--------  
	
	或者
	
	# Header 1
	## Header 2
	### Header 3
	#### Header 4
	##### Header 5
	###### Header 6
	
### 链接和邮箱  
在邮箱两侧加上尖括号(<>)就可以被点击了`<zahileoo@gmail.com>`  
<zahileoo@gmail.com>

链接字符串 `<http://www.baidu.com/>` <http://www.baidu.com/>  

带有标题的链接，标题是可以改变的 `[百度首页](http://www.baidu.com/)` [百度首页](http://www.baidu.com/)  

#### 索引超链: Reference方式  
[百度][1]  
[谷歌][2]  
![Github logo][3]  
[1]:http://www.baidu.com/  
[2]:http://www.google.cn/  
[3]:http://github.global.ssl.fastly.net/images/modules/logos_page/Octocat.png "github logo"

### 插入图片  
插入图片只需要`[站外图片上传中......(3)]`,插入图片和插入链接的语法相似，插入图片时前面多了一个`!`。  

	例如：![macdown Screenshot](http://upload-images.jianshu.io/upload_images/979175-5b32f52384271f2d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 列表  
* 列表前面必须有一个空格（加上符号之后空格）
* 无序的列表以`*`或者`-`开头  
	* 嵌入式的列表
		1. 有序列表
		2. 有序列表以`1.`开头
		1. 他不关心你使用的是哪个数字，都会按顺序递增
		5. 所以你可以每一行都以`1.`开头，它的顺序都会自动递增  
* 无序分层列表
	- item 1
	- item 2
	star:
		- item 2.1
		- item 2.2
		
```
* 列表前面必须有一个空格（加上符号之后空格）
* 无序的列表以`*`或者`-`开头  
* 嵌入式的列表
	1. 有序列表
	2. 有序列表以`1.`开头
	1. 他不关心你使用的是哪个数字，都会按顺序递增
	5. 所以你可以每一行都以`1.`开头，它的顺序都会自动递增   
* 无序分层列表
	- item 1
	- item 2
	star:
		- item 2.1
		- item 2.2
```

### 引用  
> 尖括号`>`用来进行引用  
不需要每一行都以`>`开头，只要在段落之间有两个空格  
> > 引用支持嵌套
> > > 多层嵌套  
引用内部也可使用markdown语法
>
> * 第一列
> * [第二列](http://www.baidu.com/)
> * ![第三列](http://www.baidu.com/img/bd_logo1.png)  

```
> 尖括号`>`用来进行引用  
不需要每一行都以`>`开头，只要在段落之间有两个空格  
> > 引用支持嵌套
> > > 多层嵌套  
引用内部也可使用markdown语法
>
> * 第一列
> * [第二列](http://www.baidu.com/ "百度首页")
> * ![第三列](http://www.baidu.com/img/bd_logo1.png "百度logo")  
```  

### 水平分割线  
可以使用3个`***`或者三个`---`，就可以展示水平线了。  

*** 


## MacDown高级语法  

### 表格  
First Header | Second Header 
-------------| -------------
Content Cell | Content Cell
Content Cell | Content Cell  
```
First Header | Second Header 
-------------| -------------
Content Cell | Content Cell
Content Cell | Content Cell  
```  

#### 另一种格式  
| Left Aligned | Center Aligned | Right Aligned | 
|:-------------|:--------------:|--------------:|
|col 3 is      |some wordy text |          $1600|
|col 2 is      |centered        |            $12|
|zebra stipes  |are neat        |             $1|  


```
| Left Aligned | Center Aligned | Right Aligned | 
|:-------------|:--------------:|--------------:|
|col 3 is      |some wordy text |          $1600|
|col 2 is      |centered        |            $12|
|zebra stipes  |are neat        |             $1|
```

### 代码块  
* 可以使用3个`~`或者3个 `  

```
print("hello world!");
```  

### 树结构

```
Android工程项目结构

new-structure
├─ library-foobar
├─ app
│  ├─ libs
│  ├─ src
│  │  ├─ androidTest
│  │  │  └─ java
│  │  │     └─ com/xxx/project
│  │  └─ main
│  │     ├─ java
│  │     │  └─ com/xxx/project
│  │     ├─ res
│  │     └─ AndroidManifest.xml
│  ├─ build.gradle
│  └─ proguard-rules.pro
├─ build.gradle
└─ settings.gradle
```

```
Google I/O 2015代码结构（包结构）

src
└─com
    └─domain
        └─app
            │  Config.java 配置数据、常量
            │
            ├─framework
            │      定义interface以及相关基类
            │
            ├─io
            │      数据定义（model）、数据操作（比如json解析，但不包括db操作）
            │
            ├─model
            │      定义model（数据结构以及getter/setter、compareTo、equals等等，不含复杂操作）
            │      以及modelHelper（提供便于操作model的api）
            │
            ├─provider
            │      实现ContentProvider，及其依赖的db操作
            │
            ├─receiver
            │      实现Receiver
            │
            ├─service
            │      实现Service（比如IntentService），用于在独立线程中异步do stuff
            │
            ├─ui
            │      实现BaseActivity，以及自定义view和widget，相关的Adapter也放这里
            │
            ├─util
            │      实现工具类，提供静态方法
            │
            ├─feature1
            │      Item.java                定义model
            │      ItemHelper.java          实现modelHelper
            │      feature1Activity.java    定义UI
            │      feature1DAO.java         私有db操作
            │      feature1Utils.java       私有工具函数
            │      ...其它私有class
            │
            ├─...其它feature

```

### 数学公式书写  
 \\[   
A^T_S = B  
\\]  

---  

## 写作目的  
###### 使用MarkDown仅为了排版出更好的文章，不关注技术细节。  

### 首行缩进  
&emsp;&emsp;&emsp;&emsp;写文章时，我们尝尝希望能够**首行缩进**，这时可以在首段加入`&ensp;`来输入一个空格，加入`&emsp;`来输入两个空格。  

```
&nbsp;//半角空格（英文）
&emsp;//全角空格（中文）
```

> &emsp;&emsp;一语未了，只听后院中有人笑声，说：“我来迟了，不曾迎接远客！”  

---

#### <font color="#ff0000">当然，最终境界永远都是笔下是语法，心中格式化 ：）</font>  
