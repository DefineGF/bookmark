#### RSA算法

非对称密钥，公开密钥算法

​    A发布一个公开锁，所有人都知道；那么其他人向A发送信息，只需将信息用这个公开锁加密，A用自己的私钥打开即可。那么A向B发消息，那么A只需知道B公开的锁就行了。

假设原文为：x

取两个互质的质数（尽量大足够安全）p，q，令n = p  *  q;

f(n) = (p - 1)(q - 1)

任取e，使得其与f(n)互质，且e<n，则加密：c = x^e

挑选d，使得e*d mod n = 1；那么解密：c^d

原理：证明 (x^e)^d mod n = x

​     -> 证明x^(ed) - x可以被 n 整除

​     因为：e*d = 1 + k*n = 1 + k*(p-1)*(q-1)

欧拉定理：

**若正整数** **a , n** **互质，则**  **a****φ(n)****≡1(mod n)  **其中** **φ(n)** **是欧拉函数****（1~n)** **与** **n** **互质的数**

费马小定理(欧拉定理特例)：

**对于质数****p****，任意整数****a****，均满足：****a****p****≡a（mod p）-> a^(p-1) \* a = a (mod p);而 a** **≡** **a （mod p）-> a^(p-1) mod p = 1;**



**由费马小定理得：****x^(k(p-1))^(q-1)可以被q整除，x^(k(q-1))^(p-1)可以被p整除.**

**所以p，q是x^(ed)-x的质数因子，那么p\*q = n是x^(ed)-x的因子**

**所以x^(ed)-x能被n整除；得证！**