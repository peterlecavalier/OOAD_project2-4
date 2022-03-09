Team members:
Elizabeth Shan
Peter Lecavalier 

java version: 17.0.2

Comments / assumptions:
- With two stores now, one store fully runs and prints summary, and then the second store follows.
- To handle the shared pool of staff with 2 stores, we added an extra level of delegation with Company class.
    -> We modified our original code to fit this new format.
- Now that there are up to 2 clerks that can be sick, we made this design decision:
    -> 10% chance of one getting sick
    -> 50% chance of a second one getting sick GIVEN that one was sick.
- With command-line interactions, we assumed the buying/selling of an item could be randomly selected (just like with bot customers)



to compile/run in cmd (from the root of this repo):
javac -d bin src\OOAD_project4\*.java
java -cp ./bin OOAD_project4.Main