package cookbook;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ShoppingCartScene {

  ListView<Pane> editIng = new ListView<>();
  ComboBox<String> weeksBox = new ComboBox<>();
  ComboBox<String> shoppingLstBox = new ComboBox<>();
  Text recipesText = new Text();
  Text LstExistsText = new Text();
  Button backButton = new Button();
  Button weeklyButton = new Button();
  Button shoppingButton = new Button();
  Button saveButton = new Button();
  Button deleteButton = new Button();
  Image book = new Image("file:app/images/book.jpg");
  ImageView bookView = new ImageView(book);
  Image backArrow = new Image("file:app/images/back_arrow.png");
  ImageView backArrowView = new ImageView(backArrow);
  Pane pane = new Pane();

  StackPane root = new StackPane();
  Query query = new Query();
  int chosenWeek;
  int chosenShpLst;
  int shoppingCartID;
  int[] weekIDs;
  int[] shoppingIDs;
  ArrayList<String[]> shoppingCartLst = new ArrayList<String[]>();
  String thefont;



  public void start(Stage primaryStage, User user, String thefont) {

    this.thefont = thefont;

    display();
    buttonHandler(primaryStage, user);
    
    updateDatabaseVariables(user);

    showWeeksBox(weekIDs);
    showShoppingBox(shoppingIDs);

    pane.getChildren().addAll(editIng, backButton, recipesText, weeklyButton, weeksBox, saveButton, shoppingLstBox, shoppingButton, LstExistsText, deleteButton);

    root.getChildren().add(bookView);
    root.getChildren().add(pane);
    
    primaryStage.setScene(new Scene(root, 1229, 761));
    primaryStage.setTitle("Silicon Cookbook");
    primaryStage.setResizable(false);
    primaryStage.show();
    
  }

  private void display() {
    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(110);
    backButton.setLayoutY(20);
    backButton.setGraphic(backArrowView);
    
    weeksBox.setPrefSize(275, 25);
    weeksBox.setLayoutY(250);
    weeksBox.setLayoutX(135);
    weeksBox.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    weeksBox.setPromptText("Choose a week:");
    weeksBox.getSelectionModel().selectFirst();

    shoppingLstBox.setPrefSize(275, 25);
    shoppingLstBox.setLayoutX(135);
    shoppingLstBox.setLayoutY(475);
    shoppingLstBox.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    shoppingLstBox.setPromptText("Choose a saved shopping list:");

    weeklyButton.setFont(Font.font(thefont, 14));
    weeklyButton.setPrefSize(70, 33);
    weeklyButton.setLayoutX(540);
    weeklyButton.setLayoutY(250);
    weeklyButton.setText("Select");

    shoppingButton.setFont(Font.font(thefont, 14));
    shoppingButton.setPrefSize(70, 33);
    shoppingButton.setLayoutX(540);
    shoppingButton.setLayoutY(475);
    shoppingButton.setText("Select");

    editIng.setLayoutX(660);
    editIng.setLayoutY(140);
    editIng.setPrefSize(452, 475);
    editIng.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    editIng.setEditable(false);

    saveButton.setVisible(false);
    saveButton.setLayoutX(845);
    saveButton.setLayoutY(630);
    saveButton.setPrefSize(80, 50);
    saveButton.setStyle("-fx-font-size: 16; -fx-font-family: '" + thefont + "';");
    saveButton.setText("Save");

    deleteButton.setLayoutX(135);
    deleteButton.setLayoutY(525);
    deleteButton.setPrefSize(70, 33);
    deleteButton.setStyle("-fx-font-size: 10pt;");
    deleteButton.setText("Delete");

    recipesText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    recipesText.setWrappingWidth(600);
    recipesText.setTextAlignment(TextAlignment.CENTER);
    recipesText.setLayoutX(600);
    recipesText.setLayoutY(120);
    recipesText.setText("Recipes and ingredients:");
    recipesText.setFill(Color.BLACK);

    LstExistsText.setVisible(false);
    LstExistsText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    LstExistsText.setWrappingWidth(500);
    LstExistsText.setLayoutX(720);
    LstExistsText.setLayoutY(700);
    LstExistsText.setFill(Color.RED);
    LstExistsText.setText("That weeks shopping list already exists");
    
 }
  

  private void buttonHandler(Stage primaryStage, User user) {

    // Saves the modified weekly list to the database 
    saveButton.setOnAction(e -> {
      LstExistsText.setVisible(false);
      if(!shoppingLstBox.getItems().contains("Shopping list week: " + String.valueOf(chosenWeek))) {
        try {
          
          shoppingCartLst = getAllIngrediens();
          query.saveShoppingCart(user.getId(), chosenWeek);

          
          for (String[] ing : shoppingCartLst) {
            int ingID = query.getIngredientID(ing[0], ing[2]);
            shoppingCartID = query.getShoppingCartID(user.getId(), chosenWeek);
            query.addShoppingCartIngredients(ingID, shoppingCartID, Float.parseFloat(ing[1]));
          }
        } catch (SQLException e3) {
          e3.printStackTrace();
        }
        shoppingLstBox.getItems().clear();
        updateDatabaseVariables(user);
        showShoppingBox(shoppingIDs);
        saveButton.setVisible(false);
      } else {
        LstExistsText.setVisible(true);
      }
    });

    String[] weekDayLst = {"mondayfood","tuesdayfood","wednesdayfood","thursdayfood","fridayfood","saturdayfood","sundayfood"};

    // Adds the chosen weekly lists ingredients to the listview.
    weeklyButton.setOnAction(e -> {
      LstExistsText.setVisible(false);
      saveButton.setVisible(true);
      editIng.getItems().clear();
      int[] weekRecipeLst = {};
      ArrayList<String[]> ingredientLst = new ArrayList<>();

      // Goes through all the week days to get each days recipies and their ingredients.
      for (String day : weekDayLst) {
        try {
          weekRecipeLst = query.getWeekDay(day, user.getId(), chosenWeek);
          int n = 1;
          int mealAmount = 4;
          while (n != 4) {
            n++;
            mealAmount++;

          ingredientLst = query.getIngredients(weekRecipeLst[n]);
          for (String[] i : ingredientLst){
            addToList(i, weekRecipeLst[mealAmount]);
          }
        }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
    }  
  });

  // Adds all the ingredients from the chosen shoppingcart to the listview.
  shoppingButton.setOnAction(e -> {
    LstExistsText.setVisible(false);
    saveButton.setVisible(false);
    editIng.getItems().clear();
    ArrayList<String[]> shpIngs = new ArrayList<>();

    try {
      int shpID = query.getShoppingCartID(user.getId(), chosenShpLst);
      shpIngs = query.getShoppingCartIngredients(shpID);

      for (String[] ing : shpIngs) {
        addToList(ing, 2);
      }
    } catch (SQLException e4) {
      e4.printStackTrace();
    }

  });

  // Select a specific week
  weeksBox.setOnAction(e -> {
      
    for (int i  : weekIDs) {
      if (weeksBox.getSelectionModel().getSelectedItem().equals("Week " + i)) {
        chosenWeek = i;
      }

  }
  });
  
  // Select a specific shopping list.
  shoppingLstBox.setOnAction(e -> {
    if(shoppingLstBox.getSelectionModel().getSelectedItem() != null) {
      for (int i  : shoppingIDs) {
        if (shoppingLstBox.getSelectionModel().getSelectedItem().equals("Shopping list week: " + i)) {
          chosenShpLst = i;
        }
      }
    }
    });

    // Back button to MyPagScene.
    backButton.setOnAction(e -> {
      MyPageScene mps = new MyPageScene();
      try {
       mps.start(primaryStage, user, thefont);
      } catch (Exception e1) {
        System.out.println("problem with changing stage.");
        e1.printStackTrace();
      }
    });

    // Deletes the selected shopping list from the database.
    deleteButton.setOnAction(e -> {
      try {
        query.delShoppingCart(user.getId(), chosenShpLst);
        query.delShoppingCartIngredients(shoppingCartID);
        editIng.getItems().clear();
        updateDatabaseVariables(user);
        showShoppingBox(shoppingIDs);
      } catch (SQLException e5) {
        e5.printStackTrace();
      }
    });

  }

  /**
   * Adds all the weeks to the combobox.
   * @param weekIDs Ids for weekly lists.
   */
  private void showWeeksBox(int[] weekIDs) {
    weeksBox.getItems().clear();
    for (int week : weekIDs) {
      weeksBox.getItems().add("Week " + week);
    }

  }

  /**
   * Adds all the shopping lists to the combobox.
   * @param shoppingIDs Ids for shopping lists.
   */
  private void showShoppingBox(int[] shoppingIDs) {
    shoppingLstBox.getItems().clear();
    for (int shpLst : shoppingIDs) {
      shoppingLstBox.getItems().add("Shopping list week: " + shpLst);
    }

  }

  /**
   * Gets the weekly and shopping lists from the database.
   * @param user
   */
  private void updateDatabaseVariables(User user) {
    try {
      weekIDs = query.getWeekNumbers(user.getId());
      shoppingIDs = query.getAllShoppingCarts(user.getId());
    } catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Checks if the ingredient already exists and if it does it adds the amount to the exitsting one.
   * @param ingLst the ingredient and its amount.
  */

  public void addToList(String[] ingLst, int mealAmount) {
    int index = -1;
    boolean isDuplicate = false;

    int size = editIng.getItems().size();

    if (size > 0) {
      for (Pane i : editIng.getItems()){
        index++;
        Pane temp = i;
        Text ing = (Text) temp.getChildren().get(0);
        Text amount = (Text) temp.getChildren().get(1);
        if (ing.getText().equals(ingLst[0])) {
          Float value = Float.parseFloat(amount.getText()) + Float.parseFloat(ingLst[1]) * (mealAmount/2);
          amount.setText(String.valueOf(value));
          editIng.getItems().set(index, i);
          isDuplicate = true;
        }
      }
  } 
    if (!isDuplicate) {
      Float ingAmount = Float.parseFloat(ingLst[1]) * (mealAmount/2);
      ingLst[1] = String.valueOf(ingAmount);
      addIngredients(ingLst[0], ingLst[1], ingLst[2]);
    }

  }

  /**
   * Creates the panes for the listview.
   * @param impIng ingredients name.
   * @param impAmount ingredients amount.
   * @param impMeasure ingredients measure.
   */
  public void addIngredients(String impIng, String impAmount, String impMeasure){
    
    editIng.getItems().add(new Pane());
    int Size = editIng.getItems().size();
    Pane lastPane = editIng.getItems().get(Size - 1);
    lastPane.setPrefSize(300, 40);

    lastPane.getChildren().add(new Text());
    Size = lastPane.getChildren().size();
    Text ingBox = (Text) lastPane.getChildren().get(Size - 1);
    ingBox.setLayoutY(30);
    ingBox.setLayoutX(0);
    ingBox.setStyle("-fx-font-size: 10pt;");
    ingBox.setText(impIng);

    lastPane.getChildren().add(new Text());
    Size = lastPane.getChildren().size();
    Text amountText = (Text) lastPane.getChildren().get(Size - 1);
    amountText.setLayoutY(30);
    amountText.setLayoutX(170);
    amountText.setStyle("-fx-font-size: 10pt;");
    amountText.setText(impAmount);

    lastPane.getChildren().add(new Text());
    Size = lastPane.getChildren().size();
    Text measureText = (Text) lastPane.getChildren().get(Size - 1);
    measureText.setLayoutY(30);
    measureText.setLayoutX(250);
    measureText.setStyle("-fx-font-size: 10pt;");
    measureText.setText(impMeasure);

    lastPane.getChildren().add(new Button());
    Size = lastPane.getChildren().size();
    
    Button deleteButton = (Button) lastPane.getChildren().get(Size - 1);
    deleteButton.setPrefSize(45, 25);
    deleteButton.setLayoutY(15);
    deleteButton.setLayoutX(350);
    deleteButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));
    deleteButton.setText("Delete");
    deleteButton.setOnAction(e -> {
      editIng.getItems().remove(lastPane);
    });
  }

  /**
   * Gets all the ingredients information from each pane in the listview.
   * @return
   */

  private ArrayList<String[]> getAllIngrediens(){
    ArrayList<String[]> iAM = new ArrayList<>();
    int size = editIng.getItems().size();
    for(int i = 0; i < size; i++){
      Pane temp = editIng.getItems().get(i);
      Text ing = (Text) temp.getChildren().get(0);
      Text amount = (Text) temp.getChildren().get(1);
      Text mes = (Text) temp.getChildren().get(2);
      String [] tempStr = {ing.getText(), amount.getText(), mes.getText()};
      iAM.add(tempStr);

    }
    return iAM;
  }


}
