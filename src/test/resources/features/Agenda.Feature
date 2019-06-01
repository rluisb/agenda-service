Feature: Maintain agendas

  Scenario: Create an agenda with success
    Given a title Titulo 1
    And a description Descrição 1
    And a subject Assunto 1
    When create a new agenda
    Then should return status 200
    And should return a new agenda
    And this agenda should have an id
    And should have a status WAITING_FOR_VOTING

  Scenario: Create an agenda with error because don't have a title
    Given a description Descrição 1
    And a subject Assunto 1
    When create a new agenda
    Then should return status 400
    And should contain a message equal The title can't be null.
    And should contain a message equal The title can't be empty.