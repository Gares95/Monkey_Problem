import java.util.Random;

/* Main function to create the instances of the different classes and to start the Threads 
 * that will run during this concurrency exercise.*/
public class Main {	
	public static void main( String[] args) {
		Canyon canyon = new Canyon();

		// Instance of the class Rope 
		Rope rope = new Rope(canyon);
		// Auxiliar variable to select the direction the instances of the class Monkey will be taking
		int direction;
		
		// Array of colors that will differentiate the instances of the class Monkey
		String [] colors = new String[] {"Red", "Yellow", "Blue", "Brown", "White", "Grey", "Pink", "Orange"};
		
		// The default number of the instances of the class Monkey is 8
		int nMonkeys = 8;
		if ((args.length > 0)) {
			try {
				if ((Integer.parseInt(args[0]) > 0)&&(Integer.parseInt(args[0]) < 9))
					nMonkeys = Integer.parseInt(args[0]);
			}catch (NumberFormatException e) {
				System.err.println("Argument must be an integer.");
				System.exit(1);
			}
		}
		
		// Array of Threads that will run the instances of the class Monkey
		Thread [] hilos = new Thread[nMonkeys];
		
		// Auxiliar variable to get the random values
		Random random = new Random();
		
		for (int i = 0; i<nMonkeys; i++) {
			// Random values for the directions (0 = "East", 1 = "West")
			direction = random.nextInt(2);
			
			try {
				// To force a minimum inter-monkey spacing of 1 second
				Thread.sleep((random.nextInt((9-1)/nMonkeys)+1)*1000);
				
				hilos[i] = new Thread(new Monkey(rope, direction, colors[i]));
				hilos[i].start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}