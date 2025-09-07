package com.htc.noteapp;

import com.htc.pojo.Note;
import com.htc.pojo.Tag;
import com.htc.services.BaseService;
import com.htc.services.BaseTemplateViewServices;
import com.htc.services.DefaultViewServices;
import com.htc.services.MinimalistViewServices;
import com.htc.services.NoteService;
import com.htc.services.TagService;
import com.htc.services.UpdateNoteService;
import com.htc.services.WorkViewServices;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.TextFlow;

public class PrimaryController implements Initializable {

    @FXML
    TableView tbNotes;
    @FXML
    TextField txtTitle;
    @FXML
    TextField txtContent;
    @FXML
    ComboBox<Tag> cbTags;
    @FXML
    TextFlow txtChange;
    @FXML
    ToggleButton boldToggleButton;
    @FXML
    ToggleButton italicToggleButton;
    @FXML
    ComboBox<BaseTemplateViewServices> cbViews;

    private static final BaseService<Note> noteService = new NoteService();
    private static final BaseService<Tag> tagService = new TagService();
    private static final UpdateNoteService uNoteService = new UpdateNoteService();

    private static final BaseTemplateViewServices DEFAULT_VIEW = new DefaultViewServices();
    private static final BaseTemplateViewServices MINIMALIST_VIEW = new MinimalistViewServices();
    private static final BaseTemplateViewServices WORK_VIEW = new WorkViewServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setUpViewComboBox();
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

    public void setUpViewComboBox() {
        ObservableList<BaseTemplateViewServices> viewModes = FXCollections.observableArrayList(DEFAULT_VIEW, MINIMALIST_VIEW, WORK_VIEW);
        this.cbViews.setItems(viewModes);

        this.cbViews.getSelectionModel().selectedItemProperty().addListener(
                (p, oldView, newView) -> {
                    if (newView != null) {
                        this.tbNotes.getColumns().clear();
                        newView.loadColumn(tbNotes, txtChange);
                    }
                });

        this.cbViews.getSelectionModel().selectFirst();
    }

    public void handleAddNote(ActionEvent event) {
        try {
            if (txtTitle.getText() == null || txtContent.getText() == null) {
                System.out.println("Dữ liệu trống...");
                return;
            }
            Note n = createNoteFromInput();
            this.uNoteService.addNote(n);
            this.tbNotes.getItems().add(n);
            clearInputField();
        } catch (SQLException ex) {
            System.out.println("Dữ liệu bị lỗi");
        }
    }

    public Note createNoteFromInput() {
        Date date = new Date(System.currentTimeMillis());
        Note n = new Note(txtTitle.getText(),
                txtContent.getText(), date, this.cbTags.getSelectionModel().getSelectedItem(),
                boldToggleButton.isSelected(), italicToggleButton.isSelected());
        return n;
    }

    public void clearInputField() {
        txtTitle.clear();
        txtContent.clear();
        this.cbTags.getSelectionModel().clearSelection();
        this.cbTags.setPromptText("Tags");
        this.boldToggleButton.setSelected(false);
        this.italicToggleButton.setSelected(false);
    }
}
