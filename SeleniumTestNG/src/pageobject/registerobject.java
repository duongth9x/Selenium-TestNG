package pageobject;

import org.openqa.selenium.By;

public class registerobject {
	public static By linkMenuadduser = By.xpath(".//*[@id='menu-users']/a/div[3]");
    public static By linkAddnewuser = By.xpath(".//*[@id='menu-users']/ul/li[3]/a");
    public static By txtUserlogin = By.xpath(".//*[@id='user_login']");
    public static By txtEmail = By.xpath(".//*[@id='email']");
    public static By txtFirstname = By.id("first_name");
    public static By txtLastname = By.id("last_name");
    public static By txtWebsite = By.id("url");
    public static By btnShowpasswd = By.xpath(".//*[@id='createuser']/table/tbody/tr[6]/td/button");
    public static By btnRole = By.xpath(".//*[@id='role']");
    public static By btnCreateNewUser = By.id("createusersub");
    public static By linkLogout = By.linkText("Log out");
    
    
    
    
}
