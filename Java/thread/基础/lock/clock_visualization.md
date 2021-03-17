#### 计时器AlarmThread

##### 向外提供方法

- ##### onPause -> 暂停计时进程

    由内部方法 myPause() 实现

- ##### onResume -> 恢复计时进程



##### 暂停 / 恢复 核心机制

```java
private final Object lock = new Object();
private boolean pause = false;

private void myPause() {
	synchronized (lock) {
		try {
			System.out.println("waiting~");
            lock.wait();
		} catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public void onResume() {
	pause = false;
	synchronized (lock) {
		lock.notify();
	}
}

public void onPause() {
	pause = true;
}

@Override
public void run() {
	while (true) {
    	if (pause) {
        	myPause();
        }
		maxCount--;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (maxCount == 0){
			this.interrupt();
			return;
		}
	}
}
```



通过对对象的 wait() / notify() 来完成阻塞和唤醒的操作，注意添加synchronized；

通过设置boolean类型的标志记录计时器状态；



#### 使用 javafx 可视化 （UserGUI)

##### 组件

- 一个启动线程按钮，一个暂停/恢复线程按钮；
- 一个现实计时器过程的TextField;



##### 实现过程

- UserGUI 实例化 AlarmThread，并通过构造器传入本身；

    **AlarmThread.java**

    ```java
    // 构造函数
    public AlarmThread(int maxCount, UserGUI gui) {
    	this.maxCount = maxCount;
    	this.gui = gui;
    }
    ```

    **UserGUI.java**

    ```java
    alarmThread = new AlarmThread(20, this);
    ```

    

- ##### 数据绑定并提供接口

    **UserGUI.java**

    ```java
    private StringProperty msg = new SimpleStringProperty("null");
    TextField tf = new TextField();
    tf.textProperty().bind(msg);
    
    // 提供修改接口
    public void setMsgValue(String str) {
    	msg.setValue(str);
    }
    ```

- ##### 事件监听

    ```java
    btnStart.setOnAction(event -> {
        System.out.println("start");
        ((Button)event.getSource()).setOnAction(null);
        alarmThread = new AlarmThread(20, this);
        alarmThread.start();
    });
    
    btnPause.setOnAction(event -> {
        if (isPause) {
            isPause = false;
            System.out.println("resume~");
            alarmThread.onResume();
        } else {
            isPause = true;
            System.out.println("pause~");
            alarmThread.onPause();
        }
    });
    ```

- ##### 线程执行

    ```java
    @Override
    public void run() {
        while (true) {
            if (pause) {
                myPause();
            }
            System.out.println("current count = " + maxCount);
            
            // ************************ 更改界面**********************
            gui.setMsgValue(String.valueOf(maxCount));
            
            maxCount--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (maxCount == 0){
                this.interrupt();
                // ********************* 更改界面**********************
                gui.setMsgValue("count end~");
                return;
            }
        }
    }
    ```

    

