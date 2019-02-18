package com.training.pom;

import java.awt.RenderingHints.Key;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

public class CyclosLoginPOM {
	private WebDriver driver;

	public CyclosLoginPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
  // ***Application Login ***
	@FindBy(id = "cyclosUsername")
	private WebElement userName;
	@FindBy(id = "cyclosPassword")
	private WebElement password;
	@FindBy(xpath = "//*[@id=\"cyclosLogin\"]/table/tbody/tr[3]/td/input")
	private WebElement loginBtn;

	// *** Switch to Member Login ***
	@FindBy(id = "memberUsername")
	private WebElement memberLogin;
	

	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void sendPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void clickLoginBtn() {
		this.loginBtn.click();
	}

	public void sendMemberLogin(String memberLogin)

	{
		this.memberLogin.sendKeys(memberLogin);
		WebElement wbmemberLogin=driver.findElement(By.xpath("//*[@id=\"membersByUsername\"]/ul/li[1]") );
		wbmemberLogin.click();
		
	
	}



}
