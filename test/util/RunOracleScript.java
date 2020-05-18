/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Clase genérica que se encarga de ejecutar un scriptSQL utilizando como dataSource 
 * el proporcionado a la hora de construir el objeto.
 * @author Rober
 */
public class RunOracleScript {

    private String pathPropertiesFile;
    private String pathScriptSQL;
    

    public RunOracleScript(String pathPropertiesFile, String pathScriptSQL) {
        this.pathPropertiesFile = pathPropertiesFile;
        this.pathScriptSQL = pathScriptSQL;
    }

    public DataSource getOracleDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        OracleDataSource oracleDS = null;
        try {
            fis = new FileInputStream(pathPropertiesFile);
            props.load(fis);
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }
    
    public void run() {
        try {
            ScriptRunner scriptRunner = new ScriptRunner(getOracleDataSource().getConnection(), false, true);
            scriptRunner.runScript(new BufferedReader(new FileReader(pathScriptSQL)));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
