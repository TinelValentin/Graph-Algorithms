
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


import javax.swing.JPanel;


public class Window extends JPanel {

	
	int diameter=10;
	int nodes;
	Graphics g;
	int error=0;
	double chance=0.1;
	Vector<Arc> ArcVector = new Vector<Arc>();
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
	
	
	 
	 
	
	public  Window(int nodes,int width,int height)
	{
		int widthaux=width;
		
		for(int i=0;i<nodes;i++)
		{
			Point verify=new Point();
			 verify.x = (int)(Math.random()*(((width-100)-1)));
			 verify.y= (int)(Math.random()*(((height-100)-1)));
			 
			 if(inRange(pointMap,verify))
				 {
				 i--;
				 error++;
				if(error== widthaux/2) {
					diameter/=2;
					widthaux*=2;
				}
				 }
			 else
			 {
				 
				 Node newNode = new Node(verify.x,verify.y);
				 PointVector.add(newNode);
				 for(int j=0;j<PointVector.size();j++)
				 {
					 double random=(double)((Math.random()*((width-1))))%width;
					 if(random<chance) 
						 {
						 Point aux=new Point(PointVector.get(j).getCoordX(),PointVector.get(j).getCoordY());
						 Arc newArc = new Arc(verify,aux);
						 ArcVector.add(newArc);
						 }
				 }
				 
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
			  i.drawNode(g, diameter);
			  
		  } 
		  g.setColor(Color.RED);
		  for(Arc j:ArcVector) 
		  {
	 
			  j.drawArrowLine(g,diameter/2,diameter/2);
	  
		  } 
		

		
}
}
