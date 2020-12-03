import java.util.Random;
public class Main {
	public static void main( String[] args) {
		Rope rope = new Rope();
		String [] colors = new String[] {"Red", "Yellow", "Blue", "Brown", "White", "Grey", "Pink", "Orange"};
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
		Thread [] hilos = new Thread[nMonkeys];
		Random random = new Random();
		
		for (int i = 0; i<nMonkeys; i++) {
			hilos[i] = new Thread(new Monkey(rope, random.nextInt(2), colors[i]));
			hilos[i].start();
		}
		
	}

}