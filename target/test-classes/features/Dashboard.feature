Feature: dashboard tabs


  @dashboardTabs  @regression
  Scenario: dashboard tabs verification
    When  user add valid username
    And   user add valid password
    And   user click on the login button
    And  user validate dashboard tabs
      | Dashboard | Employee | Leave |  Change Password | Master List |