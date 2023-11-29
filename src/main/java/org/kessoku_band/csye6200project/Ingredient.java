package org.kessoku_band.csye6200project;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ingredient {
	private StringProperty name;  // Name of the ingredient
	private DoubleProperty amount;  // Amount of the ingredient
	private StringProperty unit;  // Unit of the amount
	
	public Ingredient() {
		this.name = new SimpleStringProperty();
		this.amount = new SimpleDoubleProperty();
		this.unit = new SimpleStringProperty();
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public double getAmount() {
		return this.amount.get();
	}

	public void setAmount(double amount) {
		this.amount.set(amount);
	}

	public String getUnit() {
		return this.unit.get();
	}

	public void setUnit(String unit) {
		this.unit.set(unit);
	}
	
	public StringProperty getNameProperty() {
		return this.name;
	}
	
	public DoubleProperty getAmouDoubleProperty() {
		return this.amount;
	}
	
	public StringProperty getUnitProperty() {
		return this.unit;
	}
}
