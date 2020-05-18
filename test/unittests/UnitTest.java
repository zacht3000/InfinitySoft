/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittests;

import util.RunOracleScript;
import javax.sql.DataSource;
import org.junit.After;

/**
 * Clase genérica de la que heredan todos los test unitarios en este proyecto que hagan uno del DataSource
 * @author Rober
 */
public abstract class UnitTest {

    private RunOracleScript runOracleScript;
    protected DataSource dataSource;

    public UnitTest() {

        this.runOracleScript = new RunOracleScript("db.properties", "InfinitySoft_Test.sql");
        this.dataSource = this.runOracleScript.getOracleDataSource();
    }

    @After
    public void resetBD() {

        this.runOracleScript.run();

    }
}

