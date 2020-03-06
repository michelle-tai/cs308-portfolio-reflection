# Weekly Reflection
### NAME Michelle Tai (mrt36)
### DATE 03/05/2020

#### Interesting Notes
* Seeing the complete for SLogo wasn't too shocking–we definitely anticipated having more than 
one turtle on the screen. However, I'm really grateful and shocked by how well my teams works, and 
how well we have been working together. We all ty to keep each other on the same page and recap
the parts that we have done so that everyone can understand what's going on. 

* Reading about design, like SOLID and design patterns, while helpful to know and interesting 
to learn, isn't helping me much as I code. I'm not sure how to use a lot of the things I've read
about in the code that I write, and I don't know when I ever will. However, I do feel like I have a better
understanding on how to create more structure and flexibility in our program. Something I'm really
proud of was recognizing that we could refactor a part of our code, the History View part of the visualizer, 
to use what we learned about reflection and using property files for buttons to make our creation of buttons 
and their setOnAction methods more flexibly designed. I've been thinking a lot about refactoring since
I really do want to learn how to writ good code, but I often find myself really stuck between just writing 
something that works or trying to make the most flexible code possible. After reading some of the 
readings assigned for this week, like the "Big Ball of Mud" one, I realized that spending too much 
time on thinkabout about scalable and flexbile design can be detrimental if you never need to make your
program have those extra features in the first place. I just need to find my balane between writing whatever
works down and being scared that the code I write will be really STUPID and break things. I'm still
learning to be more confident in my abilites as a programmer, but I'm getting there. This project has
definitely helped, and I realize that I do enjoy frontend more than backend. 

* We've strayed pretty off track from our original API design, but was there a way for us to anticipate
how everything will work together without coding it out? Looking at the number of public methods we currently have, 
I'm wondering if there was a way for us to anticaipte needing this many of them. Would our code have been
better if we used a controller instead of bindings? I have so many questions, but no answers. 

* Something interesting that we did, which isn't good practice, is use static classes to hold our data for us. 
Our static classes that held our variable hashmap and command hashmap acted pretty much like global variables. 
I didn't really know what static classes did, and what the purpose of the static classes were, but after
going through creating these static classes, finding out that we're using them in a way that is 
not good, and thinking about how to refactor all of our code so that the static class won't be used
has definitely imprinted into me that static classes shouldn't hold any data. 

* I'm still reconciling with feeling like I don't contribute enough. Although I did work on the command line, 
the variable history view, parts of the turtle grid and toolbar, and refactored code, I feel like I didn't 
cotnribute as much as my teammates. I don't know if my feelings are accurate, but I always feel like I'm not
doing enough. I don't know how they're so on top of everything, while I had so many big projects due this week. 
I really do appreciate how helpful and kind they have been though. I guess seeing Sanna and Thomas work 
on the parsing and getting that working makes my work in the front end seem less impressive :') but it'll be okay!
 
* Something I'm starting to keep in mind more is "how can I take this part of my code and put it somewhere else"
to force myself to think more about dependencies and flexible code. 

#### Summaries
* I enjoyed the "From STUPID to SOLID code" reading. It talked how STUPID code is characterized by: 
  * Singleton
  * Tight Coupling
  * Untestability
  * Premature Optimization
  * Indescriptive Naming
  * Duplication  
all of which I have seen throughout our SLogo project. We used the Singleton (anti-)pattern when creating our
static classes for the variable hashmap and command hashmap, indescriptive naming for a lot of methods and variable names, 
which I helped refactor to make more meaningful, and duplicated code among all of our history views and 
buttons.  
However, we soon made our code more SOLID: 
  * Single Responsibility Principle
  * Open/Closed Principle
  * Liskov Substitution Principle
  * Interface Segregation Principle
  * Dependency Inversion Principle  
  by refactoring classes so that they only had one responsibility, create interfaces, and create a a factory-like
  pattern for our commands, which is similar to LSP
* A reading I found pretty interesting, which wasn't assigned, is from this link: 
https://isocpp.org/wiki/faq/proper-inheritance#circle-ellipse. I was, and still kinda am, confused about when 
to use superclasses vs interfaces, and when certain things that logically should be related should be 
related in code. This document helped clarify that when extending from a superclass, you don't usually 
want to add a lot of other functionality in your subclass to maintain flexibility. In the example 
of whether the circle and ellipse be completely separate classes or extending the same base class, but would mess with 
expected behavior and bring bugs into the code since you can't set different widths and heights for circles as you 
can for ellipses. To solve this problem, the reading suggests to do one of the following: 
ee ways to fix this problem:
  * Soften the promises made by setSize(x,y) in base class Ellipse, or perhaps remove that method completely, at the risk of breaking existing code that calls setSize(x,y)
  * Strengthen the promises made by setSize(x,y) in the derived class Circle, which really means allowing a Circle to have a different height than width — an asymmetrical circle
  * Drop the inheritance relationship, possibly getting rid of class Circle completely (in which case circleness would simply be a temporary state of an Ellipse rather than a permanent constraint on the object)  
  Seeing an example like this and possible solutions helped me understand how to approach problems with when our practical 
  logic doesn't work as expected in code, and how we can our code to accomodate for it. 
  
  
#### Reflection

##### What was difficult about your work this week and why?
* I had a lot due this week-midterm projects for multiple classes, as well as this project. I've also
had big events some of my organizations, so it's been hard balancing all my workd. Since I always prioritize this
class, my other work starts to build up, and ends up in a constant state of stress. 
* Understand the binding and where to find the certain things to add listeners to and bindings too. This might be
due to our code design, which we plan on improving on/refactoring. Our toolbar is especiialy large, and I've
been advicoating for a big refactor of that as a team. 
* Also, as I type this, I'm on the bus home for spring break. I tried really hard to do as much as a I could
and meet with the team as often as possible since I wouldn't be there tomorrow to physcially work on the assignment
before they turned it in, but I will definitely contribute remotely. 

##### What were the most important things you learned?
* Learn time management and discripline so I can get all work done + actually sleep before the sun rises
* Keep open communication with your team 
* Things are tough, but everything's a learning process. I might be learning more slowly than others, but
thinking back to how I was a month or two ago, I'm surprised by how much we've learned in such a short period of time. 

##### How will this learning change your work next week?
* Keep communicating!
* Plan things out more + time manage better