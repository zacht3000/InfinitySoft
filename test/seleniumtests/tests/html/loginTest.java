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
        clickAndWait(botonInicio);
        
    }
    
    @Test
    public void testTittle() {
        String titulo = driver.getTitle();
        assertEquals("INFINITYSOFT", titulo);
    }
    
    @Test
    public void testComprobarDatoVacioNombreCorreo() {
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Introduce tu nombre o e-mail.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarDatoVacioContrasenya() {
        driver.findElement(By.id("nombre")).sendKeys("User1");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Introduce tu contraseña.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarContrasenyaIncorrecta(){
        driver.findElement(By.id("nombre")).sendKeys("admin");
        driver.findElement(By.id("contrasenya")).sendKeys("admi");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Contraseña incorrecta pruebe de nuevo.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarUsuarioCorrecto(){
        loginAdmin();
        assertEquals("admin", driver.findElement(By.id("user")).getText());
    }
    
    @Test
    public void testComprobarUsuarioCerrarSession(){
        loginAdmin();
        clickAndWait(driver.findElement(By.id("closeSession")));
        assertEquals("ENTRAR", driver.findElement(By.id("login")).getText());
    }
    
    public void loginAdmin(){
        driver.findElement(By.id("nombre")).sendKeys("admin");
        driver.findElement(By.id("contrasenya")).sendKeys("admin");
        clickAndWait(driver.findElement(By.id("send")));
    }
    
}
