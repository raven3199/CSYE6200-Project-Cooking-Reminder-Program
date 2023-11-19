package beans;

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

    public Card(int index, String content, String interval, int displayTime) {
        Rectangle cardBackground = new Rectangle(200, 150);
        cardBackground.setArcHeight(20);
        cardBackground.setArcWidth(20);
        cardBackground.setFill(Color.LIGHTGRAY);
        
        // The label of the index of the card
        Label indexLabel = new Label(String.valueOf(index));        
        indexLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        
        // The content of the card, including operation, interval and display time
        HBox contentBox = makeLabel("Content", content);
        HBox intervalBox = makeLabel("Interval", interval);
        HBox displayTimeBox = makeLabel("Display Time", String.valueOf(displayTime));

        VBox operationContainer = new VBox();
        operationContainer.setSpacing(30);
        VBox.setMargin(contentBox, new Insets(10, 0, 0, 0));
        VBox.setMargin(displayTimeBox, new Insets(0, 0, 10, 0));
        
        operationContainer.getChildren().addAll(contentBox, intervalBox, displayTimeBox);
        operationContainer.setAlignment(Pos.CENTER_LEFT);

        
        // The buttons, for change the content and delete the card 
        HBox buttonBox = new HBox();
        Button modifyButton = makeButton("/resources/Modify_Icon.png");
        Button cancalButton = makeButton("/resources/Cancal_Icon.png");
        
        buttonBox.setSpacing(30);
        buttonBox.getChildren().addAll(modifyButton, cancalButton);
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
}
