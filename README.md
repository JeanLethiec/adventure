# Adventure

Welcome! 
This file will explain how to use the related program, where adventurers will journey through a map full of treasures awaiting them !

## Dependencies 

This program has a single external dependency: **LOG4J 2.11.2**

## Running the program

This program has been written for **Java 8 Update 201**, thus a JRE of this minimal level is mandatory.

It expects an **input file** detailing the initial setup of the adventure: how the map is structured (size, location of mountains and treasures), and where the different adventurers are.

We suggest to run the program using its JAR file:
* java -jar **Adventure.jar** *<path_to_input_file>*

However, it is still possible to run the program using its *.class* files directly.
In both cases, make sure to set your CLASSPATH environment accordingly, so that the program is able to correctly resolve its dependencies.
	
After its execution, an **output.txt** file will be written in the current directory, describing the final state of the adventure.

## Input and output format

Please refer to *Specifications.pdf* (in french) located next to this README file.

## Logging

Logging capacities haven been implemented for this program.
If needed, you can modify the logging verbose level using a LOG4J properties file located in your CLASSPATH.

The default logging level of LOG4J is ERROR.

An example **log4j2-test.properties** is proposed next to this documentation, and can be modified freely.
The *rootLogger.level* property manages the logging level.


## Automated tests

Several JUnit automated tests have been written to check this program consistency, in package *com.adventure.tst*.
They expect the JUnit dependency to be added.

## Files and directories

* *Adventure.jar*: Runnable Java ARchiven entry point of the program.
* */bin*: Contains all Java compiled .class files.
* */src*: Contains all Java source .java files.
* */dependencies*: Contains all .jar dependencies: LOG4J and JUnit
* *input.txt*: Example input file.
* *output.txt*: Example output file.
* *README.md*: This documentation file.
* *log4j2-test.properties*: Example LOG4J configuration file.
* *Specifications.pdf*: Program specifications, in french.