Feature: Login Validation Feature

  Scenario: Verification of UI Elements
    Given Open the url for Login Page
    Then Verify username fields is present
    And Verify password field is present
    And Verify Login Button is present

  Scenario Outline: Test Validation with valid Credentials
    Given Open the url for Login Page
    When Enter the "<username>" and "<password>"
    And Click login Button
    Then Verify the user is directed to product Page

    Examples:
      |  username   | password    |
      |standard_user| secret_sauce|

  Scenario Outline: Test Validation with Invalid Credentials
    Given Open the url for Login Page
    When Enter the "<username>" and "<password>"
    And Click login Button
    Then Verify the error message
    Examples:
      |  username               | password    |
      |locked_out_user1         | secret_sauce|
#      |problem_user1            | secret_sauce|

