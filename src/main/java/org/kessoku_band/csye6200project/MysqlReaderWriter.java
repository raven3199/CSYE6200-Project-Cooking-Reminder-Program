package org.kessoku_band.csye6200project;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlReaderWriter implements InputAndOutput{
	
	@Override
	public void readInput() {
		String url = "jdbc:mysql://localhost:3306/csye6200project";
		String user = "your_username";
		String password = "your_password";
		
	}

	@Override
	public void writeOutput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMenu(String name) {

	}

	@Override
	public void addMenu(Menu menu) {

	}

	@Override
	public ArrayList<Menu> getData() {
		return null;
	}

}
