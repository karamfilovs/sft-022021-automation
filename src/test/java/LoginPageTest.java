import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private WebDriver driver;

    @BeforeAll
    public static void beforeAllTests() {
        WebDriverManager.firefoxdriver().setup(); // WebDriver manager configure the right driver for chrome
    }



    @BeforeEach
    public void beforeEachTest() {
        //Before every test
        driver = new FirefoxDriver(); //Creates new browser instance
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
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.navigateTo();
        loginPage.enterEmail("karamfilovs@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
    }
}
