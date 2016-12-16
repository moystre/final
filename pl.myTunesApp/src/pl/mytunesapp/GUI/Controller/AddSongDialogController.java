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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.mytunesapp.BE.Song;
import pl.mytunesapp.BLL.MainAppManager;
import pl.mytunesapp.GUI.Model.MainAppModel;

/**
 * FXML Controller class
 *
 * @author MSI GS40 6QE
 */
public class AddSongDialogController implements Initializable {

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
    
    Song song = new Song();
    MainAppManager manager = new MainAppManager();
          

    @FXML
    public void handleChooseSongAction(ActionEvent click)throws Exception {               
       try {
           
                Stage stage = (Stage) songDialogArtistField.getScene().getWindow();

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(stage);
                    if (file != null && file.getName().endsWith(".mp3")) {
                        songDialogFilePathField.setText(file.getAbsolutePath().replace("\\", "/"));
                        songDialogTitleField.setText(file.getName());
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
    public void handleCalcelAction(ActionEvent click){               
       try {
            Stage stage = (Stage) songDialogArtistField.getScene().getWindow();
            stage.close();
            } catch(Exception e) {
           e.printStackTrace();
          }
    }
    
     @FXML
    public void handleSaveAction(ActionEvent click) {   
        //obsluga bledow
      manager.addSong(
              songDialogArtistField.getText(),
              songDialogCategoryComboBox.getSelectionModel().getSelectedItem(),
              songDialogTitleField.getText(),
              songDialogFilePathField.getText(),
              songDialogTimeField.getText());
      //jesli przejdzie to close
       Stage stage = (Stage) songDialogArtistField.getScene().getWindow();
       stage.close();
    }
    
    private void fillComboBox()
    {
        songDialogCategoryComboBox.setItems(FXCollections.observableArrayList("POP", "ROCK", "RAP", "CLASSIC"));
        songDialogCategoryComboBox.getSelectionModel().selectFirst();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    fillComboBox();
    }    
    
}
