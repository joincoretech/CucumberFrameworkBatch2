Feature: Joincoretech HRMS api work flow


  Background: create token
    Given token is generated

    @ApiWorkFlow
    Scenario: create an employee
      Given prepare a request to add employee
      When  a post call is made to create employee
      Then  validate the status code is 200
      And  validate the message "msg" and value "Employee added"
      And  add the employee id value to an variable "11"


   @ApiWorkFlow
      Scenario: retrieve all the employee
        Given prepare request to get all the employee
        When make a get call to retrieve all the employee
        Then validate the status code 200
        And validate the created employee is present in the response

  @ApiWorkFlow
     Scenario: delete employee
    Given prepare request for delete the employee
    When  make a call to delete the employee
    Then  validate the status code is 200

