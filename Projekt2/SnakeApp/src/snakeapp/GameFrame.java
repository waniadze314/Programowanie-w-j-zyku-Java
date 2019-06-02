/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
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
    
    final Point frameLimit[]={new Point(20, 60), new Point(480, 360)};
    final Dimension size = new Dimension(700, 520);  
    private final Timer tim1; 
    final int initialSpeed = 100;
    int speed, actualScore;    
    JPanel panel;
    GamePanel gamePanel;
    boolean gameOn;
    JButton start, exit;
    JLabel score, pause;

    
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
        exit = new JButton("Exit");
        exit.setBounds(500, 5, 80, 30);
        exit.setBackground(Color.red);
        score = new JLabel("Press Start");
        score.setBounds(320, 5, 100, 30);
        pause = new JLabel("Press 'P' to pause");
        pause.setBounds(150,5,150,30);
            
        gameOn=false;
        panel.add(start);
        panel.add(exit);
        panel.add(score);      
        panel.add(pause);
        panel.setVisible(true);     
                     
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                start();
                start.setVisible(false);
           
            }
        });
        
         exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
       
        
       
        getContentPane().add(panel);       
        setLayout(new FlowLayout());        
        setUndecorated(true);
        setSize(size);
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        
        tim1.scheduleAtFixedRate(new TimerTask(){
        @Override
        public void run() {   
            if(gameOn && gamePanel!=null){
                gamePanel.snake1.move();
                checkBorderCollision(gamePanel);
                checkSnakeCollision(gamePanel);
                checkFoodCollision(gamePanel);
                score.setText("Score: " + String.valueOf(actualScore));                
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
                    case KeyEvent.VK_P:
                        pause();
                        break;
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
                }try{
                TimeUnit.MILLISECONDS.sleep(initialSpeed);
                }catch(Exception ex){
                    System.err.println("Cannot wait");
                }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
   
    
    void setSpeed(int newSpeed){
        speed = newSpeed;
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
            stop();
        }     
    }
    
    void checkSnakeCollision(GamePanel gp){        
        Point snakeHead = gp.snake1.getHeadPosition();
        for(int i=1;i<gp.snake1.body.size();i++){
            Point segmentPosition = gp.snake1.body.get(i).getPosition();
            if((snakeHead.x == segmentPosition.x) && (snakeHead.y == segmentPosition.y)){
                stop();
            }
        }       
    }   
    
    void start(){
        gameOn = true;
        actualScore = 0;
        gamePanel = new GamePanel();  
        gamePanel.setPreferredSize(new Dimension(520, 400));       
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); 
        super.getContentPane().add(gamePanel);
    }
    void pause(){
        if(gameOn){
            gameOn = false;
            pause.setText("Press 'P' to resume");
        }else{
               gameOn = true;
               pause.setText("Press 'P' to pause");
        }
    }
    
    void restart(){     
        gamePanel.snake1.destroySnake();
        gamePanel.snake1 = new Snake();
        gamePanel.f1.generateFood();
        actualScore = 0;      
        gameOn = true;        
    }
    
    void stop(){
        gameOn = false;
        int decision = JOptionPane.showConfirmDialog(null, "Do You want to play again?", "GAME OVER", JOptionPane.YES_NO_OPTION);
        if(decision == 0){
            restart();       
        }
        else if(decision == 1){
            System.exit(0);
        }
    }
    
}
