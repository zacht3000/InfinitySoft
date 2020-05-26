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
import seleniumtests.SeleniumTest;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class contactTest extends SeleniumTest{
    
    private static final String NOMBRE_Y_APELLIDO_ANONIMO = "Usuario";
    private static final String ADMIN = "admin";
    private static final String ERROR_NOMBRE = "nombre";
    private static final String ERROR_APELLIDOS = "apellidos";
    private static final String ERROR_PREGUNTA = "pregunta";
    private static final String ID_COMENTARIO = "comentario";
    private static final String ID_CONTRASENYA = "contrasenya";
    
    @Before
    public void accederLinkDeContact(){
       accederContact();
    }

    @Test
    public void testDatoVacioParaAnonimoNombre() {
        clickSend();
        assertError(ERROR_NOMBRE);
    }
    
    @Test
    public void testDatoVacioParaAnonimoApellidos() {
        añadirParametro(ERROR_NOMBRE, NOMBRE_Y_APELLIDO_ANONIMO);
        clickSend();
        assertError(ERROR_APELLIDOS);
    }
    
    @Test
    public void testDatoVacioParaAnonimoPregunta() {
        añadirParametro(ERROR_NOMBRE, NOMBRE_Y_APELLIDO_ANONIMO);
        añadirParametro(ERROR_APELLIDOS, NOMBRE_Y_APELLIDO_ANONIMO);
        clickSend();
        assertError(ERROR_PREGUNTA);
    }
    
    @Test
    public void testEnviarDatosAnonimo() {
        añadirParametro(ERROR_NOMBRE, NOMBRE_Y_APELLIDO_ANONIMO);
        añadirParametro(ERROR_APELLIDOS, NOMBRE_Y_APELLIDO_ANONIMO);
        añadirParametro(ID_COMENTARIO, NOMBRE_Y_APELLIDO_ANONIMO);
        clickSend();
        assertCorrecto();
    }
    
    @Test
    public void testDatoVacioParaAdminPregunta(){
        logearse();
        clickSend();
        assertError(ERROR_PREGUNTA);
        
    }
    
    @Test
    public void testEnviarDatosAdmin(){
        logearse();
        clickSend();
        añadirParametro(ID_COMENTARIO, NOMBRE_Y_APELLIDO_ANONIMO);
        clickSend();
        assertCorrecto();
        
    }
    
    public void accederContact(){
       clickAndWait(driver.findElement(By.id("icon")));
       clickAndWait(driver.findElement(By.id("contact")));
    }
    

    public void logearse(){
        clickAndWait(driver.findElement(By.id("login")));
        añadirParametro(ERROR_NOMBRE, ADMIN);
        añadirParametro(ID_CONTRASENYA, ADMIN);
        clickSend();
        accederContact();
    }
    
//    @Test
//    public void logearse(){
//        
//    }
//    
    public void assertError(String error) {
        assertEquals("Introduce tu " + error + ".", driver.findElement(By.id("error")).getText());
    }
    
    public void assertCorrecto() {
        assertEquals("Se ha enviado al equipo de soporte su duda, no tardaremos en respoder.", driver.findElement(By.id("correcto")).getText());
    }
    
    public void añadirParametro(String id, String texto){
        driver.findElement(By.id(id)).sendKeys(texto);
    }
    
    public void clickSend(){
        clickAndWait(driver.findElement(By.id("send")));
    }
    
//    @Test
//    public void testDatoVacioNombreAnonimo() {
//        driver.findElement(By.id("contact"));
//    }
    
}
