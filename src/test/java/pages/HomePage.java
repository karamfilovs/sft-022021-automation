package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public  HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String getUserPanelText(){
        WebElement userPanelHeader = driver.findElement(By.cssSelector("div.userpanel-header"));
        return userPanelHeader.getText().trim();
    }

    public void logout(){
        System.out.println("Logout from the system");
        WebElement userPanelHeader = driver.findElement(By.cssSelector("div.userpanel-header"));
        userPanelHeader.click();
        WebElement logoutLink = driver.findElement(By.xpath("//a[@class='selenium-button-logout button-logout']"));
        logoutLink.click(); //Clicks the logout link
    }

    public void clickClientLink(){
        System.out.println("Clicking Clients link");
        WebElement clientLink = driver.findElement(By.xpath("//a[@href='https://st2016.inv.bg/clients']"));
        clientLink.click();
    }
}
