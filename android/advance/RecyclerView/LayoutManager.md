##### LayoutManager

负责子view的布局；（当然也可以在xml中通过app:layoutManager 设置）

常用：

- LinearLayoutManager（线性布局）；
- GridLayoutManager（网格布局）；
- StaggeredGridLayoutManager（瀑布流）

```java
rc.setLayoutManager(new 		                                                                    LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
rc.setLayoutManager(new StaggeredGridLayoutManager(3, 			                                               StaggeredGridLayoutManager.VERTICAL));//前者代表列数
```

常见API：

```java
canScrollHorizontally();	//能否横向滚动
canScrollVertically();		//能否纵向滚动
scrollToPosition(int position);//滚动到指定位置

setOrientation(int orientation);//设置滚动的方向
getOrientation();				//获取滚动方向

findViewByPosition(int position);			//获取指定位置的Item View
findFirstCompletelyVisibleItemPosition();   //获取第一个完全可见的Item位置
findFirstVisibleItemPosition();				//获取第一个可见Item的位置
findLastCompletelyVisibleItemPosition();	//获取最后一个完全可见的Item位置
findLastVisibleItemPosition();				//获取最后一个可见Item的位置
```

