
import java.util.Random;

/* The  Monkey class that implements Runnable will perform different actions using the methods
 * of the class Rope.*/
public class Monkey implements Runnable{
	Rope rope;
	private int mDir;
	private String color;
	private String [] way = new String[]{"East", "West"};
	Random random = new Random();
	
	// Constructor
	public Monkey (Rope rope, int mDir, String color) {
		this.rope = rope;
		this.mDir = mDir;
		this.color = color;
	}
	
	public void run() {
		try {
			// Already implemented the time arrival in the main Thread
			// Thread.sleep((random.nextInt(9)*1000));
			
			// Initial message to indicate that the instance of Monkey was created and is running
			// System.out.println(color + " monkey showed up from "+ way[1-mDir]+" wanting to cross to the " + way[mDir]+".");
			rope.showUp(mDir, color);
			
			// Using the function sleep to represent the time the "Monkey" takes to reach the Rope
			Thread.sleep((1000));
			System.out.println(color + " monkey ready to cross to the "+ way[mDir]+".");
			rope.cross(mDir, color);
			// Using the function sleep to represent the time the "Monkey" takes to cross the Rope
			Thread.sleep((4000));
			// System.out.println(color + " monkey has almost crossed the rope to the "+ way[mDir]+"." );
			rope.leave(mDir, color);
			// System.out.println(color + " monkey has crossed the rope.");
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Operation interrupted");
		}
		
	}

}
