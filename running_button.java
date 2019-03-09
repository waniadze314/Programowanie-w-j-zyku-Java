import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Event;
import java.util.Random;



class AdjFrame extends JFrame implements ActionListener{

    private int width, height;
    private JButton btn;
    private JPanel panel;
    private Random pos_generator;



    @Override 
    public void actionPerformed(ActionEvent e) {
        int x,y;
        x = pos_generator.nextInt(width);
        y = pos_generator.nextInt(height);
        btn.setLocation(x,y);
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
     btn.setPreferredSize(new Dimension(80, 50));
     setSize(width,height);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setTitle("Running Button"); 
     getContentPane().add(panel);
     setVisible(true);
    }
}


class running_button{
    public static void main(String args[]){
        AdjFrame window = new AdjFrame();
    }
}