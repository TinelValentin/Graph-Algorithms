
import java.util.Scanner;


import javax.swing.JFrame;



public class Main {
	static int nodes;
	
	public static void read()
	{
		Scanner sc= new Scanner(System.in);
		System.out.print("Introduceti numarul de Noduri: ");
		nodes=sc.nextInt();
		sc.close();
	}
	
	public static void  initialise()
	{int width=1600;
	int height=850;
		JFrame window = new JFrame();
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 window.setSize(width,height);
		 window.setVisible(true);
		 
		 
		 window.add(new Window(nodes,width,height));
		window.validate();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		read();
		initialise();

	}

}
