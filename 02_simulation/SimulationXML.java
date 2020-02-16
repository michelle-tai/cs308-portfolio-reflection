package xml;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple immutable value object representing simulation data
 *
 * This class is mainly used in the SimulationXMLFileChooser Class to store the tag data from the xml file
 *
 * This code is well designed because I limited the number of public methods and removed the constructor that took in around 7 parameters.
 * Although this class seems simple, I think it demonstrates readable code, especially after adding in the private setter methods. Originally, it
 * was a constructor with each data field value in each parameter's field, but after changing it, that constructor is now more readable and thus
 * easier to change or extend if needed.
 *
 * I also made the getInitialConfig method return a general, unmodifiable 2D list instead of an arraylist. Although it would be ideal to not return any data structure,
 * I really tried to hide as much as I could, so I made the list type as general as possible (its interface). Also, since I am passing a data structure around (only to
 * the Simulation.setData()), I don't want anyone to be able to modify this list. Hence, I made it unmodifiable so that there's not chance of someone clearing
 * the data structure.
 *
 * @author Michelle Tai
 */
public class SimulationXML {
  // name in data file that will indicate it represents data for this type of object
  public static final String DATA_TYPE = "Simulation";
  // field names expected to appear in data file holding values for this object
  // NOTE: simple way to create an immutable list
  public static final List<String> DATA_FIELDS = List.of(
      "title",
      "author",
      "width",
      "height",
      "random",
      "shape",
      "initialConfig"
  );

  // specific data values for this instance
  private String myTitle;
  private String myAuthor;
  private int myWidth;
  private int myHeight;
  private String myInitialConfig;
  private String isRandom;
  private String myShape;
  // NOTE: keep just as an example for converting toString(), otherwise not used
  private Map<String, String> myDataValues;

  /**
   * Create game data from data structure of Strings.
   *
   * @param dataValues map of field names to their values
   */
  public SimulationXML(Map<String, String> dataValues) {
    setTitle(dataValues.get(DATA_FIELDS.get(0)));
    setAuthor(dataValues.get(DATA_FIELDS.get(1)));
    setWidth(Integer.parseInt(dataValues.get(DATA_FIELDS.get(2))));
    setHeight(Integer.parseInt(dataValues.get(DATA_FIELDS.get(3))));
    setRandom(dataValues.get(DATA_FIELDS.get(4)));
    setShape(dataValues.get(DATA_FIELDS.get(5)));
    setInitialConfig(dataValues.get(DATA_FIELDS.get(6)));
    myDataValues = new HashMap<>();
  }

  /**
   * Returns title of this simulation.
   */
  public String getTitle () {
    return myTitle;
  }

  /**
   * Returns author of this file.
   */
  public String getAuthor() {
    return myAuthor;
  }

  /**
   * Returns the number of cells in a single row of the simulation grid.
   */
  public int getWidth() {
    return myWidth;
  }

  /**
   * Returns the number of cells in a single columns of the simulation grid.
   */
  public int getHeight() {
    return myHeight;
  }

  /**
   * If the string is exactly "false", false will be returned
   * else return true
   */
  public boolean isRandom(){
    return !isRandom.equals("false");
  }

  /**
   * Returns the myShape
   */
  public String getMyShape() {
    return myShape;
  }

  /**
   * Returns the initial configurations in the form of a 2D list
   */
  public List<List<Integer>> getInitialConfig() {
    String[] initialConfigArray = myInitialConfig.split("\n");
    List<List<Integer>> ret = new ArrayList<>();
    for(String rowStr : initialConfigArray){
      List<Integer> row = new ArrayList<>();
      String[] rowArr = rowStr.split(" ");
      for(int j = 0; j < rowArr.length; j++){
        row.add(Integer.parseInt(rowArr[j]));
      }
      ret.add(Collections.unmodifiableList(row));
    }
    return Collections.unmodifiableList(ret);
  }

  /**
   * @see Object#toString()
   */
  @Override
  public String toString () {
    StringBuilder result = new StringBuilder();
    result.append(DATA_TYPE + " = [\n");
    for (Map.Entry<String, String> e : myDataValues.entrySet()) {
      result.append("  ").append(e.getKey()).append(" = '").append(e.getValue()).append("',\n");
    }
    result.append("]\n");
    return result.toString();
  }

  private void setTitle(String title){
    myTitle = title;
  }

  private void setAuthor(String author){
    myAuthor = author;
  }

  private void setWidth(int width){
    myWidth = width;
  }

  private void setHeight(int height){
    myHeight = height;
  }

  private void setInitialConfig(String initialConfig){
    myInitialConfig = initialConfig;
  }

  private void setRandom(String random){
    isRandom = random;
  }

  private void setShape(String shape){
    myShape = shape;
  }
}
