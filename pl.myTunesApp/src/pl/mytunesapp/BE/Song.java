/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mytunesapp.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MSI GS40 6QE
 */
public class Song {
    private static Song instance;
    public static Song getInstance()
    {
        instance = new Song();
        return instance;
    }
    private final StringProperty title; 
    private final StringProperty artist;
    private final StringProperty category;
    private final StringProperty time; 
    private final StringProperty filePath; 

public Song() {
        title = new SimpleStringProperty();
        artist = new SimpleStringProperty();
        category = new SimpleStringProperty();
        time = new SimpleStringProperty();
        filePath = new SimpleStringProperty();
    }    

    public Song(String title, String artist, String category, String time, String filePath) {
        this();
        this.title.set(title);
        this.artist.set(artist);
        this.category.set(category);
        this.time.set(time);
        this.filePath.set(filePath);
    }

    public StringProperty artistProperty() {
        return this.artist;
    }
    public StringProperty titleProperty() {
        return this.title;
    }
    public StringProperty categoryProperty() {
        return this.category;
    }
    public StringProperty filePathProperty() {
        return this.filePath;
    }
    public StringProperty timeProperty() {
        return this.time;
    }
    
    
    public String getTitle()
    {
        return title.get();
    }
    public String getArtist()
    {
        return artist.get();
    }
    public String getCategory()
    {
        return category.get();
    }
    public String getFilePath()
    {
        return filePath.get();
    }
    public String getTime()
    {
        return time.get();
    }
    
    
    public void setTitle(String value)
    {
        title.set(value);
    }
    public void setCategory(String value)
    {
        category.set(value);
    }
    public void setArtist(String value)
    {
        artist.set(value);
    }
    public void setFilePath(String value)
    {
        filePath.set(value);
    }
    public void setTime(String value)
    {
        time.set(value);
    }
    
    
}
