@Smoke
Feature: Feature to Checkout the product from login

  Scenario: TC02 Login to Checkout
    When User enters username and password for 'TC02'
And User clicks on login button
Then User should see the homepage
And Search the product
And Add product to cart
And Proceed to checkout
And Enter address
And Enter instruction
And Select payment
Then Logout Application