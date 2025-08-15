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
public class BoldTextDecorator extends TextDecorator{
    
    public BoldTextDecorator(NormalText s) {
        super(s);
    }

    @Override
    public String generateCss() {
        return super.generateCss() + "-fx-font-weight: bold;";
    }
    
}
