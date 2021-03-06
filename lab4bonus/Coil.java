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

    public double current;
    public int turns;
    public int pPerLoop = 16;
    public double length;
    public double radius;
    public Vec position;
    public boolean cw;
    public Vec[] points;
    public Vec[] wires;
    public Vec[] wirepoints;

    public Coil (double I, int N, double l, double r, Vec pos, double theta, double phi, boolean clock) {

        current = I;
        turns = N;
        length = l;
        radius = r;
        position = pos;
        cw = clock;
  
        points = new Vec[pPerLoop * turns ]; // Create pPerLoop points along each turn
        double dheight = length / points.length; // Set the change in height as the length divided by the number of points
        double dtheta = 2*Math.PI / pPerLoop; // Set the angle increment to be 1/pPerLoop revs, to allow for easy changing of accuracy of sim.
        for (int i = 0; i < points.length; i++) {
            double height = dheight * i;
            double arg = dtheta * i;
            points[i] = new Vec(radius*Math.cos(arg), radius*Math.sin(arg), height); // Calculate the point on the helix
        }
        
        // Generate wires
        wires = new Vec[points.length - 1];
        wirepoints = new Vec[points.length - 1];
        for (int i = 0; i < points.length - 1; i++) {
            // Create a wire element by subtracting consecutive coil points
            wires[i] = Vec.subtract(points[i+1], points[i]);
            // Note the location of the midpoint of the wire
            wirepoints[i] = Vec.scale(0.5, Vec.add(points[i], points[i+1]));
        }
        
        // Rotate the coil by two rotations (any orientation in R3)
        rotate(theta, phi);
        // Translate the entire coil
        translate(pos);
    }
    
    public double getcurrent () {return current;}
    public int getppc() {return wires.length;}
    
    public Vec getwire (int i) { return wires[i]; }
    public Vec getwirepoint (int i) {return wirepoints[i]; }

    private void rotate (double theta, double phi) {
        for (int i = 0; i < wires.length; i++) {
            wires[i].rotate(theta, phi);
            wirepoints[i].rotate(theta,phi);
        }
    }
    
    private void translate (Vec pos) { // translate all points
        for (int i = 0; i < wires.length; i++) {
            wirepoints[i] = Vec.add(wirepoints[i], pos);
        }
    }

}
