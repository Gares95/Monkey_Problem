!/bin/bash
clear
if [ "$#" -eq 1 ]
then 
	java -jar Monkey_Problem.jar $1
else
	java -jar Monkey_Problem.jar
fi

read