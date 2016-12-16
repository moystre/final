/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mytunesapp.GUI.Controller;

import javafx.scene.control.ListCell;
import javafx.util.Callback;
import pl.mytunesapp.GUI.Model.MainAppModel;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import static javafx.application.Platform.runLater;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider; 
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import pl.mytunesapp.BE.PlayList;
import pl.mytunesapp.BE.Song;
import pl.mytunesapp.BLL.MainAppManager;
import pl.mytunesapp.MainApp;


/**
 * FXML Controller class
 *
 * @author MSI GS40 6QE
 */

public class MainViewController implements Initializable {
private MainApp mainApp = new MainApp();
private MainAppModel model = MainAppModel.getInstance();
private MainAppManager manager = new MainAppManager();
public static MainViewController ViewController;
private Song ultimateSelectedSong;
    @FXML 
    private Button clearButton;
    @FXML
    private Label playListTxtHolder;
    @FXML
    private Label songTxtHolder;
    @FXML
    private TableView<PlayList> playListTableView;
    @FXML
    private TableColumn<PlayList, String> playlistNameColumn;
    @FXML
    private TableColumn<PlayList, Integer> playlistSongsAmountColumn;
    @FXML
    private TableColumn<PlayList, String> playlistTimeColumn;
    @FXML
    private Button newPlaylistButton;
    @FXML
    private Button editPlaylistButton;
    @FXML
    private Button deletePlaylistButton;
    @FXML
    private Button playButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private Slider volume;
    @FXML
    private ListView<Song> listOfSongsInPlaylistView;
    @FXML
    private Button deleteSongFromPlaylistButton;
    @FXML
    private Label songPlayedLabel;
    @FXML
    private ProgressBar songProgress;
    @FXML
    private Button addSongToPlaylistButton;
    
    @FXML
    private Button moveSongDownButton;
    @FXML
    private Button moveSongUpButton;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Song> allSongsTableView;
    @FXML
    private TableColumn<Song, String> titleColumn;
    @FXML
    private TableColumn<Song, String> artistColumn;
    @FXML
    private TableColumn<Song, String> categoryColumn;
    @FXML
    private TableColumn<Song, Integer> timeColumn;
    @FXML
    private Button newSongButton;
    @FXML
    private Button deleteSongButton;
    @FXML
    private Button editSongButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button searchButton;
    private boolean isAllSongsTableViewSelected=true;
   


    ObservableList<Song> tableAllSongsContent =  MainAppModel.getInstance().getAllSongsList();
    ObservableList<Song> tableAllSongsContentFilter =  MainAppModel.getInstance().getDataAllSongsList();
    ObservableList<Song> listSongsInPlaylisContent =  MainAppModel.getInstance().getAllSongsInPlaylist();
    ObservableList<PlayList> tableAllPlayListsContent =  MainAppModel.getInstance().getAllPlayListsList();
    
    private MediaPlayer mediaPlayer;
    private Song currentSong;
    private String path;
    
     private void setMediaPlayer()
     {
           currentSong = ultimateSelectedSong;
           path = currentSong.getFilePath();
            
            File bip = new File(path);
            Media hit;
        
           try
           {
               hit = new Media(bip.toURI().toURL().toString());
               mediaPlayer = new MediaPlayer(hit);
           } catch (MalformedURLException ex)
           {
               Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
             
     }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disableButtons();
        fixViews();
        fixListeners();
       // fixSearchOption();
        fixVolume();

    }    
    
    private void setIsAllSongsTableviewSelected(boolean is)
    {
        isAllSongsTableViewSelected=is;
    }
 
    
    @FXML
    private void play(ActionEvent event) {

         if(mediaPlayer==null)setMediaPlayer();
         
         songPlayedLabel.setText(currentSong.getTitle());
         mediaPlayer.setAutoPlay(true);

            if(mediaPlayer.getStatus()==MediaPlayer.Status.PLAYING){
              mediaPlayer.pause();
              playButton.setText("Play");
            }
            else if(mediaPlayer.getStatus()==MediaPlayer.Status.PAUSED){
             mediaPlayer.play();
             playButton.setText("Pause");
            }

        if(allSongsTableView.getSelectionModel().getSelectedItem() != currentSong)
         {
           if(mediaPlayer.getStatus()!=MediaPlayer.Status.PLAYING)
               {   mediaPlayer.stop();
                   setMediaPlayer();
                   mediaPlayer.play();}
            else if(mediaPlayer.getStatus()!=MediaPlayer.Status.PAUSED)
            {       setMediaPlayer(); 
                   mediaPlayer.play();
                         }}
        mediaPlayer.setOnEndOfMedia(new Runnable() {
        @Override public void run() {
        PlayNextSong(); 
         }
});
    }
    
    public void setUltimateSelectedSong(Song selectedSong)
    {
        ultimateSelectedSong=selectedSong;
    }
    public void setTableAllSongsContent(ObservableList<Song> list)
    {
       tableAllSongsContentFilter.setAll(list);
    }
    public void setListSongsInPlaylistContent(ObservableList<Song> list)
    {
       listSongsInPlaylisContent.setAll(list);
    }
    public void refreshViews()
    {
        listOfSongsInPlaylistView.setItems(model.getAllSongsFromPlaylist());
        listOfSongsInPlaylistView.refresh();
        allSongsTableView.itemsProperty().setValue(tableAllSongsContentFilter);
        allSongsTableView.refresh();
        playListTableView.refresh();
    }

        @FXML
    private void addSongToPlaylist(ActionEvent event) {
    model.addSongToPlaylistList(returnPlayListsTableSelection(), returnAllSongsTableSelection());

    }

    @FXML
    private void newPlaylist(ActionEvent event) {
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/mytunesapp/GUI/View/AddPlaylistDialogFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
    }

    @FXML
    private void editPlaylist(ActionEvent event) {
         try {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/mytunesapp/GUI/View/EditPlaylistDialogFXML.fxml"));
               // fxmlLoader.setController(new EditController(allSongsTableView.getSelectionModel().getSelectedItem()));
               
                Parent root2 = (Parent) fxmlLoader.load();
                
                EditPlaylistDialogController editcontroller = fxmlLoader.getController();
                editcontroller.setPlayList(playListTableView.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                Scene scene = new Scene(root2);
                stage.setScene(scene);
                stage.show();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
    
    @FXML
    private void deletePlaylist(ActionEvent event) {
        PlayList selectedPlayList = playListTableView.getSelectionModel().getSelectedItem();
        if(selectedPlayList!=null)
        playListTableView.getItems().remove(selectedPlayList);
    }
    

    @FXML
    private void next(ActionEvent event) {
       PlayNextSong();
    }

    private void PlayNextSong()
    {
        mediaPlayer.stop();
        if(isAllSongsTableViewSelected==false){
          int listSelectedIndex = listOfSongsInPlaylistView.getSelectionModel().getSelectedIndex();
          int listSize = listOfSongsInPlaylistView.getItems().size();
            if(listSelectedIndex+1<listSize){
        listOfSongsInPlaylistView.getSelectionModel().selectNext();}
            else{
        listOfSongsInPlaylistView.getSelectionModel().selectFirst();}
        }
        
        else  {
          int tableSelectedIndex = allSongsTableView.getSelectionModel().getSelectedIndex();
          int tableSize = allSongsTableView.getItems().size();
          if(tableSelectedIndex+1<tableSize){
        allSongsTableView.getSelectionModel().selectNext();}
            else{
        allSongsTableView.getSelectionModel().selectFirst();}
        }

       setMediaPlayer();
       songPlayedLabel.setText(currentSong.getTitle());
       mediaPlayer.play();
    }
    @FXML
    private void previous(ActionEvent event) {
        mediaPlayer.stop();
        if(isAllSongsTableViewSelected==false){
          int listSelectedIndex = listOfSongsInPlaylistView.getSelectionModel().getSelectedIndex();
          int listSize = listOfSongsInPlaylistView.getItems().size();
            if(listSelectedIndex>0){
        listOfSongsInPlaylistView.getSelectionModel().selectPrevious();}
            else{
        listOfSongsInPlaylistView.getSelectionModel().selectLast();}
        }
        
        else  {
          int tableSelectedIndex = allSongsTableView.getSelectionModel().getSelectedIndex();
          int tableSize = allSongsTableView.getItems().size();
          if(tableSelectedIndex>0){
        listOfSongsInPlaylistView.getSelectionModel().selectPrevious();}
            else{
        listOfSongsInPlaylistView.getSelectionModel().selectLast();}
        }

       setMediaPlayer();
       songPlayedLabel.setText(currentSong.getTitle());
       mediaPlayer.play();
    }

    @FXML
    private void deleteSongFromPlaylist(ActionEvent event) {
        model.getAllSongsFromPlaylist().remove(listOfSongsInPlaylistView.getSelectionModel().getSelectedItem());
    }



    @FXML
    private void moveSongDown(ActionEvent event) {
        if((listOfSongsInPlaylistView.getSelectionModel().getSelectedIndex() != -1) &&
       (listOfSongsInPlaylistView.getSelectionModel().getSelectedIndex() < (listOfSongsInPlaylistView.getItems().size()-1))){
    int selectedSongIndex = listOfSongsInPlaylistView.getSelectionModel().getSelectedIndex();
    Song tempSong = listSongsInPlaylisContent.get(selectedSongIndex+1);
    Song selectedSong = listSongsInPlaylisContent.get(selectedSongIndex);
    listOfSongsInPlaylistView.getItems().set(selectedSongIndex+1, selectedSong);
    listSongsInPlaylisContent.set(selectedSongIndex+1, selectedSong);
    listOfSongsInPlaylistView.getItems().set(selectedSongIndex, tempSong);
    listSongsInPlaylisContent.set(selectedSongIndex, tempSong);
    refreshViews();
    listOfSongsInPlaylistView.getSelectionModel().select(selectedSongIndex+1);
        }
    }

    @FXML
    private void moveSongUp(ActionEvent event) {
        if(listOfSongsInPlaylistView.getSelectionModel().getSelectedIndex() > 0){
    int selectedSongIndex = listOfSongsInPlaylistView.getSelectionModel().getSelectedIndex();
    Song tempSong = listSongsInPlaylisContent.get(selectedSongIndex-1);
    Song selectedSong = listSongsInPlaylisContent.get(selectedSongIndex);
    listOfSongsInPlaylistView.getItems().set(selectedSongIndex-1, selectedSong);
    listSongsInPlaylisContent.set(selectedSongIndex-1, selectedSong);
    listOfSongsInPlaylistView.getItems().set(selectedSongIndex, tempSong);
    listSongsInPlaylisContent.set(selectedSongIndex, tempSong);
    refreshViews();
    listOfSongsInPlaylistView.getSelectionModel().select(selectedSongIndex-1);
    
        }
    }

    @FXML
    private void newSong(ActionEvent event) {               
       try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/mytunesapp/GUI/View/AddSongDialogFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
    }


    public PlayList returnPlayListsTableSelection() {
        PlayList playlist =  playListTableView.getSelectionModel().getSelectedItem();
        if(playlist==null)
        return new PlayList();
        else
        return  playlist;
    }    
    public Song returnAllSongsTableSelection() {
        return allSongsTableView.getSelectionModel().getSelectedItem();
    }    
    public ObservableList<Song> getSongsInPlaylist()
            {
               return model.getAllSongsFromPlaylist();
            }
    @FXML
    private void deleteSong(ActionEvent event) {
        Song selectedSong = allSongsTableView.getSelectionModel().getSelectedItem();
        model.removeSong(selectedSong);
        
    }
    
    

    @FXML
    private void editSong(ActionEvent event) {
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/mytunesapp/GUI/View/EditSongDialogFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Song song = allSongsTableView.getSelectionModel().getSelectedItem();
                EditSongDialogController editcontroller = fxmlLoader.getController();
                editcontroller.setSong(song);
                Stage stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
             
    }
    

    @FXML
    private void close(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
        String userInput=searchField.getText().trim();
        manager.filterSongs(userInput, tableAllSongsContent);
        refreshViews();
    }
    @FXML
    private void clearFilter(ActionEvent event) {
        searchField.clear();
        setTableAllSongsContent(model.getAllSongsList());
    }
    
    
      private void fixListeners(){

    playListTableView.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<PlayList>() {
        @Override
        public void changed(ObservableValue<? extends PlayList> ov, PlayList t, PlayList t1) {
           refreshViews();
        }
    }
    );
    
    allSongsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
    listOfSongsInPlaylistView.getSelectionModel().clearSelection();
    setUltimateSelectedSong(newSelection);
        setIsAllSongsTableviewSelected(true);
    }
});

listOfSongsInPlaylistView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        allSongsTableView.getSelectionModel().clearSelection();
        setUltimateSelectedSong(newSelection);
        setIsAllSongsTableviewSelected(false);
        
    }
});
    
    }

    private void fixViews(){
                allSongsTableView.itemsProperty().setValue(tableAllSongsContent);
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<Song, String>("Title")
        );
         artistColumn.setCellValueFactory(
                new PropertyValueFactory<Song, String>("Artist")
        );
          categoryColumn.setCellValueFactory(
                new PropertyValueFactory<Song, String>("Category")
        );
           timeColumn.setCellValueFactory(
                new PropertyValueFactory<Song, Integer>("Time")
        );
           
           playListTableView.itemsProperty().setValue(tableAllPlayListsContent);
           
           playlistNameColumn.setCellValueFactory(
                   new PropertyValueFactory<PlayList, String>("Name"));
           playlistTimeColumn.setCellValueFactory(
                   new PropertyValueFactory<PlayList, String>("Duration"));
           playlistSongsAmountColumn.setCellValueFactory(
                   new PropertyValueFactory<PlayList, Integer>("NumberOfSongs"));
           
           listOfSongsInPlaylistView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {

            @Override
            public ListCell<Song> call(ListView<Song> param) {
                ListCell<Song> cell = new ListCell<Song>() {

                    @Override
                    protected void updateItem(Song item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getTitle());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });
       
    }
    private void fixSearchOption(){
        searchField.textProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                manager.filterSongs(newValue, tableAllSongsContent);
                refreshViews();
            }
        });
        
    }
    private void disableButtons(){
        newSongButton.disableProperty().bind(Bindings.isNotEmpty(searchField.textProperty()));
        deleteSongButton.disableProperty().bind(Bindings.isNotEmpty(searchField.textProperty()));
        editPlaylistButton.disableProperty().bind(Bindings.isEmpty(playListTableView.getSelectionModel().getSelectedItems()));
        deletePlaylistButton.disableProperty().bind(Bindings.isEmpty(playListTableView.getSelectionModel().getSelectedItems()));
        editSongButton.disableProperty().bind(Bindings.isEmpty(allSongsTableView.getSelectionModel().getSelectedItems()));
        deleteSongButton.disableProperty().bind(Bindings.isEmpty(allSongsTableView.getSelectionModel().getSelectedItems()));
    }
            private void fixVolume()
        {
        ViewController=this;
     volume.adjustValue(50);
     volume.valueProperty().addListener(new ChangeListener<Number>() 
         {
             @Override 
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
             {
                    mediaPlayer.setVolume(volume.getValue()/100);
             }
        });
        }
}
