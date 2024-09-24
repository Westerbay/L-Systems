package turtle;


import java.awt.Graphics;
import java.util.Arrays;

import turtle.space.*;


/**
 * Classe abstraite représentant une tortue graphique
 * @author dubuiss212
 */
public abstract class Turtle {

    protected double d = 10;
    
    protected double[] position; 
    protected double angle, defaultAngle;  
    protected Pile<Position> pile = new Pile<>();
    
    /**
     * Mutateur sur length
     * @param length length
     */
    public void setLength(double length){
        d = length;
    }

    /**
     * Constructeur
     * @param position la position initiale
     */
    public Turtle(double[] position){
        this.position = position;        
    }


    /**
     * Accesseur
     * @return la position
     */
    public double[] getPos(){
        return position;
    }


    /**
     * Initialise la position de la tortue
     * @param position la position
     */
    public void setPos(double[] position){
        this.position = position;
    }

    /**
     * Mutateur sur defaultAngle
     * @param angle l'angle par défaut
     */
    public void setDefaultAngle(double angle){
        defaultAngle = angle;
    }
    

    /**
     * Reset de la tortue
     */
    public void reset(){
        Arrays.fill(position, 0);
        angle = 90;
    }

    /**
     * Avance en dessinant
     * @param g les composant graphiques
     */
    public void F(Graphics g){
        move(true, g);
    }

    /**
     * Avance sans dessiner
     * @param g les composant graphiques
     */
    public void f(Graphics g){
        move(false, g);
    }  

    /**
     * Permet le déplacement d'une distance d
     * @param draw doit-on dessiner lors du trajet ?
     * @param g les composants graphiques
     */
    public abstract void move(boolean draw, Graphics g);

    /**
     * Tourne à gauche
     */
    public abstract void left();

    /**
     * Tourne à droite
     */
    public abstract void right();

    /**
     * Sauvegarde l'emplacement de la tortue
     */
    public abstract void push();    

    /**
     * Déplace la tortue à l'ancien emplacement sauvegardé
     */
    public abstract void pop();

}
