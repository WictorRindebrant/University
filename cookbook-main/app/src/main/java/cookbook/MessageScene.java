package cookbook;

import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;

/**
 * MessageScene create the pages where the user create and interact with messages.
 */
public class MessageScene {

  StackPane root = new StackPane();

  Pane pane = new Pane();

  TextField recipeField = new TextField();
  TextField toField = new TextField();

  Text leftHeadText = new Text();
  Text receievedText = new Text();
  Text sentText = new Text();
  Text recipeText = new Text();
  Text messageText = new Text();
  Text toText = new Text();
  Text errorText = new Text();

  TextArea recipeArea = new TextArea();
  TextArea messageArea = new TextArea();

  Button recipeButton = new Button();
  Button backButton = new Button();
  Button deleteMessageButton = new Button();
  Button newMessageButton = new Button();
  Button sendButton = new Button();

  Image backArrow = new Image("file:app/images/back_arrow.png");
  Image book = new Image("file:app/images/book.jpg");

  ImageView backArrowView = new ImageView(backArrow);
  ImageView bookView = new ImageView(book);

  ListView<MessageDivision> receievedView = new ListView<>();
  ListView<MessageDivision> sentView = new ListView<>();

  MessageDivision messageHolder = null;

  Query query = new Query();

  String thefont;

  /**
   * Initiate the message page.
   * 
   * @param messageStage the container to host the different pages.
   * @param user      the object with the current user information.
   * @throws SQLException
   */
  public void start(Stage messageStage, User user, String thefont) throws SQLException {

    this.thefont = thefont;

    pane.getChildren().addAll(leftHeadText, backButton, receievedView, receievedText,
        sentText, sentView, newMessageButton, deleteMessageButton);

    leftHeadText.setFont(Font.font(thefont, FontWeight.BOLD, 40));
    leftHeadText.setLayoutX(300);
    leftHeadText.setLayoutY(90);
    leftHeadText.setText("Inbox");

    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(120);
    backButton.setLayoutY(40);
    backButton.setGraphic(backArrowView);
    backButton.setOnAction(event -> {
      MyPageScene mps = new MyPageScene();
      try {
        mps.start(messageStage, user, thefont);
      } catch (Exception e1) {
        System.out.println("problem with changing stage.");
        e1.printStackTrace();
      }
      event.consume();
      ;
    });

    receievedText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    receievedText.setLayoutX(135);
    receievedText.setLayoutY(150);
    receievedText.setText("Receieved:");

    sentText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    sentText.setLayoutX(135);
    sentText.setLayoutY(390);
    sentText.setText("Sent:");

    receievedView.setLayoutX(135);
    receievedView.setLayoutY(160);
    receievedView.setPrefSize(452, 210);
    receievedView.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    receievedView.setCellFactory(lv -> {
      // Set the displayed text
      ListCell<MessageDivision> cell = new ListCell<>() {
        protected void updateItem(MessageDivision item, boolean empty) {
          super.updateItem(item, empty);
          setText(empty || item == null ? "" : item.getReceiver());
        }
      };

      // Set event on mouse click
      cell.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
        messageHolder = cell.getItem();
        if (messageHolder != null) {
          setMessage(messageStage, pane, messageHolder, user, query);
        }

      });
      return cell;
    });

    sentView.setLayoutX(135);
    sentView.setLayoutY(400);
    sentView.setPrefSize(452, 210);
    sentView.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    sentView.setCellFactory(lv -> {
      // Set the displayed text
      ListCell<MessageDivision> cell = new ListCell<>() {
        protected void updateItem(MessageDivision item, boolean empty) {
          super.updateItem(item, empty);
          setText(empty || item == null ? "" : item.getReceiver());
        }
      };
      // Set event on mouse click
      cell.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
        messageHolder = cell.getItem();
        if (messageHolder != null) {
          setMessage(messageStage, pane, messageHolder, user, query);
        }

      });

      return cell;
    });

    newMessageButton.setLayoutX(135);
    newMessageButton.setLayoutY(620);
    newMessageButton.setText("New Message");
    newMessageButton.setOnAction(event -> {
      messageHolder = null;
      setNewMessage(pane, user, query);
      event.consume();
    });

    deleteMessageButton.setLayoutX(538);
    deleteMessageButton.setLayoutY(620);
    deleteMessageButton.setText("Delete");
    deleteMessageButton.setOnAction(event -> {
      try {
        if (messageHolder != null) {
        if (String.valueOf(user.getId()).equals(messageHolder.getSenderID())) {
          query.deleteMessage(messageHolder.getID());
          messageHolder = null;
          setView(pane, query, user);
        }
      }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      event.consume();
    });

    setView(pane, query, user);

    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    messageStage.setScene(new Scene(root, 1229, 761));
    messageStage.setTitle("Silicon Cookbook");
    messageStage.setResizable(false);
    messageStage.show();
  }

  /**
   * Set the right side with message content.
   * 
   * @param stage the container to host the different pages
   * @param pane  the container for the nodes
   * @param item  the current message
   * @param user  the user object holding the users data.
   * @param query the query object to get data from database.
   */
  private void setMessage(Stage stage, Pane pane, MessageDivision item, User user, Query query) {

    pane.getChildren().removeAll(recipeButton, recipeArea, recipeText, recipeField,
        messageText, messageArea, sendButton, toText, toField, errorText);
    pane.getChildren().addAll(recipeButton, recipeArea);

    recipeButton.setLayoutX(655);
    recipeButton.setLayoutY(160);
    recipeButton.setPrefSize(450, 50);
    recipeButton.setText(item.getRecipe());
    recipeButton.setStyle("-fx-font-size: 24; -fx-font-family: '" + thefont + "';");
    recipeButton.setOnAction(e -> {
      RecipeScene rs = new RecipeScene();
      try {
        rs.start(stage, item.getRecipe(), user, thefont);
      } catch (Exception e1) {
        System.out.println("problem with changing stage.");
        e1.printStackTrace();
      }
    });

    recipeArea.setLayoutX(655);
    recipeArea.setLayoutY(220);
    recipeArea.setPrefSize(450, 250);
    recipeArea.setText(item.getText());
  }

  /**
   * Set the right side to send message.
   * 
   * @param pane  the container for the nodes.
   * @param user  the user object holding the users data.
   * @param query the query object to get data from database.
   */
  private void setNewMessage(Pane pane, User user, Query query) {
    pane.getChildren().removeAll(recipeButton, recipeArea, recipeText, recipeField,
        messageText, messageArea, sendButton, toText, toField, errorText);
    pane.getChildren().addAll(recipeText, recipeField, messageText, messageArea,
        sendButton, toText, toField);

    toText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    toText.setLayoutX(660);
    toText.setLayoutY(155);
    toText.setText("To:");

    toField.setLayoutX(660);
    toField.setLayoutY(160);
    toField.setPrefSize(450, 10);
    toField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    toField.setEditable(true);

    recipeText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    recipeText.setLayoutX(660);
    recipeText.setLayoutY(217);
    recipeText.setText("Recipe Name:");

    recipeField.setLayoutX(660);
    recipeField.setLayoutY(222);
    recipeField.setPrefSize(450, 10);
    recipeField.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    recipeField.setEditable(true);

    messageText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    messageText.setLayoutX(660);
    messageText.setLayoutY(280);
    messageText.setText("Message Text:");

    messageArea.setLayoutX(660);
    messageArea.setLayoutY(285);
    messageArea.setPrefSize(450, 220);
    messageArea.setStyle("-fx-font-size: 14; -fx-font-family: '" + thefont + "';");
    messageArea.setEditable(true);

    sendButton.setLayoutX(1065);
    sendButton.setLayoutY(520);
    sendButton.setText("Send");
    sendButton.setOnAction(event -> {
      try {
        // Get data
        int recipe_id = query.getRecipeID(recipeField.getText());
        int sender_id = user.getId();
        int receiver_id = query.getID(toField.getText());
        String text = messageArea.getText().replace("\n", "#");

        // Clear page and send message if true.
        if (recipe_id > -1 && sender_id > -1 && receiver_id > -1) {
          query.addMessage(recipe_id, sender_id, receiver_id, text);

          toField.clear();
          recipeField.clear();
          messageArea.clear();

          setView(pane, query, user);
        } else {
          if (!pane.getChildren().contains(errorText)) {
            pane.getChildren().add(errorText);
          }

          errorText.setFont(Font.font(thefont, 20));
          errorText.setLayoutX(850);
          errorText.setLayoutY(540);
          errorText.setText("Entered data is incorrect:");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Set the send and received views.
   * 
   * @param pane  the container for the nodes.
   * @param query the query object to get data from database.
   * @param user  the user object holding the users data.
   * @throws SQLException
   */
  private void setView(Pane pane, Query query, User user) throws SQLException {
    sentView.getItems().clear();
    receievedView.getItems().clear();
    pane.getChildren().removeAll(recipeButton, recipeArea, recipeText, recipeField,
        messageText, messageArea, sendButton, toText, toField, errorText);

    // Add the sent messages.
    for (String[] data : query.getSenderData(user.getId())) {
      MessageDivision message = new MessageDivision(Integer.parseInt(data[0]),
          query.getRecipe(Integer.parseInt(data[1]))[1],
          data[3],
          data[2].replace("#", "\n"),
          data[4]);
      sentView.getItems().add(message);
    }

    // Add the received messages.
    for (String[] data : query.getReceiverData(user.getId())) {
      MessageDivision message = new MessageDivision(Integer.parseInt(data[0]),
          query.getRecipe(Integer.parseInt(data[1]))[1],
          data[3],
          data[2].replace("#", "\n"),
          data[4]);
      receievedView.getItems().add(message);
    }
  }
}
