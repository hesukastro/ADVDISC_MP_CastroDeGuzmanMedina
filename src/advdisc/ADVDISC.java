/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advdisc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jjoes
 */
public class ADVDISC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        List<Vector> vectors = new ArrayList<Vector>() {{
            add(new Vector(new double[] {2, 1, 5}, 3));
            add(new Vector(new double[] {1, 3, 6}, 3));
            add(new Vector(new double[] {3, 2, 7}, 3));
        }};        
        
        List<Vector> vectors2 = new ArrayList<Vector>() {{
            add(new Vector(new double[] {1, 1, 0}, 3));
            add(new Vector(new double[] {1, 0, 1}, 3));
            add(new Vector(new double[] {1, 1, 1}, 3));
        }};
        
        Matrix tempMatrix = new Matrix(vectors2, vectors.get(0).getDimension());
        Matrix tempMatrix2 = tempMatrix.inverse();
        
        tempMatrix.times(tempMatrix2);
    }
    
}