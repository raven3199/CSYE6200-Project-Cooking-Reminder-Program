package org.kessoku_band.csye6200project;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class OperationController {
    
    public static Stage secondStage;  // The opened second stage
    
    private static VBox contentBox;  // Main box, contends all objects
    
    private static GridPane buttonGridPane;  // Contends add operation and go cook buttons
    
    private static ArrayList<Operation> operation_list;  // List of all operation of this meal
    
    private static ArrayList<Card> card_list;  // List of cards shown in the main box
    
    private static int changeIndex;  // The index of the card that want to modify
    
    private static TextField mealNameField;  // To input the name of this meal
    
    public static Menu newMenu;  // The menu of this meal
    
    
    // Operation input completed, close stage and add operation to list
    public static void submitCompleted() {
		Main.closeStage(secondStage);
		Operation operation = OperationInputController.operation_entered;
		operation_list.add(operation);
		addCardToPane(operation);
	}
    
    // Add operation corresponding card to the main box
    private static void addCardToPane(Operation operation) {
    	int buttonIndex = contentBox.getChildren().indexOf(buttonGridPane);
    	Card card = new Card(operation);
    	card_list.add(card);
    	contentBox.getChildren().add(buttonIndex, card);
    }
    
    // Get next index of new input operation
    public static int getIndex() {
    	return operation_list.size()+1;
    }
	
    // The method to build Operation View
	public static Scene buildScene() throws IOException {
		operation_list = new ArrayList<Operation>();
		card_list = new ArrayList<Card>();
		Font font1 = Font.font("Arial", FontWeight.BOLD, 36);
		Font font2 = Font.font("Arial", FontWeight.NORMAL, 16);
		Font font3 = Font.font("Arial", FontWeight.NORMAL, 20);
		
		// The grid pane for title and instruction
		GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        
        Label title = new Label("Operation Stage");
        title.setFont(font1);
        Label instruction = new Label("Please enter all the steps required to make this meal: ");
        instruction.setFont(font2);
        instruction.setTextFill(Color.LIGHTGRAY);
        
        gridPane.add(title, 0, 0);
        gridPane.add(instruction, 0, 1);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(instruction, HPos.CENTER);
        GridPane.setMargin(title, new Insets(50, 0, 0, 0));
        GridPane.setMargin(instruction, new Insets(0, 0, 30, 0));
        
        // The grid pane for input the name of the meal
        GridPane namePane = new GridPane();
        namePane.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("What is the name of this meal? ");
        nameLabel.setFont(font3);
        TextField mealName = new TextField();
        mealNameField = mealName;
        
        namePane.add(nameLabel, 0, 0);
        namePane.add(mealName, 1, 0);
        GridPane.setMargin(nameLabel, new Insets(0, 0, 30, 0));
        GridPane.setMargin(mealName, new Insets(0, 0, 30, 0));
		
        // Main box
		VBox content = new VBox();
        content.setAlignment(Pos.TOP_CENTER); 
        content.setSpacing(10); 

        // Add operation button
        Button add_operation_button = new Button("Add Operation");
        add_operation_button.setPrefSize(200, 50);
        add_operation_button.setFont(font3);
        add_operation_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	// To add new operation and card
            	OperationInputController.comeFrom = 0;
            	secondStage = Main.openSecondStage("OperationInput.fxml", 600, 450);
            }
        });
        
        // Go cook button
        Button go_cook_button = new Button("Finished");
        go_cook_button.setPrefSize(200, 50);
        go_cook_button.setFont(font3);
        GridPane.setMargin(go_cook_button, new Insets(0, 0, 0, 30));
        go_cook_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				produceMenu();
				try {
					Main.switchView("Start.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        // The grid pane for buttons
        GridPane buttonPane = new GridPane();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.add(add_operation_button, 1, 0);
        buttonPane.add(go_cook_button, 2, 0);
        GridPane.setValignment(add_operation_button, VPos.CENTER);
        GridPane.setValignment(go_cook_button, VPos.CENTER);
        
        
        content.getChildren().addAll(gridPane, namePane, buttonPane);
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
	
	// Decide which card should be modified
	public static void startModify(int index) {
		changeIndex = index-1;
		OperationInputController.comeFrom = 1;
    	secondStage = Main.openSecondStage("OperationInput.fxml", 600, 450);
    	System.out.println("Start");
	}
	
	// Modify corresponding card and operation with newly input information
	public static void modifyCompleted() {
		Main.closeStage(secondStage);
		Operation operation = OperationInputController.operation_entered;
		
		Operation old = operation_list.remove(changeIndex);
		operation_list.add(changeIndex, operation);
		
		System.out.println("Old operation: " + old.getContent());
		System.out.println("New operation: " + operation.getContent());
		
		Card card = card_list.get(changeIndex);
		card.modifyCard(operation);
		System.out.println("Change Card");
	}
	
	// Delete corresponding card
	public static void deleteCard(int index) {
		operation_list.remove(index);
		card_list.remove(index);
		contentBox.getChildren().remove(index+2);
		
		for(int i=0; i<card_list.size(); i++) {
			card_list.get(i).modifyIndex(i+1);
		}
		showOperation();
	}
	
	// Output the operation list in the console
	public static void showOperation() {
		for(int i=0; i<operation_list.size(); i++) {
			System.out.println(operation_list.get(i).getContent());
		}
	}
	
	// End of this view and produce the menu for this meal
	private static void produceMenu() {
		Menu menu = new Menu();
		menu.setName(mealNameField.getText());
		menu.setOperations(operation_list);
		menu.setIngredients(PreparationController.ingredients);
		newMenu = menu;
		System.out.println(newMenu.getName());
		for(Operation o: newMenu.getOperations()) {
			System.out.println(o.getContent());
		}
		Main.r_w_Object.addMenu(menu);
		Main.r_w_Object.writeOutput();
	}
	
	
}
