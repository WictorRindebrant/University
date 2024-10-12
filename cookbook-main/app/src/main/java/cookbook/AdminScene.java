package cookbook;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class AdminScene {
  StackPane root = new StackPane();
  Pane pane = new Pane();
  TextField userSearchField = new TextField();
  Button backButton = new Button();
  Text adminPageText = new Text();
  Text modifyPageText = new Text();
  Text usernameText = new Text();
  Text passwordText = new Text();
  Text displayNameText = new Text();
  TextArea usernameField = new TextArea();
  TextArea passwordField = new TextArea();
  TextArea displayNameField = new TextArea();
  Button editButton = new Button();
  Button stopEditButton = new Button();
  Button deleteUserButton = new Button();
  Button addUserButton = new Button();
  Image backArrow = new Image("file:app/images/back_arrow.png");
  Image book = new Image("file:app/images/book.jpg");
  ImageView backArrowView = new ImageView(backArrow);
  ImageView bookView = new ImageView(book);
  ListView<String> userList = new ListView<>();
  ListView<String> searchUserList = new ListView<>();
  Query q = new Query();
  String oldUsername;
  String thefont;

  public void start(Stage adminStage, User user, String thefont) throws Exception {

    this.thefont = thefont;

    pane.getChildren().addAll(userSearchField, backButton, userList,
        adminPageText, usernameField, editButton, passwordField, displayNameField, modifyPageText,
        usernameText, passwordText, displayNameText, addUserButton, deleteUserButton);

    adminPageText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    adminPageText.setLayoutX(200);
    adminPageText.setLayoutY(70);
    adminPageText.setText("User management");
    adminPageText.setFill(Color.BLACK);

    modifyPageText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    modifyPageText.setLayoutX(800);
    modifyPageText.setLayoutY(70);
    modifyPageText.setText("Modify user");
    modifyPageText.setFill(Color.BLACK);

    usernameText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    usernameText.setLayoutX(660);
    usernameText.setLayoutY(130);
    usernameText.setText("Username");
    usernameText.setFill(Color.BLACK);

    usernameField.setLayoutX(660);
    usernameField.setLayoutY(140);
    usernameField.setPrefSize(452, 10);
    usernameField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    usernameField.setEditable(false);

    passwordText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    passwordText.setLayoutX(660);
    passwordText.setLayoutY(220);
    passwordText.setText("Password");
    passwordText.setFill(Color.BLACK);

    passwordField.setLayoutX(660);
    passwordField.setLayoutY(230);
    passwordField.setPrefSize(452, 10);
    passwordField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    passwordField.setEditable(false);

    displayNameText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    displayNameText.setLayoutX(660);
    displayNameText.setLayoutY(310);
    displayNameText.setText("Display name");
    displayNameText.setFill(Color.BLACK);

    displayNameField.setLayoutX(660);
    displayNameField.setLayoutY(320);
    displayNameField.setPrefSize(452, 10);
    displayNameField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    displayNameField.setEditable(false);

    editButton.setLayoutX(1075);
    editButton.setLayoutY(375);
    editButton.setText("Edit");
    editButton.setOnAction(e -> {
      // gets old password before setting each field to editable
      oldUsername = usernameField.getText();
      usernameField.setEditable(true);
      passwordField.setEditable(true);
      displayNameField.setEditable(true);
      if (!pane.getChildren().contains(stopEditButton)) {
        pane.getChildren().add(stopEditButton);
      }
    });

    // makes the user fields editable, updates and refreshes the
    // database when the admin hits the "Done" button.
    stopEditButton.setLayoutX(660);
    stopEditButton.setLayoutY(375);
    stopEditButton.setText("Done");
    stopEditButton.setOnAction(e -> {
      usernameField.setEditable(false);
      passwordField.setEditable(false);
      displayNameField.setEditable(false);
      pane.getChildren().remove(stopEditButton);
      // updates username
      try {
        if (usernameField.getText() != null || passwordField.getText() != null || displayNameField.getText() != null) {
        if (!usernameField.getText().isEmpty()) {
          q.editUser(oldUsername, "password", passwordField.getText());
          q.editUser(oldUsername, "displayname", displayNameField.getText());
          q.editUser(oldUsername, "username", usernameField.getText());
          userList.getItems().clear();
          for (String[] i : q.allUserData()) {
            userList.getItems().addAll(i[1].toString());
          }
        } else {
          usernameField.setText(oldUsername);
          usernameField.setStyle(
              "-fx-border-color: red; -fx-focus-color: red; -fx-border-width: 1px; -fx-border-radius: 3; -fx-border-insets: -1");
        }
      }
      } catch (SQLException e1) {

        e1.printStackTrace();
      }
    });

    userSearchField.setLayoutX(135);
    userSearchField.setLayoutY(100);
    userSearchField.setPrefWidth(452);
    userSearchField.setPromptText("Search for a user");
    userSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
      ObservableList<String> userObserverList = FXCollections.observableArrayList();
      ArrayList<String[]> recipeNameList = new ArrayList<>();
      try {
        recipeNameList = q.allUserData();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      for (String[] userData : recipeNameList) {
        userObserverList.add(userData[1]);
      }
      if (!(userSearchField.getText().isEmpty() || pane.getChildren().contains(searchUserList))) {
        pane.getChildren().remove(userList);
        pane.getChildren().add(searchUserList);
      } else if (userSearchField.getText().isEmpty()) {
        pane.getChildren().remove(searchUserList);
        pane.getChildren().add(userList);
      }
      FilteredList<String> filteredHelpChoices = new FilteredList<>(userObserverList);
      filteredHelpChoices.predicateProperty()
          .bind(Bindings.createObjectBinding(
              () -> name -> name.toLowerCase().contains(userSearchField.getText().toLowerCase()),
              userSearchField.textProperty()));

      searchUserList.setItems(filteredHelpChoices);
      searchUserList.setCellFactory(ComboBoxListCell.forListView(userObserverList));
      searchUserList.getSelectionModel().selectedItemProperty().addListener((v, oldVal, newVal) -> {
        try {
          for (String[] j : q.allUserData()) {
            if (j[1].toString().equals(newVal)) {
              usernameField.setText(j[1].toString());
              passwordField.setText(j[2].toString());
              displayNameField.setText(j[3].toString());
            }
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      });
    });

    // navigate to the main menu
    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(120);
    backButton.setLayoutY(40);
    backButton.setGraphic(backArrowView);
    backButton.setOnAction(e -> {
      MyPageScene mps = new MyPageScene();
      try {
        mps.start(adminStage, user, thefont);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    searchUserList.setLayoutX(135);
    searchUserList.setLayoutY(140);
    searchUserList.setPrefSize(452, 450);
    searchUserList.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");

    // Creates the view list of selectable users
    userList.setLayoutX(135);
    userList.setLayoutY(140);
    userList.setPrefSize(452, 450);
    userList.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    // creates the listview displaying usernames
    ArrayList<String[]> userData = q.allUserData();
    for (String[] i : userData) {
      userList.getItems().addAll(i[1].toString());
    }

    // allows the user to select one of the users displayed
    userList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
      try {
        for (String[] j : q.allUserData()) {
          if (j[1].toString().equals(newValue)) {
            usernameField.setText(j[1].toString());
            passwordField.setText(j[2].toString());
            displayNameField.setText(j[3].toString());
          }
        }
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    });

    // gives the admin an option to add a user.
    addUserButton.setLayoutX(135);
    addUserButton.setLayoutY(600);
    addUserButton.setText("Add user");
    addUserButton.setOnAction(e -> {
      usernameField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
      try {
        q.addUser("new user", "", "", "0");
        userList.getItems().clear();
        for (String[] i : q.allUserData()) {
          userList.getItems().addAll(i[1].toString());
        }
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    });

    // gives the admin an option to delete selected user.
    deleteUserButton.setLayoutX(465);
    deleteUserButton.setLayoutY(600);
    deleteUserButton.setText("Delete selected user");
    deleteUserButton.setOnAction(e -> {
      usernameField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
      try {
        if (usernameField.getText() != null) {
        if (!usernameField.getText().isEmpty()) {
          ArrayList<String[]> allComments = q.getComments();
          for (String comments[] : allComments) {
            if (comments[1].equals((String.valueOf(q.getUserID(usernameField.getText()))))) {
              q.removeComment(Integer.parseInt(comments[0]));
            }
          }
          q.delUser(usernameField.getText());
          userList.getItems().clear();
          for (String[] i : q.allUserData()) {
            userList.getItems().addAll(i[1].toString());
          }
          usernameField.setText(null);
          passwordField.setText(null);
          displayNameField.setText(null);
        }
        } else {
          deleteUserButton.setStyle(
              "-fx-border-color: red; -fx-focus-color: red; -fx-border-width: 1px; -fx-border-radius: 3; -fx-border-insets: -1");
        }
      
      } catch (SQLException e1) {
        e1.printStackTrace();
      }

    });

    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    adminStage.setScene(new Scene(root, 1229, 761));
    adminStage.setTitle("Silicon Cookbook");
    adminStage.setResizable(false);
    adminStage.show();
  }
}
