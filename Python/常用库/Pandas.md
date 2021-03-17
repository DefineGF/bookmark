##### 读取csv文件

```python
import pandsa as pd
file="data.csv"
df=pd.read_csv(file,header=None)
y=df.loc[0:99,4].values           #获取文件前一百行 第5列的数据
y=np.where(y=='xxx',-1,1)          #当y列表中的值等于xxx时,令y=0
x=np.iloc[0:100,[0,2]].values      #获取文件前100行，第一列和第三列的值
```

