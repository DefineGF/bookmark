 ![1612877618900](F:\Typora\Nodes\Android\Base\1612877618900.png)

Context一共有三种类型，分别是Application、Activity和Service， 他们各自有不同的生命周期， 具体Context功能则是由ContextImpl类实现的。

其中，Activity 显示界面，继承 ContextThemeWrapper（提供关于主题，显示界面的功能）；



##### Application使用

```java
public class MyApplication extends Application {
    public MyApplication() {
    	// 空指针；应该调用super.onCreate();完成attachBaseContext        
        String packageName = getPackageName();   
        Log.d("TAG", "package name is " + packageName);
    }
}
```



#### 获取单例

##### 错误单例

```java
public class MyApplication extends Application {
        private static MyApplication app;
        public static MyApplication getInstance() {
                if (app == null) {
                        app = new MyApplication();
                }
                return app;
        }
}
```

这样获取的结果只是一个简单的java的对象，并没有context功能，而且Application本来就是单例：

##### 正确使用

```java
public class MyApplication extends Application {
        private static MyApplication app;
        public static MyApplication getInstance() {
                return app;
        }
        
        @Override
        public void onCreate() {
                super.onCreate();
                app = this;
        }
}
```







##### ContextWrapper

```java
public class ContextWrapper extends Context {
    Context mBase; // 用来存放系统实现的 ContextImpl

    protected void attachBaseContext(Context base) {
        if (mBase != null) {
            throw new IllegalStateException("Base context already set");
        }
        mBase = base;
    }


    /**
     * @return the base context as set by the constructor or setBaseContext
     */
    public Context getBaseContext() {
        return mBase;
    }

    @Override
    public Resources getResources() {
        return mBase.getResources();
    }

    @Override
    public ContentResolver getContentResolver() {
        return mBase.getContentResolver();
    }

    @Override
    public Looper getMainLooper() {
        return mBase.getMainLooper();
    }
    
    @Override
    public Context getApplicationContext() {
        return mBase.getApplicationContext();
    }

    @Override
    public String getPackageName() {
        return mBase.getPackageName();
    }

    @Override
    public void startActivity(Intent intent) {
        mBase.startActivity(intent);
    }
    
    @Override
    public void sendBroadcast(Intent intent) {
        mBase.sendBroadcast(intent);
    }

    @Override
    public Intent registerReceiver(
        BroadcastReceiver receiver, IntentFilter filter) {
        return mBase.registerReceiver(receiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        mBase.unregisterReceiver(receiver);
    }

    @Override
    public ComponentName startService(Intent service) {
        return mBase.startService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        return mBase.stopService(name);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn,
            int flags) {
        return mBase.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        mBase.unbindService(conn);
    }

    @Override
    public Object getSystemService(String name) {
        return mBase.getSystemService(name);
    }

    ......
}
```

