public class DogComparator implements MComparator<Dog> {

     @Override
     public int compare(Dog t1, Dog t2) {
         if (t1.foodCount == t2.foodCount)
             return 0;
         return t1.foodCount < t2.foodCount ? -1 : 1;
     }
 }
