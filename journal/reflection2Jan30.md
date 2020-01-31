# Weekly Reflection
### NAME: Michelle Tai
### DATE: 01/30/2020

#### Interesting Notes

* Something found really interested was generics. I finally understood the syntax behind things like 
List<Integer> list = new ArrayList<>(). It was really interesting to learn that generics can pretty much
take any type of data/object, which helps us make our code really flexible. 
* Enums were also really interesting to learn about. I've heard of them before, and they sometimes showed up in 
technical interviews, but I never knew what they really were. They act like classes, but with a limited number of 
"implementations"/"types" in a sense? For examples, for planets, we can instantiate any of the 8 planets, and there's only
going to be 8 planets (for now...)

#### Summaries
* The open-close principle is the principle that classes should be be open to extension but closed to modification. 
This is to ensure that any changes to the code are only additions, so nothing can break any current code. I 
guess it can help backward compatability too. I originally though it was to have code be "closed" to outside objects
in the sense that each object should control its own behavior, I realize now that it's more encapsulation.

* I really liked the reading about the 4 pillars of object-oriented programming. I thought that it 
was really cute how they used the acronym "APIE" to help remember the pillars: abstraction, polymorphism, 
inheritance, and encapsulation. Abstraction is kinda like hiding the inner workings of something and only 
returning an intuitive way to handle the object. In the example of the car, the engine, motor, and other small parts
of the car that work together to make the car work are abstracted away, letting us make this car work by knowing
only how to start the car, use the pedals, and turn the wheel. Polymorphism is like how an object can have many forms, like
how there's different car brands. Inheritance is when a sub class/object can inherit the properties of its parent, 
like genetics. Encapsulation is similar to abstraction, but its more with packaging data I think?


#### Reflection
* I originally thought that this assignment would be really hard, and while it's proving to be difficult, 
things are going more smoothly then I expected. We'll see how I feel about this Monday night though...

##### What was difficult about your work this week and why?
* I thought planning how we wanted to approach the Cell Society project was pretty difficult. There were
a lot of parts we needed to take into consideration, and I didn't realize the magnitude of the project 
until we started planning (which was a little late). I hope that I'll learn to start planning earlier next 
time. 
* I also thought trying to figure out the method signatures and how information will be passed around was difficult,
but necessary. While we didn't plan this out perfectly, I think it definitely helped me break down the task at
hand into more manageable chunks and helped me feel less overwhelmed about the scope of the project. 
* Sometimes, it's hard to know what sort of ideas are right. Since there's so many different ways to implement things, 
there's often different ideas that kind of clash. For example, I thought that the cell should keep track of its 
neighbors, but Lucy initially thought that a manager class could keep track of the neighbors. In the end, we did have each
cell keep track of its own neighbors, but there were times like this when I was confused on what was best to do. 
* Sometimes during planning, we get pretty caught up in how we would actually code it instead of how the overall
method/class would work. I hope that we can spend more time on thinking about things like that in the future!
* Something else that's also pretty difficult is figuring out the rules of the game. We made a lot of assumptions, 
like a grid is percolated only if there's percolated cells in both the left-most columns and right-most column. There isnt'
one consensus on how these simulations are implemented, and while I understand that in the real world, we won't
always have all the information to do our work, I wish there was a little more clarification/direction on what the rules of simulation are. 
Since wator seems like such a difficult simulation, I could hypothetically choose a really easy predator-prey 
simulation since the criteria doesn't specify the Wator simulation itself? I feel like a lot of the time, things are
vague and I'm blindly groping around in the dark. 
* Trying to figure out how to pass the information from an xml file to the rest of the simulation is proving 
to be difficult. I'm able to modify the class example to take an xml file that I made for Game of Life, but I don't know
how to pass that information to the rest of the program instead of keeping it in the dialogue box that was initially
programmed. 
* I'm also starting wator after doing the logic for percolation, but as I stated earlier, it seems pretty difficult. I'm a little
lost on it + the rules, so I hope I can find more clarification online. 
* Finding times to meet is proving to be difficult as well, but we're making it work!
* Git is also hard to manage at times

##### What were the most important things you learned?
* Communication is essential!! Keep code DRY and SHY, and follow the open-close principle.
* I'm definitely starting to learn how to think about flexibility of code and manageability for future extension
* I should try to do a little bit of work each day instead of doing them in huge chunks. 
##### How will this learning change your work next week?
* I will spread my work out more evenly
* I will try to initiate more communication and continue to think about flexibility