### 关键代码

#### 相关继承实现

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```



#### 关键成员变量

```java
private static final int DEFAULT_CAPACITY = 10;
private static final Object[] EMPTY_ELEMENTDATA = {};
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

transient Object[] elementData;
private int size; // the number of elements it contains
```



#### 构造函数

```java
// 构造函数
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}

public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}	

public ArrayList(Collection<? extends E> c) {
    elementData = c.toArray();
    if ((size = elementData.length) != 0) {
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    } else {
        // replace with empty array.
        this.elementData = EMPTY_ELEMENTDATA;
    }
}
```

##### Arrays.copyOf()

```java
// 属于浅拷贝
public static <T,U> T[] copyOf(U[] original, int newLength, 
                               Class<? extends T[]> newType) {
    @SuppressWarnings("unchecked")
    // 创建与传入参数 newType 相同长度和类型的数组
    T[] copy = ((Object)newType == (Object)Object[].class)
        ? (T[]) new Object[newLength]
        : (T[]) Array.newInstance(newType.getComponentType(), newLength);
    System.arraycopy(original, 0, copy, 0,
                     Math.min(original.length, newLength)); 
    return copy;
}
```

- .class

    ```java
    System.out.println(String[].class);          // class [Ljava.lang.String;
    System.out.println(Object[].class);  		 // class [Ljava.lang.Object;
    System.out.println((Object)String[].class);  // class [Ljava.lang.String;
    ```

    

- Class.getComponentType()

    ```
    Returns the {@code Class} representing the component type of an array.
    ```

#### 相关实现



##### clone

```java
public Object clone() {
    try {
        ArrayList<?> v = (ArrayList<?>) super.clone();
        v.elementData = Arrays.copyOf(elementData, size);
        v.modCount = 0;
        return v;
    } catch (CloneNotSupportedException e) {
        // this shouldn't happen, since we are Cloneable
        throw new InternalError(e);
    }
} 
```



##### E get(int index) &E set(int index, E element)

```java
public E get(int index) {
    rangeCheck(index); // 检查越界
    return elementData(index);
}

public E set(int index, E element) {
    rangeCheck(index);
    E oldValue = elementData(index);
    elementData[index] = element;
    return oldValue;
}

E elementData(int index) {
    return (E) elementData[index];
}
```



##### boolean add(E e) & void add(int index, E element)

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}

// 1. 检查添加范围 [0, size] 
// 2. 增加 capacity
// 3. 自index(含) 处向后移动数组
public void add(int index, E element) {
    rangeCheckForAdd(index);
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    System.arraycopy(elementData, index, elementData, index + 1,
                     size - index);
    elementData[index] = element;
    size++;
}

public boolean addAll(Collection<? extends E> c) {
    Object[] a = c.toArray();
    int numNew = a.length;
    ensureCapacityInternal(size + numNew);  // Increments modCount
    System.arraycopy(a, 0, elementData, size, numNew);
    size += numNew;
    return numNew != 0;
}


private void ensureCapacityInternal(int minCapacity) {
    // 默认创建ArrayList时，elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);//DEFAULT_CAPACITY=10 
    }
    ensureExplicitCapacity(minCapacity);
}

// 确保 capacity 限制
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    int oldCapacity = elementData.length; // capacity 而非 size
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0) // MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```

​	**boolean addAll(int index, Collection<?  extends E> c)**

​	

##### E remove(int index) & boolean remove(Object o)

```java
public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);

    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work
    return oldValue;
}

public boolean remove(Object o) {
    if (o == null) {
        for (int index = 0; index < size; index++)
            if (elementData[index] == null) {
                fastRemove(index);
                return true;
            }
    } else {
        for (int index = 0; index < size; index++)
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
    }
    return false;
}

// 与 remove(int index) 相比只是不 check 边界 和 不带返回值
private void fastRemove(int index) {
    modCount++;
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work
}
```

​	**void removeRange(int fromIndex, int toIndex);**



##### indexOf(Object o)

```java
public int indexOf(Object o) {
    if (o == null) { // 可见可以插入 null 值
        for (int i = 0; i < size; i++)
            if (elementData[i]==null)
                return i;
    } else {
        for (int i = 0; i < size; i++)
            if (o.equals(elementData[i]))
                return i;
    }
    return -1;
}
```



##### Object[]  toArray() & <T> T[] toArray(T[] a)

```java
public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
}

// 可以通过传入 new CustomeObject[]{}; 来返回 CustomObject.class 类型的数组
public <T> T[] toArray(T[] a) {
    if (a.length < size)
        // Make a new array of a's runtime type, but my contents:
        return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    System.arraycopy(elementData, 0, a, 0, size);
    if (a.length > size)
        a[size] = null;
    return a;
}
```





##### void writeObject(ObjectOutputStream s) & void readObject(ObjectInputStream s)

```java
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
   
    int expectedModCount = modCount;
    s.defaultWriteObject();
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}

private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    elementData = EMPTY_ELEMENTDATA;
    
    s.defaultReadObject();
    // Read in capacity
    s.readInt(); // ignored

    if (size > 0) {
        // be like clone(), allocate array based upon size not capacity
        ensureCapacityInternal(size);
        Object[] a = elementData;
        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            a[i] = s.readObject();
        }
    }
}

```

​	**ConcurrentModificationException()**

​	&emsp;- 多线程修改ArrayList数据会抛出此异常；

​	&emsp;- 单线程使用Iterator时候对ArrayList进行增删会抛出异常；



##### trimToSize()

```java
public void trimToSize() {
    modCount++; // 修改次数
    if (size < elementData.length) {
        elementData = (size == 0)
          ? EMPTY_ELEMENTDATA
          : Arrays.copyOf(elementData, size);  // 创建新的较短数组
    }
}
```





