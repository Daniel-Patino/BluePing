import org.apache.commons.math3.linear.RealVector;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * 
 * @author Daniel
 *
 */
public class CoordinateScene{

	private TrilaterationTest trilaterate;
	private RealVector[] pos;
	
	public Pane coordinatePane;
	
	/**
	 * 
	 * 
	 * @param trilaterate
	 * @param pos
	 * @param nodesVisible
	 */
	public CoordinateScene(TrilaterationTest trilaterate, RealVector[] pos, boolean nodesVisible)
	{
		this.trilaterate = trilaterate;
		this.pos = pos;
		Shape[] nodes = nodes(nodesVisible);
		setPane();
		coordinatePane.getChildren().addAll(centerCircle(10, Color.BLUE), nodes[0], nodes[1], nodes[2]);
	}
	
	/**
	 * 
	 * @param nodesVisible
	 * @return
	 */
	private Shape[] nodes(boolean nodesVisible)
	{
		
		Shape[] nodes = new Shape[3];
		
		Circle[] node1Arr;
		Circle[] node2Arr;
		Circle[] node3Arr;
		
		for(int i = 0; i < pos.length; i++){
			node1Arr[i] = new Circle(trilaterate.[i][0], trilaterate.node1[i][1], trilaterate.distances[i][0]);
			node2Arr[i] = new Circle();
			node3Arr[i] = new Circle();
		}
		
		Circle node1 = new Circle(trilaterate.node1[0], trilaterate.node1[1], trilaterate.distances[0]);
		Circle node2 = new Circle(trilaterate.node2[0], trilaterate.node2[1], trilaterate.distances[1]);
		Circle node3 = new Circle(trilaterate.node3[0], trilaterate.node3[1], trilaterate.distances[2]);
		
		node1 = defineNodes(node1, nodesVisible);
		node2 = defineNodes(node2, nodesVisible);
		node3 = defineNodes(node3, nodesVisible);
		
		Rectangle rect = new Rectangle(750, 750);
		
		Shape node1Bound = Shape.intersect(rect, node1);
		Shape node2Bound = Shape.intersect(rect, node2);
		Shape node3Bound = Shape.intersect(rect, node3);
		
		nodes[0] = node1Bound;
		nodes[1] = node2Bound;
		nodes[2] = node3Bound;
		
		return nodes;
	}
	
	/**
	 * 
	 * @param cirSize
	 * @param color
	 * @return
	 */
	private Circle centerCircle(int cirSize, Color color)
	{
		return new Circle(pos.getEntry(0), pos.getEntry(1), cirSize, color);
	}
	
	/**
	 * 
	 * @param node
	 * @param nodesVisible
	 * @return
	 */
	private Circle defineNodes(Circle node, boolean nodesVisible)
	{
		node.setStroke(Color.BLACK);
		node.setStrokeWidth(3);
		node.setFill(null);
		node.setVisible(nodesVisible);
		
		return node;	
	}
	
	/**
	 * 
	 */
	private void setPane()
	{
		coordinatePane = new Pane();
		coordinatePane.setMaxSize(750, 750);
		coordinatePane.setStyle("-fx-border-color: red;");
	}
}
