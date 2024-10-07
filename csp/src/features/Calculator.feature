@calculator
Feature: Calculator operations


  @Calculator 
  Scenario Outline: Calculator Validation [AO 78624, ZID 20260, AO 147397]
   # Given The Browser and Application is Open
     Then user opens the application and clears all existing values
    Then enters "<value1>" and "<value2>"
    Then user performs "<operation>" and validates "<output>"
   
   
    Examples:
    | value1 | value2 | operation | output |
    | 5      | 3      | add       | 8      |
    | 10      | 4      | sub       | 6      |
    | 10      | 2      | mul       | 20     |
    | 30      | 10      | divi       | 3      |
        