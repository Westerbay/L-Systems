package main;


import javax.swing.*;


/**
 * Surface où tout sera affiché
 * @author dubuiss212
 */
public class MainFrame extends JFrame implements Runnable {


    private final int OPTION_WIDTH = 350, OPTION_HEIGHT = 800;    
    private int width = 1150, height = 800;
    private TurtlePanel turtlePanel;
    private ConfigurationPanel confPanel;
    

    /**
     * Constructeur
     * @param turtlePanel Zone graphique de la tortue
     * @param confPanel Zone graphique des configurations
     */
    public MainFrame(TurtlePanel turtlePanel, ConfigurationPanel confPanel){
    
    	this.turtlePanel = turtlePanel;
    	this.confPanel = confPanel;

        setTitle("L-Systems");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(width, height);

        add(confPanel);
        add(turtlePanel);
		
        setSize();

        setLocationRelativeTo(null);
        setVisible(true);     

    }
    
    /**
     * Boucle parallèle permettant la centralisation des éléments en fonction de la taille de la fenêtre, et permet la rotation de la tortue 3D
     */
    @Override
    public void run(){    
    	while (true) {
    		sleep(15);
    		
    		//Rotation de la tortue 3D
    		boolean haveToRepaint = false;
    		if (TurtlePanel.turtle == TurtlePanel.turtle3d){
		        TurtlePanel.turtle3d.increaseAngleRota();
		        haveToRepaint = true;
		    }
		    
		    //Responsive
		    if (getWidth() != width || getHeight() != height){
		    	if (getHeight() < OPTION_HEIGHT - 50){
		    		turtlePanel.setBounds(0, 0, getWidth(), getHeight());
		    		confPanel.setBounds(0, 0, 0, 0);
				} else {
					width = getWidth();
					height = getHeight();
					setSize();
				}
		    	haveToRepaint = true;
		    }
		    
		    if (haveToRepaint) { repaint(); }
		    		    
    	}    	
    }
    
    /**
     * Centre les éléments graphiques
     */
    public void setSize(){
    	confPanel.setBounds(0, (height-OPTION_HEIGHT)/2, OPTION_WIDTH, OPTION_HEIGHT);
        turtlePanel.setBounds(OPTION_WIDTH, 0, width - OPTION_WIDTH, height);
    }
    
    /**
     * Fonction permettant de ralentir le processus, utilisé pour une rotation lente d'un système 3D
     * @param ms temps en millisecondes
     */
    public void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {}
    }

}

