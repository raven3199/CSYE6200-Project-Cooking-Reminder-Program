package org.kessoku_band.csye6200project;

import java.util.ArrayList;

public interface InputAndOutput {
	public void readInput();
	
	public void writeOutput();

	public void removeMenu(String name);

	public void addMenu(Menu menu);

	public ArrayList<Menu> getData();
	
	

}
