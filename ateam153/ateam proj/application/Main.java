/////////////////////////////////////////////////////////////////////////////////
//
//Title: Milk Weights
//Course: 400, Spring 20
//
//Author: Juan Rodriguez, Aiden Tepper
//Email: jcrodriguez3@wisc.edu , ajtepper@wisc.edu
//Lecturer's Name: Prof. Deppeler
//
////////////////////////////////////////////////////////////////////////////////

//This class is responsible for the user interface elements of our app
package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Popup;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Main extends Application {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final String WINDOW_TITLE = "Milk Weights";


    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) {
    	
    	MilkWeightApplication app = new MilkWeightApplication();

    	
    	/**
    	 * WELCOME SCENE
    	 */
        BorderPane border = new BorderPane();
        Scene welcome = new Scene(border, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Label for welcome scene
        Label label1 = new Label();
        label1.setText("Welcome!");
        label1.setFont(Font.font("Tahoma", 32));
        label1.setTranslateY(-100);
        border.setCenter(label1);

        // Continue button for welcome scene
        Button b = new Button("Continue");
        border.setAlignment(b, Pos.CENTER);
        b.setTranslateY(-200);
        b.setFont(Font.font("Arial", 14));
        border.setBottom(b);
        
        
        /**
         * MAIN MENU SCENE
         */
        BorderPane border2 = new BorderPane();
        Scene choice = new Scene(border2, WINDOW_WIDTH, WINDOW_HEIGHT);

        // top of borderpane
        Label intro = new Label();
        intro.setText("What would you like to do?");
        intro.setFont(Font.font("Tahoma", 20));
        intro.setTranslateY(15);
        intro.setTranslateX(25);
        border2.setTop(intro);

        // left of borderpane, responsible for possible actions
        ChoiceBox<String> option =
            new ChoiceBox<String>(FXCollections.observableArrayList("Load new data",
                "View data", "Clear data"));
        option.setTranslateY(-10);
        option.setTranslateX(325);
        border2.setLeft(option);
        
        // select button
        Button select = new Button("Select");
        select.setFont(Font.font("Arial", 14));
        select.setTranslateX(-40);
        select.setTranslateY(-10);
        border2.setRight(select);
        
        // exit button
        Button exit = new Button("Exit");
        exit.setTranslateY(-50);
        exit.setFont(Font.font("Arial", 14));
        border2.setAlignment(exit, Pos.CENTER);
        border2.setBottom(exit);
        
        
        /**
         * LOAD NEW DATA SCENE
         */
        BorderPane border3 = new BorderPane();
        Scene loadData = new Scene(border3, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // top of borderpane
        Label label = new Label();
        label.setText("Select the data file you would like to load");
        label.setFont(Font.font("Tahoma", 20));
        label.setTranslateY(15);
        label.setTranslateX(25);
        border3.setTop(label);
        
        // file chooser button
        FileChooser fileChooser = new FileChooser();
        Button fc = new Button("Choose file");
        fc.setTranslateY(40);
        fc.setTranslateX(25);
        border3.setLeft(fc);
        
        // exit button
        Button exitToMain = new Button("Exit");
        border3.setAlignment(exitToMain, Pos.CENTER);
        exitToMain.setTranslateY(-50);
        exitToMain.setFont(Font.font("Arial", 14));
        border3.setBottom(exitToMain);
        
        
        /**
         * VIEW IMPORTED DATA SCENE
         */
        BorderPane border4 = new BorderPane();
        Scene viewData = new Scene(border4, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // top of borderpane
        Label label2 = new Label();
        label2.setText("Currently imported data");
        label2.setFont(Font.font("Tahoma", 20));
        label2.setTranslateY(15);
        label2.setTranslateX(25);
        border4.setTop(label2);
        
        // exit button
        Button exitToMain2 = new Button("Exit");
        border4.setAlignment(exitToMain2, Pos.CENTER);
        exitToMain2.setTranslateY(-50);
        exitToMain2.setFont(Font.font("Arial", 14));
        border4.setBottom(exitToMain2);
        
        // displaying data using TableView
        TableView<String> table = new TableView<>();
        TableColumn<String, String> column1 = new TableColumn<>("Date");
        column1.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<String, String> column2 = new TableColumn<>("Farm ID");
        column2.setCellValueFactory(new PropertyValueFactory<>("farmID"));
        TableColumn<String, String> column3 = new TableColumn<>("Weight");
        column3.setCellValueFactory(new PropertyValueFactory<>("weight"));
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.setTranslateY(30);
        border4.setLeft(table);
        
        
        // save data button
        Button saveData = new Button("Save data to new file");
        border4.setAlignment(saveData, Pos.CENTER);
        saveData.setTranslateY(137);
        saveData.setTranslateX(-20);
        saveData.setFont(Font.font("Arial", 14));
        border4.setRight(saveData);
        
        
        /**
         * BUTTON EVENT HANDLERS
         */
        
        exitToMain.setOnAction(e -> {
        	primaryStage.setScene(choice);
        });
        
        exitToMain2.setOnAction(e -> {
        	primaryStage.setScene(choice);
        });
        
        exit.setOnAction(e -> {
        	primaryStage.setScene(welcome);
        });

        b.setOnAction(e -> {
            primaryStage.setScene(choice);
        });
        
        fc.setOnAction(e -> {
        	File file = fileChooser.showOpenDialog(primaryStage);
        	Popup popup2 = new Popup();
        	try {
        		app.readFromFile(file);
        		// success popup if file imports without error
        		Label popupLabel2 = new Label("File successfully loaded.");
    	        popupLabel2.setFont(Font.font("Tahoma", 15));
    			popupLabel2.setMinWidth(80); 
    	        popupLabel2.setMinHeight(50); 
    			popup2.getContent().add(popupLabel2);
    			popup2.setAutoHide(true);
    			popup2.show(primaryStage);
        	} catch(Exception exception) {
        		System.out.println(exception);
        		// error popup if file doesn't successfully import
        		Label popupLabel2 = new Label("Failed: either file is not a csv or file has format errors.");
    	        popupLabel2.setFont(Font.font("Tahoma", 15));
    			popupLabel2.setMinWidth(80); 
    	        popupLabel2.setMinHeight(50); 
    			popup2.getContent().add(popupLabel2);
    			popup2.setAutoHide(true);
        		popup2.show(primaryStage);
        	}
        });
        
        select.setOnAction(e -> {
        	switch(option.getValue()) {
        		case "Load new data":
        			primaryStage.setScene(loadData);
        			break;
        		case "View data":
        			primaryStage.setScene(viewData);
        			break;
        		case "Clear data":
        			app.clearData();
        			// data successfully cleared popup
        			Popup popup = new Popup();
        			Label popupLabel = new Label("Data successfully cleared.");
        	        popupLabel.setFont(Font.font("Tahoma", 15));
        			popupLabel.setMinWidth(80); 
        	        popupLabel.setMinHeight(50); 
        			popup.getContent().add(popupLabel);
        			popup.show(primaryStage);
        			popup.setAutoHide(true);
        			break;
        	}
        });

        // Set welcome scene
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(welcome);
        primaryStage.show();

    }


    private void compHelper(String choice) {
        switch (choice) {
            case "FARM":
                /*
                 * MilkSale current = ht.get(farmID);
                 * int total = 0;
                 * while (current != null) {
                 * String[] split = current.date.split("-");
                 * int month = Integer.parseInt(split[1]);
                 * total += current.weight;
                 * }
                 * break;
                 */
            case "ANNUAL":
                /*int ann = 0;
                 * for (MilkSale elem : ht) {
                 * MilkSale current = elem;
                 * while (current != null) {
                 * 
                 * if (current.date.split("-")[0].equals(year user inputs); {
                 * ann += current.weight;
                 * 
                 * 
                 * }
                 */
            
        }
    }
}
