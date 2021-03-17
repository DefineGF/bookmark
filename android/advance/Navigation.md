#### 简单使用

创建两个 Fragment;



右键 res -> android resource file：

设置resource type 为 Navigation；

然后自动创建navigation/xxx.xml，内容为空；

打开 xxx.xml,选择红圈 add，添加两个fragment，并设置start，连线：

 ![Image](F:\Typora\Nodes\Android\进阶\Image.png)

此时 xml自动生成：

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/my_navi"
    app:startDestination="@id/firstFragment">

    <fragment
        android:id="@+id/firstFragment"
        android:name="com.cjm.viewmodeldemo.navig.FirstFragment"
        android:label="fragment_first"
        tools:layout="@layout/fragment_first" >
        <action
          android:id="@+id/action_firstFragment_to_secondFragment"
            app:destination="@id/secondFragment" />
    </fragment>

    <fragment
        android:id="@+id/secondFragment"
        android:name="com.cjm.viewmodeldemo.navig.SecondFragment"
        android:label="fragment_second"
        tools:layout="@layout/fragment_second" >
        <action
            android:id="@+id/action_secondFragment_to_firstFragment"
            app:destination="@id/firstFragment" />
    </fragment>
</navigation>
```

然后在主activity中添加 NAVHostFragment 作为 navigation 容器，然后布局中自动生成：

```xml
<fragment
    android:id="@+id/nav_host_Fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="409dp"
    android:layout_height="729dp"
    app:defaultNavHost="true"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
          
    app:navGraph="@navigation/my_navi" />
```

接着添加逻辑：

需要在FristFragment中点击按钮跳转到SecondFragment：

```java
// FirstFragment
@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) { 
    super.onActivityCreated(savedInstanceState);
    Log.i(TAG, "run the onActivityCreated");
    View view = getView();
    view.findViewById(R.id.btn_first_frag)
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_firstFragment_to_secondFragment);
            }
    });
}
```



##### 获取 NavController 方法：

- Navigation.findNavCOntroller(v); 源码：

```java
public static NavController findNavController(@NonNull View view) {
    NavController navController = findViewNavController(view);
    if (navController == null) {
        throw new IllegalStateException("View " + view + " does not have a NavController set");
    }
    return navController;
}


private static NavController findViewNavController(@NonNull View view) {
    while (view != null) {
        NavController controller = getViewNavController(view);
        if (controller != null) {
            return controller;
        }
        ViewParent parent = view.getParent();
        view = parent instanceof View ? (View) parent : null;
    }
    return null;
}
```

- 第二种：

    ```
    // NavController findNavController(@NonNull Activity activity, @IdRes int viewId)
    
    NavController navController =
    		Navigation.findNavController(getActivity(), R.id.nav_host_Fragment);
    navController.navigate(R.id.action_secondFragment_to_firstFragment);
    ```



#####  NAVHostFragmet 的 Activity 中设置：

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigation);
    controller = Navigation.findNavController(this, R.id.nav_host_Fragment);
    NavigationUI.setupActionBarWithNavController(this, controller);
}

@Override
public boolean onSupportNavigateUp() {
    return controller.navigateUp();
}
```

 <img src="F:\Typora\Nodes\Android\进阶\Image-1615970097840.png" alt="Image" style="zoom: 25%;" />



##### 页面数据传输

```java
// 起始页面：
Bundle bundle = new Bundle();
bundle.putString("name_key", name);
nacController.navigate(R.id.action_from_to, bundle);

// 获取方式：
String name = getArguments().getString("name_key");
```



