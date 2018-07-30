# ADVDISC_MP_CastroDeGuzmanMedina

Implement a file that contains code for a class named Vector for your chosen programming language from the following: C++, Java, Python.

 

The following must be found in the code: (For function definitions, only function names need to be followed. Variable names can be changed and are only added as an example.)

1. A proper implementation of a vector function via the usage of a List-like data structure. (5 points)
  1. A proper implementation of a default constructor that initializes the vector as a zero vector of a given dimension.
    1. Constructor definition to be used: Vector (int dimension)
  2. A proper implementation of a constructor, converting an already-existing array/list of data from a rudimentary data structure into the vector class.
    1. Constructor definition to be used: Vector (double[ ] array, int dimension)
  3. The usage of an Array/List-like structure to store the Vector data.
  4. The usage of an immutable Integer variable to hold a value for Vector dimension.
  
  DONE
  
2. An implementation of functions for vector scaling and vector addition. (10 points)
  1.A proper implementation of a function for vector scaling.
    1. Function header to be used: Vector scale (int scalar)
    2. Usage example: Assuming a Vector v and int b exists, v.scale(b) should scale the elements of v by b and return the scaled vector v. The elements inside v must be changed and be correctly scaled by b.
  2. A proper implementation of a function for vector addition. Errors for size mismatches when adding vectors must also be handled.
    1. Function header to be used: Vector add (Vector addend)
    2. Usage example: Assuming both Vector v and w exist, v.add(w) should return the vector sum between v and w. The elements inside v must be changed and be a correct result of the operation of Vector addition between v and w.
    
    DONE 
    
3. An implementation of a function that performs Gauss-Jordan Elimination on a given set of vectors. (30 points)
  1. The function must be static-like in nature, and must be callable from the Vector class. See usage example for more details.
  2. Function header to be used: Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants)
  3. The function must be a proper implementation of Gauss-Jordan Elimination, which reduces the given list of vectors into unit vectors via row operations.
 
 SCALE AND ADD -  DONE
 
  4. Usage example: Given a list of vectors vecList, an integer dim, and a Vector c, Vector.Gauss_Jordan (vecList, dim, c) should return a Vector containing the solution to the corresponding system of linear equations. Ex. [x y z w] = [2 1 3 5]
  
  DONE
  
  5. If an unsolvable configuration is given (e.g. an input with only [2 1] in vectors and [2 3] in constants), the function must return a null pointer instead of the solution Vector to denote no solution.
 
4. An implementation of a function that calculates the span of a list of vectors. (5 points)
  1. The function must be static-like in nature, and must be callable from the Vector class. See usage example for more details.
  2. Function header to be used: int span (List<Vector> vectors, int dimension)
  3. The function must call the Gauss_Jordan function within it; i.e. the calculation for span must include Gauss-Jordan Elimination.
  4. Usage example: Given a list of Vectors vecList, and an integer dim denoting the dimension of a vector inside vecList, Vector.span(vecList, dim) should return an integer variable containing the span of the set of vectors.
 

Other instructions:

1. The students in the group must sign their code with a comment containing their names and the ADVDISC section above the class header.
2. Checking will be done via black-box testing; the above functions will be called and test cases will be run. The returned output will be recorded and compared to each test case. Each test case has a point value, getting a perfect output on all of the test cases returns a perfect score for the specific section. There will be no partial points for only partially correct answers.
3. Only the Vector class file should be submitted, any other files submitted along with the Vector class will not be considered.
4. Any unfollowed instruction may warrant deductions from the final score.
5. If any instance of cheating is caught (copied code from online sources/other classmates, usage of dedicated linear algebra libraries, ) all points are immediately forfeit and the student(s) involved may face a Discipline Office case, depending on the severity of the incident.
6. You may submit late, until August 4, but any submission marked as late (submitted after 11:59:59PM July 31), will receive an unarguable 30% reduction in score. 
