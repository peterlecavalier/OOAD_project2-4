Team members:
Elizabeth Shan
Peter Lecavalier 

java version: 17.0.2

Interpretations + assumptions: 
- We assumed we could randomly generate the names of items.
- We approached this problem with the assumption that items contained all the items and subclasses,
and other classes may (or may not) have subclasses. 
- We wanted a clerk class to carry out all of the actions such as open store, check register, etc. 
- A cash register class would also be useful for storing
the money amount along with carrying out functions that handle this amount.
- We assumed clerks are the only type of staff. Based off of this assumption, we did not include a staff interface (which was in our original UML).

UML changes:
Not much changed with the UML Diagram. We kept the same objects/functionalities that were in our 
original diagram, but just updated the methods/variables with anything additional we needed to add 
with functionality. All inheritance was kept the same, but some interactions were added in the diagram 
to keep up-to-date with how we actually implemented (note the links between Customer/Clerk, and others 
in the bottom portion of the diagram). Since clerks are the only type of staff, we removed the Staff interface.