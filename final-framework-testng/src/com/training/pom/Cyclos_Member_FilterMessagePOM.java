package com.training.pom;

import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.mysql.jdbc.Driver;

public class Cyclos_Member_FilterMessagePOM {
	private WebDriver driver;

	public Cyclos_Member_FilterMessagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//* Personal section_Messages from left menu*//
	@FindBy(xpath = "//*[@class='menuText'][contains(text() ,'Personal')]")
	private WebElement personalTab;
	@FindBy(xpath = "//*[@class='subMenuText'][contains(text(),'Messages')]")
	private WebElement messageLink;
	@FindBy(id = "modeButton")
	private WebElement advancedBtn;

	//* Message List*//
	@FindBy(id = "messageBoxSelect")
	private WebElement wbmessageBox;
	@FindBy(name = "query(rootType)")
	private WebElement wbrootType;
	@FindBy(xpath = "//td[@class='tdContentTableForms innerBorder']//input[@type='submit']")
	private WebElement submitBtn;

	//*Message search Result*//
	@FindBy(xpath = "//form[@name='changeMessageStatusForm']//table[@class='defaultTableContent']")
	private WebElement searchmsgTable;
	@FindBy(xpath = "//td[4][@class='tdHeaderContents']")
	WebElement typeHeader;

	//*Click personal Tab->message Link to get Message List*//
	public void clickPersonalTab() {

		personalTab.click();
	}

	public void clickMessagesLink() {

		messageLink.click();
	}

	//*Click advances button to get filter option*//
	public void clickAdvanceBtn() {

		advancedBtn.click();
	}

	//*Select message Box and List box based on requirement*//
	public void selectMessageBox(String visibleTXT) {

		Select messageBox = new Select(wbmessageBox);
		messageBox.selectByVisibleText(visibleTXT);
	}

	public void selectType(String visibleTXT) {

		Select rootType = new Select(wbrootType);
		rootType.selectByVisibleText(visibleTXT);
	}

	public void clickSubmitBtn() {

		submitBtn.submit();
	}

	// *Filter message Box(To/From)*//
	public String searchMessageBoxType() {
		String header = typeHeader.getText();
		return header;
	}

	// *Filter the message based on "Type" selected*//
	public boolean seachMessagesforType(String typeselected) {

		boolean typevalue = false;
		List<WebElement> type = driver.findElements(By.xpath("//td[1][@class='tdContentTableLists innerBorder']//tr//td[4][not(contains(@style,'display: none;'))]"));
		for (int i = 1; i < type.size(); i++) {

			typevalue = type.get(i).getText().equals(typeselected);
		}
		return typevalue;

	}

	// *Filter the all inbox message*//
	public boolean seachAllTypeMessages(String typeselected) {

		boolean typevalue = false;

		List<WebElement> type = driver.findElements(By.xpath("//td[1][@class='tdContentTableLists innerBorder']//tr//td[4][not(contains(@style,'display: none;'))]"));
		for (int i = 1; i < type.size(); i++) {

		typevalue = type.get(i).getText().contains("Member") || type.get(i).getText().contains("Administration")
					|| type.get(i).getText().contains("System");

		}
		return typevalue;

	}

}
