import api.ClientAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.ClientPage;
import pages.HomePage;
import pages.LoginPage;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ClientPageTest {
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
    @DisplayName("Can create new client")
    public void canCreateNewClient(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ClientPage clientPage = new ClientPage(driver);
        ClientAPI api = new ClientAPI();
        loginPage.defaultLogin();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
        homePage.clickClientLink();
        clientPage.createClient("Pragmatic " + LocalDateTime.now(), "Student district", "Sofia");
        Assertions.assertEquals("Клиентът е добавен успешно.", clientPage.getSuccessMessage());
        //Clean test data via API
        api.deleteAll();
    }

    @Test
    @DisplayName("Can view correct message when no clients exist")
    public void canViewCorrectMessageWhenNoClientsExist(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ClientPage clientPage = new ClientPage(driver);
        ClientAPI api = new ClientAPI();
        api.deleteAll();
        loginPage.defaultLogin();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
        homePage.clickClientLink();
        Assertions.assertEquals("Все още нямате добавени клиенти.", clientPage.getEmptyListMessage());
    }


}
