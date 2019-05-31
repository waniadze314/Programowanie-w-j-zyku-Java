/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

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
        
        body = new ArrayList<>();
        for(int i=0;i<5;i++){
            Segment tmp_seg = new Segment();
            body.add(tmp_seg);
            add(tmp_seg);
            
        } 
        System.out.println("Born");
        System.out.println(body.size());
        
    }
    
    void grow(){
        
    }
    
    public void setDirection(){
        
    }
    
    void move(){
        
    }
    
    boolean checkCollision(){
        return true;
        
    }
    
}
