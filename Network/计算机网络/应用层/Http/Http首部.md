

### 首部

实际用途：

- 通用首部字段：请求和响应都会用到；
- 请求首部字段：客户端请求；
- 响应首部字段：服务端响应；
- 实体首部字段：请求 & 响应

缓存代理和非缓存代理：

- 端到端首部
- 逐跳首部



#### 通用首部字段

##### Cache-Control

client <--> 缓存服务器 <--> 源服务器

- public：表明其他用户也可利用缓存

    ```
    Cache-Control: public 
    ```

    

- private：响应只以特定的用户作为对象；对于其他用户发送来的请求，缓存服务器则不会返回缓存；
- no-cache：缓存服务器返回缓存前向源服务器确认以保证返回最新的；
- no-store：不进行缓存（敏感字段）
- on-if-cached：客户端仅在缓存服务器本地缓存目标资源的情况下要求返回；



##### Connection

控制不再转发给代理的首部字段；

管理持久连接；



#### 请求首部字段

##### If-Match

```
If-Match: "实体标记值"
```

实体标记（ETag） 与特定资源关联的确定值。资源更新后ETag也会随之更新；



##### If-Modified-Since

- 若在指定时间段之后更新，则服务器处理请求；
- 若在指定时间段之后没有更新过，则返回状态码 304 Not Modified



#### Cookie 相关字段

##### Set-Cookie

- expires：指定 浏览器发送Cookie的有效期；仅限于维持浏览器会话（Session)
- secure：限制web页面仅在HTTPS安全连接是才能发送Cookie；
- HttpOnly：使JavaScript 脚本无法获得Cookie，防止跨站脚本攻击（XSS）对Cookie的窃取；



#### 其他首部字段

##### X-Frame-Options

属于Http响应首部，控制网站内中在其他Web网站的Frame标签中的显示问题；

主要目的为了防止点解劫持攻击（clickJacking）；

- DENY：拒绝
- SAMEORIGIN：仅在同源域名下的页面匹配时许可；



##### DNT do not track

拒绝个人信息被收集；