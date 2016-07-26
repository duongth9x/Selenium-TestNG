package pageobject;

import org.openqa.selenium.By;

public class loginobject {
	public static By linkLogin = By.linkText("Log in");
	public static By txtUsername = By.id("user_login");
	public static By txtPassword = By.id("user_pass");
	public static By btnLogin = By.xpath(".//*[@id='wp-submit']");
	public static By actualMess = By.xpath(".//*[@id='login']/p[1]");
	
	
}
