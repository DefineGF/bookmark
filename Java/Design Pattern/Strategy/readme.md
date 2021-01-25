#### 实体类
Dog -> foodCount(int)

#### 比较器
interface MComparator<T> -> int compare(T, T)

#### 比较器实现类
DogComparator implements MComparator<Dog>

#### 排序类
Sorter<T> -> void sort(T[], MComparator<T>)

#### 具体调用
new Sorter<Dog>().sort(Dog[] dogs, new DogComparator<Dog>());
