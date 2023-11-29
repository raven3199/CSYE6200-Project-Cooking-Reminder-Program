package org.kessoku_band.csye6200project;

import java.io.*;
import org.json.simple.parser.*;
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
	public void readInput() {
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader("D:\\nbsapce\\tempProjects\\src\\main\\resources\\jsonStrcture.json"));
			JSONObject jsonObject = (JSONObject)object;

			// get menu_chain
			JSONArray menu_chain = (JSONArray)jsonObject.get("menu_chain");

			// loop through the menu_chain
			Iterator<JSONObject> menu_iterator = menu_chain.iterator();

			while(menu_iterator.hasNext()) {

				JSONObject temp = menu_iterator.next();

				this.menuList.add(this.CreateMenus(temp));
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}

	@Override
	public void writeOutput() {
		JSONObject menu_chain = new JSONObject();
		int menu_len = this.menuList.size();
		JSONArray menu_array = new JSONArray();

		for(int i = 0; i < menu_len; i++){
			Menu menu_object = this.menuList.get(i);
			JSONObject menu_json = new JSONObject();
			menu_json.put("menu_name", menu_object.getName());

			// create operation part
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
			menu_array.add(menu_json);
		}
		menu_chain.put("menu_chain", menu_array);
		try {
			FileWriter file = new FileWriter("D:\\nbsapce\\tempProjects\\src\\main\\resources\\testout.json");
			file.write(menu_chain.toJSONString());
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("JSON file created: "+ menu_chain);


		
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
		Menu menu = new Menu();
		ArrayList<Operation> ops = new ArrayList<Operation>();
		menu.setName((String)jsonObject.get("menu_name"));
		JSONArray operation_chain = (JSONArray)jsonObject.get("Operation");
		Iterator<JSONObject> op_iterator = operation_chain.iterator();

		while(op_iterator.hasNext()) {
			JSONObject temp = op_iterator.next();
			ops.add(this.createOperation(temp));
		}
		menu.setOperations(ops);
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

}
