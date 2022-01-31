
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class WindowAuto extends JPanel {

	
	int diameter=10;
	int nrNode=1;
	int nodes;
	Graphics g;
	int error=0;
	double chance=0.05;
	ArrayList<ArrayList<Integer>> ListaAdiacenta = new ArrayList<ArrayList<Integer>>();
	Vector<OrientedArc> ArcVector = new Vector<OrientedArc>();
	Vector<Node> PointVector = new Vector<Node>();
	Set<Point> pointMap = new HashSet<Point>();
	
	private boolean inRange( Set<Point> pointMap,Point verify )
	{
		for(int i =verify.x-diameter;i<verify.x+diameter;i++)
			for(int j =verify.y-diameter;j<verify.y+diameter ;j++)
		{
				Point aux=new Point();
				aux.x=i;
				aux.y=j;
			 if(pointMap.contains(aux))
				 return true;
		}
		pointMap.add(verify);
		return false;
		
	}
	
	
	 
	 
	
	public  WindowAuto(int width,int height)
	{
		int nodes;
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduceti numarul de noduri");
		nodes=sc.nextInt();
		
		for(int i=0;i<nodes;i++)
			ListaAdiacenta.add(new ArrayList<Integer>());
		
		int widthaux=width;
		
		JButton Orientat = new JButton("Sortare ");
        Orientat.setBackground(Color.white);   
        Orientat.setBounds(500,70,150,40); 
        Orientat.setFont(new Font("Arial", Font.BOLD, 15));
        add(Orientat);
        
        Orientat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(PointVector.size()>0)
        			DF(ListaAdiacenta,PointVector);
            
      
            }
          });
		
		
		for(int i=0;i<nodes;i++)
		{
			Point verify=new Point();
			 verify.x = (int)(Math.random()*(((width-100)-1)));
			 verify.y= (int)(Math.random()*(((height-100)-1)));
			 
			 if(inRange(pointMap,verify))
				 {
				 i--;
				 error++;
				if(error== widthaux) {
					diameter-=2;
					widthaux*=2;
				}
				 }
			 else
			 {
				 
				 Node newNode = new Node(verify.x,verify.y,nrNode);
				 
				 PointVector.add(newNode);
				 for(int j=0;j<PointVector.size()-1;j++)
				 {
					 double random=(double)((Math.random()*(1-0)+0));
					 if(random<chance) 
						 {
						 double random2=(double)((Math.random()*(1-0)+0));
						 
						 Point aux=new Point(PointVector.get(j).getCoordX(),PointVector.get(j).getCoordY());
//						 if(random2<0.5)
//						 {
//							 OrientedArc newArc2 = new OrientedArc(aux,verify);
//							 ArcVector.add(newArc2);
//							 ListaAdiacenta.get(nrNode-1).add(j+1);
//						 }
						 OrientedArc newArc = new OrientedArc(verify,aux);
						 ArcVector.add(newArc);
						 ListaAdiacenta.get(nrNode-1).add(j+1);
						 }
				 }
				 nrNode++;
			 }
			 repaint();
		}


	repaint();
	}
	
	protected void paintComponent(Graphics g)
	{    
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		  for(Node i:PointVector) 
		  
		  { 
			  i.drawNodeManual(g, diameter);
			  
		  } 
		  g.setColor(Color.RED);
		  for(OrientedArc j:ArcVector) 
		  {
	 
			  j.drawArrowLine(g,5,5);
	  
		  } 
		

		
}
	
	private void DF(ArrayList<ArrayList<Integer>> List,Vector<Node> listaNoduri)
	{
		
		
		for(int i=0;i<listaNoduri.size();i++)
			listaNoduri.get(i).setVisited(false);
		
		Vector<Integer> vizit = new Vector<Integer>();
		
		for(int i=0;i<listaNoduri.size();i++)
			vizit.add(0);
		
		Vector<Integer> predecesor = new Vector<Integer>();
		
		for(int i=0;i<listaNoduri.size();i++)
			
			predecesor.add(0);
		Stack<Node> Nodes = new Stack<Node>();
		Stack<Node> Progress = new Stack<Node>();
	
		boolean found=false;
		
		for(int i=0;i<listaNoduri.size();i++)
			if(listaNoduri.get(i).getVisited()==false)
			{
				
				for(int j=0;j<listaNoduri.size();j++)
     				vizit.set(j,0);
				Node curent= listaNoduri.get(i);
				Progress.push(curent);
				while(Progress.size()>0)
				{
					Node copy =curent;
					vizit.set(copy.getNumber()-1, 1);
					found=false;
					for(int k=0;k<List.get(copy.getNumber()-1).size();k++)
					{
						
						if(vizit.get(List.get(copy.getNumber()-1).get(k)-1)==0)
						{
							
							predecesor.set(List.get(copy.getNumber()-1).get(k)-1,copy.getNumber()-1);
							listaNoduri.get(List.get(copy.getNumber()-1).get(k)-1).setVisited(true);
							vizit.set(List.get(copy.getNumber()-1).get(k)-1,1);                 //setez vizitat nr din lista diacenta
							curent= listaNoduri.get(List.get(copy.getNumber()-1).get(k)-1);   //iau elementul din lista cu nr din lista de adicenta
							found = true;
							Progress.push(curent);
							break;
							
						}
						else
						{
							if(Progress.search(listaNoduri.get(List.get(copy.getNumber()-1).get(k)-1))!=-1)
								{System.out.print("Exista ciclu!");
								return;
							
						}
					}
				}
					if(!found)
					{
						Progress.pop();
						if(Nodes.search(copy)==-1)
						Nodes.push(copy);
						listaNoduri.get(copy.getNumber()-1).setVisited(true);
						curent=listaNoduri.get(predecesor.get(curent.getNumber()-1));
					}
			}
		
		
		}
		Vector<Node> aux = new Vector<Node>();
		while(Nodes.size()>0)
			{
			aux.add(Nodes.peek());
			Nodes.pop();
			}
		
		
		for(Node a : aux)
			System.out.print(a.getNumber()+" ");
		
}
		
}
