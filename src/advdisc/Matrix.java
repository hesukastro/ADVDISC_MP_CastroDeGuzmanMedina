/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advdisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Castro, Joesei Jesus
    De Guzman, Jersey Adelei
    Medina, Chelsey Ann
*/

public class Matrix {
    private int dimension;
    private int rows;
    private int columns;
    private List<Vector> vectors = new ArrayList<Vector>();
    
    public Matrix(int dimension) {
        this.dimension = dimension;
    }
    
    public Matrix(List<Vector> vectors, int dimension) {
        this.vectors = Transpose((ArrayList)vectors, dimension);
        this.dimension = dimension;
        this.rows = dimension;
        this.columns = vectors.get(0).getDimension();
    }
    
    public static ArrayList<Vector> Transpose(ArrayList<Vector> vectors, int dimension) {
        ArrayList<Vector> tempMatrix = (ArrayList) vectors.clone();
        
        vectors.removeAll(vectors);
        
        double[] currArray;
        for (int i = 0; i < dimension; i++) {
            currArray = new double[tempMatrix.size()];
            
            for (int j = 0; j < tempMatrix.size(); j++) {
                currArray[j] = tempMatrix.get(j).getVector()[i];
            }
            
            vectors.add(new Vector(currArray, currArray.length));
        }
        
        return tempMatrix;
    }
    
    public Matrix times(Matrix b) {
        // row x column
        // [a x b][c x d] is valid IFF b == c
        if(columns != b.getRows()) {
            System.out.println("Size mismath.");
            return this;
        }
        
        // [a x b][c x d] will result to [a x d]
        double[][] newMatrix = new double[rows][b.getColumns()];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < b.getColumns(); j++) {
                for (int k = 0; k < columns; k++) {
                    newMatrix[i][j] += vectors.get(k).getVector()[i] * b.getVectors().get(j).getVector()[k];
                }
            }
        }
        
//        Printing for tracing
//        for(int i = 0; i < rows; i++) {
//            for(int j = 0; j < b.getColumns(); j++) {
//                System.out.print(newMatrix[i][j] + " ");
//            }
//            System.out.println("");
//        }
        
        return new Matrix(dimension);
    }
    
    public static List<Vector> Swap(List<Vector> vectors, int currentVector) {
        for(int i = currentVector + 1; i < vectors.size(); i++) {
            if(vectors.get(i).getVector()[currentVector] != 0) {
                Collections.swap(vectors, currentVector, i);
                break;
            }
        }
        
        if(vectors.get(currentVector).getVector()[currentVector] == 0)
            return null;
        
        return vectors;
    }
    
    public static void PrintMatrix(List<Vector> vectors) {
        double[] curr;

        for(int i = 0; i < vectors.size(); i++) {
            curr = vectors.get(i).getVector();
            
            for(int j = 0; j < vectors.get(i).dimension; j++) {
                if(curr[j] == -0)
                    System.out.print("0.0 ");
                else
                    System.out.print(curr[j] + " ");
            }
            
            System.out.println("");
        }
    }
    
    public static double[] Divide(Vector vector, int dividendIndex) {
        double[] currArray = vector.getVector();
        double dividend = vector.getVector()[dividendIndex];
        
        for(int i = 0; i < currArray.length; i++)
            currArray[i] /= dividend;
        
        return currArray;
    }
    
    public double det() {
        int size;
        double div;
        double valueCheck;
        double[] currArray;
        Vector curr;
        double[] original;
        double det = 1;
        
        // 1/2 Gauss-Jordan (down)
        for(int i = 0; i < dimension; i++) {
            try {
                div = vectors.get(i).getVector()[i];
            } catch(Exception e) {
                break;
            }
            
            // If the value is 0, try to swap it woth a row that has a non-zero value in the same iumn if one doesn't exist, move to the next iumn
            if(div == 0)
                if(Swap(vectors, i) == null) {
                    i++;
                }
            
            try {
                valueCheck = vectors.get(i).getVector()[i];
            } catch (Exception e) {
                break;
            }

            // If the value isn't 0, do the rest of the operations
            if(vectors.get(i).getVector()[i] != 0) {
                // Make the main diagonal/current 1 but keep the original values of the row first
                original = new double[vectors.get(i).getDimension()];
                System.arraycopy(vectors.get(i).getVector(), 0, original, 0, vectors.get(i).getDimension());
                vectors.get(i).setVector(Divide(vectors.get(i), i)); //Had to make another function to divide the whole row by a member of itself because Scale(1/value) returns a different value
//                PrintMatrix(vectors);
//                System.out.println("");

                // Make everything below the current 1, 0
                for(int j = i + 1; j < dimension; j++) {
                    // Make a new copy of the current vector
                    size = vectors.get(i).getDimension();
                    currArray = new double[size];
                    System.arraycopy(vectors.get(i).getVector(), 0, currArray, 0, size);

                    curr = new Vector(currArray, currArray.length);
                    
                    // Scale the current (copy) vector to the negative of the element we want to make 0
                    curr.scale(-vectors.get(j).getVector()[i]);
                    // Make it 0 by adding the scaled vector
                    vectors.get(j).add(curr);
                    // Do the operations on the constant as well if constant vector isn't null

//                    PrintMatrix(vectors);
//                    System.out.println("");
                }
                
                // Put back the original values prior to making it one
                vectors.get(i).setVector(original);
//                PrintMatrix(vectors);
//                System.out.println("");
            }
        }

        for(int i = 0; i < vectors.size(); i++)
            det *= vectors.get(i).getVector()[i];
        
        return det;
    }
    
    //Idk if I'll use these getters but u kno, just in case
    public List<Vector> getVectors() {
        return vectors;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
}
