import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Event;
import java.awt.Color;
import java.util.Random;
import java.lang.Math;


class AdjFrame extends JFrame implements ActionListener{

    private int width, height, btn_x=80, btn_y=50;
    private JButton btn;
    private JPanel panel;
    private Random pos_generator;



    @Override 
    public void actionPerformed(ActionEvent e) {
        int x_temp = pos_generator.nextInt();
        int y_temp = pos_generator.nextInt();
        int x = Math.abs(x_temp%(width-btn_x));
        int y = Math.abs(y_temp%(height-btn_y));
        System.out.println(x+" "+y);
        btn.setLocation(x,y);  
        repaint();     
    }

    AdjFrame(){
     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
     width = (int)dim.getWidth();
     height = (int) dim.getHeight();
     pos_generator = new Random();
     panel = new JPanel();
     btn = new JButton("Exit");
     btn.addActionListener(this);
     panel.add(btn);
     btn.setPreferredSize(new Dimension(btn_x, btn_y));
     setSize(width,height);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setTitle("Running Button");   
     panel.setBackground(new Color(0,0,0,0));
     btn.setBackground(new Color(255,0,0));   
     getContentPane().add(panel);
     setUndecorated(true);
     setBackground(new Color(0,0,0,0));
     setVisible(true);
        
     
    }
}


class running_button{
    public static void main(String args[]){
        AdjFrame window = new AdjFrame();
    }
}