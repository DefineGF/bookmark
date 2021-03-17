##### EventBus

开始Activity

```java
private void registerEvent(){
    EventBus.getDefault().register(this);
}

@Subscribe (threadMode = ThreadMode.MAIN)
public void onMyEvent(MyEvent event){     //这个方法需要为 public
    tvDisplay.setText(event.getMessage());
    Log.i(TAG,"run the onMyEvent");
}

@Override
protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
}

```

##### 第二Activity

```java
public class SecondActivity extends AppCompatActivity {
    private EditText etInput;
    ... ...
    private void initView(){
        etInput = findViewById(R.id.et_input);
        findViewById(R.id.btn_submit).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String submit = etInput.getText().toString();
                    EventBus.getDefault().post(new MyEvent(1,submit));
                }
        });
    }
}
```

这样从第二个activity返回时，接收message的activity已经获取到消息并显示了；