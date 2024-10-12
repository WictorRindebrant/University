package cookbook;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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

public class RecipeScene {
  Pane pane = new Pane();
  Text myPageText = new Text();
  Text amountText = new Text();
  Text tagDropDown = new Text();
  Text tagCreatNew = new Text();
  Text erroMSG = new Text();
  Text tagRecipeDropDown = new Text();
  TextArea ingredients = new TextArea();
  TextArea instructions = new TextArea();
  TextArea addComment = new TextArea();
  TextArea addTag = new TextArea();

  Button plusButton = new Button();
  Button minusButton = new Button();
  Button backButton = new Button();
  Button favButton = new Button();
  Button addCommentButton = new Button();
  Button addTagButton = new Button();

  Image enterButton = new Image("file:app/images/email.png");
  Image favEmptyStar = new Image("file:app/images/empty_star.png");
  Image favFilledStar = new Image("file:app/images/filled_star.png");
  Image backArrow = new Image("file:app/images/back_arrow.png");
  Image book = new Image("file:app/images/book.jpg");
  ImageView enterButtonView = new ImageView(enterButton);
  ImageView backArrowView = new ImageView(backArrow);
  ImageView favEmptyStarView = new ImageView(favEmptyStar);
  ImageView favFilledStarView = new ImageView(favFilledStar);
  ImageView bookView = new ImageView(book);

  ListView<Pane> comments = new ListView<>();
  ComboBox<String> optionBox = new ComboBox<>();
  ComboBox<String> tagBox = new ComboBox<>();
  ComboBox<String> allTagBox = new ComboBox<>();
  ArrayList<String[]> ingList = new ArrayList<>();
  Query query = new Query();
  StackPane root = new StackPane();
  Integer portions = 2;
  String thefont;

  /**
   * In this function we decide what items there should 
   * be in the pane and then adding that to a stackpane 
   * with an image in the backround. We also call on all 
   * the function to handle how things are displayed and 
   * different kind of auctions.
   */
  public void start(Stage primaryStage, String recName, User user, String thefont) {

    this.thefont = thefont;

    pane.getChildren().addAll(amountText, plusButton, minusButton, backButton, myPageText,
        optionBox, instructions, comments, favButton, ingredients, addComment, addCommentButton);
    
    Recipe theRecipe = new Recipe(recName);

    display(primaryStage, theRecipe, user);
    buttonHandler(primaryStage, theRecipe, user);

    fillIngredients(theRecipe);
    fillInstructions(theRecipe);

    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    Scene s = new Scene(root);
    primaryStage.setScene(s);
  }

  /**
   * In this function we handle how things 
   * should be displayed with size, fonts, position and more.
   */
  public void display(Stage primStage, Recipe theRecipe, User user) {
    ingredients.setLayoutX(135);
    ingredients.setLayoutY(140);
    ingredients.setPrefSize(452, 550);
    ingredients.setStyle("-fx-font: 22 '" + thefont + "'; -fx-font-style: italic;");
    ingredients.setEditable(false);
    ingredients.setWrapText(true);

    instructions.setLayoutX(660);
    instructions.setLayoutY(140);
    instructions.setPrefSize(452, 550);
    instructions.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    instructions.setEditable(false);
    instructions.setWrapText(true);

    comments.setLayoutX(660);
    comments.setLayoutY(140);
    comments.setPrefSize(452, 450);
    comments.setStyle("-fx-font-size: 12; -fx-font-family: '" + thefont + "';");
    comments.setEditable(false);

    addComment.setLayoutX(660);
    addComment.setLayoutY(600);
    addComment.setPrefSize(400, 90);
    addComment.setStyle("-fx-font-size: 12; -fx-font-family: '" + thefont + "';");
    addComment.setEditable(true);
    addComment.setWrapText(true);
    addComment.setPromptText("Enter your comment here...");

    addCommentButton.setLayoutX(1060);
    addCommentButton.setLayoutY(600);
    addCommentButton.setStyle("-fx-font-size: 12; -fx-font-family: '" + thefont + "';");
    addCommentButton.setPrefSize(52, 90);
    addCommentButton.setGraphic(enterButtonView);

    tagDropDown.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
    tagDropDown.setLayoutX(660);
    tagDropDown.setLayoutY(395);
    tagDropDown.setText("Add existing tag");

    tagRecipeDropDown.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
    tagRecipeDropDown.setLayoutX(660);
    tagRecipeDropDown.setLayoutY(195);
    tagRecipeDropDown.setText(theRecipe.getName() + " has these tags");

    tagCreatNew.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
    tagCreatNew.setLayoutX(660);
    tagCreatNew.setLayoutY(295);
    tagCreatNew.setText("Creat and add new tag");

    erroMSG.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
    erroMSG.setFill(Color.RED);
    erroMSG.setLayoutX(740);
    erroMSG.setLayoutY(495);

    addTag.setLayoutX(660);
    addTag.setLayoutY(300);
    addTag.setPrefSize(200, 20);
    addTag.setStyle("-fx-font: 12 arial;");
    addTag.setEditable(true);
    addTag.setWrapText(true);
    addTag.setPromptText("Enter new tag...");

    addTagButton.setLayoutX(840);
    addTagButton.setLayoutY(550);
    addTagButton.setStyle("-fx-font: 12 arial;");
    addTagButton.setPrefSize(100, 50);
    addTagButton.setText("Add tag");;

    try {
      for (String t : query.getTagFromRecipe(theRecipe.getName()).split(":")) {
        tagBox.getItems().add(t);
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    tagBox.setPrefSize(150, 20);
    tagBox.setLayoutX(660);
    tagBox.setLayoutY(200);
    tagBox.getSelectionModel().selectFirst();

    try {
      for (String t : query.getTags()) {
        allTagBox.getItems().add(t);
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    allTagBox.setPrefSize(150, 20);
    allTagBox.setLayoutX(660);
    allTagBox.setLayoutY(400);
    allTagBox.setPromptText("Choose existing tag");

    pane.getChildren().removeAll(comments, addComment, addCommentButton);

    optionBox.getItems().addAll("Instructions", "Comments", "Add tag");
    optionBox.setPrefSize(452, 20);
    optionBox.setLayoutX(660);
    optionBox.setLayoutY(100);
    optionBox.getSelectionModel().selectFirst();

    myPageText.setFont(Font.font(thefont, FontWeight.BOLD, 30));
    // places the name of the recipe based on how many lines it is
    if (theRecipe.getName().toString().length() > 13) {
      myPageText.setLayoutY(60);
    } else {
      myPageText.setLayoutY(80);
    }
    myPageText.setLayoutX(250);
    myPageText.setText(theRecipe.getName());
    myPageText.setFill(Color.BLACK);
    myPageText.setWrappingWidth(220);
    myPageText.setTextAlignment(TextAlignment.CENTER);
    myPageText.setLineSpacing(-5);

    amountText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    amountText.setWrappingWidth(115);
    amountText.setLayoutX(307);
    amountText.setLayoutY(115);
    amountText.setTextAlignment(TextAlignment.CENTER);
    amountText.setText(portions + " portions");
    amountText.setFill(Color.BLACK);

    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(120);
    backButton.setLayoutY(40);
    backButton.setGraphic(backArrowView);

    plusButton.setFont(Font.font(thefont, FontWeight.BOLD, 13));
    plusButton.setPrefSize(20, 20);
    plusButton.setLayoutX(420);
    plusButton.setLayoutY(97);
    plusButton.setText("+");

    minusButton.setFont(Font.font(thefont, FontWeight.BOLD, 13));
    minusButton.setPrefSize(20, 20);
    minusButton.setLayoutX(282);
    minusButton.setLayoutY(97);
    minusButton.setText("-");

    favButton.setLayoutX(550);
    favButton.setLayoutY(20);
    favButton.setPrefSize(20, 20);
    try {
      if (query.isFavorites(user.getId(), theRecipe.getId())) {
        favButton.setGraphic(favFilledStarView);
      } else {
        favButton.setGraphic(favEmptyStarView);
      }
    } catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * In this function we have all the auctions for button events.
   */
  public void buttonHandler (Stage primStage, Recipe theRecipe, User user) {
    ArrayList<String[]> ingList = new ArrayList<>();
    try {
      ingList = query.getIngredients(theRecipe.getName());
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    final ArrayList<String[]> ingLista = ingList;

    addCommentButton.setOnAction(e -> {
      try {
        ArrayList<String[]> allComments = query.getComments();
        String[] lastComment = allComments.get(allComments.size() - 1);
        int newCommentId = Integer.parseInt(lastComment[0]) + 1;

        if (addComment.getText().equals("") == false) {
          addComment(user, user, addComment.getText(), newCommentId);
          int userId = user.getId();
          int recipeId = theRecipe.getId();
          String text = addComment.getText();
          try {
            query.addComment(userId, recipeId, text);
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
          addComment.setText("");
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    });

    addTagButton.setOnAction(e -> {
      try {
        MyPageScene mps = new MyPageScene();
        if (!addTag.getText().isEmpty()) {
          if (!query.getTags().contains(addTag.getText())) {
            String temp = query.getTagFromRecipe(theRecipe.getName()) + ":" + addTag.getText();
            query.addTag(theRecipe.getName(), temp);
            mps.start(primStage, user, thefont);
          } else {
            erroMSG.setText("The tag " + addTag.getText() + " already exist");
          }
        } else if (allTagBox.getSelectionModel().getSelectedItem() != null) {
          if (!query.getTagFromRecipe(theRecipe.getName()).contains(allTagBox.getSelectionModel().getSelectedItem())) {
            String temp = query.getTagFromRecipe(theRecipe.getName()) + ":" + allTagBox.getSelectionModel().getSelectedItem();
            query.addTag(theRecipe.getName(), temp);
            mps.start(primStage, user, thefont);
          } else {
            erroMSG.setText("The recipe already have that tag");
          }
        }
      } catch (Exception e3) {
        System.out.println(e3);
      }
    });

    optionBox.setOnAction(e -> {
      // search for tags
      if (optionBox.getValue().contains("Instructions")) {
        pane.getChildren().removeAll(instructions, comments, addComment, addCommentButton, addTag, addTagButton, 
        tagBox, allTagBox, tagCreatNew, tagDropDown, tagRecipeDropDown, erroMSG);
        pane.getChildren().addAll(instructions);
      } else if (optionBox.getValue().contains("Comments")) {
        pane.getChildren().removeAll(instructions, comments, addComment, addCommentButton, addTag,
         addTagButton, tagBox, allTagBox, tagCreatNew, tagDropDown, tagRecipeDropDown, erroMSG);
        pane.getChildren().addAll(comments, addComment, addCommentButton);
        ArrayList<String[]> allComments;
        try {
          comments.getItems().clear();
          allComments = query.getComments();
          for (String[] comment : allComments) {
            if (theRecipe.getId() == Integer.parseInt(comment[2])) {
              int intUser = Integer.parseInt(comment[1]);
              int commentId = Integer.parseInt(comment[0]);
            
              String[] aUser = query.getUser(intUser);
              User theUser = new User(aUser[1], aUser[2]);
              addComment(user, theUser, comment[3], commentId);
            
            }
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else if (optionBox.getValue().contains("Add tag")) {
        pane.getChildren().removeAll(instructions, comments, addComment, addCommentButton);
        pane.getChildren().addAll(addTag, addTagButton, tagBox, allTagBox,
         tagCreatNew, tagDropDown, tagRecipeDropDown, erroMSG);

      }
    });

    backButton.setOnAction(e -> {
      MyPageScene mps = new MyPageScene();
      try {
        mps.start(primStage, user, thefont);
      } catch (Exception e1) {
        System.out.println("problem with changing stage.");
        e1.printStackTrace();
      }
    });

    plusButton.setOnAction(e -> {
      plusPortionButton(ingLista);
    });

    minusButton.setOnAction(e -> {
      minusPortionButton(ingLista);
    });

    favButton.setOnAction(e -> {
      if (favButton.getGraphic() == favEmptyStarView) {
        favButton.setGraphic(favFilledStarView);
        try {
          query.addFavorite(user.getUsername(), theRecipe.getName());
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else if (favButton.getGraphic() == favFilledStarView) {
        favButton.setGraphic(favEmptyStarView);
        try {
          query.delFavorite(user.getUsername(), theRecipe.getName());
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }

    });
  }

  /**
   * This function doubles everything in the Ingredients 
   * text box when pressing the [+] button.
   */
  public void plusPortionButton(ArrayList<String[]> ingList) {
    String ingredientString = "";
    Integer counter = 0;
    String theInteger = "";

    for (int i = 0; i < amountText.getText().length(); i++) {
      char j = amountText.getText().charAt(i);
      try {
        theInteger += Integer.parseInt(String.valueOf(j));
      } catch (Exception e) {
      }
    }
    Integer portions = Integer.parseInt(theInteger);

    if (portions < 10) {
      for (int i = 0; i < ingList.size(); i++) {
        for (String s : ingList.get(i)) {
          if (counter == 1) {
            Float theFloat = Float.parseFloat(s);
            s = String.valueOf((portions + 2) / 2 * theFloat);

            if (s.substring(s.length() - 1).equals("0")){
              int theInt = (int) Float.parseFloat(s);
              s = String.valueOf(theInt);
            } else {
              s = String.valueOf(s);
            }
          } else if (counter == 3) {
            counter = 0;
          }
          counter++;
          ingredientString += s + " ";
        }
        ingredientString += "\n";
      }
      ingredients.setText(ingredientString);
      portions += 2;
      String newAmount = amountText.getText().replace(theInteger, String.valueOf(portions));
      amountText.setText(newAmount);
    }
  }

  /**
   * This function divides everything in the Ingredients 
   * text box when pressing the [-] button.
   */
  public void minusPortionButton(ArrayList<String[]> ingList) {
    String ingredientString = "";
    Integer counter = 0;
    String theInteger = "";

    for (int i = 0; i < amountText.getText().length(); i++) {
      char j = amountText.getText().charAt(i);
      try {
        theInteger += Integer.parseInt(String.valueOf(j));
      } catch (Exception e) {
      }
    }
    Integer portions = Integer.parseInt(theInteger);

    if (portions > 2) {
      for (int i = 0; i < ingList.size(); i++) {
        for (String s : ingList.get(i)) {
          if (counter == 1) {
            Float theFloat = Float.parseFloat(s);
            s = String.valueOf((portions - 2) / 2 * theFloat);

            if (s.substring(s.length() - 1).equals("0")){
              int theInt = (int) Float.parseFloat(s);
              s = String.valueOf(theInt);
            } else {
              s = String.valueOf(s);
            }
          } else if (counter == 3) {
            counter = 0;
          }
          counter++;
          ingredientString += s + " ";
        }
        ingredientString += "\n";
      }
      ingredients.setText(ingredientString);
      portions -= 2;
      String newAmount = amountText.getText().replace(theInteger, String.valueOf(portions));
      amountText.setText(newAmount);
    }
  }

  /**
   * Function to fill all the ingredients in the ingridients text box.
   */
  public void fillIngredients(Recipe theRecipe) {
    try {
      ingList = query.getIngredients(theRecipe.getName());
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    String ingredienString = "";
    Integer counter = 0;
    for (int i = 0; i < ingList.size(); i++) {
      for (String s : ingList.get(i)) {
        if (counter == 1) {
          if (s.substring(s.length() - 1).equals("0")){
            int theInt = (int) Float.parseFloat(s);
            s = String.valueOf(theInt);
          } else {
            s = String.valueOf(s);
          }
        } else if (counter == 3) {
          counter = 0;
        }
        counter++;
        ingredienString += s + " ";
      }
      ingredienString += "\n";
    }
    ingredients.setText(ingredienString);
    amountText.setText("2 portions");
  }

  /**
   * Function to fill all the instructions in the ingridients text box.
   */
  public void fillInstructions(Recipe theRecipe) {
    String instructionString = "";
    String newinstructionString = "";
    String instructionText = theRecipe.getInstructions();
    for (int i = 1; i < instructionText.length() - 1; i++) {
      instructionString += instructionText.charAt(i);
    }
    instructionString = instructionString.replace("\n", " ");
    String instructionList[] = instructionString.split(" ");
    int stepCounter = 2;
    for (String s : instructionList) {
      if (s.equals(String.valueOf(stepCounter) + ":")) {
        stepCounter++;
        newinstructionString += "\n\n";
      }
      newinstructionString += s + " ";
    }
    instructions.setText(newinstructionString);
  }

  /**
   * This function handles the display and auctions when creating a new comment.
   */
  public void addComment(User user , User owner, String text, int commentId) {
    comments.getItems().add(new Pane());
    int Size = comments.getItems().size();
    Pane lastPane = comments.getItems().get(Size - 1);
    lastPane.setPrefSize(423, 125);

    lastPane.getChildren().add(new TextArea());
    Size = lastPane.getChildren().size();
    TextArea textBox = (TextArea) lastPane.getChildren().get(Size - 1);
    textBox.setPrefSize(423, 100);
    textBox.setLayoutY(20);
    textBox.setText(text);
    textBox.setEditable(false);
    textBox.setWrapText(true);

    if (user.getId() == owner.getId()) {
      lastPane.getChildren().add(new Button());
      Size = lastPane.getChildren().size();
      Button editButton = (Button) lastPane.getChildren().get(Size - 1);
      editButton.setPrefSize(50, 10);
      editButton.setLayoutX(372);
      editButton.setLayoutY(0);
      editButton.setFont(Font.font(thefont, FontWeight.BOLD, 10));
      editButton.setText("Edit");
      editButton.setOnAction(e -> {
        if (editButton.getText().equals("Edit")) {
          textBox.setEditable(true);
          editButton.setText("Save");
        } else if (editButton.getText().equals("Save")) {
          try {
            query.editComment(commentId, textBox.getText());
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
          textBox.setEditable(false);
          editButton.setText("Edit");
        }
      });
      lastPane.getChildren().add(new Button());
      Size = lastPane.getChildren().size();
      Button deleteButton = (Button) lastPane.getChildren().get(Size - 1);
      deleteButton.setPrefSize(50, 10);
      deleteButton.setLayoutX(320);
      deleteButton.setLayoutY(0);
      deleteButton.setFont(Font.font(thefont, FontWeight.BOLD, 10));
      deleteButton.setText("Delete");
      deleteButton.setOnAction(e -> {
        try {
          query.removeComment(commentId);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
        comments.getItems().remove(lastPane);
      });
    }

    lastPane.getChildren().add(new Text());
    Size = lastPane.getChildren().size();
    Text publishedText = (Text) lastPane.getChildren().get(Size - 1);
    publishedText.setLayoutY(13);
    publishedText.setFont(Font.font(thefont, FontWeight.BOLD, 12));
    publishedText.setText("Published By: ");

    lastPane.getChildren().add(new Text());
    Size = lastPane.getChildren().size();
    Text NameText = (Text) lastPane.getChildren().get(Size - 1);
    NameText.setLayoutY(13);
    NameText.setLayoutX(85);
    NameText.setFont(Font.font(thefont, 12));
    NameText.setText(String.valueOf(owner.getDisplayName()));
    }
  }