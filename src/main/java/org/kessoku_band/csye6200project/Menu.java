package org.kessoku_band.csye6200project;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Menu {
	private StringProperty name;  // Name of this meal
	private ArrayList<Operation> operations;  // All operations of this meal
	private ObservableList<Ingredient> ingredients;  // All ingredients used in this meal
	
	public Menu() {
		this.name = new SimpleStringProperty();
		this.setOperations(new ArrayList<Operation>());
		ingredients = FXCollections.observableArrayList();
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public ArrayList<Operation> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<Operation> operations) {
		this.operations = operations;
	}
	
	public StringProperty getNameProperty() {
		return this.name;
	}

	public ObservableList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ObservableList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}
