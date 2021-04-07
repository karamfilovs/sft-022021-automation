import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginHardWayTest {
    private final String BASE_URL = "https://st2016.inv.bg";
    private final String EMAIL = "karamfilovs@gmail.com";
    private final String PASSWORD = "123456";
    private WebDriver driver;
    private final String INVALID_CREDENTIALS_ERROR = "Грешно потребителско име или парола. Моля, опитайте отново.";

    @BeforeAll
    public static void beforeAllTests() {
        WebDriverManager.chromedriver().setup(); // WebDriver manager configure the right driver for chrome
    }

    @BeforeEach
    public void beforeEachTest() {
        //Before every test
        driver = new ChromeDriver(); //Creates new browser instance
        driver.manage().window().maximize(); //Maximizes browser
        driver.manage().deleteAllCookies(); //Delete all cookies
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterEach
    public void afterEachTest() {
        //After every test
        driver.quit();    //Kills the browser instance
    }


    @Test
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        //Navigate to inv.bg login page https://st2016.inv.bg
        System.out.println("Navigating to:" + BASE_URL);
        driver.navigate().to(BASE_URL);
        WebElement heading1 = driver.findElement(By.xpath("//h1"));
        //Check page heading 1
        System.out.println("Checking heading 1 text");
        Assertions.assertEquals("Вход в inv.bg", heading1.getText());
        //Enter valid email
        System.out.println("Entering email: " + EMAIL);
        WebElement emailField = driver.findElement(By.id("loginusername")); //Locate email element
        emailField.clear(); //Clear any text inside of email field
        emailField.sendKeys(EMAIL); //Populate email field with real email
        //Enter valid password for the email
        System.out.println("Entering password: " + PASSWORD);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        //Click Login button
        System.out.println("Clicking Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check the user is logged in
        WebElement userPanelHeader = driver.findElement(By.cssSelector("div.userpanel-header"));
        Assertions.assertEquals(EMAIL, userPanelHeader.getText());
    }


    @Test
    @DisplayName("Cant login with invalid credentials")
    public void cantLoginWithInvalidCredentials() {
        //Navigate to inv.bg login page https://st2016.inv.bg
        System.out.println("Navigating to:" + BASE_URL);
        driver.navigate().to(BASE_URL);
        WebElement heading1 = driver.findElement(By.xpath("//h1"));
        //Check page heading 1
        System.out.println("Checking heading 1 text");
        Assertions.assertEquals("Вход в inv.bg", heading1.getText());
        //Enter valid email
        System.out.println("Entering email: " + EMAIL);
        WebElement emailField = driver.findElement(By.id("loginusername")); //Locate email element
        emailField.clear(); //Clear any text inside of email field
        emailField.sendKeys(EMAIL); //Populate email field with real email
        //Enter valid password for the email
        String fakePassword = "12345678";
        System.out.println("Entering password: " + fakePassword);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys(fakePassword);
        //Click Login button
        System.out.println("Clicking Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check error message
        WebElement errorDiv = driver.findElement(By.xpath("//div[@class='selenium-error-block']"));
        Assertions.assertEquals(INVALID_CREDENTIALS_ERROR, errorDiv.getText().trim());
    }

    @Test
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        //Navigate to inv.bg login page https://st2016.inv.bg
        System.out.println("Navigating to:" + BASE_URL);
        driver.navigate().to(BASE_URL);
        WebElement heading1 = driver.findElement(By.xpath("//h1"));
        //Check page heading 1
        System.out.println("Checking heading 1 text");
        Assertions.assertEquals("Вход в inv.bg", heading1.getText());
        //Click Login button
        System.out.println("Clicking Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check error message
        WebElement errorDiv = driver.findElement(By.xpath("//div[@class='selenium-error-block']"));
        Assertions.assertEquals("Моля, попълнете вашия email", errorDiv.getText().trim());
    }


    @Test
    @DisplayName("Can login with valid credentials and logout")
    public void canLoginWithValidCredentialsAndLogout() {
        //Navigate to inv.bg login page https://st2016.inv.bg
        System.out.println("Navigating to:" + BASE_URL);
        driver.navigate().to(BASE_URL);
        WebElement heading1 = driver.findElement(By.xpath("//h1"));
        //Check page heading 1
        System.out.println("Checking heading 1 text");
        Assertions.assertEquals("Вход в inv.bg", heading1.getText());
        //Enter valid email
        System.out.println("Entering email: " + EMAIL);
        WebElement emailField = driver.findElement(By.id("loginusername")); //Locate email element
        emailField.clear(); //Clear any text inside of email field
        emailField.sendKeys(EMAIL); //Populate email field with real email
        //Enter valid password for the email
        System.out.println("Entering password: " + PASSWORD);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        //Click Login button
        System.out.println("Clicking Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check the user is logged in
        WebElement userPanelHeader = driver.findElement(By.cssSelector("div.userpanel-header"));
        Assertions.assertEquals(EMAIL, userPanelHeader.getText());
        //Click Logout link
        System.out.println("Logout from the system");
        userPanelHeader.click(); //Click on user panel so that the logout link become visible
        WebElement logoutLink = driver.findElement(By.xpath("//a[@class='selenium-button-logout button-logout']"));
        logoutLink.click(); //Clicks the logout link
        //Check success logout message
        WebElement logoutSuccessDiv = driver.findElement(By.id("okmsg"));
        Assertions.assertEquals("Вие излязохте от акаунта си. ", logoutSuccessDiv.getText());
    }
}
