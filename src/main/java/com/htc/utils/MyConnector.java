/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Admin
 */
public class MyConnector {
    private static MyConnector instance;
    private final Connection conn;
    
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(MyConnector.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    private MyConnector() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/notedb", "root", "123456");
    }
    
    public static MyConnector getInstance() throws SQLException{
        if(instance == null)
            instance = new MyConnector();
        return instance;
    }
    
    public Connection connect(){
        return this.conn;
    }
    
    public void close() throws SQLException{
        if(this.conn != null)
            this.conn.close();
    }
}
