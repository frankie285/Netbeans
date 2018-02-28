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

    public void setImage(BufferedImage Image) {
        this.Image = Image;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
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
