#### P2P

设想这样一个场景，一个老师在网站上放了大文件，由于服务器太垃圾，只能同时允许10个同学下载，那么为了让几百个同学尽快获得这个文件，方法是什么？（问着好弱智）。肯定是下载过的再转发给没有下载的啦。上节说过，网络架构一般是客户端/服务器 和 P2P 两种。   在C/S模式中，服务器承受了极大的负担，并且消耗了大量的服务器宽带。在P2P文件分发中，每个对等方能够向任何其他对等方诚信分发他已经接受到的该文件的任何部分，从而在分发过程中协助该服务器。

最为流行的P2P文件分发协议是BitTorrent

##### 核心思想

把文件分成很多小块，让下载者互相连接，每个下载者维护这个文件的某个小块，交换彼此下载好的部分，参与的人越多，互相交换的就越密集，下载速度越快。

##### 工作原理

 BitTorrent协议需要资源共享者生成一个包含下载信息的种子文件，后缀是.torrent；

种子文件里面是个json，包含文件名字(name)，大小(lengh)，分块后每块文件的大小(piece length)，哈希值(pieces)，和Tracker服务器的地址(announce，announce-list)，Tracker很重要，通过Tracker才能找到其他下载者的联系方式；

当用下载软件打开种子，就会开始联动种子文件里内置的Tracker服务器，**服务器会记录下你的IP，并把其他正在下载或下载完成的人的IP返回给你，这样你们就可以组队下载了（想起来迅雷没有）**。如果没有找到正在下载的人，资源发布者也不在线，就只能以0kb/s的速度等着了。



Tracker服务器是P2P网络的弱点，如果Tracker被关闭或封禁，那就无法找到同伴，也就难以完成下载。