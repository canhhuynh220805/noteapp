/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import com.htc.pojo.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TagService extends BaseService<Tag>{

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("select * from tag ");
    }

    @Override
    public List<Tag> getResult(ResultSet rs) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        while(rs.next()){
            Tag t = new Tag(rs.getInt("id"), rs.getString("name"));
            tags.add(t);
        }
        
        return tags;
    }
    
}
