package cookbook;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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

public class LoginScene extends Application {

  private StackPane root = new StackPane();
  private Pane pane = new Pane();
  private Text headLine = new Text();
  private Text userText = new Text();
  private Text passText = new Text();
  private Text errorLoginMessage = new Text();
  private TextField usernameField = new TextField();
  private PasswordField passwordField = new PasswordField();
  private Button loginButton = new Button();
  private Button exitButton = new Button();
  private String thefont;

  /**
   * In this function we pretty much handles everything. 
   * Setting the positions for everything and handles auctions for buttons.
   */
  public void start(Stage primaryStage) throws Exception {
    pane.getChildren().addAll(headLine, userText, passText,
        usernameField, passwordField, loginButton, errorLoginMessage, exitButton);

    Image book = new Image("file:app/images/bookCover.jpg");
    final ImageView bookView = new ImageView(book);
    
    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    headLine.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
    headLine.setWrappingWidth(600);
    headLine.setLayoutX(540);
    headLine.setLayoutY(250);
    headLine.setText("   Silicon \nCookbook");
    headLine.setFill(Color.BLACK);

    userText.setFont(Font.font("Times New Roman", 20));
    userText.setWrappingWidth(600);
    userText.setLayoutX(555);
    userText.setLayoutY(347);
    userText.setText("Username:");
    userText.setFill(Color.BLACK);

    passText.setFont(Font.font("Times New Roman", 20));
    passText.setWrappingWidth(600);
    passText.setLayoutX(555);
    passText.setLayoutY(397);
    passText.setText("Password:");
    passText.setFill(Color.BLACK);

    usernameField.setLayoutX(555.0);
    usernameField.setLayoutY(350.0);
    usernameField.setPromptText("username");

    passwordField.setLayoutX(555.0);
    passwordField.setLayoutY(400.0);
    passwordField.setPromptText("password");

    loginButton.setLayoutX(555.0);
    loginButton.setLayoutY(450.0);
    loginButton.setText("Login");
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      /**
       * Checking with the Database if the things the user entered
       * in the textboxes for username and password is correct.
       * In that case sends the user to the next scene. 
       * And if not gives and little error message.
       */
      public void handle(ActionEvent event) {
          User user = new User(usernameField.getText(), passwordField.getText());
          if (user.getDisplayName() != null) {
            TransferScene tf = new TransferScene();
            try {
              tf.start(primaryStage, user);
            } catch (Exception e1) {
              System.out.println("problem with changing stage.");
              e1.printStackTrace();
            }
          } else {
            errorLoginMessage.setFont(Font.font(thefont, FontWeight.BOLD, 25));
            errorLoginMessage.setWrappingWidth(600.0);
            errorLoginMessage.setLayoutX(525.0);
            errorLoginMessage.setLayoutY(500.0);
            errorLoginMessage.setText("wrong login details");
            errorLoginMessage.setFill(Color.RED);

              
            }
          }
    });

    exitButton.setLayoutX(670);
    exitButton.setLayoutY(450);
    exitButton.setText("Exit");
    exitButton.setOnAction(e -> {
      Platform.exit();
    });
    primaryStage.setScene(new Scene(root, 1229, 761));
    primaryStage.setTitle("Silicon Cookbook");
    primaryStage.setResizable(false);
    primaryStage.show();

    // This is temp for skipping loginScene.
    // User user = new User("johan", "johan123");
    // MyPageScene mps = new MyPageScene();
    //   try {
    //     mps.start(primaryStage, user);
    //   } catch (Exception e1) {
    //     System.out.println("problem with changing stage.");
    //     e1.printStackTrace();
    //   }
    }

  /**
   * From here we start the application.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
