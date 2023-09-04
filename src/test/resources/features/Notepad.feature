@NotepadTest
Feature: Notepad App Actions

  Scenario: Opening Notepad Application
    Given The Notepad application is opened
    Then verifying application is open by verifying visibility of elements
    And capturing screenshot

  Scenario: Adding Multiple Notes
    Given the user adds 5 text-notes
    Then verifying notes are added by verifying list size is 5
    And capturing screenshot

  Scenario: Change the Color of Text Notes
    Given the user changes the color of text notes to RED, ORANGE, YELLOW, BLUE, PURPLE
    And capturing screenshot

  Scenario: Print the Time of Each Note
    Given the user prints the time of each note
    And capturing screenshot

  Scenario: Change First Day of the Week
    Given the user opens Settings
    When the user changes the First day of the week to MONDAY
    Then verifying the day of the week is set to MONDAY
    And capturing screenshot

  Scenario: Search for a Note
    Given the user searches for the 3rd test note using the search box
    Then verify the searched result contains the 3rd test note
    And capturing screenshot

  Scenario: Delete Text Notes and Verify Trash Bin
    Given the user deletes the 2nd and 4th text notes
    Then verifying the text notes are in the trash bin
    And capturing screenshot

  Scenario: Archive Text Note and Verify Archive
    Given the user archives the first text note
    Then verifying the first text note is in the archive
    And capturing screenshot