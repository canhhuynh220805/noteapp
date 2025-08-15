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
public class TextDecorator implements NormalText{
    protected NormalText s;

    public TextDecorator(NormalText s) {
        this.s = s;
    }
    
    
    
    @Override
    public String generateCss() {
        return s.generateCss();
    }

    @Override
    public Text getTxt() {
        return s.getTxt();
    }
    
}
