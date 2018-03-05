/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
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
    @NamedQuery(name = "role.findByRole", query = "SELECT s FROM Role s WHERE s.Role = :Role")})

@XmlRootElement
public class Role implements Serializable {
    @Id
    private String Role;
    
    public Role(){
        
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }
    
}
