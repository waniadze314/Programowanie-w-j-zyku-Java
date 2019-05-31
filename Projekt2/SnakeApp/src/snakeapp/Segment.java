/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author terg
 */
public class Segment extends JComponent{
    Dimension size;
    Point position;
    Rectangle2D body;
    
    public Segment(){
        size = new Dimension(20, 20);
        position = new Point(320, 240); 
        setLocation(position.x, position.y);
        body = new Rectangle2D.Float(position.x,position.y,size.width,size.height); 
    }
    public Segment(Point newPosition){
        size = new Dimension(20, 20);
        position = new Point(newPosition.x, newPosition.y);  
        setLocation(position.x, position.y);
        setFocusable(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D segmentGraphics = (Graphics2D)g;
        segmentGraphics.setColor(new Color(255,0,0));
        segmentGraphics.fill(body);
        segmentGraphics.setColor(new Color(0,0,0));
        segmentGraphics.draw(body);
        System.out.println("...");  
    }  
}


