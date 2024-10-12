package cookbook;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpPageScene {

  Pane pane = new Pane();
  StackPane root = new StackPane();
  Query query = new Query();
  Text helpPageText = new Text();
  TextField helpSearchField = new TextField();

  Image book = new Image("file:app/images/book.jpg");
  Image backArrow = new Image("file:app/images/back_arrow.png");
  // my page
  Image myPageClear = new Image("file:app/images/myPage-clear.png");
  Image myPageFavorites = new Image("file:app/images/myPage-favorite.png");
  Image myPageSearch = new Image("file:app/images/myPage-search.png");
  Image myPageRecipeTags = new Image("file:app/images/myPage-recipeTag.png");
  Image myPageButtons = new Image("file:app/images/myPage-buttons.png");
  Image addtag = new Image("file:app/images/tags.PNG");
  Image tagsalreadyexist = new Image("file:app/images/tagexist.png");
  Image comment = new Image("file:app/images/comment.png");

  // chat page
  Image chatPageClear = new Image("file:app/images/chatPage-clear.png");
  Image chatPageReceived = new Image("file:app/images/chatPage-received.png");
  Image chatPageSent = new Image("file:app/images/chatPage-sent.png");
  Image chatPageNewMessage = new Image("file:app/images/chatPage-newMessage.png");

  // create a recipe page
  Image createRecipePageClear = new Image("file:app/images/createRecipePage-clear.png");
  Image createRecipePageCreate = new Image("file:app/images/createRecipePage-create.png");

  // bout to be shopping cart
  Image shoppingfoodplan = new Image("file:app/images/planebook.png");
  Image shoppingfoodplanhelp = new Image("file:app/images/bookhelp.png");

  // weekly food plan page
  Image weekMealPlanPageClear = new Image("file:app/images/weekMealPlanPage-clear.png");
  Image weekMealPlanPageView = new Image("file:app/images/weekMealPlanPage-view.png");
  Image weekMealPlanPageAddRecipe = new Image("file:app/images/weekMealPlanPage-addPlan.png");

  // user management page
  Image userManagementPage = new Image("file:app/images/userManagementPage-clear.png");
  Image userManagementUserSearchFunction = new Image("file:app/images/userManagementPage-userSearchFunction.png");
  Image userManagementUserList = new Image("file:app/images/userManagementPage-userList.png");
  Image userManagementUserAdd = new Image("file:app/images/userManagementPage-userAdd.png");
  Image userManagementUserDelete = new Image("file:app/images/userManagementPage-userDelete.png");
  Image userManagementUserEdit = new Image("file:app/images/userManagementPage-userEdit.png");



  ImageView bookView = new ImageView(book);
  ImageView backArrowView = new ImageView(backArrow);
  ImageView changeableView = new ImageView();
  Button backButton = new Button();
  TreeView<String> pageTreeView = new TreeView<>();
  ListView<String> helpListView = new ListView<>();
  
  String thefont;
  
  
  
  public void start(Stage helpStage, User user, String thefont) throws Exception {
    pane.getChildren().addAll(bookView, backButton, helpSearchField, pageTreeView, changeableView, helpPageText);
    
    this.thefont = thefont;
    
    // the root which will be hidden.
    TreeItem<String> rootItem = new TreeItem<>("help");
    pageTreeView.setRoot(rootItem);
    pageTreeView.setShowRoot(false);

    // my page.
    TreeItem<String> myPage = new TreeItem<>("My page");
    myPage.getChildren().add(new TreeItem<String>("Favorites"));
    myPage.getChildren().add(new TreeItem<String>("Recipe search function"));
    myPage.getChildren().add(new TreeItem<String>("Recipe Tags"));

    myPage.getChildren().add(new TreeItem<String>("Add tag"));
    myPage.getChildren().add(new TreeItem<String>("Already existing tag"));
    myPage.getChildren().add(new TreeItem<String>("How to comment"));

    myPage.getChildren().add(new TreeItem<String>("Navigation buttons"));
    rootItem.getChildren().add(myPage);

    // chat page.
    TreeItem<String> chat = new TreeItem<>("Chat");
    chat.getChildren().add(new TreeItem<String>("Received"));
    chat.getChildren().add(new TreeItem<String>("Send"));
    chat.getChildren().add(new TreeItem<String>("Chat buttons"));
    rootItem.getChildren().add(chat);

    // create recipe page.
    TreeItem<String> createRecipe = new TreeItem<>("Create new recipe");
    createRecipe.getChildren().add(new TreeItem<String>("Add ingredient"));
    rootItem.getChildren().add(createRecipe);

    // Shopping cart
    TreeItem<String> shoppingCart = new TreeItem<>("Shopping cart");
    shoppingCart.getChildren().add(new TreeItem<String>("Choose week and edit"));
    rootItem.getChildren().add(shoppingCart);

    // weekly food plan
    TreeItem<String> weeklyPlan = new TreeItem<>("Weekly food plan");
    weeklyPlan.getChildren().add(new TreeItem<String>("View your weekly meal plan"));
    weeklyPlan.getChildren().add(new TreeItem<String>("add weekly meal plan"));
    rootItem.getChildren().add(weeklyPlan);

    // User management
    TreeItem<String> userManagement = new TreeItem<>("User management");
    userManagement.getChildren().add(new TreeItem<String>("User search function"));
    userManagement.getChildren().add(new TreeItem<String>("User list"));
    userManagement.getChildren().add(new TreeItem<String>("Add user"));
    userManagement.getChildren().add(new TreeItem<String>("Delete user"));
    userManagement.getChildren().add(new TreeItem<String>("Edit user"));
    rootItem.getChildren().add(userManagement);

    pageTreeView.getSelectionModel().selectedItemProperty()
    .addListener((current, oldValue, newValue) -> {
      // my page related pictures
      if (current.getValue().toString().contains("TreeItem [ value: My page ]")) {
        changeableView.setImage(myPageClear);
      } else if (current.getValue().toString().contains("TreeItem [ value: Favorites ]")) {
        changeableView.setImage(myPageFavorites);
      } else if (current.getValue().toString().contains("TreeItem [ value: Recipe search function ]")) {
        changeableView.setImage(myPageSearch);
      } else if (current.getValue().toString().contains("TreeItem [ value: Recipe Tags ]")) {
        changeableView.setImage(myPageRecipeTags);



      } else if (current.getValue().toString().contains("TreeItem [ value: Add tag ]")) {
        changeableView.setImage(addtag);  
      } else if (current.getValue().toString().contains("TreeItem [ value: Already existing tag ]")) {
        changeableView.setImage(tagsalreadyexist);
      } else if (current.getValue().toString().contains("TreeItem [ value: How to comment ]")) {
        changeableView.setImage(comment);



      } else if (current.getValue().toString().contains("TreeItem [ value: Navigation buttons ]")) {
        changeableView.setImage(myPageButtons);
      }
      // chat related pictures
      else if (current.getValue().toString().equals("TreeItem [ value: Chat ]")) {
        changeableView.setImage(chatPageClear);
      } else if (current.getValue().toString().contains("TreeItem [ value: Received ]")) {
        changeableView.setImage(chatPageReceived);
      } else if (current.getValue().toString().contains("TreeItem [ value: Send ]")) {
        changeableView.setImage(chatPageSent);
      } else if (current.getValue().toString().contains("TreeItem [ value: Chat buttons ]")) {
        changeableView.setImage(chatPageNewMessage);
      }
      
      
      // create recipe related pictures
      else if (current.getValue().toString().contains("Create new recipe")) {
        changeableView.setImage(createRecipePageClear);
      } else if (current.getValue().toString().contains("TreeItem [ value: Add ingredient ]")) {
        changeableView.setImage(createRecipePageCreate);
      }
      

          // create recipe related pictures
          else if (current.getValue().toString().contains("Create new recipe")) {
            changeableView.setImage(createRecipePageClear);
          } else if (current.getValue().toString().contains("TreeItem [ value: Add ingredient ]")) {
            changeableView.setImage(createRecipePageCreate);
          }

      // weekly food plan pictures
      else if (current.getValue().toString().contains("Weekly food plan")) {
        changeableView.setImage(weekMealPlanPageClear);
      } else if (current.getValue().toString().contains("TreeItem [ value: View your weekly meal plan ]")) {
        changeableView.setImage(weekMealPlanPageView);
      } else if (current.getValue().toString().contains("TreeItem [ value: add weekly meal plan ]")) {
        changeableView.setImage(weekMealPlanPageAddRecipe);
      }

      else if (current.getValue().toString().contains("TreeItem [ value: Shopping cart ]")) {
        changeableView.setImage(shoppingfoodplan);
      } else if (current.getValue().toString().contains("TreeItem [ value: Choose week and edit ]")) {
        changeableView.setImage(shoppingfoodplanhelp);
      }
      
      
      // user management pictures
      else if (current.getValue().toString().contains("TreeItem [ value: User management ]")) {
        changeableView.setImage(userManagementPage);
      } else if (current.getValue().toString().contains("TreeItem [ value: User search function ]")) {
        changeableView.setImage(userManagementUserSearchFunction);
      } else if (current.getValue().toString().contains("TreeItem [ value: User list ]")) {
        changeableView.setImage(userManagementUserList);
      } else if (current.getValue().toString().contains("TreeItem [ value: Add user ]")) {
        changeableView.setImage(userManagementUserAdd);
      } else if (current.getValue().toString().contains("TreeItem [ value: Delete user ]")) {
        changeableView.setImage(userManagementUserDelete);
      } else if (current.getValue().toString().contains("TreeItem [ value: Edit user ]")) {
        changeableView.setImage(userManagementUserEdit);
      }
    });
    
    
        

    // tutorial pictures

    changeableView.setX(660);
    changeableView.setY(140);
    changeableView.setFitHeight(550);
    changeableView.setFitWidth(452);

    // navigate to the main menu
    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(120);
    backButton.setLayoutY(40);
    backButton.setGraphic(backArrowView);
    backButton.setOnAction(e -> {
      MyPageScene mps = new MyPageScene();
      try {
        mps.start(helpStage, user, thefont);
      } catch (Exception e1) {
        System.out.println("problem with changing stage.");
        e1.printStackTrace();
      }
    });

    helpSearchField.setLayoutX(135);
    helpSearchField.setLayoutY(100);
    helpSearchField.setPrefWidth(400);
    helpSearchField.setPromptText("Search for a keywords");
    helpSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (observable.getValue().length() > 0 && !pane.getChildren().contains(helpListView)) {
        pane.getChildren().add(helpListView);
      }
      ObservableList<String> helpObserverList = FXCollections.observableArrayList();
      ArrayList<String> list = new ArrayList<>();
      pageTreeView.getRoot().getChildren().forEach(item -> {
        if (!item.isLeaf())
          list.add(item.toString());
        item.getChildren().forEach(leafItem -> {
          list.add(leafItem.toString());
        });
      });
      for (String i : list) {
        i = i.replace("TreeItem [ value: ", "");
        i = i.replace("]", "");
        helpObserverList.add(i);

      }
      FilteredList<String> filteredHelpChoices = new FilteredList<>(helpObserverList);
      filteredHelpChoices.predicateProperty()
          .bind(Bindings.createObjectBinding(
              () -> name -> name.toLowerCase().contains(helpSearchField.getText().toLowerCase()),
              helpSearchField.textProperty()));
      helpListView.setItems(filteredHelpChoices);
      helpListView.setCellFactory(ComboBoxListCell.forListView(helpObserverList));
      if (observable.getValue().length() < 1 && pane.getChildren().contains(helpListView)) {
        pane.getChildren().remove(helpListView);
      }
    });

    pageTreeView.setLayoutX(135);
    pageTreeView.setLayoutY(140);
    pageTreeView.setPrefSize(452, 550);
    pageTreeView.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    
  

    helpListView.setLayoutX(135);
    helpListView.setLayoutY(140);
    helpListView.setPrefSize(452, 550);
    helpListView.setStyle("-fx-font: 24 arial;");
    helpListView.getSelectionModel().selectedItemProperty().addListener((curr, oldValue, newValue) -> {
      String current = curr.getValue();
      current = "TreeItem [ value: " + current + "]";
      // my page related pictures
      if (current.contains("TreeItem [ value: My page ]")) {
        changeableView.setImage(myPageClear);
      } else if (current.contains("TreeItem [ value: Favorites ]")) {
        changeableView.setImage(myPageFavorites);
      } else if (current.contains("TreeItem [ value: Recipe search function ]")) {
        changeableView.setImage(myPageSearch);
      } else if (current.contains("TreeItem [ value: Recipe Tags ]")) {
        changeableView.setImage(myPageRecipeTags);
      } else if (current.contains("TreeItem [ value: Navigation buttons ]")) {
        changeableView.setImage(myPageButtons);
      } else if (current.contains("TreeItem [ value: Add tag ]")) {
      changeableView.setImage(addtag);  
      } else if (current.contains("TreeItem [ value: Already existing tag ]")) {
      changeableView.setImage(tagsalreadyexist);
      } else if (current.contains("TreeItem [ value: How to comment ]")) {
      changeableView.setImage(comment);
      }

      // chat related pictures
      else if (current.equals("TreeItem [ value: Chat ]")) {
        changeableView.setImage(chatPageClear);
      } else if (current.contains("TreeItem [ value: Received ]")) {
        changeableView.setImage(chatPageReceived);
      } else if (current.contains("TreeItem [ value: Send ]")) {
        changeableView.setImage(chatPageSent);
      } else if (current.contains("TreeItem [ value: Chat buttons ]")) {
        changeableView.setImage(chatPageNewMessage);
      }

      // create recipe related pictures
      else if (current.contains("Create new recipe")) {
        changeableView.setImage(createRecipePageClear);
      } else if (current.contains("TreeItem [ value: Add ingredient ]")) {
        changeableView.setImage(createRecipePageCreate);
      }

      // weekly food plan pictures
      else if (current.contains("Weekly food plan")) {
        changeableView.setImage(weekMealPlanPageClear);
      } else if (current.contains("TreeItem [ value: View your weekly meal plan ]")) {
        changeableView.setImage(weekMealPlanPageView);
      } else if (current.contains("TreeItem [ value: add weekly meal plan ]")) {
        changeableView.setImage(weekMealPlanPageAddRecipe);
      }

      // shopping cart pictures
      else if (current.contains("TreeItem [ value: Shopping cart ]")) {
        changeableView.setImage(shoppingfoodplan);
      } else if (current.contains("TreeItem [ value: Choose week and edit ]")) {
        changeableView.setImage(shoppingfoodplanhelp);
      }

      // user management pictures
      else if (current.contains("TreeItem [ value: User management ]")) {
        changeableView.setImage(userManagementPage);
      } else if (current.contains("TreeItem [ value: User search function ]")) {
        changeableView.setImage(userManagementUserSearchFunction);
      } else if (current.contains("TreeItem [ value: User list ]")) {
        changeableView.setImage(userManagementUserList);
      } else if (current.contains("TreeItem [ value: Add user ]")) {
        changeableView.setImage(userManagementUserAdd);
      } else if (current.contains("TreeItem [ value: Delete user ]")) {
        changeableView.setImage(userManagementUserDelete);
      } else if (current.contains("TreeItem [ value: Edit user ]")) {
        changeableView.setImage(userManagementUserEdit);
      }
    });

    helpPageText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    helpPageText.setWrappingWidth(200);
    helpPageText.setLayoutX(280);
    helpPageText.setLayoutY(70);
    helpPageText.setText("Tutorials");
    helpPageText.setFill(Color.BLACK);

    root.getChildren().add(pane);
    Scene s = new Scene(root);
    helpStage.setScene(s);
  }
}
