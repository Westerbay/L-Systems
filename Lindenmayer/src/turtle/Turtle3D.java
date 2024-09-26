package turtle;


import java.util.ArrayList;
import java.awt.Graphics;

import turtle.space.*;


/**
 * Turtle graphics for 3D
 * @author Wester
 */
public class Turtle3D extends Turtle {
    
    private int _angleRotation = -134, _centerX = 0, _centerY = 0;
    private double[][] _vectors;
    private boolean _drawPoly = false;
    private ArrayList<int[]> _polyList;

    public Turtle3D(){

        super(new double[]{0, 0, 0}); // Start at (0, 0, 0)

        /*
         * Vector H -> heading of the turtle
         * Vector L -> left of the turtle
         * Vector U -> top of the turtle
         * Matrix vectors = [H L U];
         */
        _vectors = new double[][] {
            new double[] {0, 0, 1},
            new double[] {-1, 0, 0},
            new double[] {0, 1, 0},            
        };

    }

    public void setCenterX(int x){
        _centerX = x;
    }

    public void setCenterY(int y){
        _centerY = y;
    }

    public void increaseAngleRota(){
        _angleRotation ++;
        if (_angleRotation == 360)
            _angleRotation = 0;
    }

    public double[][] copyVectors(double[][] vectors){
        double[][] copy = new double[3][];
        copy[0] = copyPoint(vectors[0]);
        copy[1] = copyPoint(vectors[1]);
        copy[2] = copyPoint(vectors[2]);
        return copy;
    }

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
        double[] oldPosition = copyPoint(_position);

        for (int i = 0; i<3; i++)
            _position[i] += _vectors[i][0] * _length;
		
		// Ignore Z axis to project in a 2D plan (the screen) and apply a constant rotation
        double[] oldProjected2d = Space.Rota3D(oldPosition, Space.getRotationMatrixL(_angleRotation));
        oldProjected2d = Space.Rota3D(oldProjected2d, Space.getRotationMatrixH(30));
        double[] projected2d = Space.Rota3D(_position, Space.getRotationMatrixL(_angleRotation));
        projected2d = Space.Rota3D(projected2d, Space.getRotationMatrixH(30));
		
		// Center new 2D points
        int x  = (int)(Math.round(oldProjected2d[0])) + _centerX;
        int y  = (int)(Math.round(oldProjected2d[1])) + _centerY;
        int dx = (int)(Math.round(projected2d[0])) + _centerX;
        int dy = (int)(Math.round(projected2d[1])) + _centerY;

        if (draw)
            g.drawLine(x, y, dx, dy);
        if (_drawPoly)
            _polyList.add(new int[] {dx, dy});

    }

    @Override
    public void reset(){
        super.reset();
        // Reset the vectors
        _vectors = new double[][] {
            new double[] {0, 0, 1},
            new double[] {-1, 0, 0},
            new double[] {0, 1, 0}, 
        };
        _polyList = new ArrayList<>();
    }

    @Override
    public void left(){
        _vectors = Space.Rota3D(_vectors, Space.getRotationMatrixU(-_defaultAngle));
    }

    @Override
    public void right(){
        _vectors = Space.Rota3D(_vectors, Space.getRotationMatrixU(_defaultAngle));
    }

    @Override
    public void push(){
        double[] pos = copyPoint(_position);
        double[][] vect = copyVectors(_vectors);
        _stack.push(new Position(pos, _angle, vect, _length));
    }

    @Override
    public void pop() {
        Position p = _stack.pop();
        _position = p.getPos();
        _angle = p.getAngle();
        _vectors = p.getVectors();
        _length = p.getLength();
    }

    public void pitchDown(){
        _vectors = Space.Rota3D(_vectors, Space.getRotationMatrixL(_defaultAngle));
    }
    
    public void pitchUp(){
        _vectors = Space.Rota3D(_vectors, Space.getRotationMatrixL(-_defaultAngle));
    }

    public void rollLeft(){
        _vectors = Space.Rota3D(_vectors, Space.getRotationMatrixH(_defaultAngle));
    }

    public void rollRight(){
        _vectors = Space.Rota3D(_vectors, Space.getRotationMatrixH(-_defaultAngle));
    }

    public void turnAround(){
        _vectors = Space.Rota3D(_vectors, Space.getRotationMatrixU(180));
    }

    public void decrement(){
        _length /= 2;
    }

    public void incrementColor() {}

    public void startPolygon(){
        _drawPoly = true;
    }

    public void completePolygon(Graphics g){

        _drawPoly = false;

        int size = _polyList.size();
        int[] allX = new int[size];
        int[] allY = new int[size];

        for (int i = 0; i<size; i++){
            allX[i] = _polyList.get(i)[0];
            allY[i] = _polyList.get(i)[1];
        }
        g.fillPolygon(allX, allY, size);

        _polyList.clear();
    }

}

