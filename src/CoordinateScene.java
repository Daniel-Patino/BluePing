import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.math3.linear.RealVector;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 * 
 * @author Daniel
 *
 */
public class CoordinateScene
{
	private TrilaterationTest trilaterate;
	private Database db;
	private RealVector[] pos;
	
	public Pane coordinatePane;
	public Random rand;
	
	/**
	 * 
	 * 
	 * @param trilaterate
	 * @param pos
	 * @param nodesVisible
	 * @throws Exception 
	 */
	public CoordinateScene(boolean nodesVisible) throws Exception
	{
		//Database dbtest = DBHandler.connectDatabase();
        TrilaterationTest test = new TrilaterationTest();
        //test = DBHandler.retrieveData(dbtest);
        RealVector[] dots = test.trilateration3DExact();
		
		this.trilaterate = test;
		this.pos = dots;
		//this.db = dbtest;

		definePane();
		//custEvent();
		bunchODots();
		//animation();
	}

	public void custEvent()
	{
		Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		                try {
		                	while(coordinatePane.getChildren().size() != 0) {
                				coordinatePane.getChildren().remove(0);
                			}
                            trilaterate = DBHandler.retrieveData(db);
                            pos = trilaterate.trilateration3DExact();
                            Circle[] circles = centerCircleArr(10, Color.BLUE);
                            Shape[][] nodes = nodes(false, trilaterate.idToDistances);

                            for(int i = 0; i < trilaterate.hashSize(); i++) {
                                coordinatePane.getChildren().add(circles[i]);
                                coordinatePane.getChildren().addAll(nodes[i][0], nodes[i][1], nodes[i][2]);
                            }                        
                        } catch (Exception e) {
		                    e.printStackTrace();
                        }
		            }
		        });
		    }
		}, 100, 1000);
	}
	
	public void bunchODots()
	{
		Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		                try {
		                	if(coordinatePane.getChildren().size() < 15) {
			                	Random rand = new Random();
			                	int x = rand.nextInt(700);
			                	int y = rand.nextInt(700);
			                	
			                	Color randomColor = new Color((Math.random()), Math.random(), Math.random(), 1);
			                	Circle newCircle = new Circle(x, y, 10, randomColor);
			                	
			                	String temp = new String();
			                	if(x < 350 && y < 350){
			                		temp = "Quadrant I";
			                	} else if(x < 350 && y >= 350) {
			                		temp = "Quadrant II";
			                	} else if(x >= 350 && y >= 350) {
			                		temp = "Quadrant III";
			                	} else {
			                		temp = "Quadrant IV";
			                	}
			                	
			                	Text[] newEntry = {
			                			new Text(Integer.toString(coordinatePane.getChildren().size() + 1)),
			                			new Text("X:" + x + " Y: " + y),
			                			new Text(temp)
			                	};
			                	
			            		DetailsScene.customerInfo.add(newEntry);
			            		DetailsScene.fillChart();
			                	
			            		coordinatePane.getChildren().add(newCircle);	
		                	} else {
		                		// Do Nothing
		                	}
                        } catch (Exception e) {
		                    e.printStackTrace();
                        }
		            }
		        });
		    }
		}, 100, 100);
	}
	
	/**
	 * 
	 * @param nodesVisible
	 * @return
	 */
	private Shape[][] nodes(boolean nodesVisible, HashMap<String, double[]> values)
	{
		Shape[][] nodes = new Shape[trilaterate.hashSize()][3];

		int i = 0;
		
		for (Entry<String, double[]> entry : trilaterate.idToDistances.entrySet()) {
			
			double[] nodeDistances = entry.getValue();
			
			Circle node1 = new Circle(trilaterate.node1[0], trilaterate.node1[1], nodeDistances[0]);
			Circle node2 = new Circle(trilaterate.node2[0], trilaterate.node2[1], nodeDistances[1]);
			Circle node3 = new Circle(trilaterate.node3[0], trilaterate.node3[1], nodeDistances[2]);
			
			Rectangle rect = new Rectangle(700, 700);
			
			Shape node1Bound = Shape.intersect(rect, node1);
			Shape node2Bound = Shape.intersect(rect, node2);
			Shape node3Bound = Shape.intersect(rect, node3);
			
			node1Bound = defineNodes(node1Bound, nodesVisible);
			node2Bound = defineNodes(node2Bound, nodesVisible);
			node3Bound = defineNodes(node3Bound, nodesVisible);
			
			nodes[i][0] = node1Bound;
			nodes[i][1] = node2Bound;
			nodes[i][2] = node3Bound;
			
			i++;
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
		coordinatePane.setMaxSize(700, 700);
		coordinatePane.setStyle("-fx-border-color: black;");
	}

	public void animation()
	{
		int x, y;
		x = 0;
		y = 375;

		Circle car = new Circle(x, y, 10);
        coordinatePane.getChildren().add(car);

        Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						try {
							car.setTranslateX(car.getTranslateX() + 62.5);
							car.setTranslateY(car.getTranslateY());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}, 5000, 750);
	}

	/* Setters and Getters */
}
