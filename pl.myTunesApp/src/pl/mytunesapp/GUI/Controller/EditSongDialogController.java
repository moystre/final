/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mytunesapp.GUI.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.mytunesapp.BE.Song;
import pl.mytunesapp.GUI.Model.MainAppModel;

/**
 * FXML Controller class
 *
 * @author MSI GS40 6QE
 */
public class EditSongDialogController implements Initializable {
    private Song song;
    private MainAppModel model = MainAppModel.getInstance();

    @FXML
    private Button songDialogCategoryMore;
    @FXML
    private Button songDialogChooseButton;
    @FXML
    private Button songDialogCancelButton;
    @FXML
    private Button songDialogSaveButton;
    @FXML
    private TextField songDialogTitleField;
    @FXML
    private TextField songDialogArtistField;
    @FXML
    private TextField songDialogTimeField;
    @FXML
    private TextField songDialogFilePathField;
    @FXML
    private ComboBox<String> songDialogCategoryComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add rest
        fillComboBox();
    }    
    private void fillComboBox()
    {
        songDialogCategoryComboBox.setItems(FXCollections.observableArrayList("POP", "ROCK", "RAP", "CLASSIC"));
        songDialogCategoryComboBox.getSelectionModel().selectFirst();
    }
    
    
    @FXML
    private void handleChooseSongAction(ActionEvent event) {
         try {
           
                Stage stage = (Stage) songDialogArtistField.getScene().getWindow();

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(stage);
                    if (file != null && file.getName().endsWith(".mp3")) {
                        songDialogFilePathField.setText(file.getAbsolutePath().replace("\\", "/"));
                    }
                    else
                    {
                        //zrobic obsluge bledu
                    }
                
            } catch(Exception e) {
           e.printStackTrace();
          }
    }
    

    @FXML
    private void handleCalcelAction(ActionEvent event) {
         try {
            Stage stage = (Stage) songDialogArtistField.getScene().getWindow();
            stage.close();
            } catch(Exception e) {
           e.printStackTrace();
          }
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        song.setTitle(songDialogTitleField.getText());
        song.setArtist(songDialogArtistField.getText());
        song.setCategory(songDialogCategoryComboBox.getSelectionModel().getSelectedItem().toString());
        song.setFilePath(songDialogFilePathField.getText());
        song.setTime(songDialogTimeField.getText());
        Stage stage = (Stage) songDialogArtistField.getScene().getWindow();
        stage.close();
    }

    public void setSong(Song song) {
        this.song = song;
                songDialogTitleField.setText(song.getTitle());
                songDialogArtistField.setText(song.getArtist());
                songDialogTimeField.setText(song.getTime()+"");
                songDialogFilePathField.setText(song.getFilePath());
                songDialogCategoryComboBox.getSelectionModel().select(0);
        
    }
    
}
