

public class XiaoShu {

	public XiaoShu(){

	}
	

	public Rational xiaoshuToFenshu(String d_num) {
		String str = d_num;
		String[]  arrs= str.split("\\.");
		double m = Math.pow(10, arrs[1].length());
		 long M = (long)m;
		 long   N   =   (long) Float.parseFloat(arrs[1]);
		Rational a  = new Rational(N+(M*(long) Float.parseFloat(arrs[0])),M);
		
		return a;
	}

	public double StringToXiaoShu(String d_num) {
		Rational ra = this.xiaoshuToFenshu(d_num);
		double a = this.FenshuToXiaoShu(ra);
		return a;
	}

	public double FenshuToXiaoShu(Rational A) {
		double a =  Double.valueOf((double)A.numerator()/(double)A.denominator());
		return a;
	}
	
	public double[][] FenshuToXiaoShu(Rational[][] A) {
		int count_row = A.length;
		int count_col = A[count_row - 1].length;
		double[][] a = new double[count_row][count_col];
		for(int x = 1; x < count_row; x++){
			for(int y = 1; y < count_col; y++){
						a[x][y] = this.FenshuToXiaoShu(A[x][y]);
			}
		}
		return a;
	}
	
	public boolean IsSame(double[][] A,double[][] B) {
		int Acount_row = A.length;
		int Acount_col = A[Acount_row - 1].length;

		for(int x = 1; x < Acount_row; x++){
			for(int y = 1; y < Acount_col; y++){
				if (Math.abs(A[x][y] - B[x][y]) > 0.000000000001d) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	
	

}
