#Author: your.email@your.domain.com
@Amazon @Regression
Feature: Amazon feature validation
  

  @sanity
  Scenario: Amazon validation
    Given User lauchs amazon application
    And User enters Login credentials and click login button
    When user enters mobiles in search box
    When user enters desired product and click on add to cart
    Then Navigate into add to cart and check order is placed 
    

  
