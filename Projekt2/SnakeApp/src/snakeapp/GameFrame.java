/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author terg
 */
public class GameFrame extends JFrame implements KeyListener{
    
    final Point frameLimit[]={new Point(20, 60), new Point(500, 380)};
    final Dimension size = new Dimension(700, 520);  
    private final Timer tim1; 
    final int initialSpeed = 100;
    int speed, actualScore;    
    JPanel panel;
    GamePanel gamePanel;
    boolean gameOn;
    JButton start, pause, exit;
    JLabel score;

    
    public GameFrame(){         
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);    
        speed=initialSpeed;
        tim1 = new  Timer();      
        panel = new JPanel();
        panel.setOpaque(true);
            
            panel.setPreferredSize(new Dimension(640,40));
            panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
            panel.setLayout(null);
            start = new JButton("Start");
            start.setBounds(50, 5, 80, 30);
            pause = new JButton("Pause");
            pause.setBounds(130, 5, 90, 30);
            exit = new JButton("Exit");
            exit.setBounds(500, 5, 80, 30);
            exit.setBackground(Color.red);
            score = new JLabel("Press Start");
            score.setBounds(320, 5, 100, 30);
            
            gameOn=false;
            panel.add(start);
            panel.add(pause);
            panel.add(exit);
            panel.add(score);      
            panel.setVisible(true);     
                        
            start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                start();
                getContentPane().add(gamePanel);
                start.setVisible(false);
           
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
        
       
        getContentPane().add(panel);
        
        setLayout(new FlowLayout());
        
        setSize(size);
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        tim1.scheduleAtFixedRate(new TimerTask(){
        @Override
        public void run() {   
            if(gameOn){
                gamePanel.snake1.move();
                checkBorderCollision(gamePanel);
                checkSnakeCollision(gamePanel);
                checkFoodCollision(gamePanel);
                score.setText("Score: " + String.valueOf(actualScore));
                if(actualScore%3==0){
                    increaseSpeed();
                }
                repaint();                
            }
                
            }
        }, 0, speed);        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {    
        int symbol = e.getKeyCode();
            if(gamePanel!=null){
                switch(symbol){
                    case KeyEvent.VK_LEFT:
                        if(gamePanel.snake1.getDirection()!='R'){
                            gamePanel.setSnakeDirection('L');
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(gamePanel.snake1.getDirection()!='L'){
                            gamePanel.setSnakeDirection('R');
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(gamePanel.snake1.getDirection()!='D'){
                            gamePanel.setSnakeDirection('U');
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(gamePanel.snake1.getDirection()!='U'){
                            gamePanel.setSnakeDirection('D');
                        } 
                        break;                
                }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
   
    
    void setSpeed(int newSpeed){
        speed = newSpeed;
    }
    
    void increaseSpeed(){
        speed-=50;
    }
    
    void checkFoodCollision(GamePanel gp){
        Point snakeHead = gp.snake1.getHeadPosition();
        if((snakeHead.x==gp.f1.getFoodPosition().x) && (snakeHead.y==gp.f1.getFoodPosition().y)){
            gp.snake1.grow();
            gp.f1.generateFood();            
            actualScore++;
            Point tmp_f = gp.f1.food1.getPosition();
            for(int point = 0; point < gp.snake1.body.size()-1 ; point++){                
                Point tmp_h = gp.snake1.body.get(point).getPosition();
                if((tmp_f.x == tmp_h.x)&&(tmp_f.y == tmp_h.y)){
                    gp.f1.generateFood();
                    break;
                }
            }
            
            
        }       
    }
    
    void checkBorderCollision(GamePanel gp){
        Point snakeHead = gp.snake1.getHeadPosition();
        if(snakeHead.x<frameLimit[0].x || snakeHead.x>frameLimit[1].x 
        || snakeHead.y<frameLimit[0].y || snakeHead.y>frameLimit[1].y){
            gameOn = false;
            stop();
        }     
    }
    
    void checkSnakeCollision(GamePanel gp){        
        Point snakeHead = gp.snake1.getHeadPosition();
        for(int i=1;i<gp.snake1.body.size();i++){
            Point segmentPosition = gp.snake1.body.get(i).getPosition();
            if((snakeHead.x == segmentPosition.x) && (snakeHead.y == segmentPosition.y)){
                gameOn = false;
                stop();
            }
        }       
    }   
    
    void start(){
        actualScore = 0;
        gameOn = true;
        gamePanel = new GamePanel();  
        gamePanel.setPreferredSize(new Dimension(520, 400));
        gamePanel.setLocation(0, 30);        
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        gamePanel.setLayout(null);        
        super.add(gamePanel);
    }
    
    void restart(){
        
    }
    
    void stop(){
        int decision = JOptionPane.showConfirmDialog(null, "GAME OVER", "Do You want to play again?", JOptionPane.YES_NO_OPTION);
        if(decision == 0){
//            start();       
        }
        else if(decision == 1){
            System.exit(0);
        }
    }
    
}
