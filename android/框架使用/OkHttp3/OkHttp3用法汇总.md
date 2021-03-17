基本使用

```java
RequestBody requestBody=new FormBody.Builder()
                                    .add("key","value")
                                    .build();
```



#### 执行方式

##### 同步方式

```java
Response response=client.newCall(request).execute();
```



##### 异步方式

响应：

```java
Response response = client.newCall(request).enqueue(new Callback(){
        @Override
        public void onFailure(Request request, IOException e) { // 失败回调
         
        } 
        @Override 
        public void onResponse(Response response) throws IOException {
         //成功 注意：这里是后台线程！
        }
        
    }    
);
```

请求：

```java
FormBody formBody = new FormBody.Builder()
                              .add("Uname",name)
                              .add("Upwd",password)
                              .build();

Request request = new Request.Builder()
                           .url(url)
                           .post(fromBody)
                           .build();
```

请求对象为键值对：FormBody

请求对象为对象：RequestBody

##### 发送Json

```java
private static final MediaType JSON = 
							MediaType.parse("application/json;charset=UTF-8");
Gson gson=new Gson();
String goodsJson=gson.toJson(goods);

RequestBody requestBody = RequestBody.create(JSON,goodsJson);
Request request=new Request.Builder()
        .post(requestBody)
        .url("http://www.baidu.com")
        .build();
OkHttpClient client = new OkHttpClient();
try {
    return client.newCall(request).execute();// 返回类型Response
} catch (IOException e) {
    e.printStackTrace();
}
return null;
```





##### 封装

```java
public class NetUtil {
    public static void connByRequest(Request request, Callback callBack){
        OkHttpClient client=new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(callBack);
    }
    
    public static void connByGET(String url,Callback callback){
        Request request=new Request.Builder()
                .url(url)
                .method("GET",null)
                .build();
        connByRequest(request,callback);
    }
    public static void connByPost(String url, RequestBody body, Callback callback){
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        connByRequest(request,callback);
    }
}
```

