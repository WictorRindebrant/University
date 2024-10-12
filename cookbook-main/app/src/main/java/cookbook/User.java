package cookbook;

import java.sql.SQLException;
import java.util.ArrayList;

public class User {

  private int id;
  private String username;
  private String displayName;
  private String password;
  private boolean admin;
  private ArrayList<String> favRecipes = new ArrayList<String>();

  public User() {

  }

  /**
   * Gets the all of the user infromation from the database when constructing.
   * 
   * @param newUsername Unique username.
   * @param newPassword the users password.
   */

  public User(String newUsername, String newPassword) {
    // create the query object
    Query q = new Query();
    String[] arrUser = null;
    try {
      arrUser = q.userLogin(newUsername, newPassword);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (arrUser[1] != null) {
      id = Integer.parseInt(arrUser[0]);
      username = arrUser[1];
      password = arrUser[2];
      displayName = arrUser[3];
      if (arrUser[4].equals("1")) {
        admin = true;
      } else {
        admin = false;
      }
    }
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getPassword() {
    return password;
  }

  public boolean getAdminVal() {
    return admin;
  }

  public void addRecipe(String recName) {
    favRecipes.add(recName);
  }

  public void removeRecipe(String recName) {
    favRecipes.remove(recName);
  }

  public boolean isFavRecipe(String recName) {
    if (favRecipes.contains(recName)) {
      return true;
    } else {
      return false;
    }
  }

}
