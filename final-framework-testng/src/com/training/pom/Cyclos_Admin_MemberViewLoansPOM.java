package com.training.pom;

import java.awt.RenderingHints.Key;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mysql.jdbc.Driver;

public class Cyclos_Admin_MemberViewLoansPOM {
   private WebDriver driver;

	public Cyclos_Admin_MemberViewLoansPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// *****Loans_View Loan**********
	@FindBy(xpath = "//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[8]/td/fieldset/table/tbody/tr[1]/td[2]/input")
	private WebElement viewloansBtn;
	@FindBy(xpath = "//*[@id=\"tdContents\"]/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td/label[2]/span")
	private WebElement closedradioBtn;
	@FindBy(xpath = "//*[@id=\"tdContents\"]/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td/label[1]/span")
	private WebElement openradioBtn;
	@FindBy(xpath = "//td[@class='tdContentTableLists innerBorder']//table[@class='defaultTable']")
	private WebElement resultTable;
	@FindBy(xpath = "//td[@class='tdContentTableLists innerBorder']//table[@class='defaultTable']/tbody/tr/td[3]")
	private List<WebElement> remainingAmount;

	public void clickViewLoanBtn() {
		this.viewloansBtn.click();

	}

	public boolean clickClosedRadioBtn()

	{
		closedradioBtn.click();

		boolean value = false;
		int rSize = resultTable.findElements(By.tagName("tr")).size();

		for (int i = 0; i < rSize;) {
			value = remainingAmount.get(i).getText().contains("0,00 units");
			return value;

		}
		return value;

	}

	public boolean clickOpenRadioBtn()

	{
		openradioBtn.click();

		boolean value = false;
		int rSize = resultTable.findElements(By.tagName("tr")).size();
		for (int i = 0; i < rSize;) {
			value = remainingAmount.get(i).getText().contains("0,00 units");
			return value;

		}
		return value;

	}

}
