import java.util.Random;
public class Main {
	public static void main( String[] args) {
		Rope rope = new Rope();
		String [] colors = new String[] {"Red", "Yellow", "Blue", "Brown", "White", "Grey", "Pink", "Orange"};
		final int nMonkeys = 3;
		Thread [] hilos = new Thread[nMonkeys];
		Random random = new Random();
		
		for (int i = 0; i<nMonkeys; i++) {
			hilos[i] = new Thread(new Monkey(rope, random.nextInt(2), colors[i]));
			hilos[i].start();
		}
		
	}
}
