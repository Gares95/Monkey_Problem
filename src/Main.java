import java.util.Random;
public class Main {
	public static void main( String[] args) {
		Rope rope = new Rope();
		String [] colors = new String[] {"Red", "Yellow", "Blue", "Brown", "White", "Grey", "Pink", "Orange"};
		int nMonkeys = 8;
		int direction;
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