package org.kessoku_band.csye6200project;

import java.util.ArrayList;

public interface InputAndOutput {
	public int readInput();
	
	public int writeOutput();

	public void removeMenu(String name);

	public void addMenu(Menu menu);

	public ArrayList<Menu> getData();
	
	

}
