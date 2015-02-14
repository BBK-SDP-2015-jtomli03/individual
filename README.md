# individual - Simple Machine Language (SML)

#About
This is a basic interpreter for a simple machine language. The general form of a machine language instruction is;

  label instruction register-list
  
where 

  label: is the label for the line. Other instructions might "jump" to that label.
  
  instruction: is the actual instruction. In SML there are instructions for adding, multiplying, and so on, for storing and 
  retrieving integers, and for conditionally branching to other labels (like an if statement).
  
  register-list: is the list of registers that the instruction manipulates. Registers are simple, integer, storage areas in 
  computer memory. In SML there are 32 registers numbered 0 - 31.
  
The following instruction types are already coded for use;

  AddInstruction - adds the contents of two registers and stores the result in a register.
  
  SubInstruction - subtracts the contents of two registers and stores the result in a register.
  
  MulInstruction - multiplies the contents of two registers and stores the result in a register.
  
  DivInstruction - divides the contents of two registers and stores the result in a register.
  
  OutInstruction - prints the content of the specified register to the Java console (using println).
  
  LinInstruction - stores an integer into a specified register.
  
  BnzInstruction - if the contents of the specified register does not equal zero, then make the instruction with the specified 
                    label the next one to execute.
  
The program reads the instructions from a file, interprets, and executes them.

The program uses Java reflection so that the SML language can be extended without having to modify the original code.
There is commented out code for use of this project without using reflection (for marking purposes - this is an assignment :) ).

#How To Run

The file name containing the SML instructions is read from the command line. The class to run is Machine, which has the main 
method to run the program;

  java Machine "filename"

#Extending the Project using the Java Reflection Version

New instructions can be added to the SML by adding new classes that extend Instruction.

It is important that the classes are in package sml and that the class names are of the following format;

  instruction.toUpperCase().charAt(0) + instruction.substring(1) + "Instruction"
  
  where instruction = the actual instruction for the SML language, eg "add", "sub", "bnz", etc.
  
  For example, if the instruction for the SML language is "add", then the classname will be AddInstruction in package sml.
