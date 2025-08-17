/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.services;

import com.htc.pojo.Note;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Admin
 */
public class MinimalistViewServices extends BaseTemplateViewServices{

  

    @Override
    public String toString() {
        return "Chế độ tối giản";
    }

    @Override
    public void loadColumn(TableView tbNotes, TextFlow txtChanges) {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(50);
        
        TableColumn colTitle = new TableColumn("Tiêu đề");
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colTitle.setPrefWidth(100);
        
        TableColumn colTag = new TableColumn("Tag");
        colTag.setCellValueFactory(new PropertyValueFactory("tag"));
        colTag.setPrefWidth(100);
        
        TableColumn colAction = new TableColumn("Định dạng");
        colAction.setCellFactory(p ->{
            TableCell cell = new  TableCell();
            
            Button b = new Button("Xem định dạng");
            b.setOnAction(event ->{
                Note n = (Note)cell.getTableRow().getItem();
                readFormmat(n, txtChanges);
            });
            cell.setGraphic(b);
            
            return cell;
        });
        
        tbNotes.getColumns().addAll(colId, colTitle, colTag, colAction);
    }
    
    
}
