package beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Operation {
	private IntegerProperty index;  // Index of the operation
	private StringProperty content;  // Content of the operation
	private IntegerProperty interval;  // Milliseconds from last operation
	private IntegerProperty displayTime;  // Milliseconds of content display

	public Operation() {
		this.index = new SimpleIntegerProperty();
		this.content = new SimpleStringProperty();
		this.interval = new SimpleIntegerProperty();
		this.displayTime = new SimpleIntegerProperty();
	}

	public int getIndex() {
		return this.index.get();
	}

	public void setIndex(int index) {
		this.index.set(index);
	}

	public String getContent() {
		return this.content.get();
	}

	public void setContent(String content) {
		this.content.set(content);
	}

	public int getInterval() {
		return this.interval.get();
	}

	public void setInterval(int interval) {
		this.interval.set(interval);
	}

	public int getDisplayTime() {
		return this.displayTime.get();
	}

	public void setDisplayTime(int displayTime) {
		this.displayTime.set(displayTime);
	}
	
	public IntegerProperty getIndexProperty() {
		return this.index;
	}
	
	public StringProperty getContentProperty() {
		return this.content;
	}
	
	public IntegerProperty getIntervalProperty() {
		return this.interval;
	}
	
	public IntegerProperty getDisplayTimeProperty() {
		return this.displayTime;
	}
}
