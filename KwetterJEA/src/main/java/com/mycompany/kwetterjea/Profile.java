/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea;

import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class Profile {
    private BufferedImage Image;
    private String Bio;
    private String Location;
    private String Url;
    
    public Profile(){
        
    }

    public BufferedImage getImage() {
        return Image;
    }

    public boolean setImage(BufferedImage Image) {
        if(!Image.equals(null)){
            this.Image = Image; 
            return true;
        }
        return false;
    }

    public String getBio() {
        return Bio;
    }

    public boolean setBio(String Bio) {
        if(Bio.length() <= 160){
            this.Bio = Bio; 
            return true;
        }
        return false;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
}
