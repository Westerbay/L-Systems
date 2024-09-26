package turtle.space;


/**
 * Lindenmayer Position
 * @author Wester
 */
public class Position{

    private final double[] _position;
    private final double _angle;
    private double[][] _vectors;
    private double _length;

    public Position(double[] position, double angle){
        _position = position;
        _angle = angle;
    }

    public Position(double[] position, double angle, double[][] vectors, double length){
        this(position, angle);
        _vectors = vectors;
        _length = length;
    }

    public double[] getPos(){
        return _position;
    }

    public double getAngle(){
        return _angle;
    }

    public double[][] getVectors(){
        return _vectors;
    }

    public double getLength(){
        return _length;
    }

}
