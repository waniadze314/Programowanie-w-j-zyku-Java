/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

public class SnakeApp implements Runnable{
    static GameFrame frame1;

    public static void main(String[] args) {
        // TODO code application logic here
        frame1 = new GameFrame();        
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
