/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advdisc;

import java.util.ArrayList;
import java.util.List;

class Vector {
    final int dimension;        //  The usage of an immutable Integer variable to hold a value for Vector dimension.
    private double[] vector;    //  The usage of an Array/List-like structure to store the Vector data.
    
    //  A proper implementation of a default constructor that initializes the vector as a zero vector of a given dimension.
    //  Constructor definition to be used: Vector (int dimension)
    public Vector(int dimension) {
        this.dimension = dimension;
        this.vector = new double[dimension];
    }
    
    //  A proper implementation of a constructor, converting an already-existing array/list of data from a rudimentary data structure into the vector class.
    //  Constructor definition to be used: Vector (double[ ] array, int dimension)
    public Vector(double[] vector, int dimension) {
        this.vector = vector;
        this.dimension = dimension;
    }
    
    //  A proper implementation of a function for vector scaling.
    //  Function header to be used: Vector scale (double scalar)
    //  Usage example: Assuming a Vector v and int b exists, v.scale(b) should scale the elements of v by b and return the scaled vector v. The elements inside v must be changed and be correctly scaled by b.     
    public Vector scale(double scalar) {
        for(int i = 0; i < vector.length; i++)
            vector[i] = vector[i] * scalar;
        
        return this;
    }
    
    //  A proper implementation of a function for vector addition. Errors for size mismatches when adding vectors must also be handled.
    //  Function header to be used: Vector add (Vector addend)
    //  Usage example: Assuming both Vector v and w exist, v.add(w) should return the vector sum between v and w. The elements inside v must be changed and be a correct result of the operation of Vector addition between v and w.
    public Vector add(Vector addend) {
        double[] addendVector = addend.getVector();
        if(dimension != addend.getDimension()) {
            System.out.println("Size mismatch.");
            return this;
        } else {
            for(int i = 0; i < vector.length; i++)
                vector[i] += addendVector[i];
        }
        
        return this;
    }
    
    public int getDimension() {
        return dimension;
    }
    
    public void setVector(double[] vector) {
        this.vector = vector;
    }
    
    public double[] getVector() {
        return vector;
    }
    
    public static void PrintMatrix(List<Vector> vectors) {
        double[] curr;
        for(int i = 0; i < vectors.size(); i++) {
            curr = vectors.get(i).getVector();
            
            for(int j = 0; j < vectors.get(i).dimension; j++)
                System.out.print(curr[j] + " ");
            
            System.out.println("");
        }
    }
    
    public static Vector Gauss_Jordan(List<Vector> vectors, int dimension, Vector constants) {
        int size;
        double[] currArray;
        Vector curr;
        for(int i = 0; i < dimension; i++) {
            if(vectors.get(i).getVector()[i] != 0) {
                // Make the main diagonal 1
                vectors.get(i).scale(1/vectors.get(i).getVector()[i]);
                PrintMatrix(vectors);
                System.out.println("");

                // Make everything below the current 1, 0
                for(int j = i + 1; j < dimension; j++) {
                    // Make a new copy of the current vector
                    size = vectors.get(i).getDimension();
                    currArray = new double[size];
                    for(int k = 0; k < size; k++)
                        currArray[k] = vectors.get(i).getVector()[k];

                    curr = new Vector(currArray, currArray.length);
                    
                    // Scale the current (copy) vector to the element of the next to make it 0
                    curr.scale(-vectors.get(j).getVector()[i]);
                    // Make it 0
                    vectors.get(j).add(curr);

                    PrintMatrix(vectors);
                    System.out.println("");
                }
            }
        }

        return constants;
    }
}

public class ADVDISC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Vector> matrix = new ArrayList<Vector>() {{
           add(new Vector(new double[] {1, 2, 3, 4}, 4));
           add(new Vector(new double[] {2, 3, 4, 5}, 4));
           add(new Vector(new double[] {2, 7, 8, 9}, 4));
           add(new Vector(new double[] {1, 3, 5, 7}, 4));
        }};
        
        Vector.Gauss_Jordan(matrix, 4, new Vector(new double[] {1, 2, 3, 4}, 4));
    }
    
}
