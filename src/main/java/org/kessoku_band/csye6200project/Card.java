package org.kessoku_band.csye6200project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Card extends StackPane {
	private int index;
	
	private Label indexLabel;
	
	private HBox contentBox;
	
	private HBox intervalBox;
	
	private HBox displayTimeBox;

    public Card(Operation operation) {
        Rectangle cardBackground = new Rectangle(150, 150);
        cardBackground.setArcHeight(20);
        cardBackground.setArcWidth(20);
        cardBackground.setFill(Color.LIGHTGRAY);
        
        // The label of the index of the card
        index = operation.getIndex();
        indexLabel = new Label(String.valueOf(index));        
        indexLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        
        // The content of the card, including operation, interval and display time
        contentBox = makeLabel("Content", operation.getContent());
        intervalBox = makeLabel("Interval", operation.getInterval());
        displayTimeBox = makeLabel("Display Time", operation.getDisplayTime());

        VBox operationContainer = new VBox();
        operationContainer.setSpacing(30);
        VBox.setMargin(contentBox, new Insets(10, 0, 0, 0));
        VBox.setMargin(displayTimeBox, new Insets(0, 0, 10, 0));
        
        operationContainer.getChildren().addAll(contentBox, intervalBox, displayTimeBox);
        operationContainer.setAlignment(Pos.CENTER_LEFT);

        
        // The buttons, for change the content and delete the card 
        HBox buttonBox = new HBox();
        Button modifyButton = makeButton("Modify_Icon.png");
        Button cancalButton = makeButton("Cancal_Icon.png");
        Button startButton = makeButton("start.png");
        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				OperationController.startModify(index);
				System.out.println("Index: "+ index);
			}
		});
        startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					ExecuteController.start(MenuSelectionController.menu_list.get(index).getOperations());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("start Index: "+ index);
			}
		});
        cancalButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				OperationController.deleteCard(index-1);
				System.out.println("Index: "+ index);
			}
		});
        
        buttonBox.setSpacing(30);
        buttonBox.getChildren().addAll(modifyButton, cancalButton,startButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        
        // Use GridPane to form them together
        GridPane cardGrid = new GridPane();
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.setHgap(10);
        cardGrid.setVgap(10);

        cardGrid.add(indexLabel, 0, 0);
        cardGrid.add(operationContainer, 1, 0);
        cardGrid.add(buttonBox, 2, 0);
        
        // Set the percentage of width of each column
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(8); 
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(40); 
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(8); 
        cardGrid.getColumnConstraints().addAll(col1, col2, col3);
        
        Insets cardMargin = new Insets(0,0,30,0);
        Card.setMargin(cardGrid, cardMargin);
        Card.setMargin(cardBackground, cardMargin);

        
        cardGrid.prefWidthProperty().bind(cardBackground.widthProperty());
        cardGrid.heightProperty().addListener((obs, oldH, newH) -> {
            cardBackground.setHeight(newH.doubleValue());
        });
        
        cardGrid.widthProperty().addListener((obs, oldW, newW) -> {
        	cardBackground.setWidth(newW.doubleValue() * 0.6);
        });

        getChildren().addAll(cardBackground, cardGrid);
        
        
        setOnMouseEntered(event -> {
            cardBackground.setFill(Color.GRAY);
            setScaleX(1.1);
            setScaleY(1.1);
        });

        setOnMouseExited(event -> {
            cardBackground.setFill(Color.LIGHTGRAY);
            setScaleX(1.0);
            setScaleY(1.0);
        });
    }
    
    public Card(Menu menu) {
        Rectangle cardBackground = new Rectangle(150, 150);
        cardBackground.setArcHeight(20);
        cardBackground.setArcWidth(20);
        cardBackground.setFill(Color.LIGHTGRAY);
        
        // The content of the card, including operation, interval and display time
        contentBox = makeLabel("Name", menu.getName());


        VBox operationContainer = new VBox();
        operationContainer.setSpacing(30);
        VBox.setMargin(contentBox, new Insets(10, 0, 0, 0));
        
        operationContainer.getChildren().addAll(contentBox);
        operationContainer.setAlignment(Pos.CENTER_LEFT);

        
        // The buttons, for change the content and delete the card 
        HBox buttonBox = new HBox();
        Button modifyButton = makeButton("Modify_Icon.png");
        Button cancalButton = makeButton("Cancal_Icon.png");
        Button startButton = makeButton("start.png");
        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				OperationController.startModify(index);
				System.out.println("Index: "+ index);
			}
		});
        startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					ExecuteController.start(MenuSelectionController.menu_list.get(index).getOperations());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("start Index: "+ index);
			}
		});
        cancalButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Index: "+ index);
				MenuSelectionController.deleteCard(index);

			}
		});
        
        buttonBox.setSpacing(30);
        buttonBox.getChildren().addAll(modifyButton, cancalButton,startButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        
        // Use GridPane to form them together
        GridPane cardGrid = new GridPane();
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.setHgap(10);
        cardGrid.setVgap(10);

        cardGrid.add(operationContainer, 1, 0);
        cardGrid.add(buttonBox, 2, 0);
        
        // Set the percentage of width of each column
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(8); 
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(40); 
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(8); 
        cardGrid.getColumnConstraints().addAll(col1, col2, col3);
        
        Insets cardMargin = new Insets(0,0,30,0);
        Card.setMargin(cardGrid, cardMargin);
        Card.setMargin(cardBackground, cardMargin);

        
        cardGrid.prefWidthProperty().bind(cardBackground.widthProperty());
        cardGrid.heightProperty().addListener((obs, oldH, newH) -> {
            cardBackground.setHeight(newH.doubleValue());
        });
        
        cardGrid.widthProperty().addListener((obs, oldW, newW) -> {
        	cardBackground.setWidth(newW.doubleValue() * 0.6);
        });

        getChildren().addAll(cardBackground, cardGrid);
        
        
        setOnMouseEntered(event -> {
            cardBackground.setFill(Color.GRAY);
            setScaleX(1.1);
            setScaleY(1.1);
        });

        setOnMouseExited(event -> {
            cardBackground.setFill(Color.LIGHTGRAY);
            setScaleX(1.0);
            setScaleY(1.0);
        });
    }
    
    // Make the label objects for card
    private HBox makeLabel(String title, String text) {
    	HBox box = new HBox();
        Label titleLabel = new Label(title + ": ");
        Label textLabel = new Label(text);
        
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        textLabel.setFont(Font.font("Times New Roman", 16));
        
        if(title.equals("Content")) {
        	textLabel.setWrapText(true);
        	textLabel.maxWidthProperty().bind(widthProperty().multiply(0.3));
        }
        
        box.getChildren().addAll(titleLabel, textLabel);
        return box;
    }
    
 // Make the label objects for card
    private HBox makeLabel(String title, int time) {
    	HBox box = new HBox();
        Label titleLabel = new Label(title + ": ");
        
        int hour = time / 3600;
        time = time % 3600;
        int min = time / 60;
        int sec = time % 60;
        
        StringBuilder builder = new StringBuilder();
        if(hour>0) {
        	builder.append(hour + "h ");
        }
        if(min>0) {
        	builder.append(min + "min ");
        }
        builder.append(sec + "s");
        Label textLabel = new Label(builder.toString());
        
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        textLabel.setFont(Font.font("Times New Roman", 16));
        
        if(title.equals("Content")) {
        	textLabel.setWrapText(true);
        	textLabel.maxWidthProperty().bind(widthProperty().multiply(0.3));
        }
        
        box.getChildren().addAll(titleLabel, textLabel);
        return box;
    }
    
    // Make the button objects for card
    private Button makeButton(String path) {
    	Button button = new Button();
    	button.setStyle("-fx-background-radius: 25em; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px;");

    	Image icon = new Image(getClass().getResourceAsStream(path));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        button.setGraphic(imageView);
        
        return button;
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    
    public void modifyCard(Operation operation) {
    	Label content = (Label) contentBox.getChildren().get(1);
    	content.setText(operation.getContent());
    	
    	Label interval = (Label) intervalBox.getChildren().get(1);
    	int time = operation.getInterval();
    	int hour = time / 3600;
        time = time % 3600;
        int min = time / 60;
        int sec = time % 60;
        StringBuilder builder = new StringBuilder();
        if(hour>0) {
        	builder.append(hour + "h ");
        }
        if(min>0) {
        	builder.append(min + "min ");
        }
        builder.append(sec + "s");
        interval.setText(builder.toString());
        
        Label displayTime = (Label) displayTimeBox.getChildren().get(1);
        displayTime.setText(operation.getDisplayTime()+"s");
	}
    
    public void modifyIndex(int index) {
    	this.index = index;
    	indexLabel.setText(String.valueOf(index));
    }
}
