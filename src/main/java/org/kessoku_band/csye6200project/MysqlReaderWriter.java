package org.kessoku_band.csye6200project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class MysqlReaderWriter implements InputAndOutput{
	private String url;
	private String user;
	private String password;
	private ArrayList<Menu> menuList;
	private ArrayList<Menu> new_menuList;
	public MysqlReaderWriter(){
		this.url = "jdbc:mysql://localhost:3306/csye6200project";
		this.user = "root";
		this.password = "rootroot";
		this.menuList = new ArrayList<Menu>();
		this.new_menuList = new ArrayList<Menu>();

	}
	
	@Override
	public int readInput() {
		// read data from menu and operations
		String sql = "Select * from menu";

		Connection connection = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(url, user, password);
			Statement st = connection.createStatement();

			try (ResultSet menu_set = st.executeQuery(sql)) {
				while (menu_set.next()) {
					Menu temp_menu = new Menu();
					temp_menu.setName(menu_set.getString("menu_name"));

					// create operation list
					String op_sql = "Select * from operations WHERE menu_search_key=\"" + temp_menu.getName() + "\"";
					ArrayList<Operation> temp_op_list = new ArrayList<Operation>();
					Statement st2 = connection.createStatement();
					try(ResultSet op_set = st2.executeQuery(op_sql)){
						while (op_set.next()) {
							Operation temp_op = new Operation();
							temp_op.setIndex(Integer.parseInt(op_set.getString("operations_index")));
							temp_op.setInterval(Integer.parseInt(op_set.getString("operations_interval")));
							temp_op.setDisplayTime(Integer.parseInt(op_set.getString("operations_displayTime")));
							temp_op.setContent(op_set.getString("operations_content"));
							temp_op_list.add(temp_op);
						}
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}

					//set operation list to menu
					temp_menu.setOperations(temp_op_list);

					// create operation list
					String in_sql = "Select * from ingredients WHERE menu_search_key=\"" + temp_menu.getName() + "\"";
					ObservableList<Ingredient> temp_in_list = FXCollections.observableArrayList();
					Statement st3 = connection.createStatement();
					try(ResultSet in_set = st3.executeQuery(in_sql)){
						while (in_set.next()) {
							Ingredient temp_in = new Ingredient();
							temp_in.setName(in_set.getString("ingredients_name"));
							temp_in.setAmount(Double.parseDouble(in_set.getString("ingredients_amount")));
							temp_in.setUnit(in_set.getString("ingredients_unit"));
							temp_in_list.add(temp_in);
						}
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}

					//set ingredient list to menu
					temp_menu.setIngredients(temp_in_list);

					// finish menu ini and add menu to menu list
					this.menuList.add(temp_menu);
				}

			}

		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return 0;




	}

	@Override
	public int writeOutput() {
		int len = this.new_menuList.size();
		for(int i = 0; i < len; i++){
			// insert menu into databse
			Menu cur_menu = this.new_menuList.get(i);
			String sql_addMenu = "INSERT INTO menu VALUES (\"" + cur_menu.getName() + "\")";
			this.exeUpdateSql(sql_addMenu);

			// insert operations into database
			int op_len = cur_menu.getOperations().size();
			for(int j = 0; j < op_len; j++){
				Operation cur_op = cur_menu.getOperations().get(j);
				String sql_addOp = "INSERT INTO operations VALUES (\"" + cur_menu.getName() + "\", \""
						+ cur_op.getIndex() + "\", \"" + cur_op.getContent() + "\", \"" + cur_op.getInterval()
						+ "\", \"" + cur_op.getDisplayTime() + "\")";
				this.exeUpdateSql(sql_addOp);
			}

			// insert Ingredients into database
			int in_len = cur_menu.getIngredients().size();
			for(int j = 0; j < in_len; j++){
				Ingredient cur_in = cur_menu.getIngredients().get(j);
				String sql_addIn = "INSERT INTO ingredients VALUES (\"" + cur_menu.getName() + "\", \""
						+ cur_in.getName() + "\", \"" + cur_in.getAmount() + "\", \"" + cur_in.getUnit() + "\")";
				this.exeUpdateSql(sql_addIn);
			}

		}
		return 0;


	}

	@Override
	public void removeMenu(String name) {
		int len_new = this.new_menuList.size();
		int len = this.menuList.size();
		Menu target = null;

		//remove from new list
		for(int i = 0; i < len_new; i++){
			if(this.new_menuList.get(i).getName().equals(name)){
				this.new_menuList.remove(i);
				break;
			}
		}

		//remove from full list
		for(int i = 0; i < len; i++){
			if(this.menuList.get(i).getName().equals(name)){
				target = menuList.get(i);
				this.menuList.remove(i);
				break;
			}
		}

		// remove from database
		if(target != null){
			String sql_removeMenu = "DELETE FROM menu WHERE menu_name=\"" + name + "\"";
			String sql_removeOperation = "DELETE FROM operations WHERE menu_search_key=\"" + name + "\"";
			this.exeUpdateSql(sql_removeMenu);
			this.exeUpdateSql(sql_removeOperation);
		}

	}

	@Override
	public void addMenu(Menu menu) {
		this.new_menuList.add(menu);
		this.menuList.add(menu);
	}

	@Override
	public ArrayList<Menu> getData() {

		return this.menuList;
	}

	private int exeUpdateSql(String sql){
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish the connection
			connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);


		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return -1;
	}

//	private ResultSet exeSearchSql(String sql){
//		Connection connection = null;
//		try{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			// Establish the connection
//			connection = DriverManager.getConnection(url, user, password);
//			Statement st = connection.createStatement();
//
//			try (ResultSet resultSet = st.executeQuery(sql)) {
//				return resultSet;
//
////				while (resultSet.next()) {
////
////				}
//			}
//
//		}catch(Exception ex){
//			System.out.println(ex.getMessage());
//		}
//		return null;
//	}


}
