# Lambda使用

### 语法

#### 基本使用

```
(parameters) -> { expression or statements }
```

下面是一些例子：

```
// 无参数，返回1+2的结果
() -> 1+2

// 接收一个参数(数字类型)，返回其2倍的值
x -> 2 * x

// 接收2个参数(数字)，返回表达式运算的结果
(x, y) -> x + y

// 多个语句要永达括号包裹，并且返回值要用return指明
(x, y) -> {
	int result = x + y;
	System.out.print(result);
	return  result;
}

// 接收string对象，并在控制台打印
s -> System.out.print(s);
```

#### Java中使用Lambda

使用匿名内部类的写法

```
new Thread(new Runnable() {
	@Override
	public void run() {
		System.out.print("Hello World!");
	}
}).start();
```

使用Lambda表达式：

```
new Thread(() -> System.out.print("Hello World!")).start();
```

改写过程一目了然，就是原本写匿名内部类的地方，改写成了：

```
参数列表 -> 表达式或者代码库块
```
<font color="#f00">注：此处参数是指形式参数</font>

总结一下Lambda表达式的使用条件：

 1. 函数(可以是构造函数)的参数是接口
 2. 这个接口只包含一个抽象方法

#### 自定义接口使用Lambda

第一步，创建一个Person类

```
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

第二步，创建一个接口，用来打印Person

```
public interface IPersonPrinter {
    void printPerson(Person p);
}
```

第三步，创建方法打印Person

```
private static void printPerson(Person p, IPersonPrinter printer) {
        printer.printPerson(p);
    }
```

接下来在main函数中调用第三部的方法，先来看看不实用Lambda的方式：

```
printPerson(person, new IPersonPrinter() {
    @Override
    public void printPerson(Person p) {
        System.out.print(p.toString());
    }
 });
```

很简单的操作缺需要这么多行代码，只有一行代码是有用的。而使用Lambda表达式之后，只需要一行代码：

```
printPerson(person, p -> System.out.print(p.toString()));
```

完整的调用代码如下：

```
public class LambdaDemo {
    public static void main(String[] arg) {
        Person person = new Person("Smith", 26);
        printPerson(person, p -> System.out.print(p.toString()));
    }

    private static void printPerson(Person p, IPersonPrinter printer) {
        printer.printPerson(p);
    }
}
```

### 如何使Android Studio支持Lambda

在Android N出现之前，大家都是使用`gradle-retrolambda`插件支持的。

下面介绍Android N支持Lambda表达式

首先确保你的jdk已经升到1.8，然后将__工程根目录__的`build.gradle`中的gradle版本改成最新版本

__moudle目录__的`build.gradle`配置如下：

```
android {
    compileSdkVersion 'android-N'
    // buildTools必须用24以上
    buildToolsVersion "24.0.0 rc3"

    defaultConfig {
        applicationId "com.github.smarxpan"
        minSdkVersion 'N'  //使用Android N最小版本也要是Android N
        targetSdkVersion 'N'
        versionCode 1
        versionName "1.0"
        
        // 使用jack(Java Android Compiler Kit)工具链
        jackOptions{
            enabled true
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    // 配置JDK为1.8
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

这样我们就可以将常写的某些匿名内部类写成Lambda表达式了：

```
findViewById(R.id.bt).setOnClickListener(view -> {
	Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
});
```

#### Lambda和匿名内部类的区别

看起来Lambda表达式只是简化了匿名内部类的书写，事实上Lambda并非匿名内部类的<font color="#0000ff">语法糖</font>，Lambda的效率比匿名内部类要高。

具体可查:[深入探索Java 8 Lambda表达式](http://www.infoq.com/cn/articles/Java-8-Lambdas-A-Peek-Under-the-Hood)