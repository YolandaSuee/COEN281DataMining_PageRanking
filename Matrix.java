

import java.lang.reflect.Array;
import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac Matrix.java
 *  Execution:    java Matrix
 *
 *  A bare-bones collection of static methods for manipulating
 *  matrices.
 *
 ******************************************************************************/

public class Matrix {

    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = Math.random();
        return C;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++)
            I[i][i] = 1;
        return I;
    }

    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return C = A^T
    public static double[][] transpose(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[j][i] = A[i][j];
        return C;
    }

    // return C = A + B
    public static double[][] add(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // return C = A - B
    public static double[][] subtract(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    // return multiplication C = A * beta;
    public static double[][] multiply(double[][] A, double beta) {
    	int mA = A.length;
    	int nA = A[0].length;
    	double[][] C = new double[mA][nA];
    	for(int i = 0; i < mA; i++) {
    		for(int j = 0; j < nA; j++) {
    			C[i][j] = A[i][j] * beta;
    		}
    	}
    	return C;
    }
    
    // return C = A * B
    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += A[i][j] * x[j];
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] A) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += A[i][j] * x[i];
        return y;
    }
    
    // remove the sink nodes
    public static double[][] removalSinkNodes(double[][]A) {
    	int row = A.length;
    	int column = A[0].length;
    	System.out.println("input matrix has " + row + "rows and " + column + "columns. ");
    	
    	double[][] C = new double[row - 1][column - 1];
    	int i = 0, j = 0;
    	boolean found = false;
    	
    	int REMOVE_ROW_COLUMN = 0;
    	int m = 0, n = 0;		// input matrix A
    	int p = 0, q = 0;		// new matrix C
    	
    	for( j = 0; j < column && !found; j++ ) {			 
    		for( i = 0; i < row; i++ ) {	 				
    			if( i != j && A[i][j] != 0.0 ) {
    				System.out.println("1i: " + i + ", j:  " + j);
    				break;
    			}
    			else if( i == row - 1 ) {
    				// find a sink node in column (j-1) which means the node (j-1) is a sink node or dead node; jump outer for loop
    				found = true;
        			System.out.println("found.");
        			System.out.println("2i: " + i + ", j:  " + j);
        			break;
    			}
    			else {
    				System.out.println("3i: " + i + ", j:  " + j);
    			}
    		}
    		/**
    		System.out.println("m: " + m);
    		if( i == m-1 ) {
    			// find a sink node in column j which means the node j is a sink node or dead node; jump outer for loop
    			found = true;
    			System.out.println("found.");
    			break;
    		}
    		**/
    	}
    	
    	if( found ) {
    		// remove line (j-1) and column (j-1) from matrix A, and assign the new (row-1)*(column-1) matrix to matrix C
        	REMOVE_ROW_COLUMN = j - 1;
        	System.out.println("REMOVE_ROW_COLUMN:" + REMOVE_ROW_COLUMN);
        	for(m = 0; m < row; m++) {
                if (m == REMOVE_ROW_COLUMN) {
                    continue;
                }
                q = 0;
                for(n = 0; n < column; n++) {
                    if (n == REMOVE_ROW_COLUMN) {
                        continue;
                    }
                    C[p][q] = A[m][n];
                    q++;
                }
                p++;
            }
        	return C;
    	}
    	else {
    		return A;
    	}
    }

	// test client
    public static void main(String[] args) {
    	double beta = 0.8;
    	double n = 4;
    	
    	StdOut.println("V0");
        StdOut.println("--------------------");
        double[][] v0 = { { 1/n }, { 1/n }, { 1/n }, { 1/n }, { 1/n }, { 1/n } };
        StdArrayIO.print(v0);
        StdOut.println();
        
        StdOut.println("M");
        StdOut.println("--------------------");
        double[][] m = { { 1.0/3, 0, 0, 0, 1.0/3, 0 }, { 0, 1, 1.0/2, 0, 0, 0 }, { 0, 0, 0, 0, 1.0/3, 0 }, { 0, 0, 1.0/2, 1, 0, 0 }, { 1.0/3, 0, 0, 0, 0, 1.0/2 }, { 1.0/3, 0, 0, 0, 1.0/3, 1.0/2 } };
        StdArrayIO.print(m);
        StdOut.println();
        
        StdOut.println("beta * M");
        StdOut.println("--------------------");
        double[][] g = Matrix.multiply(m, beta);
        StdArrayIO.print(g);
        StdOut.println();
        
        StdOut.println("betaM * V0");
        StdOut.println("--------------------");
        double[][] betaMV0 = Matrix.multiply(g, v0);
        StdArrayIO.print(betaMV0);
        StdOut.println();
        
        StdOut.println("(1 - beta) * V");
        StdOut.println("--------------------");
        double[][] h = Matrix.multiply(v0, (1 - beta));
        StdArrayIO.print(h);
        StdOut.println();
        
        StdOut.println("betaMV + (1 - beta) * V");
        StdOut.println("--------------------");
        double[][] e = Matrix.add(betaMV0, h);
        StdArrayIO.print(e);
        StdOut.println();
            

        if(m.length != removalSinkNodes(m).length){
        	StdArrayIO.print( removalSinkNodes(m) );
            StdOut.println();
        	
        }        
    }
}
