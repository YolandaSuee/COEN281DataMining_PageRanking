


/*************************************************************************
 *  Compilation:  javac StdArrayIO.java
 *  Execution:    java StdArrayIO
 *
 *************************************************************************/

public class StdArrayIO {

    // read a vector from standard input
    public static double[] readDouble1D() {
        int N = StdIn.readInt();
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdIn.readDouble();
        }
        return a;
    }

    // print a vector to standard output
    public static void print(double[] a) {
        int N = a.length;
        StdOut.println(N);
        for (int i = 0; i < N; i++) {
            StdOut.printf("%7.7f ", a[i]);
        }
        StdOut.println();
    }

    // read a matrix from standard input
    public static double[][] readDouble2D() {
        int M = StdIn.readInt();
        int N = StdIn.readInt();
        double[][] a = new double[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdIn.readDouble();
            }
        }
        return a;
    }

    // print the matrix a to standard output
    public static void print(double[][] a) {
        int M = a.length;
        int N = a[0].length;
        StdOut.println(M + " rows " + N + " colums matrix");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                StdOut.printf("%7.7f ", a[i][j]);
            }
            StdOut.println();
        }
    }


}
