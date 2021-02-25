public class Sorter<T> {
      public void sort(T[] arr, MComparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j], arr[minPos]) == -1 ? j : minPos;
            }
            T temp = arr[i];
            arr[i] = arr[minPos];
            arr[minPos] = temp;
        }
    }
}
