### LinkedList

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
```

本质上是双向链表：

```java
transient Node<E> first;
transient Node<E> last;
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```



#### 常用方法



##### 添加元素

- void add(int index, E element)
- boolean add(E e)
- boolean addAll(int index, COllection<? extends E> c)
- void addFirst(E e) 
- void addLast(E e)
- boolean offer(E e) : Adds the specified element as the tail (last element) of this list.
- boolean offerFirst(E e)
- boolean offerLast(E e)
- void push(E e)



##### 获取元素

- 获取元素但不删除：
    - E element() : Retrieves, but does not remove, the head (first element) of this list.

    - E get(int index)
    - E getFirst()
    - E getLast()
    - E peek() :Retrieves, but does not remove, the head (first element) of this list.
    - E peekFIrst()
    - E peekLast()

- 获取元素且删除：
    - E poll() : Retrieves and removes the head (first element) of this list.
    - E pollFirst():
    - E pollLast()
    - E pop() :Pops an element from the stack represented by this list.
    - E remove() : Retrieves and removes the head (first element) of this list.
    - E remove(int index) : 
    - boolean remove(Object o)
    - E removeFirst()
    - E removeLast()

**删除元素：**

- boolean removeLastOccurence(Object o)): Removes the last occurrence of the specified element in this list (when traversing the list from head to tail
- void clear()



#### 方法解析

##### 尾部添加元素：

```java
public boolean offer(E e) {
    return add(e);
}

public boolean add(E e) {
    linkLast(e);
    return true;
}

void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```



##### 头部添加元素

```java
public void push(E e) {
    addFirst(e);
}

public void addFirst(E e) {
    linkFirst(e);
}

private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null)
        last = newNode;
    else
        f.prev = newNode;
    size++;
    modCount++;
}
```

##### 头部删除元素



```java
public E pop() {
    return removeFirst();
}

public E poll() {
    final Node<E> f = first;
    return (f == null) ? null : unlinkFirst(f);
}

public E pollFirst() { // 哈哈 和 poll 实现一模一样
    final Node<E> f = first;
    return (f == null) ? null : unlinkFirst(f);
}

public E removeFirst() {
    final Node<E> f= first;
    if (f == null)
        throw new NoSuchElementException();
    return unlinkFirst(f);
}

private E unlinkFirst(Node<E> f) {
    // assert f == first && f != null;
    final E element = f.item;
    final Node<E> next = f.next;
    f.item = null;
    f.next = null; // help GC
    first = next;
    if (next == null)
        last = null;
    else
        next.prev = null;
    size--;
    modCount++;
    return element;
}
```

通过源码可以看出，在没有first 节点的情况下，poll 会返回null， 而pop 则直接调用会抛出异常的 removeFirst();



##### 尾部删除元素

```java
public E removeLast() {
    final Node<E> l = last;
    if (l == null)
        throw new NoSuchElementException();
    return unlinkLast(l);
}

private E unlinkLast(Node<E> l) {
    // assert l == last && l != null;
    final E element = l.item;
    final Node<E> prev = l.prev;
    l.item = null;
    l.prev = null; // help GC
    last = prev;
    if (prev == null)
        first = null;
    else
        prev.next = null;
    size--;
    modCount++;
    return element;
}
```
