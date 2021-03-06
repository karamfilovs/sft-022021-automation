import api.ClientAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ClientPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ClientPage clientPage;
    protected ClientAPI clientAPI;

    @BeforeAll
    public static void beforeAllTests() {
        WebDriverManager.chromedriver().setup(); //Downloads chrome driver and sets up env var for it
        WebDriverManager.firefoxdriver().setup(); //Downloads firefox driver and sets up en var for it

    }

    @BeforeEach
    public void beforeEachTest(TestInfo info) {
        //Before every test
        startBrowser(System.getProperty("browser"));
        LOGGER.info("Starting test: " + info.getDisplayName());
        driver.manage().window().maximize(); //Maximizes browser
        driver.manage().deleteAllCookies(); //Delete all cookies
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Implicitly wait 10 seconds
        loginPage = new LoginPage(driver); //Creating instances
        homePage = new HomePage(driver);
        clientPage = new ClientPage(driver);
        clientAPI = new ClientAPI();
    }

    public void startBrowser(String browserType){
        switch (browserType){
            case "chrome" : driver = new ChromeDriver();
                break;
            case "firefox" : driver = new FirefoxDriver();
                break;
            default: new InvalidArgumentException("Not supported browser");
        }
    }



    @AfterEach
    public void afterEachTest() {
        driver.quit();    //Kills the browser instance
    }
}
