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
			ml.eval("scatter(data(:,6),data(:,5));");
			ml.eval("xlabel('Number of House');");
			ml.eval("ylabel('Population of 80 over');");
			ml.eval("title('Population of 80 over for house')");
			ml.eval("pause(5);");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//6列目:世帯数、5列目:80over
	public void getRegression() {
		try {
			ml.eval("mdl = fitlm(data(:,6),data(:,5));");
			ml.eval("yPred = predict(mdl,data(:,6));");
			ml.eval("plot(data(:,6),data(:,5),'.');");
			ml.eval("hold on");
			ml.eval("plot(data(:,6), yPred, '.-');");
			ml.eval("legend('All Data','Predicted Response');");
			ml.eval("xlabel('Number of House');");
			ml.eval("ylabel('Population of 80 over');");
			ml.eval("title('Population of 80 over for house')");
			ml.eval("pause(5);");
			ml.eval("saveas(gcf,'regression.png')");
			ml.eval("hold off");
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

	}
	
}
