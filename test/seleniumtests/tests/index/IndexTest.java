/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtests.tests.index;

import seleniumtests.tests.html.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import seleniumtests.SeleniumTest;

/**
 * Test selenium para las funcionalidades de la página "index.html"
 * @author Rober
 */
public class IndexTest extends SeleniumTest {
    
    @Test
    public void testBotonInicialLlevaAMenuInicial() {
        String titulo = driver.getTitle();
       
        assertEquals("INFINITYSOFT", titulo);
    }
    
}
