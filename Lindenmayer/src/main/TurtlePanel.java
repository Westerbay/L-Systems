package main;


import javax.swing.JPanel;
import java.awt.*;

import turtle.*;
import system.SystemL;


/**
 * Zone Graphique pour les tortues
 * @author dubuiss212
 */
public class TurtlePanel extends JPanel {

	private final Color BRANCH = new Color(0, 0, 0, 100);
	
	
	/** La Tortue 3D*/
    public static final Turtle2D turtle2d = new Turtle2D();
    /** La Tortue 2D*/
    public static final Turtle3D turtle3d = new Turtle3D();
    /** La Tortue actuelle*/
    public static Turtle turtle;
    
    private SystemL actualSys;
    private int actualGen;
    private double actualLength, actualAngle;

    /**
     * Constructeur
     */
    public TurtlePanel(SystemL defaultSys, int[] defaultOption) {
        setBackground(Color.white); 
        parse(defaultSys, defaultOption);
    }
    
    /**
     * Mutateur sur length
     * @param length length
     */
    public void setLength(Double length){
    	actualLength = length;
    	repaint();
    }
    
    
    /**
     * Mutateur sur gen
     * @param gen gen
     */
    public void setGen(int gen){
    	actualGen = gen;
    	repaint();
    }	
    
    
    /**
     * Mutateur sur angle
     * @param angle angle
     */
    public void setAngle(double angle){
    	actualAngle = angle;
    	repaint();
    }
    
    /**
     * Fonction permettant d'interpréter un L-system
     * @param system L-Système
     * @param option options de configuration
     */
    public void parse(SystemL system, int[] option){
    	parse(system, option[0], (double) option[1]);
    }

    /**
     * Fonction permettant d'interpréter un L-system
     * @param system L-Système
     * @param gen la génération souhaitée
     * @param length la longueur souhaité
     */
    public void parse(SystemL system, int gen, double length){
        actualSys = system;
        actualGen = gen;
        actualLength = length;
        actualAngle = system.getAngle();
        
        if (is3D())
        	turtle = turtle3d;
        else 
            turtle = turtle2d;           
            
        repaint();
    }
    

    /**
     * Reconnaît si le système à afficher est en 3D
     * @return vrai si le système est en 3D, faux sinon
     */
    public boolean is3D(){
        return actualSys.getGeneration(1).contains("&")  ||
               actualSys.getGeneration(1).contains("/")  ||
               actualSys.getGeneration(1).contains("\\") ||
               actualSys.getGeneration(1).contains("^")  ||
               actualSys.getGeneration(1).contains("|");
    }

    /**
     * Reconnaît si le système à afficher est une plante
     * @return vrai si le système est une plante, faux sinon
     */
    public boolean isPlant(){
        return actualSys.getGeneration(actualGen).contains("[");
    }

    /**
     * Fonction permettant de centrer les fractales
     * @param g les composants graphiques
     */
    public void center(Graphics g){
    
    	g.setColor(Color.BLACK);
		
		//Centralisation 3D
        if (is3D()){
            if (isPlant())
                turtle3d.setCenterY(getHeight()/6 * 5);
            else
                turtle3d.setCenterY(getHeight()/2);
            turtle3d.setCenterX(getWidth()/2);
            
        }
        
        //Centralisation 2D
        else {
        
        	//Le but de l'algorithme est de prévisualiser l'allure du system, puis créer un losange où les 4 points 
        	//sont les extrémités en x et y (min et max), calculer le centre, et le déplacer au centre avec un vecteur v.
        	//Ainsi nous n'avons plus qu'a translater la position de départ de la tortue avec v pour tout centrer.
        	
            String shapeSystem = actualSys.getGeneration(actualGen).replaceAll("F", "f"); // L'allure du system
            double min_x = 0, max_x = 0, min_y = 0, max_y = 0;

            for(int i = 0; i<shapeSystem.length(); i++){
                apply(shapeSystem.charAt(i), g);
                double x = turtle.getPos()[0];
                double y = turtle.getPos()[1];
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

            turtle2d.reset();
            turtle2d.setPos(new double[]{vectorX, vectorY - 20});
            if (isPlant())
            	g.setColor(BRANCH);
        }

    }

    /**
     * Fonction permettant d'interpréter les caractères d'un l-système affecter à une fonction d'une tortue graphique
     * @param c le caractère
     * @param g composants graphiques
     */
    public void apply(char c, Graphics g){
        switch (c){
            case 'F':  turtle.F(g);         break;
            case 'f':  turtle.f(g);         break;
            case '+':  turtle.left();       break;
            case '-':  turtle.right();      break;
            case '[':  turtle.push();       break;
            case ']':  turtle.pop();        break;
        }
        if (turtle == turtle3d){
            switch (c){
                case '&':  turtle3d.pitchDown();        break;
                case '^':  turtle3d.pitchUp();          break;
                case '\\': turtle3d.rollLeft();         break;
                case '/':  turtle3d.rollRight();        break;
                case '|':  turtle3d.turnAround();       break;
                case '’':  turtle3d.incrementColor();   break;
                case '!':  turtle3d.decrement();        break;
                case '{':  turtle3d.startPolygon();     break;
                case '}':  turtle3d.completePolygon(g); break;
            }
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
		
		turtle.reset();
		turtle.setLength(actualLength);
        turtle.setDefaultAngle(actualAngle); 
		center(g);
        String sentence = actualSys.getGeneration(actualGen);
        for(int i = 0; i < sentence.length(); i++)
            apply(sentence.charAt(i), g);
    }
    
}

