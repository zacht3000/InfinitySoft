/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtests.tests.html;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import seleniumtests.SeleniumTest;

/**
 * Test selenium para las funcionalidades de la página "index.html"
 * @author Rober
 */
public class loginTest extends SeleniumTest {
    
    @Before
    public void accederLinkDeLogin(){
        WebElement botonInicio = driver.findElement(By.xpath("//a[@href='/InfinitySoft/html/login.jsp']"));
        botonInicio.click();
//        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            
        }
        
    }
    
    @Test
    public void testTittle() {
        String titulo = driver.getTitle();
        assertEquals("INFINITYSOFT", titulo);
    }
    
    @Test
    public void testComprobarDatoVacioNombreCorreo() {
        WebElement enviar = driver.findElement(By.cssSelector("input[type='submit']"));
        enviar.click();
        String error = driver.findElement(By.id("error")).getText();
        assertEquals("Introduce tu nombre o e-mail.", error);
    }
    
    @Test
    public void testComprobarDatoVacioContrasenya() {
        WebElement text = driver.findElement(By.cssSelector("input[type='text']"));
        text.sendKeys("User1");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        String error = driver.findElement(By.id("error")).getText();
        assertEquals("Introduce tu contraseña.", error);
    }
    
}
