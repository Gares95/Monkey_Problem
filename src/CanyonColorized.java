import java.util.*;
/* Auxiliary class to visualize the threads' (monkeys) state.*/
public class CanyonColorized {
	
	// Lists to track the number of monkeys traveling and waiting from each side
	private List<String> WWL;
	private List<String> WEL;
	private List<String> CrossingWL;
	private List<String> CrossingEL;

	// Creating an empty HashMap 
    HashMap<String, String> monkey_map = new HashMap<String, String>(); 
    
	// Auxiliary string to print for each column minus the length of the monkey name
    private String sep1 = "  |                      ";
//    private String sep2 = "     |  ";
	private String sep = "  |                    ";
	
	// Constructor
	public CanyonColorized(){
		WWL = new LinkedList<String>();
		WEL = new LinkedList<String>();
		CrossingEL = new LinkedList<String>();
		CrossingWL = new LinkedList<String>(); 
		
		// Code for the different colors
		monkey_map.put("Black", "\033[47m"+ "\033[1;30m"+ "m" + "\u001B[0m"); 
	    monkey_map.put("Yellow", "\033[1;33m"+ "m" + "\u001B[0m"); 
	    monkey_map.put("Blue", "\033[1;34m" + "m" + "\u001B[0m"); 
	    monkey_map.put("Pink", "\033[1;35m"+ "m" + "\u001B[0m"); 
	    monkey_map.put("Cyan", "\033[1;36m"+ "m" + "\u001B[0m"); 
	    monkey_map.put("White", "\033[1;37m"+ "m" + "\u001B[0m"); 
	    monkey_map.put("Green", "\033[1;32m"+ "m" + "\u001B[0m"); 
	    monkey_map.put("Red", "\033[1;31m"+ "m" + "\u001B[0m"); 
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
		
		// Auxiliary int to count the number of characters to remove from the String "sep"
		int aux = 0;
		
		String monkeysString = "";
		
		// Auxiliary int to get the greatest number of rows for each iteration
//		int maxRows = Math.max(Math.max(WEL.size(),CrossingEL.size()),CrossingWL.size());
//		maxRows = Math.max(maxRows, WWL.size());
		
		// Strings to create the head of the table
		System.out.println("        Waiting >              Crossing               < Waiting");
        System.out.println(" ______________________________________________________________________");
        
        
//        for (int i = 0; i <maxRows;i++) {
//    	System.out.print("|    ");
    	aux = 0;
    	for(int i = WEL.size(); i> 0; i--) {
        	// System.out.print(" "+monkey_map.get(WEL.get(i-1))+">");
    		monkeysString = monkeysString + " "+monkey_map.get(WEL.get(i-1))+">";
        	aux+= 3;
        }
    	System.out.print(sep1.substring(2, sep1.length()-(2+aux)));
    	System.out.print(monkeysString);
//    	System.out.print("    ");
    	aux = 0;
    	monkeysString = "";
    	for(int i = CrossingEL.size(); i>= 1; i--) {
//        	System.out.print(" "+monkey_map.get(CrossingEL.get(i-1))+">");
    		monkeysString = monkeysString +" "+monkey_map.get(CrossingEL.get(i-1))+">";
            aux+= 3;
        }
    	for(int i = 0; i< CrossingWL.size(); i++) {
//            System.out.print("<"+ monkey_map.get(CrossingWL.get(i))+ " ");
    		monkeysString = monkeysString +"<"+ monkey_map.get(CrossingWL.get(i))+ " ";
            aux+= 3;
          }
//    	System.out.print(sep1.substring(0, sep1.length()-aux));
//    	System.out.print(sep.substring(aux));
    	System.out.print(sep.substring(0, sep.length()-aux));
    	System.out.print(monkeysString);
    	System.out.print("    |  ");
    	aux = 0;
    	monkeysString = "";
    	for(int i = 0; i< WWL.size(); i++) {
          	System.out.print("<" + monkey_map.get(WWL.get(i))+ " " );
//    		monkeysString = monkeysString +"<" + monkey_map.get(WWL.get(i))+ " ";
          	aux+= 3;
          }
//    	System.out.print(sep2.substring(0, sep2.length()-aux));
//    	System.out.println(sep.substring(aux,12));
    	System.out.println("                    |".substring(aux));
//    	System.out.println("|");
        // For the finish line when every monkey has finished
//        if(maxRows==0) {
//        	aux = 0;
//        	System.out.print("|    ");
//        	System.out.print(sep.substring(aux));
//        	System.out.print(sep.substring(aux));
//        	System.out.println(sep.substring(aux,12));        	
//        }
        
        // String to create the end of the table
        System.out.println(" ----------------------------------------------------------------------");
	}
}
