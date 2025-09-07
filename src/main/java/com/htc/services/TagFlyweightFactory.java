/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import com.htc.pojo.Tag;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class TagFlyweightFactory {
    public static final Map<String, Tag> tagCache = new HashMap<>();
    
    public static Tag getTag(int id, String name){
        if(tagCache.containsKey(name)){
            return tagCache.get(name);
        }
        Tag t = new Tag(id, name);
        tagCache.put(name, t);
        return t;
    }
}
