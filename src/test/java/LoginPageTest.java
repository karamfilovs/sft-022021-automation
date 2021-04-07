import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginPageTest extends BaseTest {


    @Test
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        loginPage.navigateTo();
        loginPage.enterEmail("karamfilovs@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
    }
}
