import java.util.*;
/* Auxiliar class to visualize the threads' (monkeys) state.*/
public class Canyon {
	
	// Lists to track the number of monkeys traveling and waiting from each side
	private List<String> WWL;
	private List<String> WEL;
	private List<String> CrossingWL;
	private List<String> CrossingEL;
	
	// Auxiliar string to print for each column minus the length of the monkey name
	private String sep = "           |    ";
	
	// Constructor
	public Canyon(){
		WWL = new LinkedList<String>();
		WEL = new LinkedList<String>();
		CrossingEL = new LinkedList<String>();
		CrossingWL = new LinkedList<String>(); 
	}
	
	// Function that adds waiting monkeys to the list depending on which direction they are going
	public void addWaitMonkey (String monkeyName, int dir) {
		
		if (dir == 0) 
			WEL.add(monkeyName);
		
		else 
			WWL.add(monkeyName);
	}
	
	// Function that adds crossing monkeys (and removes waiting monkeys) to the list 
	// depending on which direction they are going
	public void addCrossingMonkey (String monkeyName, int dir) {
		
		if (dir == 0) {
			WEL.remove(monkeyName);			
			CrossingEL.add(monkeyName);
		}
		
		else {
			WWL.remove(monkeyName);	
			CrossingWL.add(monkeyName);
		}
			
	}
	
	// Function that removes crossing monkeys of the list after they have finished crossing
	public void removeMonkey (String monkeyName, int dir) {
		if (dir == 0) {	
			CrossingEL.remove(monkeyName);
		}
		
		else {
			CrossingWL.remove(monkeyName);
		}
	}
	
	// Function that prints a table with the monkeys waiting and crossing
	public void printStats() {
		
		// Auxiliar int to count the number of characteres to remove from the String "sep"
		int aux = 0;
		
		// Auxiliar int to get the greatest number of rows for each iteration
		int maxRows = Math.max(Math.max(WEL.size(),CrossingEL.size()),CrossingWL.size());
		maxRows = Math.max(maxRows, WWL.size());
		
		// Strings to create the head of the table
		System.out.println("     Waiting >       Crossing      < Waiting");
        System.out.println(" _______________________________________________");
        
        
        for (int i = 0; i <maxRows;i++) {
        	System.out.print("|    ");
        	aux = 0;
        	if(i<WEL.size()) {
        		System.out.print(WEL.get(i) + " >");
        		aux= aux + WEL.get(i).length()+2;
        	}
        	System.out.print(sep.substring(aux));
        	aux = 0;
        	if(i<CrossingEL.size()) {
        		System.out.print(CrossingEL.get(i) + " >");
        		aux= aux + CrossingEL.get(i).length()+2;
        	}
        	if(i<CrossingWL.size()) {
        		System.out.print("< "+ CrossingWL.get(i));
        		aux= aux + CrossingWL.get(i).length()+2;
        	}
        	System.out.print(sep.substring(aux));
        	
        	aux = 0;
        	if(i<WWL.size()) {
        		System.out.print("< "+ WWL.get(i));
        		aux= aux + WWL.get(i).length()+2;
        	}
        	System.out.println(sep.substring(aux,12));
        }
        
        // For the finish line when every monkey has finished
        if(maxRows==0) {
        	aux = 0;
        	System.out.print("|    ");
        	System.out.print(sep.substring(aux));
        	System.out.print(sep.substring(aux));
        	System.out.println(sep.substring(aux,12));        	
        }
        
        // String to create the end of the table
        System.out.println(" -----------------------------------------------");
	}
}
