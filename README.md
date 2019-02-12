# Adventure
Welcome! 
This file will explain how to use the related program, where adventurers will journey through a map full of treasures awaiting them !

# Dependencies 

This program has a single external dependency: LOG4J 1.2.17

# Running the program

This program has been written for **Java 8 Update 201**, thus a minimal JRE of this level is mandatory.

It expects an **input file** detailing the initial setup of the grid: how is the map structured (size, location of mountains and treasures), and where are the different adventurers.

The program can be ran two ways:
1. With its .jar:
	* java -jar -classpath *<location_of_the_dependencies>*/* Adventure.jar *<path_to_input_file>*
2. With its .class
	* java -classpath *<location_of_the_dependencies>*/* com.adventure.Adventure *<path_to_input_file>*

After its execution, an output file will be written in the current directory, called **output.txt**.

# Input and output format

Please refer to the *Exercice pratique - La carte aux trésors - 20170805.pdf* document located next to this README file.

# Logging

Loggins capacities haven been implemented for this program.
If needed, you can modify the logging verbose level using a log4j.properties file, using this argument to java on the command-line:
* -Dlog4j.configurationFile=*</path_to_log4j.properties>*

# Automated tests

Several JUnit automated tests have been written to check this program consistency, in package com.adventure.tst.
They expect the JUnit dependency to be added.