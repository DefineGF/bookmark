#### Server

- ##### 创建socket

    ```python
    server_socket = socket(AF_INET, SOCK_STREAM)
    ```

    - socket.AF_INET: IPv4
    - socket.AF_INET6: IPv6
    - socket.SOCK_STREAM:  流式；用于==TCP==
    - socket.SOCK_DGRAM:  用于UDP

    

- #####  绑定地址 & 接口

    ```python
    # '' 默认为本机的IP地址（windows用户通过 cmd 中的 ipconfig 命令获取 IPv4 地址
    # server_port 为端口号（0~65535）其中0-1023为公认端口号（比如http的80端口）
    server_socket.bind(('', server_port)) 
    ```

- ##### 限制最大连接数

    ```python
    server_socket.listen(1)
    ```

- ##### 连接

    ```python
    connection_socket, addr = server_socket.accept()
    ```

     - connection_socket: 代表一个TCP连接，用于发送/接收数据；
     - addr：addr[0] 表示客户端的ip地址；addr[1] 表示客户端的端口号

- #####  发送/接收数据并编码

    ```python
    msg_from_client = connection_socket.recv(1024).decode() # 接收数据
    connection_socket.send("hi, i have received~".encode('gbk')) # 发送数据
    ```

- ##### 关闭连接

    ```
    connection_socket.close()
    ```

**完整参见 TCPServer.python 文件** 



#### Client

- ##### 创建socket（同上)

- ##### 连接

    ```python
    # server_address: 服务端IP地址
    # server_port:    服务端TCP端口
    client_socket.connect((server_address, server_port))
    ```

- #####  发送/接收数据并编码

    ```python
    msg_to_server = input("input message to server:")
    client_socket.send(msg_to_server.encode("gbk"))
    msg_from_server = client_socket.recv(1024).decode('gbk')
    ```

- ##### 关闭连接

    ```python
    client_socket.close()
    ```


**完整参见TCPClient.python 文件**

