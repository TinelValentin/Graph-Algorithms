package def;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {	      
	static boolean aux = false;
private static void initUI() {
    JFrame f = new JFrame("Algoritmica Grafurilor");

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    
    JPanel panouB =new JPanel(null);
    
    
    
    JButton capacF = new JButton("capacitate si flux ");
    capacF.setBackground(Color.white);   
    capacF.setBounds(250,330,250,70); 
    capacF.setFont(new Font("Arial", Font.BOLD, 15));
    
   
    

    capacF.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
         panouB.setVisible(false);
         f.remove(panouB);
         f.add(new FordF(true) );
         f.repaint();
        
  
        }
      });
    
    
    JButton capac = new JButton("capacitate ");
    capac.setBackground(Color.white);   
    capac.setBounds(550,330,250,70);  
    capac.setFont(new Font("Arial", Font.BOLD, 15));
    
   
    

    capac.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
         panouB.setVisible(false);
         f.remove(panouB);
         f.add(new FordF(false) );
         f.repaint();
        
  
        }
      });
    
 
         
  
        
   
    panouB.add (capac);
    panouB.add (capacF);
  
    
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