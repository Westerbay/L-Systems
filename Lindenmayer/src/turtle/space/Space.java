package turtle.space;


/**
 * Classe de gestions de rotations dans un plan
 * @author dubuiss212
 */
public class Space {


    /**
     * Convertit les degrés en radians
     * @param angle l'angle en degrés
     * @return l'angle en radiant
     */
    public static double degresToRadian(double angle){
        return angle * Math.PI / 180;
    }


    /**
     * Renvoie la matrice de rotation sur z
     * @param angle l'angle de rotation en degrés
     * @return la matrice de rotation sur Z
     */
    public static double[][] getRotationMatrixU(double angle){
        angle = degresToRadian(angle);
        return new double[][]{
            new double[] {Math.cos(angle), Math.sin(angle),  0},
            new double[] {-Math.sin(angle), Math.cos(angle), 0},
            new double[] {      0,              0,           1}
        };
    }


    /**
     * Renvoie la matrice de rotation sur 'y'
     * @param angle l'angle de rotation en degrés
     * @return la matrice de rotation sur Y
     */
    public static double[][] getRotationMatrixL(double angle){
        angle = degresToRadian(angle);
        return new double[][]{
            new double[] {Math.cos(angle), 0, -Math.sin(angle)},
            new double[] {     0,          1,       0         },
            new double[] {Math.sin(angle), 0,  Math.cos(angle)}
        };
    }


    /**
     * Renvoie la matrice de rotation sur x
     * @param angle l'angle de rotation en degrés
     * @return la matrice de rotation sur X
     */
    public static double[][] getRotationMatrixH(double angle){
        angle = degresToRadian(angle);
        return new double[][]{
            new double[] {1,      0,                0         },
            new double[] {0, Math.cos(angle), -Math.sin(angle)},
            new double[] {0, Math.sin(angle),  Math.cos(angle)}
        };
    }


    /**
     * Fait le produit matriciel de 2 matrices, une 1x3 et l'autre 3x3
     * @param point un point en 3D
     * @param matrice la matrice de rotation
     * @return le nouveau point
     */
    public static double[] Rota3D(double[] point, double[][] matrice){
        double[] coord = new double[3];
        coord[0] = point[0] * matrice[0][0] + point[1] * matrice[1][0] + point[2] * matrice[2][0];
        coord[1] = point[0] * matrice[0][1] + point[1] * matrice[1][1] + point[2] * matrice[2][1];
        coord[2] = point[0] * matrice[0][2] + point[1] * matrice[1][2] + point[2] * matrice[2][2];
        return coord;
    }


    /**
     * Fait le produit matriciel de 2 matrices 3x3
     * @param vectors matrice de vecteurs
     * @param matrice la matrice de rotation
     * @return les nouveaux vecteurs
     */
    public static double[][] Rota3D(double[][] vectors, double[][] matrice){
        double[][] newVectors = new double[3][3];
        newVectors[0] = Rota3D(vectors[0], matrice);
        newVectors[1] = Rota3D(vectors[1], matrice);
        newVectors[2] = Rota3D(vectors[2], matrice);
        return newVectors;
    }

}
