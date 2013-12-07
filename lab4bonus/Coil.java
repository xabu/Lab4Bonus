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
    public Vec direction;
    public boolean cw;
    public Vec[] points;

    public Coil (int n, double l, double r, Vec pos, Vec dir, boolean clock) {

        turns = n;
        length = l;
        radius = r;
        position = pos;
        direction = dir;
        cw = clock;
  
        points = new Vec[16 * n ];
        double dtheta = length / points.length;
        for (int i = 0; i < points.length; i++) {
            double theta = dtheta * i;
            points[i] = new Vec(Math.cos(theta), Math.sin(theta), theta);
        }

        rotate();
    }

    private void rotate () {

    }

}
