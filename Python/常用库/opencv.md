##### 读取视频参数

```python
cap = cv2.VideoCapture(file_path)
    if cap.isOpened(): # 暂时无法获得通道数
        self.APP.Video_Info.set_frame_rate(self.cap.get(5))        # 帧率
        self.APP.Video_Info.set_frame_width(self.cap.get(3))       # 宽度
        self.APP.Video_Info.set_frame_height(self.cap.get(4))      # 高度
        self.APP.Video_Info.set_frame_count(self.cap.get(7))       # 帧数

self.ret, self.frame = self.cap.read()
if self.ret:
    self.APP.Video_Info.set_frame_channel(self.frame.shape[2]) # 通道数
```

与skvideo相比：

```python
self.video_data = skvideo.io.vread(self.video_path)
self.length = self.video_data.shape[0]
self.height = self.video_data.shape[1]
self.width = self.video_data.shape[2]
self.channel = self.video_data.shape[3]
```

直接将数据放入列表；