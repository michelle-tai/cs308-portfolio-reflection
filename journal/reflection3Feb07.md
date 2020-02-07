# Weekly Reflection
### NAME: Michelle Tai
### DATE: 02/07/2020

#### Interesting Notes
* I really liked the "Why Java Interfaces Are So Very Cool" reading. I've always been confused about the 
difference between interfaces and (abstract) classes, and the reading helped clear it up. Before, regardless of
what I googled, the explanations were fairly lackluster and confused me even more. This reading helped me learn that 
interfaces are more like "contracts," and every class that implements an interface needs to implement the 
methods specified by the interface. On the other hand, a class more or less defines how the object will do things/
the role the object plays in the grand scheme of the code. Also, I thought it was interesting how a class could
only extend 1 method, but implement multiple interfaces. I guess it makes sense since each object/class should
have a defined role, and trying to extend more than 2 class means you may be assigning too much work to the one class. 
This reading has really helped me think about how I would want to use interfaces in my code, such as writing a cell interface. 
All cells need to update neighbors, know its positions, and know its current and next state, but based on the neighbor type
and simulation, the way the update and the state types are can change. However, all cell types have the same basic "behaviors," which
is why I'm considering making a cell interface and have different simulations' cells implement the interface. 

* It's been really interesting to learn how to integrate different people's code together. Most of the time, when 
we're trying to merge our branches and our code together, we have to meet since it's so difficult and we get so many
merge conflicts. Despite the difficulties and the long (and often late) meetings, I'm really proud of how far we've gotten. 
Our code definitely isn't perfect and still has a lot of design flaws, but we were able to somehow connect everything and have 
an interactive simulation. Franklin and I have pair-programmed in a sense to get the "step" to work, but
Lucy took like 10 seconds to fix a problem Franklin and I were stuck on for hours. I really appreciate being able
to work with such talented people and learn alongside them. For me, I was really intimidated by the scope of the project, 
but being able to split the work but also work on different parts with the team really helped me learn how to write better code. 


#### Summaries

* I honestly thought that the "Designing with Exceptions" reading wasn't as helpful as its companion article "Exceptions
in Java." The "Designing with Exceptions" article felt very nuanced, and the specific examples weren't very heplful. On the 
other hand, the "Exceptions in Java" article helped clarify what counted as an exception, different kinds of exceptions, throwing
and catching exceptions, and gave me an overall view of how exceptions work. I also didn't know that we could
create our own exceptions! I've always thought of exceptions and the red text on the Intellij console as intimidating
and bad, but I now know how helpful it is and how/why the stack trace is printed. After seeing so many NullPointerExceptions
during this project, I can know confidently say that they are unchecked exceptions. This reading will definitely help my 
responsibility with coding the configuration and XML information since we'll need to recognize when an incorect file
is selected and how to deal with it correctly. 

* A design decision we made and have thought about for a while (and are still thinking about) is whether or not
the cells should know its shape and how much the cell should know. Originally, we decided to have each cell know it's shape so it
would be easier to calculate its neighbors, but very recently learned that the model (and therefore the cell) should not control
the look of the cell: no color, no shape, no anything. As a result, our idea to have every shape cell extend a Cell class
was scrapped. Now, we're trying to figure out how exactly to separate the model and the view. Our code has a lot
of dependencies and passes a lot of information/objects around, and my goal is to learn how to make each class more 
active and understand how to do it. For our idea of the cell now, the cell will know if position in the grid, the current state, the
next state, and its neighbors. The grid will update the cell based on the simulation's rules, and the view will 
render the shapes of each cell based on teh xml file. As a result, the view only needs to know the grid of the cells
and the statues of each cell, but not necessarily how it got to that state. 


#### Reflection

##### What was difficult about your work this week and why?
* Something that was really difficult was figuring out how to connect the model and view in a way that promoted
clean code. Although we currently have our model and view connected, we pass around our grid and don't follow good design principles. 
I hope to change it in the coming few days. 
* Resolving merge conflicts was also difficult since we often has a few conflicts with each merge. It's hard
to not work on different files and files that teammates are working on since our code isn't design very well, so 
a lot of code depends on other parts of the code. 
* For me, I've been struggling with how to write classes that are active and writing clean code. I don't refactor often enough, which
leads me to work with really confusing code that I struggle to understand and actually refactor later on. 
* Something I need to get used to is making design decisions. I've been getting more into it by listing out pros and 
cons of approaches and talking with my teammates about it, but often when there are a lot of ways to tackle a problem, 
I freeze. It's been difficult to overcome the initial fear of the big projects and the uncertainty about how things will work
out, but I think that will get better with experience. 
* Trying to implement the new features :') Our code isn't very flexible

##### What were the most important things you learned?
* Merge!! I put my merge requests off for a while since I do all my work in 1 branch (sometimes I should work on things
in different branches for different features), which ends up creating a lot of merge conflicts since the master branch would have
been updated a few times. As a result, our team learned how to deal with these problem and how to rebase and stash changes to help
our workflow. 
* Make meaningful commit messages. I was looking for a change I made in a commit, but it was really difficult since
my commit messages were not clear. I often commit without an actual "purpose," or just commit when a functionality isn't completed. I'm honestly not
sure if that's bad since I feel liked my changes won't be "saved" if I don't commit often, but I realize that I should
write more meaningful commit messages to help others (and myself) understand what exactly was changed. 

##### How will this learning change your work next week?
* Try to refactor more often and think through the flexibility of code earlier on. Doing so will make new
features easier to implement and allow the program to be extended in different ways. 
* I will write more meaningful commit messages and merge when a feature is done in oppose to putting it off. 