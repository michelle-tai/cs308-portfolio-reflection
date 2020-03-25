# CompSci 308: Parser Project Design Review

### Name: 

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci308/current/assign/03_parser/):


##Overall Design
###High Level Design
####Frontend
The frontend's design is mainly building smaller components into bigger components. We created separate
classes for each history view, for the toolbar at the top, for the command input area, for the turtle
canvas, etc. Afterwards, we put them together into a Visualizer class that held all the components 
necessary to see and interact with our program. In this class, we set up our program so that the toolbar
was at the top of the window, the canvas in the middle, the commandline at the bottom, and the history
views and the terminal on the right side. The Visualizer class really put everything together for 
the frontend since it would have the observable lists, the listeners, and the viewTurtles, which are all
needed in different parts of the frontend in order to render the visual part of our program. 
The history panels are the most interesting design-wise. They all follow a sort of hierarchy by implementing
the HistoryView interface. In addition, in the Visualizer, we utilize reflection to attach the onAction
methods for each tab/button using reflection to make our code more flexible. 
A lot of communication in the frontend is returning Nodes, listeners, and such. We also created
separate properties files for the frontend visuals so that we hardcoded as little as possible. We had
files for colors, the toolbar, the visualizer, etc. 

####Backend
The backend stores the commands, the parser, and the XML file reading and creating. The commands all 
implement the Command abstract class to avoid duplicate code in all the commands while also creating
a hierarchy. All commands will have a doCommand method since every command will be executed in some way, 
some moving the turtle, some not. This overlap in all commands having an execute method is a big reason
why all commands extend the Command class. The commands are also put into subpackages/folders to make our
code more general–as a result, we could easily used reflection when parsing the input text to actual 
commands that do things. In addition, each type of command extends a "base" of that command in the folder. 
For example, the Forward command extends the TurtleCommands class, which is in the TurtleCommands foldr. 
The TurtleCommands class extends the Commands class, which is the abstract class for all commands. 
We also use the Factory pattern for choosing what command to execute.  
The parser in the backend uses a tree structure to parse the text entered. This was used instead of 
stacks because it was cleaner and more reliable.   
The backend also have classes like VariableStorage and CustomCommandStorage to save information, but also
to allow the frontend to access information like the value of a certain variable. 

####Together
The frontend and the backend communicate through bindings and listeners. For our turtles, we store
the view of them (aka their image) in the TurtleGrid class. Since we have the backend calculate the 
turtle movements and commands, we have the TurtleGrid in the frontend take in a list of turtles that
have a lot of its properties bounded, like its position, if the pen is up or down, etc, so when the 
backend changes something in the turtle list, like updating the turtle position, the frontend can update
the visual position of the turtle accordingly. So in all, we use binding and listeners, mainly through
classes like TurtleGrid, PropertiesHolder, ActivityListeners, TurtleList, and DisplayOption. The
DisplayOption is particularly important since its our main "bridge" between the frontend and backend:
it holds most of our turtle properties for our bindings.Bind
We chose to use binding and listeners instead of a controller to try what we learned in class, and we 
thought that there would be simpler. 

###What is Needed to Add:
####New Command to the Language
1. Add the proper command name key and the commands variations that the user can enter, like Forward
as a key and forward|fd as its values, into the appropriate language properties file. 
2. Find the correct subpackage under the "Commands" package and create a new class using the command
name and have the class extend the class with the name of the subpackage, but singular. For example, 
for Forward, I would make a new class in the TurtleCommands subpackage and have my Forward class extend
TurtleCommand.
3. Before finishing the class, add a method that would control the behavior of the turtle to the 
class with the name of the subpackage, but singular. For example, I would add a moveForward method into
the TurtleCommand class. This is to help with reflection. 
4. Add the appropriate behaviors into your new command class, and make sure that the doCommand 
method is overridden to call the method you just made in the class with the name of the subpackage, but singular.
Continuing the example above, the moveForward method that's in the Forward's parent class would
be called in the Forward class's doCommand method. 

####New Component to The Front End
You would probably need to: 
* Create a new class in the View package
* Add to the ActivityListeners or PropertiesHolder if needed to bind something or access the turtle
listeners
* Add the component into the Visualizer class

####Unfinished Spec: Change the Pen's Current Properties Graphically
1. Create a slider-esque component for the pen thickness in a new class in the View package
2. Bind the value property of the slider to the pen width
3. Add the slider into the Visualizer, somewhere in the BorderPane object

###Dependencies
I think that the dependencies between parts are fairly clear and easy to find. A lot of our dependencies
are from parameters, which is pretty easy to trace back. Some dependencies may arise from inheritance, but
that is also fairly easy to track. We tried to avoid sub-class type requirements for our commands by having them all have a "doMethod"
command and through reflection. However, upon closer inspection of our code, it looks like we have 
"back channel" dependencies (order of method call?), which occur when the things that will be passed as arguments in 
class constructors need to made first. For example, for the Visualizer, there is only one constructor
```java
Visualizer(Display display, ActivityListeners activityListeners, Observables lists, ObservableList<Triplet<String, String, String>> customCommandList)
```
which would require all the arguments for the Visualizer be made before actually being able to make
a new Visualizer object. 


##Reflect on APIs
###Easy or Hard to Misuse
I feel like it would be hard to misuse the internal APIs for the backend since they're protected. As a 
result, they can't be used outside of the package, and only the subclasses can use it. For example, 
the methods within the TurtleCommand class are protected, so all the commands in the TurtleCommands
package that extend the TurtleCommand class can access the protected methods, but no other classes
can. On the flip side, it would be easy to misuse these methods if we made a command called Forward, but
instead had it call the moveBackward method in its parent class. It doesn't throw an error, but it 
wouldn't be the desired behavior. 
I think it would be easier to misuse some of the APIs I made, such as the returnScene method that's part
of the HistoryPanel interface, which many classes implement. Instead of implementing them as public
methods in each class, I should have implemented them as package-private so that no classes outside
of the View package can access the scenes. 
What makes the APIs hard to misuse are also the fairly clear names on the method, so that it's easy
to tell what the method will do, like returnScene or doCommand. 
####Encapsulated or Not
We tried out best to make our APIs encapsulated by not giving out our data structures like Lists. I think we
did a pretty good job with that, but sometimes we did need to pass ObservableLists around so that 
Turtles and certain history panels would be able to update properly. We also kept return values as
general as possible, like having the HistoryView interface and the history panels return a Node 
instead of a specific ListView or VBox, so the implementation of each history part can easily be 
changed.  
Something that might not be as encapsulated is the VariableStorage and the CustomCommandStorage. 
Since we needed an observable map to help with the TableView and ListView for the variable history
and the custom command table, we had to "show" our implementation. I'm not too sure how to avoid that
though in this case.
####What I Learned About Design From Teammates
Some good/interesting things I learned:
* How to use reflection. I looked at how Thomas used reflection for the commands through the 
class names and subpackages, and I thought that it was pretty clever. He uses reflection as well
as a command factory so that we don't have a chain of conditionals. I originally didn't really know 
how to avoid long conditionals since my mind is very much wired in that sense, but after seeing
Thomas and Sanna use it for commands, I've learned how to avoid conditionals and make code more
flexible and general more through design patterns and reflection. 
* Writing short methods that do one thing, and having it be very clear. It made using APIs a lot
easier too. 

Some things I learned that aren't good:
* Static classes, how to use them, what they are. Originally, we used static classes for our buttons
and to hold our variable history and our custom commands, and while I originally doubted the use of it, 
half of our team was convinced that its good design since we didn't need to constantly have to get the
information through parameters or passing it around. While it was easy to use since we never had 
to declare it–we could just easily call VariableHistory.keyset() anywhere–we soon learned that it
wasn't good design. Apparently, static classes SHOULDNT be used to hold any data. Instead, if should
"execute" things, like math operations. Since we learned this pretty late, we had already use those static
classes throughout our code. It was difficult to refactor, but after that experience, I now know that
using static classes to hold information is BAD. 


## Your Design
###My Code At A High Level 
* I wrote a good part of the history panels, the command line, and some of the tool bar. 
The history panels all implement the HistoryView interface, which creates a type of "contract" that
each history panel, like the variable history, the user-defined commands, the command history, etc, must
follow. In essence, they all need to return a Node that can easily be added to the the Scene in our
Stage. As a result, all the history panels return a Node using their returnScene method they implemented, 
whether that Node is a VBox or a ListView, and the HistoryPanel class then displays these Nodes
when their corresponding button/tab is clicked. The HistoryPanel class also implements the returnScene 
method so that the history panel area can be added to the overall view of our SLogo program in the 
Visualizer class. The CommandLine class also returns a Node through its getCommandLineGroup method 
so that the Visualizer can use it. This is the only public method in the CommandLine class. I designed
the code this way so that the Visualizer didn't need to care about how each component of the front end 
is implemented, only that each component will definitely return an appendable Node to the scene.  
The VariableHistory class interacts with the VariableHistoryRow by calling it to create a new
row every time a new variable is made. The VariableHistory class keeps track of this through the
ObservableMap in VariableStorage. Whenever an addition to the variable map is detected, a new row
is made. The variable can also be updated through the history panel itself. If you enter a new 
value in the variable row's TextField and press enter, it also updates the map and the display of 
the variable value.  
* The user defined commands work in a similar way, but instead of creating a new row every time, I use
the TableColumn's cell factory to create a new cell every time a new Triplet is added to the Observable
map in the CustomCommandStorage.   
* I designed the Triplet class with the help of stack overflow, making the class not only serve my uses
for the user-defined command history. Instead of only allowing the triplets to be of Strings/StringProperties, 
* I designed the class so that the type of each object in the Triplet can be of any type, as long 
as it's specified at compile-time. I tried to create it to be as flexible as possible.
* The CommandLine class has properties that are bound to the text area, so that whenever the 
"Go" button is pressed, the text will be sent to the parser
* I also used bindings for the images of the turtles, which is controlled through a combobox in 
the tool bar. 
* I tried avoiding magic numbers as much as possible through the use of constants and properties files. 

###Remaining Design Checklist Issues
* In my CommandLine class, I reference 
```java
public static ResourceBundle myResources = Main.MY_RESOURCES;
```   
which may not be the best design practice. However, I think that it's okay to access "global" information
like this if its public static in main, so that it will never change? It's only a resource.
* There are some unused private variables that I didn't have time to remove
* Some exceptions aren't caught properly 
###2 Features I Implemented
Both features implement the HistoryView interface. 
1. Variable History (Better one)  
* One feature I implemented was the variable history. I designed it so that the variable history
would be a ListView, and when a new variable was created, a new row would be added in the variable
history. Instead of just adding a new row, though, in my VariableHistory class, you might notice that
I kinda hacked around it. I had to clear the ListView each time there was a change in the observable map
for the variables because if I added a row each time there was a change in the map, there would be 
duplicate rows if a value of a variable was only updated. To avoid having the users be confused, I 
just cleared what was in the ListView and created a row for every key, which is the 
variable name, in the observable map. This prevented a lot of conditionals/checking if the change in the
map was an addition, and there's a finnicky thing in the map where updating a value in the map is 
implemented in away so that the map would remove the old entry and add in an entry with the same key, 
but new value. Originally, I had the ListView update only when there was an addition to the map, but the
previously stated implementation continued to cause duplicates, which is why my code is written the way 
it is.   
In addition, I chose to have each row be made in a separate class instead of within the 
VariableHistory class to separate functionalities and make sure that each class has one job. Also, 
it would allow for the user to be able to change the value of the variable interactively. When 
a new row for a new variable is created, a new VariableHistoryRow object is made. The row
displays the variable name, the variable value, a TextField, and an "Enter" button. When I originally
tried to use TableView and click the cell to change the value, I had a lot of difficulties 
with updating the variable map when a new value is typed in. Also, after clicking off the 
changed cell, the value changes back. Apparently, you have to change a lot
with how the TableView renders and somehow detect a change in its rendering to update the variable
map. As a result, I chose to just use a ListView and have each row have a text area that the user
can use to update the value of the variable from history panel. Thus, the VariableHistoryRow itself
is responsible for **updating** the variable storage and the displayed value of the variable in its row, while
the VariableHistory is in charge of recognizing when there are new variables and **creating** a new rows
for them.
* Some assumptions that I made were that every variable and its value would have a String representation since
that's what the VariableHistory uses to display the variable and its value. Also, I assume that the
variable history storage gives me an ObservableMap, which isn't the best design. Some dependencies
are the ones VariableHistory has on VariableHistoryRow

2. User Defined Commands (could be improved)
* Another feature I implemented was the viewing of the user-defined commands. I decided to use
a TableView to display this information because I thought it would display the information in the 
most logical way. I had to create a Triplet class so that the command name, the variables needed for the 
command, and the turtle commands that make up the custom command could all be together in one data 
structure. This was so that each TableColumn could have access to all three, but looking back now, I 
didn't need to make a Triplet class––I could have just made separate observable lists. The pro 
to creating this class is that it can easily be reused in other programs if necessary: It's not dependent
on anything in this code. On the other hand, I had to add StringProperties within the Triplet data 
structure so it would be be compatible with the TableColumns.  
Another problem is that, like the variable history, I struggled to make the table editable and save the 
edits. Also, when a command is updated through the command line, the update isn't reflected in the 
view.  
To improve this, I would try to find a way to make it updateable, as well as not have the UserDefinedCommands
class dependent on an ObservableList of Triplet objects, which would make the class more flexible. 
* As mentioned above, a dependency in this feature is the dependency of having an ObservableList of Triplet objects
passed into when a UserDefinedCommand object is made. 

## Flexibilty
###What I Think Makes Our Project's Design Flexible Or Not
I think the main things that made our project flexible were: 
1. Using a Tree for command parsing  
  a. Using a tree instead of a stack made it easier to implement the new commands since using the stack
  had issues with repeats/loops.
2. Command Factory + Reflection  
  a. By creating the structure we have with the commands, as described earlier, it allows new commands
  to be easily added without having to change a lot of code. Using reflection in our command factory
  class also allows our code to be more general and support more commands. 
3. Separate classes for each component  
  a. By breaking up each feature/component into as small of components as possible and putting them 
  into separate classes, we can easily build new components or accomodate for new feature by stitching
  together our small components. Also, it makes it easier to add visual things to the frontend, like if
  we wanted to add slides, we could just make a separate class for them and easily insert a few lines into the 
  Visualizer class to have that functionality. 
4. Reflection in the HistoryPanel's tab buttons  
  a. Sanna and I spent too much time on this to not make our design flexible. Jk. It makes it easier
  to reorder things, since we used reflection with methods to assigned actions for each tabl button 
  the History panel. If we wanted to add a new panel, we would only need to write a private method in
  the HistoryPanel class and add the method name into the appropriate properties files. It also
  serves as an example of how we can use the button reflection in other places if needed. 
  
###2 Features That Were Made Easier to Implement By Our Design
1. Multiple Turtles  
  Because we were planning on/had turtles in a separate class with the appropriate properties, like
  the position values, pen up or down, etc., it was easier to implement multiple turtles. We just needed
  to add the IDs and the appropriate commands. We did anticipate this being a part of the complete, so we
  adapted our design so that multiple modelTurtles and viewTurtles can be. The model turtle is bound to 
  the view turtle, so that when things like the position of the model turtle changes, the visual 
  position of the view turtle also changes. 
2. New commands  
  New command were easier to implement because of our command structure and reflection. Since we 
  designed our commands without using a lot of conditionals, we were able to easily add the new commands like
  "Ask," "Tell," "SetShape," etc. without a lot of difficulties. We only needed to add a new class/new package and 
  add to properties files.

###2 Features I Did Not Implement
1. Saving the commands that have been run into an XML file  
  i. What I thought was interesting about this code was pretty much how easily Sanna
  adapted it from her simulation team's to our project. I think it serves as a pretty good example
  of how flexible our code was. She pretty much only needed to pass in a list of commands, and easily
  wrote the XMLCreator class to save the current state of the program. I also think that this functionality
  is really, to be able to save the past commands as XML files and then being able to run them again.   
  ii. Classes and resources required to implement this features are pretty much DocumentBuilder classes, 
  a list of commands passed in to be able to create the XML file. To actually use this function, you 
  need an empty XML file.   
  iii. Sanna pretty much broke this down into separate methods that are protected, so that 
  other classes in the XML package can access the methods in the XMLCreator too. She follows the pattern in having
  each method only have 1 behavior, and it's very cleanly split. Besides that, I feel there's not a lot
  to her design that needs to be closed, per se. Since XML tags are unique to the program, there isn't much
  that can be extended, so the design isn't really closed. I guess we could generalize it more by having the 
  tags needed be passed as a parameter, but it would introduce more dependencies, and we would need more 
  complicated logic to order the tags and decide which tag goes to what. Also, I feel like all the implementation 
  details are encapsulated, except for needed a File to save the XML to. An assumption made is that this
  XMLCreator is used only for our SLogo and nothing else in the future, which limits its flexibility
  to be easily used in other programs.  
  iv. I feel like it would be fairly easy to extend this class since the methods/behaviors are broken
  down in a very intuitive way, making it easy to edit if needed.   
  
2. Command Parsing
  i. What I thought was interesting about this code was how flexible it was. There was a lot of thought 
  put into how the commands should be parsed, especially without using conditionals, which made it 
  really easy to add new commands. The commands are organized in a way so that is was easy to 
  use reflection to do things.  
  ii. Classes or resources needed to implement this feature are pretty extensive-pretty much all of the
  model package is needed since its made of up mostly commands and what is used to parse text into a tree
  structure. Resources needed are properties files that have the commands in different languages.  
  iii. About the design: 
    * All the commands are within a Commands package within the model package.
      * Inside the Commands package are subpackages based on each command's behavior, a Command abstract class, 
      a CommandFactoryInterface, and a CommandFactory class that implements the CommandFactoryInterface. The
      interface allows extensibility, and the abstract class provides an inheritance hierarchy. These parts
      are also closed since other classes extend them. 
    * Each command is then divided up into subpackage based on functionality, such as BooleanCommands, 
    DisplayCommands, MathCommands, etc. 
    * Each command subpackage has a parent class that all the other commands in the package extends. 
    This class extends the Command class, and all the other commands extend this class because each 
    command needs to implement the doCommand method (by overriding), and there's some information needed
    needed by all the commands in the same package (which is why all other commands in the package extend
    this parent class). For example, the parent class to all the commands in the BooleanCommands package
    is the BooleanCommand class.
    * Each command in the subpackage extends the appropriate parent class in the package and all override
    the doCommand. This encapsulates how the implementation of how the command is executed, which 
    helps with the reflection since every command must have the doCommand method implemented, so no
    matter the command, as long as doCommand is called, the appropriate behavior for the command will be 
    executed. 
    * The CommandFactory then executes the command using the package name and reflection. The CommandTreeExecutor 
    uses this. 
      * In the CommandFactory
      ```java
      public Command createCommand(String commandClass) {
          System.out.println("Command Created");
          try {
            return (Command) Class.forName(commandClass).getConstructors()[0].newInstance(commandClass);
            //command.setParams(params);
          } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            //printStackTrace();
            showError("Class doesn't exist");
          }
          return null;
        }
      ```
      * In the CommandTreeExecutor, the commandClass String is found through
      ```java
       private String getCommandClass(TreeNode element, List<String> parameters) {
          String commandName = element.getName();
          String commandClass = THIS_PACKAGE + getPackageName(commandName) + "."
                  + commandName;
          int numParamsShouldHave = Integer.parseInt(commandParameterNumbers.getString(commandName));
          if (parameters.size() != numParamsShouldHave) {
            throw new CommandException(errors.getString("WrongParameterNumber"));
          }
          return commandClass;
        }
      ```  
     * An assumption would be that we would be given the groupings of the commands, which limits 
     flexibility since we would need to be told which command belongs into which subpackage.
   iv. This feature is fairly extensible/extendable. Because there is an inheritance hierarchy, it's 
   pretty clear how to extend the code–if we needed to add a new command, find the right subpackage, 
   extend the parent class in the subpackage, and override the doCommand method. A change that would 
   be hard for the design would be adding a lot of new commands, each within their own, unique subpackage. 
   We would need to create a lot more parent classes and edit a lot of property files (for each language).


## Alternate Designs
###Classes Affected By Adding Multiple Turtles
* The classes that were affected by adding the multiple turtles features were:
  * Turtle class: Had to add more getters, setters, and properties. Also needed to implement a method
  the returned the turtle's ID.
  * TurtleList class (new): To create all the bindings for the list of turtles, as well as to 
  separate the list the view could see vs the one the model could see.
  * Observables (new); Creates all the observable lists/maps, so added observable list of turtles
  * TurtleGrid: Changed the constructor so that the observable list of turtles could be passed in. Needed
  the list so that we could tell which turtles to draw lines, to change the images of turtles, to keep 
  track of position of all turtles, etc.
  * Visualizer: Changed constructor to pass in Observables object, which held list of turtles 
  * Main: Initialize list of turtles
  * Toolbar: Toolbar controlled the turtle images, pen color, and more, so needed to have a list of 
  turtles so that all the turtles would reflect the changes
* A way to reduce the number of classes affected would be to not have similar classes, like Observables
and TurtleList classes, as well as keeping the Turtle class as close to its use place as possible. I also 
feel like it shouldn't be necessary to have to change both the Visualizer and Main, but I'm not sure how to 
remedy it. However, I think the list of affected classes isn't too long, which is good?

###How Our Original Design Handled The Extensions
* I'm not sure how to quantify how well or poorly our original design handled the extensions. I guess
since I didn't have a huge headache or bad experience over it, our design handled it pretty well? It
definitely can be improved, but we did try to make classes as general and flexible as possible, and 
tried to incorporate a lot of what we learned in class to our code, like making flexible buttons through 
reflection, using bindings and properties, etc. We definitely strayed off our API.   
* I would say that the backend handled having new commands and more turtles very well due to our
design. Our frontend handled all the new features fairly well–we just had to create more bindings, activity
listeners, etc. It was hard at times, but definitely not impossible. It might only feel like the frontend
didn't handle the new features as well since we had to do a lot of research into new JavaFX tools and had 
to implement a lot more components. 
* Changes were handles and discussed as a group. We always met and talked about how to approach things 
and to make sure everyone was on the same page. Decisions were made usually by weighing pros and cons of 
the design and how it would affect our current code. 

###2 Design Decisions Discussed By The Team

####Design Decision 1: Controller vs No Controller
1. Something our parser team talked extensively about was whether or not to use a controller. After learning
about bindings during class, we thought that it was a viable option and might make our code easier. It would also 
mostly eliminate the need to something in between the frontend and backend.
2. Tradeoffs:   
    * Yes controller: 
      * Pros:  
        * Divides the code into clear components and would be easy to transfer values between the different components like Front-end and Back-end
        * Would make it much easier to divide the code as the methods that should be common for Back-end and Front-end could be put in the controller 
      * Cons:
        * More APIs to build as the controller component would have 2 different APIs as well
        * Data would have to be passed between more components 
    * No controller:
      * Pros: 
        * Less classes that feel a bit redundant and would cause some duplication. 
        * ViewTurtle (only stores an image along with the other variables stored by the model turtle) 
        * ValueTransfer -  has the sole purpose of transferring values between the view and model.
        * We could try to use binding - could update a lot of values automatically
      *Cons:
        * Mixing the front end and back end
        * Model depends on javaFX (but only beans and observable list)
        * Can’t have any type of controller classes?                        
3. After considering the pros and cons, we thought that we would want to at least try out binding with
no controller–it seemed interesting to try and use. Looking back now, I think I still would have gone with 
bindings. Although there were moments during the project where we wished we had a controller, it was good to 
practice bindings, and I think having the "automatic value transfer" helped a lot. It also allowed our team 
to focus on two sides, with the team split more evenly, although Sanna did end up working on both the frontend and backend, 
acting as a middle person (and was a pro at bindings).

####Design Decision 2: Static Classes?
1. Something that our team struggled a lot with was whether to store data in static classes or regular classes. In the beginning, 
half our team was convinced that the static classes were better design. They said that it would lead to less duplicate code, 
and that a TA suggested it to them. As a result, the other half of the team conceded and went with it, using the static
classes for our variable history and custom commands. 
2. Tradeoffs: 
  * Static classes: 
    * Pros:
      * Less duplicate code
      * Data can act in a more "global" type of way without the name of a "public global variable"
      * Only 1 version of the data/don't need to make an instance
      * Very easy to update the data and access public methods since don't need to have it passed in or initialized in the class
    * Cons:
      * Acts like global variable, which is no bueno
      * Doesn't feel right
      * Apparently not supposed to use static classes to hold data (which we didn't know util later)
      * Can't override methods
      * BAD DESIGN (which we also didn't know until later) since static data shouldn't change
  * Regular, public class: 
    * Pros:
      * Probably better design
      * Felt more right (to me)
      * Makes more sense
    * Cons: 
      * Have to pass around the data
      * Need to initialize the object/data
      * Creates dependencies
3. Originally, we stuck with the static classes, despite 2/4 teammates being skeptical. However, having less duplicate is good, 
right? Despite this and using the static classes for both of my history panels (both classes were not started by me though; I 
had to access that data, which was already made and "stored" in those static classes), it still didn't feel right to me. After a lot 
of prodding and research, as well as checking Piazza, we soon saw someone ask a similar question. From that, we learned that
static classes should ONLY be used in the case of utility classes and should NOT be used to hold data, since static data
means that the data should not change. We ended up having to do a big refactoring of our static classes. I AM glad that we
ended up using the regular classes and not static classes, especially after learning that static classes shouldn't be
used to hold data. Not using static classes was what I preferred, and I now know it is a good that we ended up refactoring
to get rid of the static classes. 
  
## Conclusions
###Best Feature
I think the best feature of our project is our commands parsing. The way it was designed using inheritance and reflection 
allows new commands to be easily added and avoided using long chains of conditionals. By having every command have a doCommand
method, it was easy to use reflection and allowed us to avoid having to be depending on subtypes of classes. I learned a lot 
about good ways to use inheritance and abstraction through reading through the commands + the parsing.  
###Worst Feature
I think the worst feature is adding new rows to the terminal and the command history. They're both in the Visualizer/HistoryPanel, which 
doesn't make sense to me; shouldn't they be in their respective OutputView and CommandHistory classes? By reading through this code, 
I learned that we should definitely Tell, not Ask. Also, keep behaviors involving objects either within the object itself
or as close to the object as possible.
###To Be A Better Designer: 
* Start doing differently: 
  * Be more proactive and voice my opinions more. If I had pushed against the static classes idea more + had more confidence, 
  we wouldn't have had to waste time refactoring it. 
  * Don't be scared to write code and then refactor it to make it better. Something I have struggled with is being confident
  in the code I write–I'm scared of messing things up–which leads to less commits and less code written. However, I do like 
  talking about the design process and planning. 
* Keep doing the same: 
  * Communicating a lot. I always talk with the team about design choices and get their input on it, which helps me 
  learn as well. It also helps with bouncing ideas off of each other. I'm definitely more comfortable with talking with people
  about the design and how to tackled things than writing the code for it. 
  * Keep pushing for team meetings & pair programming
  * Keep improving on writing more code! Wrote more code than last time, so I have a better grasp on how to implement 
  certain design techniques. 
* Stop doing: 
  * Stop being unsure of myself. Best way to learn and design better is to make mistakes, so don't be afraid to fail. 
  * Stop being meek and voice my thoughts more–it might help the team in the long run
  * Stop doing most work in the team meetings and be confident enough to code by myself, without always having to have 
  my ideas validated by my teammates.  
  
In all, I think I've improved a good amount since the last project!


