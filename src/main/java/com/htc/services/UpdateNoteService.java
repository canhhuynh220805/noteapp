/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import com.htc.pojo.Note;
import com.htc.utils.MyConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class UpdateNoteService {
    
    public void addNote(Note n) throws SQLException{
        Connection conn = MyConnector.getInstance().connect();
        
        
        
        conn.setAutoCommit(false);
        
        String sql = "insert into note(id, title, content, created_date ,tag_id) VALUES(?,?,?,?,?)";
        
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setInt(1, n.getId());
        stm.setString(2, n.getTitle());
        stm.setString(3, n.getContent());
        stm.setDate(4, (Date)n.getCreatedDate());
        stm.setInt(5, n.getTag().getId());
        
        if (stm.executeUpdate() > 0) {
            int nId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next())
                nId = r.getInt(1);
            
            conn.commit();
        }
        else{
            conn.rollback();
        }

    }
}
