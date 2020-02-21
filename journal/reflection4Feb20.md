# Weekly Reflection
### NAME Michelle Tai (mrt36)
### DATE 2/20/2020

#### Interesting Notes

* One thing that I found interesting was how MVC was defined. In the Model-View-Controller for 
this week's reading, it defined the controller as the what takes the user input, and the user interacts
directly with the controller. In the MP3 player example. the user interacted with buttons to play songs, 
and the actions are reported directly to the controller. In our team's understanding, as we were debating
whether or not to include a controller, we thought that the controller was the means of communication
between the front-end and back-end. We thought that the controller "controlled" the flow of information 
between the front and back ends, and that the user only interacted with the front-end. I'm still unsure
on which is the "correct" view of controllers. A question was asked on Piazza about this too, but the answer
said that it depends. 
* Learning about regular expressions was interesting too. Although I've always heard of the term "regex," 
I never understood the meaning (and power) behind it until now. It's pretty amazing how its syntax is used
to help parse and modify strings in Java. I'm definitely excited to see how we will use it to parse commands
and such in our SLogo project. 


#### Summaries

* The "Introduction to Design Patterns" reading talked about how patterns can be used to solve solutions
in context. While patterns were developed to help solve common problems, they are not the panacea to everything. 
I liked how they described that there were pros and cons to every design pattern, and that we should take that into
consideration when trying to come up with solutions. I also really enjoyed the transition of architectural
design patterns to software design patterns. I wish that the reading went more in depth about the actual
software design patterns because the author's writing style was easy to understand and digest, while the 
software design patterns website is honestly hard to read. 

* Something our parser team talked extensively about was whether or not to use a controller. After learning
about bindings during class, we thought that it was a viable option and might make our code easier. We talked
about the pros and cons of having a controller and not having a controller, which is summarized below  
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
        
        
After considering the pros and cons, we thought that we would want to at least try out binding with
no controller–it seemed interesting to try and use. 

#### Reflection

##### What was difficult about your work this week and why?
Honestly, planning was the most difficult since we had to decide how we wanted to approach a lot of things. 
We spent a lot of time debating whether or not we should have a controller, and how the frontend and backend
(and maybe controller) would interact with each other. Also, learning how to create the front end components
will be difficult since I wasn't very good at it for the breakout game, and I didn't do the GUI for
our simulation project. 

##### What were the most important things you learned?
Planning a little can go a long way. Planning things out definitely gives me a better plan of how to 
tackle our project, and it was one of my goals for this project to plan things out better. 

##### How will this learning change your work next week?
I will definitely plan things out more with my team, like who will work on what and when things will be done. 
I also will reach out for help sooner if I need it. 