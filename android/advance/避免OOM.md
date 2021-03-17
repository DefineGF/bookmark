##### bitmap

BitmapFactory这个类提供了多个解析方法(decodeByteArray, decodeFile, decodeResource等)用于创建Bitmap对象；

- decodeFile() 用于内存中图片
- decodeStream() 用于网络上的图片
- decodeResource() 用于资源文件中的图片

贸然为图片开辟内存会导致 OOM，因此在加载图片之前，需要获取图片的参数, 从而根据情况对图片进行压缩：

```java
BitmapFactory.Options options = new BitmapFactory.Options();
options.inJustDecodeBounds = true; // 设置为true就可以让解析方法禁止为bitmap分配内存

BitmapFactory.decodeResource(getResources(), R.id.myimage, options);
int imageHeight = options.outHeight;
int imageWidth = options.outWidth;
String imageType = options.outMimeType;
```

