/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import util.RunOracleScript;

/**
 * Clase genérica que utilizan todos los test Selenium. Se DEBE cambiar la constante URL_WEB para que acceda 
 * a la web que se desea testear
 * @author Rober
 */
public abstract class SeleniumTest {
    
    private static final String URL_WEB = "http://localhost:8080/InfinitySoft/";
    
    protected WebDriver driver;
    
    @Before
    public void setup() {
        
        resetBD();
        System.setProperty("webdriver.chrome.driver", "test/seleniumtests/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_WEB);
    }
    
    private void resetBD() {
        new RunOracleScript("db.properties", "InfinitySoft_Test.sql").run();
    }
    
    protected void clickAndWait(WebElement webElement) {
        
        webElement.click();
        
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex) {
            
        }
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
