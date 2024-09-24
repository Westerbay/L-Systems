package turtle;


import java.awt.Graphics;

import turtle.space.Space;
import turtle.space.Position;


/**
 * Classe représentant une tortue graphique en 2D
 * @author dubuiss212
 */
public class Turtle2D extends Turtle {

    /**
     * Constructeur
     */
    public Turtle2D(){
        
        super(new double[]{0, 0});

    }


    @Override
    public void move(boolean draw, Graphics g){

        double x = position[0];
        double y = position[1];

        double radiantAngle = Space.degresToRadian(angle);
        double dx = x + Math.cos(radiantAngle) * d;
        double dy = y - Math.sin(radiantAngle) * d;

        if (draw)
            g.drawLine((int)Math.round(x), (int)Math.round(y), (int)Math.round(dx), (int)Math.round(dy));        
        
        setPos(new double[] {dx, dy});
    }     

    @Override
    public void left(){
        angle += defaultAngle;
    }

    @Override
    public void right(){
        angle -= defaultAngle;
    }

    @Override
    public void push(){
        pile.empile(new Position(position, angle));
    }

    @Override
    public void pop(){
        Position p = pile.depile();
        position = p.getPos();
        angle = p.getAngle();
    }

}

