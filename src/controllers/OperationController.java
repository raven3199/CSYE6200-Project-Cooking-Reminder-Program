package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import beans.Card;
import beans.Operation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OperationController implements Initializable {

    @FXML
    private Button addOperation_button;

    @FXML
    private Label first_title;

    @FXML
    private static VBox operations_vbox;

    @FXML
    private Label second_title;
    
    public static Stage secondStage;
    
    private static ArrayList<Operation> operation_list;

    @FXML
    void addOperation(ActionEvent event) {
    	// secondStage = Main.openSecondStage("/views/OperationInput.fxml", 600, 450);
    	
    	System.out.println("Click");
    	Scene scene = new Scene(OperationController.operations_vbox);
    	Main.primaryStage.setScene(scene);
    }
    
    public static void submitCompleted() {
		Main.closeStage(secondStage);
		Operation operation = OperationInputController.operation_entered;
		operation_list.add(operation);
		addCardToPane(operation);
	}
    
    private static void addCardToPane(Operation operation) {
    	Card operation_card = new Card(operation);
    	
    	operations_vbox.getChildren().add(operation_card);
    	operations_vbox.requestLayout();
    }
    
    public static int getIndex() {
    	return operation_list.size()+1;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		operation_list = new ArrayList<Operation>();
		operations_vbox = new VBox();
		
		Button button = new Button("Add");
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				secondStage = Main.openSecondStage("/views/OperationInput.fxml", 600, 450);
				
			}
		});
		operations_vbox.getChildren().add(button);
		
//		Scene scene = new Scene(OperationController.operations_vbox);
//    	Main.primaryStage.setScene(scene);
	}
}
