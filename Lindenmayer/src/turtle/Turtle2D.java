package turtle;


import java.awt.Graphics;

import turtle.space.Space;
import turtle.space.Position;


/**
 * Turtle graphics for 2D
 * @author Wester
 */
public class Turtle2D extends Turtle {

    public Turtle2D(){
        super(new double[]{0, 0});
    }


    @Override
    public void move(boolean draw, Graphics g){

        double x = _position[0];
        double y = _position[1];

        double radiantAngle = Space.degresToRadian(_angle);
        double dx = x + Math.cos(radiantAngle) * _length;
        double dy = y - Math.sin(radiantAngle) * _length;

        if (draw)
            g.drawLine((int)Math.round(x), (int)Math.round(y), (int)Math.round(dx), (int)Math.round(dy));        
        
        setPos(new double[] {dx, dy});
    }     

    @Override
    public void left(){
        _angle += _defaultAngle;
    }

    @Override
    public void right(){
        _angle -= _defaultAngle;
    }

    @Override
    public void push(){
        _stack.push(new Position(_position, _angle));
    }

    @Override
    public void pop(){
        Position p = _stack.pop();
        _position = p.getPos();
        _angle = p.getAngle();
    }

}

