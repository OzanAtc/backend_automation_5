
  Feature: As a QA tester, I validate the GoRest CRUD Operations

  Scenario Outline: Validating GoREST CRUD Operations

    Given Create an user with "<name>", email, "<gender>", "<status>", "<GoRestUrlPath>"
    Then Validate that status code is 201

    Examples:

    |name|gender|status|GoRestUrlPath
    |Hakki|Male |active|public/v2/users|

    When I make a GET request with the "<GoRestUrlPath>" with id
    Then Validate that status code is 200

    When I make a PUT request with following data with "<GoRestUrlPath>"
    Then Validate that status code is 200

 |name|Elvis|
 |gender|Male


    When I make a PATCH request with following data "<GoRestUrlPath>" with id
    Then Validate that status code is 200

    |name|Ricky|


    When I
