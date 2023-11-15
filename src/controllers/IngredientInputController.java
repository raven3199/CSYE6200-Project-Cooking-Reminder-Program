package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import beans.Ingredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IngredientInputController implements Initializable {
	
	@FXML
    private Label amountLabel;

    @FXML
    private TextField ingredientAmount;

    @FXML
    private TextField ingredientName;

    @FXML
    private ChoiceBox<String> ingredientUnit;

    @FXML
    private Label nameLabel;

    @FXML
    private Button submitButton;

    @FXML
    private Label title;

    @FXML
    private Label unitLabel;
    
    final private String[] units = {"g", "kg", "mmg", "lb", "oz", "l", "ml", "gal", "pint"};
    
    public static Ingredient passingValue;

    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ingredientUnit.getItems().addAll(units);
		ingredientUnit.getSelectionModel().select(0);
	    ingredientUnit.setOnAction(this::getUnit);
	}
    
    
    // Information input completed, submit it
    @FXML
    void submit(ActionEvent event) {
    	String name = ingredientName.getText();
    	double amount = Double.parseDouble(ingredientAmount.getText());
    	String unit = ingredientUnit.getValue();
    	
    	Ingredient ingredient = new Ingredient();
    	ingredient.setName(name);
    	ingredient.setAmount(amount);
    	ingredient.setUnit(unit);
    	
    	passingValue = ingredient;
    	PreparationController.submitCompleted();
    }

	
	// Get the unit value in the ChoiceBox
	public String getUnit(ActionEvent event) {
	    String unit = ingredientUnit.getValue();
	    return unit;
	}
}
