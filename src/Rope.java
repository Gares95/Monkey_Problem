
public class Rope {
	private int[] numMonkeysWaiting;
	private int[] monkeysCrossing;

	private String [] way = new String[]{"East", "West"};
	
	
	public Rope() {
		numMonkeysWaiting = new int[]{0,0};
		monkeysCrossing = new int[]{0,0};
	}
	
	public synchronized void arrive(int direction) throws InterruptedException {
		numMonkeysWaiting[direction]++;
		System.out.println(numMonkeysWaiting[direction] + " monkey/ies waiting to cross to the "+ way[direction]+".");
		while((monkeysCrossing[1-direction] > 0)||(monkeysCrossing[direction] > 0)&&(numMonkeysWaiting[1-direction]>0))
		{
			try{
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("1 monkey down.");
			}
		}
		
	}
	
	public synchronized void cross(int direction) throws InterruptedException {
		numMonkeysWaiting[direction]--;
		System.out.println(numMonkeysWaiting[direction] + " monkey/ies waiting to cross to the "+ way[direction]+".");
		monkeysCrossing[direction]++;
		System.out.println(monkeysCrossing[direction] + " monkey/ies crossing to the "+ way[direction]+".");
		Thread.sleep((4000));
		
	}
	
	public synchronized void leave(int direction) {
		monkeysCrossing[direction]--;
		notifyAll();
		
	}

}