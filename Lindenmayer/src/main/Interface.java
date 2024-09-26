package main;


import system.Fractals;


/**
 * Interface
 * @author Wester
 */
public class Interface {

	/** Index of a default fractal */
	public static int INDEX;


    public Interface(int index) {
        
        index %= Fractals.systemPresets.length;
        INDEX = index;
        
        TurtlePanel turtlePanel = new TurtlePanel(Fractals.systemPresets[index], Fractals.optionPresets[index]);
        MainFrame screen = new MainFrame(turtlePanel, new ConfigurationPanel(turtlePanel));
        
        Thread thread2 = new Thread(screen);
        thread2.start();
        
    }
    
    public Interface() {
    	this(0);
    }

}

