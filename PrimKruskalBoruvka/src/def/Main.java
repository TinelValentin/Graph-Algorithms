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
    
    
    
    JButton Prim = new JButton("Algoritmul lui Prim ");
    Prim.setBackground(Color.white);   
    Prim.setBounds(400,330,250,70); 
    Prim.setFont(new Font("Arial", Font.BOLD, 15));
    
    JButton Boruvka = new JButton("Algoritmul lui Boruvka ");
    Boruvka.setBackground(Color.white);   
    Boruvka.setBounds(700,330,250,70); 
    Boruvka.setFont(new Font("Arial", Font.BOLD, 15));
   
    
    JButton Kruskal = new JButton("Algoritmul lui Kruskal");
    Kruskal.setBackground(Color.white);   
    Kruskal.setBounds(100,330,250,70); 
    Kruskal.setFont(new Font("Arial", Font.BOLD, 15));
    
    
    Prim.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
         panouB.setVisible(false);
         f.remove(panouB);
         f.add(new Prim() );
         f.repaint();
        
  
        }
      });
    
    Boruvka.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
         panouB.setVisible(false);
         f.remove(panouB);
         f.add(new Boruvka() );
         f.repaint();
        
  
        }
      });

    Kruskal.addActionListener(new ActionListener() {
    	//WindowManual windowM = new WindowManual();
        public void actionPerformed(ActionEvent e) {
         panouB.setVisible(false);
         f.remove(panouB);
          f.add(new Kruskal() );
         f.repaint();
         
  
        }
      });
    panouB.add (Prim);
    panouB.add (Kruskal); 
    panouB.add (Boruvka); 

    
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