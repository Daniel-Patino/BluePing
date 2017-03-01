import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * This class creates the scene for details regarding the application
 * the details will be placed to the left of the CoordinateScene and
 * will contain three tabs at the top which will give the user control
 * over the information to be displayed.
 * 
 * The three tabs will be;
 * 1. Pings (A list of MAC Addresses)
 * 2. (Empty)
 * 3. (Empty)
 * 
 * @author Daniel Patino
 * @author Danny Suarez
 */
public class DetailsScene {

	private HBox optionsTabs = new HBox();
	private BorderPane detailsScene = new BorderPane();	
	private GridPane someRandomPane = new GridPane();

	public Button[] buttons;
	
	/**
	 * The constructor will create a DetailsScene with the parameters
	 */
	public DetailsScene(int numberOfButtons){
		buttons = buttons(numberOfButtons);
		optionsTabs.getChildren().addAll(buttons);
		detailsScene.setTop(optionsTabs);
		detailsScene.setMaxSize(Main.WIDTH - 750, Main.HEIGHT - (Main.HEIGHT - 750));
		detailsScene.setStyle("-fx-border-color: red;");
		
		someRandomPane.setStyle("-fx-border-color: green;");
		someRandomPane.setMaxSize(Main.WIDTH - 775, Main.HEIGHT - (Main.HEIGHT - 700));
		
		someEvent();
		
		detailsScene.setCenter(someRandomPane);
	}
	
	/**
	 * Creates the buttons for the HBox
	 * 
	 * @param numberOfButtons
	 * @return
	 */
	private Button[] buttons(int numberOfButtons){
		
		Button[] buttons = new Button[numberOfButtons];
		
		buttons[0] = new Button("Customers");
		buttons[1] = new Button("Location");
		buttons[2] = new Button("Duration");
		
		for(int i = 0; i < numberOfButtons; i++){
			//buttons[i] = new Button("Customers");
			buttons[i].setPrefWidth((Main.WIDTH - 750) / numberOfButtons);
		}
		
		return buttons;
	}
	
	private void someEvent()
	{
		buttons[0].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
			Text text1 = new Text("Customer One: ");
			Text text2 = new Text("Customer Two: ");
			Text text3 = new Text("Customer Three: ");
			Text text4 = new Text("Customer Four: ");
			Text text5 = new Text("Customer Five: ");
			Text text6 = new Text("Customer Six: ");
			someRandomPane.add(text1, 0, 0);
			someRandomPane.add(text2, 0, 1);
			someRandomPane.add(text3, 0, 2);
			someRandomPane.add(text4, 0, 3);
			someRandomPane.add(text5, 0, 4);
			someRandomPane.add(text6, 0, 5);
		});
		
		buttons[1].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: orange; -fx-border-width: 10;");
			if(someRandomPane.getChildren().size() != 0){
				someRandomPane.getChildren().remove(0);
			}
		});
		
		buttons[2].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: red; -fx-border-width: 15;");
		});
	}
	
	/* Setters and Getters */
	
	public HBox getOptionsTabs() {
		return optionsTabs;
	}

	public void setOptionsTabs(HBox optionsTabs) {
		this.optionsTabs = optionsTabs;
	}

	public BorderPane getDetailsScene() {
		return detailsScene;
	}

	public void setDetailsScene(BorderPane detailsScene) {
		this.detailsScene = detailsScene;
	}
	
	public Pane getSomeRandomPane() {
		return someRandomPane;
	}

	public void setSomeRandomPane(GridPane someRandomPane) {
		this.someRandomPane = someRandomPane;
	}
}