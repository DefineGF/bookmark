#### Http 性能优化

##### 缺陷

使用Http协议探知服务器上是否有内容更新，必须频繁的到服务器进行确认；如果没有更新则产生徒劳的通信；



##### Ajax 

异步JavaScript与xml技术，有效利用JavaScript 和 DOM的操作，达到局部web页面替换加载的异步通信；

只更新一部分页面，从而降低数据量；

但是：实时的从服务器获取内容，有可能导致大量请求产生。



##### Comet

一旦服务端有内容更新，Comet不会等待请求，而是主动给客户端发送响应；

通常，服务端收到请求在处理完毕后会返回响应，但是为了实现推送功能保留响应，一次连接的持续时间延长，同时维持连接也耗费更多的资源；



##### SPDY

并没有完全改写HTTP，而是在应用层和传输层之间通过新加会话层的形式运作；

- 多路复用流：通过单一的TCP连接，无限制处理多个HTTP请求；
- 赋予请求优先级
- 压缩HTTP首部
- 推送功能
- 服务器提示功能





#### 全双工通信 WebSocket

一旦Web 服务器与客户端之间建立起WebSocket协议的通信连接，之后所有的通信都依赖这个专用协议进行；

建立在Http基础上的，因此连接建立请求仍需客户端发起；

##### 主要功能

- 推送功能：服务端向客户端推送数据；
- 减少通信量：首部信息很少，通信量也就减少了；

##### 连接过程

请求报文：

- Upgrade：websocket
- Sec-WebSocket-Key：握手中必不可少的键值；
- Sec-WebSocket-Protocol：使用的自协议

