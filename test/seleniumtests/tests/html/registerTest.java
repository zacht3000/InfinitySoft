/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtests.tests.html;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import seleniumtests.SeleniumTest;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class registerTest extends SeleniumTest{
    
    @Before
    public void accederLinkDeRegister(){
        clickAndWait(driver.findElement(By.id("register"))); 
    }
    
    @Test
    public void testTittle() {
        assertEquals("INFINITYSOFT", driver.getTitle());
    }
    
    @Test
    public void testComprobarDatoVacioNombreCorreo() {
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Introduce tu nickname.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarDatoVacioNombre() {
        driver.findElement(By.id("nickname")).sendKeys("User");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Introduce tu nombre.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarDatoVacioApellidos() {
        driver.findElement(By.id("nickname")).sendKeys("User");
        driver.findElement(By.id("nombre")).sendKeys("User");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Introduce tu apellidos.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarDatoVacioCorreo() {
        driver.findElement(By.id("nickname")).sendKeys("User");
        driver.findElement(By.id("nombre")).sendKeys("User");
        driver.findElement(By.id("apellidos")).sendKeys("User");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Introduce tu correo.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarDatoCorreoIncorrecto() {
        driver.findElement(By.id("nickname")).sendKeys("User");
        driver.findElement(By.id("nombre")).sendKeys("User");
        driver.findElement(By.id("apellidos")).sendKeys("User");
        driver.findElement(By.id("correo")).sendKeys("user@user");
        driver.findElement(By.id("contrasenya")).sendKeys("1234");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("El e-mail no cumple los requisitos.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testComprobarDatoVacioContrasenya() {
        driver.findElement(By.id("nickname")).sendKeys("User");
        driver.findElement(By.id("nombre")).sendKeys("User");
        driver.findElement(By.id("apellidos")).sendKeys("User");
        driver.findElement(By.id("correo")).sendKeys("user@user.com");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Introduce tu contrasenya.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void testUserRegisterCorrect() {

        driver.findElement(By.id("nickname")).sendKeys("User");
        driver.findElement(By.id("nombre")).sendKeys("User");
        driver.findElement(By.id("apellidos")).sendKeys("User");
        driver.findElement(By.id("correo")).sendKeys("user@user.com");
        driver.findElement(By.id("contrasenya")).sendKeys("1234");
        clickAndWait(driver.findElement(By.id("send")));
        assertEquals("Registrado correctamente.", driver.findElement(By.id("correcto")).getText());
    }
    
}
