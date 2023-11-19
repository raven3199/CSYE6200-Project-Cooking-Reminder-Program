# CSYE6200-Project-Cooking-Reminder-Program

### Project structure:
- application: The main program file `Main.java` is where common methods across pages throughout the entire project should be written as much as possible.
- beans: The place where Java Beans are stored.
- controllers: The location for storing page controllers.
- views: Location for storing FXML files, i.e., the place for each page.
- resources: Location for storing static resources, such as PNG files.



### Points to note:
- When constructing pages through Scene Builder, it is advisable to use `BorderPane` or `GridPane` types of Panes as the bottommost layer. This allows the positions of components within the page to adjust with changes in the page size.
- After constructing the page in Scene Builder, you can click on View -> Show Sample Controller Skeleton at the top to copy the skeleton into the Controller. Before doing this, make sure to bind IDs and methods to various components, and don't forget to associate the corresponding Controller with the entire FXML file.
- When declaring a Java Bean, within its internal basic types, it is advisable to use the `ObjectProperty` class instead of primitive types. For example, use `StringProperty` instead of `String`. This may be beneficial for adding listeners later on, although the specific advantages might not be clear at the moment. In any case, you can refer to the Java Bean I've written for the relevant syntax.
- It's a good practice to include comments on each method, providing an explanation of its purpose. When naming variables, opt for longer names that effectively convey their functionality.



### Project flowchart:
Certainly! Feel free to share your envisioned project's basic flow and any Java Beans you have in mind. I'd be happy to discuss and provide feedback or suggestions.
![6200Project - Cooking Reminder](https://github.com/raven3199/CSYE6200-Project-Cooking-Reminder-Program/assets/93770915/17c5082c-0165-466f-8adc-e58474ebcca0)



### Project division of tasks:

- **Rundong, Zhang**： Complete the core of the program, including a series of pages and their corresponding methods for the entire process from the start -> preparation phase -> step input phase.
-  **Chuanwei, Xiong**： Complete the execution page of the program, where after clicking "Start," the program displays operational steps on the page over time. It should include a pause function and be divided into two modes: manual operation mode and automatic operation mode. In manual operation mode, the user needs to click to proceed from one step to the next. In automatic operation mode, after a certain time (e.g., 30 seconds), the prompt should automatically transition to the next step.
- **Zifan, Wang**： Complete the recipe saving and loading functionality, and design the saving format, such as using JSON.
