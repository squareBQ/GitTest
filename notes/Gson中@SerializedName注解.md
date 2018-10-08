# Gson中@SerializedName注解

### @SerializedName注解
使用Gson解析json成对象时默认是将json里对应字段的值解析到java对象里对应字段的属性里面。

```java
场景：我们自己定义的java对象里的属性名跟json里的字段名不一样，这种情况如何处理。
解决：使用@SerializedName注解来将对象里的属性跟json里字段对应值匹配起来。@SerializedName注解最大的作用就是属性重命名。
```

> @SerializedName注解提供了两个属性，`value`和`alternate`
> 
> `value`接收一个值，`alternate`接收一个String数组
> 
> __注：alternate需要2.4以上版本__

```java
@SerializedName("user_name")
String userName;

@SerializedName(value = "user_name", alternate = {"user", "name", "username"})
String userName;
```

> 当上面三个属性`user`、`name`、`username`中出现任意一个时均可以得到正确的结果。__注：当多种情况同时出现时，以最后一个出现的值为准__。
> 
> `value`的值与`alternate`数组中的值为互斥关系，每个字段有且只能有一个！！！（个人理解为，alternate数组为value值的替换）


参考：[Gson中的@SerializedName注解](https://www.jianshu.com/p/b1ac639b858a)