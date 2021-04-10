import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
    @DisplayName("Can create new client - negative")
    public void canCreateNewClientNegative(){
        loginPage.defaultLogin();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
        homePage.clickClientLink();
        clientPage.createClient("", "Student district", "Sofia");
        Assertions.assertEquals("Моля, въведете име на фирмата.", clientPage.getErrorMessage());
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

    @Test
    @DisplayName("Can get all clients via API")
    public void canCanGetAllClientsViaAPI(){
        clientAPI.deleteAll(); //Delete all clients
        Response response = clientAPI.getAll();
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals( "{\"clients\":[]}", response.getBody().asString());
    }

    @Test
    @DisplayName("Search clients")
    public void canSearchClientsViaAPI(){
        loginPage.defaultLogin();
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getUserPanelText());
        homePage.clickClientLink();
        clientPage.createClient("Pragmatic " + LocalDateTime.now(), "Student district", "Sofia");
        Assertions.assertEquals("Клиентът е добавен успешно.", clientPage.getSuccessMessage());
        homePage.clickClientLink();
        clientPage.expandSearch();
        clientPage.search("Pragmatic");
        Assertions.assertEquals("Pragmatic", clientPage.getClientListItem().split(" ")[0]);
        //Clean test data via API
        clientAPI.deleteAll();
    }

}
