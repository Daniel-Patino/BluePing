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
		
		Circle yoshi = new Circle(75, 750/2, 10, Color.LIGHTGREEN);
		Circle flash = new Circle(235, 375, 10, Color.DARKRED);
		Circle megaman = new Circle(235, 300, 10, Color.ORANGE);
		Circle spiderman = new Circle(570, 170, 10, Color.RED);
		Circle batjoker = new Circle(675, 100, 10, Color.GREEN);
		Circle dano = new Circle(630, 630, 10, Color.PURPLE);

		coordinatePane.getChildren().addAll(yoshi, flash, megaman, spiderman, batjoker, dano);
		
		Circle[] circles = centerCircleArr(10, Color.BLUE);
		
		for(int i = 0; i < circles.length; i++){
			coordinatePane.getChildren().add(circles[i]);
		}
	}
	
	/**
	 * 
	 * @param nodesVisible
	 * @return
	 */
	private Shape[] nodes(boolean nodesVisible)
	{
		
		Shape[] nodes = new Shape[3];
		
		Circle node1 = new Circle(trilaterate.node1[0], trilaterate.node1[1], trilaterate.distance[0]);
		Circle node2 = new Circle(trilaterate.node2[0], trilaterate.node2[1], trilaterate.distance[1]);
		Circle node3 = new Circle(trilaterate.node3[0], trilaterate.node3[1], trilaterate.distance[2]);
		
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
	private Circle[] centerCircleArr(int cirSize, Color color)
	{
		Circle[] positions = new Circle[pos.length];
		for(int i = 0; i < positions.length; i++){
			positions[i] = new Circle(pos[i].getEntry(0), pos[i].getEntry(1), cirSize, color);
		}
		
		return positions;
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
