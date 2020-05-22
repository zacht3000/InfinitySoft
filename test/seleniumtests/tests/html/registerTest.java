/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtests.tests.html;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import seleniumtests.SeleniumTest;
import seleniumtests.tests.index.IndexTest;

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
        assertEquals("Introduce tu nombre de usuario.", driver.findElement(By.id("error")).getText());
    }
}
