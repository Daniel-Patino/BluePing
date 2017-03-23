import java.util.Random;

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
	public Random rand;
	public String[] keys = {"12-34-56-78-90", "23-45-67-89-01", "34-56-78-90-12", "45-67-89-01-23",
							"12:34:56:78:90", "23:45:67:89:01", "34:56:78:90:12", "45:67:89:01:23",
							"12:34:56-78-90", "23:45:67-89-01", "34:56:78-90-12", "45:67:89-01-23"};
	
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
		definePane();
		
		Circle[] circles = centerCircleArr(10, Color.BLUE);
		Shape[][] nodes = nodes(nodesVisible, this.keys);
		
		for(int i = 0; i < trilaterate.hashSize(); i++){
			coordinatePane.getChildren().add(circles[i]);
			coordinatePane.getChildren().addAll(nodes[i][0], nodes[i][1], nodes[i][2]);
		}
	}
	
	/**
	 * 
	 * @param nodesVisible
	 * @return
	 */
	private Shape[][] nodes(boolean nodesVisible, String[] keys)
	{
		Shape[][] nodes = new Shape[trilaterate.hashSize()][3];

		for(int i = 0; i < trilaterate.hashSize(); i++){
			
			Circle node1 = new Circle(trilaterate.node1[0], trilaterate.node1[1], trilaterate.idToDistances.get(keys[i])[0]);
			Circle node2 = new Circle(trilaterate.node2[0], trilaterate.node2[1], trilaterate.idToDistances.get(keys[i])[1]);
			Circle node3 = new Circle(trilaterate.node3[0], trilaterate.node3[1], trilaterate.idToDistances.get(keys[i])[2]);
			
			Rectangle rect = new Rectangle(750, 750);
			
			Shape node1Bound = Shape.intersect(rect, node1);
			Shape node2Bound = Shape.intersect(rect, node2);
			Shape node3Bound = Shape.intersect(rect, node3);
			
			node1Bound = defineNodes(node1Bound, nodesVisible);
			node2Bound = defineNodes(node2Bound, nodesVisible);
			node3Bound = defineNodes(node3Bound, nodesVisible);
			
			nodes[i][0] = node1Bound;
			nodes[i][1] = node2Bound;
			nodes[i][2] = node3Bound;
		}

		return nodes;
	}
	
	/**
	 * 
	 * @param cirSize
	 * @param color
	 * @return
	 */
	private Circle[] centerCircleArr(int cirSize, Color color)
	{
		Circle[] positions = new Circle[pos.length];
		for(int i = 0; i < positions.length; i++){
			Color randomColor = new Color((Math.random()), Math.random(), Math.random(), 1);
			positions[i] = new Circle(pos[i].getEntry(0), pos[i].getEntry(1), cirSize, randomColor);
		}
		
		return positions;
	}
	
	/**
	 * 
	 * @param node
	 * @param nodesVisible
	 * @return
	 */
	private Shape defineNodes(Shape node, boolean nodesVisible)
	{
		node.setStroke(Color.RED);
		node.setStrokeWidth(3);
		node.setFill(null);
		node.setVisible(nodesVisible);

		return node;
	}
	
	/**
	 * Method to keep the constructor clean, will define the design of the pane
	 */
	private void definePane()
	{
		coordinatePane = new Pane();
		coordinatePane.setMaxSize(750, 750);
		coordinatePane.setStyle("-fx-border-color: black;");
	}

	/* Setters and Getters */
}
