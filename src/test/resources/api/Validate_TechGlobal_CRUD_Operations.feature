Feature: As a QE, I validate the TechGlobal CRUD operations

  Scenario Outline: Validating the TechGlobal CRUD operations
    Given Create an user with "<firsName>", "<lastName>", email, "<dob>" and "<urlPath>"
    And Validate that status code is 200

    Examples: TechGlobal Data
      | firsName | lastName | dob        | urlPath   |
      | Batch    | Five     | 2022-08-29 | /students |





    When I make a GET request with "<urlPath>" with id
    Then Validate that status code is 200


    When I make a PUT request with following data and with "<urlPath>"
      | firstName | Global |
      | lastName  | Tech   |

    Then Validate that status code is 200


    When I make a PATCH request with following data and with "<urlPath>"
      | firstName | Best |
      | lastName  | Batch|


    Then Validate that status code is 200
    When I make a DELETE request with "<urlPath>" with id
    Then Validate that status code is 200

