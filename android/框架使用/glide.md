#### Glide

##### 导入

```
implementation 'com.github.bumptech.glide:glide:3.7.0'
```



##### 使用

```java
Glide.with(this).load(url).into(imageView);
```

可以加载的类型：

```java
// 加载本地图片
File file = new File(getExternalCacheDir() + "/image.jpg");
Glide.with(this).load(file).into(imageView);

// 加载应用资源
int resource = R.drawable.image;
Glide.with(this).load(resource).into(imageView);

// 加载二进制流
byte[] image = getImageBytes();
Glide.with(this).load(image).into(imageView);

// 加载Uri对象
Uri imageUri = getImageUri();
Glide.with(this).load(imageUri).into(imageView);
```

占位图（在未加载完之前显示）：

```java
Glide.with(this)
     .load(url)
     .placeholder(R.drawable.loading)
     .diskCacheStrategy(DiskCacheStrategy.NONE) // 禁止glide提供缓存
     .error(R.drawable.error) // 加载失败时显示
     .into(imageView);
```

