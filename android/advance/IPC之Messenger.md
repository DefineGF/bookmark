### Messenger 进程通信

#### 客户端

##### 信使

```java
private Messenger mGetReplyMessenger = new Messenger(new MessageHandler());
@SuppressLint("HandlerLeak")
private class MessageHandler extends Handler{
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case Constant.MSG_FROM_SERVICE:
                String getData = msg.getData().getString(Constant.MSG_KEY);
                Toast.makeText(MainActivity.this,"get the replay is: " + getData, Toast.LENGTH_SHORT).show();
                Log.i(TAG,"handle_message get data --> " + getData);
                break;
        }
    }
}

```

##### 连接

```java
private ServiceConnection mServiceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i(TAG," --> onServiceConnected");

        Messenger mService = new Messenger(service);
        // 设置消息内容 & 恢复信使
        Message msg = Message.obtain(null,Constant.MSG_FROM_CLIENT);
        Bundle data = new Bundle();
        data.putString(Constant.MSG_KEY,"<- 山川异域 ");
        msg.setData(data);
        msg.replyTo = mGetReplyMessenger; 

        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {    }
};
```

##### 使用 

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn_bind_service).setOnClickListener(this);
}

@Override
public void onClick(View v) {
    if(v.getId() == R.id.btn_bind_service){
        Intent intent = new Intent(this, MessengerService.class);
   		bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }
}
```



#### 服务端

```java
public class MessengerService extends Service {
    private static final String TAG = "IPC_MSG_SERVICE";
    private Messenger mMessenger = new Messenger(new MessengerHandler());

    @SuppressLint("HandlerLeak")
    private class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.MSG_FROM_CLIENT:
                    String getData = msg.getData().getString(Constant.MSG_KEY);
                    Log.i(TAG,"handle_message get data --> " + getData);
                    try {
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    Messenger client = msg.replyTo; // 获取返回信使
                    Message replyMsg = Message.obtain(null,Constant.MSG_FROM_SERVICE); //前者handler，后者为int标志
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.MSG_KEY,"风月同天 ->");
                    replyMsg.setData(bundle);
                    
                    try {
                        client.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG," --> onBind");
        return mMessenger.getBinder();
    }
}
```

