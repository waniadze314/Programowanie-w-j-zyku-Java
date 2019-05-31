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
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(new Color(0,0,0));
        Rectangle2D scene = new Rectangle2D.Float(10, 60, 600, 350);
        g2D.draw(scene);
        g2D.setColor(new Color(0,255,0,50));
        g2D.fill(scene);
    }
    
    
}
