##### 常用方法

- cv2.cvtColor(src, code, dst=None, dstCn = None)

    - src: 原图像 
    - code：颜色空间转换类型  
    - dst：目标图像；与原图像深度一样  
    - dstCn：指定目标图像通道数

- cv2.flip(src, flipCode[, dst]) 图像翻转

       flipCode:   

    - 1 ：水平翻转  
    - 0 ：垂直翻转 
    -  -1 ：水平垂直翻转



##### 实例

```python
import cv2

width, height = 640, 480
cap = cv2.VideoCapture(0, cv2.CAP_DSHOW)
cap.set(3, width) # 宽度
cap.set(4, height) # 高度

fourcc = cv2.VideoWriter_fourcc('m', 'p', '4', 'v') # 设置格式
out = cv2.VideoWriter('out.mp4', fourcc, 20.0, (width, height)) # 20.0 表示帧率

while cap.isOpened():
    ret, frame = cap.read()
    if ret:
        frame = cv2.resize(frame, (width, height))
        out.write(frame) # 保存
        cv2.imshow('frame', frame)
    else:
        break

    key = cv2.waitKey(1)
    if key == 27: # esc键
        break

cap.release()
out.release()
cv2.destroyAllWindows()
```

