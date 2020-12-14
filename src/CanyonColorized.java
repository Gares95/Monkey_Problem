import java.util.*;
/* Auxiliary class to visualize the threads' (monkeys) state.*/
public class CanyonColorized extends Canyon{
	
	// Creating an empty HashMap 
    HashMap<String, String> monkey_map = new HashMap<String, String>(); 
    
	// Auxiliary string to print for each column minus the length of the monkey name
    private String sep1 = "  |                      ";
	private String sep = "  |                    ";
	
	// Constructor
	public CanyonColorized(){
		super(); 
		
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
	
	
	// Function that prints a table with the monkeys waiting and crossing
	@Override
	public void printStats() {
		
		// Auxiliary int to count the number of characters to remove from the String "sep"
		int aux = 0;
		
		String monkeysString = "";
		
		// Strings to create the head of the table
		System.out.println("        Waiting >              Crossing               < Waiting");
        System.out.println(" ______________________________________________________________________");
        
        
    	aux = 0;
    	for(int i = WEL.size(); i> 0; i--) {
    		monkeysString = monkeysString + " "+monkey_map.get(WEL.get(i-1))+">";
        	aux+= 3;
        }
    	System.out.print(sep1.substring(2, sep1.length()-(2+aux)));
    	System.out.print(monkeysString);
    	aux = 0;
    	monkeysString = "";
    	for(int i = CrossingEL.size(); i>= 1; i--) {
    		monkeysString = monkeysString +" "+monkey_map.get(CrossingEL.get(i-1))+">";
            aux+= 3;
        }
    	for(int i = 0; i< CrossingWL.size(); i++) {
    		monkeysString = monkeysString +"<"+ monkey_map.get(CrossingWL.get(i))+ " ";
            aux+= 3;
          }
    	System.out.print(sep.substring(0, sep.length()-aux));
    	System.out.print(monkeysString);
    	System.out.print("    |  ");
    	aux = 0;
    	for(int i = 0; i< WWL.size(); i++) {
          	System.out.print("<" + monkey_map.get(WWL.get(i))+ " " );
          	aux+= 3;
          }
    	System.out.println("                    |".substring(aux));
        
        // String to create the end of the table
        System.out.println(" ----------------------------------------------------------------------");
	}
}
