/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author terg
 */


public class GamePanel extends JPanel{
    Snake snake1;
    FoodGenerator f1;
    
    public GamePanel(){
        snake1 = new Snake();
        f1 = new FoodGenerator();
    }
    
    public void setSnakeDirection(char dir){        
        snake1.setDirection(dir);
    }
        
    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        Rectangle2D scene = new Rectangle2D.Float(20, 60, 640, 360);
        g2D.setColor(new Color(0,255,0,100));
        g2D.fill(scene);
        
        for (int i=0;i<snake1.body.size();i++){
            g2D.setColor(Color.green);
            g2D.fill(snake1.body.get(i).body);
            g2D.setColor(Color.red);
            g2D.draw(snake1.body.get(i).body);  
        }            
        g2D.setColor(Color.black);
        g2D.fill(f1.food1.body);
    }   
}
