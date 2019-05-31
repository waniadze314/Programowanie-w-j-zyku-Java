/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    
    final Point frameLimit[]={new Point(20, 80), new Point(620, 420)};
    final Dimension size = new Dimension(680, 520);
    GamePanel panel;
    JPanel menuPanel;
    JButton start, pause, exit;
    JLabel score;
    private final Timer tim1; 
    int speed;
    boolean gameOn;
    
    Snake snake1;
    FoodGenerator f1;
    
    
    public GameFrame(){ 
        speed=100;
        addKeyListener(this);        
        tim1 = new  Timer();
        menuPanel = new JPanel(); 
        panel = new GamePanel();
        panel.setLocation(0, 20);
        panel.setSize(640,445);
        panel.setLayout(null);
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(640, 30)); 
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
        menuPanel.setOpaque(false);       
        setTitle("Snake");
        snake1 = new Snake(); 
        f1 = new FoodGenerator();    
        getContentPane().add(panel);
        getContentPane().add(menuPanel);
        setSize(size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);         
        
        tim1.scheduleAtFixedRate(new TimerTask(){
        @Override
        public void run() {   
            if(gameOn){
                snake1.move();
                checkBorderCollision(snake1);
//                checkFoodCollision(snake1, f1);
                repaint();                
            }
                
        }
    }, 0, speed);   
        
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
            getContentPane().add(f1);
            getContentPane().add(snake1);
            
                score.setText("Score: "+ String.valueOf(snake1.length));
                gameOn=true;
                start.setVisible(false);                
                gameOn = true;
           
            }
        });
        
         exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameOn){
                    gameOn = false;
                    pause.setText("Resume");
                }else{
                    gameOn = true;
                    pause.setText("Pause");
                }
            }
        });
        
}
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {    
        int symbol = e.getKeyCode();
            if(snake1!=null){
                switch(symbol){
                    case KeyEvent.VK_LEFT:
                        if(snake1.getDirection()!='R'){
                            setSnakeDirection('L');
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(snake1.getDirection()!='L'){
                            setSnakeDirection('R');
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(snake1.getDirection()!='D'){
                            setSnakeDirection('U');
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(snake1.getDirection()!='U'){
                            setSnakeDirection('D');
                        } 
                        break;                
                }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    void setSnakeDirection(char dir){        
        snake1.setDirection(dir);
    }
    
    void setSpeed(int newSpeed){
        speed = newSpeed;
    }
    
    void checkFoodCollision(Snake s, FoodGenerator f ){
        Point snakeHead = s.getHeadPosition();
        if(snakeHead==f.getFoodPosition()){
            s.grow();
            f1.generateFood();
        }       
    }
    
    void checkBorderCollision(Snake s){
        Point snakeHead = s.getHeadPosition();
        if(snakeHead.x<frameLimit[0].x || snakeHead.x>frameLimit[1].x 
        || snakeHead.y<frameLimit[0].y || snakeHead.y>frameLimit[1].y){
            gameOn=false;
        }


        
    }
}
