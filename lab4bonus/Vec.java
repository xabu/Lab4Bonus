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
    /*
    The array that will store the values of the vector. 
    For example, vector holds {x,y,z} in R3
    */
    private double[] vector;
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
        //Sets the size of vector to fit the dimensions of the situation
        vector = new double [dimensions];
        //sets all values to 0, creating a 0 vector
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
        /*
        The cross product values were solved generally by hand.
        This solution was found and is used to simplify the computations made 
        by the computer.
        */
        return new Vec(
                u.getY()*v.getZ() - u.getZ()*v.getY(),
                u.getZ()*v.getX() - u.getX()*v.getZ(), 
                u.getX()*v.getY() - u.getY()*v.getX());
    }
    /**
     * Calculates the dot product of two vectors
     * @param u The first factor of the dot product
     * @param v The second factor 
     * @return the dot product
     */
    public static double dot(Vec u, Vec v){
        /*
        The dot product between two vectors is a scalar given by the sum of the
        products of each components of the two vectors.
        */
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
        /*
        The product of a sclar and a vector is a vector with each component 
        multiplied by the scalar.
        */
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
        /*
        The sum of two vectors is given by a new vector with each component
        being equal to the sum of the corresponding components of the two 
        vectors that were summed.
        */
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
        /*
        The difference is equal to the sum of one vector and the negative of 
        the other vector. The negative of a vector is a scalar multiple of the 
        vector given by -1*v. 
        */
        Vec negv = scale(-1,v);
        return add(u,negv);
    }
    /**
     * Calculates the norm of this vector.
     * @return The magnitude, or norm of the vector.
     */
    public double norm(){
        /*
        The norm of a vector is given by the root of the internal dot product
        of the vector.
        */
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
    
    public void setX(double x) { vector[0] = x; }
    public void setY(double y) { vector[1] = y; }
    public void setZ(double z) { vector[2] = z; }
    
    public void rotate (double theta, double phi) {
        // prefetch the existing vector
        double x = getX();
        double y = getY();
        double z = getZ();
        // apply x rotation matrix
        setY(y*Math.cos(theta) - z*Math.sin(theta));
        setZ(y*Math.sin(theta) + z*Math.cos(theta));
        
        // prefetch the new vector
        x = getX();
        y = getY();
        z = getZ();
        // apply y rotation matrix
        setX(x*Math.cos(theta)+z*Math.sin(theta));
        setZ(-x*Math.sin(theta)+z*Math.cos(theta));
    }
    
    public void print () {
        System.out.println(getX());
        System.out.println(getY());
        System.out.println(getZ());
        System.out.println();
    }
}
