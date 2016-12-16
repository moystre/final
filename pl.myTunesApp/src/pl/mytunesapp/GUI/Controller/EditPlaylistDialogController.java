/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mytunesapp.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.mytunesapp.BE.PlayList;
import pl.mytunesapp.GUI.Model.MainAppModel;

/**
 * FXML Controller class
 *
 * @author MSI GS40 6QE
 */
public class EditPlaylistDialogController implements Initializable {
    private PlayList playlist;
    private MainAppModel model = MainAppModel.getInstance();
    @FXML
    private TextField playlistDialogNameField;
    @FXML
    private Button playlistDialogCancelButton;
    @FXML
    private Button playlistDialogSaveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void playlistDialogCancel(ActionEvent event) {
        try {
            Stage stage = (Stage) playlistDialogNameField.getScene().getWindow();
            stage.close();
            } catch(Exception e) {
           e.printStackTrace();
          }
    }

    @FXML
    private void playlistDialogSave(ActionEvent event) {
        playlist.setName(playlistDialogNameField.getText().trim());
        Stage stage = (Stage) playlistDialogNameField.getScene().getWindow();
        stage.close();
    }
    
    public void setPlayList(PlayList playlist) {
        this.playlist = playlist;
                playlistDialogNameField.setText(playlist.getName());
                
        
    }
}
