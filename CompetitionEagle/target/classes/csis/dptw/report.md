Final Project
Spencer Caramanna, Brian Dell, Madelyn Papa and Jaclyn Wirth
CSIS225
5/5/22

1. Instructions on how to run each program. ✅
2. A statement on how each basic requirement (the list from the “Basic Requirements” section
of this document) was met, how course style guidelines were met, and how project-specific
requirements from your proposal were met. ✅
3. A brief summary of the group’s use of git and GitHub for collaboration and version control. ✅
4. A paragraph written by each group member outlining their specific contributions toward the
design, implementation, and testing of the project. ✅

Contributions
Spencer Caramanna:
I wrote code for the Java swing components, the main menu, and for the Connect Four game. I also incorporated the GridBagLayout with different GridBagConstraints to create a more seamless design of the overlay. I also wrote code for the animations. I helped calculate formulas for the ball traveling into the cup in Cup Pong and for the chip falling into place in Connect Four. I also wrote various lines of code throughout the entire project, as well as troubleshoot errors, and came up with alternative solutions. 


David Tang: 
I came up with the designs for the menus. I contributed to the Java swing component layout. Modified animations to look more realistic. Helped plan out the approach and structure for both games such as coming up with the direction meter and power bar for cup pong. I also contributed to the images used by adding them to the framework.

Brian Dell: 
I developed and formatted the class hierarchy design which includes all of the files in the engine package. This includes classes such as map, game, entity, and animation, which abstract away a great deal of the functionality used to create games with java graphics. In addition, a lot of work was put into creating the gameInput and entity classes. These classes redefine how events work in java by allowing you to add mouseAMouseListener that executes a specific function using lambda expressions and or method references. It also allows you to sort these events in order so they execute in the order you want them to, and lastly so they only execute under a given condition. Each event contains an implementation of the Event<T> function interface that is implemented using a lambda expression or method reference. The event types that are supported by gameInput are: MouseEvent, KeyEvent, ActionEvent, and PropertyChangeEvent. I also did most of the base logic behind the programs.

Madelyn Papa: 
I resolved most of the math needed for our programs, especially in Cup Pong’s ball launch, Created checking inside of Connect4 to check for when the game should conclude, and engaged in figuring out the thread-related tasks for cup removal in Cup Pong. I helped with the path that the ball is animated through and debugged errors related to this animation as well. I debugged connect4, finding combinations in which the code did not account for a correct win. I also helped debug errors in the cup pong class by finding instances of when the ball is launched that the code did not run effectively. Made images usable for the games, and menus to make sure that the design of our program looked clean.

Jaclyn Wirth:
 I implemented entity’s referring to the cups and also designed the ball. I set up the specific location for the cups and also figured out where the trajectory of the ball for landing. Specifically where on the image of the cup would count as landed for the ball using 2DArcs. I figured out the Animation for when the cup leaves the window. I also completed the Cup class. I also contributed to the design of the CupPong background which was in pongMap class. I contributed to figuring out how to add images and resize them in the window. 

Basic Requirements
• A meaningful object-oriented design, making effective use of interfaces, abstract classes,
inheritance, etc.
The hierarchy consists of Animation, Game, Entity, Map, Repainter, event, Player, Paint Helper
The game Cup Pong and Connect4 extends the Game class. The classes that extend Entity are Cup, Ball, Chip, Meter, and Bar.
Game extends GameInput
Animation has Direction Meter, Power Bar, Cup Animation, Ball Animation, and Chip Falling
PongMap and ConnectBoard extends Map
Connect Player extends Player
Event Function, and Event Restriction are functional interfaces. 
In the animation class, we have the abstract class animation
• Event-driven programming including Java graphics and Java Swing GUI components.
Grid Layout with Buttons, labels, and images inside the menu, Keyboard listeners to play the game
• Threads for animation or computational speedup.
Chip falling, cups moving, direction meter, power bar, and ball motion
• Appropriate use of data structures.
2D Matrix, functional interfaces, generic types, enumerated types, abstract classes, priority queues/heaps and lambda expressions, arrayLists, linkedLists
• At least one Java or general programming feature or construct that we have not specifically
studied in this or prerequisite classes.
lambda expressions, transformations, grid bag constraints, clipping, transformations and maven


Collaboration
Our group’s meetings were always in person and most of the time we worked on it one computer at a time, switching between users, while everyone else followed along and looked at a different section on their own computer. As we worked on specific parts of the project, we tried figuring out how to approach a different section whether it was figuring out calculations, or designing the layout of Swing Components or the logic. Most of the time our commits to Github are to give the code to other members so we can physically view it and run the code (the laboratory’s vscode glitched countless times). Ultimately, our group created an atmosphere of hardwork and nonstop advancement on our project.


Description
Running our games is quite simple, as we designed our menus to be quite user-friendly. In order to access each game; Connect4 and Cup Pong within the Competition Eagle™ main menu, just click on the specific icon that clearly represents the game. A game chip representing Connect4 and a red solo cup in place for Cup Pong. After that has been performed, the game immediately starts running. Start the new game and this will allow for the entertainment to fully begin! 
The program Connect4 consists of two players facing head to head in a duel where the players race to get to four chips in a consecutive line, being anywhere from diagonal to horizontal order. When this occurs that player will be deemed the winner. There can be a possibility for a tie where no four chips are consecutive. In order to actually play the game, the players must use the arrow keys (left and right arrows) to position the chip to their liking. When the placement has been decided, drop the chip by hitting the enter key. There are also two buttons that are shown on the screen being, the restart game, and the main menu director. 
Our group's variation of Cup Pong showcases a single-player straightforward cup pong simulation where the aim is to, quite frankly, aim, and get the ball into the cups. When the ball goes into a cup, it is removed. The game is finished when all the cups are removed. The system of tossing the ball is a unique technique consisting of a direction meter and a power bar. The direction meter repeatedly swings back and forth from left to right until the user presses the spacebar button on their keyboard. This sets the direction of trajectory for the shot. Once that has been completed a bar appears. This bar represents the power of how far the user desires the ball to travel, but don’t be fooled, setting the power to full doesn’t guarantee it’ll go into a cup! 
	Our goal for this project is to create fun games to enjoy either by yourself or with a friend. Our selection of these two games is due to the developers’ favorite games being Cup Pong and Connect 4 in iMessage. We are very delighted to put forth these ideas and share our creation with the world. 
