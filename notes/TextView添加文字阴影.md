### TextView添加文字阴影

设置TextView字体阴影需要四个相关参数：

 1. `android:shadowColor`： 阴影的颜色
 2. `android:shadowDx`：    水平方向上的偏移量
 3. `android:shadowDy`：    垂直方向上的偏移量
 4. `android:shadowRadius`：阴影的范围

具体实现：

```
<TextView
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:maxLines="1"
	android:shadowColor="#80000000"
	android:shadowDx="1.0"
	android:shadowDy="1.0"
	android:shadowRadius="2.0"
	android:textColor="@color/white"
	android:textSize="32sp"
	tools:text="515499158" />
```

实际效果：

![图片描述](../imgs/text_shadow.png)
