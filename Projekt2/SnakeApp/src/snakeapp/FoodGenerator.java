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
import javax.swing.JPanel;
import java.util.Random;

/**
 *
 * @author terg
 */
public class FoodGenerator extends JPanel{
    Random positionGenerator;
    Food food1;
    
    public FoodGenerator(){
        positionGenerator = new Random();  
        generateFood();
    }
    
    public void checkFoodExists(){
        
    }
    
    public Point getFoodPosition(){
        return food1.getPosition();
    }
    
    public void generateFood(){
        int tmp_x = 20+Math.abs(20*(positionGenerator.nextInt()%30));
        int tmp_y = 80+Math.abs(20*(positionGenerator.nextInt()%20));
        food1 = new Food(new Point(tmp_x, tmp_y));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.black);
        g2D.fill(food1.body);
        System.out.println("FG repaint");
    }
}
