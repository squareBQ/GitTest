# Android中的RTL Layout
#### RTL由来
平时我们的布局习惯都是：从左到右、从上到下。但在某些国家却是：从右到左、从下到上，比如阿拉伯语、希伯来语等。。

因此，对于从右到左的习惯，从Android 4.2开始，Android SDK支持一种从右到左（RTL：right-to-left）UI布局方式。

> __RTL Layout，就是把界面的右边作为原点，布局方式为：从右到左，从上到下__

#### RTL Layout实现
 1. 在`AndroidManifest.xml`文件中将`application`标签的`android:supportsRtl`属性值设置为`true`
 2. 将响应标签的`android:layoutDirection`属性设置为`rtl`

![](imgs/rtl_01.png)

布局样式：

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <!--默认ltr-->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#ff0000"
            android:padding="10dp"
            android:text="Hello World!"
            android:textColor="@android:color/white" />

    </LinearLayout>

    <!--设置rtl-->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layoutDirection="rtl">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#ff0000"
            android:padding="10dp"
            android:text="Hello World!"
            android:textColor="@android:color/white" />

    </LinearLayout>

</LinearLayout>
```

实际效果：

![](/imgs/rtl_02.png)