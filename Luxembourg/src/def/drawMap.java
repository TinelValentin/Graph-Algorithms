package def;

import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class drawMap extends JPanel {
	
	Vector<Nodes> m_nodeList;
	Vector<Arc> m_arcList;
	Point coordFirstCity = new Point(-1,-1),coordLastCity=new Point(-1,-1);
	Integer nrOfClicks=0;
	int node_diam =10;
	
		drawMap(Vector<Nodes> nodeList,Vector<Arc> arcList)
		
		{
			
			
			
			m_nodeList=nodeList;
			m_arcList=arcList;
			repaint();
			
			
			addMouseListener(new MouseAdapter() {
						
				public void mousePressed(MouseEvent e) 
				{
					switch(nrOfClicks)
					{
					case 0:
						coordFirstCity= e.getPoint();
						nrOfClicks++;
						break;
					case 1:
						coordLastCity = e.getPoint();
						nrOfClicks++;
						break;
					case 2:
						coordFirstCity= e.getPoint();
						coordLastCity= new Point(-1,-1);
						nrOfClicks=1;
					}
					repaint();
				}
							
					});
		}
		
		public void updateQueue( PriorityQueue<Nodes> iteration,Nodes node)  //verific daca
		{
			if(iteration.contains(node))
			{
				for(Nodes a : iteration)
					if(a.getNumber()==node.getNumber())
					{
						if(a.getValue()!=node.getValue())
							iteration.remove(a);
						iteration.add(node);
						break;
					}
			}
			else
				iteration.add(node);
		}
		
		
		 public void Djekstra(Nodes start, Nodes end)
		 {
			 final long startTime = System.nanoTime();
			 for(int i=0;i<m_nodeList.size();i++)
			 {
				m_nodeList.get(i).setParent(-1);
				m_nodeList.get(i).setVisited(false);
				m_nodeList.get(i).setValue(Integer.MAX_VALUE);
			 }
			 start.setValue(0);
			 PriorityQueue<Nodes> iteration = new PriorityQueue<Nodes>(100000,new NodeComparator());
			 iteration.add(start);
			 
			 
			 while(iteration.size()>0)
			 {
				 Nodes current = iteration.poll();
				 m_nodeList.get(current.getNumber()).setVisited(true);
				 Vector<Point> neighboors = current.getNeighboors(); // get all the neighboors
				 for(int i=0;i<neighboors.size();i++)                // see if there is a neighboor not visited
				 {
					int cost = neighboors.get(i).y+current.getValue();
					if(cost<m_nodeList.get(neighboors.get(i).x).getValue())
						{
						Nodes addToIteration =m_nodeList.get(neighboors.get(i).x);
						m_nodeList.get(neighboors.get(i).x).setValue(cost);
						m_nodeList.get(neighboors.get(i).x).setParent(current.getNumber());
						if(!iteration.contains(addToIteration))
							{
							if(!addToIteration.getVisited())
							updateQueue(iteration,addToIteration);
							
							}
						}
					}
				 
				 
				 }
			 
			 
//			 Nodes currentRev = end;
//			 while(currentRev!=start)
//			 {
//				System.out.print(currentRev.getNumber()+" ");
//				currentRev=m_nodeList.get(currentRev.getParent());
//			 }
			 final long duration = System.nanoTime() - startTime;
				System.out.println("djekstra: "+duration/1000000000.); 
			 }
		 
		 
			 // set all nodes parent and visited false
			 //get the first verice from priority queue
			 //
		 
		
		private double calcDistance(int x1, int y1, int x2 , int y2)
		{
			double x = Math.pow(x1 -x2,2);
			double y = Math.pow(y1 -y2,2);
			return Math.sqrt(x+y);
		}

		
		private Nodes findNode(Point coordinates)
		{
			Nodes nod = new Nodes();
			double distance = Double.MAX_VALUE;
			for(int i=0;i<m_nodeList.size();i++)
			{
				double dist = calcDistance(m_nodeList.get(i).getCoordX(),m_nodeList.get(i).getCoordY(),coordinates.x,coordinates.y);
				if(dist<distance)
					
				{
				distance =dist;

				nod =m_nodeList.get(i);
			}
				
				}
			
			return nod;
			
		}
		
		public Arc findArc(Nodes nod)
		{
			
			Arc aux= new Arc();
			for(Arc a : m_arcList)
			{
				if(a.getNrNoduri().x==nod.getNumber()&&a.getNrNoduri().y==nod.getParent())
					return a;
				if(a.getNrNoduri().x==nod.getParent()&&a.getNrNoduri().y==nod.getNumber())
					return a;
			}
			return aux;
		}
	
	protected void paintComponent(Graphics g)
	{    
		super.paintComponent(g);
		final long startTime = System.nanoTime();
		Nodes first= new Nodes(),second = new Nodes();
		
		for (Arc b: m_arcList)
		{
			b.drawArc(g);
		}
		
		
		if(coordFirstCity.x!=-1)
		{
			 first =findNode(coordFirstCity);
			first.drawNode(g, node_diam);
		}
		
		if(coordLastCity.x!=-1)
		{
			 second =findNode(coordLastCity);
			second.drawNode(g, node_diam);
		}
		
		if(coordFirstCity.x!=-1&&coordLastCity.x!=-1)
		{
			Djekstra(first,second);
			Nodes current = second;
			while(current!= first)
			{
				Arc currentArc = findArc(current);
				currentArc.drawArcReverse(g);
				current = m_nodeList.get(current.getParent());
				
			}
			 final long duration = System.nanoTime() - startTime;
				System.out.println("desenat + algoritm: "+duration/1000000000.);
		}
		
	
		
	}		

		
	
	  
}