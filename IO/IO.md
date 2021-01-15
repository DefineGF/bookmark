java ![20201202134500318](F:\Typora\Nodes\java\IO\20201202134500318.png)

​											**IO流**

 ![20200922220641678](F:\Typora\Nodes\java\IO\20200922220641678.png)

​										**IO对象**

### 字节流

- #### FileInputStream/FileOutputStream

    ```java
    // 写
    OutputStream os = new FileOutputStream(file, true); // true: 文件末尾追加
    os.write(data.getBytes());                          // 获取 byte 数组
    os.close();
    
    // 读
    InputStream is = new FileInputStream(file);
    byte[] bytes = new byte[64];
    int length;
    while ((length = is.read(bytes)) != -1) {
    	sb.append(new String(bytes, 0, length));
    }
    is.close();
    ```

- #### BufferedInputStream/BufferedOutputStream (缓冲字节流)

    ```java
    // 写
    BufferedOutputStream bos = 
     					new BufferedOutputStream(new FileOutputStream(file, true));
    bos.write(data.getBytes());
    bos.close();
    
    // 读
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
    byte[] bytes = new byte[16];
    StringBuilder sb = new StringBuilder();
    int length;
    while ((length = bis.read(bytes)) != -1) {
    	sb.append(new String(bytes, 0, length));
    }
    bis.close();
    ```

 ![2020092115091457](F:\Typora\Nodes\java\IO\2020092115091457.png)

   - InputStream：抽象类，不能实例化；
   - FileInputStream：对文件进行读取操作；
   - PipedInputStream：管道字节输入流，多线程间的管道通信；
   - ByteArrayInputStream：从字节数组（byte[]）中以字节为单位读取；
   - FilterInputStream：对节点类进行封装；
   - DataInputStream：允许应用程序以与机器无关方式从底层输入流中读取基本java数据卡类型；
   - ObjectInputStream：对象输入流，用来提供对基本数据或者对象的持久存储；



### 字符流

- #### InputStreamReader/OutputStreamWriter

    ```java
    // 写
    OutputStreamWriter osw = 
        		new OutputStreamWriter(new FileOutputStream(file, true));
    osw.write(data); // 无需转换成 byte 数组（毕竟字符流)
    osw.close();
    
    // 读
    InputStreamReader isr = 
        			new InputStreamReader(new FileInputStream(file), "utf-8");
    char[] chars = new char[4];
    StringBuilder sb = new StringBuilder();
    int length;
    while ((length = isr.read(chars)) != -1) {
    	sb.append(chars, 0, length);
    }
    isr.close();
    
    // sb.append(char[] str, int offset, int length);
    // sb.append(new String(byte[] bytes, int offset, int length));
    ```

    

- #### FileReader / FileWriter

    ```java
    // 写
    FileWriter fw = new FileWriter(file, true);
    fw.write(data);
    fw.close();
    
    // 读
    FileReader fr = new FileReader(file);
    char[] chars = new char[4];
    StringBuilder sb = new StringBuilder();
    int length;
    while ((length = fr.read(chars)) != -1) {
    	sb.append(chars, 0, length);
    }
    fr.close();
    ```

- #### BufferedReader / BufferedWriter

    ```java
    // 写; 传入 FileWriter
    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
    bw.write(data);
    bw.close()
    
    // 读；传入 FileReader
    BufferedReader br = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null) {
    	sb.append(line);
    }
    br.close();
    ```

 ![20200921161501573](F:\Typora\Nodes\java\IO\20200921161501573.png)

- InputStreamReader：从字节流到字符流的桥梁；构造器传入InputStream

- FileReader：等同于 new InputStreamReader(new FileInputStream(file), "utf-8");

    ```java
    public class FileReader extends InputStreamReader {
        public FileReader(File file) throws FileNotFoundException {
    		super(new FileInputStream(file));
    	}
    }
    ```

- BufferedReader：

    ```java
    new BufferedReader(new FileReader(file));
        
    public class BufferedReader extends Reader {
    	public BufferedReader(Reader in) {
    		this(in, defaultCharBufferSize);
    	}
     }
    ```

- PipedReader：多线程间的管道通信；

- CharArrayReader：从char数组中读取数据的介质流

- StringReader：从String中读取数据的介质流；



### 相关方法介绍

