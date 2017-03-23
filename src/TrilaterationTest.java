import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.math3.linear.RealVector;

public class TrilaterationTest {

	public double[] node1 = new double[] {375, 0, 0};
	public double[] node2 = new double[] {0, 750, 0};
	public double[] node3 = new double[] {750, 750, 0};

	public HashMap<String, double[]> idToDistances = new HashMap<>();
	
	public RealVector[] trilateration3DExact() throws Exception
	{
		double[][] positions = new double[][] {node1, node2, node3};
		
		idToDistances.put("12-34-56-78-90", new double[]{10, 20, 30});
		idToDistances.put("23-45-67-89-01", new double[]{100, 200, 300});
		idToDistances.put("34-56-78-90-12", new double[]{300, 400, 500});
		idToDistances.put("45-67-89-01-23", new double[]{50, 70, 120});
		
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