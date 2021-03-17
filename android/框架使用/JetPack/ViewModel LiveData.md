##### 数据读取报错

![Image](F:\Typora\Nodes\Android\框架使用\JetPack\Image.png)

##### 简单使用

- 事件分发者

```java
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NameViewModel extends ViewModel {
    private MutableLiveData<String> nameLiveData;

    public MutableLiveData<String> getNameLiveData(){
        if(nameLiveData == null){
            nameLiveData = new MutableLiveData<>();
        }
        return nameLiveData;
    }
}
```

- 添加观察者

```java
final Observer<String> nameObserver = new Observer<String>() {
    @Override
    public void onChanged(String s) {
        tvDisplay.setText(s);
    }
};

private NameViewModel model;
model = ViewModelProvider.AndroidViewModelFactory
                         .getInstance(getApplication())
                         .create(NameViewModel.class);
model.getNameLiveData().observe(this,nameObserver);
model.getNameLiveData().setValue(name);//状态变化，之后tvDisplay会修改显示值
```

数据变更 ->观察者 onChanged()



