```java
  public static void increase();
    Code:
       0: getstatic     #2                  // Field race:I
       3: iconst_1
       4: iadd
       5: putstatic     #2                  // Field race:I
       8: return
```

#### volatile

##### 可见性

保证此变量对所有线程的可见性；

普通变量的值在线程之间传递需通过主内存（java内存模型，相当于 堆）来完成；



```
public static volatile int race = 0;
public static void increase() {
	race++;
}
  
// 反编译
  public static void increase();
    Code:
       0: getstatic     #2                  // Field race:I
       3: iconst_1
       4: iadd
       5: putstatic     #2                  // Field race:I
       8: return
```

通过反编译：race++ 需要四步；多个线程并不保证原子性操作；





##### 禁止指令重排序

