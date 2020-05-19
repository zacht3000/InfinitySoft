/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittests.main.usuarios;



import java.sql.SQLException;
import main.Exception.UserNotExistException;

import main.usuarios.ListaUsuarios;
import main.usuarios.TipoUsuario;
import main.usuarios.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;
import unittests.UnitTest;

public class ListaUsuariosTest extends UnitTest{
    
    private final ListaUsuarios listaUsuarios;
    
    public ListaUsuariosTest() {
        super();
        this.listaUsuarios = new ListaUsuarios(this.dataSource);
    }
    
    @Test
    public void yaExisteNickName(){
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            assertTrue(listaUsuarios.yaExisteNickNameOrCorreo(usuarios1.getNickName()));
        } catch (SQLException ex) {
            fail();
        }
    }
    
    @Test
    public void yaExisteCorreo(){
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            assertTrue(listaUsuarios.yaExisteNickNameOrCorreo(usuarios1.getCorreo()));
        } catch (SQLException ex) {
            fail();
        }
    }
    
    @Test
    public void noExisteNickName(){
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            assertFalse(listaUsuarios.yaExisteNickNameOrCorreo("User"));
        } catch (SQLException ex) {
            fail();
        }
    }
    
    @Test
    public void noExisteCorreo(){
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            assertFalse(listaUsuarios.yaExisteNickNameOrCorreo("User@noemail.com"));
        } catch (SQLException ex) {
            fail();
        }
    }
    
    
    @Test
    public void getBuscaUsuarioNickName() throws UserNotExistException{
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            Usuario usuarios = this.listaUsuarios.getBuscaUsuarioNickNameOrCorreo(usuarios1.getNickName());
            assertEquals(usuarios.getNickName(), "UserR");
        }catch (UserNotExistException ex){
            fail();
        } catch (SQLException ex) {
            fail();
        }
    }
    
    @Test
    public void getBuscaUsuarioCorreo() throws UserNotExistException{
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            Usuario usuarios = this.listaUsuarios.getBuscaUsuarioNickNameOrCorreo(usuarios1.getNickName());
            assertEquals(usuarios.getCorreo(), "user@gmail.com");
        }catch (UserNotExistException ex){
            fail();
        } catch (SQLException ex) {
            fail();
        }
    }
    
    @Test
    public void getBuscaUsuarioNickNameException() throws UserNotExistException{
        
        try {
            this.listaUsuarios.getBuscaUsuarioNickNameOrCorreo("User");
            fail();
        }catch (UserNotExistException ex){

        } catch (SQLException ex) {
            
        }
    }
    
    @Test
    public void getBuscaUsuarioCorreoException() throws UserNotExistException{
        
        try {
            this.listaUsuarios.getBuscaUsuarioNickNameOrCorreo("User@noemail.com");
            fail();
        }catch (UserNotExistException ex){
            
        } catch (SQLException ex) {
            
        }
    }
    
    
}
