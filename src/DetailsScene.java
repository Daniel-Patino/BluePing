import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
	public ArrayList<Text[]> customerInfo = new ArrayList<>();
	private boolean flagID = true;
	
	/**
	 * The constructor will create a DetailsScene with the parameters
	 */
	public DetailsScene(int numberOfButtons){
		buttons = buttons(numberOfButtons);
		optionsTabs.getChildren().addAll(buttons);
		detailsScene.setTop(optionsTabs);
		detailsScene.setMaxSize(Main.WIDTH - 700, Main.HEIGHT - (Main.HEIGHT - 700));
		detailsScene.setStyle("-fx-border-color: red;");
		formatGridPane();
		someRandomPane.setStyle("-fx-border-color: green;");
		someRandomPane.setMaxSize(Main.WIDTH - 725, Main.HEIGHT - (Main.HEIGHT - 650));
		
		Text[] customerTextA = {new Text("A"), new Text("E"), new Text("D")};
		Text[] customerTextB = {new Text("B"), new Text("D"), new Text("E")};
		Text[] customerTextC = {new Text("C"), new Text("C"), new Text("A")};
		Text[] customerTextD = {new Text("D"), new Text("B"), new Text("C")};
		Text[] customerTextE = {new Text("E"), new Text("A"), new Text("B")};
		
		customerInfo.add(customerTextA);
		customerInfo.add(customerTextB);
		customerInfo.add(customerTextC);
		customerInfo.add(customerTextD);
		customerInfo.add(customerTextE);
		
		fillChart();
		someEvent();
		
		detailsScene.setCenter(someRandomPane);
	}
	
	private void formatGridPane()
	{
		someRandomPane.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
		someRandomPane.setGridLinesVisible(true);
		someRandomPane.setAlignment(Pos.TOP_CENTER);
		someRandomPane.getColumnConstraints().add(new ColumnConstraints((Main.WIDTH - 725) / 3));
		someRandomPane.getColumnConstraints().add(new ColumnConstraints((Main.WIDTH - 725) / 3));
		someRandomPane.getColumnConstraints().add(new ColumnConstraints((Main.WIDTH - 725) / 3));
	}
	
	private void fillChart()
	{
		while(someRandomPane.getChildren().size() != 0){
			someRandomPane.getChildren().remove(0);
		}
		
		//someRandomPane.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
		
		Text textA = new Text("Customer ID");
		Text textB = new Text("Location");
		Text textC = new Text("Time Spent");
		
		someRandomPane.add(textA, 0, 0);
		someRandomPane.add(textB, 1, 0);
		someRandomPane.add(textC, 2, 0);
		
		for(int i = 0; i < customerInfo.size(); i++){
			try
			{
				someRandomPane.add(customerInfo.get(i)[0], 0, i + 1);
				someRandomPane.add(customerInfo.get(i)[1], 1, i + 1);
				someRandomPane.add(customerInfo.get(i)[2], 2, i + 1);
			}
			catch (Exception err) {
				System.out.println(err + "Customer Missing Data: Discarded.");
			}
		}
	}
	
	/**
	 * Creates the buttons for the HBox
	 * 
	 * @param numberOfButtons
	 * @return
	 */
	private Button[] buttons(int numberOfButtons){
		
		Button[] buttons = new Button[numberOfButtons];
		
		buttons[0] = new Button("Sort ID");
		buttons[1] = new Button("Sort Lo");
		buttons[2] = new Button("Sort TS");
		
		for(int i = 0; i < numberOfButtons; i++){
			buttons[i].setPrefWidth((Main.WIDTH - 700) / numberOfButtons);
		}
		
		return buttons;
	}
	
	private void someEvent()
	{	
		buttons[0].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
			while(someRandomPane.getChildren().size() != 0){
				someRandomPane.getChildren().remove(0);
			}
			
			if(flagID){
				Collections.sort(customerInfo, new Comparator<Text[]>(){
					@Override
					public int compare(Text[] o1, Text[] o2) {
						// TODO Auto-generated method stub
						return o1[0].getText().compareTo(o2[0].getText());
					}});
				flagID = !flagID;
			} 
			else {
				Collections.sort(customerInfo, new Comparator<Text[]>(){
					@Override
					public int compare(Text[] o1, Text[] o2) {
						// TODO Auto-generated method stub
						return o1[0].getText().compareTo(o2[0].getText());
					}});
				flagID = !flagID;
			}
			
			fillChart();
		});
		
		buttons[1].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: orange; -fx-border-width: 10;");
			while(someRandomPane.getChildren().size() != 0){
				someRandomPane.getChildren().remove(0);
			}
			fillChart();
		});
		
		buttons[2].setOnMouseClicked(e-> {
			someRandomPane.setStyle("-fx-border-color: red; -fx-border-width: 15;");
			while(someRandomPane.getChildren().size() != 0){
				someRandomPane.getChildren().remove(0);
			}
			fillChart();
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