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
		
		idToDistances.put("12-34-56-78-90", new double[]{10, 30, 50});
		idToDistances.put("23-45-67-89-01", new double[]{20, 30, 40});
		idToDistances.put("34-56-78-90-12", new double[]{30, 60, 90});
		idToDistances.put("45-67-89-01-23", new double[]{40, 80, 120});
		
		idToDistances.put("12:34:56:78:90", new double[]{50, 100, 150});
		idToDistances.put("23:45:67:89:01", new double[]{160, 260, 360});
		idToDistances.put("34:56:78:90:12", new double[]{470, 170, 370});
		idToDistances.put("45:67:89:01:23", new double[]{180, 180, 380});
		
		idToDistances.put("12-34-56-78-90", new double[]{90, 90, 90});
		idToDistances.put("23-45-67-89-01", new double[]{100, 100, 100});
		idToDistances.put("34-56-78-90-12", new double[]{200, 200, 200});
		idToDistances.put("45-67-89-01-23", new double[]{300, 300, 300});
		
		idToDistances.put("1234:56-78-90", new double[]{450, 300, 100});
		idToDistances.put("2345:67-89-01", new double[]{500, 500, 100});
		idToDistances.put("3456:78-90-12", new double[]{600, 100, 300});
		idToDistances.put("4567:89-01-23", new double[]{700, 300, 450});
		
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