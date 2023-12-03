package org.kessoku_band.csye6200project;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StartController {

	// used for adding new menu
    @FXML
    private Button add_button;
    
    //used for start old menu
    @FXML
    private Button start_button;

    @FXML
    private Label start_title;
   

    @FXML
    void startNewMeal(ActionEvent event) throws IOException {
    	Main.switchView("Preparation.fxml");
    	
    }
    
    @FXML
    void startCooking(ActionEvent event) throws IOException {
		Scene scene = MenuSelectionController.buildScene();
        //add menu to it
        addMenuCard(scene);
		Main.switchViewWithScene(scene);
    	
    }
    
    private void addMenuCard(Scene scene) {
    	int len = Main.r_w_Object.getData().size();
    	for(int i = 0 ; i< len; i++) {
    		MenuSelectionController.addCardToPane(Main.r_w_Object.getData().get(i));
    	}
    	
    }

}