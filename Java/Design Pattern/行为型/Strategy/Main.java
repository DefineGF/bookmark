import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Dog[] a= {new Dog(4), new Dog(3), new Dog(5)};
        DogComparator comparator = new DogComparator();
        Sorter<Dog> sorter = new Sorter<>();
        sorter.sort(a, comparator);
        System.out.println(Arrays.toString(a));
    }
}
