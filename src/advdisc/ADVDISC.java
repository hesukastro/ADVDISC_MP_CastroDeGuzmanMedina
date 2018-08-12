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
           add(new Vector(new double[] {1, 3}, 2));
           add(new Vector(new double[] {2, 4}, 2));
        }};
        
        List<Vector> vectors2 = new ArrayList<Vector>() {{
           add(new Vector(new double[] {2, 1}, 2));
           add(new Vector(new double[] {0, 2}, 2));
        }};
        
        Matrix tempMatrix = new Matrix(vectors, vectors.get(0).getDimension());
        Matrix tempMatrix2 = new Matrix(vectors2, vectors.get(0).getDimension());
        
        tempMatrix.times(tempMatrix2);
        
        /*
            this creates a matrix
            | 1 0 0 0 |
            | 0 0 1 0 |
            | 0 0 0 0 |
            | 0 0 0 1 |
            basically, nagtranspose siya. take note.
        */
        
//        Vector constant = Vector.Gauss_Jordan(vectors, 4, new Vector(new double[] {2, 0, 0, 2}, 4));
//        if (constant != null)
//            constant.PrintVector();
//        System.out.println("span = " + Vector.span(vectors, 3));
    }
    
}