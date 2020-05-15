/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;

/**
 *
 * @author Rober
 */
public abstract class DataSourceTest {

    protected DataSource dataSource;

    public DataSourceTest() {

        this.dataSource = DataSourceFactory2.getOracleDataSource();

    }

    @After
    public void resetBD() {

        try {
            ScriptRunner scriptRunner = new ScriptRunner(this.dataSource.getConnection(), false, true);
            scriptRunner.runScript(new BufferedReader(new FileReader("InfinitySoft_Test.sql")));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

