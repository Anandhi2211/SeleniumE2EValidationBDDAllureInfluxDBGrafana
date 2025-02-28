Feature: Product Page UI Validation

  Background:
    Given Open the url for Login Page
    When Enter the "standard_user" and "secret_sauce"
    And Click login Button

  Scenario: Verify if the Product Page Loads correctly
    Then Verify the user is directed to product Page
    And Verify if all product images are loaded
    And Verify if all product names are loaded
    And Verify if all product prices are loaded
    And Verify if all product addToCarts are loaded

    Scenario: Add the products to the cart
      When Choose the following products:
        |product             |
        |Sauce Labs Backpack |
        |Sauce Labs Onesie   |
      Then Verify if cart icon properly updates the count
      And Click cart icon
      Then Verify if all the products added

  Scenario: Cart Page Validation
    And Click cart icon
    Then Verify all the products name and price
    And Verify the total amount
    And Verify the checkout button is present
