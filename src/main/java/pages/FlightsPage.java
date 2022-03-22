package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.List;

public class FlightsPage extends BasePage {

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='SEARCH FOR BEST FLIGHTS']")
    private WebElement headerFlightsPage;

    @FindBy(id = "departure")
    private WebElement inpDeparture;

    @FindBy(css = "div[class='datepicker-days'] th[class='switch']")
    private WebElement txtMonthAndYearInCalendar;

    @FindBy(css = "[class='datepicker-days'] th[class='next']")
    private WebElement btnNextMonthInCalendar;

    @FindBy(css = ".day")
    private List<WebElement> daysInCalendar;

    @FindBy(css = ".day")
    private WebElement dayInCalendar;

    public void verifyIfFlightsPageIsOpen() {
        Log.info("Verifying if flights page is open.");
        waitUntilElementIsVisible(headerFlightsPage);
        Log.info("Flights page is open.");
    }

    public void selectDepartureDateByCalendar(String day, String month, String year) {
        Log.info("Selecting departure date by calendar.");
        Log.info("Choosing '"+day+"' of month '"+month+"' in year '"+year+"'.");
        waitUntilElementIsClickable(inpDeparture);
        inpDeparture.click();
        waitUntilElementIsVisible(txtMonthAndYearInCalendar);
        while(!(txtMonthAndYearInCalendar.getText().contains(month) && txtMonthAndYearInCalendar.getText().contains(year)))
        {
            btnNextMonthInCalendar.click();
        }
        for (WebElement element : daysInCalendar) {
            String singleDay = element.getText();
            if (singleDay.equals(day)) {
                element.click();
                break;
            }
        }
    }

    public void verifyIfDateByCalendarWasSelected(String dateInIsoFormat) {
        Log.info("Verifying if date '"+dateInIsoFormat+"' was selected.");
        waitUntilElementIsVisible(inpDeparture);
        textAssertion(inpDeparture.getAttribute("value"), dateInIsoFormat);
        Log.info("Date was selected, calendar is working fine.");
    }



}
