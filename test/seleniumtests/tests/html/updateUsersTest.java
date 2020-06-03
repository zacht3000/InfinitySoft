/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtests.tests.html;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import seleniumtests.SeleniumTest;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class updateUsersTest extends SeleniumTest {
    
    private static final String NICKNAME_CONTRASNYA_ADMIN = "admin";
    
    @Test
    public void datoVacio(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("nickname");
        send();
        assertEquals("Introduce tu nickname.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void formatoIncorrecto(){
        /*Terminar!!!!!*/
    }
    
    
    public void borrarDato(String nombre){
        driver.findElement(By.id(nombre)).clear();
        
    }

    public void accederConUsuario() {
        accederRegister();
        registrarUsuario();
        accederComoAdmin();
        abrirMenúResponsive();
        accederAdministrar();
    }

    public void registrarUsuario() {

        driver.findElement(By.id("nickname")).sendKeys("User");
        driver.findElement(By.id("nombre")).sendKeys("User");
        driver.findElement(By.id("apellidos")).sendKeys("User");
        driver.findElement(By.id("correo")).sendKeys("user@user.com");
        driver.findElement(By.id("contrasenya")).sendKeys("1234");
        clickAndWait(driver.findElement(By.id("send")));
    }

    public void accederComoAdmin() {
        accederLogin();
        loginAdmin(NICKNAME_CONTRASNYA_ADMIN, NICKNAME_CONTRASNYA_ADMIN);
    }

    public void accederLogin() {
        clickAndWait(driver.findElement(By.id("login")));
    }

    public void atributoCorrecto() {
        assertEquals("ADMINISTRAR", driver.findElement(By.id("administrar")).getText());
    }

    public void errorNoHayUsuarios() {
        assertEquals("No hay usuarios", driver.findElement(By.id("vacio_user")).getText());
    }

    public void añadirParametro(String id, String texto) {
        driver.findElement(By.id(id)).sendKeys(texto);
    }

    public void loginAdmin(String nombre, String contrasenya) {
        añadirParametro("nombre", nombre);
        añadirParametro("contrasenya", contrasenya);
        send();
    }

    public void loginNoAdmin() {
        añadirParametro("nombre", "anonimo");
        añadirParametro("contrasenya", "1234");
        send();
    }

    public void send() {
        clickAndWait(driver.findElement(By.id("send")));
    }

    public void accederRegister() {
        clickAndWait(driver.findElement(By.id("register")));
    }

    public void accederAdministrar() {
        clickAndWait(driver.findElement(By.id("administrar")));
    }
    
    public void seleccionarUsuario() {
        clickAndWait(driver.findElement(By.id("input_user")));
        send();
    }

    public void abrirMenúResponsive() {
        clickAndWait(driver.findElement(By.id("icon")));
    }
}
