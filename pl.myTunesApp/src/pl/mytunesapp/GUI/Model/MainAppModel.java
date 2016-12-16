/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mytunesapp.GUI.Model;

import java.util.List;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import pl.mytunesapp.BE.PlayList;
import pl.mytunesapp.BE.Song;
import pl.mytunesapp.BLL.MainAppManager;
import pl.mytunesapp.GUI.Controller.MainViewController;

/**
 *
 * @author MSI GS40 6QE
 */
public class MainAppModel {
    

   
    
    private static MainAppModel instance;
    private MainAppManager manager = new MainAppManager();
    public static MainAppModel getInstance()
    {
        if(instance==null)
            instance = new MainAppModel();
        return instance;
    }
    public MainViewController getController()
        {
            return MainViewController.ViewController;
        }
    
    private final ObservableList<Song> allSongsList;
    private final ObservableList<PlayList> allPlayListsList;
    private  ObservableList<Song> allSongsInPlaylist;
    private  ObservableList<Song> dataAllSongsList;
    
    public MainAppModel()
    {
        this.allSongsList = FXCollections.observableArrayList(Song ->
            new Observable[] {
                    Song.titleProperty(),
                    Song.artistProperty(),
                    Song.categoryProperty(),
                    Song.timeProperty(),
                    Song.filePathProperty() 
            });
        allSongsList.addListener((Change<? extends Song> c) -> {
            while (c.next()) {
                  if (c.wasUpdated()) {
                  getController().refreshViews();
                 }  
                  if (c.wasAdded()) {
                  getController().refreshViews();
                 } 
                  if (c.wasRemoved()) {
                  getController().refreshViews();
                 } 
                  if (c.wasReplaced()) {
                  getController().refreshViews();
                 } 
                  
           }
        });
            
        this.allPlayListsList = FXCollections.observableArrayList(PlayList ->
            new Observable[] {
                    PlayList.nameProperty(),
                    PlayList.durationProperty(),
                    PlayList.numberOfSongsProperty(),
                    PlayList.getListOfSongs()
            });
        allPlayListsList.addListener((Change<? extends PlayList> c) -> {
            while (c.next()) {
                  if (c.wasUpdated()) {
                  int numberOfSongsInPlaylist = getController().returnPlayListsTableSelection().getListOfSongs().size();
                  getController().returnPlayListsTableSelection().setNumberOfSongs(numberOfSongsInPlaylist);
                  getController().refreshViews(); 
                  }
  
           }
        });
        
        
        this.allSongsInPlaylist = FXCollections.observableArrayList();
        this.dataAllSongsList = FXCollections.observableArrayList(Song ->
            new Observable[] {
                    Song.titleProperty(),
                    Song.artistProperty(),
                    Song.categoryProperty(),
                    Song.timeProperty(),
                    Song.filePathProperty() 
            });
        dataAllSongsList.addListener((Change<? extends Song> c) -> {
            while (c.next()) {
                  if (c.wasUpdated()) {
                  getController().refreshViews();
                 }  
                  if (c.wasAdded()) {
                  getController().refreshViews();
                 } 
                  if (c.wasRemoved()) {
                  getController().refreshViews();
                 } 
                  if (c.wasReplaced()) {
                  getController().refreshViews();
                 } 
           }
        });
    }
    
    public ObservableList<Song> getAllSongsList()
    {
        return allSongsList;
    }
    
    public ObservableList<PlayList> getAllPlayListsList()
    {
        return allPlayListsList;
    }
    
    public ObservableList<Song> getDataAllSongsList()
    {
        return dataAllSongsList;
    }
    
    
    public void addSong(Song song)
    {
        allSongsList.add(song);
        dataAllSongsList.add(song);
    }
    
    public void addPlayList(PlayList playlist)
    {
        allPlayListsList.add(playlist);
    }


    public ObservableList<Song> getAllSongsInPlaylist()
    {      
       return allSongsInPlaylist;
    }
    public ObservableList<Song> getAllSongsFromPlaylist()
    {      
       PlayList playlist = getController().returnPlayListsTableSelection();
       allSongsInPlaylist=playlist.getListOfSongs();
       if(playlist!=null)
       return allSongsInPlaylist;
        else
       return new PlayList().getListOfSongs();

    }
    
     public void addSongToPlaylistList(PlayList playlist, Song song)
    {
       if(song!=null && playlist!=null)
       playlist.addSong(song);
       getController().setListSongsInPlaylistContent(allSongsInPlaylist);
    }
     
     public void setDataAllSongsList(Boolean userFilters, ObservableList<Song> filteredList)
    {
        if(userFilters==true)
        {
        dataAllSongsList=filteredList;
        }
        else
        {
        dataAllSongsList=allSongsList;
        }    
        getController().setTableAllSongsContent(dataAllSongsList);       
     }

     public void removeSong(Song song)
     {
         dataAllSongsList.remove(song);
         allSongsList.remove(song);

     }
}
