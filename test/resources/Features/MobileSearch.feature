#Author: your.email@your.domain.com
@Flipkart @Regression
Feature: Mobile Search Validation

Background: 
    Given User launch flipkart application
    And User login credentials and click on login button 

#Scenario: Validating Mobile Purchase
    #When User enters mobiles in search box
    #And User enter desired product and click on add to cart
    #Then Navigate into add to cart and check order is placed correctly
   
Scenario: Validating Mobile Purchase by using 1 dimensional list
    When User enters mobiles in search box by 1 dimensional list 
    |iphone|samsung|One+|Nokia|
    And User enter desired product and click on add to cart
    Then Navigate into add to cart and check order is placed correctly
    
    @somke @sanity
Scenario: Validating Mobile Purchase by using 1 dimensional Map
    When User enters mobiles in search box by 1 dimensional Map concept
    |Phone1|iphone |
    |Phone2|samsung|
    |Phone3|  One+ |
    |Phone4| Nokia |
    And User enter desired product and click on add to cart
    Then Navigate into add to cart and check order is placed correctly
  

Scenario Outline:  
    When User enters mobiles in search box "<Phone Name>"
    And User enter desired product and click on add to cart
    Then Navigate into add to cart and check order is placed correctly
    
    Examples:
    |Phone Name|
    |  Iphone  |
    |  Samsung |
    |  Realme  |
    