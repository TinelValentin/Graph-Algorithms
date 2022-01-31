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

public class Kruskal extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;      
	private Vector<Arc> listaArceOrientate; 
	private  Vector<Arc> visitedArcs=new Vector<Arc>();
	ArrayList<ArrayList<Integer>> listaAdiacentaKruskal= new ArrayList<ArrayList<Integer>>();
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
	 
	public Kruskal()
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
	            	Kruskal();
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
		
		
		for(int i=0;i<listaAdiacentaKruskal.size();i++)
		{
			int start=i+1;
			for(int j=0;j<listaAdiacentaKruskal.get(i).size();j++)
			{
				int end = listaAdiacentaKruskal.get(i).get(j);
				for(Arc a : listaArceOrientate)
				{
					Point nrNoduri = a.getNrNoduri();
					if(nrNoduri.x==start&&nrNoduri.y==end)
						a.drawArc(g);
				}
			}
			
		}
		
		
	
		
	}
			       
}
	
	public Boolean ContainsCycle()
	{
		Boolean visited[] = new Boolean[listaNoduri.size()];
        for (int i = 0; i < listaNoduri.size(); i++)
            visited[i] = false;
        Stack<Integer> cycle = new Stack<Integer>();
        int curent;
        for(int i=0;i<listaAdiacentaKruskal.size();i++)
        {
        	if(!visited[i])
        	{
        		curent = listaNoduri.get(i).getNumber();
        		cycle.push(curent);
        		while(cycle.size()>0)
        			{curent=cycle.peek();
        		visited[curent-1]=true;
        		boolean found = false;
        		
        		 for(int j=0;j<listaAdiacentaKruskal.get(curent-1).size();j++)
        		 {
        			 
        			 
        			 if (!visited [listaAdiacentaKruskal.get(curent-1).get(j)-1])
        			 {
        				 curent = listaNoduri.get(listaAdiacentaKruskal.get(curent-1).get(j)-1).getNumber();
        				 cycle.push(curent);
        				 found = true;
        				 break;
        			 }
        			 else
        			 {
        				 if(cycle.search(listaAdiacentaKruskal.get(curent-1).get(j))>2)
        				 {
        					 return true;
        				 }
        				
        				 
        				 
        			 }
        			 
        		 }
        		 if(listaAdiacentaKruskal.get(i).size()==0)
        			 if(cycle.size()>0)
        				 cycle.pop();
        		 if(found == false)
        			 if(cycle.size()>0)
					 cycle.pop();
        	}
        		}
        }
        return false;
	}
	
	public void Kruskal()
	{
		for(ArrayList<Point> a : listaAdiacenta)
		{
			listaAdiacentaKruskal.add(new ArrayList<Integer>());
		}
		listaArceOrientate.sort(new Comparator<Arc>(){
			   @Override
			   public int compare(final Arc a,Arc b) {
			     return a.getCost() - b.getCost();
			     }
			 });
		int number =0;
		for(Arc b: listaArceOrientate)
		{
		Point getNoduri = b.getNrNoduri();
		if(listaAdiacentaKruskal.size()<1)
		{
			listaAdiacentaKruskal.get(getNoduri.x).add(getNoduri.y);
			listaAdiacentaKruskal.get(getNoduri.y).add(getNoduri.x);
			number ++;
		}
		else
		{
			listaAdiacentaKruskal.get(getNoduri.x-1).add(getNoduri.y);
			listaAdiacentaKruskal.get(getNoduri.y-1).add(getNoduri.x);
			number++;
			if(ContainsCycle())
			{
				listaAdiacentaKruskal.get(getNoduri.x-1).remove(listaAdiacentaKruskal.get(getNoduri.x-1).size()-1);
				number--;
				listaAdiacentaKruskal.get(getNoduri.y-1).remove(listaAdiacentaKruskal.get(getNoduri.y-1).size()-1);
			}
		}
		if(number==listaNoduri.size()-1)
			break;
		}
	
		
		
	}
}