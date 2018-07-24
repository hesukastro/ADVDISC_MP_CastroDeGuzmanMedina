/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advdisc;

/**
 *
 * @author jjoes
 */
public class Vector {
    final int dimension;
    private int[] vector;
    
    public Vector(int dimension) {
        this.dimension = dimension;
        this.vector = new int[dimension];
    }
    
    //multiple lahat ng elements with a scalar; scalar yung 5 sa 5[1 2], pwede sa malaking vector na kunyaru 2x2
    public Vector scale(int scalar) {
        for(int i = 0; i < vector.length; i++)
            vector[i] = vector[i] * scalar;
        
        return this;
    }
    
    //add vecotrs; duh; pero dapat same size. tapos yung add lang yung same position
    public Vector add(Vector addend) {
        int[] addendVector = addend.getVector();
        if(dimension != addend.getDimension()) {
            System.out.println("Size mismatch.");
            return this;
        } else {
            for(int i = 0; i < vector.length; i++)
                vector[i] += addendVector[i];
        }
        
        return this;
    }
    
    //kailangan rin ng function para macheck size nung vector para checker sa add ganun
    public int getDimension() {
        return dimension;
    }
    
    public int[] getVector() {
        return vector;
    }
}
