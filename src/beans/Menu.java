package beans;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menu {
	private StringProperty name;  // Name of this meal
	private ArrayList<Operation> operations;  // All operations of this meal
	
	public Menu() {
		this.name = new SimpleStringProperty();
		this.setOperations(new ArrayList<Operation>());
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
	
}
