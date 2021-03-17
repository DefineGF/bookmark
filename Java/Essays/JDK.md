##### JDK：Java Development Kit

包括工具：

- Java 运行时的环境（JRE）
- 解释器（Java）
- 编译器（javac）
- Java 归档（jar ——一种软件包文件格式）
- 文档生成器（Javadoc）



##### JRE：Java Runtime Enviroment 

由 Java 虚拟机（JVM）、核心类、支持文件组成



##### JVM：Java Virtual Machine

- JVM规范要求
- 满足 JVM 规范要求的一种具体实现（一种计算机程序）
- 一个 JVM 运行实例，在命令提示符下编写 Java 命令以运行 Java 类时，都会创建一个 JVM 实例。

 ![img](F:\Typora\Nodes\java\随笔\14805043-171ef9e473a9f85d.png)



##### JDK 和 JRE 交互

- 首先 JDK 中的编译器（javac）对代码进行编译——存储在`.java`文件中的源代码被编译成字节码存储在`.class`文件中

- 运行过程发生在 JRE 中：

    1. Class Loader 加载执行程序所需的全部类；
    2. Byte Code Verifier 验证代码的格式和合法性；
    3. Interpreter 加载并执行字节码；

    

 <img src="F:\Typora\Nodes\java\随笔\14805043-c2eeca7fbdc0935d.jpg" alt="img" style="zoom:50%;" />