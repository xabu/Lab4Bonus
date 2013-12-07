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
public class Vec {
    //the array that will store the values of the vector. Vector holds {x,y,z}
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
        if(u.getSize() == 3 && v.getSize() == 3){
            return new Vec(
                    u.vector[1]*v.vector[2] - u.vector[2]*v.vector[1],
                    u.vector[2]*v.vector[0]-u.vector[0]*v.vector[2], 
                    u.vector[0]*v.vector[1]-u.vector[1]*v.vector[0]);
        }
        return new Vec();
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
                dot+= u.vector[i]*v.vector[1];
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
}
