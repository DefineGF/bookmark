#### 基础

##### 捕捉窗口关闭事件

```python
self.window.protocol("WM_DELETE_WINDOW", self.window.iconify)
# 捕捉事件中
window.quit()
```



#### 布局

组件设置 width 和 height 精确属性的话，尽量使用 place 布局；

##### grid(row = x, column = y, sticky = N | S | E | W )

 ![Image](F:\Typora\Nodes\Python\常用库\Image.png)



##### pack 属性

- side：

    扭停靠在父布局位置：写法 side = "left" 或者 side = LEFT （推荐后者，统一）；

- fill

    填充； 写法 fill = "x" 或者 fill = X　　　　

    x:水平方向填充 | y:竖直方向填充 | both:水平和竖直方向填充 | none:不填充

- expand:

    -  YES:扩展整个空白区　　 　
    - NO:不扩展

- anchor

    N:北 下 | E:东 右 | S:南 下 | W:西 左 | CENTER:中间

- padx:x方向的外边距；

     pady同之；

- ipadx:x方向的内边距； 

    ipady同



##### place 属性

 ![Image](F:\Typora\Nodes\Python\常用库\Image-1615967249741.png)



##### 实例参考

```
label_frame = ttk.LabelFrame(window, text = "帧标签")
label_frame.pack(side=TOP, fill=BOTH, expand=TRUE, padx = 20, pady = 20)
Frame(label_frame, bg="red").pack(fill=X, expand=YES) 
# 其中expand 设置 YES 表示 该控件可以占据父布局剩余空间
```

 <img src="F:\Typora\Nodes\Python\常用库\Image-1615967302774.png" alt="Image" style="zoom:25%;" />

发现只有中间有段红色区间；

把fill 设置为 BOTH：

 <img src="F:\Typora\Nodes\Python\常用库\Image-1615967333754.png" alt="Image" style="zoom:25%;" />



##### 常踩坑

- label 和 button 宽高：前者为像素值，而后者是能显示的字的个数;





#### 实例

##### 图像显示

```python
def resize(w, h, label_width, label_height, pil_img):  # 缩放图片大小 适配Label
    f1 = label_width / w
    f2 = label_height / h
    f = min(f1, f2)
    width = int(w * f)
    height = int(h * f)
    return pil_img.resize((width, height), Image.ANTIALIAS)


def get_tk_img(path):
    img = Image.open(path)  # 打开图片;使用 tk.PhotoImage 的话 只能显示gif文件
    w, h = img.size
    print("get the w = %s, h = %s" % (w, h))
    img_resized = resize(w, h, img)
    photo = ImageTk.PhotoImage(img_resized)  # 用PIL模块的PhotoImage打开
    return photo
```



##### Label 实现图片轮播

```python
from tkinter import *
from PIL import Image, ImageTk


w_box, h_box = 640, 640
imgs = ("F:/Python/code/GUI/TkinterDemo/imgs/1.jpg",
        "F:/Python/code/GUI/TkinterDemo/imgs/2.jpg",
        "F:/Python/code/GUI/TkinterDemo/imgs/3.jpg",
        "F:/Python/code/GUI/TkinterDemo/imgs/4.jpg",
        "F:/Python/code/GUI/TkinterDemo/imgs/5.jpg")
img_index = 0

def resize(w, h, pil_img):  # 缩放图片大小 适配Label
    f1 = w_box / w
    f2 = h_box / h
    f = min(f1, f2)
    width = int(w * f)
    height = int(h * f)
    return pil_img.resize((width, height), Image.ANTIALIAS)


def get_tk_img(cur_index):
    img = Image.open(str(imgs[cur_index]))  # 打开图片;使用 tk.PhotoImage 的话 只能显示gif文件
    w, h = img.size
    print("get the w = %s, h = %s" % (w, h))
    img_resized = resize(w, h, img)
    photo = ImageTk.PhotoImage(img_resized)  # 用PIL模块的PhotoImage打开
    return photo


def show_next(window, img_label):
    global img_index
    img_index = (img_index + 1) % len(imgs)
    photo = get_tk_img(img_index)
    img_label.config(image = photo)
    img_label.image = photo
    window.update_idletasks()


def img_display():
    root = Tk()
    root.title('读取图片')

    photo = get_tk_img(img_index)
    img_label = Label(root, image=photo, width=w_box, height=h_box)
    img_label.image = photo
    img_label.grid(row=0, column=0, columnspan=3)

    Button(root, text="next", command = lambda : show_next(root, img_label)).grid(row=1, column=2)

    mainloop()
```

