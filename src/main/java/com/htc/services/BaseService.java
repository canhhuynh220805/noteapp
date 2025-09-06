/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import com.htc.utils.MyConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public abstract class BaseService<T> {
    public abstract PreparedStatement getStm(Connection conn) throws SQLException;
    public abstract List<T> getResult(ResultSet rs) throws SQLException;
    
    public List<T> list() throws SQLException{
        Connection conn = MyConnector.getInstance().connect();
        PreparedStatement stm = getStm(conn);
        
        return this.getResult(stm.executeQuery());
    }
}
