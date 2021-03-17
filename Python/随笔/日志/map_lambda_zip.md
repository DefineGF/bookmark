python2 中 map 使用 lambda 传入两个参数：

```python
map(lambda (x, w): x * w,  zip(input_vec, self.weights))
```

 在python3 中：

```python
map(lambda (x, w): x * w,  input_vec, self.weights) # 直接传入两个列表就行
```



其次 python2 中 map 直接返回 list 类型，在python中需要进行 list() 转化。

