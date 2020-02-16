# CompSci 308: Simulation Project Design Review

### Name: Michelle Tai (mrt36)

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci308/current/assign/02_simulation/):


## Overall Design

###Overall

####High Level Design of Each Part And How They Work Together
**Simulation**  
The simulation is designed so that new simulations would be easier to add. There is a superclass/abstract class "Simulation,"
and every simulation is a subclass of it. The simulation classes also have their own Grid object to keep track of cells
and cell states. The simulation interacts with the configuration by taking data parsed from the XML file, like whether or not
the simulation is random or the number of rows and columns, to correctly set up the simulation according to set parameters.  
The simulation interacts with the visualization through the the buttons and the DisplayGrid class. The DisplayGrid class
takes the current states of the cells in the grid and translates them in a way so that the visualization classes can render 
the simulation. For example, it sets the shape of the shapes of the cells in the grid and tells the View classes what shape 
and color ot render each cell.   
**Configuration**  
The configuration is made up of 3 main classes: the SimulationXMLFileChooser, which interacts with the visualization 
to make a file choosing dialogue box pop up in the UI when the "File" button is pressed; the SimulationXML parser, which 
does the actually parsing of the XML file chosen and throw exceptions when there are problems with the XML file; and the 
SimulationXML class, which holds all the information (in the appropriate types) from the XML file so that the simulation/model
classes can use them. The configuration classes interact with the simulation by providing it the information in the XML file
in the form the SimulationXML object, and the configuration classes interact with the visualization classes by presenting a file-choosing
window when a button is clicked and passes the file to the XMLParser object so that the information can be parsed.   
**Visualization**  
The visualization is designed so that most of the components are held in the SimulationViewGui class, but that class is made up of
a SimulationLineChart object, a SimulationViewInfoLabel object, SimulationViewButton objects, and a SimulationViewSubscene object. 
The SimulationViewSubscene is also made of similar objects. The simulation is updated within the SimulationViewSubscene class, and to
update the view of the simulation, the visualization classes interact with the SimulationXML class to get the initial layout of the 
cells in the simulation, and later interacts with each simulation class the DisplayGrid class to properly update the view. 
####To Add A New Simulation
To add a new simulation, you could: 
* Change the XML file, and if you add a new XML tag, you also need to add a new data field in the DATA_FIELDS list within 
SimulationXML: 
```java
public static final List<String> DATA_FIELDS = List.of(
      "title",
      "author",
      "width",
      "height",
      "random",
      "shape",
      "initialConfig"
  );
```
In addition, you would need to add another field to the SimulationXML's constructor 
```java
public SimulationXML(String title, String author, int width, int height, String random, String shapes, String initialConfig)
```
And within 
```java
public SimulationXML(Map<String, String> dataValues) {
    this(dataValues.get(DATA_FIELDS.get(0)),
        dataValues.get(DATA_FIELDS.get(1)),
        Integer.parseInt(dataValues.get(DATA_FIELDS.get(2))),
        Integer.parseInt(dataValues.get(DATA_FIELDS.get(3))),
        dataValues.get(DATA_FIELDS.get(4)),
        dataValues.get(DATA_FIELDS.get(5)),
        dataValues.get(DATA_FIELDS.get(6)));
    myDataValues = dataValues;
  }
```
As well as create a new private variable and a getter method for this new tag. 
* Add another simulation class that extends Simulation and implements the frequency and checkAndReact abstract methods. 
Outside of that, you would also need to override the updateGrid so that the grid is updated following the new simulation's rules. 
That is most of the work you need to do! If anything, you could create a new cell class for your grid if you really needed it, 
but it's not necessarily for creating a new cellular automata simulation. 
####Dependencies
It think that dependencies in our code are both clear and easy to find and exist through "back channels." Some examples of easy
and clear dependencies in our code are our public methods and parameters to methods, like 
```public void start(SimulationXML simInfo) throws FileNotFoundException {``` in line 123 of our SimulationViewSubscene class, which 
depends on having a SimulationXML parameter. There are some unexpected dependencies too though; after using IntelliJ's dependency 
viewer, I saw that xml classes depended on the javafx.stage. This is a bit unexpected, but my reasoning for this is because to display
the file chooser in the view, you need to have access to the stage. However, I don't know how justified this design truly is
in terms of "correct design." Another rather interesting dependency is our grid package's dependency on javafx.scene.layout, but I now 
see that it's due to our DisplayGrid class. This is my fault–I didn't really put the files into packages in the best way, 
and in the future, I will follow more of putting all the files that deal with the view into a package instead of putting
files that are seemingly related (DisplayGrid and Grid) into the same package. 

###Component I Did Not Implement: The View Buttons (SimulationViewButton)
####What Makes It Readable
* The class is relatively short (around 68 lines), so it's easy to see the single purpose of the SimulationViewButton class, which is to render
a single button for the GUI. 
* The methods are named in a way that makes it easy to deduce the purpose. For example, within the SimulationViewButton constructor, 
there is 
```java
setText(words);
setButtonTextFont();
setPrefHeight(BUTTON_HEIGHT);//45
setPrefWidth(BUTTON_WIDTH);//190
setStyle(font);
mouseUpdateListener();
```
and we can easily read that the setText method will set the text of the button, the setButtonTextFont method will set the 
font of the button's text, the setPrefHeight will set the preferred height of the button, etc. 
####Encapsulation
I think the above methods, as well has making a simulation button class, help with encapsulation because outside classes
and methods that need this button only needs to call the constructor of this method to get a button; it doesn't need to 
care about how this button is styled or how event listeners are attached to it to be able to get a button. As a result, 
the implementation of this button can easily be changed without breaking any other part of the program. 
####What I Have Learned About Design 
My teammates' code has taught me to keep my code very readable and use private methods as much as possible. All the methods
listed in the above code chunk are private, which makes sense since no outside classes or methods care about how the button
sets its style. This code is a good example of writing clean and readable code, and I will definitely be using it as a 
reference in the future. 



## Your Design

####How My Code Was Designed
Since I was in charge of the configuration, I designed my code so that: 
1. The visualization/view interacted with the configuration/xml parsing through the SimulationXMLFileChooser class, which created
 a file chooser window when the "File" button was clicked by calling the openFile() method
2. Once a file was selected, the SimulationXMLFileChooser's openFile method created a new XMLParser object using the 
selected file 
3. To get the parsed XML information from the XML parser, the XMLParser's getGame() method is called so that a SimulationXML
object is returned, which holds the correctly formatted/typed data from the XMLParser so that other parts of the program
can use it
4. Because I didn't want the SimulationXML to mutable (the title would never change, the width or height would never change, etc
after being put into the XML), I only created public getter methods for different parts of the XML file, like getTitle(), 
getInitialConfig(), getShpae(), and more so that those would be the only interfaces the rest of the program had to that object
and to the XML's information itself. 
5. Throughout these classes are exceptions that can be thrown just in case the XML file is not properly formatted. 

####Design Checklist Issues
* In SimulationException, there is an unused private variable, which I would remove if I had extra time
* In SimulationXML, the checklist tool says that magic numbers are used in 
```java
public SimulationXML(Map<String, String> dataValues) {
    this(dataValues.get(DATA_FIELDS.get(0)),
        dataValues.get(DATA_FIELDS.get(1)),
        Integer.parseInt(dataValues.get(DATA_FIELDS.get(2))),
        Integer.parseInt(dataValues.get(DATA_FIELDS.get(3))),
        dataValues.get(DATA_FIELDS.get(4)),
        dataValues.get(DATA_FIELDS.get(5)),
        dataValues.get(DATA_FIELDS.get(6)));
    myDataValues = dataValues;
  }
```
which I don't completely agree with. While I could attach a variable/constant do each index, I feel that it's unnessary since
the numbers are index values to access values within a list, not actual magic numbers. 
* SimulationXML also has an unused variable getGridConfig that I would remove if I had time
* I left 
```java
public static final List<String> DATA_FIELDS = List.of(
      "title",
      "author",
      "width",
      "height",
      "random",
      "shape",
      "initialConfig"
  );
``` 
as a public static final, which now looking back on it, I could have made it protected since only classes within the XML
package will ever use this list. I also don't need to make it static since it goes against modularity 

####Good Feature: Percolation Simulation
A feature that I helped implement was the percolation simulation. The code for this class is divided into
3 public methods: frequency, updateGrid, and checkAndReact. frequency and checkAndReact are classes that must be 
implemented from the superclass since they were originally declared as abstract methods. This helps with extending
functionality since the Simulation superclass can allow each simulation to implement frequency and checkAndReact in 
ways that are suitable to individual simulations. The updateGrid class is overriden because the percolation 
simulation has a different way of updating grids based on neighbors (based on whether or not a neighboring cell
is percolated or not). I feel like if I could, I would change the superclass's updateGrid method to also be abstract
since many subclasses override this method.   
I think some assumptions made in this code is that system is percolated only when there are percolated
cells on both the leftmost edge and the rightmost edge of the simulation, not the top and bottom. I was
thinking about whether it should be all sides, but if the top and left side had percolated cells, the system is not
necessarily percolated. Thus, we had to make the assumption that percolation only occurs from left to right or right to 
left. A dependency this class has is in the checkAndReact method, where it accepts int curCell and List<Integer> neighbours.
Without having the correct arguments, the program cannot run correctly, so this class/simulation is dependent on 
this class getting the current cell and a list of its neighbors. However, overall, I dont think these
assumptions and dependencies impact the overall design of the program.

####Feature To Be Improved: SimulationXML
A feature that can be improved is the SimulationXML class, which holds all the parsed xml information. 
Some things I wrestled with when writing this class is that it's not an active class, and that when I added
more XML tags, the constructor for the class became longer and longer. I thought about how to make it so that
there weren't as many parameters in the constructor, but the only solution I could think of were to 
create setter methods instead, but I didn't want any other parts of the program outside of the XMLPArser to be able
to set or change the SimulationXML's informations. Creating setter methods would allow outside classes to possible
mess up the information about the simulation that the SimulationXML is holding, and I didn't want to take 
that chance. However, thinking about it now, I could make setter methods that were protected so only classes within 
the xml package could change the values of the simulation that were parsed from an xml file. This way, classes
not related to XML-parsing and configuration wouldn't mess with that class and it's information, and there
won't be as many parameters in the SimulationXML's constructor as there are now (around 6-8 parameters?).  
I think an assumption/dependency in the code is wht the getInitialConfig method returns. I'm returning
a 2d list, which doesn't hide its data structure and could easily be changed, but I'm not sure how I would
change this or if this is a big issue. This 2d list would hold the initial locations and states of the cell in the simulation. 


## Flexibilty
####Overall
* I think that we created a flexible design by making sure we dont have duplicated code and by 
making declared types as general as possible. To prevent duplicating code, we created a superclass called
Simulation, in which all the simulation types extended. This is so that all the simulations won't have 
duplicate code if they all have the same functionalities, such as update and gettingIndices, and that
shared methods could be encapsulated by the superclass without having each simulation implementing the same methods. 
Because we designed the code this way, it would be easy to add another simulation (we would just need to 
extend the Simulation superclass and implement some functions).
* Our Cell and Grid classes also contribute our flexible design. By having a Cell superclass, we can create
cells of different shapes, like triangles, hexagons, etc by extending the Cell superclass, which has the general cell
behaviors of knowing its currents and next stats, its position within the grid, its shape, etc. Our grid can also be
styled similarly–we can extend the Grid superclass, which has general behaviors of getting a cell from the grid, getting the 
neighbors of a cell, etc. so that it can support different shaped cells (since different cell shapes have
different neighborhood calculations).
* Our view's design is also flexible–by creating button objects and information label objects, the view of
our simulation chooser can easily be modified without breaking the existing code. We can add another button
by making a new button object and adding it into the SimulationViewGUI with an appropriate event handler. 
* I think our XML/configuration isn't as flexible as the rest of the code since it needs to be tailoed
to the specific XML file types, but I'm not sure how we would make it more flexible.

####Two Features Easy to Implement
1. The first feature that was easy to implement was creating a new simulations, like Percolation and SugarScape. 
Since we already had a superclass simulation that each simulation needed to extend, it helped us be able to 
"switch" out any simulations that need to be run without breaking the code. Implementing a new simulation isn't 
too difficult since the biggest thing you would need to do is implement new rules for the simulation. 
2. The second feature that was easy to implement due to our design was making a new simulation screen so you can 
run different simulations at the same time. Since we already had all our view information encapsulated in 
one SimulationViewGui class, we only needed to add a button that created a new instance of this object to bring up
another screen where new simulations can be run. 

####Feature 1 That I Did Not Implement: Triangle Grid
*I chose to this feature since I thought it was a really interested extension to the basic, and seeing 
Lucy write this code was very cool. To create a triangle grid (or any new shape in general), you need to separately
extend 2 superclasses–the Grid class and the Cell classes. The TriCell class, which extends the Cell class, holds the triangle shape of the cell, 
and the TriGrid, which extends Grid, calculates the new neighbors for the grid based on the triangle formation.
* Some classes and resources this feature needs is the Grid and Cell superclasses so that the new shapes of cells
and the new neighborhoods can be made. The new Grid class also depends on the SimulationViewGUI so that it can add
the cells in the grid to the view of the simulation.
* I guess what's closed in this design is more within the classes that are extended. Because the Grid and Cell 
classes are extended (TriCell extends Cell and TriGrid extends Grid) instead of added on to, it follows the 
Open/Close principle of design. 
* I think that having classes themselves that are simple yet effective are also encapsulating. 
* Some assumptions made are that the cells will never be images, they will only be colors from a set
colors list madel, and that we would only ever look at a neighborhood of 12 or 3 for triangle cells. 
* I think these assumptions are limiting because, in the future, we might want to implements any type of 
neighborhood, so we would need to make bigger changes to the code if we wanted to implement that. As for colors, 
it wouldn't be as big of a change, but still is considerable effort to figure out the list order and which
color they want exactly for the cells. As a result, it may be a little more difficult to extend the design. 

####Feature 2 That I Did Not Implement: The Simulation Line Chart
* I chose this feature because I also thought it was really cool in our simulation and really wanted 
to highlight that. I also thought it was interesting how in the code, a new class SimulationLineChart was
created, but not used to create the line chart–the line chart is created in the SimulationViewSubscene instead.
In the SimulationViewSubscene, there is a displayLineChart method, createTimeSeries method, and a updateTimeSeries method
that are used to maintain the line chart. These methods are also in the SimulationLineChart class that isn't used, 
so I thought that it was interesting that the class wasn't used. 
* This feature needs the SimulationViewSubscene class in order to attach the line chart view onto the 
scene. It also need the English.properties file and the defaults.css file to show the words on the chart
and to style the chart. 
* I think what's interesting about the feature is that it would be closed to the SimulationViewSubscene 
had we used the SimulationLineChart object, but instead we put that object's methods directly into the 
SimulationViewSubscene class, which didn't make this feature closed anymore. To improve it, I would switch over
to using the SimulationLineChart object so that the code is more encapsulated and is easier to extend and change in 
the future.
* Some assumptions made are that this line chart will only be used in the subscene class (since the line chart's
functionalities that are currently used in the code are all within the SimulationViewSubscene class), which would make it
harder to extend the functionalities of this feature in the future since we would be changing methods within a class
instead of just extending or changing an object. Switching over to the SimulationLineChart class would
help with that. 
## Alternate Designs

* The Grid class itself would be affect by changing the implementation of the Grid's data structure, as well
the Grid's subclasses (RectGrid and TriGrid), and DisplayGrid. I think that that makes sense since the implementation of 
some methods in the grid will need to be changed, such as updating, getting neighbors, etc. This is a big
change from our original implementation, since we passed around a 2D array that we called our grid so that 
the simulations could directly update it. 
* Some parts of our original design made it hard to implement extensions like different arrangements of neighbors, but other
parts were fairly easy, like implementing new simulations and styling the simulation through XML. Making different 
arrangements of neighbors outside of the 4 and 8 number was difficult since a lot of our code already 
depended on having the current configuration of neighbors. In the future, I would change our design so that the 
neighbors is more closed and so that other methods and classes don't need to care about the location of neighbors. 
However, adding new simulations was fairly easy because we made our code flexible in that sense–by making
a Simulation superclass, all the other simulations can extend this superclass and implement the abstract methods, like updating and such, 
to fit each simulation's needs. In addition, early on in our code, we considered styling, and we were able to easily 
add specifying locations of cell types or having it all be random, adding in cell shapes, and such. In that sense, 
our code was flexible and extendable. 
####Design Discussion 1: Cell Class
* For our cell class, we talked about the tradeoffs of having the cell store its neighbors or not knowing its neighbors 
(aka storing its neighbors in the form of a list).
* The pros of designing our cell to know its neighbors is:
  * Makes sense logically 
  * Makes it easier to update next state based off its stored neighbors
* The cons of designing our Cell class to know its neighbors: 
  * Might need to hard code all the different neighbor configurations into the cell
  * Might be redundant work for the computer
* I preferred having the cell know and store its neighbors because it made sense to me for a cell to 
know who its neighbors are. As a result, the cell can also become a more active class by updating its state
based off its neighbor list

####Design Discussion 2: Where to Put The Simulation Rules
* When designing our simulation, we also came across a point where we were deciding how much of the simulation
we want to be decided by the XML and how much of the simulation to be decided by actual hardcode. We weighed
the pros and cons of having an enumerated class that had the simulation types and rules, had the xml
hold all the rules, or code the simulation rules into different classes. 
* Pros of enumerated class: 
  * All the simulation types and rules in one place, different simulations viewed by calling the different forms of the 
  constructor/parameters
  * Might be easier to have all simulation types in one location 
* Cons of enumerated class: 
  * Doesn't follow open/close principle if we want to add a new simulation since we would need to directly
  edit the current class
  * Not meant to be changed/extended
* Pros of having XML decide rules and type of simulation: 
  * Can make the Simulation class a lot more general and don't need to write a new class for each 
  simulation 
  * More extendable and flexible
* Cons of having XML decide rules and type of simulation: 
  * Harder to code the simulation to follow rules in an XML
  * How should the rules be formatted in an easy-to-understand way for both humans and the computer
* Pros of having a Simulation superclass and each simulation being a subclass: 
  * Flexible and extendable as well
  * Can put all the general, shared behavior into the superclass and the subclasses can override or implement
  the methods in the superclass as they see fit
  * Supports open/close design principle more
* Cons of having a Simulation superclass: 
  * Have to write a new class for each new simulation type, so not sustainable if we want 100 different simulation types
* I prefer the Simulation superclass and having each simulation be a subclass because it follows the 
open/close design principle and suits our needs the best. While it won't be super sustainable if we had a 1000
simualation types to implement, we arent't going to implement that many, which makes this design perfect. 


Here is a graphical look at my design:

![This is cool, too bad you can't see it](online-shopping-uml-example.png "An alternate design")

made from [a tool that generates UML from existing code](http://staruml.io/).


## Conclusions
* I think the best feature in our design is the ability to easily add new simulations and being
able to change the shape of the grid/cells into triangles. Seeing the code for these features really showed me 
how the open/close principle should operate and how to make our code more flexible so that we can add 
more features. 
* I think the worst feature in our design is our XML reading since adding new tags makes the SimulationInfo's 
constructor parameter's much longer and less readable. I'm not too sure how to resolve this issue though. 
* To be a better designer in the next project, I need to:
  * Start being more confident in myself. Since I'm nost as confident, I didn't try to write a lot of code in fear
  of breaking things, and I didn't try to merge my code in fear of messing up. 
  * Keep communicating a lot with my team and checking in on their progress, as well as weighing pros
  and cons of ideas and organizing teams meetings to talk through issues and ideas. 
  * Stop being not confident and stop being passive; I need to be more assertive about my skills and trust
  in my abilities as a programmer!
