Feature: login funcionality check with valid credential

  @smoke
  Scenario: check login functionality
    Given I have username password
    When I login to homwpage
    Then I validate the login
    @smoke
    Scenario:login with negative data
    Given I have fake username password
    When I not login to homwpage
    Then I check the login
    