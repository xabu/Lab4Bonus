/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab4bonus;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Stephen Wen
 */
public class Lab4Bonus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // set up cube length and lattice density
        double length = 0.50;
        int density = 20;
        double side = length / density;
        
        // create an array of the points
        Vec points[][][] = new Vec[density][density][density];
        for (int i = 0; i < density; i++) {
            for (int j = 0; j < density; j++) {
                for (int k = 0; k < density; k++) {
                    points[i][j][k] = new Vec (i*side, j*side, k*side);
                }
            }
        }
        
        // Parameters for first coil
        double i1 = 3;
        int N1 = 320;
        double l1 = 0.0214;
        double r1 = 0.076;
        Vec pos1 = new Vec(0.25, 0.25, 0.15);
        double theta1 = 0;
        double phi1 = 0;
        boolean clock1 = true;
        
        // Create first coil
        Coil coil1 = new Coil (i1, N1, l1, r1, pos1, theta1, phi1, clock1);
        
        // Parameters for second coil
        double i2 = 3;
        int N2 = 320;
        double l2 = 0.0214;
        double r2 = 0.076;
        Vec pos2 = new Vec(0.25, 0.25, 0.35);
        double theta2 = 0;
        double phi2 = 0;
        boolean clock2 = true;
        
        // Create second coil
        Coil coil2 = new Coil (i2, N2, l2, r2, pos2, theta2, phi2, clock2);
        
        //Vec field = pointField(new Vec(1, 0, 0.5), coil1, coil2);
        //field.print();
        
        // Compute field
        Vec[][][] field = field(points, coil1, coil2);
        
        // Output to CSV
        output (field, points);
        
        // Sucess message
        System.out.println("TADA");
    }
    
    public static Vec[][][] field (Vec[][][] points, Coil coil1, Coil coil2) {
        // Create empty field
        Vec[][][] field = new Vec[points.length][points[0].length][points[0][0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int k = 0; k < field[0][0].length; k++) {
                    // Compute the field at the point
                    field[i][j][k] = pointField(points[i][j][k], coil1, coil2);
                }
            }
        }
        return field;
    }
    
    public static Vec pointField(Vec point, Coil coil1, Coil coil2) {
        Vec field = new Vec (); // Initialize to 0 B field
        double current1 = coil1.getcurrent(); // Fetch the currents
        double current2 = coil2.getcurrent();
        for (int i = 0; i < coil1.getppc(); i++) {
            // Add the field due to each differential wire element
            field = Vec.add(field, diffField(point, coil1.getwire(i), coil1.getwirepoint(i), current1));
        }
        for (int i = 0; i < coil2.getppc(); i++) {
            // Add the field due to each differential wire element
            // field = Vec.add(field, diffField(point, coil2.getwire(i), coil2.getwirepoint(i), current2));
        }
        return field;
    }
    
    public static Vec diffField(Vec point, Vec wire, Vec wirepoint, double current) {
        Vec r = Vec.subtract(point, wirepoint); // Find the displacement between the point and the wire element
        double distance = r.norm(); // Find the distance
        double scalar = Math.pow (10,-7) / Math.pow(distance, 3); // Find the coefficient
        return Vec.scale(scalar, Vec.cross(wire, r)); // Scale the cross product by the coefficient
    }
    
    public static void output (Vec[][][] field, Vec[][][] points) {
        try {
        // write to Buyers.txt, appending to the end of the file if it exists, creating the file if it does not
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("onaxis.csv", true)));
        
        out.println("x,y,z,Bx,By,Bz");
        
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int k = 0; k < field[0][0].length; k++) {
                    Vec vector = field[i][j][k];
                    Vec point = points[i][j][k];
                    out.print(point.getX());
                    out.print(',');
                    out.print(point.getY());
                    out.print(',');
                    out.print(point.getZ());
                    out.print(',');
                    out.print(vector.getX());
                    out.print(',');
                    out.print(vector.getY());
                    out.print(',');
                    out.print(vector.getZ());
                    out.print('\n');
                }
            }
        }
        // close the printwriter
        out.close();
        }
        catch (IOException e) {
            System.out.println("The output to file failed");
        }
    }

}