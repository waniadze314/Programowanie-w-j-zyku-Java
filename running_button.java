import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;

class AdjFrame extends JFrame{
    AdjFrame(){
     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
     int width = (int)dim.getWidth();
     int height = (int) dim.getHeight();
     //System.out.println("Width="+(int)width+"\n"+"Height="+(int)height);
     setSize(width,height);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setVisible(true);
     setTitle("Running Button");
    }
}


class running_button{
    public static void main(String args[]){
        AdjFrame window = new AdjFrame();
    }
}