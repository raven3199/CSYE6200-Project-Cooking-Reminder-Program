package org.kessoku_band.csye6200project;


import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuSelectionController {
    
    public static Stage secondStage;  // The opened second stage
    
    private static VBox contentBox;  // Main box, contends all objects
    
    private static GridPane buttonGridPane;  // Contends add operation and go cook buttons
    
    public static ArrayList<Menu> menu_list;  // List of all operation of this meal
    
    private static ArrayList<Card> card_list;  // List of cards shown in the main box
    
    public static Menu newMenu;  // The menu of this meal
    
    
    // Add operation corresponding card to the main box
    public static void addCardToPane(Menu menu) {
    	int buttonIndex = contentBox.getChildren().indexOf(buttonGridPane);
    	Card card = new Card(menu);
    	card.setIndex(MenuSelectionController.card_list.size());
    	card_list.add(card);
    	contentBox.getChildren().add(buttonIndex, card);
    }
    
	
    // The method to build Operation View
	public static Scene buildScene() throws IOException {
		menu_list = Main.r_w_Object.getData();
		card_list = new ArrayList<Card>();
		Font font1 = Font.font("Arial", FontWeight.BOLD, 36);
		Font font2 = Font.font("Arial", FontWeight.NORMAL, 16);
		Font font3 = Font.font("Arial", FontWeight.NORMAL, 20);
		
		// The grid pane for title and instruction
		GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        
        Label title = new Label("Menu Selection Stage");
        title.setFont(font1);
        Label instruction = new Label("Please select the menu you want to cook");
        instruction.setFont(font2);
        instruction.setTextFill(Color.LIGHTGRAY);
        
        gridPane.add(title, 0, 0);
        gridPane.add(instruction, 0, 1);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(instruction, HPos.CENTER);
        GridPane.setMargin(title, new Insets(50, 0, 0, 0));
        GridPane.setMargin(instruction, new Insets(0, 0, 30, 0));
		
        // Main box
		VBox content = new VBox();
        content.setAlignment(Pos.TOP_CENTER); 
        content.setSpacing(10); 

        // The grid pane for buttons
        GridPane buttonPane = new GridPane();
        buttonPane.setAlignment(Pos.CENTER);
        content.getChildren().addAll(gridPane, buttonPane);
        contentBox = content;
        buttonGridPane = buttonPane;
        
        // Add scroll pane
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true); 
        scrollPane.setFitToHeight(true); 
        scrollPane.setPadding(new Insets(0));
        
        Main.setFullScreen();
        Scene scene = new Scene(scrollPane, 640, 480);
        

        return scene;
	}
	
	
	// Delete corresponding card
	public static void deleteCard(int index) {
		menu_list.remove(index);
		card_list.remove(index);
		contentBox.getChildren().remove(index + 1);
		Main.r_w_Object.writeOutput();
		for(int i=0; i < card_list.size(); i++) {
			card_list.get(i).setIndex(i);;
		}
	}
	
}
