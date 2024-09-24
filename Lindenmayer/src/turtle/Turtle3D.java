package turtle;


import java.util.ArrayList;
import java.awt.Graphics;

import turtle.space.*;


/**
 * Classe représentant une tortue graphique en 3D
 * @author dubuiss212
 */
public class Turtle3D extends Turtle {
    
    private int angleRotation = -134, centerX = 0, centerY = 0;
    private double[][] vectors;
    private boolean drawPoly = false;
    private ArrayList<int[]> polyList;

    /**
     * Constructeur
     */
    public Turtle3D(){

        super(new double[]{0, 0, 0}); // Start at (0, 0, 0)

        /*
         * Vector H -> heading of the turtle
         * Vector L -> left of the turtle
         * Vector U -> top of the turtle
         * Matrix vectors = [H L U];
         */
        vectors = new double[][] {
            new double[] {0, 0, 1},
            new double[] {-1, 0, 0},
            new double[] {0, 1, 0},            
        };

    }

    /**
     * Centre la fractale en 'x'
     * @param x coordonnée en 'x'
     */
    public void setCenterX(int x){
        centerX = x;
    }

    /**
     * Centre la fractale en 'y'
     * @param y coordonnée en 'y'
     */
    public void setCenterY(int y){
        centerY = y;
    }

    /**
     * Incrémente l'angle de rotation
     */
    public void increaseAngleRota(){
        angleRotation ++;
        if (angleRotation == 360)
            angleRotation = 0;
    }

    /**
     * Copie des vecteurs 3D
     * @param vectors vecteurs
     * @return une copie des vecteurs
     */
    public double[][] copyVectors(double[][] vectors){
        double[][] copy = new double[3][];
        copy[0] = copyPoint(vectors[0]);
        copy[1] = copyPoint(vectors[1]);
        copy[2] = copyPoint(vectors[2]);
        return copy;
    }

    /**
     * Copie un point 3D
     * @param point point
     * @return une copie du point
     */
    public double[] copyPoint(double[] point){
        double[] copy = new double[3];
        copy[0] = point[0];
        copy[1] = point[1];
        copy[2] = point[2];
        return copy;
    }

    @Override
    public void move(boolean draw, Graphics g){
        // Consider the origin

        double[] oldPosition = copyPoint(position);

        for (int i = 0; i<3; i++)
            position[i] += vectors[i][0] * d;
		
		//On ignore la coordonnée z, mais pour faire un semblant de profondeur, on applique une rotation matricielle sur notre vecteur pour faire du mouvement
        double[] oldProjected2d = Space.Rota3D(oldPosition, Space.getRotationMatrixL(angleRotation));
        oldProjected2d = Space.Rota3D(oldProjected2d, Space.getRotationMatrixH(30));
        double[] projected2d = Space.Rota3D(position, Space.getRotationMatrixL(angleRotation));
        projected2d = Space.Rota3D(projected2d, Space.getRotationMatrixH(30));
		
		//Conversion et centrage des nouvelles coordonnées 2d
        int x  = (int)(Math.round(oldProjected2d[0])) + centerX;
        int y  = (int)(Math.round(oldProjected2d[1])) + centerY;
        int dx = (int)(Math.round(projected2d[0])) + centerX;
        int dy = (int)(Math.round(projected2d[1])) + centerY;

        if (draw)
            g.drawLine(x, y, dx, dy);
        if (drawPoly)
            polyList.add(new int[] {dx, dy});

    }

    @Override
    public void reset(){
        super.reset();
        // Reset the vectors
        vectors = new double[][] {
            new double[] {0, 0, 1},
            new double[] {-1, 0, 0},
            new double[] {0, 1, 0}, 
        };
        polyList = new ArrayList<>();
    }

    @Override
    public void left(){
        vectors = Space.Rota3D(vectors, Space.getRotationMatrixU(-defaultAngle));
    }

    @Override
    public void right(){
        vectors = Space.Rota3D(vectors, Space.getRotationMatrixU(+defaultAngle));
    }

    @Override
    public void push(){
        double[] pos = copyPoint(position);
        double[][] vect = copyVectors(vectors);
        pile.empile(new Position(pos, angle, vect, d));
    }

    @Override
    public void pop() {
        Position p = pile.depile();
        position = p.getPos();
        angle = p.getAngle();
        vectors = p.getVectors();
        d = p.getLength();
    }

    /**
     * PitchDown
     */
    public void pitchDown(){
        vectors = Space.Rota3D(vectors, Space.getRotationMatrixL(defaultAngle));
    }

    /**
     * PitchUp
     */
    public void pitchUp(){
        vectors = Space.Rota3D(vectors, Space.getRotationMatrixL(-defaultAngle));
    }

    /**
     * RollLeft
     */
    public void rollLeft(){
        vectors = Space.Rota3D(vectors, Space.getRotationMatrixH(defaultAngle));
    }

    /**
     * RollRight
     */
    public void rollRight(){
        vectors = Space.Rota3D(vectors, Space.getRotationMatrixH(-defaultAngle));
    }

    /**
     * TurnAround
     */
    public void turnAround(){
        vectors = Space.Rota3D(vectors, Space.getRotationMatrixU(180));
    }

    /**
     * Decremente length
     */
    public void decrement(){
        d /= 2;
    }

    /**
     * Pas encore implémenté, cela dépend des couleurs par défauts que l'on choisit
     */
    public void incrementColor() {}

    /**
     * On annonce que l'on veut dessiner un polygone
     */
    public void startPolygon(){
        drawPoly = true;
    }

    /**
     * Dessine un polygone
     * @param g graphics component
     */
    public void completePolygon(Graphics g){

        drawPoly = false;

        int size = polyList.size();
        int[] allX = new int[size];
        int[] allY = new int[size];

        for (int i = 0; i<size; i++){
            allX[i] = polyList.get(i)[0];
            allY[i] = polyList.get(i)[1];
        }
        g.fillPolygon(allX, allY, size);

        polyList.clear();
    }

}
