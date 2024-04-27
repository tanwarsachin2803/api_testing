# Created by sachintanwar at 2024-04-26
Feature: Api testing

  Scenario: Verifying the products
    Given Access end point url
    When Access products
    Then Verify status code 200

  Scenario Outline: Verifying the different database
    Given Access end point url
    When Access databases <database>
    Then Verify database <database> is present
    Examples:
      | database |
      |products  |


  Scenario: Verifying the rate of product 1
    Given Access end point url
    When Access products
    Then Check the product 1
    Then Verify status code 200

  Scenario: Adding new product
    Given Access end point url
    When  Access products
    Then Pass the parameters
    When Pass the post method
    Then Verify status code 201

