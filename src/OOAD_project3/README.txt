Team members:
Elizabeth Shan
Peter Lecavalier 

java version: 17.0.2

Comments / assumptions:
- Using/assuming a hashmap to carry out the functions in observer
- observer is an abstract class and tracker + logger are classes that extend the observer
- decorator decorates the stringed sale in packageditem
- Package the entire sale into one function for increased abstraction and ease in implementing decorator


to compile/run in cmd:
javac -d bin src\OOAD_project3\*.java
java -cp ./bin OOAD_project3.Main > output.txt