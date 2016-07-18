

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P1 {

	public static void main(String[] args) {

		//StdOut.print("Please enter the absolute path of the file: ");
    	//String fileName = StdIn.readString();
		//StdOut.println("file name: " + fileName);


    	/*try {
    		StdIn.setScanner(new Scanner(new java.io.BufferedInputStream(new FileInputStream(new File(fileName))), "UTF-8"));


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
	        StdOut.println("Throw exception: " + e.getMessage());
	        System.exit(0);
		}*/
		/*
		try {
			//StdIn.setScanner(new Scanner(new FileInputStream("/Users/YolandaSuee/Desktop/t10.dat")));
		}
		catch (FileNotFoundException ex) {

		}
		*/
		StdIn.setScanner(new Scanner(System.in));

        String s = StdIn.readAll();

        //StdOut.println();
		//StdOut.println("Primitive matrix: ");
        //StdOut.println(s);
        //StdOut.println();
        StringTokenizer lineToken = new StringTokenizer(s, "\n\r");
        List<String> strList = new ArrayList<String>();
        int rowCount = 0;
        int colCount = 0;
        while(lineToken.hasMoreTokens()){
        	String str = lineToken.nextToken();
        	if(str.trim().startsWith("#")){
        		continue;
        	}
        	if(str.indexOf("x") > 0){
        		String[] sA = str.trim().split("x");
        		try{
	        		rowCount = Integer.parseInt(sA[0].trim());
	        		colCount = Integer.parseInt(sA[1].trim());
        		}catch (NumberFormatException e){
        			StdOut.println(e.getMessage());
        			StdOut.println("Please enter the correct number of rows and columns of a matrix");
        			System.exit(0);
        		}
        	}else{
        		if(str.trim().length() > 0)
        			strList.add(str);
        	}
        }
        if(rowCount == 0 || colCount ==0){
			StdOut.println("Please enter the correct number of rows and columns of a matrix");
			System.exit(0);
        }
        Rational[][] rat = new Rational[rowCount + 1][colCount + 1];
        
		Pattern pattern_number = Pattern.compile("[0-9]+(\\.[0-9]*)?");
		Pattern pattern_fenshu = Pattern.compile("[0-9]+/[0-9]+");
        for(int i = 0; i < strList.size(); i++){
        	String str = strList.get(i);
        	if(str.trim().length() == 0)
        		continue;
        	StringTokenizer st = new StringTokenizer(str);
        	if(colCount != st.countTokens()){
    			StdOut.println("The number of columns should be : " + colCount);
    			StdOut.println("Error line : " + str);
    			System.exit(0);
        	}
        	
        	int colNum = 1;
        	while(st.hasMoreTokens()){
                String ss = st.nextToken();
                Matcher matcher1 = pattern_number.matcher(ss);
                Matcher matcher2 = pattern_fenshu.matcher(ss);
                if(!matcher1.matches() && !matcher2.matches()){
        			StdOut.println("The element is neither rational number nor floating number");
        			StdOut.println("Error element : " + str);
                }
                if(ss.indexOf("/") >= 0){
                	String[] strArr = ss.split("/");
                	rat[i + 1][colNum] = new Rational(Long.parseLong(strArr[0]), Long.parseLong(strArr[1]));
                }else if(ss.indexOf(".") >= 0){
                	String[] strArr = ss.split("\\.");
                	long multiple = 1;
                	for(int j = 1; j <= strArr[1].length(); j++){
                		multiple *= 10;
                	}
                	rat[i + 1][colNum] = new Rational((long)(Double.parseDouble(ss) * multiple), multiple);
                }else{
                	rat[i + 1][colNum] = new Rational(Long.parseLong(ss), 1);
                }
//            	rat2[rowNum][colNum] = rat[rowNum][colNum];
                colNum ++;
        	}
        }
        

        

        //StdOut.println("Output:");
        
        /*  DELETE  */

        Rational  r = new Rational();
        XiaoShu n = new XiaoShu();
        /*sink node*/
        Rational[][] rat1 = r.delete_sink_node(rat);

        Rational[][] rat2 = r.HeSuan(rat1);
        //r.print_FenShu(rat2);
        
        


        Rational[][] rat3 = rat2;
         Rational[][] v1 = r.Return_e_r(rat3); 
         Rational[][] v2 ;
         double[][] d1 = null;
         double[][] d2 = null;
         String str = "0.875";
//
//         n.StringToXiaoShu(str);
//         System.out.println("*******************************");
//         r.print_FenShu(n.xiaoshuToFenshu(str));
//         n.xiaoshuToFenshu(str);
         int cc = 1;
         boolean config = true;
//         boolean config = false;
         double[][] f_rat2 = null;
         double[][] f_v1 = null;
         double[][] f_v2 = null;
         double f_str = 0;
         
         while(config){
        	 if(cc<6){
        		 System.out.println("--  Iteration "+cc +":");
        		 v2 = r.leap_XV(rat3, v1, str);
        		 d1 = n.FenshuToXiaoShu(v1);
        		 d2 = n.FenshuToXiaoShu(v2);
        		 v1 = v2;
//        		 r.FenShuToXiaoshu(v2);
        		 r.print_FenShu(v2);
//        		 r.print_XiaoShu(r.FenShuToXiaoshu(v2));

        		 if(n.IsSame(d1, d2)){
        			 config=false;
        		 } 
        	 }else if(cc==6){
        		 System.out.println("--  Iteration "+cc +":");
        		 f_rat2 = r.FenShuToXiaoshu(rat3);
        		 f_v1 = r.FenShuToXiaoshu(v1);
        		 f_str = n.StringToXiaoShu(str);
        		 f_v2 = r.leap_XV(f_rat2, f_v1, f_str);
        		 
        		 if(n.IsSame(f_v1, f_v2)){
        			 config=false;
        		 }
        		 f_v1 = f_v2;
        		 r.print_XiaoShu(f_v2);
        		 
        		 
        	 }else{
        		 System.out.println("--  Iteration "+cc +":");
        		 
        		 f_v2 = r.leap_XV(f_rat2, f_v1, f_str);
        		 
//        		 if(cc>30){
        		 if(n.IsSame(f_v1, f_v2)){
        			 config=false;
        		 }
        		 f_v1 = f_v2;	
        		 r.print_XiaoShu(f_v2);

        		
        	 }
        	 cc = cc+1;
        	 
         }
   
	}

}
