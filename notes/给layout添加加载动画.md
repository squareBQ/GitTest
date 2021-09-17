# 给 `layout` 设置加载动画

`ViewGroup` 中有个 `android.R.styleable#ViewGroup_layoutAnimation` 属性，可以用来给 `layout` 设置加载时的动画效果。

- 首先，需要定义动画效果：

`fall_down_item.xml` 定义一个动画集：
```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="400">

    <translate
        android:fromYDelta="-20%"
        android:interpolator="@android:anim/decelerate_interpolator"
        android:toYDelta="0" />

    <alpha
        android:fromAlpha="0"
        android:interpolator="@android:anim/decelerate_interpolator"
        android:toAlpha="1" />

    <scale
        android:fromXScale="105%"
        android:fromYScale="105%"
        android:interpolator="@android:anim/decelerate_interpolator"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="100%"
        android:toYScale="100%" />

</set>
```

- 然后将动画定义到 `layoutAnimation` 中：

`fall_down_layout.xml` 文件： 
```xml
<?xml version="1.0" encoding="utf-8"?>
<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
    android:animation="@anim/fall_down_item"
    android:animationOrder="normal"
    android:delay="15%" />
```

- 最后将动画应用到对应的 `layout` 文件的根 `ViewGroup` 上即可

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layoutAnimation="@anim/fall_down_layout">

    <!--布局详细-->

</android.support.constraint.ConstraintLayout>

```

由此，使用该布局的页面在加载布局时，将会以定义的动画效果进行布局展示；（以上效果为，从上往下坠落）