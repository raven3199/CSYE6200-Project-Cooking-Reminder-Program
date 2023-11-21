package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import beans.Operation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OperationInputController implements Initializable {

    @FXML
    private ChoiceBox<String> display_time_choice;

    @FXML
    private TextArea operation_input;

    @FXML
    private Button submitButton;

    @FXML
    private TextField time_hour_input;

    @FXML
    private TextField time_min_input;

    @FXML
    private TextField time_sec_input;

    @FXML
    private Label title;
    
    final private String[] display_choices = {"10s", "30s", "1min"};
    
    public static Operation operation_entered;

    @FXML
    void submit(ActionEvent event) throws IOException {
    	String content = operation_input.getText();
    	int hours = Integer.parseInt(time_hour_input.getText());
    	int minutes = Integer.parseInt(time_min_input.getText());
    	int seconds = Integer.parseInt(time_sec_input.getText());
    	String choice = display_time_choice.getValue();
    	
    	int index = OperationController.getIndex();
    	int interval = hours * 3600 + minutes * 60 + seconds;
    	int displayTime = 10;
    	if(choice.equals("10s")) {
    		displayTime = 10;
    	} else if (choice.equals("30s")) {
    		displayTime = 30; 
    	} else if (choice.equals("1min")) {
    		displayTime = 60;
    	}
    	
    	Operation operation = new Operation();
    	operation.setIndex(index);
    	operation.setContent(content);
    	operation.setInterval(interval);
    	operation.setDisplayTime(displayTime);
    	
    	operation_entered = operation;
    	OperationController.submitCompleted();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		display_time_choice.getItems().addAll(display_choices);
		display_time_choice.getSelectionModel().select(0);
		display_time_choice.setOnAction(this::getChoice);
	}

	private String getChoice(ActionEvent event) {
		String choice = display_time_choice.getValue();
		return choice;
	}
}

