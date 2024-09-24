package turtle.space;


/**
 * Classe représentant une Position graphique
 * @author leriche213
 * @author peltier212
 * @author dubuiss212
 */
public class Position{

    private final double[] position;
    private final double angle;
    private double[][] vectors;
    private double length;


    /**
     * Constructeur Position 2D
     * @param position coordonnées
     * @param angle direction
     */
    public Position(double[] position, double angle){
        this.position = position;
        this.angle = angle;
    }

    /**
     * Constructeur Position 3D
     * @param position coordonnées
     * @param angle direction
     * @param vectors vecteurs
     * @param length taille
     */
    public Position(double[] position, double angle, double[][] vectors, double length){
        this(position, angle);
        this.vectors = vectors;
        this.length = length;
    }

    /**
     * Accesseur Position
     * @return la position
     */
    public double[] getPos(){
        return position;
    }

    /**
     * Accesseur angle
     * @return angle
     */
    public double getAngle(){
        return angle;
    }

    /**
     * Accesseur vectors
     * @return vectors
     */
    public double[][] getVectors(){
        return vectors;
    }


    /**
     * Accesseur length
     * @return length
     */
    public double getLength(){
        return length;
    }

}
