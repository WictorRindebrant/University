package cookbook;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class MyPageScene {
  Text recipesText = new Text();
  Text favoriteText = new Text();
  Text myPageText = new Text();
  Text displayName = new Text();
  Text tagText = new Text();
  Text ingredientText = new Text();
  TextField currentTags = new TextField();
  TextField currentIngredients = new TextField();
  TextField recipesSearchField = new TextField();

  Button mPsearchButton = new Button();
  Button logoutButton = new Button();
  Button helpButton = new Button();
  Button createRecipeButton = new Button();
  Button shoppingCardButton = new Button();
  Button weeklyListButton = new Button();
  Button messageButton = new Button();
  Button userManagerButton = new Button();
  Button resetTagButton = new Button();
  Button resetIngredientButton = new Button();

  Image helpPicture = new Image("file:app/images/help-button.png");
  Image book = new Image("file:app/images/book.jpg");
  ImageView helpView = new ImageView(helpPicture);
  ImageView bookView = new ImageView(book);

  ListView<String> tagList = new ListView<>();
  ListView<String> recipeListView = new ListView<>();
  ListView<String> tagListView = new ListView<>();
  ListView<String> recipeFromTagList = new ListView<>();
  ListView<String> ingredientsListView = new ListView<>();
  ListView<String> recipeFromIngredientsList = new ListView<>();
  ListView<String> myPageTree = new ListView<>();

  TreeItem<String> recipes = null;
  TreeView<String> tree = new TreeView<>();

  Pane pane = new Pane();
  Tooltip test = new Tooltip("Enter Tag. Name or ingrediens");
  StackPane root = new StackPane();
  Query query = new Query();
  ArrayList<String[]> listOfLists = new ArrayList<>();
  ArrayList<String> first = new ArrayList<>();
  ArrayList<String> compareList = new ArrayList<>();
  ArrayList<String> FinalList = new ArrayList<>();
  ArrayList<String> recipe = new ArrayList<>();
  int counter = 0;
  String thefont;

  /**
   * In this function we decide what items there should
   * be in the pane and then adding that to a stackpane
   * with an image in the backround. We also call on all
   * the function to handle how things are displayed and
   * different kind of auctions.
   */
  public void start(Stage primStage, User user, String thefont) throws Exception {

    this.thefont = thefont;

    try {
      recipes = tagDropDown();
    } catch (SQLException e2) {
      e2.printStackTrace();
    }
    tree = new TreeView<>(recipes);

    display(primStage, user, thefont);
    buttonHandler(primStage, user);

    pane.getChildren().addAll(myPageText, displayName,
        tree, recipesText, recipesSearchField,
        logoutButton, myPageTree, createRecipeButton, shoppingCardButton,
        weeklyListButton, messageButton, helpButton, favoriteText);

    if (user.getAdminVal() == true) {
      pane.getChildren().add(userManagerButton);
    }

    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    Scene s = new Scene(root);
    primStage.setScene(s);
  }

  /**
   * In this function we handle how things
   * should be displayed with size, fonts, position and more.
   */
  public void display(Stage primStage, User user, String thefont) throws Exception {
    tagList.setLayoutX(660);
    tagList.setLayoutY(140);
    tagList.setPrefSize(452, 550);
    tagList.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");

    for (String i : query.getFavorites(user.getUsername()))
      myPageTree.getItems().add(i);

    myPageTree.setLayoutX(135);
    myPageTree.setLayoutY(140);
    myPageTree.setPrefSize(452, 275);
    myPageTree.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");

    // Making myPageTree to an cellFactory (For listView).
    myPageTree.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
      public ListCell<String> call(ListView<String> param) {
        return new ComboBoxListCell<String>();
      }
    });
    makingBoldTextListView(user.getUsername());

    tree.setLayoutX(660);
    tree.setLayoutY(140);
    tree.setPrefSize(452, 550);
    tree.setStyle("-fx-font-size: 20; -fx-font-family: '" + thefont + "';");
    tree.setShowRoot(false);

    // Making tree to an cellFactory (For treeView).
    tree.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
      public TreeCell<String> call(TreeView<String> param) {
        return new ComboBoxTreeCell<String>();
      }
    });

    // Function to make the text bold when hower
    makingBoldText();
    makingBoldTextListViewIng();
    makingBoldTextListViewTag();

    recipesText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    recipesText.setWrappingWidth(400);
    recipesText.setLayoutX(795);
    recipesText.setLayoutY(70);
    recipesText.setText("All recipes:");
    recipesText.setFill(Color.BLACK);

    favoriteText.setFont(Font.font(thefont, FontWeight.BOLD, 25));
    favoriteText.setWrappingWidth(400);
    favoriteText.setLayoutX(135);
    favoriteText.setLayoutY(135);
    favoriteText.setText("Favorites:");
    favoriteText.setFill(Color.BLACK);

    currentTags.setLayoutX(660);
    currentTags.setLayoutY(410);
    currentTags.setPrefWidth(452);
    currentTags.setEditable(false);

    currentIngredients.setLayoutX(660);
    currentIngredients.setLayoutY(410);
    currentIngredients.setPrefWidth(452);
    currentIngredients.setEditable(false);

    recipesSearchField.setLayoutX(660);
    recipesSearchField.setLayoutY(100);
    recipesSearchField.setPrefWidth(452);
    recipesSearchField.setPromptText("Search for recipes using name, tag with '#' or ingredients with '!'");

    messageButton.setLayoutX(135);
    messageButton.setLayoutY(430);
    messageButton.setText("Messages");
    messageButton.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    messageButton.setPrefSize(452, 20);

    resetTagButton.setLayoutX(1033);
    resetTagButton.setLayoutY(370);
    resetTagButton.setText("Reset tags");
    resetTagButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
    resetTagButton.setOnAction(e -> {
      currentTags.setText("");
      recipeFromTagList.getItems().clear();
      recipeFromIngredientsList.getItems().clear();
      first.clear();
      FinalList.clear();
      if (counter > 0) {
        counter = 0;
      }
    });

    resetIngredientButton.setLayoutX(990);
    resetIngredientButton.setLayoutY(370);
    resetIngredientButton.setText("Reset ingredients");
    resetIngredientButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
    resetIngredientButton.setOnAction(e -> {
      currentIngredients.setText("");
      recipeFromIngredientsList.getItems().clear();
      first.clear();
      FinalList.clear();
      if (counter > 0) {
        counter = 0;
      }
    });

    createRecipeButton.setLayoutX(135);
    createRecipeButton.setLayoutY(485);
    createRecipeButton.setText("Create a new recipe");
    createRecipeButton.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    createRecipeButton.setPrefSize(452, 20);

    shoppingCardButton.setLayoutX(135);
    shoppingCardButton.setLayoutY(540);
    shoppingCardButton.setText("Shopping cart");
    shoppingCardButton.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    shoppingCardButton.setPrefSize(452, 20);

    weeklyListButton.setLayoutX(135);
    weeklyListButton.setLayoutY(595);
    weeklyListButton.setText("Weekly food plan");
    weeklyListButton.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    weeklyListButton.setPrefSize(452, 20);

    userManagerButton.setLayoutX(135);
    userManagerButton.setLayoutY(650);
    userManagerButton.setText("User Management");
    userManagerButton.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    userManagerButton.setPrefSize(452, 20);

    recipesSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
      // this is for searching for recipes.
      if (observable.getValue().length() > 0
          && !(pane.getChildren().contains(recipeListView) || pane.getChildren().contains(tagListView)
              || pane.getChildren().contains(ingredientsListView))
          && !(observable.getValue().contains("#") || observable.getValue().contains("!"))) {
        if (!pane.getChildren().contains(recipeListView)) {
          pane.getChildren().add(recipeListView);
          pane.getChildren().remove(tree);
        } else {
          ;
        }
        ObservableList<String> recipeObserverList = FXCollections.observableArrayList();
        String[] recipeNameList = {};
        try {
          recipeNameList = query.getAllRecipesNames();
        } catch (SQLException e) {
          System.out.println("problem with recipe observer list");
          e.printStackTrace();
        }
        for (String recipeName : recipeNameList) {
          recipeObserverList.add(recipeName);
        }

        FilteredList<String> filteredRecipeNames = new FilteredList<>(recipeObserverList);
        filteredRecipeNames.predicateProperty()
            .bind(Bindings.createObjectBinding(
                () -> name -> name.toLowerCase().contains(recipesSearchField.getText().toLowerCase()),
                recipesSearchField.textProperty()));
        recipeListView.setItems(filteredRecipeNames);
        recipeListView.setCellFactory(ComboBoxListCell.forListView(recipeObserverList));
        // will bring user to recipe when clicked.
        recipeListView.getSelectionModel().selectedItemProperty().addListener((v, oldRecipeValue, newRecipeValue) -> {
          if (newRecipeValue != null) {
            RecipeScene rs = new RecipeScene();
            try {
              rs.start(primStage, newRecipeValue, user, thefont);
            } catch (Exception e1) {
              System.out.println("problem with changing stage.");
              e1.printStackTrace();
            }
          }
        });
      }

      // this is for searching for recipes through tags.
      if (observable.getValue().length() > 0
          && !(pane.getChildren().contains(tagListView) || pane.getChildren().contains(recipeListView)
              || pane.getChildren().contains(ingredientsListView))
          && observable.getValue().startsWith("#")) {
        if (!pane.getChildren().contains(tagListView)) {
          pane.getChildren().add(tagListView);
          pane.getChildren().add(recipeFromTagList);
          pane.getChildren().add(tagText);
          pane.getChildren().add(currentTags);
          pane.getChildren().add(resetTagButton);
          pane.getChildren().remove(tree);
        } else {
          ;
        }
        ObservableList<String> tagObserverList = FXCollections.observableArrayList();
        ArrayList<String> tagList = new ArrayList<>();
        try {
          tagList = query.getTags();
        } catch (SQLException e) {
          System.out.println("problem with tag observer list");
          e.printStackTrace();
        }
        for (String tagType : tagList) {
          tagObserverList.add("#" + tagType);
        }

        FilteredList<String> filteredTagNames = new FilteredList<>(tagObserverList);
        filteredTagNames.predicateProperty()
            .bind(Bindings.createObjectBinding(
                () -> name -> name.toLowerCase().contains(recipesSearchField.getText().toLowerCase()),
                recipesSearchField.textProperty()));
        tagListView.setItems(filteredTagNames);
        tagListView.setCellFactory(ComboBoxListCell.forListView(tagObserverList));

        // tag action when clicked.

        tagListView.getSelectionModel().selectedItemProperty().addListener((current, oldTagValue, newTagValue) -> {
          try {
            if (current.getValue() != null) {
              if (!currentTags.getText().contains(current.getValue().toString().replace("#", ""))) {
                currentTags.setText(currentTags.getText() + " " + current.getValue().toString().replace("#", ""));
              }
              ArrayList<String> testis = query.getRecipeByTag(current.getValue().toString().replace("#", ""));
              for (String i : testis) {
                if (!recipeFromTagList.getItems().contains(i)) {
                  recipeFromTagList.getItems().add(i);
                }
              }

              try {
                recipe.clear();
                recipe = query.getRecipeByTag(current.getValue().replace("#", ""));

              } catch (SQLException e) {
                System.out.println("problem with recipe from ingredient list.");
                e.printStackTrace();
              }

              // clears before recreating the lists.
              recipeFromTagList.getItems().clear();

              // cleared so that only new items will appear.
              FinalList.clear();

              for (String i : recipe) {
                // checks duplicate
                if (!recipeFromTagList.getItems().contains(i) && counter == 0) {
                  recipeFromTagList.getItems().add(i);
                  // first chosen ingredient, which will be used to compare the second choice.
                  first.add(i);

                  // used to compare first ingredient with the others to come.
                  // then adds the elements that exist in both, to one list.
                } else if (counter > 0) {
                  if (first.contains(i) && !FinalList.contains(i)) {
                    FinalList.add(i);
                  }

                }
              }
              // list that will be shown, containing only the elements that first and
              // comparelist contains.
              for (String finalRecipe : FinalList) {
                if (!recipeFromTagList.getItems().contains(finalRecipe)) {
                  recipeFromTagList.getItems().add(finalRecipe);
                }
              }
              // makes the first list into the final list, so we can compare the next
              // ingredient aswell.
              if (counter > 0) {
                first.clear();
                first.addAll(FinalList);

              }
              counter++;

            } else {
              ;
            }

          } catch (SQLException e) {
            System.out.println("problem with pressing a tag");
            e.printStackTrace();
          }

        });
      }

      // This is for searching for recipes through ingredients.
      if (observable.getValue().length() > 0
          && !(pane.getChildren().contains(tagListView) || pane.getChildren().contains(recipeListView)
              || pane.getChildren().contains(ingredientsListView))
          && observable.getValue().contains("!")) {
        if (!pane.getChildren().contains(ingredientsListView)) {
          pane.getChildren().add(ingredientsListView);
          pane.getChildren().add(resetIngredientButton);
          pane.getChildren().add(currentIngredients);
          pane.getChildren().add(recipeFromIngredientsList);
          pane.getChildren().add(ingredientText);
          pane.getChildren().remove(tree);
        } else {
          ;
        }
        ObservableList<String> ingredientsObserverList = FXCollections.observableArrayList();
        ArrayList<String[]> ingredientsList = new ArrayList<>();
        try {
          ingredientsList = query.getAllIngredients();
        } catch (SQLException e) {
          System.out.println("problem with ingredients observer list");
          e.printStackTrace();
        }
        for (String[] ingredientName : ingredientsList) {
          if (!ingredientsObserverList.contains(ingredientName[0])) {
            ingredientsObserverList.add(ingredientName[0]);
          } else {
            ;
          }

        }

        FilteredList<String> filteredingredientNames = new FilteredList<>(ingredientsObserverList);
        filteredingredientNames.predicateProperty()
            .bind(Bindings.createObjectBinding(
                () -> name -> name.toLowerCase().contains(recipesSearchField.getText().toLowerCase().replace("!", "")),
                recipesSearchField.textProperty()));
        ingredientsListView.setItems(filteredingredientNames);
        ingredientsListView.setCellFactory(ComboBoxListCell.forListView(ingredientsObserverList));

        // ingredient button action when clicked.
        ingredientsListView.getSelectionModel().selectedItemProperty()
            .addListener((current, oldingredientValue, newingValue) -> {
              if (current.getValue() != null) {
                // checks duplicate
                // textbox with chosen ingredients.
                if (!currentIngredients.getText().contains(current.getValue().toString().replace("!", ""))) {
                  currentIngredients
                      .setText(currentIngredients.getText() + " " + current.getValue().toString().replace("!", ""));
                }
                try {
                  recipe.clear();
                  recipe = query.getRecipeByIngredient(current.getValue().toString().replace("!", ""));
                } catch (SQLException e) {
                  System.out.println("problem with recipe from ingredient list.");
                  e.printStackTrace();
                }

                // clears before recreating the lists.
                recipeFromIngredientsList.getItems().clear();

                // cleared so that only new items will appear.
                FinalList.clear();

                for (String i : recipe) {
                  // checks duplicate
                  if (!recipeFromIngredientsList.getItems().contains(i) && counter == 0) {
                    recipeFromIngredientsList.getItems().add(i);
                    // first chosen ingredient, which will be used to compare the second choice.
                    first.add(i);

                    // used to compare first ingredient with the others to come.
                    // then adds the elements that exist in both, to one list.
                  } else if (counter > 0) {
                    if (first.contains(i) && !FinalList.contains(i)) {
                      FinalList.add(i);
                    }

                  }
                }
                // list that will be shown, containing only the elements that first and
                // comparelist contains.
                for (String finalRecipe : FinalList) {
                  if (!recipeFromIngredientsList.getItems().contains(finalRecipe)) {
                    recipeFromIngredientsList.getItems().add(finalRecipe);
                  }
                }
                // makes the first list into the final list, so we can compare the next
                // ingredient aswell.
                if (counter > 0) {
                  first.clear();
                  first.addAll(FinalList);

                }
                counter++;
              } else {
                ;
              }
            });
      }

      // condition for restoring page.
      if (observable.getValue().length() < 1
          && (pane.getChildren().contains(tagListView) || pane.getChildren().contains(recipeListView)
              || pane.getChildren().contains(ingredientsListView))) {
        if (pane.getChildren().contains(tagListView)) {
          pane.getChildren().remove(resetTagButton);
          pane.getChildren().remove(tagListView);
          pane.getChildren().remove(recipeFromTagList);
          pane.getChildren().remove(tagText);
          pane.getChildren().remove(currentTags);
          currentTags.setText("");
          recipeFromTagList.getItems().clear();
          recipeFromIngredientsList.getItems().clear();
          first.clear();
          FinalList.clear();
          if (counter > 0) {
            counter = 0;
          }

          pane.getChildren().add(tree);
        }
        if (pane.getChildren().contains(recipeListView)) {
          pane.getChildren().remove(recipeListView);
          pane.getChildren().add(tree);
        }
        if (pane.getChildren().contains(ingredientsListView)) {
          pane.getChildren().remove(ingredientsListView);
          pane.getChildren().remove(currentIngredients);
          pane.getChildren().remove(resetIngredientButton);
          pane.getChildren().remove(recipeFromIngredientsList);
          pane.getChildren().remove(ingredientText);
          currentIngredients.setText("");
          recipeFromIngredientsList.getItems().clear();
          first.clear();
          FinalList.clear();
          if (counter > 0) {
            counter = 0;
          }
          pane.getChildren().add(tree);
        }
      }
    });

    tagListView.setLayoutX(660);
    tagListView.setLayoutY(140);
    tagListView.setPrefSize(452, 200);
    tagListView.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");

    ingredientsListView.setLayoutX(660);
    ingredientsListView.setLayoutY(140);
    ingredientsListView.setPrefSize(452, 200);
    ingredientsListView.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");

    recipeListView.setLayoutX(660);
    recipeListView.setLayoutY(140);
    recipeListView.setPrefSize(452, 550);
    recipeListView.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");

    // Making myPageTree to an cellFactory (For listView).
    recipeListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
      public ListCell<String> call(ListView<String> param) {
        return new ComboBoxListCell<String>();
      }
    });
    makingBoldTextListViewRec();

    // will bring user to recipe when clicked.
    recipeFromTagList.setLayoutX(660);
    recipeFromTagList.setLayoutY(450);
    recipeFromTagList.setPrefSize(452, 220);
    recipeFromTagList.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    recipeFromTagList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
      if (newValue != null) {
        RecipeScene rs = new RecipeScene();
        try {
          rs.start(primStage, newValue, user, thefont);
        } catch (Exception e1) {
          System.out.println("problem with changing stage.");
          e1.printStackTrace();
        }
      }
    });

    // will bring user to recipe when clicked.
    recipeFromIngredientsList.setLayoutX(660);
    recipeFromIngredientsList.setLayoutY(450);
    recipeFromIngredientsList.setPrefSize(452, 220);
    recipeFromIngredientsList.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    recipeFromIngredientsList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
      if (newValue != null) {
        RecipeScene rs = new RecipeScene();
        try {
          rs.start(primStage, newValue, user, thefont);
        } catch (Exception e1) {
          System.out.println("problem with changing stage.");
          e1.printStackTrace();
        }
      }
    });

    logoutButton.setLayoutX(120);
    logoutButton.setLayoutY(60);
    logoutButton.setText("Logout");

    helpButton.setLayoutX(550);
    helpButton.setLayoutY(40);
    helpButton.setGraphic(helpView);

    displayName.setFont(Font.font(thefont, FontWeight.BOLD, 10));
    displayName.setWrappingWidth(600);
    displayName.setLayoutX(120);
    displayName.setLayoutY(50);
    displayName.setText("Logged in as: " + user.getDisplayName());
    displayName.setFill(Color.BLACK);

    tagText.setFont(Font.font(thefont, FontWeight.BOLD, 18));
    tagText.setWrappingWidth(100);
    tagText.setLayoutX(660);
    tagText.setLayoutY(400);
    tagText.setText("Tags");
    tagText.setFill(Color.BLACK);

    ingredientText.setFont(Font.font(thefont, FontWeight.BOLD, 18));
    ingredientText.setWrappingWidth(100);
    ingredientText.setLayoutX(660);
    ingredientText.setLayoutY(400);
    ingredientText.setText("Ingredients");
    ingredientText.setFill(Color.BLACK);

    myPageText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    myPageText.setWrappingWidth(200);
    myPageText.setLayoutX(280);
    myPageText.setLayoutY(70);
    myPageText.setText("My Page");
    myPageText.setFill(Color.BLACK);

    // Skips mypage
    // RecipeScene.recipeScene(primStage, "Pancakes", user);
  }

  /**
   * In this function we have all the auctions for button events.
   */
  private void buttonHandler(Stage primStage, User user) {
    tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
      boolean tagFound = false;
      for (TreeItem<String> tags : recipes.getChildren()) {
        if (tags.getValue().equals(newValue.getValue())) {
          tagFound = true;
        }
      }
      if (newValue != null && tagFound == false) {
        RecipeScene rs = new RecipeScene();
        try {
          rs.start(primStage, newValue.getValue(), user, thefont);
        } catch (Exception e1) {
          System.out.println("problem with changing stage.");
          e1.printStackTrace();
        }
      }
    });

    logoutButton.setOnAction(e -> {
      LoginScene loginScreen = new LoginScene();
      Stage stage = (Stage) logoutButton.getScene().getWindow();
      stage.close();
      try {
        loginScreen.start(new Stage());
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    helpButton.setOnAction(e -> {
      HelpPageScene helpPage = new HelpPageScene();
      try {
        helpPage.start(primStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    messageButton.setOnAction(e -> {
      MessageScene messageScreen = new MessageScene();
      try {
        messageScreen.start(primStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    createRecipeButton.setOnAction(e -> {
      CreateRecipeScene createRecipeScene = new CreateRecipeScene();
      try {
        createRecipeScene.start(primStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    shoppingCardButton.setOnAction(e -> {
      ShoppingCartScene shoppingCartScene = new ShoppingCartScene();
      try {
        shoppingCartScene.start(primStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    weeklyListButton.setOnAction(e -> {
      WeekListScene weekListScene = new WeekListScene();
      try {
        weekListScene.start(primStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    userManagerButton.setOnAction(e -> {
      AdminScene adminScene = new AdminScene();
      try {
        adminScene.start(primStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    myPageTree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
      RecipeScene rs = new RecipeScene();
      try {
        rs.start(primStage, newValue, user, thefont);
      } catch (Exception e1) {
        System.out.println("problem with changing stage.");
        e1.printStackTrace();
      }
    });
  }

  // Creates the dropdown for the tags and filles the tree
  public TreeItem<String> tagDropDown() throws SQLException {
    TreeItem<String> recipes = new TreeItem<>("Recipes");
    recipes.setExpanded(true);
    TreeItem<String> viewallrecipes = new TreeItem<>("All");
    recipes.getChildren().add(viewallrecipes);
    viewallrecipes.setExpanded(false);

    for (String[] dish : query.getAllRecipes()) {
      viewallrecipes.getChildren().add(new TreeItem<>(dish[0]));
    }
    for (String tag : query.getTags()) {
      recipes.getChildren().add(new TreeItem<>(tag));
    }
    for (TreeItem<String> tag : recipes.getChildren()) {
      for (String dish : query.getRecipeByTag(tag.getValue())) {
        tag.getChildren().add(new TreeItem<>(dish));
      }
    }
    return recipes;
  }

  // The code for making the treeView recipe text bold and adding hoverText.
  @SuppressWarnings("unchecked")
  public void makingBoldText() {
    tree.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxTreeCell")) {
          ComboBoxTreeCell<String> picked = (ComboBoxTreeCell<String>) event.getTarget();
          for (TreeItem<String> tags : recipes.getChildren()) {
            for (TreeItem<String> recipe : tags.getChildren()) {
              if (recipe.getValue().equals(picked.getText())) {
                picked.setStyle("-fx-font-weight: bold;");
                // Adding tootltip to the hovered text
                String descText = "";
                try {
                  descText = query.getRecipeDescription(recipe.getValue());
                } catch (SQLException e) {
                  e.printStackTrace();
                }
                picked.setTooltip(new Tooltip(descText));
                picked.getTooltip().setMaxWidth(400);
                picked.getTooltip().setWrapText(true);
                picked.getTooltip().setOpacity(1);
                picked.getTooltip().setShowDelay(Duration.ZERO);
                picked.getTooltip().setShowDuration(Duration.INDEFINITE);
                picked.getTooltip().setTextAlignment(TextAlignment.CENTER);
                picked.getTooltip().setStyle("-fx-font-style: italic; -fx-font-size: 0.5em; -fx-show-delay: 1000ms;");
              }
            }
          }
        }
      }
    });

    tree.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxTreeCell")) {
          ComboBoxTreeCell<String> picked = (ComboBoxTreeCell<String>) event.getTarget();
          for (TreeItem<String> tags : recipes.getChildren()) {
            for (TreeItem<String> recipe : tags.getChildren()) {
              if (recipe.getValue().equals(picked.getText())) {
                picked.setStyle(null);
                picked.setTooltip(null);
              }
            }
          }
        }
      }
    });
  }

  // The code for making the ListView recipe text bold and adding hoverText.
  @SuppressWarnings("unchecked")
  public void makingBoldTextListView(String username) {
    // Making text bold when entering the textBox
    myPageTree.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          try {
            for (String recipe : query.getFavorites(username)) {
              if (recipe.equals(picked.getText())) {
                picked.setStyle("-fx-font-weight: bold;");
                // Adding tootltip to the hovered text
                String descText = "";
                try {
                  descText = query.getRecipeDescription(recipe);
                } catch (SQLException e) {
                  e.printStackTrace();
                }
                picked.setTooltip(new Tooltip(descText));
                picked.getTooltip().setMaxWidth(400);
                picked.getTooltip().setWrapText(true);
                picked.getTooltip().setOpacity(1);
                picked.getTooltip().setShowDelay(Duration.ZERO);
                picked.getTooltip().setShowDuration(Duration.INDEFINITE);
                picked.getTooltip().setTextAlignment(TextAlignment.CENTER);
                picked.getTooltip().setStyle("-fx-font-style: italic; -fx-font-size: 0.5em; -fx-show-delay: 1000ms;");
              }
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    });

    // Making text go back to not bold when exiting the textBox
    myPageTree.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          try {
            for (String recipe : query.getFavorites(username)) {
              if (recipe.equals(picked.getText())) {
                picked.setStyle(null);
                picked.setTooltip(null);
              }
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  // The code for making the ListView recipe text bold and adding hoverText.
  @SuppressWarnings("unchecked")
  public void makingBoldTextListViewRec() {
    // Making text bold when entering the textBox
    recipeListView.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          for (TreeItem<String> tags : recipes.getChildren()) {
            for (TreeItem<String> recipe : tags.getChildren()) {
              if (recipe.getValue().equals(picked.getText())) {
                picked.setStyle("-fx-font-weight: bold;");
                // Adding tootltip to the hovered text
                String descText = "";
                try {
                  descText = query.getRecipeDescription(recipe.getValue());
                } catch (SQLException e) {
                  e.printStackTrace();
                }
                picked.setTooltip(new Tooltip(descText));
                picked.getTooltip().setMaxWidth(400);
                picked.getTooltip().setWrapText(true);
                picked.getTooltip().setOpacity(1);
                picked.getTooltip().setShowDelay(Duration.ZERO);
                picked.getTooltip().setShowDuration(Duration.INDEFINITE);
                picked.getTooltip().setTextAlignment(TextAlignment.CENTER);
                picked.getTooltip().setStyle("-fx-font-style: italic; -fx-font-size: 0.5em; -fx-show-delay: 1000ms;");
              }
            }
          }
        }
      }
    });

    // Making text go back to not bold when exiting the textBox
    recipeListView.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          picked.setStyle(null);
          picked.setTooltip(null);
        }
      }
    });
  }

  // The code for making the ListView text bold.
  @SuppressWarnings("unchecked")
  public void makingBoldTextListViewIng() {
    ingredientsListView.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          picked.setStyle("-fx-font-weight: bold;");
        }
      }
    });

    // Making text go back to not bold when exiting the textBox
    ingredientsListView.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          picked.setStyle(null);
          picked.setTooltip(null);
        }
      }
    });
  }

  // The code for making the ListView text bold.
  @SuppressWarnings("unchecked")
  public void makingBoldTextListViewTag() {
    tagListView.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          picked.setStyle("-fx-font-weight: bold;");
        }
      }
    });

    // Making text go back to not bold when exiting the textBox
    tagListView.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (event.getTarget().getClass().getSimpleName().equals("ComboBoxListCell")) {
          ComboBoxListCell<String> picked = (ComboBoxListCell<String>) event.getTarget();
          picked.setStyle(null);
          picked.setTooltip(null);
        }
      }
    });
  }
}