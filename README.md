# Calculator

This calculator version simulate Apple's calculator but a more simple one. 

## Data Structure and Tools used:
- Tools: JavaFX and CSS
- Data Structure: 
  + CalNumber: StringProperty - to bind the result with the change user made
  + List of Numbers and contractors: Array

## Implementation:
- Calculator.java: main operation of calculator, including the user interface
- MyButton: the super class of buttons, including numbers, operators and clear button
  + SignButton: Plus, Subtract, Multiply, Divide and Equal
  + Number: from 0-9
- CalNumber: The number shown on the screen for operations
  
## Future improvements:
- Apply stack for the parentheses to prioritize operations in the parentheses
- Optimize code
