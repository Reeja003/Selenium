package com.training.pom;

import java.awt.RenderingHints.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.mysql.jdbc.Driver;

public class Cyclos_Member_FilterTransactionPOM {
	private WebDriver driver;

	public Cyclos_Member_FilterTransactionPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// *** Account section from left menu***
	@FindBy(xpath = "//*[@class=\"menuText\"][contains(text(),\"Account\")]")
	private WebElement accountTab;
	// ***Search transactions on Member account section ***
	@FindBy(xpath = "//*[@class=\"subMenuText\"][contains(text(),\"Account Information\")]")
	private WebElement accountinfoLink;
	@FindBy(id = "modeButton")
	private WebElement advancedBtn;
	@FindBy(id = "memberUsername")
	private WebElement memberLogin;
	@FindBy(xpath = "//input[@class=\"button\"][@type=\"submit\"]")
	private WebElement searchBtn;

	// ***Calendar section to select from and to date

	@FindBy(xpath = "//input[@name=\"query(period).begin\"]/following-sibling::img")
	private WebElement fromDate;
	@FindBy(className = "day")
	private List<WebElement> days;
	@FindBy(css = "[class='calendar'] td[class='title']")
	private WebElement month;

	@FindBy(xpath = "/html/body/div[6]/table/thead/tr[2]/td[2]")
	private WebElement leftnavMonth;
	@FindBy(xpath = "//tr[@class=\"headrow\"]//td[@class=\"button nav\"][4]")
	private WebElement rightnavMonth;

	@FindBy(xpath = "//input[@name='query(period).end']/following-sibling::img")
	private WebElement toDate;
	@FindBy(name = "query(paymentFilter)")
	private WebElement wbpaymentType;

	// ****Click Accounttab then click account info link
	public void clickAccountTab() {

		accountTab.click();
	}

	public void clickAccountInformationLink() {

		accountinfoLink.click();
	}

//****Click advanced button to filter the transaction
	public void clickAdvancedbtn() {
		advancedBtn.click();
	}

//****Enter the memberlogin	
	public void sendMemberLogin(String memberuser) {

		this.memberLogin.sendKeys(memberuser);

	}

	// **** click search button to display the transactions
	public void clickSearchSubmit() {

		searchBtn.submit();

	}

// ****select from and to date from calendar
	public void clickFromdateCalendar() {
		fromDate.click();

		int count = days.size();
		for (int i = 0; i < count; i++) {
			String text = days.get(i).getText();
			if (text.equalsIgnoreCase("20")) {
				days.get(i).click();
				break;
			}
		}

	}

	public void clickTodateCalendar() {
		toDate.click();
		int count = days.size();
		for (int i = 0; i < count; i++) {
			String text = days.get(i).getText();

			if (text.equalsIgnoreCase("26")) {
				days.get(i).click();
			}
		}

	}

//***Comparing search result date with respect to the selected date from calendar****

	public boolean resultBasedonDate() throws ParseException {

		boolean result = false;
		List<WebElement> date = driver.findElements(By
				.xpath("//td[@class='innerBorder']//table[@class=\"defaultTable\"]//tr//following-sibling::tr//td[1]"));
		for (int i = 0; i < date.size(); i++) {
			String searchDate = date.get(i).getText();
			result = CompareDates(searchDate);
		}

		return result;
	}

	public boolean CompareDates(String searchDate) throws ParseException {
		boolean value = false;
		String startDate = "19/02/2019";
		String endDate = "26/02/2019";
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = fmt.parse(startDate);
		Date date2 = fmt.parse(endDate);
		Date resultdate = fmt.parse(searchDate);
		value = !(resultdate.before(date1) || resultdate.after(date2));
		return value;

	}

	// ****Select paymentType
	public void selectPaymentType(String visibletext) {

		Select paymentType = new Select(wbpaymentType);
		paymentType.selectByVisibleText(visibletext);
	}

	// **** serach transaction based on date and type
	public boolean clickSearch() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		boolean result = false;
		List<WebElement> fromtoContent = driver.findElements(
				By.xpath("//td[@class='innerBorder']//tr//td[2][not(contains(@style,'display: none;'))]"));
		List<WebElement> descriptionContent = driver.findElements(
				By.xpath("//td[@class='innerBorder']//tr//td[3][not(contains(@style,'display: none;'))]"));
		for (int i = 0; i < fromtoContent.size(); i++) {
			result = fromtoContent.get(i).getText().contentEquals("Debit account")
					&& descriptionContent.get(i).getText().contains("Loan");
		}
		return result;
	}

}
