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

public class ClientPageTest extends BaseTest {


    @Test
    @DisplayName("Can create new client")
    public void canCreateNewClient(){
        loginPage.defaultLogin();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
        homePage.clickClientLink();
        clientPage.createClient("Pragmatic " + LocalDateTime.now(), "Student district", "Sofia");
        Assertions.assertEquals("Клиентът е добавен успешно.", clientPage.getSuccessMessage());
        //Clean test data via API
        clientAPI.deleteAll();
    }

    @Test
    @DisplayName("Can view correct message when no clients exist")
    public void canViewCorrectMessageWhenNoClientsExist(){
        clientAPI.deleteAll();
        loginPage.defaultLogin();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
        homePage.clickClientLink();
        Assertions.assertEquals("Все още нямате добавени клиенти.", clientPage.getEmptyListMessage());
    }


}
