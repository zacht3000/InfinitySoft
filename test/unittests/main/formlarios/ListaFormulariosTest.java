/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittests.main.formlarios;

import main.formularios.ListaFormularios;
import unittests.UnitTest;

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
    
}
