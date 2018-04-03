package matlabjava05;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.engine.MatlabExecutionException;
import com.mathworks.engine.MatlabSyntaxException;

public class MatlabJava05_lib {

	Future<MatlabEngine> eng;
	MatlabEngine ml;
	private double data[][];
	
	public MatlabJava05_lib(double[][] data) {
		this.data = data;
		this.eng = MatlabEngine.startMatlabAsync();
		try {
			ml = eng.get();
			ml.putVariableAsync("data", data);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//https://jp.mathworks.com/help/matlab/ref/summary.html
	//https://jp.mathworks.com/help/matlab/ref/table.html
	public double[][] getSummary() {
		double result[][] = new double[3][data[0].length];
		try {
			ml.eval("data = table(data(:,:));"); //table 配列に変換
			ml.eval("summary(data);");
			ml.eval("s = summary(data);"); //構造体を取得
			ml.eval("min = s.Var1.Min");
			ml.eval("median = s.Var1.Median");
			ml.eval("max = s.Var1.Max");
			Future<double[]> futureEval_min = ml.getVariableAsync("min");
			result[0] = futureEval_min.get();
			Future<double[]> futureEval_median = ml.getVariableAsync("median");
			result[1] = futureEval_median.get();
			Future<double[]> futureEval_max = ml.getVariableAsync("max");
			result[2] = futureEval_max.get();
			
		} catch (MatlabExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatlabSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CancellationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
