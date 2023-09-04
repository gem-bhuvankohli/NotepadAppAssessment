package implementations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import locators.NotepadLocators;
import logger.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.net.URL;
import java.time.Duration;
import java.util.List;

/**
 * Contains implementations related to the Notepad application.
 */
public class NotepadImplementation {
    public static AppiumDriver driver;
    static List<WebElement> noteElementsList;
    static List<WebElement> timeElementsList;
    public static LongPressOptions longPressOptions = new LongPressOptions();
    public static final String addText = "helloWorld ";
    public static int screenshotCount = 1;

    /**
     * Opens the Notepad application.
     * Initializes the appium driver with desired capabilities and opens the application.
     */
    public static void openApplication() {
        try {
            // Set desired capabilities for the driver
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("deviceName", ConfigReader.getProperty("appium.deviceName"));
            cap.setCapability("udid", ConfigReader.getProperty("appium.udid"));
            cap.setCapability("platformName", ConfigReader.getProperty("appium.platformName"));
            cap.setCapability("app", System.getProperty("user.dir") + ConfigReader.getProperty("appium.appPath"));
            cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

            // Initialize the driver
            URL url = new URL(ConfigReader.getProperty("appium.appUrl"));
            driver = new AndroidDriver(url, cap);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Click on the skip button for introduction
            driver.findElement(NotepadLocators.skipBtn).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the Notepad application is open.
     * Checks if the required buttons are displayed.
     */
    public static void verifyApplicationIsOpen() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement addNoteBtn = driver.findElement(NotepadLocators.addNoteBtn);
            WebElement searchBtn = driver.findElement(NotepadLocators.searchBtn);

            Assert.assertTrue("Add note button is not displayed.", addNoteBtn.isDisplayed());
            Assert.assertTrue("Search button is not displayed.", searchBtn.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds multiple notes to the Notepad application.
     */
    public static void addNotes() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Add multiple notes
            for (int i = 1; i <= 5; i++) {
                WebElement addNoteBtn = driver.findElement(NotepadLocators.addNoteBtn);
                addNoteBtn.click();

                WebElement textOption = driver.findElement(NotepadLocators.textOption);
                textOption.click();

                WebElement addTitle = driver.findElement(NotepadLocators.addTitle);
                addTitle.sendKeys("Title " + i);

                WebElement addNote = driver.findElement(NotepadLocators.addNote);
                addNote.sendKeys(addText);

                WebElement backBtn = driver.findElement(NotepadLocators.backBtn);
                backBtn.click();
                backBtn.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if notes are added by checking the note elements list.
     */
    public static void verifyIfNotesAreAdded() {
        try {
            // Find and verify note elements list
            noteElementsList = driver.findElements(NotepadLocators.noteElementsList);

            Assert.assertTrue("No notes were added.", noteElementsList.size() == 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the color of notes.
     */
    public static void updateColorOfNotes() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            int colorCode = 1;

            for (WebElement note : noteElementsList) {
                LongPressOptions longPressOptions = new LongPressOptions();
                longPressOptions.withElement(ElementOption.element(note));
                TouchAction touchAction = new TouchAction<>((PerformsTouchActions) driver);
                touchAction.longPress(longPressOptions).release().perform();
                WebElement addColorBtn = driver.findElement(NotepadLocators.addColorBtn);
                addColorBtn.click();

                if (colorCode == 4) {
                    colorCode++;
                }
                WebElement color = driver.findElement(By.id("com.socialnmobile.dictapps.notepad.color.note:id/btn" + colorCode));
                color.click();
                colorCode++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs the time of every note.
     */
    public static void timeOfEveryNote() {
        try {
            timeElementsList = driver.findElements(NotepadLocators.timeElementsList);
            for (WebElement time : timeElementsList) {
                Log.info(time.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the settings menu.
     */
    public static void openSettings() {
        try {
            WebElement kebabMenu = driver.findElement(NotepadLocators.kebabMenu);
            kebabMenu.click();
            WebElement settingsMenu = driver.findElement(NotepadLocators.settingsMenu);
            settingsMenu.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the first day of the week setting.
     */
    public static void changeFirstDayOfTheWeek() {
        try {
            WebElement firstDayOfWeek = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"First day of week\").instance(0))"));
            firstDayOfWeek.click();
            WebElement day = driver.findElement(NotepadLocators.day);
            day.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Verifies the first day of the week setting.
     */
    public static void verifyFirstDayOfTheWeek() {
        try {
            WebElement verifyFirstDayOfTheWeek = driver.findElement(NotepadLocators.verifyFirstDayOfTheWeek);
            String actualDay = verifyFirstDayOfTheWeek.getText();

            Assert.assertEquals("Expected the first day of the week to be 'Monday'.", "Monday", actualDay);
            driver.navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for a specific note by number.
     *
     * @param noteNumber The number of the note to search for.
     */
    public static void searchTextNote(int noteNumber) {
        try {
            WebElement searchBtn = driver.findElement(NotepadLocators.searchBtn);
            searchBtn.click();
            WebElement searchBox = driver.findElement(NotepadLocators.searchBox);
            searchBox.sendKeys(String.valueOf(noteNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies the search result for a specific note by number.
     *
     * @param noteNumber The number of the note to verify.
     */
    public static void verifySearchResult(int noteNumber) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            String searchResult = driver.findElement(NotepadLocators.searchResult).getText();

            Assert.assertEquals("The search result does not match the expected note title.", "Title " + noteNumber, searchResult);

            WebElement backBtn = driver.findElement(NotepadLocators.searchBack);
            backBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes two notes from the Notepad application.
     *
     * @param noteToDelete1 The number of the first note to delete.
     * @param noteToDelete2 The number of the second note to delete.
     */
    public static void deleteNotes(int noteToDelete1, int noteToDelete2) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement firstElementToDelete = noteElementsList.get(noteToDelete1 - 1);
            WebElement secondElementToDelete = noteElementsList.get(noteToDelete2 - 1);
            longPressOptions.withElement(ElementOption.element(firstElementToDelete));
            TouchAction touchAction = new TouchAction<>((PerformsTouchActions) driver);
            touchAction.longPress(longPressOptions).release().perform();
            driver.findElement(NotepadLocators.deleteBtn).click();
            driver.findElement(NotepadLocators.okBtn).click();

            longPressOptions.withElement(ElementOption.element(secondElementToDelete));
            touchAction.longPress(longPressOptions).release().perform();
            driver.findElement(NotepadLocators.deleteBtn).click();
            driver.findElement(NotepadLocators.okBtn).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the note deletion was successful.
     */
    public static void verifyDeletion() {
        WebElement navMenu = driver.findElement(NotepadLocators.navMenu);
        navMenu.click();
        WebElement trash = driver.findElement(NotepadLocators.trash);
        trash.click();
        String note4 = driver.findElement(NotepadLocators.textNote4).getText();
        String note2 = driver.findElement(NotepadLocators.textNote2).getText();

        Assert.assertEquals("The deleted note is still present.", "Title 4", note4);
        Assert.assertEquals("The deleted note is still present.", "Title 2", note2);

        driver.navigate().back();
    }

    /**
     * Archives the first text note.
     */
    public static void archiveFirstTextNote() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement note1 = driver.findElement(NotepadLocators.textNote1);
            longPressOptions.withElement(ElementOption.element(note1)).withDuration(Duration.ofSeconds(2));
            TouchAction touchAction = new TouchAction<>((PerformsTouchActions) driver);
            touchAction.longPress(longPressOptions).release().perform();
            WebElement archive = driver.findElement(NotepadLocators.archiveOption);
            archive.click();
            driver.findElement(NotepadLocators.confirmOption).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the first text note is archived.
     */
    public static void verifyArchivedNotes() {
        try {
            driver.findElement(NotepadLocators.navMenu).click();
            driver.findElement(NotepadLocators.archive).click();

            try {
                String note1 = driver.findElement(NotepadLocators.textNote1).getText();

                Assert.assertEquals("The archived note is not found in the archive.", "Title 1", note1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}