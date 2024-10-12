package cookbook;

import java.sql.SQLException;

public class Recipe {

  private int id;
  private String name;
  private String instructions;
  private String type;

  public Recipe() {

  }

  /**
   * Get the all the recipies information from the database when constructing.
   * 
   * @param recName the wanted recipes' name.
   */

  public Recipe(String recName) {
    // create the query object
    Query q = new Query();

    String[] arrRecipe = null;
    try {
      arrRecipe = q.getRecipe(recName);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    id = Integer.parseInt(arrRecipe[0]);
    name = arrRecipe[1];
    instructions = arrRecipe[2];
    type = arrRecipe[3];
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getInstructions() {
    return instructions;
  }

  public String getType() {
    return type;
  }
}
