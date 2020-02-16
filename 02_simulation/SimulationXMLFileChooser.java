package xml;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * This class opens up a file chooser to load in XML configuration files for the simulations
 * This class depends on JavaFx stages
 *
 * To use this class, you would instaniate it and then call it's openFile method to open the file chooser dialog
 * and choose the xml file.
 *
 * I think this is an example of well designed code as well because (Dr. Duvall wrote most of it) it throws exceptions
 * if something is not right about the file, whether it doenst match or is missing a data field or something else. It throws a
 * SimulationException, which is our own custom Exception class, and an alert box appears. I've also refactored the code so that
 * the method names make more sense (getGame to getSim), which helps anyone else trying to edit this code understand what that function is
 * doing and make the code itself more readable. I mainly added this class to the masterpiece because the
 *
 * Pair<String, SimulationXML> p = new Pair<>(dataFile.getName(), new XMLParser("media").getSim(dataFile));
 * simXML = p.getSecond();
 *
 * lines kinda demonstrates the how the constructor of the simulationXML works
 *
 * @author Michelle Tai
 * @author Robert Duvall
 */
public class SimulationXMLFileChooser {
  // kind of data files to look for
  public static final String DATA_FILE_EXTENSION = "*.xml";
  // NOTE: generally accepted behavior that the chooser remembers where user left it last
  public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
  private SimulationXML simXML;

  /**
   * Opens the file chooser and saves the parsed XML information into a SimulationXML object
   * @param primaryStage is the JavaFX Stage in which the filechooser will pop up
   * @throws Exception
   */
  public void openFile (Stage primaryStage) throws SimulationException {
    File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
    while (dataFile != null) {
      try {
        Pair<String, SimulationXML> p = new Pair<>(dataFile.getName(), new XMLParser("media").getSim(dataFile));
        // do something "interesting" with the resulting data
        simXML = p.getSecond();
        break;
      }
      catch (SimulationException e) {
        // handle error of unexpected file format
        showMessage(AlertType.ERROR, e.getMessage());
      }
    }
  }

  /**
   * @return the information parsed from the XML file in the form of a SimulationXML object
   */
  public SimulationXML getSimulationXMLInfo(){
    return simXML;
  }

  // display given message to user using the given type of Alert dialog box
  private void showMessage (AlertType type, String message) {
    //new Alert(type, message).showAndWait();
    new Alert(type, message).show();
  }

  // set some sensible defaults when the FileChooser is created
  private static FileChooser makeChooser (String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Open Data File");
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(System.getProperty("user.dir")));
    result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
    return result;
  }
}