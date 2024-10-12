package cookbook;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateRecipeScene {
  private StackPane root = new StackPane();
  private Pane pane = new Pane();

  Button backButton = new Button();
  Button saveButton = new Button();
  Button createIngButton = new Button();
  Image backArrow = new Image("file:app/images/back_arrow.png");
  ImageView backArrowView = new ImageView(backArrow);

  Text errorMsg = new Text();
  Text CreatPageText = new Text();
  Text recipeNameText = new Text();
  Text recipeTagText = new Text();
  Text recipeIngredientsText = new Text();
  Text recipeToDoText = new Text();
  Text descriptionText = new Text();

  TextArea descriptionField = new TextArea();
  TextArea recipeToDoField = new TextArea();
  TextArea recipeIngredientsField = new TextArea();
  TextArea recipeNameField = new TextArea();

  ListView<Pane> editIng = new ListView<>();
  ComboBox<String> tagBox = new ComboBox<>();
  ComboBox<String> createIng = new ComboBox<>();
  ComboBox<String> createMeasure = new ComboBox<>();
  TextField createAmount = new TextField();
  String chosenTag = null;
  Query q = new Query();

  String thefont;

  /**
   * Creat recipe and saves the to the database.
   */
  public void start(Stage primaryStage, User user, String thefont) throws SQLException {

    this.thefont = thefont;

    pane.getChildren().addAll(CreatPageText, backButton, recipeNameText, recipeNameField, recipeTagText,
        recipeIngredientsText, recipeToDoField, recipeToDoText, errorMsg, saveButton, tagBox, editIng, createAmount,
        createIng, createMeasure, createIngButton, descriptionField, descriptionText);

    CreatPageText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    CreatPageText.setLayoutX(250);
    CreatPageText.setLayoutY(70);
    CreatPageText.setText("Create recipe:");
    CreatPageText.setFill(Color.BLACK);

    saveButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    saveButton.setPrefSize(160, 33);
    saveButton.setLayoutX(800);
    saveButton.setLayoutY(600);
    saveButton.setText("SAVE");

    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(120);
    backButton.setLayoutY(40);
    backButton.setGraphic(backArrowView);

    recipeNameText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    recipeNameText.setLayoutX(135);
    recipeNameText.setLayoutY(130);
    recipeNameText.setText("Recipe name:");
    recipeNameText.setFill(Color.BLACK);

    recipeNameField.setLayoutX(132);
    recipeNameField.setLayoutY(140);
    recipeNameField.setPrefSize(220, 8);
    recipeNameField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    recipeNameField.setEditable(true);
    recipeNameField.setPromptText("Enter the name of the recipe");

    recipeTagText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    recipeTagText.setLayoutX(450);
    recipeTagText.setLayoutY(130);
    recipeTagText.setText("Selecte a tag:");
    recipeTagText.setFill(Color.BLACK);

    recipeIngredientsText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    recipeIngredientsText.setLayoutX(135);
    recipeIngredientsText.setLayoutY(200);
    recipeIngredientsText.setText("Ingredients:");
    recipeIngredientsText.setFill(Color.BLACK);

    tagBox.getItems().addAll("Select a tag");
    for (String tag : q.getTags()) {
      tagBox.getItems().addAll(tag);
    }

    tagBox.setPrefSize(100, 20);
    tagBox.setLayoutX(450);
    tagBox.setLayoutY(140);
    tagBox.getSelectionModel().selectFirst();

    recipeToDoText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    recipeToDoText.setLayoutX(660);
    recipeToDoText.setLayoutY(130);
    recipeToDoText.setText("Instructions");
    recipeToDoText.setFill(Color.BLACK);

    editIng.setLayoutX(135);
    editIng.setLayoutY(260);
    editIng.setPrefSize(452, 300);
    editIng.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    editIng.setEditable(false);

    createIng.setPrefSize(210, 25);
    createIng.setLayoutY(215);
    createIng.setLayoutX(130);
    createIng.setStyle("-fx-font-size: 10pt;");
    createIng.setPromptText("Choose an ingrediens:");
    createIng.getSelectionModel().selectFirst();

    createAmount.setPrefSize(70, 25);
    createAmount.setLayoutY(215);
    createAmount.setLayoutX(340);
    createAmount.setStyle("-fx-font-size: 10pt;");
    createAmount.setPromptText("Amount");
    createAmount.setEditable(true);

    createIngButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    createIngButton.setPrefSize(75, 30);
    createIngButton.setLayoutX(480);
    createIngButton.setLayoutY(215);
    createIngButton.setText("ADD");

    createMeasure.setPrefSize(72, 25);
    createMeasure.setLayoutY(215);
    createMeasure.setLayoutX(410);
    createMeasure.setPromptText("Measurs");
    createMeasure.setStyle("-fx-font-size: 10pt;");

    recipeToDoField.setLayoutX(660);
    recipeToDoField.setLayoutY(140);
    recipeToDoField.setPrefSize(452, 250);
    recipeToDoField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    recipeToDoField.setEditable(true);
    recipeToDoField.setPromptText("Enter instructions. Use 1: 2: :... to enter diffrent steps");

    descriptionText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    descriptionText.setLayoutX(660);
    descriptionText.setLayoutY(450);
    descriptionText.setText("Description");
    descriptionText.setFill(Color.BLACK);

    descriptionField.setLayoutX(660);
    descriptionField.setLayoutY(460);
    descriptionField.setPrefSize(452, 100);
    descriptionField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    descriptionField.setEditable(true);
    descriptionField.setPromptText("Enter a description to the recipe:");

    fillCreateViews();
    buttonAuctions(primaryStage, user);

    Image book = new Image("file:app/images/book.jpg");
    final ImageView bookView = new ImageView(book);

    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    primaryStage.setScene(new Scene(root, 1229, 761));
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  // Handel all the button auctions
  public void buttonAuctions(Stage primaryStage, User user) throws SQLException {
    saveButton.setOnAction(e -> {
      if (!recipeNameField.getText().isEmpty()
          && !recipeToDoField.getText().isEmpty()) {
        try {
          q.addRecipe(recipeNameField.getText(), recipeToDoField.getText(), chosenTag, descriptionField.getText());
          for (String[] i : getAddIngrediens()) {
            q.addQuantity(q.getIngredientID(i[0], i[2]),
                q.getRecipeID(recipeNameField.getText()), Double.parseDouble(i[1]));
          }

        } catch (SQLException e1) {
          System.out.println("det funkar inte");
          e1.printStackTrace();
        }
        MyPageScene mps = new MyPageScene();
        try {
          mps.start(primaryStage, user, thefont);
        } catch (Exception e1) {
          System.out.println("problem with changing stage.");
          e1.printStackTrace();
        }
      } else {
        errorMsg.setFont(Font.font(thefont, FontWeight.BOLD, 25));
        errorMsg.setWrappingWidth(600.0);
        errorMsg.setLayoutX(525.0);
        errorMsg.setLayoutY(600.0);
        errorMsg.setText("You need to fill all fields");
        errorMsg.setFill(Color.RED);
      }
    });

    backButton.setOnAction(e -> {
      MyPageScene mps = new MyPageScene();
      try {
        mps.start(primaryStage, user, thefont);
      } catch (Exception e1) {
        System.out.println("problem with changing stage.");
        e1.printStackTrace();
      }
    });

    tagBox.setOnAction(e -> {
      // search for tags
      if (tagBox.getValue().contains("Select a tag")) {

      } else {
        chosenTag = tagBox.getValue();
      }
    });

    createIngButton.setOnAction(e -> {
      try {
        String impIng = createIng.getSelectionModel().getSelectedItem();
        String impAmount = createAmount.getText();
        String impMeasure = createMeasure.getSelectionModel().getSelectedItem();
        if (!(impIng == null || impAmount.isEmpty() || impMeasure == null)) {
          addIngredients(impIng, impAmount, impMeasure);
          createIng.getSelectionModel().clearSelection();
          createAmount.setText("");
          createMeasure.getSelectionModel().clearSelection();
        }
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    });

    createIng.setOnAction(e -> {
      try {
        createMeasure.getItems().clear();
        for (String[] info : q.getAllIngredients()) {
          if (createIng.getSelectionModel().getSelectedItem() != null) {
            if (createIng.getSelectionModel().getSelectedItem().equals(info[0])) {
              createMeasure.getItems().add(info[1]);
            }
            createMeasure.getSelectionModel().clearSelection();
            createMeasure.getSelectionModel().selectFirst();
          }
        }
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    });
  }

  public void addIngredients(String impIng, String impAmount, String impMeasure) throws SQLException {

    editIng.getItems().add(new Pane());
    int Size = editIng.getItems().size();
    Pane lastPane = editIng.getItems().get(Size - 1);
    lastPane.setPrefSize(423, 40);

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
    deleteButton.setLayoutX(383);
    deleteButton.setFont(Font.font(thefont, FontWeight.BOLD, 10));
    deleteButton.setText("Delete");
    deleteButton.setOnAction(e -> {
      editIng.getItems().remove(lastPane);
    });
  }

  // Fills the ingredents list.
  private void fillCreateViews() throws SQLException {
    for (String[] info : q.getAllIngredients()) {
      int ingSize = createIng.getItems().size();
      boolean duplicate = false;
      for (int i = 0; i < ingSize; i++) {
        String objectName = createIng.getItems().get(i);
        if (objectName.equals(info[0])) {
          duplicate = true;
        }
      }
      if (duplicate == false) {
        createIng.getItems().add(info[0]);
      }
    }
  }

  // Creat an array list with all ingredients mesurs and amount.
  private ArrayList<String[]> getAddIngrediens() {
    ArrayList<String[]> iAM = new ArrayList<>();
    int size = editIng.getItems().size();
    for (int i = 0; i < size; i++) {
      Pane temp = editIng.getItems().get(i);
      Text ing = (Text) temp.getChildren().get(0);
      Text amount = (Text) temp.getChildren().get(1);
      Text mes = (Text) temp.getChildren().get(2);
      String[] tempStr = { ing.getText(), amount.getText(), mes.getText() };
      iAM.add(tempStr);

    }
    return iAM;
  }
}