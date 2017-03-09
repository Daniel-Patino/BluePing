import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
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
		formatGridPane();
		someRandomPane.setStyle("-fx-border-color: green;");
		someRandomPane.setMaxSize(Main.WIDTH - 775, Main.HEIGHT - (Main.HEIGHT - 700));
		
		someEvent();
		
		detailsScene.setCenter(someRandomPane);
	}
	
	private void formatGridPane()
	{
		someRandomPane.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
		someRandomPane.setGridLinesVisible(true);
		someRandomPane.setAlignment(Pos.TOP_CENTER);
		someRandomPane.getColumnConstraints().add(new ColumnConstraints((Main.WIDTH - 775) / 3));
		someRandomPane.getColumnConstraints().add(new ColumnConstraints((Main.WIDTH - 775) / 3));
		someRandomPane.getColumnConstraints().add(new ColumnConstraints((Main.WIDTH - 775) / 3));
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
			buttons[i].setPrefWidth((Main.WIDTH - 750) / numberOfButtons);
		}
		
		return buttons;
	}
	
	private void someEvent()
	{
		buttons[0].setOnMouseClicked(e-> {
			
			while(someRandomPane.getChildren().size() != 0){
				someRandomPane.getChildren().remove(0);
			}
			
			someRandomPane.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
			
			Text text1 = new Text("ID: Yoshi");
			Text text2 = new Text("ID: Megaman");
			Text text3 = new Text("Loc: DiscoArea");
			Text text4 = new Text("Loc: Bar");
			Text text5 = new Text("Dur: Bar");
			Text text6 = new Text("Dur: Magical Rainforest");
			
			someRandomPane.add(text1, 0, 0);
			someRandomPane.add(text2, 0, 1);
			someRandomPane.add(text3, 1, 0);
			someRandomPane.add(text4, 1, 1);
			someRandomPane.add(text5, 2, 0);
			someRandomPane.add(text6, 2, 1);
		});
		
		buttons[1].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: orange; -fx-border-width: 10;");
			while(someRandomPane.getChildren().size() != 0){
				someRandomPane.getChildren().remove(0);
			}
		});
		
		buttons[2].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: red; -fx-border-width: 15;");
			while(someRandomPane.getChildren().size() != 0){
				someRandomPane.getChildren().remove(0);
			}
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