/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit;

import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import usuarios.ListaUsuarios;
import usuarios.TipoUsuario;
import usuarios.Usuario;
import util.DataSourceTest;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class TestListaUsuarios extends DataSourceTest{
    private final ListaUsuarios listaUsuarios;
    
    public TestListaUsuarios(){
        super();
        this.listaUsuarios = new ListaUsuarios(this.dataSource);
    }
    
    @Test
    public void yaExisteNickNameOrCorreo(){
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            Usuario usuarios = this.listaUsuarios.getBuscaUsuarioNickNameOrCorreo(usuarios1.getNickName());
            assertEquals(usuarios.getNickName(), "UserR");
        } catch (SQLException ex) {
            fail();
        }
        
    
    }
    
//    @Test
//    public void noExisteNickNameOrCorreo() {
//        
//        try {
//            this.listaUsuarios.getBuscaUsuarioNickNameOrCorreo("User");
//            fail();
//        } catch (SQLException ex) {
//            
//        }
//    }
    
    @Test
    public void getBuscaUsuarioNickNameOrCorreo(){
        
        try {
            Usuario usuarios1 = new Usuario("UserR", "user@gmail.com", "Usuario", "Usuario", "1234", TipoUsuario.NORMAL);
            this.listaUsuarios.mete(usuarios1);
            Usuario usuarios = listaUsuarios.getBuscaUsuarioNickNameOrCorreo(usuarios1.getNickName());
            assertEquals(usuarios.getNickName(), usuarios1.getNickName());
        } catch (SQLException ex) {
            fail();
        }
        
    
    }
    
}
