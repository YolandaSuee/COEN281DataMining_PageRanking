

import java.io.*;
import java.util.StringTokenizer;

class MatrixMultiplyRA{

   // mult()
   // Return a new matrix which is the product of matrices A and B.
   // pre: #columns(A)=#rows(B)
   static Rational[][] mult(Rational[][] A, Rational[][] B){
      int i, k, j, n, p, q, m;
      n = A.length - 1;		//rows of A
      p = A[0].length - 1;	//columns of A
      q = B.length - 1;		//rows of B
      m = B[0].length - 1;	//rows of B
      if( p!=q ){
         throw new RuntimeException("MatrixMultiplyRA: mult: matrix dimensions do not match");
      }
      Rational[][] P = new Rational[n+1][m+1];
      for(i=1; i<=n; i++){
         for(j=1; j<=m; j++){
            P[i][j] = new Rational(0, 1);
//            for(k=1; k<=p; k++) P[i][j].addEq(A[i][k].mult(B[k][j]));
            for(k=1; k<=p; k++) 
            	P[i][j] = P[i][j].plus(A[i][k].times(B[k][j]));
         }
      }
      return P;
   }

   // printMatrix()
   // print Matrix A to file out
   static void printMatrix(FileOutputStream out, Rational[][] A) throws IOException{
      int n = A.length - 1;
      int m = A[0].length - 1;
      StringBuffer sb = new StringBuffer(n + " " + m + "\n");
      for(int i=1; i<=n; i++){
         for(int j=1; j<=m; j++) sb.append(A[i][j] + " ");
         sb.append("\n");
      }
      out.write(sb.toString().getBytes());
   }

   // main()
   // read input file, build up the product one factor at a time, then append
   // the resulting matrix to the output file.
   public static void main(String[] args) throws IOException {
      /**int n, p, m, i, k, j;
      String line;
      StringTokenizer st;
      Rational[][] Product;
      Rational[][] A;

      // check command line arguments, open input and output files
      if( args.length!=2 ){
         System.out.println("Usage: MatrixMultiplyRA infile outfile");
         System.exit(1);
      }
      BufferedReader in = new BufferedReader(new FileReader(args[0]));
      FileOutputStream out = new FileOutputStream(args[1], true);

      // read in first matrix, get it's dimensions
      st = new StringTokenizer(in.readLine());
      n = Integer.parseInt(st.nextToken());
      p = Integer.parseInt(st.nextToken());

      // allocate Product to be of size (n+1)x(p+1), don't use index 0
      Product = new Rational[n+1][p+1];

      // read in each row
      for(i=1; i<=n; i++){
         st = new StringTokenizer(in.readLine());
         for(k=1; k<=p; k++){
            Product[i][k] = Rational.valueOf(st.nextToken());
         }
      }

      // read in remaining matrices, and build up Product
      st = new StringTokenizer((line=in.readLine())==null?" ":line);
      while(st.countTokens()>=2){ // there is another matrix to read

         // get it's dimensions
         p = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());

         // check that dimensions are compatible
         if( p != (Product[0].length - 1) ){
            throw new RuntimeException("MatrixMultiplyRA: matrix dimensions do not match");
         }

         // allocate A to be of size (p+1)x(m+1), don't use index 0
         A = new Rational[p+1][m+1];

         // read in each row
         for(k=1; k<=p; k++){
            st = new StringTokenizer(in.readLine());
            for(j=1; j<=m; j++){
               A[k][j] = Rational.valueOf(st.nextToken());
            }
         }

         // build up Product by multiplying by A
         Product = mult(Product, A);

         // get dimensiions of the next matrix
         st = new StringTokenizer( (line=in.readLine())==null?" ":line );
      }

      // close input file
      in.close();

      // print Product to output file
      printMatrix(out, Product);

      // close output file
      out.close();*/
   }
}
