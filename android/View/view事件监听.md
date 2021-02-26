#### 事件分发机制

```java
public boolean dispatchTouchEvent(MotionEvent event) {  
	if (mOnTouchListener != null 
		&& (mViewFlags & ENABLED_MASK) == ENABLED 
		&&  mOnTouchListener.onTouch(this, event)) {  
            return true;  
        }
        return onTouchEvent(event);  
}
```

- mOnTouchListener != null

    **mOnTouchListener变量在View.setOnTouchListener（）方法里赋值**



- (mViewFlags & ENABLED_MASK) == ENABLED

    **判断当前点击的控件是否enable**



- **mOnTouchListener.onTouch(this, event)**

    ```java
    button.setOnTouchListener(new OnTouchListener() {  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {  
                return false;  
            }  
    });
    ```

    分析发现，如果 onTouch() 方法中 返回 false，那么dispatchTouchEvent() 则调用 onTouchEvent(event)， 完成事件传递；

     ![1612784302521](F:\Typora\Nodes\Android\view\1612784302521.png)



##### Activity、ViewGroup 和 View 事件传递

- Activity

    ![1612784402352](F:\Typora\Nodes\Android\view\1612784402352.png)

- ViewGroup

     ![1612784443926](F:\Typora\Nodes\Android\view\1612784443926.png)

- View

     ![1612784478758](F:\Typora\Nodes\Android\view\1612784478758.png)