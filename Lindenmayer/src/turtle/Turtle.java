package turtle;


import java.awt.Graphics;
import java.util.Arrays;

import turtle.space.*;


/**
 * Abstract Turtle Graphics
 * @author Wester
 */
public abstract class Turtle {

	private static final double DEFAULT_START_ANGLE = 90;
	private static final double DEFAULT_LENGTH = 10;

    protected double _length = DEFAULT_LENGTH; 
    protected double _angle;
    protected double _defaultAngle;    
    protected double[] _position;  
    protected Stack<Position> _stack = new Stack<>();
    
    public Turtle(double[] position){
        _position = position;        
    }    
   
    public void setLength(double length){
        _length = length;
    }

    public double[] getPos(){
        return _position;
    }

    public void setPos(double[] position){
        _position = position;
    }

    public void setDefaultAngle(double angle){
        _defaultAngle = angle;
    }
    
    public void reset(){
        Arrays.fill(_position, 0);
        _angle = DEFAULT_START_ANGLE;
    }

	/**
	 * Forward fonction (Move and draw)
	 */
    public void F(Graphics g){
        move(true, g);
    }
	
	/**
	 * Forward fonction (Move but do not draw)
	 */
    public void f(Graphics g){
        move(false, g);
    }  

    public abstract void move(boolean draw, Graphics g);

    public abstract void left();

    public abstract void right();

    public abstract void push();    

    public abstract void pop();

}

