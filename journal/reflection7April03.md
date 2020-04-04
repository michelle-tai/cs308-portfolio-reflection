# Weekly Reflection
### NAME: Michelle Tai (mrt36)
### DATE: 04/03/2020

#### Interesting Notes
* I didn't know how exactly Pac-Man was played until we started brainstorming this game. 
* I never really worked on the backend until this project, but I'm starting to enjoy it too. 
* I thought the reading that talked about "Teaching Design Patterns through Game Development" was
a really insightful reading, especially since it talked about Pac-Man! 
  * Maybe using the visitor pattern for collision checking
  * State pattern for the different states of each sprite


#### Summaries

* Visitor Pattern: used when we have to perform an operation on a group of similar objects; puts
logic in a different class (https://www.geeksforgeeks.org/visitor-design-pattern/)
  * Pros: 
    * logic of operation changes, then only need to make change in visitor implementation rather 
    than doing it in all the classes
    * Adding a new item to the system is easy, it will require change only in 
    visitor interface and implementation and existing item classes will not be affected
  * Cons: 
    * Need to know return type of visit
    * Not closed
    * If too many implementations of visitor interface, makes it hard to extend
  * I've been thinking about using this for collisions, so the
    * Pros: 
      * All collision logic for each sprite is contained in its own Visitor class
      * Makes other parts of code more flexible
    * Cons: 
      * Might not entirely make sense since need to know the other sprite's visitor class
      to do visitor.visit(this), so a .getVisitor method will be needed in the Sprite interface, 
      * Coins might not necessarily need it
      * Each added sprite or collision check requires changing each visitor class and the interface
    * alternatives: some sort of lookup table? So if it's a sprite pair, maybe put all collision types
    for each sprite pair into a map, so the key is the pair and the value is the collision method 
    name, so then use reflection?
      * would need to call 2 times though since both sprites will have different collision behavior
 * State pattern: having the states be objects that are swapped within a class, so objects can 
 alter behavior when internal state changes
  * Pros: 
    * Single responsibility 
    * Delegates responsibilities
    * Seems like good design
  * Cons: 
    * Need to find a way to transition states without using if statements, since that would
    be overkill; maybe have the states be in the collision behavior?
  * could also send a message to the collided with sprite? like is pacman collides with wall, wall 
  sends message to pacman like collided with math or passes in a method or idk
  * https://gamedev.stackexchange.com/questions/137344/pattern-for-collision-handling
   

#### Reflection

##### What was difficult about your work this week and why?
* Doing the work itself and planning since we weren't able to meet physically. Also, 
my team has made my work more difficult since it seems like they don't especially care about 
doing well, even if we're all taking it pass/fail. I feel like they don't care about design, but
I also don't know how to tell them that we probably need to do semi-well at LEAST to pass
* Coronavirus
* Seeing the increase in hate crimes against Asian-Americans and Asians honestly makes me 
kinda sad
* Life is tough man

##### What were the most important things you learned?
* Life is tough, but life keeps going
* Design patterns

##### How will this learning change your work next week?
* idk
* Think more about the logic behind design patterns and use them in our code