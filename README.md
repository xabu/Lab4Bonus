Lab4Bonus
=========

This is a project to simulate the magnetic field caused by two solenoids. This is for the grade 12 AP Physics course.

It will work by creating several vectors to define the solenoids, and then using the Biot-Savart law, it will approximate the magnetic field at all points in the domain.

Begin solenoid:

class Coil {

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
  
  points = new Vec[16 * n / (2 * Math.Pi())];
  dtheta = length / points.length;
  for (int i = 0; i < points.length; i++) {
    theta = dtheta * i;
    points[i] = new Vec(Math.cos(theta), Math.sin(theta), theta);
  }
  
  rotate();

}

private void rotate () {

};

}

