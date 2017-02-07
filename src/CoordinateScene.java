import org.apache.commons.math3.linear.RealVector;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CoordinateScene {

	private TrilaterationTest trilaterate;
	private RealVector pos;
	
	public Pane coordinatePane;
	
	public CoordinateScene(TrilaterationTest trilaterate, RealVector pos, boolean nodesVisible){
		this.trilaterate = trilaterate;
		this.pos = pos;	
		Shape[] nodes = nodes(nodesVisible);
		setPane();
		coordinatePane.getChildren().addAll(centerCircle(10, Color.BLUE), nodes[0], nodes[1], nodes[2]);
	}
	
	private Shape[] nodes(boolean nodesVisible){
		
		Shape[] nodes = new Shape[3];
		
		Circle node1 = new Circle(trilaterate.node1[0], trilaterate.node1[1], trilaterate.distances[0]);
		Circle node2 = new Circle(trilaterate.node2[0], trilaterate.node2[1], trilaterate.distances[1]);
		Circle node3 = new Circle(trilaterate.node3[0], trilaterate.node3[1], trilaterate.distances[2]);
		
		node1.setStroke(Color.BLACK);
		node1.setStrokeWidth(3);
		node1.setFill(null);
		node1.setVisible(nodesVisible);
		
		node2.setStroke(Color.BLACK);
		node2.setStrokeWidth(3);
		node2.setFill(null);
		node2.setVisible(nodesVisible);
		
		node3.setStroke(Color.BLACK);
		node3.setStrokeWidth(3);
		node3.setFill(null);
		node3.setVisible(nodesVisible);
						
		Rectangle rect = new Rectangle(750, 750);
		
		Shape node1Bound = Shape.intersect(rect, node1);
		Shape node2Bound = Shape.intersect(rect, node2);
		Shape node3Bound = Shape.intersect(rect, node3);
		
		nodes[0] = node1Bound;
		nodes[1] = node2Bound;
		nodes[2] = node3Bound;
		
		return nodes;
	}
	
	private Circle centerCircle(int cirSize, Color color){
		return new Circle(pos.getEntry(0), pos.getEntry(1), cirSize, color);
	}
	
	private void setPane(){
		coordinatePane = new Pane();
		coordinatePane.setMaxSize(750, 750);
		coordinatePane.setStyle("-fx-border-color: red;");
	}
}
