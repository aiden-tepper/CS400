package application;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;
	private static final String APP_TITLE = "Hello World!";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		// Create a vertical box with Hello labels for each args
		VBox vbox = new VBox();
		for (String arg : args) {
			vbox.getChildren().add(new Label("hello "+arg));
		}

		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		// Add the vertical box to the center of the root pane
		root.setTop(new Label("CS400 My First JavaFx Program"));
		root.setCenter(vbox);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// Add ComboBox in left panel
		String[] options = {"Option 1", "Option 2", "Option 3"};
		ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(options));
		root.setLeft(combo_box);
		
		// Add image in the center panel
		Image image =
				new Image("/application/sandwich.jpg");
		ImageView iv = new ImageView();
		iv.setImage(image);
		iv.setFitHeight(300); 
	    iv.setFitWidth(200);
		root.setCenter(iv);
		
		// Add button in the bottom panel
		Button b = new Button("Done");
		root.setBottom(b);
		
		// Add slider in the right panel
		Slider slider = new Slider();
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		root.setRight(slider);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}