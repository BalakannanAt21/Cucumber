#Author: my company@your.domain.com


Feature: MobileSearchValidation
  
  @tag1
  Scenario: Validating Mobile purchase
    Given User launch flipkart application
    And User login credentials and click on login button
    When User enters mobiles in search box
    And User enter desired product and click on add to cart 
    Then Navigate into add to cart and check order is placed correctly
   

 