Feature: Create agenda

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

  Scenario: Create an agenda with error because don't the title is empty
    Given an empty title
    And a description Descrição 1
    And a subject Assunto 1
    When create a new agenda
    Then should return status 400
    And should contain a message equal The title can't be empty.

  Scenario: Create an agenda with error because don't have a subject
    Given a title Titulo 1
    And a description Descrição 1
    When create a new agenda
    Then should return status 400
    And should contain a message equal The subject can't be null.
    And should contain a message equal The subject can't be empty.

  Scenario: Create an agenda with error because don't the subject is empty
    Given a title Titulo 1
    And an empty subject
    And a description Descrição 1
    When create a new agenda
    Then should return status 400
    And should contain a message equal The subject can't be empty.

  Scenario: Create an agenda with success even without a description
    Given a title Titulo 1
    And a subject Assunto 1
    When create a new agenda
    Then should return status 200
    And should return a new agenda
    And this agenda should have an id
    And should have a status WAITING_FOR_VOTING
    And a null description

  Scenario: Find all agendas
    When request all agendas
    Then should return status 200
    And a list of agendas

  Scenario: Find all agendas filtering by status
    Given a status WAITING_FOR_VOTING
    When request all agendas
    Then should return status 200
    And a list of agendas
    And all agendas should have a status equal to the used to filter

  Scenario: Find agenda by id
    Given an id
    When request an agenda filtering by it's id
    Then should return status 200
    And an agenda with an id equal to the id used to filter

  Scenario: Find agenda by id with error for invalid id
    Given an invalid id
    When request an agenda filtering by it's id
    Then should return status 400
    And a message containing No records found for this request.

  Scenario: Update the state of an Agenda by it's id
    Given an id
    And a status VOTING_IN_PROGRESS
    When update the agenda status
    Then should return status 200
    And updated agenda
    And a new agenda's status equal to status used to update

  Scenario: Update the state of an Agenda by it's id
    Given an id
    And a status CLOSED_VOTE
    When update the agenda status
    Then should return status 200
    And updated agenda
    And a new agenda's status equal to status used to update
