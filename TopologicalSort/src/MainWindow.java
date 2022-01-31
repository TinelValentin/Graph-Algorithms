
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow
{	       static boolean aux = false;
	private static void initUI() {
        JFrame f = new JFrame("Algoritmica Grafurilor");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        JPanel panouB =new JPanel(null);
        
        
        
        JButton Orientat = new JButton("Graf  Automat ");
        Orientat.setBackground(Color.white);   
        Orientat.setBounds(500,330,250,70); 
        Orientat.setFont(new Font("Arial", Font.BOLD, 15));
       
        
        JButton Neorientat = new JButton("Graf Manual");
        Neorientat.setBackground(Color.white);   
       Neorientat.setBounds(150,330,250,70); 
        Neorientat.setFont(new Font("Arial", Font.BOLD, 15));
        
        
        Orientat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             panouB.setVisible(false);
             f.remove(panouB);
             f.add(new WindowAuto(1200,800) );
             f.repaint();
            
      
            }
          });
 
        Neorientat.addActionListener(new ActionListener() {
        	WindowManual windowM = new WindowManual();
            public void actionPerformed(ActionEvent e) {
             panouB.setVisible(false);
             f.remove(panouB);
             f.add(windowM );
             f.repaint();
             
      
            }
          });
        panouB.add (Orientat);
        panouB.add (Neorientat); 
        
   
        
        f.add(panouB);
        f.setSize(1200, 800);
    }
	
	public static void main(String[] args)
	{
		
		SwingUtilities.invokeLater(new Runnable() //new Thread()
		{
            public void run() 
            {
            	initUI(); 
            }
        });
	}	
}