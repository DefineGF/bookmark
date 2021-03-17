#### Adapter

将数据转换为视图；

```java
recyclerView.setAdapter(adapter);
```

##### 常见封装

```java
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH>{
    private List<T> mDatas;
    public QuickAdapter(List<T> datas){
        this.mDatas = datas;
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(VH holder, T data, int position);
    
    static class VH extends RecyclerView.ViewHolder{
       private SparseArray<View> mViews; // 保存ItemView中的子view，比如TextView
       private View mConvertView;
    
        private VH(View v){
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }
    
        public static VH get(ViewGroup parent, int layoutId){
          	View convertView = LayoutInflater.from(parent.getContext())
                							 .inflate(layoutId, parent, false);
         	return new VH(convertView);
        }
    
        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }
    
        public void setText(int id, String value){
            TextView view = getView(id);
            view.setText(value);
        }
    }
}
```

具体实现：

```java
public class RegularAccountAdapter extends RecyclerView.Adapter<RegularAccountAdapter.ViewHolder> {
    private List<AccountOverview> accOvList;

    public RegularAccountAdapter(List<AccountOverview> accountOverviewList){
        this.accOvList = accountOverviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
        		.from(viewGroup.getContext())                                                       .inflate(R.layout.regular_acc_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AccountOverview accOv = accOvList.get(i);
        viewHolder.tvAcc.setText(accOv.getCreditcard());
        viewHolder.tvType.setText(accOv.getType());
    }

    @Override
    public int getItemCount() {
        return accOvList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAcc,tvType;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAcc = itemView.findViewById(R.id.reg_item_tv_acc);
            tvType = itemView.findViewById(R.id.reg_item_tv_type);
           
        }
    }
}
```





setAdapter()具体实现：

```java
public void setAdapter(@Nullable Adapter adapter) {
    // bail out if layout is frozen
    setLayoutFrozen(false);
    setAdapterInternal(adapter, false, true);
    processDataSetCompletelyChanged(false);
    requestLayout();
}

setAdapterInternal(adapter, false, true) // 主要是下面这两行
// mAdapter = adapter
// adapter.registerAdapterDataObserver(mObserver);

// requestLayout() RecyclerView 会调用 
    void onLayout(boolean changed, int l, int t, int r, int b) {
        TraceCompat.beginSection(TRACE_ON_LAYOUT_TAG); 
        dispatchLayout(); 
        TraceCompat.endSection();
        mFirstLayoutComplete = true;
}
```

```java
void dispatchLayout() {
       ......
        if (mState.mLayoutStep == State.STEP_START) {
            dispatchLayoutStep1(); // 做一下准备工作：决定哪一个动画被执行，保存一些目前view的相关信息
            mLayout.setExactMeasureSpecsFrom(this);
              //分发第二步
            dispatchLayoutStep2(); // 找到实际的view和最终的状态后运行layout。
        }
        ......
         //分发第三步
        dispatchLayoutStep3(); // 保存动画和一些其他的信息
        ......
    }
}
```

 ![1658859ccd64da54](F:\Typora\Nodes\Android\进阶\RecyclerView\1658859ccd64da54.webp)