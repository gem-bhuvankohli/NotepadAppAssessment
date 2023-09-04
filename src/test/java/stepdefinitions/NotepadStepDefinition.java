package stepdefinitions;

import implementations.NotepadImplementation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TakeScreenshot;

import static implementations.NotepadImplementation.screenshotCount;

public class NotepadStepDefinition {

    @Given("The Notepad application is opened")
    public void openTheNotepadApplication() {
        NotepadImplementation.openApplication();
    }

    @Then("verifying application is open by verifying visibility of elements")
    public void verifyingApplicationIsOpen() {
        NotepadImplementation.verifyApplicationIsOpen();
    }

    @And("capturing screenshot")
    public void takeScreenshot() {
        TakeScreenshot.screenshot("Screenshot" + (screenshotCount++) + ".jpg");
    }

    @Given("the user adds 5 text-notes")
    public void addMultipleNotes() {
        NotepadImplementation.addNotes();
    }

    @Then("verifying notes are added by verifying list size is 5")
    public void verifyIfNotesAreAddedSuccessfully() {
        NotepadImplementation.verifyIfNotesAreAdded();
    }

    @Given("the user changes the color of text notes to RED, ORANGE, YELLOW, BLUE, PURPLE")
    public void changeColorOfTheTextNotes() {
        NotepadImplementation.updateColorOfNotes();
    }

    @Given("the user prints the time of each note")
    public void theUserPrintsTheTimeOfEachNote() {
        NotepadImplementation.timeOfEveryNote();
    }

    @Given("the user opens Settings")
    public void theUserOpensSettings() {
        NotepadImplementation.openSettings();
    }

    @When("the user changes the First day of the week to MONDAY")
    public void changeTheFirstDayOfTheWeekToMONDAY() {
        NotepadImplementation.changeFirstDayOfTheWeek();
    }

    @Then("verifying the day of the week is set to MONDAY")
    public void verifyingTheDayOfTheWeekIsSetToMONDAY() {
        NotepadImplementation.verifyFirstDayOfTheWeek();
    }

    @Given("the user searches for the {int}rd test note using the search box")
    public void theUserSearchesForTheThirdTestNote(int arg0) {
        NotepadImplementation.searchTextNote(arg0);
    }

    @Then("verify the searched result contains the {int}rd test note")
    public void verifyTheSearchedResultContainsThirdTestNote(int arg0) {
        NotepadImplementation.verifySearchResult(arg0);
    }

    @Given("the user deletes the {int}nd and {int}th text notes")
    public void theUserDeletesTheSecondAndFourthTextNotes(int arg0, int arg1) {
        NotepadImplementation.deleteNotes(arg0, arg1);
    }

    @Then("verifying the text notes are in the trash bin")
    public void verifyingTheNotesAreInTheTrashBin() {
        NotepadImplementation.verifyDeletion();
    }

    @Given("the user archives the first text note")
    public void theUserArchivesTheFirstTextNote() {
        NotepadImplementation.archiveFirstTextNote();
    }

    @Then("verifying the first text note is in the archive")
    public void verifyingTheFirstTextNoteIsInTheArchive() {
        NotepadImplementation.verifyArchivedNotes();
    }
}