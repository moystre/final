/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mytunesapp.BE;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI GS40 6QE
 */
public class PlayList {
    
    private  StringProperty name; 
    private  StringProperty duration;
    private  IntegerProperty numberOfSongs;
    private  ObservableList<Song> listOfSongs; 

    public PlayList()
    {
        name = new SimpleStringProperty();
        duration = new SimpleStringProperty();
        numberOfSongs = new SimpleIntegerProperty();
        this.listOfSongs = FXCollections.observableArrayList();
    }
    
    public PlayList(String aname, String aduration, int anumberofsongs,ObservableList<Song> alistofsongs)
    {
        name.set(aname);
        duration.set(aduration);
        numberOfSongs.set(anumberofsongs);
        listOfSongs.addAll(listOfSongs);
    }
    
    public void addSong(Song song)
    {
        listOfSongs.add(song);
    }
    
      public StringProperty nameProperty() {
        return name;
    }
      public StringProperty durationProperty() {
        return duration;
    }
      public IntegerProperty numberOfSongsProperty() {
        return numberOfSongs;
    }


      public String getName()
    {
        return name.get();
    }
     public String getDuration()
    {
        return duration.get();
    }   
      public int getNumberOfSongs()
    {
        return numberOfSongs.getValue();
    }
      public ObservableList<Song> getListOfSongs()
      {
          return listOfSongs;
      }

    public void setName(String value)
    {
        name.set(value);
    }
    public void setDuration(String value)
    {
        duration.set(value);
    }
    public void setNumberOfSongs(int value)
    {
        numberOfSongs.set(value);
    }
    public void setListOfSongs(List<Song> list)
    {
       listOfSongs.clear();
       if(list !=null)
       listOfSongs.addAll(list);
    }
      
}