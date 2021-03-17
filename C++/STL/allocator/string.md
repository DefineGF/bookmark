#### string

##### 创建

```c++
string s("cheng");
string s("cheng", 3); // "che"
string s(4, "="); // "===="

string source("old string");
string target(source, 0, source.length()); // s.length() == s.size()
```

##### size_type 类型

体现了标准库类型和机器无关的特性；

属于无符号数，推荐使用 auto 声明长度；

错误点：

```c++
str.size() < n; // 当 n 为负数时，n 会自动转换为 数值很大的无符号数，从而导致判断结果出错；
```



##### 比较

- 相等：长度和对应字符都相等；
- 长度不同：较短字符与较长字符对应字符都相等，则短string  < 长string；
- 其他：第一对相异字符比较的结果；



##### 拼接 & 截取

- 添加后缀
    - +：只能运算 string 类型
    - s.append("tail")；可以添加 char 类型

- 截取子串

    string substr(int pos = 0, int len)



##### 查找

```c++
// 查找失败 返回 -1
int find(char c,int pos=0) const;  				// 查找字符，默认自 0 始 
int find(const char *s, int pos=0) const;  		// 查找字符串
int find(const string &s, int pos=0) const;     

// 反向查找
int rfind(char c, int pos=npos) const;  
int rfind(const char *s, int pos=npos) const;
int rfind(const string &s, int pos=npos) const;
```

##### 截取

```c++
str.substr(int pos, int len);
```



##### 替换

```c++
string &replace(int pos, int n, const char *s);		 //删除从pos开始的n个字符，然后在pos处插入串s
string &replace(int pos, int n, const string &s);    //删除从pos开始的n个字符，然后在pos处插入串s
void swap(string &s2);   							 //交换当前字符串与s2的值
```



##### 插入

```c++
string &insert(int pos, const char *s);
string &insert(int pos, const string &s);
```



##### 删除

```c++
string &erase(int pos=0, int n=len);  //删除pos开始的n个字符，返回修改后的字符串
```



##### 转换为 char 数组

```c++
string in = "cheng ming";
const char* cs;
cs = in.c_str();

char *cs = in; // 错误：不能使用 string 初始化 char*
```



#### 相关技巧

##### 去除空格

```c++
string in = "4 + 2 * (1 + 2) - 7 / 11";
while (in.find(' ') != -1) {
    in.erase(in.find(' '), 1);
}
```

#####  char 转 string

```+-
// 方法一
string str;
str.append('c');

// 方法二
string str(1, 'c'); // 构造一个字符 'c' 的字符串

// 方法三
str.push_back('c');
```

##### c++vector 转 string

```c++
string str;
vector<int> v{1,2,3};
str.assign(v.begin(), v.end());
```



##### string 转换为 int

```
string str = "120";
int num = stoi(str);
```

