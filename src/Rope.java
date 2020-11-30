//import java.util.Random;

public class Rope {
//	private int monkeyDirection;
	private int[] numMonkeysWaiting;
	private int[] monkeysCrossing;

	private String [] way = new String[]{"East", "West"};
	
	
	public Rope() {
		numMonkeysWaiting = new int[]{0,0};
		monkeysCrossing = new int[]{0,0};
	}
	
	public synchronized void arrive(int direction) throws InterruptedException {
		System.out.println("Arrive open.");
		numMonkeysWaiting[direction]++;
		System.out.println(numMonkeysWaiting[direction] + " monkey/ies waiting to cross to the "+ way[direction]+".");

		int auxMessage = 0;
		while((monkeysCrossing[1-direction] > 0)||((monkeysCrossing[direction] > 0)&&(numMonkeysWaiting[1-direction]>0)))
		{
			try{
				if(auxMessage==0) {
					System.out.println("Monkey waiting because there are other monkeys in the opposite side waiting already.");
					auxMessage++;
				}
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("1 monkey down.");
			}
		}
//		monkeyDirection = direction;
		System.out.println("Arrive closed.");
	}
	
	public synchronized void cross(int direction) throws InterruptedException {
		System.out.println("Cross open.");
		numMonkeysWaiting[direction]--;
		notifyAll();
		int auxMessage = 0;
		while(monkeysCrossing[1-direction] > 0)
		{
			try{
				if(auxMessage==0) {
					System.out.println("Monkey waiting to other monkey finish crossing in opposite direction.");
					auxMessage++;
				}
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("1 monkey down.");
			}
		}
		System.out.println(numMonkeysWaiting[direction] + " monkey/ies waiting to cross to the "+ way[direction]+".");
		monkeysCrossing[direction]++;
		System.out.println(monkeysCrossing[direction] + " monkey/ies crossing to the "+ way[direction]+".");
		System.out.println("Cross closed.");
	}
	
	public synchronized void leave(int direction) {
		System.out.println("Leave open.");
		monkeysCrossing[direction]--;
		notifyAll();
		System.out.println("Leave closed.");
		
	}

}
