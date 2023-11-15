package controllers;

import java.io.IOException;

import application.Main;
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
    	Main.switchView("/views/Preparation.fxml");
    }

}