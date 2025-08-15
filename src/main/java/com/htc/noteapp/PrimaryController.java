package com.htc.noteapp;

import com.htc.pojo.Note;
import com.htc.pojo.Tag;
import com.htc.services.BaseService;
import com.htc.services.BoldTextDecorator;
import com.htc.services.ItalicTextDecorator;
import com.htc.services.NormalText;
import com.htc.services.NoteService;
import com.htc.services.SimpleText;
import com.htc.services.TagService;
import com.htc.services.UpdateNoteService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PrimaryController implements Initializable{
    @FXML TableView tbNotes;
    @FXML TextField txtTitle;
    @FXML TextField txtContent;
    @FXML ComboBox<Tag> cbTags;
    @FXML TextFlow txtChange;
    @FXML private ToggleButton boldToggleButton;
    @FXML private ToggleButton italicToggleButton;
    

    private static final BaseService<Note> noteService = new NoteService();
    private static final BaseService<Tag> tagService = new TagService();
    private static final UpdateNoteService uNoteService = new UpdateNoteService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbTags.setItems(FXCollections.observableList(tagService.list()));
            
            this.loadColumn();
            this.tbNotes.setItems(FXCollections.observableList(noteService.list()));
        } catch (SQLException ex) {
            System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        this.txtContent.textProperty().addListener((obs, oldV, newV) -> handlePreview(null));
       
//        this.txtContent.textProperty().addListener((p, oldValue, newValue) ->{
//            this.txtChange.getChildren().clear();
//            
//            Text textNote = new Text(newValue);
//            
//            this.txtChange.getChildren().add(textNote);
//            
//        });
    }
    
    public void handleAddNote(ActionEvent event){
        try {
            if(txtTitle.getText() == null || txtContent.getText() == null){
                System.out.println("Dữ liệu trống...");
                return;
            }
            Date date = new Date(System.currentTimeMillis());
            Note n = new Note(this.tbNotes.getItems().size() + 1, txtTitle.getText(), txtContent.getText(),date ,this.cbTags.getSelectionModel().getSelectedItem());
            this.uNoteService.addNote(n);
            this.tbNotes.getItems().add(n);
            txtTitle.clear();
            txtContent.clear();
            this.cbTags.getSelectionModel().clearSelection();
            this.cbTags.setPromptText("Tags");
        }
        catch (Exception ex) {
            System.out.println("Dữ liệu bị lỗi");
        }
    }
    
    @FXML
    public void handlePreview(ActionEvent event){
        Text textNote = new Text(this.txtContent.getText());
        
        NormalText s = new SimpleText(textNote);
        
        if(boldToggleButton.isSelected()){
            s = new BoldTextDecorator(s);
        }
        
        if(italicToggleButton.isSelected()){
            s = new ItalicTextDecorator(s);
        }
        String finalCss = s.generateCss();
        Text finalText = s.getTxt();
        finalText.setStyle(finalCss);
        this.txtChange.getChildren().clear();
        this.txtChange.getChildren().add(finalText);
    }
    
    
    public void loadColumn(){
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(50);
        
        TableColumn colTitle = new TableColumn("Tiêu đề");
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colTitle.setPrefWidth(100);
        
        TableColumn colContent = new TableColumn("Nội dung");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(100);
        
        TableColumn colDate = new TableColumn("Ngày tạo");
        colDate.setCellValueFactory(new PropertyValueFactory("createdDate"));
        colDate.setPrefWidth(100);
        
        TableColumn colTag = new TableColumn("Tag");
        colTag.setCellValueFactory(new PropertyValueFactory("tag"));
        colTag.setPrefWidth(100);
        
        
        this.tbNotes.getColumns().addAll(colId, colTitle, colContent, colDate, colTag);
        
    }
    
}
