
import java.util.Random;
public class Monkey implements Runnable{
	Rope rope;
	private int mDir;
	private String color;
	private String [] way = new String[]{"East", "West"};
	Random random = new Random();
	public Monkey (Rope rope, int mDir, String color) {
		this.rope = rope;
		this.mDir = mDir;
		this.color = color;
	}
	public void run() {
		try {
			Thread.sleep((random.nextInt(9)*1000));
			
			System.out.println(color + " monkey showed up from "+ way[1-mDir]+" wanting to cross to the " + way[mDir]+".");
			Thread.sleep((1000));
			System.out.println(color + " monkey ready to cross to the "+ way[mDir]+".");
			rope.cross(mDir);
			Thread.sleep((4000));
			System.out.println(color + " monkey has almost crossed the rope to the "+ way[mDir]+"." );
			rope.leave(mDir);
			System.out.println(color + " monkey has crossed the rope.");
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Operation interrupted");
		}
		
	}

}
