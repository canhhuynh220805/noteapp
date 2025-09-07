/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import com.htc.pojo.Note;
import com.htc.pojo.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NoteService extends BaseService<Note>{

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
//        return conn.prepareCall("SELECT n.id, n.title, n.content, n.created_date, t.id, t.name FROM note AS n JOIN tag AS t ON n.tag_id = t.id");
          return conn.prepareCall("select n.id,  n.title, n.content, n.created_date, t.id, t.name, n.is_bold, n.is_italic from note as n, tag as t where n.tag_id = t.id");
    }

    @Override
    public List<Note> getResult(ResultSet rs) throws SQLException {
        List<Note> notes = new ArrayList<>();
        while(rs.next()){
//            Tag tag = new Tag(rs.getInt("t.id"), rs.getString("t.name"));
            Tag tag = TagFlyweightFactory.getTag(rs.getInt("t.id"), rs.getString("t.name"));
            int id = rs.getInt("n.id");
            String title = rs.getString("n.title");
            String content = rs.getString("n.content");    
            Date createdDate = rs.getDate("n.created_date");
            boolean isBold = rs.getBoolean("n.is_bold");
            boolean isItalic = rs.getBoolean("n.is_italic");
            Note n = new Note(id, title, content, createdDate, tag, isBold, isItalic);
            
            notes.add(n);
            
        }
        return notes;
    }
    
    
    
}
