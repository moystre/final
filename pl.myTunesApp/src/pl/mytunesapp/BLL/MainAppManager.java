/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mytunesapp.BLL;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.mytunesapp.BE.PlayList;
import pl.mytunesapp.BE.Song;
import pl.mytunesapp.GUI.Model.MainAppModel;

/**
 *
 * @author MSI GS40 6QE
 */
public class MainAppManager {
    
    public MainAppManager(){
        
    }

    public void addSong(String artist, String category, String title, String fpath, String time)
    {
       Song newSong = new Song(title, artist, category, time, fpath);
       
       MainAppModel.getInstance().addSong(newSong);
       //bindowanie zeby wyswietlac w liscie z miejsca
       
    }
    
    public void addPlayList(String name)
    {
       PlayList playlist = new PlayList();
       playlist.setName(name);
       playlist.setDuration("0");
       playlist.setNumberOfSongs(0);
       playlist.setListOfSongs(null);
       if(name.length()!=0)
       MainAppModel.getInstance().addPlayList(playlist);
       
       //bindowanie zeby wyswietlac w liscie z miejsca
    }
    public void filterSongs(String userInput, ObservableList<Song> allSongs)
    {
        boolean userFilters;
        if(userInput=="" || userInput==null)
        {
            userFilters=false;
        }
        else
        {
            userFilters=true;
        }
        ObservableList<Song> filteredSongs = FXCollections.observableArrayList();
        for (Song currSong : allSongs) {
            if(currSong.getTitle().toLowerCase().trim().contains(userInput.toLowerCase().trim()))
            {
                filteredSongs.add(currSong);
            }
        }
        MainAppModel.getInstance().setDataAllSongsList(userFilters, filteredSongs);
    }
}