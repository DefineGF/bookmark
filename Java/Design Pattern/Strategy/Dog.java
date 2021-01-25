public class Dog {
    int foodCount;
    public Dog(int foodCount){
        this.foodCount = foodCount;
    }
    
    @Override
    public String toString() {
        return "Dog food_count = " + foodCount;
    }
}
