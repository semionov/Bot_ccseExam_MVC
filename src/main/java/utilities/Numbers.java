package utilities;

public class Numbers {

    //generator of random number in concrete range
    public static int getRandomNumberFrom(int range) {
        return (int) (Math.random() * range);
    }
}
