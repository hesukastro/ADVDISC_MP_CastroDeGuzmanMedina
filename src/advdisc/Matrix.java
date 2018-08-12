/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advdisc;

import java.util.ArrayList;
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
        
        Vector.PrintMatrix(vectors, null);
        System.out.println("rows = " + rows);
        System.out.println("columns = " + columns);
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
        double temp;
        int row = 0;
        int column = 0;
        double[][] newMatrix = new double[rows][b.getColumns()];
        Vector currentAVector;
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < b.getColumns(); j++) {
                newMatrix[i][j] = 0;
            }
        }
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < b.getColumns(); j++) {
                System.out.print(newMatrix[i][j] + " ");
            }
            System.out.println("");
        }
        
        return new Matrix(dimension);
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
