/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import com.htc.pojo.Note;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Admin
 */
public abstract class BaseTemplateViewServices {
    
    public void readFormmat(Note n, TextFlow txtChange){
        Text textNote = new Text(n.getContent());
        NormalText s = new SimpleText(textNote);
        if(n.isIsBold())
        {
            s = new BoldTextDecorator(s);
        }
        if(n.isIsItalic()){
            s = new ItalicTextDecorator(s);
        }

        String css = s.generateCss();

        Text finalText = s.getTxt();
        finalText.setStyle(css);

        txtChange.getChildren().clear();
        txtChange.getChildren().add(finalText);
    }
    
    public abstract void loadColumn(TableView tbNotes, TextFlow txtChanges);
}
