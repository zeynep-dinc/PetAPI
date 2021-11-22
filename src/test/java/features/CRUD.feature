Feature: CRUD

  Scenario:CRUDSteps
    Given "Add a new pet to the store" open to form
    Then click to "Try it out " button
    Then complate to textarea with data
    Then click to "Execute" button
    Then read to code "POST Add a new pet to the store"
    Then "Add a new pet to the store" close to form

    Then "Find pet by ID" open to form
    Then click to "Try it out " button
    Then enter to "1" is "1"
    Then click to "Execute" button
    Then read to code "GET Find pet by ID"
    Then "Find pet by ID" close to form

    Then "Updates a pet in the store with form data" open to form
    Then click to "Try it out " button
    Then enter to "1" is "1"
    Then  enter to "2" is "testtt"
    Then click to "Execute" button
    Then read to code "POST Updates a pet in the store with form data"
    Then "Updates a pet in the store with form data" close to form

    Given "Deletes a pet" open to form
    Then click to "Try it out " button
    Then enter to "2" is "1"
    Then click to "Execute" button
    Then read to code "DELETE Deletes a pet"
    Then "Deletes a pet" close to form

#    Then "Finds Pets by status" open to form
#    Then click to "try it out " button
#    Then "available" select to option
#    Then click to "execute" button
#    Then read to code
#    And  go to "/pet/findByStatus?status=available" url
#    Then get first id
#    Then return first tab
#    Then "Finds Pets by status" close to form


