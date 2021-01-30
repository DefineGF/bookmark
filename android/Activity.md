#### 标签属性

- **android:alwaysRetainTaskState**  默认为false，仅对root Activity有效

    - true：表示始终保持activity所在任务的状态；

    - false：在特定情况下可将task状态重置至初始状态。通常，用户从主屏幕上重新选择另一任务，若用户在一段时间(ex：30min)未访问该任务,则系统会清除该任务（从activity堆栈上清除所有该任务的activity）

- **android:configChanges:**

    - screenSize：当前可用屏幕尺寸发生变更。

        该值表示当前可用尺寸相对于当前纵横比的变更，当用户在横向与纵向之间切换时，它便会发生变更。

    - orientation：屏幕方向发生变更 — 用户旋转设备。Android 3.2（API 级别 13）或更高版本的系统，则还应声明 **"screenSize"** 配置，因为当设备在横向与纵向之间切换时，该配置也会发生变更

- **android:directRootAware：**

    是否支持设备解锁之前使用（未解锁时仅能使用设备保护存储区的数据)

- **android:excludeFromRecents:**

    是否从最近使用应用列表中排除；true，排除；false：不排除

- **android:exported:**

    是否可由其他应用的组件启动activity

    - true：可以
    - false：只能由同一用户的组件或者同一用户ID的不同应用打开；

    *android操作系统是一个多用户的Linux系统，每个应用都是一个用户；系统为每个应用分配一个唯一的Linux用户ID，为用户中的所有文件设置权限，只能本用户访问（仍有相关途径供不同应用共享资源和服务）；每个进程都有各自的虚拟机，互不干涉；可安排两个用户共享用户ID，便可共享资源；*

- **android:finshOnTaskLaunch:**

    在主屏幕上再次启动activity时，是否关闭现有activity（true：关闭；false：不关闭）



- **android:hardwareAccelerated:**  是否启动经硬件加速的openGL图形渲染器
- **android:icon & android:label** :桌面图标 + 桌面标签

- **android:launchMode:**  standard + singleTop + singleTask + singleInstance

    - **standard**：标准模式

     ![1611965772859](F:\Typora\Nodes\Android\Base\1611965772859.png)

    - **singleTop:** 在singleTop中，second跳转至first时，发现栈顶为second，则创建first：

     ![1611965846545](F:\Typora\Nodes\Android\Base\1611965846545.png)

    - **singleTask: **如果发现有对应的Activity实例，则使此Activity实例之上的其他Activity实例统统出栈，使此Activity实例成为栈顶对象，显示到幕前（无情）

         ![1611965898894](F:\Typora\Nodes\Android\Base\1611965898894.png)

    - **singleInstance: **

        first -> standard;

        second -> singleInstance;

        first --> second:重新启动一个栈; 返回则回到原始栈

         ![1611966064688](F:\Typora\Nodes\Android\Base\1611966064688.png)

##### 常见配置

```xml
<activity android:name=".ExampleActivity" android:icon="@drawable/app_icon">
    <intent-filter>                                             //若不希望其他应用激活activity，则无需配置intent-filter
        <action android:name="android.intent.action.SEND" />           //指定该activity会发送数据
        <category android:name="android.intent.category.DEFAULT" />    //DEFAULT指示可接受启动请求
        <data android:mimeType="text/plain" />                         //可发送数据类型
    </intent-filter>
</activity>
```



#### 加载流程 (接口回调)



##### onCreate():

​	系统创建Activity时执行，调用setContentView()完成Activity布局；

  	此处应初始化Activity基本组件：比如创建视图并将数据绑定到列表。

​	**onCreate()退出后，activity进入已启动状态，并对用户可见**

 ![1611967643625](F:\Typora\Nodes\Android\Base\1611967643625.png)

##### onStart():

​	**activity进入前台与用户交互的最后一次准备工作；**



##### onResume():

​	**activity 与用户交互前调用，此时该activity位于activity栈顶，并接受用户输入；**

​	应用大部分核心功能都在onResume实现；

​	后面总跟着 **onPause();**



##### onPause(): 

​	**下一回调是onResume() 或者 onStop()**

​	用户失去焦点并进入已暂停状态，例如：点击返回或者最近使用应用按钮等；

​	**不能使用onPause()保存应用或用户数据、进行网络请求或者数据库事务**



##### onStop():

​	**下一回调是onRestart() or onDestory()**

​	当activity对用户不可见时，会调用之；



##### onRestart(): 

​	**下一回调总是 onStart()**

​	停止状态的activity重新启动;会从停止时的activity恢复；



#####  onDestroy():

​	销毁activity之前调用



##### 常见分析

- 息屏：onPause() -> onStop()

- 配置更改：onPause() -> onStop() -> onDestroy();
- 应用切换：onPause()； 另一个：onResume()

- 应用重启：onPause() --> onStop() --> onSaveInstanceState(); 

    ​		   返回之后： onRestart() -> onStart() -> onResume();

- 旋转屏幕：onPause() -> onStop() -> **( onSaveInstanceState())** -> onDestroy()

     		   onCreate() (saveInstanceState not null) -> onStart() -> **(onRestoreInstanceState())** 

    ​			-> onReume()

- home/最近APP使用情况：

    onPause() -> onStop(), 随后执行 **onSaveInstanceState(）**

    切换回去的时候，依次执行onRestart() -> onStart() -> onResume(); 但是并不执行**onRestoreInstanceState()**



#### 状态恢复

发生配置变更（旋转或者切换多窗口等）系统会默认销毁activity：

​    默认情况下，系统会使用Bundle来保存布局中view状态，内容为键值对，但是Bundle适合保存轻量级数据，因为占用主线程进行序列化并占用系统内存；

​    保存界面状态推荐使用：**persistent local store，ViewModel，onSaveInstanceStacte**类

##### 轻量级

```java
static final String STATE_SCORE = "playerScore";
static final String STATE_LEVEL = "playerLevel";

// 当用户显式关闭（比如点击返回到主屏幕）或者执行finish()关闭activity时:
// 系统不会调用onSaveInstanceState()
@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
    // Save the user's current game state
    savedInstanceState.putInt(STATE_SCORE, currentScore);
    savedInstanceState.putInt(STATE_LEVEL, currentLevel);

    // Always call the superclass so it can save the view hierarchy state
    super.onSaveInstanceState(savedInstanceState);
}

// onCreate() 中恢复
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState); // Always call the superclass first

    // Check whether we're recreating a previously destroyed instance
    if (savedInstanceState != null) {
        // Restore value of members from saved state
        currentScore = savedInstanceState.getInt(STATE_SCORE);
        currentLevel = savedInstanceState.getInt(STATE_LEVEL);
    } else {
        // Probably initialize members with default values for a new instance
    }
    // ...
}

// 在 onStart() 之后调用，仅当存在要恢复的数据时系统才会调用之
public void onRestoreInstanceState(Bundle savedInstanceState) {
    // Always call the superclass so it can restore the view hierarchy
    super.onRestoreInstanceState(savedInstanceState);

    // Restore state members from saved instance
    currentScore = savedInstanceState.getInt(STATE_SCORE);
    currentLevel = savedInstanceState.getInt(STATE_LEVEL);
}
```



onSaveInstanceState() 调用情况

- 用户按下HOME键
- 长按HOME键，选择运行其他的程序时
- 按下电源按键（关闭屏幕显示）时
- 从activity A中启动一个新的activity时
- 屏幕方向切换时，例如从竖屏切换到横屏时