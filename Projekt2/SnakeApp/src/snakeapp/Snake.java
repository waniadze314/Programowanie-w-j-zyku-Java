/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author terg
 */
public class Snake extends JPanel{
    ArrayList<Segment> body;
    Point initialPosition;
    char direction;
    int length;
    boolean grow;
    
    public Snake(){
        direction = 'R';
        body = new ArrayList<>();
            Segment tmp_seg1 = new Segment(new Point(360, 240));            
            Segment tmp_seg2 = new Segment(new Point(340, 240));                        
            Segment tmp_seg3 = new Segment(new Point(320, 240));                                   
            Segment tmp_seg4 = new Segment(new Point(300, 240));
            body.add(tmp_seg1);
            body.add(tmp_seg2);
            body.add(tmp_seg3);
            body.add(tmp_seg4);        
    }
    
    public void grow(){
        System.out.println("Grows");        
    }
    
    public void setDirection(char newDirection){
        direction = newDirection;
    }
    
    public char getDirection(){
        return direction;
    }
    
    public void move(){
        //issue
        for(int segment = body.size()-1;segment>=1;segment--){
            Point new_position = body.get(segment-1).getPosition();
            
            body.get(segment).moveToPosition(new_position);
        }
        body.get(0).move(direction);
        
        
    }
    
    public Point getHeadPosition(){
        return body.get(0).getPosition();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        for (int i=0;i<body.size();i++){
            g2D.setColor(Color.green);
            g2D.fill(body.get(i).body);
            g2D.setColor(Color.red);
            g2D.draw(body.get(i).body);      
        }            
    } 
    
    
       
}
