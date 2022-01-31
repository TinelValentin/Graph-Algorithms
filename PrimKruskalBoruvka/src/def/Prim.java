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
import java.util.HashSet;
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

public class Prim extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;      
	private Vector<Arc> listaArceOrientate; 
	private  Set<Point> visitedArcs=new HashSet<Point>();
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
	 
	public Prim()
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
	            	Prim();
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
							
							
							
							Arc arcOrientat = new Arc(pointStart, pointEnd,number);
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
		
		for(int i=1;i<Vizitat.size();i++)
		{
		Node start = listaNoduri.get(i);
		Node finish = listaNoduri.get(Vizitat.get(i).y-1);
		Point coordStart = new Point(start.getCoordX(),start.getCoordY());
		Point coordFinish = new Point(finish.getCoordX(),finish.getCoordY());
		for(Arc b : listaArceOrientate)
		{
			if(isNear(b.getEnd(),coordStart)||isNear(b.getStart(),coordStart))
				if(isNear(b.getEnd(),coordFinish)||isNear(b.getStart(),coordFinish))
				
			b.drawArc(g);
		}
		}
		
		
	
		
	}
			

		
	
	        
}
	
	public boolean allVisited(Vector<Point> visited)
	{
		for(int i=0;i<listaNoduri.size();i++)
			if(visited.get(i).x==Integer.MAX_VALUE) return false;
		return true;
	}
	
	
	public void Prim()
	{
		
		   // in puncte retin : x-valoare y-de unde vin
		for(int i=0;i<listaNoduri.size();i++)			// in lista de adiacenta am : x-nrNod y:valoare arc
			Vizitat.add(new Point(Integer.MAX_VALUE,0));
		Node curent = listaNoduri.get(0);
		Vizitat.get(curent.getNumber()-1).x = 0;
		Queue<Node> search = new LinkedList<Node>();
		search.add(curent);
		while(search.size()>0)
		{																	//trebuie sa verific daca arcul curent a fost verificat
			curent =search.peek();
			for(int i=0;i<listaAdiacenta.get(curent.getNumber()-1).size();i++)
			{
				
				Point number = listaAdiacenta.get(curent.getNumber()-1).get(i);
				Point punctArc = new Point(curent.getNumber(),number.x);
				Point punctArcRev = new Point(number.x,curent.getNumber());
				if(Vizitat.get(number.x-1).x>number.y)
					if((!visitedArcs.contains(punctArc))&&(!visitedArcs.contains(punctArcRev)))
				{
						visitedArcs.add(punctArc);
					search.add(listaNoduri.get(number.x-1));
					Vizitat.get(number.x-1).x=number.y;
					Vizitat.get(number.x-1).y=curent.getNumber();
					
				}
			}
			search.remove();
		}
		

	}
}