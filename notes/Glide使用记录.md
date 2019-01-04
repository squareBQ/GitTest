## Glide图片加载框架使用记录

### Glide加载图片不使用缓存

Glide加载相同URL时由于缓存无法更新图片的问题。

1. 如果是本地图片，可以采用取消缓存的方式：
    ```
    Glide.with(getContext())
        .load(R.drawable.*****)
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .into(imageView);
    ```

2. 无论本地图片还是网络图片，都可以使用这个方法，就是使用Glide的方法`.signature(Key signature)`,
 通过创建一个签名，然后在图片更新的时候更改签名，达到重新加载的效果。
    ```
    Glide.load(url)
        .signature(new StringSignature(updateTime))
        .into(image);
    ```

 __方式二的原理：__

 Glide中的图片缓存key的生成是通过一个三列算法来实现的，所以很难手动去删除指定的图片缓存

 Glide的图片缓存都有对应的唯一标识符，如果是相同的，就不加载调用缓存；不过改变标识符很困难，所以
 Glide提供`signature()`方法，来附加一个数据到缓存key中。

 如果链接是文件，就使用`StringSignature`；如果链接的是多媒体，就用`MediaStoreSignature`。

参考：[深入探究Glide的缓存机制-郭霖](http://www.10tiao.com/html/227/201705/2650239697/1.html)