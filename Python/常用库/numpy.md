##### 常用方法

>参考网站：https://blog.csdn.net/u011995719/article/details/71080987



- 随机生成4个浮点类型的数组

    ```python
    arr_float=np.random.random(4)
    ```

- arange

    ```python
     x=np.arange(0,1,0.1)
     #输出[0. 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9]
    ```

- linspace:

    ```python
    x=np.linspace(0,1,5)
    # 输出 [0. 0.25  0.5  0.75 1.]
    ```

    

- fromstring

    ```python
    str='cheng'
    arr=np.fromstring(str,dtype=np.int8)
    #输出[ 99 104 101 110 103] 即将字符转换为int（8位） 类型为int8位
    ```

- eye

    ```python
    a=np.eye(3)
    #输出: [[1., 0., 0.],
           [0., 1., 0.],
           [0., 0., 1.]]
    ```

- ones

    ```python
    a=np.ones((3,3))     #不可以写成np.ones(3,3)   会默认将第二个3看成type的参数而出错
    #输出 [[1., 1., 1.],
    	 [1., 1., 1.],
    	 [1., 1., 1.]]
    ```

- c_ 和 r_

    矩阵合并 c_按行连接（行相等）_

     r_ 按列连接（列相等）   使用方法：np.c_[a,b]

- np.ravel（） 和np.flatten()

    将多维数组转化为一维数组（） 不同的是flatten()将原来的值拷贝了一份;

- **np.random.normal(loc=0.0, scale=1.0, size=None)：生成高斯分布密度随机数**

    - loc: 概率分布的均值；
    - scale：越大->矮胖； 
    - 越小 -> 瘦高
    - size：大小（个数）

- **unique：**去掉重复值 并按照从小到大的顺序排列
- **vstack和hstack**
    - vstack：垂直合并
    - hstack：水平合并：a3=np.vstack((a1,a2))