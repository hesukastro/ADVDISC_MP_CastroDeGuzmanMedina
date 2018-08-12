# ADVDISC_MP_CastroDeGuzmanMedina

The following must be found in the code: (For function definitions, only function names need to be followed. Variable names can be changed and are only added as an example.)

A proper implementation of a Matrix function via the usage of the created Vector data structure. (5 points)
A proper implementation of a default constructor that initializes the matrix as an identity matrix of a given dimension.
Constructor definition to be used: Matrix (int dimension)
A proper implementation of a constructor, converting an already-existing array/list of data from a rudimentary data structure into the vector class.
Constructor definition(s) to be used: Matrix (List<Vector> list, int dimension)
Note: the dimension variable refers to the length of each vector inside list. The 
The usage of an Array/List-like structure to store Matrix data as a list of Vectors. You may also store the Matrix as a 2d array.
The usage of immutable Integer variables to hold values for the number of rows/columns.


An implementation of function for matrix multiplication. (10 points)
A proper implementation of a function for matrix multiplication.
Function header to be used: Matrix times (Matrix other)
Usage example: Assuming a Matrix a and Matrix b exists, a.times(b) should output the matrix multiplication of a and b.
Errors for size mismatches when multiplying matrices must also be handled.


An implementation of a function that performs Gauss-Jordan Elimination to find the determinant of the matrix. (10 points)
Function header to be used: double det ()
The function must incorporate an implementation of Gauss-Jordan Elimination.
Usage example: Given a Matrix m, the function call m.det() should return the determinant of the matrix.


An implementation of a function that finds the inverse of the matrix. (10 points)
Function header to be used: Matrix inverse ()
The function must incorporate an implementation of Gauss-Jordan Elimination. The function must return a null value if the matrix is not invertible; the matrix does not have an inverse.
Usage example: Given a Matrix m, m.inverse() should return a matrix containing the matrix inverse of m.
