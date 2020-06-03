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
    private static final String ID_EMAIL = "correo";
    
    @Test
    public void datoVacioNickName(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("nickname");
        send();
        assertEquals("Introduce tu nickname.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void datoVacioNombre(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("nombre");
        send();
        assertEquals("Introduce tu nombre.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void datoVacioApellidos(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("apellidos");
        send();
        assertEquals("Introduce tu apellidos.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void datoVacioCorreo(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("correo");
        send();
        assertEquals("Introduce tu correo.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void datoVacioContraaenya(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("contrasenya");
        send();
        assertEquals("Introduce tu contrasenya.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void formatoIncorrectoCorreo(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato(ID_EMAIL);
        anyadirParametro(ID_EMAIL,"prueba@gmail");
        send();
        assertEquals("El e-mail no cumple los requisitos.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void formatoIncorrectoNickName(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("nickname");
        anyadirParametro("nickname","aaaaaaaaaa");
        send();
        assertEquals("El nombre de usuario no cumple los requisitos.", driver.findElement(By.id("error")).getText());
    }
    
    @Test
    public void formatoIncorrectoContrasenya(){
        accederConUsuario();
        seleccionarUsuario();
        borrarDato("contrasenya");
        anyadirParametro("contrasenya","1234568");
        send();
        assertEquals("La contraseña no cumple los requisitos.", driver.findElement(By.id("error")).getText());
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

    public void anyadirParametro(String id, String texto) {
        driver.findElement(By.id(id)).sendKeys(texto);
    }

    public void loginAdmin(String nombre, String contrasenya) {
        anyadirParametro("nombre", nombre);
        anyadirParametro("contrasenya", contrasenya);
        send();
    }

    public void loginNoAdmin() {
        anyadirParametro("nombre", "anonimo");
        anyadirParametro("contrasenya", "1234");
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
