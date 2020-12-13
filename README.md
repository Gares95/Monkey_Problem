# Monkey_Problem
This repository includes the files necessary to implement a Java program which will serve as a concurrency example.

The main objective of the program is to perform an efficient concurrency program where the instances of the class _Monkey_ have to share an instance of the class _Rope_ to cross a canyon.

The program allows a command line argument to select the number of instances of the class Monkey to create and start (there will be 8 by default).
Example: `java Monkey_Problem 3` will start the program creating and running only 3 instances of the class _Monkey_.

# Program Files
The first version of this repository contains the java classes: 
- Main.java: Main function to create the instances of the different classes and to start the Threads that will run during this concurrency exercise. 
- Rope.java: Rope class that contains the methods to cross the Rope. It also contains the conditions and functions to handle concurrency.
- Monkey.java: The  Monkey class that implements Runnable will perform different actions using the methods of the class Rope.
- Canyon.java: Auxiliary class to visualize the threads' (monkeys) state.

The repository contains a **Script** to test the program.

# Description of the concurrency example
We want to know if African monkeys can be taught about deadlocks. She locates a deep canyon and
fastens a rope across it, so the monkeys can cross hand-over-hand.
Passage along the rope follows these rules:
- Several monkeys can cross at the same time, provided that they are all going in the same
direction.
- If eastward moving and westward moving monkeys ever get onto the rope at the same time, a
deadlock will result (the monkeys will get stuck in the middle) because it is impossible for one
monkey to climb over another one while suspended over the canyon.
- If a monkey wants to cross the canyon, he must check to see that no other monkey is currently
crossing in the opposite direction.
- Your solution should avoid starvation. When a monkey that wants to cross to the east arrives at
the rope and finds monkeys crossing to the west, the monkey waits until the rope in empty, but
no more westward moving monkeys are allowed to start until at least one monkey has crossed
the other way.
For this exercise, you are to write a program to simulate activity for this canyon crossing problem:
- Simulate each monkey as a separate process.
- Altogether, a lot of monkeys, whatever you want, will cross the canyon, with a random number
generator specifying whether they are eastward moving or westward moving.
- Use a random number generator, so the time between monkey arrivals is between 1 and 8
seconds.
- Each monkey takes 1 second to get on the rope. (That is, the minimum inter-monkey spacing is
1 second.)
- All monkeys travel at the same speed. Each traversal takes exactly 4 seconds, after the monkey
is on the rope.

# Approach
The approach adopted for this problem was using _intrinsic locks_ or _Monitors_ to enforce the exclusive access to the instance _Rope_ and to guarantee that the intances of the class _Monkey_ follow the requirements indicated in the description of the problem (considering the order of execution for each Thread and their interruptions) as well as the proper function of the concurrency program avoiding _Dead Locks_ and _Starvation_.

Being _Threads_ the basic unit of execution for concurrent programming in the Java programming language the instances of the class Monkey are _Runnable objects_ (Implements Runnable). Here you can find my other repository for this same exercise in Scala: https://github.com/Gares95/Monkey_Problem_Scala

# Diagram
Here you can see a Diagram representing the _Guarded Block_ implemented in the program with the conditions in the class _Rope_.

![alt text](https://raw.githubusercontent.com/Gares95/Monkey_Problem/master/Img/Diagram.png)

# Example of result
Here you can see an example of the output obtained using this concurrency program.

![alt text](https://raw.githubusercontent.com/Gares95/Monkey_Problem/master/Img/Result.PNG)