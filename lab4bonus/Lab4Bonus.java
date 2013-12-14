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
        // TODO code application logic here
        int N1 = 300;
        double l1 = 0.05;
        double r1 = 0.10;
        Vec pos1 = new Vec();
        double theta1 = 0;
        double phi1 = 0;
        boolean clock1 = true;
        
        Coil coil1 = new Coil (N1, l1, r1, pos1, theta1, phi1, clock1);
        
        int N2 = 300;
        double l2 = 0.05;
        double r2 = 0.10;
        Vec pos2 = new Vec(0, 0, 0.3);
        double theta2 = 0;
        double phi2 = 0;
        boolean clock2 = true;
        
        Coil coil2 = new Coil (N2, l2, r2, pos2, theta2, phi2, clock2);
        
        
    }
    
}
