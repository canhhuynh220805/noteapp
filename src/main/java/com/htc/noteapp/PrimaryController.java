package com.htc.noteapp;

import com.htc.pojo.Note;
import com.htc.pojo.Tag;
import com.htc.services.BaseService;
import com.htc.services.BaseTemplateViewServices;
import com.htc.services.BoldTextDecorator;
import com.htc.services.DefaultViewServices;
import com.htc.services.ItalicTextDecorator;
import com.htc.services.MinimalistViewServices;
import com.htc.services.NormalText;
import com.htc.services.NoteService;
import com.htc.services.SimpleText;
import com.htc.services.TagService;
import com.htc.services.UpdateNoteService;
import com.htc.services.WorkViewServices;
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
    @FXML ComboBox<BaseTemplateViewServices> cbViews;

    private static final BaseService<Note> noteService = new NoteService();
    private static final BaseService<Tag> tagService = new TagService();
    private static final UpdateNoteService uNoteService = new UpdateNoteService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<BaseTemplateViewServices> viewModes = FXCollections.observableArrayList();
            viewModes.add(new DefaultViewServices());
            viewModes.add(new MinimalistViewServices());
            viewModes.add(new WorkViewServices());
            this.cbViews.setItems(viewModes);
            this.cbViews.getSelectionModel().selectFirst();
            
            this.cbViews.getSelectionModel().selectedItemProperty().addListener(
                    (p, oldView, newView)->{
                        if(newView != null){
                            this.tbNotes.getColumns().clear();
                            newView.loadColumn(tbNotes, txtChange);
                        }
            });
            
            BaseTemplateViewServices defaultView = this.cbViews.getSelectionModel().getSelectedItem();
            if(defaultView != null)
                defaultView.loadColumn(tbNotes, txtChange);
            
            
            this.cbTags.setItems(FXCollections.observableList(tagService.list()));
            
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
    
    
}
