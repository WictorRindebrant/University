package cookbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 * Insert data into the database.
 */
class Data {

  private final CsvReader csvr;

  /**
   * Constructor for data.
   */
  public Data() {
    csvr = new CsvReader();
  }

  /**
   * Insert ingredients into the ingredient table.
   * 
   * @param con the connection to cookbook.
   * @param Data the data to insert.
   * @throws Exception
   */
  public void insertUserData(Connection con) {
    try {
      ArrayList<String[]> data = csvr.getData("app/resources/Users.csv");
      PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO cookbook.Users" +
                                                     "(username, password, displayname, admin) " +
                                                     "VALUES(?, ?, ?, ?)");
      for(int i = 0; i < data.size(); i++) {
        pstmt.setString(1, data.get(i)[0]);
        pstmt.setString(2, data.get(i)[1]);
        pstmt.setString(3, data.get(i)[2]);
        pstmt.setString(4, data.get(i)[3]);
        pstmt.addBatch();
      }
      pstmt.executeBatch();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert ingredients into the ingredient table.
   * 
   * @param con the connection to cookbook.
   * @param Data the data to insert.
   * @throws Exception
   */
  public void insertIngredientData(Connection con) {
    try {
      ArrayList<String[]> data = csvr.getData("app/resources/Ingredients.csv");
      PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO cookbook.Ingredients" +
                                                     "(name, measure) " +
                                                     "VALUES(?, ?)");
      for(int i = 0; i < data.size(); i++) {
        pstmt.setString(1, data.get(i)[0]);
        pstmt.setString(2, data.get(i)[1]);
        pstmt.addBatch();
      }
      pstmt.executeBatch();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert recipes into the recipe table.
   * 
   * @param con the connection to cookbook.
   * @param Data the data to insert.
   * @throws Exception
   */
  public void insertRecipeData(Connection con) {
    try {
      ArrayList<String[]> data = csvr.getData("app/resources/Recipes.csv");
      PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO cookbook.Recipes" +
                                                     "(name, instruction, type, hovertext) " +
                                                     "VALUES(?, ?, ?, ?)");
      for(int i = 0; i < data.size(); i++) {
        pstmt.setString(1, data.get(i)[0]);
        pstmt.setString(2, data.get(i)[1]);
        pstmt.setString(3, data.get(i)[2]);
        pstmt.setString(4, data.get(i)[3]);
        pstmt.addBatch();
      }
      pstmt.executeBatch();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert quantities of the ingredients for each recipe into the quantities table.
   * 
   * @param con the connection to cookbook.
   * @param Data the data to insert.
   * @throws Exception
   */
  public void insertQuantityData(Connection con) {
    try {
      ArrayList<String[]> data = csvr.getData("app/resources/Quantities.csv");
      PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO cookbook.Quantities" +
                                                     "(ingredient_id, recipe_id, amount) " +
                                                     "VALUES(?, ?, ?)");
      for(int i = 0; i < data.size(); i++) {
        pstmt.setString(1, data.get(i)[0]);
        pstmt.setString(2, data.get(i)[1]);
        pstmt.setString(3, data.get(i)[2]);
        pstmt.addBatch();
      }
      pstmt.executeBatch();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert all users messages into the messages table.
   * 
   * @param con the connection to cookbook.
   * @param Data the data to insert.
   * @throws Exception
   */
  public void insertMessageData(Connection con) {
    try {
      ArrayList<String[]> data = csvr.getData("app/resources/Messages.csv");
      PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO cookbook.Messages" +
                                                      "(recipe_id, sender_id, receiver_id, text) " +
                                                      "VALUES(?, ?, ?, ?)");
      for(int i = 0; i < data.size(); i++) {
        pstmt.setString(1, data.get(i)[0]);
        pstmt.setString(2, data.get(i)[1]);
        pstmt.setString(3, data.get(i)[2]);
        pstmt.setString(4, data.get(i)[3]);
        pstmt.addBatch();
      }
      pstmt.executeBatch();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

    /**
   * Insert all users comments into the comments table.
   * 
   * @param con the connection to cookbook.
   * @param Data the data to insert.
   * @throws Exception
   */
  public void insertCommentData(Connection con) {
    try {
      ArrayList<String[]> data = csvr.getData("app/resources/Comments.csv");
      PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO cookbook.Comments" +
                                                      "(user_id, recipe_id, text) " +
                                                      "VALUES(?, ?, ?)");
      for(int i = 0; i < data.size(); i++) {
        pstmt.setString(1, data.get(i)[0]);
        pstmt.setString(2, data.get(i)[1]);
        pstmt.setString(3, data.get(i)[2]);
        pstmt.addBatch();
      }
      pstmt.executeBatch();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

    /**
   * Insert all users favorites into the favorites table.
   * 
   * @param con the connection to cookbook.
   * @param Data the data to insert.
   * @throws Exception
   */
  public void insertFavoriteData(Connection con) {
    try {
      ArrayList<String[]> data = csvr.getData("app/resources/Favorites.csv");
      PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO cookbook.Favorites" +
                                                      "(user_id, recipe_id) " +
                                                      "VALUES(?, ?)");
      for(int i = 0; i < data.size(); i++) {
        pstmt.setString(1, data.get(i)[0]);
        pstmt.setString(2, data.get(i)[1]);
        pstmt.addBatch();
      }
      pstmt.executeBatch();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}