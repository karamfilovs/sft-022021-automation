package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientPage {
    private WebDriver driver;

    public ClientPage(WebDriver driver){
        this.driver = driver;
    }

    public void createClient(String name, String address, String city){
        clickAddNewClientButton();
        enterName(name);
        enterAddress(address);
        enterCity(city);
        clickSaveButton();
    }

    private void enterName(String name){
        System.out.println("Entering name: " + name);
        WebElement input = driver.findElement(By.name("firm_name"));
        input.clear();
        input.sendKeys(name);
    }

    private void enterAddress(String address){
        System.out.println("Entering address: " + address);
        WebElement input = driver.findElement(By.name("firm_addr"));
        input.clear();
        input.sendKeys(address);
    }

    private void enterCity(String city){
        System.out.println("Entering city name: " + city);
        WebElement input = driver.findElement(By.name("firm_town"));
        input.clear();
        input.sendKeys(city);
    }

    private void clickSaveButton(){
        System.out.println("Clicking Save button");
        WebElement button = driver.findElement(By.name("do_submit"));
        button.click();
    }

    private void clickAddNewClientButton(){
        System.out.println("Clicking Add New Item link");
        WebElement newButton = driver.findElement(By.xpath("//a[@class='newbtn selenium-add-client-button']"));
        newButton.click();
    }

    public String getSuccessMessage() {
        WebElement logoutSuccessDiv = driver.findElement(By.id("okmsg"));
        return logoutSuccessDiv.getText().trim();
    }

    public String getEmptyListMessage(){
        WebElement emptyDiv = driver.findElement(By.id("emptylist"));
        return emptyDiv.getText().trim();
    }
}