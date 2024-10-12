package cookbook;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * Extends button so that users can choose a object from a given list.
 */
public class EditableButton extends Button {
  ListView<String> lstView = new ListView<>();

  public EditableButton() {
    setOnMouseClicked(event -> {
      this.toFront();
      setGraphic(lstView);
    });

    lstView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
      setText(newValue);
      setGraphic(null);
    });
  }

  /**
   * Add items to the listView.
   * 
   * @param str string list.
   */
  public void setList(String[] str) {
    lstView.getItems().addAll(str);
    lstView.setMaxHeight(200);
  }

  /**
   * Change the display of the ListView.
   * 
   * @param xloc Displacement of x coordinate.
   * @param yloc Displacement of y coordinate.
   * @param width Prefered width.
   */
  public void setDisplay(int xloc, int yloc, int width) {
    lstView.setTranslateX(xloc);
    lstView.setTranslateY(yloc);
    lstView.setPrefWidth(width);
  }
}