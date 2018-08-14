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
        this.rows = dimension;
        this.columns = dimension;
    }
    
    public Matrix(List<Vector> vectors, int dimension) {
        this.vectors = vectors;
        this.vectors = transposeVector((ArrayList)this.vectors, this.vectors.size());
        this.dimension = this.vectors.size();
        this.rows = this.dimension;
        this.columns = this.vectors.get(0).getVector().length;
    }
    
    public Matrix(Matrix original) {
        this.dimension = original.getDimension();
        this.rows = original.getRows();
        this.columns = original.getColumns();
        
        double[] tempArray;
        this.vectors = new ArrayList<Vector>();
        
        for(int i = 0; i < original.getVectors().size(); i++) {
            tempArray = new double[dimension];
            System.arraycopy(original.getVectors().get(i).getVector(), 0, tempArray, 0, original.getVectors().get(i).getDimension());
            this.vectors.add(new Vector(tempArray, dimension));
        }
    }
    
    public static Matrix newIdentityMatrix(int dimension) {
        double[] tempArray;
        List<Vector> vectors = new ArrayList<Vector>();
        
        for(int i = 0; i < dimension; i++) {
            tempArray = new double[dimension];
            for(int j = 0; j < dimension; j++) {
                if(i == j)
                    tempArray[j] = 1;
                else
                    tempArray[j] = 0;
            }
            
            vectors.add(new Vector(tempArray, dimension));
        }
        
        return new Matrix(vectors, dimension);
    }
    
    //takes the vectors and turns them into a matrix
    public static List<Vector> transposeVector(ArrayList<Vector> vectors, int dimension){
        //create matrix
        ArrayList<Vector> matrix = (ArrayList)vectors.clone();
        
        matrix.removeAll(vectors);
        for(int j = 0; j < vectors.get(0).getVector().length; j++){
            double[] temp = new double[vectors.size()];    
            for(int i = 0; i < vectors.size(); i++){
                double[] curr = vectors.get(i).getVector();
                temp[i] = curr[j];
            }
            Vector temp2 = new Vector(temp, vectors.size());
            matrix.add(temp2);
        }
        return matrix;
    }
    
    public Matrix transpose() {
        ArrayList<Vector> vectorsClone = (ArrayList) this.vectors;
        ArrayList<Vector> tempMatrix = (ArrayList) vectorsClone.clone();
        
        tempMatrix.removeAll(vectors);
        for(int j = 0; j < vectors.get(0).getVector().length; j++){
            double[] temp = new double[vectors.size()];    
            for(int i = 0; i < vectors.size(); i++){
                double[] curr = vectors.get(i).getVector();
                temp[i] = curr[j];
            }
            Vector temp2 = new Vector(temp, vectors.size());
            tempMatrix.add(temp2);
        }
        
        System.out.println("Transposed:");
        PrintMatrix(tempMatrix);
        return new Matrix(tempMatrix, tempMatrix.size());
    }
    
    public Matrix times(Matrix b) {
        List<Vector> newVectors = new ArrayList<Vector>();
        
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
                    newMatrix[i][j] += vectors.get(i).getVector()[k] * b.getVectors().get(k).getVector()[j];
                }
            }
            newVectors.add(new Vector(newMatrix[i], newMatrix[i].length));
        }
        
        System.out.println("Product:");
        PrintMatrix(newVectors);
        
        return new Matrix(newVectors, newVectors.size());
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
            
            for(int j = 0; j < curr.length; j++) {
                if(curr[j] == -0)
                    System.out.print("0.0 ");
                else
                    System.out.print(curr[j] + " ");
            }
            
            System.out.println("");
        }
    }
    
    public static double[] Divide(Vector vector, double dividend) {
        double[] currArray = vector.getVector();
        
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
        
        Matrix cloneMatrix = new Matrix(this);
        
        System.out.println("Your matrix:");
        PrintMatrix(vectors);
        
        // 1/2 Gauss-Jordan (down)
        for(int i = 0; i < dimension; i++) {
            try {
                div = cloneMatrix.getVectors().get(i).getVector()[i];
            } catch(Exception e) {
                break;
            }
            
            // If the value is 0, try to swap it woth a row that has a non-zero value in the same iumn if one doesn't exist, move to the next iumn
            if(div == 0)
                if(Swap(cloneMatrix.getVectors(), i) == null) {
                    i++;
                }
            
            try {
                valueCheck = cloneMatrix.getVectors().get(i).getVector()[i];
            } catch (Exception e) {
                break;
            }

            // If the value isn't 0, do the rest of the operations
            if(cloneMatrix.getVectors().get(i).getVector()[i] != 0) {
                // Make the main diagonal/current 1 but keep the original values of the row first
                original = new double[cloneMatrix.getVectors().get(i).getDimension()];
                System.arraycopy(cloneMatrix.getVectors().get(i).getVector(), 0, original, 0, cloneMatrix.getVectors().get(i).getDimension());
                cloneMatrix.getVectors().get(i).setVector(Divide(cloneMatrix.getVectors().get(i), div)); //Had to make another function to divide the whole row by a member of itself because Scale(1/value) returns a different value

                // Make everything below the current 1, 0
                for(int j = i + 1; j < dimension; j++) {
                    // Make a new copy of the current vector
                    size = cloneMatrix.getVectors().get(i).getDimension();
                    currArray = new double[size];
                    System.arraycopy(cloneMatrix.getVectors().get(i).getVector(), 0, currArray, 0, size);

                    curr = new Vector(currArray, currArray.length);
                    
                    // Scale the current (copy) vector to the negative of the element we want to make 0
                    curr.scale(-cloneMatrix.getVectors().get(j).getVector()[i]);
                    // Make it 0 by adding the scaled vector
                    cloneMatrix.getVectors().get(j).add(curr);
                    // Do the operations on the constant as well if constant vector isn't null
                }
                
                // Put back the original values prior to making it one
                cloneMatrix.getVectors().get(i).setVector(original);
            }
        }

        for(int i = 0; i < cloneMatrix.getVectors().size(); i++)
            det *= cloneMatrix.getVectors().get(i).getVector()[i];
        
        System.out.println("Determinant = " + det);
        
        return det;
    }
    
    public Matrix inverse () {
        int size;
        double currConstant;
        double div;
        double valueCheck;
        double[] currArray;
        double[] currIdentityArray;
        Vector curr;
        Vector currIdentity;
        
        Matrix cloneMatrix = new Matrix(this);
        
        if(rows != columns || cloneMatrix.det() == 0) {
            System.out.println();
            System.out.println("Not invertible!");
            return null;
        }
        
        Matrix identityMatrix = newIdentityMatrix(dimension);
            
        // 1/2 Gauss-Jordan (down)
        for(int i = 0; i < dimension; i++) {
            try {
                div = cloneMatrix.getVectors().get(i).getVector()[i];
            } catch(Exception e) {
                break;
            }
            
            // If the value is 0, try to swap it woth a row that has a non-zero value in the same iumn if one doesn't exist, move to the next iumn
            if(div == 0) {
                if(Swap(cloneMatrix.getVectors(), i) == null) {
                    i++;
                }
                else {
                    Swap(identityMatrix.getVectors(), i);
                }
            }
            
            try {
                valueCheck = cloneMatrix.getVectors().get(i).getVector()[i];
            } catch (Exception e) {
                break;
            }

            // If the value isn't 0, do the rest of the operations
            if(cloneMatrix.getVectors().get(i).getVector()[i] != 0) {
                // Make the main diagonal/current 1
                cloneMatrix.getVectors().get(i).setVector(Divide(cloneMatrix.getVectors().get(i), div)); //Had to make another function to divide the whole row by a member of itself because Scale(1/value) returns a different value
                identityMatrix.getVectors().get(i).setVector(Divide(identityMatrix.getVectors().get(i), div));

                // Make everything below the current 1, 0
                for(int j = i + 1; j < dimension; j++) {
                    // Make a new copy of the current vector
                    size = cloneMatrix.getVectors().get(i).getDimension();
                    currArray = new double[size];
                    currIdentityArray = new double[size];
                    System.arraycopy(cloneMatrix.getVectors().get(i).getVector(), 0, currArray, 0, size);
                    System.arraycopy(identityMatrix.getVectors().get(i).getVector(), 0, currIdentityArray, 0, size);

                    curr = new Vector(currArray, currArray.length);
                    currIdentity = new Vector(currIdentityArray, currIdentityArray.length);
                    
                    // Scale the current (copy) vector to the negative of the element we want to make 0
                    curr.scale(-cloneMatrix.getVectors().get(j).getVector()[i]);
                    currIdentity.scale(-cloneMatrix.getVectors().get(j).getVector()[i]);
                    // Make it 0 by adding the scaled vector
                    cloneMatrix.getVectors().get(j).add(curr);
                    identityMatrix.getVectors().get(j).add(currIdentity);
                }
            }
        }
        
        // Remove the values on top of the main diagonal
        for(int i = dimension-1; i >= 0; i--) {
            try {
            div = cloneMatrix.getVectors().get(i).getVector()[i];
            } catch(Exception e) {
                break;
            }
            if(div != 0) {
                // Make everything above the current 1, 0
                for(int j = i - 1; j >= 0; j--) {
                    // Make a new copy of the current vector
                    size = cloneMatrix.getVectors().get(i).getDimension();
                    currArray = new double[size];
                    currIdentityArray = new double[size];
                    System.arraycopy(cloneMatrix.getVectors().get(i).getVector(), 0, currArray, 0, size);
                    System.arraycopy(identityMatrix.getVectors().get(i).getVector(), 0, currIdentityArray, 0, size);

                    curr = new Vector(currArray, currArray.length);
                    currIdentity = new Vector(currIdentityArray, currIdentityArray.length);
                    
                    // Scale the current (copy) vector to the negative of the element we want to make 0
                    curr.scale(-cloneMatrix.getVectors().get(j).getVector()[i]);
                    currIdentity.scale(-cloneMatrix.getVectors().get(j).getVector()[i]);
                    // Make it 0 by adding the scaled vector
                    cloneMatrix.getVectors().get(j).add(curr);
                    identityMatrix.getVectors().get(j).add(currIdentity);
                }
            }
        }
        
        System.out.println("Inverse:");
        PrintMatrix(identityMatrix.getVectors());
        System.out.println("");        
        return identityMatrix;
    }
    
    //Idk if I'll use these getters but u kno, just in case
    public List<Vector> getVectors() {
        return vectors;
    }
    
    public int getDimension() {
        return dimension;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
}
