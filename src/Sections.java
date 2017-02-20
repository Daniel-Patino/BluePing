import javafx.scene.shape.Shape;

/**
 * This class will display the areas of interest on the CoordinateScene
 * it will determine whether trilateration point intersects with these
 * areas. Ultimately this class will return a true or false
 * 
 * @author Daniel
 *
 */
public class Sections
{
	private String id;
	private Shape shape;
	
	/**
	 * Associates a shape with an id
	 * 
	 * @param id
	 * @param shape
	 */
	public Sections(String id, Shape shape)
	{
		this.id = id;
		this.shape = shape;
	}
	
	/* Setters and Getters */
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
