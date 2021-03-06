#### Http缺点

- 通信使用明文；（窃听）
- 不验证对方身份；（伪装）
- 无法验证报文完整性；（篡改）

HTTP + 加密 + 认证 + 完整性保护 = HTTPS

##### 加密

- 通信加密

    通过和SSL（安全套接层）或者 TLS（安全传输层）组合使用，加密HTTP通信内容；

    与SSL组合使用的HTTP被称为 HTTPS；

- 内容加密

    要求Client 和 Server同时具有加密和解密的功能；



##### 防伪装

通过SSL提供的证书：客户端通过持有证书完成个人身份的确认，也可完成对Web网站的认证环节；



##### 验证完整性

或可遭受中间人攻击（攻击者拦截并篡改内容）;

通常使用 MD5 和 SHA-1 等散列值校验方法，以及用来确认文件的数字签名；





#### 用户身份认证

##### BASIC 认证（基本认证）

- 客户端发送请求
- 服务端返回状态码 401 响应；
- 用户 ID 和 密码 以Base64方式编码后发送；
- 服务端验证成功后 200 OK；验证失败则 401；



##### DIGEST 认证

同样使用 质询/响应的方式，但是不会发送明文；

1. 发送认证要求；
2. 接收到质询码，并计算响应码；
3. 发送响应码；



#####  SSL 客户端认证

需事先将客户端证书发送给客户端，且客户端安装证书；

1. 接收到认证资源的请求，服务器会发送 Certificate Request 报文，要求 客户端提供客户段证书；
2. 客户端把证书以Client Certificate 报文方式发送给服务器；
3. 服务器校验证书通过后领取证书内公开密钥进行HTTPS加密通信；