package org.kessoku_band.csye6200project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import javafx.scene.control.Button;
public class ExecuteController implements Initializable {
private BooleanProperty istimer = new SimpleBooleanProperty(true);
private static ArrayList<Menu> menuList;
private static ArrayList<Operation> operations;
private int index =1;
private int timesecond;
private Timeline timeline;
public ExecuteController(){
	JsonReaderWriter inandout = new JsonReaderWriter();
	inandout.readInput();
	
	if (istimer.get()) {
		System.out.println("is timer");
//		btnnextstep.setVisible(false);
	}
};

public static void start(ArrayList<Operation> operation) throws Exception {
	operations=operation;
	Main.switchView("execute.fxml");
}


@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	if (istimer.get()) {
		System.out.println("is timer");
		btnnextstep.setVisible(true);
		timer.setVisible(true);

	dotimer(operations.get(index-1));
	
	}
	else {
		btnnextstep.setVisible(true);
		timer.setVisible(false);
	}
	}
private void dotimer(Operation operation)  {
	
	stepnum.setText("STEP"+index);
		timesecond = operation.getInterval();
		System.out.println("Interval"+operation.getInterval());
		System.out.println("display time"+timesecond);
		stepcontent.setText(operation.getContent());
		timer.setText(String.valueOf(timesecond));
		timeline = new Timeline(new KeyFrame(Duration.seconds(1),e->{
			int currenTime = Integer.parseInt(timer.getText());
			timer.setText(Integer.toString(currenTime-1));
		}));
		timeline.setCycleCount(timesecond);
		timeline.setOnFinished(e->{
			index++;
			
			if (index>operations.size()) {
				timer.setText("Finish");
				
					Platform.runLater(()->{
						try {
							showConfirmationDialog();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
				 
				return;
			}
			dotimer(operations.get(index-1));
		});
		timeline.play();

	    
	    

}

@FXML
private Button btnnextstep;

@FXML
private TextArea stepcontent;

@FXML
private Label stepnum;

@FXML
private Label timer;

@FXML
private Button pauseResumebtn;

@FXML
void Nextstep(ActionEvent event) {
	
	
	if (index<operations.size()) {
		System.out.println("next btn clicked");
		index++;
		timeline.stop();
		dotimer(operations.get(index-1));
		stepnum.setText("STEP"+index);
		stepcontent.setText(operations.get(index-1).getContent());
		System.out.println(operations.get(index-1).getContent());
	}

}

@FXML
void handlePauseResumeAction(ActionEvent event) {
if (pauseResumebtn.getText().equals("Pause")) {
	pauseResumebtn.setText("Resume");
	timeline.pause();
}
else {
	pauseResumebtn.setText("Pause");
	timeline.play();
}
}
public void showConfirmationDialog() throws Exception {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Yes");
    alert.setHeaderText("Back To Home");
    alert.setContentText("Back To Home？");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
    	Main.switchView("Start.fxml");
    }
}
}
