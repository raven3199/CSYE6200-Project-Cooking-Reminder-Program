# CSYE6200-Project-Cooking-Reminder-Program

### 项目结构：
- application: 项目主程序 `Main.java` 所在地，将整个项目跨页面间的通用方法尽量写在这里
- beans: 存放 Java Bean 的地方
- controllers: 存放页面控制器
- views: 存放 fxml 文件，即各个页面的地方
- resources: 存放静态资源的地方，例如 png 文件



### 注意事项：
- 通过 Scene Builder 构建页面时，尽可能在使用 `BorderPane` 或 `GridPane` 类型的 Pane 作为最底层的 Pane，从而使得页面内组件位置可以随着页面大小变化而变化
- 在 Scene Builder 中构建完页面之后，可以点击上方 View -> Show Sample Controller Skeleton，将骨架复制到 Controller 中。在此之前记得让各个组件绑定 ID 以及方法，整个 fxml 文件也不要忘记绑定对应 Controller
- 在声明 Java Bean 时，在其内部的几大基本类型，尽量使用 `ObjectProperty` 类而非基本类型。例如，使用 `StringProperty` 类，而非 `String` 类。这样可能对于之后添加监听器有好处，但目前具体有什么好处我还不确定，但总之相关写法可以参考我写好的 Java Bean
- 在每个方法上尽可能写注释，介绍一下方法的作用。变量命名可以长一点，尽可能体现其功能



### 项目流程图：
以下为我设想的项目基础流程，以及一些 Java Bean，如果有其他意见可以进行讨论
![6200Project - Cooking Reminder](https://github.com/raven3199/CSYE6200-Project-Cooking-Reminder-Program/assets/93770915/17c5082c-0165-466f-8adc-e58474ebcca0)



### 项目分工：

- **张润东**：完善程序主体，完成从开始 -> 准备阶段 -> 步骤输入阶段一系列页面及其对应方法。
-  **熊传炜**：完成程序执行页面，即点击开始之后程序按时间在页面上显示操作步骤。需要有暂停功能，以及分为两种模式：有手操作模式与无手操作模式。有手操作模式下，从一步到下一步需要用户点击；无手操作模式下，提示显示一定时间（例如30秒）后自动跳转至下一步。
- **王子帆**：完成菜谱保存与读取功能，设计保存格式（如JSON）。
