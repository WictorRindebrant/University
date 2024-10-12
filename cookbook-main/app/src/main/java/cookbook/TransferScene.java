package cookbook;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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


public class TransferScene {
  Button welcomebutton = new Button();
  Image book = new Image("file:app/images/book.jpg");
  Image cook = new Image("file:app/images/CookbookAnimation.gif");
  ImageView bookView = new ImageView(book);
  ImageView cookView = new ImageView(cook);
  Pane pane = new Pane();
  StackPane root = new StackPane();
  private Text welcome = new Text();
  ComboBox<String> font = new ComboBox<>();
  String thefont;

  public void start(Stage primaryStage, User user) throws Exception {
    pane.setPrefSize(1229, 671);
    pane.getChildren().addAll(welcomebutton, welcome, cookView, font);
    cookView.setLayoutX(100);
    cookView.setLayoutY(200);
    root.getChildren().addAll(bookView, pane);

    welcome.setFont(Font.font(thefont, FontWeight.BOLD, 60));
    welcome.setWrappingWidth(440);
    welcome.setLayoutX(135);
    welcome.setLayoutY(100);
    welcome.setText("Welcome\n"  + user.getDisplayName());
    welcome.setTextAlignment(TextAlignment.CENTER);
    welcome.setFill(Color.BLACK);

    welcomebutton.setLayoutX(280.0);
    welcomebutton.setLayoutY(550.0);
    welcomebutton.setText("Go to my page scene");

    Scene s = new Scene(root);primaryStage.setScene(s);
    primaryStage.setScene(s);
    MyPageScene mps = new MyPageScene();
    welcomebutton.setOnAction(e-> {
      try {
        mps.start(primaryStage, user, font.getValue());
      } catch (Exception e1) {
        System.out.println("error404.");
        e1.printStackTrace();
    }
  }
  );

  // Sets the font choosen by user.
  String[] fonts = {"Arial", "Times New Roman", "Verdana",
    "Comic Sans MS", "Impact", "Lucida Sans Unicode", "Calibri"};
    ObservableList<String> fontlist = font.getItems();

    for (String i: fonts) {
      fontlist.add(i);
    }
    
    font.setPrefSize(100, 10);
    font.setLayoutY(650);
    font.setLayoutX(135);
    font.setStyle("-fx-font: 14 " + thefont);
    font.setPromptText("Choose font");
    font.getSelectionModel().select("Times New Roman");
  
  }
}