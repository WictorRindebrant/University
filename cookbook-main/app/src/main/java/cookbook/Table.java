package cookbook;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create the database tables.
 */
class Table {

  /**
   * Create the user table.
   * 
   * @param con the connection to MySQL.
   * @throws Exception
   */
  public void createUserTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.Users" +
                   "(id INT NOT NULL AUTO_INCREMENT," +
                   "username varchar(50) NOT NULL," +
                   "password varchar(50) NOT NULL," +
                   "displayname varchar(50) NOT NULL," +
                   "admin BOOLEAN NOT NULL," +
                   "UNIQUE(username)," +
                   "PRIMARY KEY(id))";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the ingredient table.
   * 
   * @param con the connection to MySQL.
   * @throws Exception
   */
  public void createIngredientTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.Ingredients" +
                   "(id INT NOT NULL AUTO_INCREMENT," +
                   "name varchar(50) NOT NULL," +
                   "measure varchar(10)," +
                   "PRIMARY KEY(id))";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the recipe table.
   * 
   * @param con the connection to MySQL.
   * @throws Exception
   */
  public void createRecipeTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.Recipes" +
                   "(id INT NOT NULL AUTO_INCREMENT," +
                   "name varchar(50) NOT NULL," +
                   "instruction varchar(1000)," +
                   "type varchar(1000)," +
                   "hovertext varchar(1000)," +
                   "UNIQUE(name)," +
                   "PRIMARY KEY(id))";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the quantity table.
   * 
   * @param con the connection to MySQL.
   * @throws Exception
   */
  public void createQuantityTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.Quantities" +
                   "(ingredient_id INT NOT NULL," +
                   "recipe_id INT NOT NULL," +
                   "amount FLOAT NOT NULL," +
                   "UNIQUE(ingredient_id, recipe_id))";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

   /**
   * Create the message table.
   * 
   * @param con the connection to MySQL.
   * @throws Exception
   */
  public void createMessageTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.Messages" +
                   "(id INT NOT NULL AUTO_INCREMENT," +
                   "recipe_id INT NOT NULL," +
                   "sender_id INT NOT NULL," +
                   "receiver_id INT NOT NULL," +
                   "text varchar(255)," +
                   "PRIMARY KEY(id))";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the comment table.
   * 
   * @param con the connection to MySQL.
   * @throws Exception
   */
  public void createCommentTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.Comments" +
                   "(id INT NOT NULL AUTO_INCREMENT," +
                   "user_id INT NOT NULL," +
                   "recipe_id INT NOT NULL," +
                   "text varchar(255)," +
                   "PRIMARY KEY(id))";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the favorite table.
   * 
   * @param con the connection to MySQL.
   * @throws Exception
   */
  public void createFavoriteTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.Favorites" +
                   "(user_id INT NOT NULL," +
                   "recipe_id INT NOT NULL)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create monday food plan table.
   * 
   * @param con the connection to MySQL.
   */
  public void createMondayTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.MondayFood" +
                  "(id varchar(255) UNIQUE," +
                  "user_id INT NOT NULL," +
                  "week_id INT NOT NULL," +
                  "breakfast_recipe_id INT," +
                  "lunch_recipe_id INT," +
                  "dinner_recipe_id INT," +
                  "breakfast_amount INT," +
                  "lunch_amount INT," +
                  "dinner_amount INT)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create tuesday food plan table.
   * 
   * @param con the connection to MySQL.
   */
  public void createTuesdayTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.TuesdayFood" +
                   "(id varchar(255) UNIQUE," +
                   "user_id INT NOT NULL," +
                   "week_id INT NOT NULL," +
                   "breakfast_recipe_id INT," +
                   "lunch_recipe_id INT," +
                   "dinner_recipe_id INT," +
                   "breakfast_amount INT," +
                   "lunch_amount INT," +
                   "dinner_amount INT," +
                   "CONSTRAINT FOREIGN KEY (id) " + 
                   "REFERENCES cookbook.MondayFood(id) " + 
                   "ON DELETE CASCADE ON UPDATE NO ACTION)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create wednesday food plan table.
   * 
   * @param con the connection to MySQL.
   */
  public void createWednesdayTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.WednesdayFood" +
      "(id varchar(255) UNIQUE," +
      "user_id INT NOT NULL," +
      "week_id INT NOT NULL," +
      "breakfast_recipe_id INT," +
      "lunch_recipe_id INT," +
      "dinner_recipe_id INT," +
      "breakfast_amount INT," +
      "lunch_amount INT," +
      "dinner_amount INT," +
      "CONSTRAINT FOREIGN KEY (id) " + 
      "REFERENCES cookbook.MondayFood(id) " + 
      "ON DELETE CASCADE ON UPDATE NO ACTION)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create thursday food plan table.
   * 
   * @param con the connection to MySQL.
   */
  public void createThursdayTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.ThursdayFood" +
      "(id varchar(255) UNIQUE," +
      "user_id INT NOT NULL," +
      "week_id INT NOT NULL," +
      "breakfast_recipe_id INT," +
      "lunch_recipe_id INT," +
      "dinner_recipe_id INT," +
      "breakfast_amount INT," +
      "lunch_amount INT," +
      "dinner_amount INT," +
      "CONSTRAINT FOREIGN KEY (id) " + 
      "REFERENCES cookbook.MondayFood(id) " + 
      "ON DELETE CASCADE ON UPDATE NO ACTION)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create friday food plan table.
   * 
   * @param con the connection to MySQL.
   */
  public void createFridayTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.FridayFood" +
      "(id varchar(255) UNIQUE," +
      "user_id INT NOT NULL," +
      "week_id INT NOT NULL," +
      "breakfast_recipe_id INT," +
      "lunch_recipe_id INT," +
      "dinner_recipe_id INT," +
      "breakfast_amount INT," +
      "lunch_amount INT," +
      "dinner_amount INT," +
      "CONSTRAINT FOREIGN KEY (id) " + 
      "REFERENCES cookbook.MondayFood(id) " + 
      "ON DELETE CASCADE ON UPDATE NO ACTION)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * create saturday food plan table.
   * 
   * @param con the connection to MySQL.
   */
  public void createSaturdayTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.SaturdayFood" +
      "(id varchar(255) UNIQUE," +
      "user_id INT NOT NULL," +
      "week_id INT NOT NULL," +
      "breakfast_recipe_id INT," +
      "lunch_recipe_id INT," +
      "dinner_recipe_id INT," +
      "breakfast_amount INT," +
      "lunch_amount INT," +
      "dinner_amount INT," +
      "CONSTRAINT FOREIGN KEY (id) " + 
      "REFERENCES cookbook.MondayFood(id) " + 
      "ON DELETE CASCADE ON UPDATE NO ACTION)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create sunday food plan table.
   * 
   * @param con the connection to MySQL.
   */
  public void createSundayTable(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.SundayFood" +
      "(id varchar(255) UNIQUE," +
      "user_id INT NOT NULL," +
      "week_id INT NOT NULL," +
      "breakfast_recipe_id INT," +
      "lunch_recipe_id INT," +
      "dinner_recipe_id INT," +
      "breakfast_amount INT," +
      "lunch_amount INT," +
      "dinner_amount INT," +
      "CONSTRAINT FOREIGN KEY (id) " + 
      "REFERENCES cookbook.MondayFood(id) " + 
      "ON DELETE CASCADE ON UPDATE NO ACTION)";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void createShoppingCartTable(Connection con) {
    try {
    Statement stmt = con.createStatement();
    String sql = "CREATE TABLE cookbook.ShoppingCart" +
    "(id INT NOT NULL AUTO_INCREMENT, " +
    "user_id INT NOT NULL," +
    "week_id INT NOT NULL," +
    "PRIMARY KEY(id))";
    stmt.executeUpdate(sql);
    stmt.close();
    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  public void createShoppingCartQuantities(Connection con) {
    try {
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE cookbook.ShoppingCartQuantities" +
                   "(ingredient_id INT NOT NULL," +
                   "shoppingcart_id INT NOT NULL," +
                   "amount FLOAT NOT NULL," +
                   "UNIQUE(ingredient_id, shoppingcart_id))";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
