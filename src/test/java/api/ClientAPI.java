package api;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class ClientAPI {
    private static final String BASE_URL = System.getProperty("url");
    private static final String BASE_PATH = "/RESTapi";
    private static final String EMAIL = System.getProperty("email");
    private static final String PASSWORD = System.getProperty("password");
    private static final String CLIENT_URL = "/client";
    private static final String CLIENTS_URL = "/clients";

    static {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        RestAssured.authentication = RestAssured.preemptive().basic(EMAIL, PASSWORD);
    }

    //Retrieves all clients
    public Response getAll() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(CLIENTS_URL);
        response.prettyPrint();
        return response;
    }

    /*
     Deletes client by id
     */
    public Response delete(String id) {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .delete(CLIENT_URL + "/" + id);
        response.prettyPrint();
        return response;
    }

    public void deleteAll() {
        //Get all clients
        Response response = getAll();
        //Get all ids from the clients
        List<String> ids = JsonPath.read(response.body().asString(), "$..id");
        if (ids.isEmpty()) {
            System.out.println("No items for deletion found");
        } else {
            System.out.println("Ids found for deletion => " + ids.toString());
        }

        //Delete all ids by calling deleteClient method
        ids.forEach(id -> delete(id));
    }

}
