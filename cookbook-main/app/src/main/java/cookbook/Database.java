package cookbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create database or create a database if it does not exist and return a connection.
 */
class database {

  private final String DB_URL;
  private final String USER;
  private final String PASSWORD;
  private final Table tb;
  private final Data dt;

  public database() {
    DB_URL = "jdbc:mysql://localhost:8889";
    USER = "root";
    PASSWORD = "root";
    tb = new Table();
    dt = new Data();
  }

  /**
   * Connect to MySQL and create database if it does not exist.
   * 
   * @return connection to the 
   * @throws SQLException
   */
  public Connection getConnect() {
    try {
      Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
      ResultSet res = con.getMetaData().getCatalogs();
      if (!dbExists(res, "cookbook")) {
        createDataBase(con);
      }
      con.setCatalog("cookbook");
      return con;
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Check if the database exists.
   *
   * @param con connection to MySQL
   * @param name name of the database to check for
   * @return true if it exists, otherwise false
   * @throws SQLException
   */
  private Boolean dbExists(ResultSet res, String name) throws SQLException{
    while(res.next()) {
      if(name.equals(res.getString(1))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Create the database cookbook.
   * 
   * @param con the connection to MySQL.
   */
  private void createDataBase(Connection con) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS cookbook");
      stmt.close();
      createTables(con);
      insertData(con);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create tables.
   * 
   * @param con connection to database.
   */
  private void createTables(Connection con) {
    tb.createUserTable(con);
    tb.createIngredientTable(con);
    tb.createRecipeTable(con);
    tb.createQuantityTable(con);
    tb.createMessageTable(con);
    tb.createCommentTable(con);
    tb.createFavoriteTable(con);
    tb.createMondayTable(con);
    tb.createTuesdayTable(con);
    tb.createWednesdayTable(con);
    tb.createThursdayTable(con);
    tb.createFridayTable(con);
    tb.createSaturdayTable(con);
    tb.createSundayTable(con);
    tb.createShoppingCartTable(con);
    tb.createShoppingCartQuantities(con);
  }

  /**
   * Insert data into tables.
   * 
   * @param con connection to database.
   */
  private void insertData(Connection con) {
    dt.insertUserData(con);
    dt.insertIngredientData(con);
    dt.insertRecipeData(con);
    dt.insertQuantityData(con);
    dt.insertMessageData(con);
    dt.insertCommentData(con);
    dt.insertFavoriteData(con);
  }
}
