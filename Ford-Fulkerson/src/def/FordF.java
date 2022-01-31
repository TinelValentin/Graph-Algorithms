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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FordF extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;      
	private Vector<Arc> listaArceOrientate; 
	ArrayList<ArrayList<Integer>> listaAdiacenta= new ArrayList<ArrayList<Integer>>();
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
	 Node selectStart =null,selectFinish=null;
	 int parent[];
	 ArrayList<int[]> allParents= new ArrayList<int[]>();
	 boolean capacitateSiFlux;
	 
	 
	 
	 
	 
	 
	public FordF(boolean capacF)
	{
		capacitateSiFlux=capacF;
		JLabel capacityLabel =new JLabel("capacitate");
		add(capacityLabel);
		JTextField capacityInput = new JTextField(16);
		add(capacityInput);
		JTextField fluxInput = new JTextField(16);
		if(capacF)
		{
			JLabel fluxLabel =new JLabel("flux");
			add(fluxLabel);
			add(fluxInput);
		}
		
		repaint();
		listaNoduri = new Vector<Node>();
		 listaArceOrientate=new Vector<Arc>();
		
		
		
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
							String fluxString="0";
							String aux = capacityInput.getText();
							int number = Integer.parseInt(aux);
							
							
							listaAdiacenta.get(pozStart).set(pozFinish,number);
							
							//listaAdiacenta.get(pozFinish).add(-(pozStart+1));
							Arc arcOrientat ;
							if(capacF)
							{
								if(fluxInput.getText()!="")
									fluxString = fluxInput.getText();
								int flux = Integer.parseInt(fluxString);
								 arcOrientat = new Arc(pointStart, pointEnd,number,flux,new Point(pozStart,pozFinish));
							}
							
							else
								{ arcOrientat = new Arc(pointStart, pointEnd,number,new Point(pozStart,pozFinish));}
							
							
							
							
							
							
							
							
							
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
	
					for(int i=0;i<listaNoduri.size();i++)
					{
						if(pointStartDrag.x - listaNoduri.get(i).getCoordX()<30&&pointStartDrag.y - listaNoduri.get(i).getCoordY()<30)
							if(pointStartDrag.x - listaNoduri.get(i).getCoordX()>0&&pointStartDrag.y - listaNoduri.get(i).getCoordY()>0)
							{
								if(selectStart==null) selectStart = listaNoduri.get(i);
								else
									if(selectFinish==null)
										{
										
										algoritm = true;
										selectFinish = listaNoduri.get(i);																          
						                System.out.println("Fluxul maxim este:  "
						                                   + fordFulkerson(listaAdiacenta, selectStart.getNumber()-1, selectFinish.getNumber()-1,parent)); //merge matricea dar alg nu :/
									
										}else
									{
										
										selectStart = listaNoduri.get(i);
										selectFinish=null;
									
									}
								repaint();
							}	
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

	
	private void addNode(int x, int y) {
		Node node = new Node(x, y,nodeNr);
		listaNoduri.add(node);
		for(int i=0;i<listaNoduri.size()-1;i++)
			listaAdiacenta.get(i).add(0);
		listaAdiacenta.add(new ArrayList<Integer>());
		for(int i=0;i<listaNoduri.size();i++)
			listaAdiacenta.get(listaAdiacenta.size()-1).add(0);
		nodeNr++;
		repaint();
		
	}
	


	protected void paintComponent(Graphics g)
	{    
		super.paintComponent(g);
		
	if(algoritm==false)
		
	{
		for(Arc b : listaArceOrientate)
		{
			g.setColor(Color.RED);
			b.drawArc(g);
		}
		
	g.setColor(Color.RED);
	if (pointStart != null)
	{ 
		 g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
	}
	
	for(int i=0; i<listaNoduri.size(); i++)
	{
		g.setColor(Color.RED);
		if(listaNoduri.get(i)==selectStart||listaNoduri.get(i)==selectFinish)
			g.setColor(Color.ORANGE);
		listaNoduri.elementAt(i).drawNode(g, node_diam);
	}
	}
	else
	{
		
	
	
	for(int i=0; i<listaNoduri.size(); i++)
	{
		g.setColor(Color.RED);
		if(listaNoduri.get(i)==selectStart||listaNoduri.get(i)==selectFinish)
			g.setColor(Color.ORANGE);
		listaNoduri.elementAt(i).drawNode(g, node_diam);
	}
		
	
	for(int i=0;i<allParents.size();i++)	
	{
		for(int j=0;j<listaNoduri.size();j++)
		{
			if(allParents.get(i)[j]!=0)
			{
				
				if(allParents.get(i)[j]!=-1)
					
				{
					g.setColor(Color.ORANGE);
				if(findArc(allParents.get(i)[j]-1,j)!=null)
					findArc(allParents.get(i)[j]-1,j).drawArc(g);
				g.setColor(Color.ORANGE);
				listaNoduri.elementAt(allParents.get(i)[j]-1).drawNode(g, node_diam);
				
				}
			}
		}
		
	}
		
		
	
	}
		
		
	
		
	}
			
	Arc findArc(int start,int end)
	{
		
		for(Arc a :listaArceOrientate)
			if(start==a.getNrNoduri().x&&end==a.getNrNoduri().y)
				return a;
			
		return null;
	}
		
	
	void updateArc(int start,int end,int flux)
	{
		
		for(Arc a :listaArceOrientate)
			if(start==a.getNrNoduri().x&&end==a.getNrNoduri().y)
			{
				a.setFlux(a.getFlux()+flux);
				break;
			}
		
		
		
	}
	

	 boolean bfs(int rGraph[][], int start, int finish, int parent[])
	    {
	       
	        boolean visited[] = new boolean[listaNoduri.size()];
	        for (int i = 0; i < listaNoduri.size(); ++i)
	            visited[i] = false;

	       
	        LinkedList<Integer> queue = new LinkedList<Integer>();
	        queue.add(start);
	        visited[start] = true;
	        parent[start] = -1;

	        
	        while (queue.size() != 0) {
	            int front = queue.poll();

	            for (int i = 0; i < listaNoduri.size(); i++) {
	                if (visited[i] == false
	                    && rGraph[front][i] > 0) {
	                   
	                    if (i == finish) {
	                        parent[i] = front;
	                        return true;
	                    }
	                    queue.add(i);
	                    parent[i] = front;
	                    visited[i] = true;
	                }
	            }
	        }

	        
	        return false;
	    }

	 
	 
	 void copyParents(int[] parent,int start,int finish)
	 {
		 for (int i = finish; i != start; i = parent[i]-1) 
         {
			 if(parent[i]==-1)
	    		 break;
     	parent[i]++;
         }
     int [] copy =new int[listaNoduri.size()];
     for(int i=0;i<listaNoduri.size();i++)
     	copy[i]=parent[i];
     allParents.add(copy);
     for (int i = finish; i != start; i = parent[i]) 
     {
    	 if(parent[i]==-1)
    		 break;
 	parent[i]--;
     }
	 }
	 
	    int fordFulkerson(ArrayList<ArrayList<Integer>> graph, int start, int finish,int parent[])
	    {
	    	
	        
	        int rGraph[][] = new int[listaNoduri.size()][listaNoduri.size()];

	        for (int i = 0; i < listaNoduri.size(); i++)
	            for (int j = 0; j < listaNoduri.size(); j++)
	            
	            { 
	            	rGraph[i][j] = graph.get(i).get(j);
	            	if(capacitateSiFlux)
	            		{
	            		Arc aux =findArc(i,j);
	            		if(aux!=null)
	            		{rGraph[j][i]= aux.getFlux(); //iau fluxul arcului
	            		rGraph[i][j]-=aux.getFlux();
	            		}
	            		}
	            	
	            }

	  
	         parent = new int[listaNoduri.size()];

	        int max_flow = 0; 

	       
	        while (bfs(rGraph, start, finish, parent)) {
	            
	            int path_flow = Integer.MAX_VALUE;
	            for (int i = finish; i != start; i = parent[i]) {
	                int j = parent[i];
	                path_flow = Math.min(path_flow, rGraph[j][i]);
	            }

	           
	            for (int i = finish; i != start; i = parent[i]) {
	                int j = parent[i];
	                updateArc(j,i,path_flow);
	                rGraph[j][i] -= path_flow;
	                rGraph[i][j] += path_flow;
	            }

	            
	            max_flow += path_flow;
	           copyParents(parent,start,finish);
	        }

	      
	        return max_flow;
	    }
	        
}
	

	
