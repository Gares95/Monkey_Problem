/* Rope class that contains the methods to cross the Rope. It also contains the conditions and functions
 * to handle concurrency.*/
public class Rope {
	// Array that will contain the number of "Monkeys" waiting in each side ([0] = "East", [1]= "West")
	private int[] numMonkeysWaiting;
	// Array that will contain the number of "Monkeys" currently croosing the rope
	private int[] monkeysCrossing;
	// Array that will contain the values to indicate which side has priority (1 has priority, 0 does not)
	private int[] directionPriority;
	
	// Array that contains the name of the sides of the canyon
	private String [] way = new String[]{"East", "West"};
	
	// Constructor
	public Rope() {
		numMonkeysWaiting = new int[]{0,0};
		monkeysCrossing = new int[]{0,0};
		directionPriority = new int[]{1,1};
	}
	
	/* Method cross:
	 * This function represent the actions the instances of the class Monkey can make considering the different conditions:
	 * - There are monkeys crossing from the other side
	 * - There are monkeys waiting on the other side that have priority
	 * This implements the Guarded Block that makes the concurrency to function.
	 * (Diagram in README file)
	 * 
	 * To select monkeys of each side using the arrays, the variable "direction" given as parameter will be used.
	 * To indicate the opposite side "1-direction" will be used (considering that the only two values possible for 
	 * the variable "direction" are 1 and 0).*/
	public synchronized void cross(int direction) throws InterruptedException {
		
		// When the method begins it adds a "Monkey" to the array indicating the number of "Monkeys" waiting in each side
		numMonkeysWaiting[direction]++;
		System.out.println(numMonkeysWaiting[direction] + " monkey/s waiting to cross to the "+ way[direction]+".");

		// Auxiliar variable to print message only once
		int auxMessage = 0;
		
		// Guarded Block condition: 
		// While there are monkeys crossing from the opposite direction or there are monkeys waiting on the other side with priority
		while((monkeysCrossing[1-direction] > 0)||((directionPriority[direction]==0)&&(directionPriority[1-direction]==1)))
		{
			try{
				// Get priority (and remove priority for monkeys on the other side)
				if(monkeysCrossing[1-direction] > 0) {
					directionPriority[direction]=1;
					directionPriority[1-direction]=0;
				}
				
				// Auxiliar messages to see the program execution
				if(auxMessage==0) {
					if(monkeysCrossing[1-direction] > 0) {
						System.out.println("Monkey waiting to other monkey finish crossing in opposite direction.");
					}
					if((monkeysCrossing[direction] > 0)&&(directionPriority[1-direction]==1)) 
						System.out.println("Monkey waiting because there are other monkeys in the opposite side waiting already.");					
					auxMessage++;
				}
				// invoke wait to suspend the current thread until another thread issues a notification
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Operation Interrupted.");
			}
		}
		// Increase and decrease the values of the respective arrays indicating the progress of the "Monkey"
		numMonkeysWaiting[direction]--;
		monkeysCrossing[direction]++;
		
		// If it is necessary to make the monkeys go one by one in case there are monkeys waiting in both sides
		/*if(numMonkeysWaiting[1- direction]>0)
			directionPriority[1-direction]=1;*/
		
		// If we want to force 1 second inter-monkey spacing in the rope as well
		/*Thread.sleep(1000);*/ // And reduce to 3 seconds the wait in Monkey class
		
		// Notify the other Threads
		notifyAll();
		
		// Auxiliar messages to follow progress of the program
		System.out.println(numMonkeysWaiting[direction] + " monkey/s waiting to cross to the "+ way[direction]+".");
		System.out.println(monkeysCrossing[direction] + " monkey/s crossing to the "+ way[direction]+".");
	}
	
	public synchronized void leave(int direction) {
		monkeysCrossing[direction]--;
		notifyAll();
		System.out.println("1 monkey has finished crossing to the "+ way[direction]+". "+  monkeysCrossing[direction] + " monkey/s left crossing to the "+ way[direction]);
	}

}