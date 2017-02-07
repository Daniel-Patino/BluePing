import org.apache.commons.math3.linear.RealVector;

public class TrilaterationTest {

	
	public double[] distances = new double[] {375, 530.33, 530.33};
	double[] node1 = new double[] {375, 0, 0};
	double[] node2 = new double[] {0, 750, 0};
	double[] node3 = new double[] {750, 750, 0};
	
	public RealVector trilateration3DExact() throws Exception{
		double[][] positions = new double[][] {node1, node2, node3};
		double[] distances = new double[] {this.distances[0], this.distances[1], this.distances[2]};
		
		TrilaterationFunction trilatertionFunction = new TrilaterationFunction(positions, distances);
		LinearLeastSquaresSolver lSolver = new LinearLeastSquaresSolver(trilatertionFunction);
		
	    RealVector x = lSolver.solve();
	    System.out.println(x.getEntry(0));
	    System.out.println(x.getEntry(1));
	    System.out.println(x.getEntry(2));
	    return x;
	}
	
	public static void main(String[] args) throws Exception {
		TrilaterationTest test = new TrilaterationTest();
		test.trilateration3DExact();
	}
}