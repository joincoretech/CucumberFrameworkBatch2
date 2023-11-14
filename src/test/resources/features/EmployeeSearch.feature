Feature: Employee Search

  Background:
    Given user login as admin
    And  user navigate to employee view page

    @smoke
  Scenario: search an employee by id
    When user add employee id in the search box
    And  user click the submit button
    Then user validate the employee is visible in the table


  Scenario: search an employee by name
    When user add employee name in the search box
    And  user click the submit button
    Then user validate the employee is visible in the table

  @example
 Scenario Outline: search with multiple data
   When user add employee "<data>"
   And  user click the submit button
   Then user validate the employee is visible in the table

   Examples:
       |  data |
       |  105  |
       |  Aziz |

    @db
    Scenario: validate data of frontend and backend
      When user get data from back end
      And  user get data from employee view page
      Then suer verify frontend data with backend