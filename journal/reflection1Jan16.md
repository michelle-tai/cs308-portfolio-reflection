# Weekly Reflection
### NAME: Michelle Tai (mrt36)
### DATE: 01/16/2020

#### Interesting Notes

##### Keep it DRY, Shy, and Tell the Other Guy
I thought that this reading was really interesting because I never thought about what sort of code
is "good" code. I've definitely run into times when I couldn't understand what the code was 
supposed to do, but this will help me write better code in the future. The acronym is kinda catchy, so
I won't be forgetting it anytime soon.

##### Refactoring
Refactoring is a concept I didn't know until this week either. I actually encountered it in an interview
a week into classes, but I didn't know what it was by then. However, I understood that it was some type of
optimization in a sense. The hangman lab was interesting since I got to see how refactoring can be done, especially
in IntelliJ, but the lab was also confusing as well. How far can refactoring go? Beyond methods, to classes, to interfaces, 
and beyond? 


#### Summaries

##### Thing #1
I got tips on how to create a breakout game in JavaFX from this website, http://zetcode.com/tutorials/javagamestutorial/breakout/, 
which helped me learn how to organize the classes and how to make objects. It made a sprite superclass, and I did that in 
the beginning, but I'm switching to making an interface now because I want the objects to have the same methods, but return 
different things for the methods (like getX() for a rectangle is different than getCenterX() for a circle, even though both return
a reference x coordinate).I think the interface is going to be easier since I was trying to implement/override the methods of the
sprite superclass to fit different shape's needs, but after realizing that I couldn't use the same method name as the superclass's, 
I ended up with a lot of similar methods that extended the same Sprite class that returned similar things, just had different ways of
finding them because of different shapes. 

##### Thing #2
One of the readings that I always keep in mind when writing code for the game is "Bad Smells in Code." It gave a really good overview
of when refactoring is necessary, like when there's duplicated code, long methods, large classes, etc, which helps me think more about 
object-oriented programming. I'm finally learning how to actually write programs using OOP, programs that can do cool things 
instead of writing algorithms, and I'm excited to learn more.

#### Reflection

##### What was difficult about your work this week and why?
I felt like I was thrown into the deep end of the pool–I didn't really know where to start with this project, even with the 
lab bounce example we had. Since we didn't really cover a lot in class, I struggled to complete portions of code within reasonable time, 
which is frustrating. 

##### What were the most important things you learned?
Just write anything and start from there. I took the first few days reading things, hoping it would clarify my confusion, but it didn't. 
Also, start earlier (this is going to be hard since I have a lot going on this month, but I'm going to strive to start projects the day we
get them.)

##### How will this learning change your work next week?
I'll ideally be less stressed and be able to have the project done earlier. Next week, I'm going to dive right into assignment and try to get
my feet wet instead of trying to understand everything before writing a single line of code. 