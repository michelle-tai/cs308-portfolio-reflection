package slogo.View;

import javafx.scene.Node;

/**
 * This code's purpose is to create a type of "contract" so that every history panel that extends this must return a type of Node, whether it be a VBox or a
 * ListView, so that it can be added to the HistoryPanel area in SLogo. This is my attempt to create a type of hierarchy and to do something with the duplicate
 * code that I saw in all the history panels–they all had a returnScene method. By creating this interface and having each history view implement this interface,
 * it can be ensured that every history panel WILL return an Node that can be appended to a scene/stage. I hope that this makes our code more flexible, and I chose an
 * interface instead of an abstract class since classes can implement multiple interfaces, but only one abstract class. Also, by defining what is to be returned as a
 * Node, we don't need to specify the implementation of it the method before/don't need to override anything, since all things in a scene are Nodes. For example, if a VBox is returned,
 * it's still a Node, so we don't have to worry about the specifications in other parts of our code.
 */

/**
 * Every history view, such as the command history, outputview, and all the other panels, implements this class, since every view needs to return a view/node
 * that can be accessed when the corresponding tab button is pressed for that view. For example, in our code, when the tab/button with the "Command" text is
 * pressed, we need to show the node that holds that information, which happens to be a ListView.
 *
 * Purpose: To ensure that every object within the "HistoryPanel" area of the code will return the node that displays the appropriate information.
 * By creating this interface, every object that implements it must return a Node object.
 *
 * Assumptions: That there is a Node to be returned?
 *
 * Dependencies: Node class
 *
 * Example:
 * <hr><blockquote><pre>
 *   public class VariableHistory implements HistoryView {
 *      private VBox variableHist;
 *      public VariableHistory() {
 *         ...
 *      }
 *
 *      public Node returnScene() {
 *        return variableHist;
 *      }
 *   }
 * </pre></blockquote><hr>
 * And then within Main, there might be something like
 * <hr><blockquote><pre>
 *   private HBox HistoryPanelArea;
 *   private VariableHistory vh;
 *   HistoryPanelArea.getChildren.add(vh.returnScene);
 * </pre></blockquote><hr>
 *
 * @author Michelle Tai
 */

interface HistoryView {

  /**
   * This is a getter class for the Node that holds the "scene", or all the visual things of the object. It's not named the best, but
   * I kept this name because when refactoring, most other classes that would implement this interface had a method named that, and I didn't have time
   * to go back and change it.
   * @return Node that holds all other nodes of the scene/panel
   */
  Node returnScene();
}
