Feature: Login

  @smoke @regression
  Scenario: valid login
    When  user add valid username
    And   user add valid password
    And   user click on the login button
    Then  validate user login successfully

  @smoke
  Scenario: Invalid login
    When  user add invalid username
    And   user add invalid password
    And   user click on the login button
    Then  validate user see error message

  @regression
  Scenario: invalid login with empty username
    When  user does not add username
    And   user add valid password
    And   user click on the login button
    Then  user validate error message credentials are required

  @dataLogin
  Scenario Outline: login with multiple data
    When  user add valid "<username>" and "<password>"
    And   user click on the login button
    Then  validate user login successfully

    Examples:
      | username        | password   |
      | admin@gmail.com | admin#123  |
      | batch1@gmailcom | batch1@123 |

  @errorData
  Scenario: login with invalid data
    When  user add username and password and validate error message
      | username         | password  | errormessage                    |
      | admin@gmail.com  | admin123  | Username and Password is Wrong! |
      | batch1@yahoo.com | admin#123 | Username and Password is Wrong! |


    @excel
  Scenario: invalid login with excel data
    When user add username and password from excel "testData" and verify the error message