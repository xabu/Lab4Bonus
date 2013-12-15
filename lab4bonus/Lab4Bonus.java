/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab4bonus;

/**
 *
 * @author Stephen Wen
 */
public class Lab4Bonus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Parameters for first coil
        double i1 = 3;
        int N1 = 300;
        double l1 = 0.05;
        double r1 = 0.10;
        Vec pos1 = new Vec();
        double theta1 = 0;
        double phi1 = 0;
        boolean clock1 = true;
        
        // Create first coil
        Coil coil1 = new Coil (i1, N1, l1, r1, pos1, theta1, phi1, clock1);
        
        // Parameters for second coil
        double i2 = 3;
        int N2 = 300;
        double l2 = 0.05;
        double r2 = 0.10;
        Vec pos2 = new Vec(0, 0, 0.3);
        double theta2 = 0;
        double phi2 = 0;
        boolean clock2 = true;
        
        // Create second coil
        Coil coil2 = new Coil (i2, N2, l2, r2, pos2, theta2, phi2, clock2);
        
        
    }
    
    public Vec[][][] field (Vec[][][] points, int ppc, Coil coil1, Coil coil2) {
        Vec[][][] field = new Vec[points.length][points[0].length][points[0][0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int k = 0; k < field[0][0].length; k++) {
                    field[i][j][k] = pointField(points[i][j][k], ppc, coil1, coil2);
                }
            }
        }
        return field;
    }
    
    public Vec pointField(Vec point, int ppc, Coil coil1, Coil coil2) {
        Vec field = new Vec ();
        double current1 = coil1.getcurrent();
        double current2 = coil2.getcurrent();
        for (int i = 0; i < ppc; i++) {
            field = Vec.add(field, diffField(point, coil1.getwire(i), coil1.getwirepoint(i), current1));
            field = Vec.add(field, diffField(point, coil2.getwire(i), coil2.getwirepoint(i), current2));
        }
        return field;
    }
    
    public Vec diffField(Vec point, Vec wire, Vec wirepoint, double current) {
        Vec r = Vec.subtract(point, wire);
        double distance = r.norm();
        double scalar = Math.pow (10,-7) / Math.pow(distance, 3);
        return Vec.scale(scalar, Vec.cross(wire, r));
    }
    
}