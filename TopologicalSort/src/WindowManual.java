import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WindowManual extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;      
	private Vector<OrientedArc> listaArceOrientate; 
	ArrayList<ArrayList<Integer>> listaAdiacenta= new ArrayList<ArrayList<Integer>>();
	Point pointStart = null;
	Point pointEnd = null;
	Node Start1;
	boolean isDragging = false;
	boolean auxiliar =false,start=false,finish=false,orient2,paint=false,drag=false;           
	double distanta;                                           
	int x1, y1,pozStart,pozFinish;                          
	Point pointStartDrag;
	
	
	public WindowManual()
	{
		
		listaNoduri = new Vector<Node>();
		 listaArceOrientate=new Vector<OrientedArc>();
		
		 JButton Orientat = new JButton("Sortare ");
	        Orientat.setBackground(Color.white);   
	        Orientat.setBounds(500,70,150,40); 
	        Orientat.setFont(new Font("Arial", Font.BOLD, 15));
	        add(Orientat);
	        
	        Orientat.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if(listaNoduri.size()>0)
	        			DF(listaAdiacenta,listaNoduri);
	            
	      
	            }
	          });
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e))
				pointStart = e.getPoint();
				if (SwingUtilities.isRightMouseButton(e)) {
			         pointStartDrag=e.getPoint();
			}
			};	
			
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e))
				{
					if (!isDragging) {
					
					for(int i=0;i<listaNoduri.size();i++)
					{
						x1=listaNoduri.get(i).getCoordX()-15;
						y1=listaNoduri.get(i).getCoordY()-15;
						
						distanta =Math.sqrt((x1-(e.getX()-15))*(x1-(e.getX()-15))+(y1-(e.getY()-15))*(y1-(e.getY()-15)));
						if(distanta<=node_diam)auxiliar =true;
						
					}
					if(auxiliar==false)
					addNode(e.getX(), e.getY());
					else auxiliar=false;
				}
				else {
					start=false;
					finish=false;
					for(int i=0;i<listaNoduri.size();i++)
					{
						if(pointStart.x - listaNoduri.get(i).getCoordX()<30&&pointStart.y - listaNoduri.get(i).getCoordY()<30)
							if(pointStart.x - listaNoduri.get(i).getCoordX()>0&&pointStart.y - listaNoduri.get(i).getCoordY()>0)
							{
								start=true;
								pozStart=i;
							}
						if(pointEnd.x - listaNoduri.get(i).getCoordX()<30&&pointEnd.y - listaNoduri.get(i).getCoordY()<30)
							if(pointEnd.x - listaNoduri.get(i).getCoordX()>0&&pointEnd.y - listaNoduri.get(i).getCoordY()>0)
							{
								finish=true;
								pozFinish=i;
							}
					}
					
						if(start==true&&finish==true&&pozStart!=pozFinish)
						{
							listaAdiacenta.get(pozStart).add(pozFinish+1);
							
						OrientedArc arcOrientat = new OrientedArc(pointStart, pointEnd);
						listaArceOrientate.add(arcOrientat);	
						paint=true;
						
						}
						
				}
				
				pointStart = null;
				isDragging = false;
				repaint();
			}
		
			
			if (SwingUtilities.isRightMouseButton(e)) 
			{
				finish=false;
				if(isDragging)
					for(int i=0;i<listaNoduri.size();i++)
					{
						if(pointStartDrag.x - listaNoduri.get(i).getCoordX()<30&&pointStartDrag.y - listaNoduri.get(i).getCoordY()<30)
							if(pointStartDrag.x - listaNoduri.get(i).getCoordX()>0&&pointStartDrag.y - listaNoduri.get(i).getCoordY()>0)
							{
								start=true;
								pozStart=i;
							}
						
								x1=listaNoduri.get(i).getCoordX()-15;
								y1=listaNoduri.get(i).getCoordY()-15;
								
								distanta =Math.sqrt((x1-(e.getX()-15))*(x1-(e.getX()-15))+(y1-(e.getY()-15))*(y1-(e.getY()-15)));
								if(distanta<=node_diam)
								finish=true;
								
							
						
					}
				
				
				if(start==true&&finish==false)	
				{
					for(int i=0;i<listaArceOrientate.size();i++)
					{
					if(listaArceOrientate.get(i).getEnd().x - listaNoduri.get(pozStart).getCoordX()<30&&listaArceOrientate.get(i).getEnd().y-listaNoduri.get(pozStart).getCoordY()<30)
						if(listaArceOrientate.get(i).getEnd().x - listaNoduri.get(pozStart).getCoordX()>0&&listaArceOrientate.get(i).getEnd().y-listaNoduri.get(pozStart).getCoordY()>0)
							
							listaArceOrientate.get(i).setEnd(pointEnd);
					if(listaArceOrientate.get(i).getStart().x - listaNoduri.get(pozStart).getCoordX()<30&&listaArceOrientate.get(i).getStart().y-listaNoduri.get(pozStart).getCoordY()<30)
						if(listaArceOrientate.get(i).getStart().x - listaNoduri.get(pozStart).getCoordX()>0&&listaArceOrientate.get(i).getStart().y-listaNoduri.get(pozStart).getCoordY()>0)
							
							listaArceOrientate.get(i).setStart(pointEnd);
					}    
							listaNoduri.get(pozStart).setCoordX(pointEnd.x);
							listaNoduri.get(pozStart).setCoordY(pointEnd.y);
							repaint();
							}
					
			}
			
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			//evenimentul care se produce la drag&drop pe mousse
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) 
				{
					pointEnd = e.getPoint();
				isDragging = true;
				repaint();
				}
				if (SwingUtilities.isLeftMouseButton(e)) 
				{
					pointEnd = e.getPoint();
				isDragging = true;
				repaint();
				}
					
				
			}
		});
	}

	//metoda care se apeleaza la eliberarea mouse-ului
	private void addNode(int x, int y) {
		Node node = new Node(x, y,nodeNr);
		listaNoduri.add(node);
		listaAdiacenta.add(new ArrayList<Integer>());
		nodeNr++;
		repaint();
		
	}
	

	protected void paintComponent(Graphics g)
	{    
		super.paintComponent(g);
		
	
		
			for(OrientedArc b : listaArceOrientate)
			{
				b.drawArrowLine(g,20,20);
			}
		//deseneaza arcul curent; cel care e in curs de desenare
		
		if (pointStart != null)
		{ g.setColor(Color.RED);
			 g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
			 
			 int dx = pointEnd.x - pointStart.x, dy = pointEnd.y - pointStart.y;
			    double D = Math.sqrt(dx*dx + dy*dy);
			    double xm = D - 20, xn = xm, ym = 20, yn = -20, x;
			    double sin = dy / D, cos = dx / D;

			    x = xm*cos - ym*sin + pointStart.x;
			    ym = xm*sin + ym*cos + pointStart.y;
			    xm = x;

			    x = xn*cos - yn*sin + pointStart.x;
			    yn = xn*sin + yn*cos + pointStart.y;
			    xn = x;

			    int[] xpoints = {pointEnd.x-1, (int) xm, (int) xn};
			    int[] ypoints = {pointEnd.y-1, (int) ym, (int) yn};

			   
			   
			    g.fillPolygon(xpoints, ypoints, 3);
		}
		
		for(int i=0; i<listaNoduri.size(); i++)
		{
			listaNoduri.elementAt(i).drawNodeManual(g, node_diam);
		}
		
		try
		{
		FileWriter fisier = new FileWriter("Matrice");
		
		fisier.write(System.getProperty( "line.separator" ));
		for (int i = 0; i < listaAdiacenta.size(); i++) 
			{
			
			for(int j=0;j<listaAdiacenta.get(i).size();j++)
			
				 fisier.write(listaAdiacenta.get(i).get(j) + " "+ " ");
			fisier.write(System.getProperty( "line.separator" ));
			}
		fisier.close();
		}
		catch(IOException ie)  {
            ie.printStackTrace();
        } 
		
		
	
	        
}

	
	private boolean search(Vector<Integer> vec, int valoare)
	{
		for(int i =0;i<vec.size();i++)
			if(vec.get(i)==valoare)
				return true;
		return false;
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
