package adv;
import java.util.Random;
public class rand {


	    public static void main(String[] args) {
	        Random random = new Random();

	        // Generate random integers
	        int randomInt = random.nextInt(100); // 0 to 99
	        System.out.println("Random Integer (0-99): " + randomInt);

	        // Generate random double
	        double randomDouble = random.nextDouble(); // 0.0 to 1.0
	        System.out.printf("Random Double: %.4f%n", randomDouble);

	        // Generate random boolean
	        boolean randomBool = random.nextBoolean();
	        System.out.println("Random Boolean: " + randomBool);

	        // Pick a random element from an array
	        String[] fruits = {"Apple", "Banana", "Cherry", "Mango", "Orange"};
	        String randomFruit = fruits[random.nextInt(fruits.length)];
	        System.out.println("Random Fruit: " + randomFruit);

	        // Generate a random number in a custom range [min, max]
	        int min = 10, max = 50;
	        int randomInRange = random.nextInt(max - min + 1) + min;
	        System.out.println("Random Number (" + min + "-" + max + "): " + randomInRange);
	    }
	}

}
