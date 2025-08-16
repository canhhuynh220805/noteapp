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
    @FXML ToggleButton boldToggleButton;
    @FXML ToggleButton italicToggleButton;
    @FXML ComboBox<String> cbViews;

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
            Note n = new Note(txtTitle.getText(),
                    txtContent.getText(),date ,this.cbTags.getSelectionModel().getSelectedItem());
            if(boldToggleButton.isSelected())
                n.setIsBold(true);
            if(italicToggleButton.isSelected())
                n.setIsItalic(true);
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
    

    public void readFormmat(Note n){
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
        colContent.setPrefWidth(120);
        
        TableColumn colDate = new TableColumn("Ngày tạo");
        colDate.setCellValueFactory(new PropertyValueFactory("createdDate"));
        colDate.setPrefWidth(100);
        
        TableColumn colTag = new TableColumn("Tag");
        colTag.setCellValueFactory(new PropertyValueFactory("tag"));
        colTag.setPrefWidth(100);
        
        TableColumn colAction = new TableColumn("Định dạng");
        colAction.setCellFactory(p ->{
            TableCell cell = new  TableCell();
            
            Button b = new Button("Xem định dạng");
            b.setOnAction(event ->{
                Note n = (Note)cell.getTableRow().getItem();
                readFormmat(n);
            });
            cell.setGraphic(b);
            
            return cell;
        });
        
        this.tbNotes.getColumns().addAll(colId, colTitle, colContent, colDate, colTag, colAction);
        
    }
    
}
