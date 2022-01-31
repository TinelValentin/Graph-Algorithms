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

public class ComponenteConexe extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;      
	private Vector<Arc> listaArceOrientate; 
	ArrayList<ArrayList<Integer>> listaAdiacenta= new ArrayList<ArrayList<Integer>>();
	Point pointStart = null;
	Point pointEnd = null;
	Node Start1;
	boolean isDragging = false;
	boolean auxiliar =false,start=false,finish=false,orient2,paint=false,drag=false;           
	double distanta;                                           
	int x1, y1,pozStart,pozFinish;                          
	Point pointStartDrag;
	 boolean conex =false;
	 Vector<Stack<Node>> predecesor;
	public ComponenteConexe()
	{
		
		listaNoduri = new Vector<Node>();
		 listaArceOrientate=new Vector<Arc>();
		
		 JButton Orientat = new JButton("Compoenente Conexe");
	        Orientat.setBackground(Color.white);   
	        Orientat.setBounds(500,70,150,40); 
	        Orientat.setFont(new Font("Arial", Font.BOLD, 15));
	        add(Orientat);
	        
	        Orientat.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	conex=true;
	            	DFS();
	        			
	            
	      
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
							listaAdiacenta.get(pozFinish).add(pozStart+1);
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
			b.drawArc(g);
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
		
		
		
		for(int i=0;i<predecesor.size();i++)
			{Node aux=predecesor.get(i).peek();
			g.setColor(new Color((int)(Math.random() * 0x1000000)));
			int stackSize =predecesor.get(i).size();
			for(int j=0;j<stackSize;j++)
				{
				Node a=predecesor.get(i).peek();
				
				if(j>0)
					g.drawLine(a.getCoordX(),a.getCoordY()+15,aux.getCoordX(),aux.getCoordY()+15);
				a.drawNodeManual(g, 30);
				aux=a;
				predecesor.get(i).pop();
				}
			}
		
		
	
		
	}
			
		

		
		
	
	        
}
	public void DFS()
	{predecesor = new Vector<Stack<Node>>();
		for(int i=0;i<listaNoduri.size();i++)
			listaNoduri.get(i).setVisited(false);
		int j;
		int k=-1;
		
		for(int i=0;i<listaNoduri.size();i++)
		{
			                                               //iau nodul i din lista noduri
			if(listaNoduri.get(i).getVisited()==false)
			{	
				listaNoduri.get(i).setVisited(true);
				
				Stack<Node> stiva = new Stack<Node>();
				stiva.push(listaNoduri.get(i));
				k++;
				predecesor.add(new Stack<Node>());
				while(stiva.size()>0)
				{	
					Node curent =stiva.peek();
					if(listaAdiacenta.get(curent.getNumber()-1).size()!=0)
					for( j=0;j<listaAdiacenta.get(curent.getNumber()-1).size();j++)
					{																
					   int number =listaAdiacenta.get(curent.getNumber()-1).get(j)-1;
					   if(listaNoduri.get(number).getVisited()==false)
					   {
						   listaNoduri.get(number).setVisited(true);
						   curent= listaNoduri.get(number);
						   stiva.push(curent);
						   
						
						   break;
						   
					   }
					   
					   if(j==listaAdiacenta.get(curent.getNumber()-1).size()-1)
						   {
						   predecesor.get(k).push(stiva.peek());
						   stiva.pop();
						   }
					   }
					else
					{
						   predecesor.get(k).push(stiva.peek());
						   stiva.pop();
						   }
					
				}
		
				repaint();
			}
		}
		
	}
}
