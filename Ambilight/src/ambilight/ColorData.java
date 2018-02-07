/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambilight;

/**
 *
 * @author Frank
 */
public class ColorData implements java.io.Serializable{
    
    /**
     * public fields
     */
    public int id;
    public String color1;
    public String color2;
    public String color3;
    public String color4;
    public String color5;
    public String color6;
    
    /**
     * generates a ColorData object
     * @param id the id that matches the user id
     * @param color1 preset color 1
     * @param color2 preset color 2
     * @param color3 preset color 3
     * @param color4 preset color 4
     * @param color5 preset color 5
     * @param color6  preset color 6
     */
    public ColorData(int id, String color1, String color2, String color3, String color4, String color5, String color6){
        this.id = id;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        this.color5 = color5;
        this.color6 = color6;
    }
    
    /**
     * generates an empty ColorData object
     */
    public ColorData(){
        
    }    
}
