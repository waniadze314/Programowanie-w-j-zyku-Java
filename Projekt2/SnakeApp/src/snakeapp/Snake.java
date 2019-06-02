/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;


import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author terg
 */
public class Snake{
    final Point defaultSegments[]={new Point(260, 240), new Point(240, 240), new Point(220, 240),
    new Point(200, 240), new Point(180, 240), new Point(160, 240)};
    ArrayList<Segment> body;
    char direction;
    
    public Snake(){
        body = new ArrayList<>();
        initSnake();
    }
    
    void initSnake(){        
        direction = 'R';
        if(body!=null){
            for (int i=0;i<=2;i++){
                body.add(new Segment(defaultSegments[i]));
            }
        }
    }
    
    public void destroySnake(){
        body.clear();
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
