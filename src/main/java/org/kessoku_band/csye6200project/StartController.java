package org.kessoku_band.csye6200project;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StartController {

    @FXML
    private Button start_button;

    @FXML
    private Label start_title;

    @FXML
    void startNewMeal(ActionEvent event) throws IOException {
    	Main.switchView("Preparation.fxml");
    	
    }

}