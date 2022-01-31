
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Graf
{	
	private static void initUI() {
        JFrame f = new JFrame("Algoritmica Grafurilor");
        //sa se inchida aplicatia atunci cand inchid fereastra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //imi creez ob MyPanel
      //  f.add(new MyPanel(false));
        //setez dimensiunea ferestrei
       
        //fac fereastra vizibila
        f.setVisible(true);
        
        JPanel panouB =new JPanel(null);
        
        
        
        JButton Orientat = new JButton("Graf Orientat");
        Orientat.setBackground(Color.white);   
        Orientat.setBounds(370,280,180,50); 
        Orientat.setFont(new Font("Arial", Font.BOLD, 15));
       
        
        JButton Neorientat = new JButton("Graf Neorientat");
        Neorientat.setBackground(Color.white);   
       Neorientat.setBounds(100,280,180,50); 
        Neorientat.setFont(new Font("Arial", Font.BOLD, 15));
        
        
        Orientat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             panouB.setVisible(false);
             f.remove(panouB);
             f.add(new MyPanel(true));
             f.repaint();
            
      
            }
          });
        
        Neorientat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             panouB.setVisible(false);
             f.remove(panouB);
             f.add(new MyPanel(false));
             f.repaint();
      
            }
          });
        panouB.add (Orientat);
        panouB.add (Neorientat); 
        
   
        
        f.add(panouB);
        f.setSize(700, 700);
    }
	
	public static void main(String[] args)
	{
		//pornesc firul de executie grafic
		//fie prin implementarea interfetei Runnable, fie printr-un ob al clasei Thread
		SwingUtilities.invokeLater(new Runnable() //new Thread()
		{
            public void run() 
            {
            	initUI(); 
            }
        });
	}	
}
