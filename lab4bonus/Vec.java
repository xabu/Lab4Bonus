/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab4bonus;

/**
 * This is a class to define a vector
 * @author Stephen Wen
 */
public class Vec {
    //the array that will store the values of the vector. For example, vector holds {x,y,z} in R3
    double[] vector;
    /**
     * creates a default 0 vector in R3
     */
    public Vec(){
        vector = new double[3];
        vector[0] = 0;
        vector[1] = 0;
        vector[2] = 0;
    }
    /**
     * Creates a vector in R3 with passed x, y, and z values
     * @param x The x component of the vector.
     * @param y The y component of the vector.
     * @param z The z component of the vector.
     */
    public Vec(double x, double y, double z){
        vector = new double [3];
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
    }
    /**
     * Creates a zero vector in 'dimensions'-space
     * @param dimensions The dimensions of the vector
     */
    public Vec (int dimensions){
        vector = new double [dimensions];
        for(int i = 0; i < dimensions; i++){
            vector[i] = 0;
        }
    }
    /**
     * Calculates the cross product of two vectors
     * @param u The first factor of the cross product
     * @param v The second factor of the cross product
     * @return A new vector that is equal to u x v
     */
    public static Vec cross (Vec u, Vec v){
        return new Vec(
                u.getY()*v.getZ() - u.getZ()*v.getY(),
                u.getZ()*v.getX()-u.getX()*v.getZ(), 
                u.getX()*v.getY()-u.getY()*v.getX());
    }
    /**
     * Calculates the dot product of two vectors
     * @param u The first factor of the dot product
     * @param v The second factor 
     * @return the dot product
     */
    public static double dot(Vec u, Vec v){
        double dot = 0;
        if(u.getSize() == v.getSize()){
            for(int i = 0; i < u.getSize(); i++){
                dot+= u.vector[i]*v.vector[i];
            }
        }
        return dot;
    }
    /**
     * Returns the product of a scalar and a vector
     * @param d The scalar
     * @param v The vector
     * @return A new vector equal to d*v.
     */
    public static Vec scale(double d, Vec v){
        Vec ret = new Vec(v.getSize());
        for(int i = 0; i < v.getSize();i++){
            ret.vector[i] = v.vector[i]*d;
        }
        return ret;
    }
    /**
     * Gives the sum of two vectors, u + v.
     * @param u The first vector
     * @param v The second vector
     * @return The sum of u and v
     */
    public static Vec add(Vec u, Vec v){
        if(u.getSize() != v.getSize())
            return new Vec();
        Vec ret = new Vec(u.getSize());
        for(int i = 0;i<ret.getSize();i++){
            ret.vector[i] = u.vector[i]+v.vector[i];
        }
        return ret;
    }
    /**
     * Gives the difference between two vectors, u - v.
     * @param u The first vector
     * @param v The second vector
     * @return The difference between u and v
     */
    public static Vec subtract(Vec u, Vec v){
        Vec negv = scale(-1,v);
        return add(u,negv);
    }
    /**
     * Calculates the norm of this vector.
     * @return The magnitude, or norm of the vector.
     */
    public double norm(){
        return Math.sqrt(dot(this,this));
    }
    /**
     * Returns the dimensions of the vector. 
     * @return The size of the vector
     */
    public int getSize(){
        return vector.length;
    }
    /**
     * Returns the x value of a vector in 3 space
     * @return The x component
     */
    public double getX(){
        if(getSize() == 3)
            return vector[0];
        else
            return 0;
    }
    /**
     * Returns the y value of a vector in 3 space
     * @return The y component
     */
    public double getY(){
        if(getSize() == 3)
            return vector[1];
        else
            return 0;
    }
    /**
     * Returns the z value of a vector in 3 space
     * @return The z component
     */
    public double getZ(){
        if(getSize() == 3)
            return vector[2];
        else
            return 0;
    }
}
