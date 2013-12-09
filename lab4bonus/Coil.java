/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab4bonus;

/**
 *
 * @author User
 */
public class Coil {

    public int turns;
    public double length;
    public double radius;
    public Vec position;
    public boolean cw;
    public Vec[] points;
    public Vec[] wires;

    public Coil (int n, double l, double r, Vec pos, boolean clock, double theta, double phi) {

        turns = n;
        length = l;
        radius = r;
        position = pos;
        cw = clock;
  
        points = new Vec[16 * n ];
        double dtheta = length / points.length;
        for (int i = 0; i < points.length; i++) {
            double theta = dtheta * i;
            points[i] = new Vec(Math.cos(theta), Math.sin(theta), theta);
        }

        rotate(theta, phi);
        
        wires = new Vec[points.length - 1];
        for (int i = 0; i < points.length - 1; i++) {
            wires[i] = Vec.subtract(points[i+1], points[i]);
        }
    }

    private void rotate (double theta, double phi) {
        // prefetch the existing vector
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        // apply x rotation matrix
        position.setY(y*Math.cos(theta) - z*Math.sin(theta));
        position.setZ(y*Math.sin(theta) + z*Math.cos(theta));
        
        // prefetch the new vector
        x = position.getX();
        y = position.getY();
        z = position.getZ();
        // apply y rotation matrix
        position.setX(x*Math.cos(theta)+z*Math.sin(theta));
        position.setZ(-x*Math.sin(theta)+z*Math.cos(theta));
    }

}
