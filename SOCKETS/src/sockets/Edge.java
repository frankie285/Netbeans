/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Frank
 */
public class Edge implements Serializable{
    public int x1;
    public int y1;
    public String kleur;
    public Color color;
    
    public Edge(int x1, int y1, String kleur, Color color){
        this.x1 = x1;
        this.y1 = y1;
        this.kleur = kleur;
        this.color = color;
    }
}
