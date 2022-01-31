package def;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Boruvka extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;      
	private Vector<Arc> listaArceOrientate; 
	private Vector<Point> listaArceBorukav=new Vector<Point>(); 
	ArrayList<ArrayList<Integer>> listaAdiacentaBoruvka= new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Point>> listaAdiacenta= new ArrayList<ArrayList<Point>>();
	Vector<Point> Vizitat = new Vector<Point>(); 
	Point pointStart = null;
	Point pointEnd = null;
	Node Start1;
	boolean isDragging = false;
	boolean auxiliar =false,start=false,finish=false,orient2,paint=false,drag=false;           
	double distanta;                                           
	int x1, y1,pozStart,pozFinish;                          
	Point pointStartDrag;
	 boolean algoritm =false;
	 
	public Boruvka()
	{
		JTextField costInput = new JTextField(16);
		add(costInput);
		repaint();
		listaNoduri = new Vector<Node>();
		 listaArceOrientate=new Vector<Arc>();
		
		 JButton Orientat = new JButton("Aplica algoritmul!");
	        Orientat.setBackground(Color.white);   
	        Orientat.setBounds(500,70,150,40); 
	        Orientat.setFont(new Font("Arial", Font.BOLD, 15));
	        add(Orientat);
	        
	        Orientat.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	algoritm=true;
	            	BoruvkaF();
	            	repaint();
	        			
	            
	      
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
					algoritm=false;
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
							String aux = costInput.getText();
							int number = Integer.parseInt(aux);
							Point pointRead = new Point(pozFinish+1,number);
							listaAdiacenta.get(pozStart).add(pointRead);
							Point pointReadSecond = new Point(pozStart+1,number);
							listaAdiacenta.get(pozFinish).add(pointReadSecond);
							Point noduriLegate = new Point(pozStart+1,pozFinish+1);
							
							
							Arc arcOrientat = new Arc(pointStart, pointEnd,number,noduriLegate);
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
		listaAdiacenta.add(new ArrayList<Point>());
		nodeNr++;
		repaint();
		
	}
	
	private boolean isNear(Point b, Point a)
	{
		if(b.x-a.x<40&&b.x-a.x>-40)
			if(b.y-a.y<40&&b.y-a.y>-40)
		return true;
		return false;
	}

	protected void paintComponent(Graphics g)
	{    
		super.paintComponent(g);
		
	if(algoritm==false)
		
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
		
		for(int i=0; i<listaNoduri.size(); i++)
		{
			g.setColor(Color.RED);
			listaNoduri.elementAt(i).drawNode(g, node_diam);
		}
		
		
		
		
		for(int i=0;i<listaArceBorukav.size();i++)
		{
			for(Arc b : listaArceOrientate)
			{
				Point nrNod = b.getNrNoduri();
				if(nrNod.x==listaArceBorukav.get(i).x&&nrNod.y==listaArceBorukav.get(i).y)
					b.drawArc(g);
				if(nrNod.x==listaArceBorukav.get(i).y&&nrNod.y==listaArceBorukav.get(i).x)
					b.drawArc(g);
				
			}
		}
		
		
	
		
	}
			       
}
	
	public Point findNodes(Integer index)
	{
		Point nrNodes=new Point();          //x are valoare min y are nr nod
		int min = Integer.MAX_VALUE;
		int componentNumber=-1;
		for(int k=0;k<listaAdiacentaBoruvka.size();k++)
			if(listaAdiacentaBoruvka.get(k).contains(index))
				componentNumber=k;
		
		for(int i=0;i<listaAdiacenta.get(index-1).size();i++)
		{
		if (min>listaAdiacenta.get(index-1).get(i).y)	
			if(!listaAdiacentaBoruvka.get(componentNumber).contains(listaAdiacenta.get(index-1).get(i).x))
		{
			min=listaAdiacenta.get(index-1).get(i).y;
			nrNodes.y=listaAdiacenta.get(index-1).get(i).x;
		}
		}
		nrNodes.x=min;
		return nrNodes;
	}
	
	public void cheapest()
	{
		Integer pozitie=-1;
		for(int i=0;i<listaAdiacentaBoruvka.size();i++)
		{int min = Integer.MAX_VALUE;
		Integer nrNodes=-1;
		
		
		for(int j=0;j<listaAdiacentaBoruvka.get(i).size();j++)
		{
			if(findNodes(listaAdiacentaBoruvka.get(i).get(j)).x<min&&findNodes(listaAdiacentaBoruvka.get(i).get(j)).x!=-1)
			{
				min = findNodes(listaAdiacentaBoruvka.get(i).get(j)).x;
				nrNodes =findNodes(listaAdiacentaBoruvka.get(i).get(j)).y;
				pozitie = j;
			}
				
		}
		int componentNumber=-1;
		for(int k=0;k<listaAdiacentaBoruvka.size();k++)
			if(listaAdiacentaBoruvka.get(k).contains(nrNodes))
				componentNumber=k;
		if(componentNumber==-1) return;
		if(i<=componentNumber)
			{ Point createArc = new Point(listaAdiacentaBoruvka.get(i).get(pozitie),nrNodes);          //arc de la pozitie la nrNodes
			listaArceBorukav.add(createArc);
			listaAdiacentaBoruvka.get(i).addAll(listaAdiacentaBoruvka.get(componentNumber));
			for(int j=componentNumber;j<listaAdiacentaBoruvka.size()-1;j++)
			{
			listaAdiacentaBoruvka.set(j, listaAdiacentaBoruvka.get(j+1));	
			}
			
			listaAdiacentaBoruvka.remove(listaAdiacentaBoruvka.size()-1);
			}
		else
		{
			Point createArc = new Point(listaAdiacentaBoruvka.get(i).get(pozitie),nrNodes);
			listaArceBorukav.add(createArc);
			listaAdiacentaBoruvka.get(componentNumber).addAll(listaAdiacentaBoruvka.get(i));
			for(int j=i;j<listaAdiacentaBoruvka.size()-1;j++)
			{
			listaAdiacentaBoruvka.set(j, listaAdiacentaBoruvka.get(j+1));	
			}
			
			listaAdiacentaBoruvka.remove(listaAdiacentaBoruvka.size()-1);
		}
		
			
		}
	}
	
	
	public void BoruvkaF()
	{
		for(int i=0;i<listaNoduri.size();i++)
			{
				listaAdiacentaBoruvka.add(new ArrayList<Integer>());
				listaAdiacentaBoruvka.get(i).add(i+1);
			}
		
		while(listaAdiacentaBoruvka.size()>1)
		{
			cheapest();
		}
	for(int i=0;i<listaArceBorukav.size();i++)
		System.out.print(listaArceBorukav.get(i).x+ " "+listaArceBorukav.get(i).y+"\n");
		
		
	}
}