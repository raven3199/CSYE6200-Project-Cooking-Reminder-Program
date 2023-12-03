package org.kessoku_band.csye6200project;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
	public static Stage primaryStage;
	public static InputAndOutput r_w_Object = new JsonReaderWriter();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// ini the reader and writer IO
			Main.r_w_Object.readInput();
			
			Main.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(Main.class.getResource("Start.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Cooking Reminder Program");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	// Switch the view in the primary stage
	public static void switchView(String resource) throws IOException {  
		Parent content = FXMLLoader.load(Main.class.getResource(resource));
		Scene scene = new Scene(content);
		primaryStage.setTitle("Cooking Reminder Program");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Switch the view in the primary stage
	public static void switchViewWithScene(Scene scene) throws IOException {  
		primaryStage.setTitle("Cooking Reminder Program");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Open a second stage
	public static Stage openSecondStage(String resource, int width, int height) {  
		Stage secondStage = new Stage();
        try {
			Parent root = FXMLLoader.load(Main.class.getResource(resource));
			Scene scene = new Scene(root);
			secondStage.setWidth(width);
			secondStage.setHeight(height);
			secondStage.setScene(scene);
			secondStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
        return secondStage;
	}
	
	// Open the stage which is passed
	public static void closeStage(Stage stage) {
		stage.close();
	}
	
	// Set stage to full screen
	public static void setFullScreen() {
		primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		//primaryStage.setFullScreen(true);
	}
}
