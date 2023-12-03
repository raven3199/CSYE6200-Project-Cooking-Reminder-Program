package org.kessoku_band.csye6200project;

import java.io.*;
import org.json.simple.parser.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonReaderWriter implements InputAndOutput{
	private ArrayList<Menu> menuList;

	public JsonReaderWriter(){
		this.menuList = new ArrayList<Menu>();
	}

	@Override
	public int readInput() {
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader("D:\\testout.json"));
			JSONObject jsonObject = (JSONObject)object;

			// get menu_chain
			JSONArray menu_chain = (JSONArray)jsonObject.get("menu_chain");
			if(menu_chain == null) {
				return 1;
			}

			// loop through the menu_chain
			Iterator<JSONObject> menu_iterator = menu_chain.iterator();

			while(menu_iterator.hasNext()) {

				JSONObject temp = menu_iterator.next();

				this.menuList.add(this.CreateMenus(temp));
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		System.out.println(this.menuList.size());
		return 0;
	}

	@Override
	public int writeOutput() {
		JSONObject menu_chain = new JSONObject();
		int menu_len = this.menuList.size();
		JSONArray menu_array = new JSONArray();

		// loop write each menu
		for(int i = 0; i < menu_len; i++){
			Menu menu_object = this.menuList.get(i);
			JSONObject menu_json = new JSONObject();
			menu_json.put("menu_name", menu_object.getName());

			// loop write each operations
			int operation_size = menu_object.getOperations().size();
			JSONArray operations = new JSONArray();
			for(int j = 0; j < operation_size; j++){
				Operation operation_object = menu_object.getOperations().get(j);
				JSONObject operation_json = new JSONObject();
				operation_json.put("index", operation_object.getIndex());
				operation_json.put("content", operation_object.getContent());
				operation_json.put("interval", operation_object.getInterval());
				operation_json.put("displayTime", operation_object.getDisplayTime());
				operations.add(operation_json);
			}
			menu_json.put("Operation", operations);

			//loop write each ingredients
			int ingredient_size = menu_object.getIngredients().size();
			JSONArray ingredients = new JSONArray();
			for(int j = 0; j < ingredient_size; j++){
				Ingredient ingredient_object = menu_object.getIngredients().get(j);
				JSONObject ingredient_json = new JSONObject();
				ingredient_json.put("name", ingredient_object.getName());
				ingredient_json.put("amount", ingredient_object.getAmount());
				ingredient_json.put("unit", ingredient_object.getUnit());
				ingredients.add(ingredient_json);
			}
			menu_json.put("Ingredients", ingredients);

			// add menu to menu array
			menu_array.add(menu_json);

		}
		menu_chain.put("menu_chain", menu_array);
		try {
			FileWriter file = new FileWriter("D:\\testout.json");
			file.write(menu_chain.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;


		
	}

	@Override
	public void removeMenu(String name) {
		int size = this.menuList.size();
		for(int i = 0; i < size; i ++){
			if(this.menuList.get(i).getName().equals(name)){
				this.menuList.remove(i);
				break;
			}
		}

	}

	@Override
	public void addMenu(Menu menu) {
		this.menuList.add(menu);

	}

	@Override
	public ArrayList<Menu> getData() {
		return this.menuList;
	}

	private Menu CreateMenus(JSONObject jsonObject){
		// create menu object
		Menu menu = new Menu();
		menu.setName((String)jsonObject.get("menu_name"));

		//*********************** Create operations Objects **************//

		//create operation list in menu
		ArrayList<Operation> ops = new ArrayList<Operation>();

		//read the operations list
		JSONArray operation_chain = (JSONArray)jsonObject.get("Operation");
		Iterator<JSONObject> op_iterator = operation_chain.iterator();

		//loop create the operation objects
		while(op_iterator.hasNext()) {
			JSONObject temp = op_iterator.next();
			ops.add(this.createOperation(temp));
		}

		// set operation list to menu
		menu.setOperations(ops);

		//*********************** Create Ingredient Objects **************//

		//create ingredient list in menu
		ObservableList<Ingredient> ins = FXCollections.observableArrayList();;

		//read the ingredient list
		JSONArray ingredient_chain = (JSONArray)jsonObject.get("Ingredients");
		Iterator<JSONObject> in_iterator = ingredient_chain.iterator();

		//loop create the ingredient objects
		while(in_iterator.hasNext()) {
			JSONObject temp = in_iterator.next();
			ins.add(this.createIngredient(temp));
		}

		// set ingredient list to menu
		menu.setIngredients(ins);

		return menu;
	}

	private Operation createOperation(JSONObject jsonObject){

		Operation temp_op = new Operation();
		temp_op.setIndex((int)(long)jsonObject.get("index"));
		temp_op.setContent((String)jsonObject.get("content"));
		temp_op.setDisplayTime((int)(long)jsonObject.get("interval"));
		temp_op.setInterval((int)(long)jsonObject.get("displayTime"));
		
		return temp_op;
	}

	private Ingredient createIngredient(JSONObject jsonObject){

		Ingredient temp_in = new Ingredient();
		temp_in.setName((String)jsonObject.get("name"));
		//System.out.println( jsonObject.get("amount").getClass());
		if(jsonObject.get("amount") instanceof Double) {
			temp_in.setAmount((Double)jsonObject.get("amount"));
		}else {
			temp_in.setAmount(Double.parseDouble((String)jsonObject.get("amount")));
		}
		
		temp_in.setUnit((String)jsonObject.get("interval"));
		
		return temp_in;
	}

}
