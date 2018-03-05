/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.Entity;
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
    @NamedQuery(name = "hashtag.findByText", query = "SELECT s FROM HashTag s WHERE s.Text = :Text")})

@XmlRootElement
public class HashTag {  
    @Id
    private String Text;
    
    public HashTag(){
        
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }
}
