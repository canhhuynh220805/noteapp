/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class SimpleText implements NormalText{
    private Text txtContent;

    public SimpleText(Text txtContent) {
        this.txtContent = txtContent;
    }
   
    @Override
    public String generateCss() {
        return "";
    }

    /**
     * @return the txtContent
     */
    public Text getTxtContent() {
        return txtContent;
    }

    /**
     * @param txtContent the txtContent to set
     */
    public void setTxtContent(Text txtContent) {
        this.txtContent = txtContent;
    }

    @Override
    public Text getTxt() {
        return this.getTxtContent();
    }
    
    
    
}
