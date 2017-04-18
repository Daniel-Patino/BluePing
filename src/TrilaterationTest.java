import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.math3.linear.RealVector;

public class TrilaterationTest {

	public double[] node1 = new double[] {350, 0, 0};
	public double[] node2 = new double[] {0, 700, 0};
	public double[] node3 = new double[] {700, 700, 0};

	public HashMap<String, double[]> idToDistances = new HashMap<>();
	
	public RealVector[] trilateration3DExact() throws Exception
	{
		double[][] positions = new double[][] {node1, node2, node3};

		RealVector[] x = new RealVector[idToDistances.size()];
		
		/* Getting ready to go through the entire HashMap */
		Set<Entry<String, double[]>> hashSet = idToDistances.entrySet();
		Iterator<Entry<String, double[]>> iter = hashSet.iterator();
		int i = 0;
		
		/* Begin going through each element in the HashMap */ 
		while(iter.hasNext()){
			@SuppressWarnings("rawtypes")
			Map.Entry masterKey = (Map.Entry)iter.next();
			
			double[] mapDist = (double[]) masterKey.getValue();
			double[] distances = new double[]{mapDist[0], mapDist[1], mapDist[2]};
			
			TrilaterationFunction trilatertionFunction = new TrilaterationFunction(positions, distances);
			LinearLeastSquaresSolver lSolver = new LinearLeastSquaresSolver(trilatertionFunction);
			x[i] = lSolver.solve();
			i++;
		}
		
	    return x;
	}
	
	public int hashSize()
	{
		return idToDistances.size();
	}
}