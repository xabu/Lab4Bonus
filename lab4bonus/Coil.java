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
    public Vec[] wirepoints;

    public Coil (int N, double l, double r, Vec pos, double theta, double phi, boolean clock) {

        turns = N;
        length = l;
        radius = r;
        position = pos;
        cw = clock;
  
        points = new Vec[16 * turns ]; // Create 16 points along each turn
        double dheight = length / points.length; // Set the change in height as the length divided by the number of points
        double dtheta = 2*Math.PI*16; // Set the angle incremen tto be 1/16th of a full turn
        for (int i = 0; i < points.length; i++) {
            double height = dheight * i;
            double arg = dtheta * i;
            points[i] = new Vec(Math.cos(arg), Math.sin(arg), height); // Calculate the point on the helix
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
        theta = 0;
        phi = 0;
        rotate(theta, phi);
        translate(pos);
    }

    private void rotate (double theta, double phi) {
        for (int i = 0; i < wires.length; i++) {
            wires[i].rotate(theta, phi);
            wirepoints[i].rotate(theta,phi);
        }
    }
    
    private void translate (Vec pos) { // translate all points
        for (int i = 0; i < wires.length; i++) { // 
            Vec.add(wires[i], pos);
            Vec.add(wirepoints[i], pos);
        }
    }

}
