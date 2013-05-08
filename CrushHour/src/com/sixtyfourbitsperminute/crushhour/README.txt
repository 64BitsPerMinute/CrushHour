We decided to add this text file so that the graders would have some small explanation of the way our program 
works. The code we wrote has been JavaDoc'd, as to explain each of the methods, but we decided having a quick 
overall explanation wouldn't hurt either. The program is written with a breadth first search algorithm that 
returns the shortest path to a solution. It was written in Java with the help of an OSC Library to talk to the 
GUI, which was written in a program called Max MSP. Essentially what happens is the Java program must be run 
alongside the Max GUI. When both programs are running, the user can select a puzzle (from 0 to 50), or input 
their own, subject to a set of rules. The GUI then hands a string containing the grid over to the Java program 
which runs it through the algorithm and hands the solved Grid and a number of steps back. The user can then 
scroll through the steps while the two programs communicate back and forth. 

If you're a TA about to test this. You should run the Java program first, and then use the Max MSP GUI. Otherwise, 
nothing will happen. 