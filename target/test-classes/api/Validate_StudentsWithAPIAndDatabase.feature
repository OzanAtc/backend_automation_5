@dbapi

Feature : As a QE, I am validating the data from Database and API

  Scenario Outline: Validating the data from Database and API

    Given user is able to connect to database
    When user send "<query>" to database

    Examples: Data

      | query                 | urlPath   |
      | select * from student | /students |

