#### 常用方法

##### 检索元素

| `boolean` | `isEmpty()`                    | Returns `true` if this list contains no elements.            |
| :-------- | ------------------------------ | ------------------------------------------------------------ |
| `boolean` | `contains(Object o)`           | Returns `true` if this list contains the specified element.  |
| `boolean` | `containsAll(Collection<?> c)` | Returns `true` if this list contains all of the elements of the specified collection. |
| `int`     | `indexOf(Object o)`            | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| `int`     | `lastIndexOf(Object o)`        | Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. |



##### 添加元素：

|  **`void`**   | add(int index, E element)                          | Inserts the specified element at the specified position in this list (optional operation). |
| :-----------: | -------------------------------------------------- | ------------------------------------------------------------ |
| **`boolean`** | **`add(E e)`**                                     | **Appends the specified element to the end of this list (optional operation).** |
| **`boolean`** | **`addAll(int index, Collection<? extends E> c)`** | **Inserts all of the elements in the specified collection into this list at the specified position (optional operation).** |
| **`boolean`** | **`addAll(Collection<? extends E> c)`**            | **Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).** |

##### 获取元素 & 修改元素

| `E`  | `get(int index)`            | Returns the element at the specified position in this list   |
| :--- | --------------------------- | ------------------------------------------------------------ |
| `E`  | `set(int index, E element)` | Replaces the element at the specified position in this list with the specified element (optional operation). |

##### 删除元素

| **`E`**       | **`remove(int index)`**          | **Removes the element at the specified position in this list (optional operation).** |
| :------------ | -------------------------------- | ------------------------------------------------------------ |
| **`boolean`** | **`remove(Object o)`**           | **Removes the first occurrence of the specified element from this list, if it is present (optional operation).** |
| **`boolean`** | **`removeAll(Collection<?> c)`** | **Removes from this list all of its elements that are contained in the specified collection (optional operation).** |
| `void`        | `clear()`                        | Removes all of the elements from this list (optional operation). |

##### 其他

| `default void` | `sort(Comparator<? super E> c)`       | Sorts this list according to the order induced by the specified [`Comparator`](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Comparator.html). |
| :------------- | ------------------------------------- | ------------------------------------------------------------ |
| `List<E>`      | `subList(int fromIndex, int toIndex)` | Returns a view of the portion of this list between the specified `fromIndex`, inclusive, and `toIndex`, exclusive. |
| `Object[]`     | `toArray()`                           | Returns an array containing all of the elements in this list in proper sequence (from first to last element). |
