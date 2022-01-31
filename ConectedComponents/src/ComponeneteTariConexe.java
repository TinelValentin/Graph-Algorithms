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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ComponeneteTariConexe extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;      
	private Vector<Arc> listaArceOrientate; 
	ArrayList<ArrayList<Integer>> listaAdiacenta= new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> listaAdiacentaRev= new ArrayList<ArrayList<Integer>>();
	Vector<Stack<Node>> componente = new Vector<Stack<Node>>();
	Point pointStart = null;
	Point pointEnd = null;
	Node Start1;
	boolean isDragging = false;
	boolean auxiliar =false,start=false,finish=false,orient2,paint=false,drag=false;           
	double distanta;                                           
	int x1, y1,pozStart,pozFinish;                          
	Point pointStartDrag;
	 boolean conex =false;
	 Stack<Node> Dfs = new Stack<Node>();   
	public ComponeneteTariConexe()
	{
		
		listaNoduri = new Vector<Node>();
		 listaArceOrientate=new Vector<Arc>();
		
		 JButton Orientat = new JButton("Compoenente Tari Conexe");
	        Orientat.setBackground(Color.white);   
	        Orientat.setBounds(500,70,150,40); 
	        Orientat.setFont(new Font("Arial", Font.BOLD, 15));
	        add(Orientat);
	        
	        Orientat.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	conex=true;
	            	DFS();
	        		Reverse();	
	        		reverseDfs();
	            
	      
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
					conex=false;
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
							
							Arc arcOrientat = new Arc(pointStart, pointEnd);
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
		
	if(conex==false)
		
	{
		for(Arc b : listaArceOrientate)
		{
			b.drawArrowLine(g,10,10);
		}
	if (pointStart != null)
	{ g.setColor(Color.RED);
		 g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
	}
	
	for(int i=0; i<listaNoduri.size(); i++)
	{
		g.setColor(Color.RED);
		listaNoduri.elementAt(i).drawNode(g, node_diam);
	}
	}
	else
	{
		for(int i=0;i<listaNoduri.size();i++)
			listaNoduri.get(i).setVisited(false);
		
		
		
		
		for(int i=0;i<componente.size();i++)
		{
			g.setColor(new Color((int)(Math.random() * 0x1000000)));
			Stack<Node> aux =(Stack<Node>) componente.get(i).clone();
			for(Node a: aux)
			{
				a.drawNodeManual(g, 30);
			}
			Node precedent = new Node(0,0);
			Node first=aux.peek();
			while(aux.size()>0)
			{
				Node curent=aux.peek();
				
				for(int k=0;k<listaAdiacentaRev.get(curent.getNumber()-1).size();k++)  // verific daca in lista lui de adicaenta exista un nod care nu face parte din compoennta
				{
					int auxiliar =listaAdiacentaRev.get(curent.getNumber()-1).get(k);
				if(componente.get(i).search(listaNoduri.get(auxiliar-1))==-1)	
				{
					Point a1 = new Point();
					Point b1=new Point() ;
					a1.x=curent.getCoordX();
					a1.y=curent.getCoordY()+15;
					b1.x=listaNoduri.get(auxiliar-1).getCoordX();
					b1.y=listaNoduri.get(auxiliar-1).getCoordY();
					Arc desen = new Arc(b1,a1);
					desen.drawArrowLineManual(g,15,15);
				}
					
				}
				if(curent!=first&&aux.size()-1>=0)
				{
					Point a1 = new Point();
					Point b1=new Point() ;
					a1.x=curent.getCoordX();
					a1.y=curent.getCoordY()+15;
					b1.x=precedent.getCoordX();
					b1.y=precedent.getCoordY()+15;
					Arc desen = new Arc(a1,b1);
					desen.drawArrowLineManual(g,15,15);
					
					
				}
				precedent=curent;
				aux.pop();
				if(aux.size()-1==-1)
				{
					Point a1 = new Point();
					Point b1=new Point() ;
					a1.x=curent.getCoordX();
					a1.y=curent.getCoordY()+15;
					b1.x=first.getCoordX();
					b1.y=first.getCoordY()+15;
					Arc desen = new Arc(a1,b1);
					desen.drawArrowLineManual(g,15,15);
				}
				
			}
			
			
			

		}
		
	}
			
		

	
		
	
	        
}
	public void DFS()
	{
		int time=1;
		Vector<Integer> timeStart =new Vector<Integer>();
		Vector<Integer> timeFinish =new Vector<Integer>();
		for(int i=0;i<listaNoduri.size();i++)
			{
			timeStart.add(0);
			timeFinish.add(0);
			
			}
		int j=0;
		
		         //stiva in care retin toat parcurgerea
		for(int i=0;i<listaNoduri.size();i++)
		{
			                                               //iau nodul i din lista noduri
			if(timeStart.get(i)==0)						// verific daca am trecut vreodata pe acolo
			{	
				timeStart.set(i, time++);
				Stack<Node> stiva = new Stack<Node>();  
				stiva.push(listaNoduri.get(i));
				while(stiva.size()>0)
				{	Node curent =stiva.peek();
					
				if(listaAdiacenta.get(curent.getNumber()-1).size()!=0)
						
					for( j=0;j<listaAdiacenta.get(curent.getNumber()-1).size();j++)
					{																
					   int number =listaAdiacenta.get(curent.getNumber()-1).get(j)-1;
					   if(timeStart.get(number)==0)
					   {
						   timeStart.set(number,time++);
						   curent= listaNoduri.get(number);
						   stiva.push(curent);
						   break;
						   
					   }
					   
					   if(j==listaAdiacenta.get(curent.getNumber()-1).size()-1)
						   {
						   Dfs.push(curent);
						   timeFinish.set(number,time++);
						   stiva.pop();
						   }
					   }
					else
					{
						Dfs.push(curent);
						   timeFinish.set(curent.getNumber()-1,time++);
						   stiva.pop();
						   }
					
				}
		
				repaint();
			}
		}
		
//		for(Node a: Dfs)
//			System.out.print(a.getNumber()+" ");
		
	}
	
	public void Reverse()
	{
		for(int i=0;i<listaAdiacenta.size();i++)
		{
			listaAdiacentaRev.add(new ArrayList <Integer>());
		}
		
		for(int i=0;i<listaAdiacenta.size();i++)
		{
			
			for(int j=0;j<listaAdiacenta.get(i).size();j++)
			{
				int number =listaAdiacenta.get(i).get(j)-1;
				listaAdiacentaRev.get(number).add(i+1);
			}
		}
	
	
//	for(int i=0;i<listaAdiacentaRev.size();i++)
//	{
//		
//		for(int j=0;j<listaAdiacentaRev.get(i).size();j++)
//		{
//			System.out.print(listaAdiacentaRev.get(i).get(j)+" ");
//		}
//		System.out.print("\n");
//	}
	}

public void reverseDfs()
{
	int time=1;
	Vector<Integer> timeStart =new Vector<Integer>();
	Vector<Integer> timeFinish =new Vector<Integer>();
	for(int i=0;i<listaNoduri.size();i++)
	{
	timeStart.add(0);
	timeFinish.add(0);
	
	}
	int j=0,k=-1;
	
	while(Dfs.size()>0)
	{
		
		Node curent=Dfs.peek();
		
		Dfs.pop();
		if(timeStart.get(curent.getNumber()-1)==0)
		{
			timeStart.set(curent.getNumber()-1,time++);
			k++;
			Stack<Node> stiva = new Stack<Node>();  
			stiva.push(curent);
			
			componente.add(new Stack<Node>());
			
			while(stiva.size()>0)
			{	 curent =stiva.peek();
			if(listaAdiacentaRev.get(curent.getNumber()-1).size()!=0)
					
				for( j=0;j<listaAdiacentaRev.get(curent.getNumber()-1).size();j++)
				{																
				   int number =listaAdiacentaRev.get(curent.getNumber()-1).get(j)-1;
				   if(timeStart.get(number)==0)
				   {
					   timeStart.set(number,time++);
					   curent= listaNoduri.get(number);
					   stiva.push(curent);
					   break;
					   
				   }
				   
				   if(j==listaAdiacentaRev.get(curent.getNumber()-1).size()-1)
					   {
					   componente.get(k).push(curent);
					   timeFinish.set(number,time++);
					   stiva.pop();
					   }
				   }
				else
				{
					   componente.get(k).push(curent);
					   timeFinish.set(curent.getNumber()-1,time++);
					   stiva.pop();
					   
				}
				
			} //oprit while 1
		}
		
	}
	
	for(int i=0;i<componente.size();i++)
		{
		for(Node a: componente.get(i))
			System.out.print(a.getNumber()+" ");
		System.out.print("\n");
		}
			
}

}
