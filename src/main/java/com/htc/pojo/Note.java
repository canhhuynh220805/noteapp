/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.pojo;

import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class Note {
    /**
     * @return the createdDate
     */
    
    
    private int id;
    private String title;
    private String content;
    private Date createdDate;
    private Tag tag;
    private boolean isBold;
    private boolean isItalic;

    public Note(int id, String title, String content, Date createdDate, Tag tag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.tag = tag;
        this.isBold = this.isItalic = false;
    }
    
    public Note(String title, String content, Date createdDate, Tag tag) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.tag = tag;
        this.isBold = this.isItalic = false;
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the created_date
     */
    

    /**
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the isBold
     */
    public boolean isIsBold() {
        return isBold;
    }

    /**
     * @param isBold the isBold to set
     */
    public void setIsBold(boolean isBold) {
        this.isBold = isBold;
    }

    /**
     * @return the isItalic
     */
    public boolean isIsItalic() {
        return isItalic;
    }

    /**
     * @param isItalic the isItalic to set
     */
    public void setIsItalic(boolean isItalic) {
        this.isItalic = isItalic;
    }
    
}
