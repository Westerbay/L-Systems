package main;


import javax.swing.*;


/**
 * The Main Frame
 * @author Wester
 */
public class MainFrame extends JFrame implements Runnable {


    private final int OPTION_WIDTH = 350, OPTION_HEIGHT = 800;    
    
    private int _width = 1150, _height = 800;
    private TurtlePanel _turtlePanel;
    private ConfigurationPanel _confPanel;
    
    public MainFrame(TurtlePanel turtlePanel, ConfigurationPanel confPanel){
    
    	_turtlePanel = turtlePanel;
    	_confPanel = confPanel;

        setTitle("L-Systems");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(_width, _height);

        add(confPanel);
        add(turtlePanel);
		
        setSize();

        setLocationRelativeTo(null);
        setVisible(true);     

    }
    
    @Override
    public void run(){    
    	while (true) {
    		sleep(15);
    		
    		//3D Rotation of turtle
    		boolean haveToRepaint = false;
    		if (TurtlePanel._turtle == TurtlePanel._turtle3d){
		        TurtlePanel._turtle3d.increaseAngleRota();
		        haveToRepaint = true;
		    }
		    
		    //Responsive
		    if (getWidth() != _width || getHeight() != _height){
		    	if (getHeight() < OPTION_HEIGHT - 50){
		    		_turtlePanel.setBounds(0, 0, getWidth(), getHeight());
		    		_confPanel.setBounds(0, 0, 0, 0);
				} else {
					_width = getWidth();
					_height = getHeight();
					setSize();
				}
		    	haveToRepaint = true;
		    }
		    
		    if (haveToRepaint) { repaint(); }
		    		    
    	}    	
    }
    

    public void setSize(){
    	_confPanel.setBounds(0, (_height-OPTION_HEIGHT)/2, OPTION_WIDTH, OPTION_HEIGHT);
        _turtlePanel.setBounds(OPTION_WIDTH, 0, _width - OPTION_WIDTH, _height);
    }
    

    public void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {}
    }

}

