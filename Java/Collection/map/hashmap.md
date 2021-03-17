### HashMap

#### 属性

```java
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16 默认 capacity
static final int TREEIFY_THRESHOLD = 8; // hash冲突过多，导致链表过长，（默认超过8）就会将链表转换为红黑树
static final float DEFAULT_LOAD_FACTOR = 0.75f; // 装填因子
int threshold; // 阈值 = 容量 * 装载因子 （capacity * factor）; size 达到 阈值吗，容量加倍
transient Node<K,V>[] table;
transient Set<Map.Entry<K,V>> entrySet;
```

##### threshold

- 时间敏感：降低 factor， 减少碰撞
- 空间敏感：增大 factor，减少 threshold

##### interface Map.Entry<K, V>

```
K getKey();
V getValue();
V setValue(V value);
int hashCode();
```



##### class Node<K, V> implements Map.Entry<K, V>

```java
final int hash;
final K key;
V value;
Node(int hash, K key, V value, Node<K,V> next) {
	this.hash = hash;
	this.key = key;
	this.value = value;
	this.next = next;
}

// 此处的 hashCode 仅是作为 Object 的派生类从而重写的方法
public final int hashCode() {
    return Objects.hashCode(key) ^ Objects.hashCode(value);
}
```



#### 构造函数

```java
HashMap()								   // 构造函数：容量大小默认为16，装载因子默认为 0.75
HashMap(int capacity)					   // 指定“容量大小”的构造函数
HashMap(int capacity, float loadFactor)    // 指定“容量大小”和“加载因子”的构造函数
HashMap(Map<? extends K, ? extends V> map) // 包含“子Map”的构造函数
```



#### 常用函数

```java
V                    get(Object key)
V                    put(K key, V value)
void                 putAll(Map<? extends K, ? extends V> map)
V                    remove(Object key)
boolean              containsKey(Object key)
boolean              containsValue(Object value)
boolean              isEmpty()
int                  size()
Set<K>               keySet()
Collection<V>        values()
void                 clear()
Object               clone()
Set<Entry<K, V>>     entrySet()
```



#### 具体实现

##### 容量capacity

2的次方值，这是为了在取数组索引时：hash & (capacity - 1)；当capacity为2的次方数时，capacity - 1 ： 0011\**\*1,这样效果等于 hash % capacity;

相对而言，HashTable 初始化桶的大小为：11；



##### hash

```java
// Object.hashCode()
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

注意此处的 hash 值，与 table.length 相与 就可得到 key 对应的 数组下标；

key不相等的情况下，hash 或相等（key相等，则 hash必不相等）


##### V get(Object key)

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; 
    Node<K,V> first, e; 
    int n;  // table.length = 2 ^ n
    K k;
    if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```



##### V put(K key, V value)

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,  boolean evict) {
    Node<K,V>[] tab; 
    Node<K,V> p;  // 记录首元素
    int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    
    if ((p = tab[i = (n - 1) & hash]) == null){
    	tab[i] = newNode(hash, key, value, null); // 插入新值
    } else {
        Node<K,V> e;
        K k;
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

插入流程：

 <img src="F:\Typora\Nodes\java\集合\map\58e67eae921e4b431782c07444af824e_r.jpg" alt="58e67eae921e4b431782c07444af824e_r" style="zoom:50%;" />

##### resize 原理

 ![a285d9b2da279a18b052fe5eed69afe9_hd](F:\Typora\Nodes\java\集合\map\a285d9b2da279a18b052fe5eed69afe9_hd.png)

通过新capacity计算出来的key与原来相比要么相同，要么比原来多 **oldCap** (newCap = oldCap * 2）；例如：当oldCap = 16 下index = 15 的时候，newCap = 32 下的索引要么为15，要么为31：

 ![544caeb82a329fa49cc99842818ed1ba_hd](F:\Typora\Nodes\java\集合\map\544caeb82a329fa49cc99842818ed1ba_hd.png)