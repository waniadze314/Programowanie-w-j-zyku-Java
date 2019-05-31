/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author terg
 */
public class GameFrame extends JFrame implements KeyListener{
    
    Dimension size;
    GamePanel panel;
    JPanel menuPanel;
    JButton start, pause, exit;
    JLabel score;
    private final Timer tim1;    
    Rectangle2D scene;
    Border border = BorderFactory.createLineBorder(Color.black,2);
    int speed;
    
    Snake snake1;
    
    
    public GameFrame(){ 
        speed=250;
        addKeyListener(this);        
        tim1 = new  Timer();
        menuPanel = new JPanel(); 
        panel = new GamePanel();
        panel.setLocation(0, 10);
        panel.setSize(640,445);
        panel.setLayout(null);
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(640, 30));
        menuPanel.setBorder(border);
        
        size = new Dimension(640, 480);    
        start = new JButton("Start");
        start.setFocusable(false);
        pause = new JButton("Pause");
        pause.setFocusable(false);
        exit = new JButton("Exit");
        start.setBounds(50, 20, 80, 30);
        exit.setBounds(500, 20, 80, 30);
        pause.setBounds(130, 20, 90, 30);
        score = new JLabel("Score:");
        score.setBounds(320, 20, 100, 30);
        exit.setBackground(Color.red);
        menuPanel.add(start);
        menuPanel.add(pause);
        menuPanel.add(exit);
        menuPanel.add(score);
        
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(snake1==null){
                snake1 = new Snake();               
                getContentPane().add(snake1);
                System.out.println("Start");
            }
            }
        });
        
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pause");
            }
        });
        
        getContentPane().add(panel);
        getContentPane().add(menuPanel);
        setTitle("Snake");
        panel.setBackground(Color.gray);
        setSize(size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);         
        
        tim1.scheduleAtFixedRate(new TimerTask(){
        @Override
        public void run() {              
                try{  
                    repaint();
                }catch (NullPointerException e){
                    System.out.println("Null ptr exception");
                }                      
        }
    }, speed, speed);   
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {    
        int symbol = e.getKeyCode();
        switch(symbol){
            case KeyEvent.VK_LEFT:
                score.setText("Left");
                break;
            case KeyEvent.VK_RIGHT:
                score.setText("Right");
                break;
            case KeyEvent.VK_UP:
                score.setText("Up");
                break;
            case KeyEvent.VK_DOWN:
                score.setText("Down");
                break;
        }        
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    void setSnakeDirection(){
        
    }
    
    void setSpeed(int newSpeed){
        speed = newSpeed;
    }
    
}
