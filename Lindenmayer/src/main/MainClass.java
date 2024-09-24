package main;


/**
 * The main class
 * @author dubuiss212
 */
public class MainClass {
	
	
	private static int strToNumber(String... testNumber){
		try {
			return Integer.parseInt(testNumber[0]);
		} catch (Exception e) {
			return -1;
		}
	}


    /**
     * Main
     * @param args options supplémentaires, ici l'index par défaut d'une fractale
     */
    public static void main(String... args){
    	int index = strToNumber(args);
    	Interface i = index == -1 ? new Interface() : new Interface(index);
    }
    

}

