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
public class FoodGenerator{
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
        int tmp_x = 20+Math.abs(20*(positionGenerator.nextInt()%24));
        int tmp_y = 60+Math.abs(20*(positionGenerator.nextInt()%17));
        food1 = new Food(new Point(tmp_x, tmp_y));
    }
}
