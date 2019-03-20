import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.lang.Math;
import java.util.TimerTask;
import java.util.Timer;

class RunningButton extends JButton {
    private final int default_step=120;
    private Random pos_generator;
    private Point next_location, current_location;
    private JLabel text;
    private int x_limit=640, y_limit=480;
    private final int x_dim=100, y_dim=100;
    private int step;


    RunningButton(int frame_x, int frame_y){ 
        x_limit = frame_x;
        y_limit = frame_y;
        pos_generator = new Random();   
        setPreferredSize(new Dimension(x_dim, y_dim));   
        current_location = next_location = new Point(x_limit/2, y_limit/2);        
        setLocation(current_location);
        setBackground(new Color(255,0,0));
        text = new JLabel("Exit");
        add(text);
        step=default_step;   
        System.out.format("x: %f y: %f\n", current_location.getX(), current_location.getY());
        }
    
    public Point getNextLocation(){
        return next_location;
    }

    public Point getCurrentLocation(){
        return current_location;
    }
    
    public Point getNewLocation(){
        int x_temp = pos_generator.nextInt();
        int y_temp = pos_generator.nextInt();
        next_location = new Point( Math.abs(x_temp%(x_limit-x_dim)), Math.abs(y_temp%(y_limit-y_dim)) );
        System.out.format("x: %f y: %f\n", next_location.getX(), next_location.getY());
        return next_location;
    }

    public void move(){ 
        double temp_x_distance = next_location.getX()-current_location.getX();
        double temp_y_distance = next_location.getY()-current_location.getY();
        Point temp_location = new Point((int)temp_x_distance/step, (int)temp_y_distance/step);
        current_location.x += temp_location.x;
        current_location.y += temp_location.y;
        setLocation(current_location);
        step--;
        if(step==0){
              current_location = next_location;
              step=120;
        }
    }


}

class AdjFrame extends JFrame {
    private int width, height;
    private RunningButton button;
    private JPanel panel;
    private Timer tim1;

    AdjFrame(){
        width = 640;
        height = 480;
        makeContent();
    }

    AdjFrame(Dimension dim){
        width = (int)dim.getWidth();
        height = (int)dim.getHeight();
        makeContent();
    }

     private void makeContent(){
     panel = new JPanel();
     tim1 = new Timer();
     button = new RunningButton(width, height);
     button.addActionListener(new ActionListener(){
     
         @Override
         public void actionPerformed(ActionEvent e) {
            button.getNewLocation();
         }
     } );
     panel.add(button);
     panel.setBackground(new Color(0,0,0,0));
     setSize(width,height);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setTitle("Running Button");  
     getContentPane().add(panel);
     setUndecorated(true);
     setBackground(new Color(0,0,0,0));
     setVisible(true);
     tim1.scheduleAtFixedRate(new TimerTask(){
        
        @Override
        public void run() {   
            if(button.getCurrentLocation() != button.getNextLocation()){
                try{  
                repaint();
                button.move();
                }catch (NullPointerException e){
                    System.out.println("Null ptr exception");
                }
            }                       
        }
    }, 15, 15);   
    }
}
class running_button{
    public static void main(String args[]){        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        AdjFrame window = new AdjFrame(dim);
    }
}