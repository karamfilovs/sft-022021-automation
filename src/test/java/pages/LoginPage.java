package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);
    private final String BASE_URL = System.getProperty("url");
    private final String EMAIL = System.getProperty("email");
    private final String PASSWORD = System.getProperty("password");

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void defaultLogin(){
        navigateTo();
        enterEmail(EMAIL);
        enterPassword(PASSWORD);
        clickLoginButton();
    }


    public void enterEmail(String email) {
        //Enter valid email
        LOGGER.info("Entering email: " + email);
        WebElement emailField = driver.findElement(By.id("loginusername")); //Locate email element
        emailField.clear(); //Clear any text inside of email field
        emailField.sendKeys(email); //Populate email field with real email
    }

    public void enterPassword(String password) {
        //Enter valid password for the email
        LOGGER.info("Entering password: " + password);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        //Click Login button
        LOGGER.info("Clicking Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
    }

    public String getLoginError() {
        WebElement errorDiv = driver.findElement(By.xpath("//div[@class='selenium-error-block']"));
        return errorDiv.getText();
    }

    public String getLogoutSuccessMessage() {
        WebElement logoutSuccessDiv = driver.findElement(By.id("okmsg"));
        return logoutSuccessDiv.getText();
    }

    public void navigateTo() {
        //Navigate to inv.bg login page https://st2016.inv.bg
        LOGGER.info("Navigating to:" + BASE_URL);
        driver.navigate().to(BASE_URL);
    }
}
