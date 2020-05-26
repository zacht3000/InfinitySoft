/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittests.main.formlarios;

import java.sql.SQLException;
import main.formularios.ListaFormularios;
import org.junit.Test;
import unittests.UnitTest;
import static org.junit.Assert.*;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class ListaFormulariosTest extends UnitTest{
    
    private final ListaFormularios listaFormulario;
    
    public ListaFormulariosTest(){
        super();
        this.listaFormulario = new ListaFormularios(this.dataSource);
    }
    
    @Test
    public void testGetIdSiguiente(){
        try{
            assertEquals(1, listaFormulario.getIdSiguiente());
        }catch(SQLException ex){
            fail();
        }
        
    }
    
    
}
