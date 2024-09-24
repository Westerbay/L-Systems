package main;


import system.Fractals;


/**
 * Classe d'instanciation des éléments graphiques
 */
public class Interface {

	/** Le numéro correspondant à une fractale par défaut lors du lancement */
	public static int INDEX;


	/**
	 * Constructeur
	 */
    public Interface(int index) {
        
        index %= Fractals.systemPresets.length;
        INDEX = index;
        
        TurtlePanel turtlePanel = new TurtlePanel(Fractals.systemPresets[index], Fractals.optionPresets[index]);
        MainFrame screen = new MainFrame(turtlePanel, new ConfigurationPanel(turtlePanel));
        
        Thread thread2 = new Thread(screen);
        thread2.start();
        
    }
    
    /**
	 * Constructeur par défaut
	 */
    public Interface() {
    	this(0);
    }

}

