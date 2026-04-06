Feature: Feature to test Login

  Scenario: TC01 User logs into Amazon

    Given User opens Amazon website
    When User enters username and password for 'TC01'
    And User clicks on login button
    Then User should see the homepage
    Then Logout Application

