import org.apache.commons.math3.linear.RealVector;

public class TrilaterationTest {

	
	public double[] distance = new double[] {375, 530.33, 530.33};
	double[] node1 = new double[] {375, 0, 0};
	double[] node2 = new double[] {0, 750, 0};
	double[] node3 = new double[] {750, 750, 0};
	
	public double[][] distances = new double[][]{{375, 530.33, 530.33},  {100, 600.33, 600.33},  {600, 100.33, 100.33}};
	
	public RealVector[] trilateration3DExact() throws Exception
	{
		double[][] positions = new double[][] {node1, node2, node3};
		RealVector[] x = new RealVector[distances.length];
		
		for(int i = 0; i < distances.length; i++){
			double[] distances = new double[] {this.distances[i][0], this.distances[i][1], this.distances[i][2]};
			TrilaterationFunction trilatertionFunction = new TrilaterationFunction(positions, distances);
			LinearLeastSquaresSolver lSolver = new LinearLeastSquaresSolver(trilatertionFunction);
			x[i] = lSolver.solve();
			
		    System.out.println(x[i].getEntry(0));
		    System.out.println(x[i].getEntry(1));
		    System.out.println(x[i].getEntry(2));
		}
	    return x;
	}
}