/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advdisc;

import java.util.ArrayList;
import java.util.Collections;
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
    
    public void PrintVector() {
        for(int i = 0; i < dimension; i++)
            System.out.print(vector[i] + " ");
    }
    
    public static void moveToEnd(double[] vector, int index) {
        for(int i = index; i < vector.length && i + 1 < vector.length; i++) {
            vector[i] = vector[i + 1];
        }
        
        vector[vector.length-1] = 0;
    }
    
    public static void PrintMatrix(List<Vector> vectors, Vector constants) {
        double[] curr;
        double[] constant = new double[vectors.size()];
                
        if(constants != null)
            constant = constants.getVector();
        for(int i = 0; i < vectors.size(); i++) {
            curr = vectors.get(i).getVector();
            
            for(int j = 0; j < vectors.get(i).dimension; j++) {
                if(curr[j] == -0)
                    System.out.print("0.0 ");
                else
                    System.out.print(curr[j] + " ");
            }
            
            if(constants != null)
                System.out.print(constant[i]);
            
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
    
    public static List<Vector> Swap(List<Vector> vectors, int currentVector, Vector constants) {
        double tempSwitch;
        
        for(int i = currentVector + 1; i < vectors.size(); i++) {
            if(vectors.get(i).getVector()[currentVector] != 0) {
                Collections.swap(vectors, currentVector, i);
                tempSwitch = constants.getVector()[currentVector];
                constants.getVector()[currentVector] = constants.getVector()[i];
                constants.getVector()[i] = tempSwitch;
                break;
            }
        }
        
        if(vectors.get(currentVector).getVector()[currentVector] == 0)
            return null;
        
        return vectors;
    }
    
    public static boolean TransposeCheck(ArrayList<Vector> vectors, int dimension) {
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
        
        if(vectors.size() < vectors.get(0).getDimension() || vectors.size() <= 1)
            return false;
        else
            return true;
    }
    
    public static Vector finalCheck(List<Vector> vectors, Vector constants, int dimension) {
        boolean zero = true;
        int constantCounter = 0;
        Vector curr = null;
        
        // Check for no solutions by iumn
        if(constants != null) {
            for(int i = 0; i < dimension; i++) {
                zero = true;
                // Get the row we want to check so it's easier to iterate
                curr = vectors.get(i);
                double[] currVector = vectors.get(i).getVector();
                
                // If we find a non-zero value, we say false
                for(int j = 0; j < currVector.length; j++) {
                    if(currVector[j] != 0)
                        zero = false;
                }
                
                // If all values is 0, check if the constant is also 0
                if(zero == true){
                    if(constants.getVector()[i] != 0) {
                        System.out.println();
                        System.out.println("No solution!");
                        return null;
                    }
                    else {
                        moveToEnd(constants.getVector(), i);
                        vectors.remove(curr);
                        vectors.add(curr);
                        i--;
                        dimension--;
                    }
                }
            }
            
            for(int i = 0; i < vectors.get(0).getDimension(); i ++ ){
                zero = true;
                
                for(int j = 0; j < vectors.size(); j++) {
                    if(vectors.get(j).getVector()[i] != 0)
                        zero = false;
                }
                
                if(zero == false)
                    constantCounter++;
            }
            
            double[] newConstants = new double[constantCounter];
            System.arraycopy(constants.getVector(), 0, newConstants, 0, constantCounter);
            
            PrintMatrix(vectors, constants);
            
            return new Vector(newConstants, newConstants.length);
        }
        
        return constants;
    }
    
    public static Vector Gauss_Jordan(List<Vector> vectors, int dimension, Vector constants) {
        int size;
        double currConstant;
        double div;
        double valueCheck;
        double[] currArray;
        Vector curr;
        
        // Transpose the vectors
        if(!TransposeCheck((ArrayList)vectors, dimension)) {
            System.out.println();
            System.out.println("No solution!");
            return null;
        }
            
        System.out.println("after transpose");
        PrintMatrix(vectors, constants);
        System.out.println("");
        
        // 1/2 Gauss-Jordan (down)
        for(int i = 0; i < dimension; i++) {
            try {
            div = vectors.get(i).getVector()[i];
            } catch(Exception e) {
                break;
            }
            
            // If the value is 0, try to swap it woth a row that has a non-zero value in the same iumn if one doesn't exist, move to the next iumn
            if(div == 0)
                if(Swap(vectors, i, constants) == null) {
                    i++;
                }
            
            try {
                valueCheck = vectors.get(i).getVector()[i];
            } catch (Exception e) {
                break;
            }

            // If the value isn't 0, do the rest of the operations
            if(vectors.get(i).getVector()[i] != 0) {
                // Do the operations on the constant as well if constant vector isn't null
                if(constants != null)
                    constants.getVector()[i] /= vectors.get(i).getVector()[i];
                // Make the main diagonal/current 1
                vectors.get(i).setVector(Divide(vectors.get(i), i)); //Had to make another function to divide the whole row by a member of itself because Scale(1/value) returns a different value
                PrintMatrix(vectors, constants);
                System.out.println("");

                // Make everything below the current 1, 0
                for(int j = i + 1; j < dimension; j++) {
                    currConstant = 0;
                    // Make a new copy of the current vector
                    size = vectors.get(i).getDimension();
                    if(constants != null)
                        currConstant += constants.getVector()[i];
                    currArray = new double[size];
                    System.arraycopy(vectors.get(i).getVector(), 0, currArray, 0, size);

                    curr = new Vector(currArray, currArray.length);
                    
                    // Scale the current (copy) vector to the negative of the element we want to make 0
                    curr.scale(-vectors.get(j).getVector()[i]);
                    currConstant *= (-vectors.get(j).getVector()[i]);
                    // Make it 0 by adding the scaled vector
                    vectors.get(j).add(curr);
                    // Do the operations on the constant as well if constant vector isn't null
                    if(constants != null)
                        constants.getVector()[j] += currConstant;

                    PrintMatrix(vectors, constants);
                    System.out.println("");
                }
            }
        }

        // Remove the values on top of the main diagonal
        for(int i = dimension-1; i >= 0; i--) {
            try {
            div = vectors.get(i).getVector()[i];
            } catch(Exception e) {
                break;
            }
            if(div != 0) {
                // Make everything above the current 1, 0
                for(int j = i - 1; j >= 0; j--) {
                    currConstant = 0;
                    // Make a new copy of the current vector
                    size = vectors.get(i).getDimension();
                    if(constants != null)
                        currConstant += constants.getVector()[i];
                    currArray = new double[size];
                    System.arraycopy(vectors.get(i).getVector(), 0, currArray, 0, size);

                    curr = new Vector(currArray, currArray.length);
                    
                    // Scale the current (copy) vector to the negative of the element we want to make 0
                    curr.scale(-vectors.get(j).getVector()[i]);
                    currConstant *= (-vectors.get(j).getVector()[i]);
                    // Make it 0 by adding the scaled vector
                    vectors.get(j).add(curr);
                    if(constants != null)
                        constants.getVector()[j] += currConstant;

                    PrintMatrix(vectors, constants);
                    System.out.println("");
                }
            }
        }
        
        return finalCheck(vectors, constants, dimension);
    }
    
    public static int span(List<Vector> vectors, int dimension) {
        int span = 0;
        
        Gauss_Jordan(vectors, dimension, null);
        
        for(int i = 0; i < vectors.size(); i++) {
            for(int j = 0; j < vectors.get(i).getDimension(); j++) {
                if (vectors.get(i).getVector()[j] != 0) {
                    span++;
                    break;
                }
            }
        }
        
        return span;
    }
}

public class ADVDISC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        List<Vector> vectors = new ArrayList<Vector>() {{
           add(new Vector(new double[] {1, 0, 0, 0}, 4));
           add(new Vector(new double[] {0, 0, 0, 0}, 4));
           add(new Vector(new double[] {0, 1, 0, 0}, 4));
           add(new Vector(new double[] {0, 0, 0, 1}, 4));
        }};
        
        Vector constant = Vector.Gauss_Jordan(vectors, 4, new Vector(new double[] {2, 0, 0, 2}, 4));
        if (constant != null)
            constant.PrintVector();
//        System.out.println("span = " + Vector.span(vectors, 3));
    }
    
}
