package main;


import javax.swing.JPanel;
import java.awt.*;

import turtle.*;
import system.SystemL;


/**
 * Graphic panel for Turtle
 * @author Wester
 */
public class TurtlePanel extends JPanel {

	private final Color BRANCH = new Color(0, 0, 0, 100);
	
    public static final Turtle2D _turtle2d = new Turtle2D();
    public static final Turtle3D _turtle3d = new Turtle3D();
    public static Turtle _turtle;
    
    private SystemL _actualSys;
    private int _actualGen;
    private double _actualLength, _actualAngle;

    public TurtlePanel(SystemL defaultSys, int[] defaultOption) {
        setBackground(Color.white); 
        interprete(defaultSys, defaultOption);
    }
    
    public void setLength(Double length){
    	_actualLength = length;
    	repaint();
    }
    
    public void setGen(int gen){
    	_actualGen = gen;
    	repaint();
    }	
    
    public void setAngle(double angle){
    	_actualAngle = angle;
    	repaint();
    }
    
    public void interprete(SystemL system, int[] option){
    	interprete(system, option[0], (double) option[1]);
    }

    public void interprete(SystemL system, int gen, double length){
        _actualSys = system;
        _actualGen = gen;
        _actualLength = length;
        _actualAngle = system.getAngle();
        
        if (is3D())
        	_turtle = _turtle3d;
        else 
            _turtle = _turtle2d;           
            
        repaint();
    }
    
    public boolean is3D(){
        return _actualSys.getGeneration(1).contains("&")  ||
               _actualSys.getGeneration(1).contains("/")  ||
               _actualSys.getGeneration(1).contains("\\") ||
               _actualSys.getGeneration(1).contains("^")  ||
               _actualSys.getGeneration(1).contains("|");
    }

    public boolean isPlant(){
        return _actualSys.getGeneration(_actualGen).contains("[");
    }

    public void center(Graphics g){
    
    	g.setColor(Color.BLACK);
		
		// Center 3D
        if (is3D()){
            if (isPlant())
                _turtle3d.setCenterY(getHeight()/6 * 5);
            else
                _turtle3d.setCenterY(getHeight()/2);
            _turtle3d.setCenterX(getWidth()/2);
            
        }
        
        // Center 2D
        else {
        
        	// Computes polygon center, and translate all Points by the difference between polygon center and the center of the Surface
        	
            String shapeSystem = _actualSys.getGeneration(_actualGen).replaceAll("F", "f");
            double min_x = 0, max_x = 0, min_y = 0, max_y = 0;

            for(int i = 0; i<shapeSystem.length(); i++){
                apply(shapeSystem.charAt(i), g);
                double x = _turtle.getPos()[0];
                double y = _turtle.getPos()[1];
                if (x < min_x)
                    min_x = x;
                if (x > max_x)
                    max_x = x;
                if (y < min_y)
                    min_y = y;
                if (y > max_y)
                    max_y = y;
            }

            double x_center = (min_x + max_x) / 2;
            double y_center = (min_y + max_y) / 2;
            double vectorX = getWidth()/2.0 - x_center;
            double vectorY = getHeight()/2.0 - y_center;

            _turtle2d.reset();
            _turtle2d.setPos(new double[]{vectorX, vectorY - 20});
            if (isPlant())
            	g.setColor(BRANCH);
        }

    }

    public void apply(char c, Graphics g){
        switch (c){
            case 'F':  _turtle.F(g);         break;
            case 'f':  _turtle.f(g);         break;
            case '+':  _turtle.left();       break;
            case '-':  _turtle.right();      break;
            case '[':  _turtle.push();       break;
            case ']':  _turtle.pop();        break;
        }
        if (_turtle == _turtle3d){
            switch (c){
                case '&':  _turtle3d.pitchDown();        break;
                case '^':  _turtle3d.pitchUp();          break;
                case '\\': _turtle3d.rollLeft();         break;
                case '/':  _turtle3d.rollRight();        break;
                case '|':  _turtle3d.turnAround();       break;
                case '’':  _turtle3d.incrementColor();   break;
                case '!':  _turtle3d.decrement();        break;
                case '{':  _turtle3d.startPolygon();     break;
                case '}':  _turtle3d.completePolygon(g); break;
            }
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
		
		_turtle.reset();
		_turtle.setLength(_actualLength);
        _turtle.setDefaultAngle(_actualAngle); 
		center(g);
        String sentence = _actualSys.getGeneration(_actualGen);
        for(int i = 0; i < sentence.length(); i++)
            apply(sentence.charAt(i), g);
    }
    
}

