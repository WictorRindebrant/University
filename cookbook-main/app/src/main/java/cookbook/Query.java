package cookbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import com.google.common.primitives.Ints;

/**
 * Query will contain most queries that will be used for transactions to the
 * database.
 */
class Query {

  private final Connection con;

  public Query() {
    database db = new database();
    this.con = db.getConnect();
  }

  /**
   * Get user information.
   * 
   * @param username user name.
   * @param password user password.
   * @return String array with user information, otherwise a null pointer.
   * @throws SQLException
   */
  public String[] userLogin(String username, String password) throws SQLException {
    String query = String.format("SELECT * " +
                                 "FROM users " +
                                 "WHERE username = '%s' AND password = '%s'", 
                                 username, password);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    String[] arr = new String[5];
    if (res.next()) {
      arr[0] = res.getString("id");
      arr[1] = res.getString("username");
      arr[2] = res.getString("password");
      arr[3] = res.getString("displayname");
      arr[4] = res.getString("admin");
    }
    pstmt.close();
    return arr;
  }

  /**
   * Get all users information.
   * 
   * @return list of string array with user data [id,username,displayname,admin]
   * @throws SQLException
   */
  public ArrayList<String[]> allUserData() throws SQLException {
    String query = String.format("SELECT * " +
                                 "FROM users");
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arrList = toArray(res);
    pstmt.close();
    return arrList;
  }

  /**
   * Get user information.
   * 
   * @param userID user id.
   * @return all user information in string array.
   * @throws SQLException
   */
  public String[] getUser(int userID) throws SQLException {
    String query = String.format("SELECT * " +
                                 "FROM users " +
                                 "WHERE id = '%s'",
                                 userID);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    if (res.next()) {
      String[] arr = {res.getString("id"),
                      res.getString("username"),
                      res.getString("password"),
                      res.getString("displayname"),
                      res.getString("admin")};
      pstmt.close();
      return arr;
    }
    pstmt.close();
    return null;
  }

  /**
   * Get all users usernames.
   * 
   * @return string array with user names [username]
   * @throws SQLException
   */
  public String[] allUserNames() throws SQLException {
    String query = String.format("SELECT username " +
                                 "FROM users");
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String> arrList = new ArrayList<>();
    while (res.next()) {
      arrList.add(res.getString(1));
    }
    String[] arr = arrList.toArray(new String[0]);
    pstmt.close();
    return arr;
  }

  /**
   * Get recept information.
   * 
   * @param name recipe name.
   * @return String array with recipe information, otherwise a null pointer.
   * @throws SQLException
   */
  public String[] getRecipe(String name) throws SQLException {
    String query = String.format("SELECT * " +
                                 "FROM recipes " +
                                 "WHERE name = '%s'", 
                                 name);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    String[] arr = new String[4];
    if (res.next()) {
      arr[0] = res.getString("id");
      arr[1] = res.getString("name");
      arr[2] = res.getString("instruction");
      arr[3] = res.getString("type");
    }
    pstmt.close();
    return arr;
  }

  /**
   * Get recept information.
   * 
   * @param id recipe id.
   * @return String array with recipe information, otherwise a null pointer.
   * @throws SQLException
   */
  public String[] getRecipe(int id) throws SQLException {
    String query = String.format("SELECT * " +
                                 "FROM recipes " +
                                 "WHERE id = '%d'", 
                                 id);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    String[] arr = new String[4];
    if (res.next()) {
      arr[0] = res.getString("id");
      arr[1] = res.getString("name");
      arr[2] = res.getString("instruction");
      arr[3] = res.getString("type");
    }
    pstmt.close();
    return arr;
  }

  /**
   * Get recipes in relation to set tag.
   * 
   * @param tag the tag the recipe belongs to.
   * @return the recipes belonging to the tag in a list of string arrays.
   * @throws SQLException
   */
  public ArrayList<String> getTags() throws SQLException {
    String query = String.format("SELECT DISTINCT type " + 
                                 "FROM recipes ");
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String> temp = new ArrayList<>();
    ArrayList<String> rec = new ArrayList<>();

    while (res.next()) {
      temp.add(res.getString("type"));
    }

    for(String tag : temp) {
      for (String i : tag.split(":")){
        if (!rec.contains(i))
        rec.add(i);
        }
    }
    

    pstmt.close();
    return rec;
  }

  public void addTag(String name, String newtag) throws SQLException {
    String query = String.format("UPDATE recipes " + 
                                 "SET type = '%s' " + 
                                 "WHERE name = '%s'", newtag, name);
    Statement stmt = con.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
  }

  /**
   * 
   * @param name name of the recipe 
   * @return return the tag of the recipe
   * @throws SQLException
   */

  public String getTagFromRecipe(String name) throws SQLException {
    String query = String.format("SELECT type " + 
                                 "FROM recipes " + 
                                 "WHERE name = '%s'", name);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    if(res.next()) {
      return res.getString(1);
    }
    stmt.close();
    return "error";
  }

  /**
   * Get recipes in relation to set tag.
   * 
   * @param tag the tag the recipe belongs to.
   * @return the recipes belonging to the tag in a list of string arrays.
   * @throws SQLException
   */
  public ArrayList<String> getRecipeByTag(String tag) throws SQLException {
    String temp = "'%" + tag + "%'";
    String query = String.format("SELECT name " + 
                                 "FROM `recipes` " + 
                                 "WHERE type LIKE %s " +
                                 "ORDER BY name", temp);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String> rec = new ArrayList<>();

    while (res.next()) {
      rec.add(res.getString("name"));
    }

    pstmt.close();
    return rec;
  }

  /**
   * Get the recipe name and type.
   * 
   * @return ArrayList with string[] with name and type [name, type].
   * @throws SQLException
   */
  public ArrayList<String[]> getAllRecipes() throws SQLException {
    String query = String.format("SELECT recipes.name, recipes.type " + 
                                 "FROM recipes " +
                                 "ORDER BY name");
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arrList = toArray(res);
    pstmt.close();
    return arrList;
  }

  /**
   * 
   * @param name
   * @param instruction
   * @param Type
   * @param Description
   * @throws SQLException
   */
  public void addRecipe(String name, String instruction, String Type, String Description) throws SQLException{
    String query = String.format("INSERT INTO recipes " + 
                                 "(name, instruction, Type, hovertext) " +
                                 "VALUES(?, ?, ?, ?)");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, name);
    pstmt.setString(2, '"' + instruction + '"');
    pstmt.setString(3, Type);
    pstmt.setString(4, '"' + Description + '"');
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Get the recipe names.
   * 
   * @return a String[] with recipe names.
   * @throws SQLException
   */
  public String[] getAllRecipesNames() throws SQLException {
    String query = String.format("SELECT recipes.name " + 
                                 "FROM recipes");
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String> arr = new ArrayList<>();
    while(res.next()) {
      arr.add(res.getString(1));
    }
    return arr.toArray(new String[0]);
  }

  
  /**
   * Get recipe id.
   * 
   * @param name of the recipe.
   * @return the recipe id, otherwise -1.
   * @throws SQLException
   */
  public int getRecipeID(String name) throws SQLException {
    String query = String.format("SELECT recipes.id " +
                                 "FROM recipes " +
                                 "WHERE name = '%s'", name);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    if(res.next()) {
      return res.getInt(1);
    }
    stmt.close();
    return -1;
  }

  /**
   * Get recipe id.
   * 
   * @param name of the recipe.
   * @return the recipe id, otherwise -1.
   * @throws SQLException
   */
  public String getRecipeDescription(String name) throws SQLException {
    String query = String.format("SELECT recipes.hovertext " +
                                 "FROM recipes " +
                                 "WHERE name = '%s'", name);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    if(res.next()) {
      return res.getString(1);
    }
    stmt.close();
    return null;
  }

  /**
   * Get all recipes from ingredient.
   * 
   * @param name name of the ingredient.
   * @return an ArrayList with with each ingredient data. [name]
   * @throws SQLException
   */
  public ArrayList<String> getRecipeByIngredient(String name) throws SQLException {
    String query = String.format("SELECT recipes.name " +
                                 "FROM (recipes " +
                                 "INNER JOIN quantities " +
                                 "ON (recipes.id = quantities.recipe_id))" +
                                 "INNER JOIN ingredients " + 
                                 "ON (ingredients.id = quantities.ingredient_id) " +
                                 "WHERE ingredients.name = '%s'" +
                                 "ORDER BY recipes.name", name);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String> rec = new ArrayList<>();

    while (res.next()) {
      rec.add(res.getString("name"));
    }

    pstmt.close();
    return rec;
  }

  /**
   * Get week day meal plan.
   * 
   * @param day the day fo the week
   * @param user_id the user id number
   * @param week_id the week number
   * @return a int[] with the id and amount values
   * @throws SQLException
   */
  public int[] getWeekDay(String day, int user_id, int week_id) throws SQLException {
    String query = String.format("SELECT * " +
                                 "FROM %s " +
                                 "WHERE user_id = %d AND week_id = %d",
                                 day, user_id, week_id);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    ArrayList<Integer> arr = new ArrayList<>(); 
    if(res.next()) {
      arr.add(res.getInt(2));
      arr.add(res.getInt(3));
      arr.add(res.getInt(4));
      arr.add(res.getInt(5));
      arr.add(res.getInt(6));
      arr.add(res.getInt(7));
      arr.add(res.getInt(8));
      arr.add(res.getInt(9));
    } else {
      stmt.close();
      return new int[8];
    }
    stmt.close();
    return Ints.toArray(arr);
  }

  /**
   * Set given days meal plan.
   * 
   * @param day the name of the table
   * @param user_id the users id
   * @param week_id the week number
   * @param breakfast_recipe_id breakfast recipe id
   * @param lunch_recipe_id lunch recipe id
   * @param dinner_recipe_id dinner recipe id
   * @param breakfast_amount breakfast amount
   * @param lunch_amount lunch amount
   * @param dinner_amount dinner amount
   * @throws SQLException
   */
  public void setWeekDay(String day, int user_id, int week_id,
                             int breakfast_recipe_id, int lunch_recipe_id, int dinner_recipe_id, 
                             int breakfast_amount, int lunch_amount, int dinner_amount) throws SQLException{

    String query = String.format("INSERT INTO %1$s " +
                                 "(id, user_id, week_id, breakfast_recipe_id, lunch_recipe_id, dinner_recipe_id, breakfast_amount, lunch_amount, dinner_amount) " +  
                                 "VALUES(CONCAT(?,'_',?),?, ?, ?, ?, ?, ?, ?, ?) " +
                                 "ON DUPLICATE KEY UPDATE breakfast_recipe_id = ?, lunch_recipe_id = ?, dinner_recipe_id = ?, " +
                                 "breakfast_amount = ?, lunch_amount = ?, dinner_amount = ?", day);
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, user_id);
    pstmt.setInt(2, week_id);
    pstmt.setInt(3, user_id);
    pstmt.setInt(4, week_id);
    pstmt.setInt(5, breakfast_recipe_id);
    pstmt.setInt(6, lunch_recipe_id);
    pstmt.setInt(7, dinner_recipe_id);
    pstmt.setInt(8, breakfast_amount);
    pstmt.setInt(9, lunch_amount);
    pstmt.setInt(10, dinner_amount);
    pstmt.setInt(11, breakfast_recipe_id);
    pstmt.setInt(12, lunch_recipe_id);
    pstmt.setInt(13, dinner_recipe_id);
    pstmt.setInt(14, breakfast_amount);
    pstmt.setInt(15, lunch_amount);
    pstmt.setInt(16, dinner_amount);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Delete all the weeks content
   * 
   * @param user_id
   * @param week_id
   * @throws SQLException
   */
  public void delWeek(int user_id, int week_id) throws SQLException {
    String query = String.format("DELETE " +
                                 "FROM mondayfood " +
                                 "WHERE id = CONCAT(%d,'_',%d)",
                                 user_id, week_id);
    Statement stmt = con.createStatement();
    stmt.execute(query);
    stmt.close();
  }

  /**
   * Get a list of the week id's
   * 
   * @param user_id the user id
   * @return a list with week id's
   * @throws SQLException
   */
  public int[] getWeekNumbers(int user_id) throws SQLException {
    String query = String.format("SELECT week_id " +
                                 "FROM mondayfood " +
                                 "WHERE user_id = %d",
                                 user_id);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    ArrayList<Integer> weeks = new ArrayList<>();
    while (res.next()) {
      weeks.add(res.getInt(1));
    }
    stmt.close();
    return Ints.toArray(weeks);
  }  

  /**
   * Get the user id.
   * 
   * @param name users username.
   * @return the user id, otherwise -1.
   * @throws SQLException
   */
  public int getUserID(String name) throws SQLException {
    String query = String.format("SELECT users.id " +
                                 "FROM users " +
                                 "WHERE username = '%s'", name);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    if(res.next()) {
      return res.getInt(1);
    }
    stmt.close();
    return -1;
  }

  /**
   * Get the user id.
   * 
   * @param name users username.
   * @return the user id, otherwise -1.
   * @throws SQLException
   */
  public int getID(String name) throws SQLException {
    String query = String.format("SELECT users.id " +
                                 "FROM users " +
                                 "WHERE displayname = '%s'", name);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    if(res.next()) {
      return res.getInt(1);
    }
    stmt.close();
    return -1;
  }

  /**
   * Get all ingredients for a recipe.
   * 
   * @param name name of the recipe.
   * @return an ArrayList with string arrays with each ingredient data. [name, amount, measure]
   * @throws SQLException
   */
  public ArrayList<String[]> getIngredients(String name) throws SQLException {
    String query = String.format("SELECT ingredients.name, quantities.amount, ingredients.measure " +
                                 "FROM (recipes " +
                                 "INNER JOIN quantities " +
                                 "ON (recipes.id = quantities.recipe_id))" +
                                 "INNER JOIN ingredients " + 
                                 "ON (ingredients.id = quantities.ingredient_id) " +
                                 "WHERE recipes.name = '%s'" +
                                 "ORDER BY recipes.name", name);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arr = toArray(res);
    pstmt.close();
    return arr;
  }

  /**
   * Get all ingredients for a recipe.
   * 
   * @param name name of the recipe.
   * @return an ArrayList with string arrays with each ingredient data. [name, amount, measure]
   * @throws SQLException
   */
  public ArrayList<String[]> getIngredients(int id) throws SQLException {
    String query = String.format("SELECT ingredients.name, quantities.amount, ingredients.measure " +
                                 "FROM (recipes " +
                                 "INNER JOIN quantities " +
                                 "ON (recipes.id = quantities.recipe_id))" +
                                 "INNER JOIN ingredients " + 
                                 "ON (ingredients.id = quantities.ingredient_id) " +
                                 "WHERE recipes.id = '%s'" +
                                 "ORDER BY recipes.id",String.valueOf(id));
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arr = toArray(res);
    pstmt.close();
    return arr;
  }

  
  public ArrayList<String[]> getAllIngredients() throws SQLException {
    String query = String.format("SELECT name, measure " +
                                 "FROM ingredients " + 
                                 "ORDER BY name");
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arr = toArray(res);
    pstmt.close();
    return arr;
  }
  
  public int getIngredientID(String ingredientName, String measure) throws SQLException {
    String query = String.format("SELECT id " +
                                 "FROM ingredients " +
                                 "WHERE name = '%s' AND measure = '%s'",
                                  ingredientName, measure);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    if(res.next()) {
      return res.getInt(1);
    }
    pstmt.close();
    return -1;
    }

    /**
     * 
     * @param ingredient_id
     * @param recipe_id
     * @param amount
     * @throws SQLException
     */
    public void addQuantity(int ingredient_id, int recipe_id, Double amount) throws SQLException {
      String query = String.format("INSERT INTO quantities " +
                                   "(ingredient_id, recipe_id, amount) " +
                                   "VALUES(?, ?, ?)");
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setInt(1, ingredient_id);
      pstmt.setInt(2, recipe_id);
      pstmt.setDouble(3, amount);
      pstmt.execute();
      pstmt.close();
    }


    // WIP
    public void editquantity(int ingredient_id, int recipe_id, Double newAmount, int newIngredient_id) throws SQLException {
      String query = String.format("UPDATE quantities " +
                                   "SET ingredient_id = '%d', amount = '%.1f' " +
                                   "WHERE ingredient_id = '%d' AND recipe_id = '%d'",
                                   newIngredient_id, newAmount, ingredient_id, recipe_id);
      Statement stmt = con.createStatement();
      stmt.executeUpdate(query);
      stmt.close();
    }

/**
   * Get all information about messages, and the name of the person who sent it.
   * 
   * @return all message data [recipe_id, sender_id, receiver_id, text, sender displayname]
   * @throws SQLException
   */
  public ArrayList<String[]> getReceiverData(int receiverUserID) throws SQLException {
    String query = String.format("SELECT messages.id, recipe_id, text, users.displayname, sender_id " +
                                 "FROM messages " +
                                 "JOIN users " +
                                 "ON (messages.sender_id = users.id) " +
                                 "WHERE receiver_id = '%s'", receiverUserID);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arr = toArray(res);
    pstmt.close();
    return arr;
  }

  /**
   * Get all information about messages, and the name of the person who sent it.
   * 
   * @return all message data [recipe_id, sender_id, receiver_id, text, sender displayname]
   * @throws SQLException
   */
  public ArrayList<String[]> getSenderData(int senderUserID) throws SQLException {
    String query = String.format("SELECT messages.id, recipe_id, text, users.displayname, sender_id " +
                                 "FROM messages " +
                                 "JOIN users " +
                                 "ON (messages.receiver_id = users.id) " +
                                 "WHERE sender_id = '%s'", senderUserID);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arr = toArray(res);
    pstmt.close();
    return arr;
  }

 /**
  * will be used to add messages.
  *
  * @param recipe_id the id of the recipe.
  * @param sender_id the id of the user sending the message.
  * @param receiver_id the id of the user receiving the message.
  * @param text the message you want to add.
  * @throws SQLException
  */
  public void addMessage(int recipe_id, int sender_id, int receiver_id, String text) throws SQLException{
    String query = String.format("INSERT INTO messages " + 
                                 "(recipe_id, sender_id, receiver_id, text) " +
                                 "VALUES(?, ?, ?, ?)");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, recipe_id);
    pstmt.setInt(2, sender_id);
    pstmt.setInt(3, receiver_id);
    pstmt.setString(4, text);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * will be used to remove messages.
   * 
   * @param id message id.
   * @throws SQLException
   */
  public void deleteMessage(int id) throws SQLException{
    String query = String.format("DELETE " +
                                 "FROM messages " +
                                 "WHERE id = ?");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, id);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * will be used to edit messages.
   * 
   * @param recipe_id the id of the recipe.
   * @param sender_id the id of the sending user whos message is about to be edited.
   * @param receiver_id the id of the receiving user.
   * @param newText the new message you want to replace the old one with.
   * @throws SQLException
   */
  public void editMessage(int recipe_id, int sender_id, int receiver_id, String newText) throws SQLException {
    String query = String.format("UPDATE messages " +
                                 "SET text = '%s' " +
                                 "WHERE recipe_id = %d AND sender_id = %d AND receiver_id = %d",
                                 newText, recipe_id, sender_id, receiver_id);
    Statement stmt = con.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
  }

  /**
   * 
   * @param recipe_id
   * @param sender_id
   * @param receiver_id
   * @return
   * @throws SQLException
   */
  public int getMessageID(int recipe_id, int sender_id, int receiver_id) throws SQLException {
    String query = String.format("SELECT id " +
                                 "FROM messages " +
                                 "WHERE recipe_id = %d AND sender_id = %d AND receiver_id = %d",
                                 recipe_id, sender_id, receiver_id);
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(query);
    if(res.next()) {
      return res.getInt(1);
    }
    stmt.close();
    return -1;
  }

  /**
   * Get all comments information.
   * 
   * @return all comments data [id, user_id, recipe_id, text]
   * @throws SQLException
   */
  public ArrayList<String[]> getComments() throws SQLException {
    String query = String.format("SELECT * " +
                                 "FROM comments ");
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arr = toArray(res);
    pstmt.close();
    return arr;
  }

  /**
   * Get the comments a user done on a recipe.
   * 
   * @param userID the users id.
   * @param recipeID the commented recipe id.
   * @return the comment(s) id.
   * @throws SQLException
   */
  public int[] getCommentID(int userID, int recipeID) throws SQLException {
    String query = String.format("SELECT id " +
                                 "FROM comments " +
                                 "WHERE user_id = ? AND recipe_id = ?");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, userID);
    pstmt.setInt(2, recipeID);
    ResultSet res = pstmt.executeQuery();
    ArrayList<Integer> arr = new ArrayList<>();
    while(res.next()) {
      arr.add(res.getInt(1));
    }
    pstmt.close();
    return Ints.toArray(arr);
  }

  /**
   * Get comment id.
   * 
   * @param userID the user id.
   * @param recipeID the commented recipe id.
   * @param text the content of the comment.
   * @return the comment id and -1 if the comment is not found.
   * @throws SQLException
   */
  public int getCommentID(int userID, int recipeID, String text) throws SQLException {
    String query = String.format("SELECT id " +
                                 "FROM comments " +
                                 "WHERE user_id = ? AND recipe_id = ? AND text = ?");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, userID);
    pstmt.setInt(2, recipeID);
    pstmt.setString(3, text);
    ResultSet res = pstmt.executeQuery();
    int i = -1;
    if (res.next()) {
      i = res.getInt(1);
    }
    pstmt.close();
    return i;
  }
  
  /**
   * Add comment to database.
   * 
   * @param userID the comment author.
   * @param recipeID the commented recipe.
   * @param text comment content.
   * @throws SQLException
   */
  public void addComment(int userID, int recipeID, String text) throws SQLException{
    String query = String.format("INSERT INTO comments " + 
                                 "(user_id, recipe_id, text) " +
                                 "VALUES(?, ?, ?)");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, userID);
    pstmt.setInt(2, recipeID);
    pstmt.setString(3, text);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Remove comment.
   * 
   * @param id the comment id.
   * @throws SQLException
   */
  public void removeComment(int id) throws SQLException {
    String query = String.format("DELETE " + 
                                 "FROM comments " +
                                 "WHERE id = %d", id);
    Statement stmt = con.createStatement();
    stmt.execute(query);
    stmt.close();
  }

  /**
   * Edit comment text.
   * 
   * @param id the comment id.
   * @param newText the new content of the comment.
   * @throws SQLException
   */
  public void editComment(int id, String newText) throws SQLException {
    String query = String.format("UPDATE comments " +
                                 "SET text = '%s' " +
                                 "WHERE id = %d",
                                 newText, id);
    Statement stmt = con.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
  }

  /**
   * Get all favorites for the given user.
   * 
   * @param name the users username
   * @return a list of a user's favorite recipes [name, text]
   * @throws SQLException
   */
  public String[] getFavorites(String name) throws SQLException {
    String query = String.format("SELECT recipes.name " +
                                 "FROM (users " +
                                 "INNER JOIN favorites " +
                                 "ON (favorites.user_id = users.id)) " +
                                 "INNER JOIN recipes " +
                                 "ON (favorites.recipe_id = recipes.id) " +
                                 "WHERE users.username = '%s' " +
                                 "ORDER BY recipes.name", name);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String> arr = new ArrayList<>();
    while ( res.next()) {
      arr.add(res.getString(1));
    }
    pstmt.close();
    return arr.toArray(new String[0]);
  }

  /**
    * Check if the recipe is favorit for the given user.
    * 
    * @param userID user id.
    * @param recipeID recipe id.
    * @return true if it's favorit, otherwise false.
    * @throws SQLException
    */
  public Boolean isFavorites(int userID, int recipeID) throws SQLException {
    String query = String.format("SELECT * " +
                                  "FROM favorites " +
                                  "WHERE user_id = ? AND recipe_id = ?");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, userID);
    pstmt.setInt(2, recipeID);
    ResultSet res = pstmt.executeQuery();
    if (res.next()) {
      pstmt.execute();
      return true;
    }
    pstmt.execute();
    return false;
  }

  /**
   * Add a favorite to a user.
   * 
   * @param username user username.
   * @param recipe recipe name.
   * @param text users thought of favorites.
   * @throws SQLException
   */
  public void addFavorite(String username, String recipe) throws SQLException {
    String query = String.format("INSERT IGNORE INTO favorites" +
                          "(user_id, recipe_id) " +
                          "VALUES(?, ?)");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, getUserID(username));
    pstmt.setInt(2, getRecipeID(recipe));
    pstmt.execute();
    pstmt.close();                    
  }

  /**
   * Add a favorite to a user.
   * 
   * @param userID user username.
   * @param recipeID recipe name.
   * @param text uers thought of favorites.
   * @throws SQLException
   */
  public void addFavorite(int userID, int recipeID) throws SQLException {
    String query = String.format("INSERT IGNORE INTO favorites" +
                                 "(user_id, recipe_id) " +
                                 "VALUES(?, ?)");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, userID);
    pstmt.setInt(2, recipeID);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Delete a favorite recipe of the user.
   * 
   * @param username users username
   * @param recipe favorite recipe name
   * @throws SQLException
   */
  public void delFavorite(String username, String recipe) throws SQLException {
    String query = String.format("DELETE FROM favorites " +
                                 "WHERE user_id = ? AND recipe_id = ?");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, getUserID(username));
    pstmt.setInt(2, getRecipeID(recipe));
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Delete a favorite recipe of the user.
   * 
   * @param userID users id.
   * @param recipeID favorite recipe id.
   * @throws SQLException
   */
  public void delFavorite(int userID, int recipeID) throws SQLException {
    String query = String.format("DELETE FROM favorites " +
                                 "WHERE user_id = ? AND recipe_id = ?");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, userID);
    pstmt.setInt(2, recipeID);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Add a user to the users table.
   * 
   * @param username users username.
   * @param password users password.
   * @param displayname users displayname.
   * @param admin 1 if user is admin, otherwise 0.
   * @throws SQLException
   */
  public void addUser(String username, String password, String displayname, String admin) throws SQLException {
    String query = String.format("INSERT IGNORE INTO users" +
                                 "(username, password, displayname, admin) " +
                                 "VALUES('%s', '%s', '%s', '%s')",
                                 username, password, displayname, admin);
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Delete a user from the users table.
   * 
   * @param username users username.
   * @throws SQLException
   */
  public void delUser(String username) throws SQLException {
    String query = String.format("DELETE FROM users " +
                                 "WHERE users.username = '%s'",
                                 username);
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Edit user in the users table.
   * 
   * @param username user username.
   * @param edit what column to edit.
   * @param newValue the new value to insert.
   * @throws SQLException
   */
  public void editUser(String username, String edit, String newValue) throws SQLException {
    String query = String.format("UPDATE users " +
                                 "SET %s = '%s' " +
                                 "WHERE username = '%s'",
                                 edit, newValue, username);
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Private method to convert result set to arraylist.
   * 
   * @param res the ResultSet from query.
   * @return the ArrayList<String[]>
   * @throws SQLException
   */
  private ArrayList<String[]> toArray(ResultSet res) throws SQLException {
    ResultSetMetaData metaRes = res.getMetaData();
    int cc = metaRes.getColumnCount();
    ArrayList<String> arrHolder = new ArrayList<>();
    ArrayList<String[]> arrList = new ArrayList<>();
    while (res.next()) {
      for (int i = 0; i < cc; i++) {
        arrHolder.add(res.getString(i + 1));
      }
      arrList.add(arrHolder.toArray(new String[0]));
      arrHolder.clear();
    }
    return arrList;
  }

  /**
   * Edit comment text.
   * 
   * @param id the comment id.
   * @param newText the new content of the comment.
   * @throws SQLException
   */
  public void editInstructions(int id, String newText) throws SQLException {
    String query = String.format("UPDATE recipes " +
                                 "SET instruction = '%s' " +
                                 "WHERE id = %d",
                                 newText, id);
    Statement stmt = con.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
  }

  public void saveShoppingCart(int userID, int weekID) throws SQLException {
    String query1 = String.format("INSERT IGNORE INTO shoppingcart " + 
                                 "(user_id, week_id) " +
                                 "VALUES('%d', '%d')", userID, weekID);
    PreparedStatement pstmt = con.prepareStatement(query1);
    pstmt.executeUpdate(query1);

    pstmt.close();
  }

  public void addShoppingCartIngredients(int ingID, int shoppingCartID, float ingAmount ) throws SQLException {
    String query = String.format("INSERT IGNORE INTO shoppingcartquantities " + 
                                 "(ingredient_id, shoppingcart_id, amount) " +
                                 "VALUES('%d', '%d', '%f')", ingID, shoppingCartID, ingAmount);
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.executeUpdate(query);

    pstmt.close();
  }


  public void delShoppingCart(int userID, int weekID) throws SQLException {
    String query = String.format("DELETE FROM shoppingcart " + 
                                 "WHERE user_id = '%d' AND week_id = '%d'", userID, weekID);
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.executeUpdate(query);
    pstmt.close();
  }

  public void delShoppingCartIngredients(int shpID) throws SQLException {
    String query = String.format("DELETE FROM shoppingcartquantities " + 
                                 "WHERE shoppingcart_id = '%d'", shpID);
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.executeUpdate(query);
    pstmt.close();
  }

  public int[] getAllShoppingCarts(int userID) throws SQLException {
    String query = String.format("SELECT week_id " + 
    "FROM shoppingcart " +
    "WHERE user_id = %d", userID);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<Integer> shpLst = new ArrayList<>();
    while (res.next()) {
      shpLst.add(res.getInt(1));
    }
    pstmt.close();
    return Ints.toArray(shpLst);
  }

 

  public ArrayList<String[]> getShoppingCartIngredients(int shoppingCartID) throws SQLException {
    String query = String.format("SELECT ingredients.name, shoppingcartquantities.amount, ingredients.measure " +
                                 "FROM (shoppingcart " +
                                 "INNER JOIN shoppingcartquantities " +
                                 "ON (shoppingcart.id = shoppingcartquantities.shoppingcart_id))" +
                                 "INNER JOIN ingredients " + 
                                 "ON (ingredients.id = shoppingcartquantities.ingredient_id) " +
                                 "WHERE shoppingcart.id = '%d'", shoppingCartID);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery();
    ArrayList<String[]> arr = toArray(res);
    pstmt.close();
    return arr;
  }
  
  public int getShoppingCartID(int userID, int weekID) throws SQLException {
    String query = String.format("SELECT id FROM shoppingcart " + 
                                 "WHERE user_id = '%d' AND week_id = '%d'", userID, weekID);
    PreparedStatement pstmt = con.prepareStatement(query);
    ResultSet res = pstmt.executeQuery(query);

    if(res.next()) {
      return Integer.parseInt(res.getString(1));
    }
    pstmt.close();
    return -1;
  }

}