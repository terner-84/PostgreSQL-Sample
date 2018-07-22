/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.terner.postgesqlsample;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 *
 * @author terner
 */
public class PostgreSQLDatabaseConnection {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/library";
    
    static final String USER = "postgres";
    static final String PASS = "user";
    
    //records to generate
    static final int RECORDS = 300000;
    
    //records to store in database
    private static List<VssItemDemo> vid;
    
    public static void main(String[] args) throws SQLException {
        
        //prepare records of VssItemDemo
        vid = new ArrayList<>();
        VssItemGenerator vig = new VssItemGenerator(RECORDS);
        long startList = System.currentTimeMillis();
        vid = vig.getViList();
        long endList = System.currentTimeMillis();
        
        //zkouska viListu
//        for (VssItemDemo vss : vid) {
//            System.out.println("ID: " + vss.getVssuid() + "\tTeilNummer: " + vss.getTeilnummer() +
//                    "\tType: " + vss.getVsstype().name() +
//                    "\tEgew: " + vss.getEgew() + "\tGgew: " + vss.getGgew());
//        }
        
//        if (vid.isEmpty()) {
//            
//        } else {
//            return;
//        }
        
        Connection conn = null;
        QueryRunner qr = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        
        //Open connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                              
        //ResultSetHandler<VssItemDemo> rh = new BeanHandler<>(VssItemDemo.class);
        
        long start = System.currentTimeMillis();
        
        int rec = 0;
        
        try {
            
            for (VssItemDemo vss : vid) {
                
                rec += qr.update(conn,
                    "INSERT INTO vssitem(teilnummer,vsstype,egew,ggew,vssuid) VALUES (?,?,?,?,?)",
                    vss.getTeilnummer(),
                    vss.getVsstype().name(),
                    vss.getEgew(),
                    vss.getGgew(),
                    vss.getVssuid()
                );
                if (rec % 5000 == 0) {
                    System.out.println("Stored: " + rec + " records into database.");
                }
            }
            
            //int ir = qr.update(conn,
                    
                    //"INSERT INTO books(title,author_id) VALUES (?,?)",
                    //"Kákona zpívá", 63);

            //System.out.println("Records inserted: " + ir);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            DbUtils.close(conn);
        }
        long end = System.currentTimeMillis();
        
        System.out.println("Time list: " + (endList - startList));
        System.out.println("Time: " + (end - start));
        System.out.println("Records generated: " + RECORDS);
        System.out.println("Time need for writting 1 record to db: " + ((double) (end - start) / RECORDS));
    }
}
