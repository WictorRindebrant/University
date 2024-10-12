package cookbook;

import java.sql.SQLException;

import java.util.Arrays;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;

/**
 * Creates the scene to display the week meal plans and interact with them.
 */
public class WeekListScene {
  StackPane root = new StackPane();

  Pane pane = new Pane();

  int largeButtonWidth = 300;
  int buttonHeight = 30;
  int smallButtonWidth = 100;
  int leftAmountXloc = 480;
  int leftRecipeXloc = 160;
  int rightAmountXloc = 1000;
  int rightRecipeXloc = 680;
  int currentWeek = 0;

  Text weekMealPlan = new Text();

  Button backButton = new Button();
  Button newWeek = new Button();
  Button delWeek = new Button();
  Button editWeek = new Button();
  Button addToShopping = new Button();

  TextArea displayWeek = new TextArea();

  Image backArrow = new Image("file:app/images/back_arrow.png");
  Image book = new Image("file:app/images/book.jpg");

  ImageView backArrowView = new ImageView(backArrow);
  ImageView bookView = new ImageView(book);

  ListView<String> weekView = new ListView<>();

  Query query = new Query();

  String thefont;

  public void start(Stage weekListStage, User user, String thefont) throws SQLException {

    this.thefont = thefont;

    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(120);
    backButton.setLayoutY(40);
    backButton.setGraphic(backArrowView);
    backButton.setOnAction(event -> {
      MyPageScene mps = new MyPageScene();
      try {
        mps.start(weekListStage, user, thefont);
      } catch (Exception e) {
        System.out.println("problem with changing stage.");
        e.printStackTrace();
      }
      event.consume();
      ;
    });

    weekMealPlan.setLayoutX(220);
    weekMealPlan.setLayoutY(120);
    weekMealPlan.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    weekMealPlan.setText("Weeks meal plan");

    setWeekView(user);

    newWeek.setLayoutX(leftAmountXloc);
    newWeek.setLayoutY(600);
    newWeek.setPrefSize(smallButtonWidth, 25);
    newWeek.setText("New");
    newWeek.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    newWeek.setOnAction(event -> {
      WeekEditScene wes = new WeekEditScene();
      try {
        wes.start(weekListStage, user, 0, thefont);
      } catch (Exception e) {
        System.out.println("problem with changing stage.");
        e.printStackTrace();
      }
      event.consume();
    });

    editWeek.setLayoutX(320);
    editWeek.setLayoutY(600);
    editWeek.setPrefSize(smallButtonWidth, 25);
    editWeek.setText("Edit");
    editWeek.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    editWeek.setOnAction(event -> {
      WeekEditScene wes = new WeekEditScene();
      try {
        if (currentWeek != 0) {
          wes.start(weekListStage, user, currentWeek, thefont);
        }
      } catch (Exception e) {
        System.out.println("problem with changing stage.");
        e.printStackTrace();
      }
      event.consume();
    });

    delWeek.setLayoutX(leftRecipeXloc);
    delWeek.setLayoutY(600);
    delWeek.setPrefSize(smallButtonWidth, 25);
    delWeek.setText("Delete");
    delWeek.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    delWeek.setOnAction(event -> {
      try {
        query.delWeek(user.getId(), currentWeek);
        currentWeek = 0;
        pane.getChildren().remove(displayWeek);

        WeekListScene wls = new WeekListScene();
        wls.start(weekListStage, user, thefont);
        
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });

    displayWeek.setLayoutX(670);
    displayWeek.setLayoutY(160);
    displayWeek.setPrefSize(420, 420);
    displayWeek.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");

    addToShopping.setLayoutX(670);
    addToShopping.setLayoutY(600);
    addToShopping.setPrefSize(420, 25);
    addToShopping.setText("Add to shopping list");
    addToShopping.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    addToShopping.setOnAction(event -> {

      ShoppingCartScene shoppingCartScene = new ShoppingCartScene();
      try {
        shoppingCartScene.start(weekListStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }

    });

    pane.getChildren().addAll(editWeek, backButton, newWeek, delWeek, weekMealPlan,
        weekView);

    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    weekListStage.setScene(new Scene(root, 1229, 761));
    weekListStage.setTitle("Silicon Cookbook");
    weekListStage.setResizable(false);
    weekListStage.show();
  }

  /**
   * Set the attributes and objects for the list view of the weeks.
   * 
   * @param user
   * @throws SQLException
   */
  private void setWeekView(User user) throws SQLException {
    int[] AllWeeks = query.getWeekNumbers(user.getId());
    Arrays.sort(AllWeeks);
    String[] strAllWeeks = new String[AllWeeks.length];
    for (int i = 0; i < AllWeeks.length; i++) {
      strAllWeeks[i] = "Week " + AllWeeks[i];
    }

    weekView.getItems().clear();
    weekView.setLayoutX(160);
    weekView.setLayoutY(160);
    weekView.setPrefWidth(420);
    weekView.setMaxHeight(420);
    weekView.getItems().addAll(strAllWeeks);
    weekView.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    weekView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
      currentWeek = Integer.parseInt(newValue.split(" ")[1]);
      pane.getChildren().removeAll(displayWeek, addToShopping);
      try {
        setDisplay(user);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Sets weeks information display.
   * 
   * @param user the user object.
   * @throws SQLException
   */
  private void setDisplay(User user) throws SQLException {
    pane.getChildren().addAll(displayWeek, addToShopping);
    int id = user.getId();
    displayWeek.clear();

    // Append data to display
    displayWeek.appendText("Monday:\n");
    ifDisplay(query.getWeekDay("mondayfood", id, currentWeek));
    displayWeek.appendText("Tuesday:\n");
    ifDisplay(query.getWeekDay("tuesdayfood", id, currentWeek));
    displayWeek.appendText("Wednesday:\n");
    ifDisplay(query.getWeekDay("wednesdayfood", id, currentWeek));
    displayWeek.appendText("Thursday:\n");
    ifDisplay(query.getWeekDay("thursdayfood", id, currentWeek));
    displayWeek.appendText("Friday:\n");
    ifDisplay(query.getWeekDay("fridayfood", id, currentWeek));
    displayWeek.appendText("Saturday:\n");
    ifDisplay(query.getWeekDay("saturdayfood", id, currentWeek));
    displayWeek.appendText("Sunday:\n");
    ifDisplay(query.getWeekDay("sundayfood", id, currentWeek));
  }

  /**
   * Check if data should be displayed.
   * 
   * @param data the days information.
   * @throws SQLException
   */
  private void ifDisplay(int[] data) throws SQLException {
    if (data[2] > 0) {
      displayWeek.appendText("    Breakfast: " + query.getRecipe(data[2])[1]);
      if (data[5] > 0) {
        displayWeek.appendText("    Amount: " + data[5] + "\n");
      } else
        displayWeek.appendText("\n");
    }
    if (data[3] > 0) {
      displayWeek.appendText("    Luch: " + query.getRecipe(data[3])[1]);
      if (data[6] > 0) {
        displayWeek.appendText("    Amount: " + data[6] + "\n");
      } else
        displayWeek.appendText("\n");
    }
    if (data[4] > 0) {
      displayWeek.appendText("    Dinner: " + query.getRecipe(data[4])[1]);
      if (data[7] > 0) {
        displayWeek.appendText("    Amount: " + data[7] + "\n");
      } else
        displayWeek.appendText("\n");
    }
  }
}
