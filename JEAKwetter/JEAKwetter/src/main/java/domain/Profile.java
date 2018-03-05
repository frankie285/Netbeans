/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Frank
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "profile.findById", query = "SELECT s FROM Profile s WHERE s.Id = :Id")})

@XmlRootElement
public class Profile implements Serializable{
    @Id @GeneratedValue
    private Long Id;   
    
    private byte[] Image;
    private String Bio;
    
    private String Location;
    private String Url;
    
    public Profile(){
        
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }
    
    public byte[] getImage() {
        return Image;
    }

    public boolean setImage(byte[] Image) {
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
