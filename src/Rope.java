
public class Rope {
	private int[] numMonkeysWaiting;
	private int[] monkeysCrossing;
	private int[] directionPriority;

	private String [] way = new String[]{"East", "West"};
	
	public Rope() {
		numMonkeysWaiting = new int[]{0,0};
		monkeysCrossing = new int[]{0,0};
		directionPriority = new int[]{1,1};
	}
	
	public synchronized void cross(int direction) throws InterruptedException {
		numMonkeysWaiting[direction]++;
		System.out.println(numMonkeysWaiting[direction] + " monkey/s waiting to cross to the "+ way[direction]+".");

		int auxMessage = 0;
		while((monkeysCrossing[1-direction] > 0)||((directionPriority[direction]==0)&&(directionPriority[1-direction]==1)))
		{
			try{
				if(monkeysCrossing[1-direction] > 0) {
					directionPriority[direction]=1;
					directionPriority[1-direction]=0;
				}
				if(auxMessage==0) {
					if(monkeysCrossing[1-direction] > 0) {
						System.out.println("Monkey waiting to other monkey finish crossing in opposite direction.");
					}
					if((monkeysCrossing[direction] > 0)&&(directionPriority[1-direction]==1)) 
						System.out.println("Monkey waiting because there are other monkeys in the opposite side waiting already.");					
					auxMessage++;
				}
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Operation Interrupted.");
			}
		}
		numMonkeysWaiting[direction]--;
		monkeysCrossing[direction]++;
		
		// If it is necessary to make the monkeys go one by one in case there are monkeys waiting in both sides
		/*if(numMonkeysWaiting[1- direction]>0)
			directionPriority[1-direction]=1;*/
		
		// If we want to force 1 second inter-monkey spacing in the rope as well
		/*Thread.sleep(1000);*/ // And reduce to 3 seconds the wait in Monkey class
		
		
		notifyAll();
		
		System.out.println(numMonkeysWaiting[direction] + " monkey/s waiting to cross to the "+ way[direction]+".");
		System.out.println(monkeysCrossing[direction] + " monkey/s crossing to the "+ way[direction]+".");
	}
	
	public synchronized void leave(int direction) {
		monkeysCrossing[direction]--;
		notifyAll();
		System.out.println("1 monkey has finished crossing to the "+ way[direction]+". "+  monkeysCrossing[direction] + " monkey/s left crossing to the "+ way[direction]);
	}

}