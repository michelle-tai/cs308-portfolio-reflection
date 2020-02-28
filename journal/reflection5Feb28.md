# Weekly Reflection
### NAME Michelle Tai (mrt36)
### DATE 02/28/2000

#### Interesting Notes
* Something I've been thinking about a lot is how to make our code flexible and easy to navigate. 
Recently, when I tried to bind the turtle's image to a combobox's choice, I had a really hard time finding
where the turtle image was even stored. After this, Sanna and I suggested to have a meeting where we 
all refactor our code to make it more easy to navigate and easy to read. To make code more flexible, I've
been thinking about ways to not have to hardcode buttons, and something that was really interesting was that
I was actually lamenting the fact that you couldn't pass functions as parameters into other methods (which 
would help attaching eventhandling code to the buttons), but I now learned that lambdas do that exact thing. 
Although lambda expressions are still a bit hard to grasp, I'm really excited to learn more about how to 
use them in our code so that I can make our GUI as flexible as possible. Also, reading in the function/method
names from property files to attach to buttons (like in today's lab) was really cool, and I'm excited to also
use reflection to refactor our code and make our code design a bit better. 
* As a team, we've been working fairly effectively. I like how everyone is always willing to meet and 
always communicating about the project. For myself, I would like to be able to do more coding outside
of team meetings to make them more productive, which is what I strive to do this week for complete. However, 
that will be difficult since I have a lot of midterm projects due this week as well. Besides that, some 
difficulties we've had in the front end in making a type of observable map for our variable history so
that any updates to the variable map can automatically be displayed in the variable history box. We've
been testing out observable lists and tableviews, as well as listviews, but we're still trying to figure
that out. Also, I really want to find a way to making a type of binding interface or abstract method
that makes binding objects a lot easier and cleaner. Our current way of just searching through the 
code isn't the most efficient, but I still need to look into how we can effectively do that without
making every method and/or object public. 


#### Summaries

* I thought the readings about the lambda expressions, especially the one from "Java SE 8 For the Really Impatient"
book, were really insightful and helped me understand what lambda expressions were. The readings defined lambda expressions as
a way to use functional programming in Java, pretty much passing in a method/code functionality as a "parameter" to make
our code more efficient and readable. Before this reading, I only knew how to use lambda expressions when attaching event
handlers to buttons, but after this reading, I now know that lambda expressions can be used anywhere where you 
want to use/call the same block of code multiple times, but don't really need to create a class or object for it. 

* An interesting design choice that our team went through was whether or not to have a custom button class that served
as a superclass/interface to all the buttons in our GUI or creating a static class that had code to create a button. In all
honesty, I didn't, and still don't, fully understand the use of a static class. My understanding right now is that
you use it when you only need a single object of that type, or you want to use it to execute certain functionalities or calculate
certain values (like Integer.parseInt?). However, Himanshu chose to make a static class since a TA told him that 
would make the code less redundant. We debated the pros and cons of this design, and I ultimately combined both of our 
designs into a more superclass-like class/interface. The pros of using his design was that you only needed "one" button making
class, and you didn't need to create new instances of buttons classes. However, the cons was that the buttons couldn't be 
customized, the class had multiple roles (like creating a color picker as well as regular buttons), and was static. 
Since I thought that we would want our button to be easily changed and styled, we ended up talking about the design choice, and since
we both wanted less redundant and more readable code, but also code that made sense, we agreed to not keep the static
button class. 


#### Reflection

##### What was difficult about your work this week and why?
* Connecting the front end objects with back end information. I think a lot of what we're doing now is not
following our API design, which makes things more confusing. Also, I feel like our way of binding things
exposes our implementations of classes and is not efficient, but I'm struggling to find a better way to do it. It's hard
when we can create views like a variable history viewer, but not know how to connect it with the backend's data 
structure that holds variables and their values. Also, it's hard to design with the idea that we should make everything
as independent as possible, so that if we were to take certain components to use in other projects, it would be 
easy to just insert. However, I think that managing our dependencies and taking that into consideration has been difficult
as well. 

##### What were the most important things you learned?
* That having my own spike/tester project that I can play around with JavaFX code that could potentially 
be used in our parser is really helpful. It helps me learn by doing, and I can see the code behavior before
inserting it into our project. 
* Work outside of meetings!

##### How will this learning change your work next week?
* I will definitely work more outside of our team meetings and utilize outside resources and "playgrounds"
to test my javafx code. 
