package matlabjava05;

import java.util.Arrays;

public class MatlabJava05_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//csv取り込み
		MySQL mysql = new MySQL(1); //csv取り込み
		double data[][] = new double[831][6];
		data = mysql.getCSV("csv/population.csv", 831, 6);
		System.out.println("selectData = "+Arrays.deepToString(data));
		//csv取り込みここまで
		
		MatlabJava05_lib mlib = new MatlabJava05_lib(data); 
		double [][]summary = mlib.getSummary();
		System.out.println("Summary = "+Arrays.deepToString(summary));
	}

}
