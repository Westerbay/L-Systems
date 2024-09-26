package turtle.space;


/**
 * All geometric tools needed
 * @author Wester
 */
public class Space {

    public static double degresToRadian(double angle){
        return angle * Math.PI / 180;
    }
    
    public static double[][] getRotationMatrixU(double angle){
        angle = degresToRadian(angle);
        return new double[][]{
            new double[] {Math.cos(angle), Math.sin(angle),  0},
            new double[] {-Math.sin(angle), Math.cos(angle), 0},
            new double[] {      0,              0,           1}
        };
    }

    public static double[][] getRotationMatrixL(double angle){
        angle = degresToRadian(angle);
        return new double[][]{
            new double[] {Math.cos(angle), 0, -Math.sin(angle)},
            new double[] {     0,          1,       0         },
            new double[] {Math.sin(angle), 0,  Math.cos(angle)}
        };
    }

    public static double[][] getRotationMatrixH(double angle){
        angle = degresToRadian(angle);
        return new double[][]{
            new double[] {1,      0,                0         },
            new double[] {0, Math.cos(angle), -Math.sin(angle)},
            new double[] {0, Math.sin(angle),  Math.cos(angle)}
        };
    }

    public static double[] Rota3D(double[] point, double[][] matrice){
        double[] coord = new double[3];
        coord[0] = point[0] * matrice[0][0] + point[1] * matrice[1][0] + point[2] * matrice[2][0];
        coord[1] = point[0] * matrice[0][1] + point[1] * matrice[1][1] + point[2] * matrice[2][1];
        coord[2] = point[0] * matrice[0][2] + point[1] * matrice[1][2] + point[2] * matrice[2][2];
        return coord;
    }
    
    public static double[][] Rota3D(double[][] vectors, double[][] matrice){
        double[][] newVectors = new double[3][3];
        newVectors[0] = Rota3D(vectors[0], matrice);
        newVectors[1] = Rota3D(vectors[1], matrice);
        newVectors[2] = Rota3D(vectors[2], matrice);
        return newVectors;
    }

}

