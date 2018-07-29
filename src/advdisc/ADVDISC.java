/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advdisc;

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
    public Vector scale(int scalar) {
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
    
    public double[] getVector() {
        return vector;
    }
}

public class ADVDISC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
