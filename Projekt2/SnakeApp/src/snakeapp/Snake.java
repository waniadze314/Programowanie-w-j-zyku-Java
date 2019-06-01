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
public class Snake{
    ArrayList<Segment> body;
    Point initialPosition;
    char direction;
    boolean grow;
    
    public Snake(){
        System.out.println("Snake born");
        direction = 'R';
        body = new ArrayList<>();        
            Segment tmp_seg1 = new Segment(new Point(360, 240));            
            Segment tmp_seg2 = new Segment(new Point(340, 240));                        
            Segment tmp_seg3 = new Segment(new Point(320, 240));                                   
            Segment tmp_seg4 = new Segment(new Point(300, 240));                                   
            Segment tmp_seg5 = new Segment(new Point(280, 240));                                  
            Segment tmp_seg6 = new Segment(new Point(260, 240));
            body.add(tmp_seg1);
            body.add(tmp_seg2);
            body.add(tmp_seg3);
            body.add(tmp_seg4);            
            body.add(tmp_seg5);            
            body.add(tmp_seg6);
    }
    
    public void grow(){
        Point tmp_new_position = body.get(body.size()-1).getPosition();
        Segment newSegment = new Segment(tmp_new_position);
        body.add(newSegment);    
    }
    
    public void setDirection(char newDirection){
        direction = newDirection;
    }
    
    public char getDirection(){
        return direction;
    }
    
    public void move(){
        for(int segment = body.size()-1;segment>=1;segment--){
            Point new_position = body.get(segment-1).getPosition();            
            body.get(segment).moveToPosition(new_position);
        }
        body.get(0).move(direction);    
        
    }
    
    public Point getHeadPosition(){
        return body.get(0).getPosition();
    }

       
}
