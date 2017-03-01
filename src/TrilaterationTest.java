import org.apache.commons.math3.linear.RealVector;

public class TrilaterationTest {

	
	//public double[] distances = new double[] {375, 530.33, 530.33};
	//double[] node1 = new double[] {375, 0, 0};
	//double[] node2 = new double[] {0, 750, 0};
	//double[] node3 = new double[] {750, 750, 0};
	
	public RealVector[] trilateration3DExact(double[][] node1, double[][] node2, double[][] node3, double[][] distances) throws Exception{
		RealVector[] positions = createArrayOfNodesAndCircles(node1, node2, node3, distances);
	    return positions;
	}
	
	private RealVector[] createArrayOfNodesAndCircles(double[][] node1, double[][] node2, double[][] node3, double[][] distances)
	{
		if(node1.length == node2.length && node2.length == node3.length){
			
			RealVector[] positions = new RealVector[node1.length];
			
			for(int i = 0; i < node1.length; i++){
				double[][] positionArray = new double[][]{node1[i], node2[i], node3[i]};
				TrilaterationFunction trilatertionFunction = new TrilaterationFunction(positionArray, distances[i]);
				LinearLeastSquaresSolver lSolver = new LinearLeastSquaresSolver(trilatertionFunction);
				
				positions[i] = lSolver.solve();
			}
			
			return positions;
		}
		
		return null;
	}
}