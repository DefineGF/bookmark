import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
from pylab import mpl


'''
    设置字体格式
'''
mpl.rcParams['font.sans-serif'] = ['FangSong']
mpl.rcParams['axes.unicode_minus'] = False
plt.figure("TCP拥塞窗口的演化")
plt.xlabel("传输回合")
plt.ylabel("拥塞窗口(以报文段计)")
plt.title("红线：TCP Tahoe\n绿线：TCP Reno(前段与Tahoe相同)")


tahoe_x = [i for i in range(1,16)]
tahoe_y = [1,2,4,8,9,10,11,12,1,2,4,6,7,8,9]

reno_x = [i for i in range(9,16)]
reno_y = [i for i in range(9,16)]

plt.scatter(tahoe_x,tahoe_y)
plt.scatter(reno_x,reno_y)

plt.plot(tahoe_x,tahoe_y,color = "red")
plt.plot(reno_x,reno_y,color = "green")


'''设置坐标轴刻度'''
ax=plt.gca() #ax为两条坐标轴的实例
ax.xaxis.set_major_locator(MultipleLocator(1)) #把x轴的主刻度设置为1的倍数
ax.yaxis.set_major_locator(MultipleLocator(1)) #同上

'''垂直 坐标轴直线'''
x_s = [4,8]
for i in range(17):
    if (i % 2 == 0):
        plt.axhline(i)
for i in x_s:
    plt.axvline(i)

plt.show()


