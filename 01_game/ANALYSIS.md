# CompSci 308: Game Project Design Review

### Name: Michelle Tai

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci308/current/assign/01_game/):

## Status

**I spent the most time** on making the objects, specifically the paddle, brick, and ball. I think I may have tried to refactor this part of 
my code too much, and really tried to make these classes use hierarchy. I switched between having all three objects extend a "Sprite" class, then I 
made it into a interface (even though I wasn't completely sure what I was doing), then tried to make a RectangularSprite class that implemented the "Sprite"
interface and had the Paddle and Brick classes extend this class. In the end, I had the Ball class implement the sprite interface, and I had the Paddle and Brick class
extend the Rectangle class so that Paddle and Brick objects could be added directly to the root node. I spent around 5-9 hours on this small part, and in hindsight, 
I should have just coded all three classes and tried to find what they shared instead of forcing them into a hierarchy. 

**I also spent a lot of time** revising my collisions. I really wanted the ball to travel in a way that it won't change y-direction
unless it hits the bottom or top of a brick, top of a paddle, or the top of the screen. I originally had the ball change both the x and y direction whenever it 
collided with anything, which wasn't the expected behavior of a ball in a breakout game. As a result, I spent a lot of time debugging my ball movement
and editing the conditionals in the "bounceOffPaddle" and "bounceOffBrick" methods in the Ball class. I also tried to refactor these 2 methods into 1 since they're
almost identical pieces of code, but I wasn't able to combine them in a way that worked. I tried having "Rectangle rect" be a parameter to 
these 2 classes instead of "Paddle paddle" or "Brick brick," but a lot of errors came up when I tried to do so. This was also a reason
why I spent a lot of time fitting the Paddle and Brick objects in a type of hierarchy–since they're both so similar (both rectangles), I wanted to be able to 
pass either into a method and have very similar resulting behavior.

The parts of my code that are **not DRY** is, as stated above, my "bounceOffPaddle" and "bounceOffBrick" methods in the Ball class. They both 
contain this exact piece of code: 
```java
    /**
     * Returns sum of all values in given list.
     */
    if(paddle.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterX() || paddle.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterX()){
        yDir = yDir * __
    }

    else if(paddle.getLeftBound() - ballCircle.getRadius() <= ballCircle.getCenterY() || paddle.getRightBound() + ballCircle.getRadius() >= ballCircle.getCenterY()){
        xDir = xDir * ___
    }
```
I tried to refactor this code into one method that could take a Rectangle object instead of taking either a Paddle object
or a Brick object (they both extended the Rectangle class), but I wasn't completely sure how inheritance worked (and still don't), 
so my plan failed. I also tried making a different method that would handle general direction change and could be caled in this method, but
that didn't work as well.

My code is also **not DRY** for the Paddle class, Ball class, and Brick class in general. There's a lot of methods among the 3 classes
that are the same, such as getters and setters. While I now understand that this is bad practice, I'm not sure how to write code that
doesn't need getters or setters to "hide" implementation. 

The **most important bug** remaining in my program is either the score not being carried through the levels or the ball sometimes getting stuck. 
I tried to somehow pass the score at the end of the level back to the Main class, but it didn't work/introduced some bugs with adding a child node that
already exists. For the ball, it would somehow get stuck at the edge of the screen sometimes an vibrate upward and out of the scene. I'm not too sure why; 
it could be due to my collision checking. However, this happens rarely and can be reset with the cheat code, so it's not a huge issue, albeit an annoying one.

**Describing the "bouceOffBrick" method in the Ball class**
```java
/**
 * Changes the direction of the ball's travel when the ball comes in contact with a brick
 * @param brick is the brick the ball is in contact with
 * @author Michelle Tai
 */
public void bounceOffBrick(Brick brick){
    if( brick.getBotBound() + ballCircle.getRadius() >= ballCircle.getCenterY() && (ballCircle.getCenterX() > brick.getLeftBound() && ballCircle.getCenterX() < brick.getRightBound())){
        yDir = yDir * -1;
    }
    else if(brick.getTopBound() - ballCircle.getRadius() <= ballCircle.getCenterY() && (ballCircle.getCenterX() > brick.getLeftBound() && ballCircle.getCenterX() < brick.getRightBound())){
        yDir = yDir * -1;
    }
    else{
        xDir = xDir * -1;
    }
    brick.incHitCount();
}
```
* This method is meant to control the Ball's behavior when it collides with a brick. If the ball's outer edge position (the current 
position + the radius of the ball/circle) overlaps with the bottom bound of a brick, it changes its y direction. Else if it overlaps with the top bound, 
the ball still changes the y-direction of travel. Else, the ball has encountered a side edge of the brick, so the ball should change its x-direction.
When the ball encounters a brick, it should increase the value that keeps track of the brick's number of collisions. 
* My brick's "incHitCount" method makes this method easier to read since the method name describes exactly the method does: Increase the hit count
of the brick, the number of times the ball has collided with 
* The very similar conditions for first 2 conditional statements make the method hard to read since the code seems duplicated, but the only thing
that is changed is whether the ball is checking a top bound or a bottom bound. Also, the conditionals are a little long to read, so I could probably refactor
each part of the condition into variables so that the condition statements are more intuitive. 
* Git commits that contributed to this method are: 
    * [Changed ordering so that there is a sprite interface and a rectangular sprite class, and the paddle and the brick extend it. Commented code out](https://coursework.cs.duke.edu/compsci308_2020spring/game_mrt36/commit/d7f0e0a64dfb40c1dfd61ef70e06eac61aa863b0#ef7523496ec64f83dbee4270996193395ef598af)
        * Purpose: Tried to create a hierarchy so that paddlle, ball, and brick class could extend a class. Mainly new code and refactoring
    * [the rectangles are not showing up anymore...](https://coursework.cs.duke.edu/compsci308_2020spring/game_mrt36/commit/3c765046ac57d66696cd85024f9ec542069c156e#88222688748185fc53154be3294aea4fc2446709)
        * Purpose: Saving my current work to debug so I could reset to this commit if needed
    * [switching between levels works, now need to make score carry over](https://coursework.cs.duke.edu/compsci308_2020spring/game_mrt36/commit/daf694640572b8e739973bd1b096f6f30b0b3019#88222688748185fc53154be3294aea4fc2446709)
        * Purpose: Refactoring and adding code since I made levels
    * Looking at my commit messages, it definitely doesn't seem like my edit to my method is actually related to most of these commit "themes."
    * Why package: I honestly wasn't sure how to package my commits, so this reason applies to the following commits too. I 
            wanted to "save" my work in a sense since I did reset to a different commit when things were getting really broken. I know that
            each commit should have a "feature" it focused on, but I didn't know how to package that, especially when I Was working between 
            different classes that might have dependencies on each other (which I now know is bad code design).

**Describing the "setBounds" method in the Brick class**
``` java
/**
 * Sets the boundaries of the block, which is used to determine collisions
 * @author Michelle Tai
 */
public void setBounds() {
    leftBound = this.getX();
    rightBound = this.getX() + this.getWidth();
    topBound = this.getY();
    botBound = this.getY() + this.getHeight();
}
```
* The purpose of this method is to calculate the "bounds," or edges, of the Brick's rectangle shape and save them into local variables. 
The left and right bounds' values are determined by their x positions, with the left position being the the value from the extended Rectangle's
class "getX", and the right bound is the left bound's value + the width of the rectangle. The top and bottom bounds' values are
determined by their y position in the window. The top bound is the value returned from the getY function, and the bottom bound
is the top bound's value + the height of the rectangle (going downward in the window = larger y value).
* I think the variable names make the method pretty easy to understand. The variable names are exactly what they appear to be. On the other hand, 
the "this.get_" might make the code a little hard to read since it looks a bit redundant? But it's not actually duplicate code. I think I could have made 
it more clear by explicitly using the "leftBound" and "botBound" variables in the calculating the "rightBound" and "botBound"
values respectively.
* Git commits that contributed to this method are: 
    * [Changed ordering so that there is a sprite interface and a rectangular sprite class, and the paddle and the brick extend it. Commented code out](https://coursework.cs.duke.edu/compsci308_2020spring/game_mrt36/commit/7a46b128d9825e6c66765b070abe52ac170f84c2#ef7523496ec64f83dbee4270996193395ef598af)
        * Purpose: Tried to create a hierarchy so that paddlle, ball, and brick class could extend a class. Mainly new code and refactoring
    * [the rectangles are not showing up anymore...](https://coursework.cs.duke.edu/compsci308_2020spring/game_mrt36/commit/3c765046ac57d66696cd85024f9ec542069c156e#ef7523496ec64f83dbee4270996193395ef598af)
        * Purpose: Saving my current work to debug so I could reset to this commit if needed
    * [Changed the paddle and block class to extend the rectangle class, and changed logic so that rectangles would actually be removed](https://coursework.cs.duke.edu/compsci308_2020spring/game_mrt36/commit/4327a078d0678e13135e40a10a9284ed6a24ab5b#ef7523496ec64f83dbee4270996193395ef598af)
        * Purpose: To have the paddle and block class, which act very similarly since both are rectangles, extend the Rectangle class and edited logic so that 
        rectangles woul dbe removed from the grid when hit
    * [Making levels now](https://coursework.cs.duke.edu/compsci308_2020spring/game_mrt36/commit/7a46b128d9825e6c66765b070abe52ac170f84c2)
        * Purpose: Save my working game before trying to make levers
    * As said previously, looking at my commit messages, it definitely doesn't seem like my edit to my method is actually related to most of these commit "themes."
    * Why package: As said above, I honestly wasn't sure how to package my commits, so this reason applies to the following commits too. I 
            wanted to "save" my work in a sense since I did reset to a different commit when things were getting really broken. I know that
            each commit should have a "feature" it focused on, but I didn't know how to package that, especially when I Was working between 
            different classes that might have dependencies on each other (which I now know is bad code design).

I tried to make my code **as readable as possible** by naming my variables and methods in a a descriptive fashion. For example, brickGroup is literally
a Group object that holds all the bricks. Also, I made sure that each method only did 1 specific thing, so the name of the method wouldn't
be long and would be intuitive. For example, my "initializeText" method in levelChooser initializes the text so that it can be displayed in the scene. As a result, 
when this method is called, the person reading the code can easily assume what it supposed to happen instead of guessing what a clump of code is supposed to do. 
I also made classes, which could help with readability. 

## Design

My code is not the most organized. In particular, my Main class and my levelChooser class are messy. In those classes, I pretty much slapped code in 
to try to make my game work, which was not the best idea. However, I think I'm taking a step in the right direction by thinking about a hierarchy and trying
to create separate classes for the ball, the paddle, and the bricks. 
* I would add a new level, level 4, to the game by changing line 151 in the Main class
    ```java
    if (code == KeyCode.DIGIT3 || code == KeyCode.DIGIT4 || code == KeyCode.DIGIT5 || code == KeyCode.DIGIT6 || code == KeyCode.DIGIT7 || code == KeyCode.DIGIT8 || code == KeyCode.DIGIT9) {
        currLevelNum = 3;
        setNewLevel();
    }
    ```
    to 
    ```java
    if (code == KeyCode.DIGIT3) {
        currLevelNum = 3;
        setNewLevel();
    } 
    if(code == KeyCode.DIGIT4 || code == KeyCode.DIGIT5 || code == KeyCode.DIGIT6 || code == KeyCode.DIGIT7 || code == KeyCode.DIGIT8 || code == KeyCode.DIGIT9))
          currLevelNum = 4;
            setNewLevel();
        } 
    ```
    However, I see that this is not sustainable since if I were to 5 more levels, for example, I would pretty much have to copy and paste the above code 5 times
    and make edits to the conditions. Also, the levels currently aren't customizable, so it would be boring rows of bricks. In addition, 
    I would need to add more 
* My code is not SHY in my Paddle, Ball, and Brick classes. My Paddle and Brick classes extend the Rectangle class, and my Ball class implements my Sprite interface, which 
are all dependencies. If I were to remove a method in the Sprite class (or somehow changed the Rectangle methods), then the classes that inherit/extend/implement it
would also have these changes. This can be both good or bad. I think that making the Paddle and Brick class extend Rectangle is good in my code because it shares similar behavior 
as it, but I don't think having the Ball implement my Sprite interface is smart, especially since the Sprite interface isn't implemented
anywhere else. I could have just gotten rid of the Sprite interface overall. I also have dependencies in my "bounceOffBrick/Paddle" methods
in the ball class since an entire object is passed as an argument to the method. Looking at the dependency viewer, it seems that all of my classes have dependencies
since I pass around a lot of objects. I now know that this is bad design, but I need to clarify how to fix this. I think I could pass the values of an object instead of the 
whole object itself to decrease dependencies. 

**Describing Feature 1: A Grid/Layout of Bricks**
* This feature is the layout of bricks in the scene. This layouts the bricks in the scene so that the ball can bounce of them. In the grid, the topmost row of blocks is light pink, 
and each block only takes 1 hit to clear. The second row has light blue bricks that takes 2 hits to clear, and the third row has green bricks
that take 3 hits to clear. This is the "interesting" part of the game since the player now has a goal to clear the bricks from the screen
while keeping the ball alive. I implemented this feature shortly after creating the Ball, Paddle, and Brick classes and ensuring that they all
interacted in a reasonable way.
* My Brick class, the levelChooser class, and the Main class are all required to implement this feature. The levelChooser class contains the grid
that is made up of Brick objects, and the Main class adds the group of bricks from the levelChooser class to the scene's root node so that 
the brick will show in the window. Specific methods needed is the levelChooser's class updateBrickArr method and initializeBrickGroup method so that the 
grid's blocks could be added a group within the levelChooser class and passed to the Main class to display and update. The Main class's update method
is needed to. Some other resources needed would be a layout of the bricks and the size of the screen/bricks. Although I wasn't able to
have the grid of blocks be displayed using information from a text file, the text file would be the next resource needed. 
* The assumptions this feature made is that: 
    * Every Brick is a Rectangle
        * If wanted to add Images, would be more difficult
    * There will be max 3 rows of bricks
        * This is limiting because it would be if someone wanted to add more levels, they would have to edit different part os the code 
        to make it happen. 
    * There will be 5 bricks per row with the same width and height
        * This is limiting since someone could want bricks of different width and heights, and not the same number of bricks per row. The current
        layout is boring, but since I used a 2D array to hold my Brick objects and iterate through them, I'm limited to rows with the same number of bricks. 
    * There will only be 3 brick types/colors
        * It would be pretty difficult to add more brick types and colors since I pretty much hardcoded the colors and types to correspond with the 
        level number, so to add more types, I would need more rows, and to have more rows, I would need to make the bricks smaller. A lot of factors depend on
        other factors, so this assumption is also pretty limiting. 
     * I think that all these assumptions affect other parts of the program, especially since these bricks are displayed in the scene
     and used to calculate the score, as well as when the next level should be reached. 
* The design of this feature was slightly changed later, but then reverted. The main change was to add more "levels," so when I started to add new 
features in the form of levels, changed the conditions in my for-loop that looped through my 2D array. However, before finalizing, I tried to read in the grid
configuration using a Scanner, but had trouble scanning and displaying the blocks on the screen. 

**Describing Feature 2: The Lives and Score Section**
* This feature is the status bar at the bottom of each level. As a brick clears, the score updates by adding the number of hits it took to clear the block
to the score at the bottom. Also, when the balls pass the paddle and "die", the number of lives decreases by one. I implemented this feature
after implemented the grid of bricks described previously. 

* My Brick class, the Ball class, the levelChooser class, and the Main class are all required to implement this feature. 
The Brick class's maxHitValue is the point value of each brick, and the brick keeps track of when it's supposed to be cleared. As a result, 
when it is cleared, the maxHitVal is added to the score. Specifically, the getHitCount and the incHitCount methods in the brick classes
are used to determine what text should be displayed for the score. The Ball class's checkIfDead and checkRectIntersect methods are used because
1 is subtracted from the total number of lives remaining if checkIfDead returns true. Also, checkRectIntersect is used to check whether or not the ball and brick shapes
intersect, increasing the hit count of the 
bricks and contributing to the display of the score. The levelChooser class's incrementLives, decrementLives, getScore, setScore, setLifeStats, and initializeText methods are 
required to implement this feature: the first two methods actually adds or subtracts from the number of lives left and lets an updated score
be displayed (lives are added when cheat key 'l' is pressed), the next two methods are in charge of helping display the accurate score, and the last
method actually adds Text objects to the root of the Scene's node to visually show us the number of lives left and the number of points accumulated. 
All methods in the Main class were in charge of continuously updating/refreshing the score to show us an accurate number of lives and points. 

* The assumptions made for this feature were: 
    * There were only 2 things that would be shown: the score and the number of lives left
        * This limits flexibility since the Text to be displayed is set as instance variables in the levelChooser class, if we wanted to add more option to what the
        status bar would display, it would be tedious to type it all out.
    * The displayed words would be Text objects
        * This limits flexibility since it would be difficult to display an object that contains text, but is not necessarily a text object
    * I feel like these assumptions do affect other parts of the program, especially since Text objects from the levelChooser class is added directly
    to the Scene's root node. If they weren't text objects, problems would arise. 
    * The design of this feature did change as it was being implemented: Originally, I added each individual Text object to the Scene's root, but then I realized that if I wanted
    to add a lot more Text objects in the future, it would not be easy to add each individual object to the root. Also, the code wouldn't be as readable. As a result, 
    I packaged all the Text objects in the levelChooser class into a Group object and passed that to the main class so it could be easily added to the root node
    without many lines of code. Implementing this features made it so that methods needed to be added to my block class and levelChooser class to take into account
    when to decrease the number of lives left. 


**Program Design**
* As stated earlier, the level is decided by the levelChooser, which has Brick, Ball, Paddle, and Text objects. The levelChooser then give the Main class the necessary information 
to display the game on the screen. The ball interacts with the bricks by checking whether or not it intersects with them and changes its direction accordingly. I chose to have the ball
control its own movement to keep my code SHY. On the other hand, the ball changes the hitCount of the brick, which is not SHY. To remedy this, I could have the brick 
also check for a collision with the ball so that it could update its own hitCount. However, this could introduce duplicated code. Once a Brick reaches its maximum hit count, the Brick
is removed from the grid and the corresponding points are added to the score. The paddle and ball interact by having the ball detect when it collides with the paddle and changes direction accordingly. Also, 
when the ball passes a certain point (beyond the paddle), the ball interacts with the Text objects to update the number of lives left. It would be 
relatively easy to add a power up that added more balls to the screen since I already have a Ball object that encapsulates its interactions with other object on the
screen. 

## Alternate Designs
* A design-related thing I wrestled with when adding new features to my program, like the grid of bricks, was how I wanted to design the brick and paddle class
so that they could interact with the ball in a similar way, even though they are pretty different objects. However, they're both rectangles that the balls collided with. 
To remedy this, I first tried making a Sprite interface so that all the other objects in the Scene would have the same methods needed/implemented in each class. Later, 
I realized how little that interface did, so I tried creating a rectangularSprite class that the Paddle and Brick could extend to help me organize my hierarchy. This would also allow 
less duplicated code among the 2 classes, and the ball wouldn't need to deal with 2 cases of similar things. However, this also did not work because I always had to extract the Rectangle object
from the class, so I tried having the Paddle and Brick class extend the Rectangle object instead. This made it a lot easier to add these objects as nodes
to a scene since I didn't need to extract the Rectangle object anymore, but I still had some duplicated code in the Ball class to deal with these 2 classes that extended the same class. I tried
using "Rectangle rect" instead of a specific object type as a parameter for some collision checking, but it didn't work. 

* As stated earlier, my code could be better at Telling the Other Guy by empowering the Brick class to increment hits own hit value is a ball collides with it instead of
having the Brick passed as an argument in one of the Ball's classes and having the Brick's hit count incremented from within a different class. My concern with this is that
there would be duplicated code for the collision checking, so what would be the best way to approach this? The Ball detects whether or not it's at the edge of the screen or if it collides with something else, and based on this 
information, it changes direction accordingly. Also, I could improve Telling the Other Guy by NOT importing static variables from the Main class.

**Design Decision 1: Having the Ball Object (Mainly) Control Its Own Behaviors**
* I'm pretty happy with this design decision. I tried to apply the "Tell the Other Guy" principle from the readings, and I think the Ball class does it pretty well. 
The Ball class internally calculates whether or not it's colliding with a paddle or brick. Based on this (checkRectIntersect), the ball changes direction accordingly. 
I took the time to plan out how I wanted the ball to behave, and I'm happy that I was able to do this small thing. However, I would want to 
try to make the Ball class have complete control over its own behavior–currently, the levelChooser class does the screen-edge detection and changes the direction of the ball
if its at the top, left, or right edge of the screen. This could be easily remedied by putting the moveBall() method's contents into the Ball class itself. 
* I'm not sure if there's assumptions or choices that have a global impact on the program. Maybe the assumption is that the Ball object always is/has a Circle
object within it/is displayed as a Circle shape? This could impact how the ball's coordinates are changed in the future. 
* An alternate design that I'm currently considering is the fix stated in the first bullet point.
* Comparison: 
    * Pros of completely encapsulating behavior of ball: 
        * Code becomes more "Tell the Other Guy" 
        * Can easily find where ball movement is controlled (first place to check would be in the Ball class, so if it's there instead of the main method, it'll
    take less time and effort to change the Ball's movement)
    * Cons: 
        * Might introduce dependencies in the Main class or duplicated code (if I don't do it correctly)
    * Pros of leaving it as is: 
        * Might have less duplicated code
        * Don't need to pass in extra values to the Ball class (ie don't need to pass in the values of the size of the scene)
    * Cons: 
        * Harder to find where all the code that controls the ball's movement is
        * Not as encapsulated
* I would prefer the fi because it would align more closely with the "Tell the Other Guy" principle and make it easy to find where all the 
ball's behavior is controlled (since it'll be where we expect it to be).

**Design Decision 2: Having the Rectangle and Paddle Classes Extend the Rectangle Class**
* I'm also pretty happy with this design decision because I tried to keep my code DRY and shows that I started to think about the hierarchy and inheritance. 
However, it was difficult to figure out how to make the classes share funcionalities (both are Rectangle objects, both need to have positions, etc) but also 
take into account their unique behaviors.
* I assumed that the Bricks and Paddles would be Rectangle objects/Rectangle shapes, so if someone wanted to make it an Image instead, it would definitely
have a global impact on the program. 
* I originally considered just copy pasting code from the Paddle class to the Brick class (no class extension) and tweaking it a little bit so that the Brick's code would be more aligned 
with the brick's behavior since I didn't completey understand how inheritance works (and I still don't really). 
* Comparison:  
    * Pros of having the Paddle and Brick Class extend the Rectangle Class: 
        * Easier to add the objects as nodes to the scene
        * Able to be accessed similarly in their classes
        * Can possible abstract away the type of object (aka can pass in a Rectangle object as a parameter instead of having to specify whether its a brick or 
        paddle for the ball's methods)
    * Cons: 
        * Harder to change/more to change if wanted to make the brick and paddle Images instead of Shapes
    * Pros of having them not extending any class: 
        * Easier to change between Shape and Image displayed in the scene
    * Cons: 
        * Possibly duplicated code
        * If want to change a behavior that applies to both (like displaying the shape/deciding bounds), need to do it in two places instead of
        one (my code does also have this in the 2 classes though, which is not good)
* I prefer the code I currently implemented because it makes adding objects to the scene so much easier.
* My code is designed the way it is to take into account a hierarchy: each level is made of Brick objects, Paddle objects, and Ball objects. The Main class displays information 
encapsulated in the levelChooser object. While I tried to keep a code hierarchy and inheritance in mind, I wish I could have made it less dependent on the structure of the data 
(ex. all have to be Shapes) so that this game can use different types of images and information. My current code organization helps to add new features since more
bricks, balls, and paddles can easily be added to the scene by adding another Brick/Paddle/Ball object.  
        

## Conclusions
* The best thing I learned during this project was how to figure things out on a deadline. I've never done a coding project on this scope, so 
starting out this project was really rought. I felt really lost and overwhelmed by all the possible things I could do. Although I didn't finish my project, this
first assignment has really motivated me to learn as much as I can about good code design and object-oriented programming. I'm really excited to see how concepts I've
learned before can be used in programs that are not APTs. It was also really rewarding to see my basic game work. 
* The worst thing I experienced during this project was feeling really overwhelmed by all the concepts and work I had to do. Since I've never done something like this before, 
I got really stuck on debugging things, and I tried to refactor things for too long. The longer I got stuck on debugging, the more frustrated and confused I felt, causing me to doubt
my abilities as a programmer. However, I'm glad that this happened, so now I'm more motivated to learn and utilize the concepts learned in class to code I write. I've never thought 
about good code design, like keeping this DRY and SHY, and while I got too caught up in small details and minor setback (definitely causing some tears
at like 4 am), I look forward to improving from this low. 
* To be a better designer in the next project, I will: 
    * Start to plan things out better. I went into this project with a half-baked plan, and halfway through, I tried to actually plan what I wanted to do. By then, it 
    was too late. As one of my interviewers said, "Projects should be 50% planning and 50% coding; any less planning will cause more headaches down the road," and I definitely felt that
    with this project. Not having a plan made me not be able to complete more than half the specs, so in the future, I would like to plan more in depth before coding. 
    Also, I want to not get too caught up in reading about how to do things and actually start coding. I would read the readings over and over and try to research the best ways to
    write something, but that wasted time from me actually trying to create the game + learn things through trial and error. 
    * I want to continue to try refactoring my code as I continue in projects. I think refactoring definitely helps further down the road–for some parts of my project, I got confused by 
    my own code when I started rushing, and I don't want that to happen again. I also want to continue thinking about the hierarchy and inheritance because it seems that it would help
    the code readability and make the code less redundant. 
    * I want to stop getting caught up in the details of implementation. Instead of researching how a breakout game should/can be made, I should have started to get
    my hands dirty with tutorials. Reading can only do so much, and I should try to actually code to figure how things can be implemented. I also want to stop getting down when I'm stuck on a 
    bug and instead adopt more effective practices, such as taking a break if I've been on one problem for too long. 
* In all, this project was a really good learning experience for me to figure out what I want to improve on and what I want to learn from this class. A lot of what I described
above is less about the technical part of the project but more about myself, and I think it's indicative of how much I think this project has made
me reflect about how I want to move forward from now.
