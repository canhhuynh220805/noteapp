package com.htc.noteapp;

import com.htc.pojo.Note;
import com.htc.pojo.Tag;
import com.htc.services.BaseService;
import com.htc.services.NoteService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable{
    @FXML TableView tbNotes;
    @FXML TextField txtTitle;
    @FXML TextField txtContent;
    @FXML ComboBox<Tag> cbTags;

    private static final BaseService<Note> noteService = new NoteService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadColumn();
            this.tbNotes.setItems(FXCollections.observableList(noteService.list()));
        } catch (SQLException ex) {
            System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    public void loadColumn(){
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);
        
        TableColumn colTitle = new TableColumn("Tiêu đề");
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colTitle.setPrefWidth(100);
        
        TableColumn colContent = new TableColumn("Nội dung");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(300);
        
        TableColumn colDate = new TableColumn("Ngày tạo");
        colDate.setCellValueFactory(new PropertyValueFactory("createdDate"));
        colDate.setPrefWidth(100);
        
        TableColumn colTag = new TableColumn("Tag");
        colTag.setCellValueFactory(new PropertyValueFactory("tag"));
        colTag.setPrefWidth(100);
        
        this.tbNotes.getColumns().addAll(colId, colTitle, colContent, colDate, colTag);
        
    }
    
}
