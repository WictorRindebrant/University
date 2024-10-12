package cookbook;

import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;

/**
 * Create scene to edit the meal plan layout.
 */
public class WeekEditScene {
  StackPane root = new StackPane();

  Pane pane = new Pane();

  int largeButtonWidth = 300;
  int buttonHeight = 30;
  int smallButtonWidth = 100;
  int leftAmountXloc = 480;
  int leftRecipeXloc = 160;
  int rightAmountXloc = 1000;
  int rightRecipeXloc = 680;

  Text leftHeadText = new Text();
  Text mondayText = new Text();
  Text tuesdayText = new Text();
  Text wednesdayText = new Text();
  Text thursdayText = new Text();
  Text fridayText = new Text();
  Text saturdayText = new Text();
  Text sundayText = new Text();
  Text weekText = new Text();

  Button backButton = new Button();
  Button save = new Button();
  Button clear = new Button();

  EditableButton weekButton = new EditableButton();

  EditableButton monBreakfast = new EditableButton();
  EditableButton monLunch = new EditableButton();
  EditableButton monDinner = new EditableButton();
  EditableButton tueBreakfast = new EditableButton();
  EditableButton tueLunch = new EditableButton();
  EditableButton tueDinner = new EditableButton();
  EditableButton wedBreakfast = new EditableButton();
  EditableButton wedLunch = new EditableButton();
  EditableButton wedDinner = new EditableButton();
  EditableButton thuBreakfast = new EditableButton();
  EditableButton thuLunch = new EditableButton();
  EditableButton thuDinner = new EditableButton();
  EditableButton friBreakfast = new EditableButton();
  EditableButton friLunch = new EditableButton();
  EditableButton friDinner = new EditableButton();
  EditableButton satBreakfast = new EditableButton();
  EditableButton satLunch = new EditableButton();
  EditableButton satDinner = new EditableButton();
  EditableButton sunBreakfast = new EditableButton();
  EditableButton sunLunch = new EditableButton();
  EditableButton sunDinner = new EditableButton();

  EditableButton monBreakAmount = new EditableButton();
  EditableButton monLunchAmount = new EditableButton();
  EditableButton monDinnerAmount = new EditableButton();
  EditableButton tueBreakAmount = new EditableButton();
  EditableButton tueLunchAmount = new EditableButton();
  EditableButton tueDinnerAmount = new EditableButton();
  EditableButton wedBreakAmount = new EditableButton();
  EditableButton wedLunchAmount = new EditableButton();
  EditableButton wedDinnerAmount = new EditableButton();
  EditableButton thuBreakAmount = new EditableButton();
  EditableButton thuLunchAmount = new EditableButton();
  EditableButton thuDinnerAmount = new EditableButton();
  EditableButton friBreakAmount = new EditableButton();
  EditableButton friLunchAmount = new EditableButton();
  EditableButton friDinnerAmount = new EditableButton();
  EditableButton satBreakAmount = new EditableButton();
  EditableButton satLunchAmount = new EditableButton();
  EditableButton satDinnerAmount = new EditableButton();
  EditableButton sunBreakAmount = new EditableButton();
  EditableButton sunLunchAmount = new EditableButton();
  EditableButton sunDinnerAmount = new EditableButton();

  Image backArrow = new Image("file:app/images/back_arrow.png");
  Image book = new Image("file:app/images/book.jpg");

  ImageView backArrowView = new ImageView(backArrow);
  ImageView bookView = new ImageView(book);

  Query query = new Query();
  int[] dayData = null;
  String thefont;

  public void start(Stage weekListStage, User user, int week_id, String thefont) throws SQLException {

    pane.getChildren().addAll(backButton, weekButton, weekText, mondayText, thursdayText, wednesdayText,
        tuesdayText, fridayText, saturdayText, sundayText, monBreakfast, monLunch, monDinner,
        tueBreakfast, tueLunch, tueDinner, wedBreakfast, wedLunch, wedDinner, thuBreakfast, thuLunch,
        thuDinner, friBreakfast, friLunch, friDinner, satBreakfast, satLunch, satDinner, sunLunch,
        sunBreakfast, sunDinner, monBreakAmount, monLunchAmount, monDinnerAmount, tueBreakAmount,
        tueLunchAmount, tueDinnerAmount, wedBreakAmount, wedLunchAmount, wedDinnerAmount, thuBreakAmount,
        thuLunchAmount, thuDinnerAmount, friBreakAmount, friLunchAmount, friDinnerAmount, satBreakAmount,
        satLunchAmount, satDinnerAmount, sunBreakAmount, sunLunchAmount, sunDinnerAmount, save, clear);

    weekText.setFont(Font.font(thefont, FontWeight.BOLD, 30));
    weekText.setLayoutX(280);
    weekText.setLayoutY(80);
    weekText.setText("Week: ");

    // The text of the days names.
    mondayText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    mondayText.setLayoutX(leftRecipeXloc);
    mondayText.setLayoutY(115);
    mondayText.setText("Monday:");

    tuesdayText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    tuesdayText.setLayoutX(leftRecipeXloc);
    tuesdayText.setLayoutY(280);
    tuesdayText.setText("Tuesday:");

    wednesdayText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    wednesdayText.setLayoutX(leftRecipeXloc);
    wednesdayText.setLayoutY(445);
    wednesdayText.setText("Wednesday:");

    thursdayText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    thursdayText.setLayoutX(rightRecipeXloc);
    thursdayText.setLayoutY(40);
    thursdayText.setText("Thursday:");

    fridayText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    fridayText.setLayoutX(rightRecipeXloc);
    fridayText.setLayoutY(205);
    fridayText.setText("Friday:");

    saturdayText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    saturdayText.setLayoutX(rightRecipeXloc);
    saturdayText.setLayoutY(370);
    saturdayText.setText("Saturday:");

    sundayText.setFont(Font.font(thefont, FontWeight.BOLD, 20));
    sundayText.setLayoutX(rightRecipeXloc);
    sundayText.setLayoutY(535);
    sundayText.setText("Sunday:");

    // Week buttons
    weekButton.setLayoutX(370);
    weekButton.setLayoutY(50);
    weekButton.setPrefSize(80, buttonHeight);
    weekButton.setText(Integer.toString(week_id));
    weekButton.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    weekButton.setDisplay(-16, 76, 80);

    dayData = query.getWeekDay("mondayfood", user.getId(), week_id);
    // Monday recipe buttons
    monBreakfast.setLayoutX(leftRecipeXloc);
    monBreakfast.setLayoutY(125);
    monBreakfast.setPrefSize(largeButtonWidth, buttonHeight);
    monBreakfast.setText(ifBreakFast(dayData[2], week_id));
    monBreakfast.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    monBreakfast.setDisplay(-16, 76, largeButtonWidth);

    monLunch.setLayoutX(leftRecipeXloc);
    monLunch.setLayoutY(170);
    monLunch.setPrefSize(largeButtonWidth, buttonHeight);
    monLunch.setText(ifLunch(dayData[3], week_id));
    monLunch.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    monLunch.setDisplay(-16, 76, largeButtonWidth);

    monDinner.setLayoutX(leftRecipeXloc);
    monDinner.setLayoutY(215);
    monDinner.setPrefSize(largeButtonWidth, buttonHeight);
    monDinner.setText(ifDinner(dayData[4], week_id));
    monDinner.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    monDinner.setDisplay(-16, 76, largeButtonWidth);

    // Monday amount buttons
    monBreakAmount.setLayoutX(leftAmountXloc);
    monBreakAmount.setLayoutY(125);
    monBreakAmount.setPrefSize(smallButtonWidth, buttonHeight);
    monBreakAmount.setText(Integer.toString(dayData[5]));
    monBreakAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    monBreakAmount.setDisplay(-16, 76, smallButtonWidth);

    monLunchAmount.setLayoutX(leftAmountXloc);
    monLunchAmount.setLayoutY(170);
    monLunchAmount.setPrefSize(smallButtonWidth, buttonHeight);
    monLunchAmount.setText(Integer.toString(dayData[6]));
    monLunchAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    monLunchAmount.setDisplay(-16, 76, smallButtonWidth);

    monDinnerAmount.setLayoutX(leftAmountXloc);
    monDinnerAmount.setLayoutY(215);
    monDinnerAmount.setPrefSize(smallButtonWidth, buttonHeight);
    monDinnerAmount.setText(Integer.toString(dayData[7]));
    monDinnerAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    monDinnerAmount.setDisplay(-16, 76, smallButtonWidth);

    dayData = query.getWeekDay("tuesdayfood", user.getId(), week_id);
    // Tuesday recipe buttons
    tueBreakfast.setLayoutX(leftRecipeXloc);
    tueBreakfast.setLayoutY(290);
    tueBreakfast.setPrefSize(largeButtonWidth, buttonHeight);
    tueBreakfast.setText(ifBreakFast(dayData[2], week_id));
    tueBreakfast.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    tueBreakfast.setDisplay(-16, 76, largeButtonWidth);

    tueLunch.setLayoutX(leftRecipeXloc);
    tueLunch.setLayoutY(335);
    tueLunch.setPrefSize(largeButtonWidth, buttonHeight);
    tueLunch.setText(ifLunch(dayData[3], week_id));
    tueLunch.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    tueLunch.setDisplay(-16, 76, largeButtonWidth);

    tueDinner.setLayoutX(leftRecipeXloc);
    tueDinner.setLayoutY(380);
    tueDinner.setPrefSize(largeButtonWidth, buttonHeight);
    tueDinner.setText(ifDinner(dayData[4], week_id));
    tueDinner.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    tueDinner.setDisplay(-16, 76, largeButtonWidth);

    // Tuesday Amount buttons
    tueBreakAmount.setLayoutX(leftAmountXloc);
    tueBreakAmount.setLayoutY(290);
    tueBreakAmount.setPrefSize(smallButtonWidth, buttonHeight);
    tueBreakAmount.setText(Integer.toString(dayData[5]));
    tueBreakAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    tueBreakAmount.setDisplay(-16, 76, smallButtonWidth);

    tueLunchAmount.setLayoutX(leftAmountXloc);
    tueLunchAmount.setLayoutY(335);
    tueLunchAmount.setPrefSize(smallButtonWidth, buttonHeight);
    tueLunchAmount.setText(Integer.toString(dayData[6]));
    tueLunchAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    tueLunchAmount.setDisplay(-16, 76, smallButtonWidth);

    tueDinnerAmount.setLayoutX(leftAmountXloc);
    tueDinnerAmount.setLayoutY(380);
    tueDinnerAmount.setPrefSize(smallButtonWidth, buttonHeight);
    tueDinnerAmount.setText(Integer.toString(dayData[7]));
    tueDinnerAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    tueDinnerAmount.setDisplay(-16, 76, smallButtonWidth);

    dayData = query.getWeekDay("wednesdayfood", user.getId(), week_id);
    // Wednesday recipe buttons
    wedBreakfast.setLayoutX(leftRecipeXloc);
    wedBreakfast.setLayoutY(455);
    wedBreakfast.setPrefSize(largeButtonWidth, buttonHeight);
    wedBreakfast.setText(ifBreakFast(dayData[2], week_id));
    wedBreakfast.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    wedBreakfast.setDisplay(-16, -76, largeButtonWidth);

    wedLunch.setLayoutX(leftRecipeXloc);
    wedLunch.setLayoutY(500);
    wedLunch.setPrefSize(largeButtonWidth, buttonHeight);
    wedLunch.setText(ifLunch(dayData[3], week_id));
    wedLunch.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    wedLunch.setDisplay(-16, -76, largeButtonWidth);

    wedDinner.setLayoutX(leftRecipeXloc);
    wedDinner.setLayoutY(545);
    wedDinner.setPrefSize(largeButtonWidth, buttonHeight);
    wedDinner.setText(ifDinner(dayData[4], week_id));
    wedDinner.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    wedDinner.setDisplay(-16, -76, largeButtonWidth);

    // Wednesday amount buttons
    wedBreakAmount.setLayoutX(leftAmountXloc);
    wedBreakAmount.setLayoutY(455);
    wedBreakAmount.setPrefSize(smallButtonWidth, buttonHeight);
    wedBreakAmount.setText(Integer.toString(dayData[5]));
    wedBreakAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    wedBreakAmount.setDisplay(-16, -76, smallButtonWidth);

    wedLunchAmount.setLayoutX(leftAmountXloc);
    wedLunchAmount.setLayoutY(500);
    wedLunchAmount.setPrefSize(smallButtonWidth, buttonHeight);
    wedLunchAmount.setText(Integer.toString(dayData[6]));
    wedLunchAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    wedLunchAmount.setDisplay(-16, -76, smallButtonWidth);

    wedDinnerAmount.setLayoutX(leftAmountXloc);
    wedDinnerAmount.setLayoutY(545);
    wedDinnerAmount.setPrefSize(smallButtonWidth, buttonHeight);
    wedDinnerAmount.setText(Integer.toString(dayData[7]));
    wedDinnerAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    wedDinnerAmount.setDisplay(-16, -76, smallButtonWidth);

    dayData = query.getWeekDay("thursdayfood", user.getId(), week_id);
    // Thursday recipe buttons
    thuBreakfast.setLayoutX(rightRecipeXloc);
    thuBreakfast.setLayoutY(50);
    thuBreakfast.setPrefSize(largeButtonWidth, buttonHeight);
    thuBreakfast.setText(ifBreakFast(dayData[2], week_id));
    thuBreakfast.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    thuBreakfast.setDisplay(-16, 76, largeButtonWidth);

    thuLunch.setLayoutX(rightRecipeXloc);
    thuLunch.setLayoutY(95);
    thuLunch.setPrefSize(largeButtonWidth, buttonHeight);
    thuLunch.setText(ifLunch(dayData[3], week_id));
    thuLunch.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    thuLunch.setDisplay(-16, 76, largeButtonWidth);

    thuDinner.setLayoutX(rightRecipeXloc);
    thuDinner.setLayoutY(140);
    thuDinner.setPrefSize(largeButtonWidth, buttonHeight);
    thuDinner.setText(ifDinner(dayData[4], week_id));
    thuDinner.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    thuDinner.setDisplay(-16, 76, largeButtonWidth);

    // Thursday amount buttons
    thuBreakAmount.setLayoutX(rightAmountXloc);
    thuBreakAmount.setLayoutY(50);
    thuBreakAmount.setPrefSize(smallButtonWidth, buttonHeight);
    thuBreakAmount.setText(Integer.toString(dayData[5]));
    thuBreakAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    thuBreakAmount.setDisplay(-16, 76, smallButtonWidth);

    thuLunchAmount.setLayoutX(rightAmountXloc);
    thuLunchAmount.setLayoutY(95);
    thuLunchAmount.setPrefSize(smallButtonWidth, buttonHeight);
    thuLunchAmount.setText(Integer.toString(dayData[6]));
    thuLunchAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    thuLunchAmount.setDisplay(-16, 76, smallButtonWidth);

    thuDinnerAmount.setLayoutX(rightAmountXloc);
    thuDinnerAmount.setLayoutY(140);
    thuDinnerAmount.setPrefSize(smallButtonWidth, buttonHeight);
    thuDinnerAmount.setText(Integer.toString(dayData[7]));
    thuDinnerAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    thuDinnerAmount.setDisplay(-16, 76, smallButtonWidth);

    dayData = query.getWeekDay("fridayfood", user.getId(), week_id);
    // Friday recipe buttons
    friBreakfast.setLayoutX(rightRecipeXloc);
    friBreakfast.setLayoutY(215);
    friBreakfast.setPrefSize(largeButtonWidth, buttonHeight);
    friBreakfast.setText(ifBreakFast(dayData[2], week_id));
    friBreakfast.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    friBreakfast.setDisplay(-16, 76, largeButtonWidth);

    friLunch.setLayoutX(rightRecipeXloc);
    friLunch.setLayoutY(260);
    friLunch.setPrefSize(largeButtonWidth, buttonHeight);
    friLunch.setText(ifLunch(dayData[3], week_id));
    friLunch.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    friLunch.setDisplay(-16, 76, largeButtonWidth);

    friDinner.setLayoutX(rightRecipeXloc);
    friDinner.setLayoutY(305);
    friDinner.setPrefSize(largeButtonWidth, buttonHeight);
    friDinner.setText(ifDinner(dayData[4], week_id));
    friDinner.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    friDinner.setDisplay(-16, 76, largeButtonWidth);

    // Friday amount buttons
    friBreakAmount.setLayoutX(rightAmountXloc);
    friBreakAmount.setLayoutY(215);
    friBreakAmount.setPrefSize(smallButtonWidth, buttonHeight);
    friBreakAmount.setText(Integer.toString(dayData[5]));
    friBreakAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    friBreakAmount.setDisplay(-16, 76, smallButtonWidth);

    friLunchAmount.setLayoutX(rightAmountXloc);
    friLunchAmount.setLayoutY(260);
    friLunchAmount.setPrefSize(smallButtonWidth, buttonHeight);
    friLunchAmount.setText(Integer.toString(dayData[6]));
    friLunchAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    friLunchAmount.setDisplay(-16, 76, smallButtonWidth);

    friDinnerAmount.setLayoutX(rightAmountXloc);
    friDinnerAmount.setLayoutY(305);
    friDinnerAmount.setPrefSize(smallButtonWidth, buttonHeight);
    friDinnerAmount.setText(Integer.toString(dayData[7]));
    friDinnerAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    friDinnerAmount.setDisplay(-16, 76, smallButtonWidth);

    dayData = query.getWeekDay("saturdayfood", user.getId(), week_id);
    // Saturday recipe buttons
    satBreakfast.setLayoutX(rightRecipeXloc);
    satBreakfast.setLayoutY(380);
    satBreakfast.setPrefSize(largeButtonWidth, buttonHeight);
    satBreakfast.setText(ifBreakFast(dayData[2], week_id));
    satBreakfast.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    satBreakfast.setDisplay(-16, 76, largeButtonWidth);

    satLunch.setLayoutX(rightRecipeXloc);
    satLunch.setLayoutY(425);
    satLunch.setPrefSize(largeButtonWidth, buttonHeight);
    satLunch.setText(ifLunch(dayData[3], week_id));
    satLunch.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    satLunch.setDisplay(-16, 76, largeButtonWidth);

    satDinner.setLayoutX(rightRecipeXloc);
    satDinner.setLayoutY(470);
    satDinner.setPrefSize(largeButtonWidth, buttonHeight);
    satDinner.setText(ifDinner(dayData[4], week_id));
    satDinner.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    satDinner.setDisplay(-16, 76, largeButtonWidth);

    // Saturday amount buttons
    satBreakAmount.setLayoutX(rightAmountXloc);
    satBreakAmount.setLayoutY(380);
    satBreakAmount.setPrefSize(smallButtonWidth, buttonHeight);
    satBreakAmount.setText(Integer.toString(dayData[5]));
    satBreakAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    satBreakAmount.setDisplay(-16, 76, smallButtonWidth);

    satLunchAmount.setLayoutX(rightAmountXloc);
    satLunchAmount.setLayoutY(425);
    satLunchAmount.setPrefSize(smallButtonWidth, buttonHeight);
    satLunchAmount.setText(Integer.toString(dayData[6]));
    satLunchAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    satLunchAmount.setDisplay(-16, 76, smallButtonWidth);

    satDinnerAmount.setLayoutX(rightAmountXloc);
    satDinnerAmount.setLayoutY(470);
    satDinnerAmount.setPrefSize(smallButtonWidth, buttonHeight);
    satDinnerAmount.setText(Integer.toString(dayData[7]));
    satDinnerAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    satDinnerAmount.setDisplay(-16, 76, smallButtonWidth);

    dayData = query.getWeekDay("sundayfood", user.getId(), week_id);
    // Sunday recipe buttons
    sunBreakfast.setLayoutX(rightRecipeXloc);
    sunBreakfast.setLayoutY(545);
    sunBreakfast.setPrefSize(largeButtonWidth, buttonHeight);
    sunBreakfast.setText(ifBreakFast(dayData[2], week_id));
    sunBreakfast.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    sunBreakfast.setDisplay(-16, -76, largeButtonWidth);

    sunLunch.setLayoutX(rightRecipeXloc);
    sunLunch.setLayoutY(590);
    sunLunch.setPrefSize(largeButtonWidth, buttonHeight);
    sunLunch.setText(ifLunch(dayData[3], week_id));
    sunLunch.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    sunLunch.setDisplay(-16, -76, largeButtonWidth);

    sunDinner.setLayoutX(rightRecipeXloc);
    sunDinner.setLayoutY(635);
    sunDinner.setPrefSize(largeButtonWidth, buttonHeight);
    sunDinner.setText(ifDinner(dayData[4], week_id));
    sunDinner.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    sunDinner.setDisplay(-16, -76, largeButtonWidth);

    // Sunday amount buttons
    sunBreakAmount.setLayoutX(rightAmountXloc);
    sunBreakAmount.setLayoutY(545);
    sunBreakAmount.setPrefSize(smallButtonWidth, buttonHeight);
    sunBreakAmount.setText(Integer.toString(dayData[5]));
    sunBreakAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    sunBreakAmount.setDisplay(-16, -76, smallButtonWidth);

    sunLunchAmount.setLayoutX(rightAmountXloc);
    sunLunchAmount.setLayoutY(590);
    sunLunchAmount.setPrefSize(smallButtonWidth, buttonHeight);
    sunLunchAmount.setText(Integer.toString(dayData[6]));
    sunLunchAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    sunLunchAmount.setDisplay(-16, -76, smallButtonWidth);

    sunDinnerAmount.setLayoutX(rightAmountXloc);
    sunDinnerAmount.setLayoutY(635);
    sunDinnerAmount.setPrefSize(smallButtonWidth, buttonHeight);
    sunDinnerAmount.setText(Integer.toString(dayData[7]));
    sunDinnerAmount.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    sunDinnerAmount.setDisplay(-16, -76, smallButtonWidth);

    dayData = null;

    backButton.setFont(Font.font(thefont, FontWeight.BOLD, 15));
    backButton.setPrefSize(33, 33);
    backButton.setLayoutX(120);
    backButton.setLayoutY(40);
    backButton.setGraphic(backArrowView);
    backButton.setOnAction(event -> {
      WeekListScene wls = new WeekListScene();
      try {
        wls.start(weekListStage, user, thefont);
      } catch (Exception e) {
        System.out.println("problem with changing stage.");
        e.printStackTrace();
      }
      event.consume();
      ;
    });

    save.setLayoutX(leftAmountXloc);
    save.setLayoutY(600);
    save.setPrefSize(smallButtonWidth, 25);
    save.setText("Save");
    save.setStyle("-fx-font-size: 20; -fx-font-family: '" + thefont + "';");
    save.setOnAction(event -> {
      if(Integer.parseInt(weekButton.getText()) > 0){
        try {
          // Send data to the database.
          int saveId = Integer.parseInt(weekButton.getText());
          query.setWeekDay("mondayfood", user.getId(), saveId,
              query.getRecipeID(monBreakfast.getText()),
              query.getRecipeID(monLunch.getText()),
              query.getRecipeID(monDinner.getText()),
              Integer.parseInt(monBreakAmount.getText()),
              Integer.parseInt(monLunchAmount.getText()),
              Integer.parseInt(monDinnerAmount.getText()));

          query.setWeekDay("tuesdayfood", user.getId(), saveId,
              query.getRecipeID(tueBreakfast.getText()),
              query.getRecipeID(tueLunch.getText()),
              query.getRecipeID(tueDinner.getText()),
              Integer.parseInt(tueBreakAmount.getText()),
              Integer.parseInt(tueLunchAmount.getText()),
              Integer.parseInt(tueDinnerAmount.getText()));

          query.setWeekDay("wednesdayfood", user.getId(), saveId,
              query.getRecipeID(wedBreakfast.getText()),
              query.getRecipeID(wedLunch.getText()),
              query.getRecipeID(wedDinner.getText()),
              Integer.parseInt(wedBreakAmount.getText()),
              Integer.parseInt(wedLunchAmount.getText()),
              Integer.parseInt(wedDinnerAmount.getText()));

          query.setWeekDay("thursdayfood", user.getId(), saveId,
              query.getRecipeID(thuBreakfast.getText()),
              query.getRecipeID(thuLunch.getText()),
              query.getRecipeID(thuDinner.getText()),
              Integer.parseInt(thuBreakAmount.getText()),
              Integer.parseInt(thuLunchAmount.getText()),
              Integer.parseInt(thuDinnerAmount.getText()));

          query.setWeekDay("fridayfood", user.getId(), saveId,
              query.getRecipeID(friBreakfast.getText()),
              query.getRecipeID(friLunch.getText()),
              query.getRecipeID(friDinner.getText()),
              Integer.parseInt(friBreakAmount.getText()),
              Integer.parseInt(friLunchAmount.getText()),
              Integer.parseInt(friDinnerAmount.getText()));

          query.setWeekDay("saturdayfood", user.getId(), saveId,
              query.getRecipeID(satBreakfast.getText()),
              query.getRecipeID(satLunch.getText()),
              query.getRecipeID(satDinner.getText()),
              Integer.parseInt(satBreakAmount.getText()),
              Integer.parseInt(satLunchAmount.getText()),
              Integer.parseInt(satDinnerAmount.getText()));

          query.setWeekDay("sundayfood", user.getId(), saveId,
              query.getRecipeID(sunBreakfast.getText()),
              query.getRecipeID(sunLunch.getText()),
              query.getRecipeID(sunDinner.getText()),
              Integer.parseInt(sunBreakAmount.getText()),
              Integer.parseInt(sunLunchAmount.getText()),
              Integer.parseInt(sunDinnerAmount.getText()));

          WeekListScene wls = new WeekListScene();
          wls.start(weekListStage, user, thefont);

        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    });

    clear.setLayoutX(leftRecipeXloc);
    clear.setLayoutY(600);
    clear.setPrefSize(smallButtonWidth, 25);
    clear.setText("Clear");
    clear.setStyle("-fx-font-size: 22; -fx-font-family: '" + thefont + "';");
    clear.setOnAction(event -> {
      try {
        // Clear all the days data
        query.setWeekDay("mondayfood", user.getId(), week_id,
            0, 0, 0,
            0, 0, 0);

        query.setWeekDay("tuesdayfood", user.getId(), week_id,
            0, 0, 0,
            0, 0, 0);

        query.setWeekDay("wednesdayfood", user.getId(), week_id,
            0, 0, 0,
            0, 0, 0);

        query.setWeekDay("thursdayfood", user.getId(), week_id,
            0, 0, 0,
            0, 0, 0);

        query.setWeekDay("fridayfood", user.getId(), week_id,
            0, 0, 0,
            0, 0, 0);

        query.setWeekDay("saturdayfood", user.getId(), week_id,
            0, 0, 0,
            0, 0, 0);

        query.setWeekDay("sundayfood", user.getId(), week_id,
            0, 0, 0,
            0, 0, 0);

        WeekEditScene wes = new WeekEditScene();
        wes.start(weekListStage, user, week_id, thefont);

      } catch (SQLException e) {
        e.printStackTrace();
      }
    });

    setAllButtonsValue();

    root.getChildren().add(bookView);
    root.getChildren().add(pane);

    weekListStage.setScene(new Scene(root, 1229, 761));
    weekListStage.setTitle("Silicon Cookbook");
    weekListStage.setResizable(false);
    weekListStage.show();
  }

  /**
   * Check the text that will be displayed.
   * 
   * @param id   the recipe id.
   * @param week the week number.
   * @return return the string to be displayed.
   * @throws SQLException
   */
  private String ifBreakFast(int id, int week) throws SQLException {
    if (week != 0 && id > 0) {
      return query.getRecipe(id)[1];
    }
    return "Breakfast";
  }

  /**
   * Check the text that will be displayed.
   * 
   * @param id   the recipe id.
   * @param week the week number.
   * @return return the string to be displayed.
   * @throws SQLException
   */
  private String ifLunch(int id, int week) throws SQLException {
    if (week != 0 && id > 0) {
      return query.getRecipe(id)[1];
    }
    return "Lunch";
  }

  /**
   * Check the text that will be displayed.
   * 
   * @param id   the recipe id.
   * @param week the week number.
   * @return return the string to be displayed.
   * @throws SQLException
   */
  private String ifDinner(int id, int week) throws SQLException {
    if (week != 0 && id > 0) {
        return query.getRecipe(id)[1];
    }
    return "Dinner";
  }

  /**
   * Set all buttons lists.
   * 
   * @throws SQLException
   */
  private void setAllButtonsValue() throws SQLException {
    String[] recipeNames = query.getAllRecipesNames();
    String[] servings = { "2", "4", "6", "8", "10" };
    String[] weekNumbers = new String[52];
    for (int i = 1; i < 53; i++) {
      weekNumbers[i - 1] = Integer.toString(i);
    }

    weekButton.setList(weekNumbers);

    monBreakfast.setList(recipeNames);
    monLunch.setList(recipeNames);
    monDinner.setList(recipeNames);
    tueBreakfast.setList(recipeNames);
    tueLunch.setList(recipeNames);
    tueDinner.setList(recipeNames);
    wedBreakfast.setList(recipeNames);
    wedLunch.setList(recipeNames);
    wedDinner.setList(recipeNames);
    thuBreakfast.setList(recipeNames);
    thuLunch.setList(recipeNames);
    thuDinner.setList(recipeNames);
    friBreakfast.setList(recipeNames);
    friLunch.setList(recipeNames);
    friDinner.setList(recipeNames);
    satBreakfast.setList(recipeNames);
    satLunch.setList(recipeNames);
    satDinner.setList(recipeNames);
    sunBreakfast.setList(recipeNames);
    sunLunch.setList(recipeNames);
    sunDinner.setList(recipeNames);

    monBreakAmount.setList(servings);
    monLunchAmount.setList(servings);
    monDinnerAmount.setList(servings);
    tueBreakAmount.setList(servings);
    tueLunchAmount.setList(servings);
    tueDinnerAmount.setList(servings);
    wedBreakAmount.setList(servings);
    wedLunchAmount.setList(servings);
    wedDinnerAmount.setList(servings);
    thuBreakAmount.setList(servings);
    thuLunchAmount.setList(servings);
    thuDinnerAmount.setList(servings);
    friBreakAmount.setList(servings);
    friLunchAmount.setList(servings);
    friDinnerAmount.setList(servings);
    satBreakAmount.setList(servings);
    satLunchAmount.setList(servings);
    satDinnerAmount.setList(servings);
    sunBreakAmount.setList(servings);
    sunLunchAmount.setList(servings);
    sunDinnerAmount.setList(servings);
  }
}