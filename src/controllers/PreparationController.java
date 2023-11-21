package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import beans.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PreparationController implements Initializable {
	@FXML
    private Button addIngredient_button;
	
    @FXML
    private Button operation_button;

    @FXML
    private TableColumn<Ingredient, Double> amountColumn;
    
    @FXML
    private TableView<Ingredient> ingredientTable;

    @FXML
    private TableColumn<Ingredient, String> nameColumn;
    
    @FXML
    private Text suggestion;

    @FXML
    private Text title;

    @FXML
    private TableColumn<Ingredient, String> unitColumn;
    
    private static ObservableList<Ingredient> ingredients;
    
    public static Stage secondStage;
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ingredients = FXCollections.observableArrayList();
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("amount"));
		unitColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("unit"));
		ingredientTable.setItems(ingredients);
	}
	
	
	// Press Add Ingredient Button and open Ingredient Input View
	@FXML
    void addIngredient(ActionEvent event) {
		secondStage = Main.openSecondStage("/views/IngredientInput.fxml", 600, 350);
    }
	
	
	// Ingredient submission
	public static void submitCompleted() {
		Main.closeStage(secondStage);
		Ingredient ingredient = IngredientInputController.passingValue;
		ingredients.add(ingredient);
	}
	
	
	// Go to next page: Operation View
	@FXML
    void goOperation(ActionEvent event) throws IOException {
		Main.switchView("/views/Operation.fxml");
    }
}
