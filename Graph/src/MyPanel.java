import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyPanel extends JPanel {
	private int nodeNr = 1;             
	private int node_diam = 30;         
	private Vector<Node> listaNoduri;   
	private Vector<Arc> listaArce;      
	private Vector<ArcOrientat> listaArceOrientate; 
	ArrayList<ArrayList<Integer>> listaAdiacenta= new ArrayList<ArrayList<Integer>>();
	Point pointStart = null;
	Point pointEnd = null;
	boolean isDragging = false;
	boolean auxiliar =false,start=false,finish=false,orient2,paint=false,drag=false;           
	double distanta;                                           
	int x1, y1,pozStart,pozFinish;                          
	Point pointStartDrag;
	
	
	public MyPanel(boolean orient)
	{
		orient2=orient;
		listaNoduri = new Vector<Node>();
		if( orient==false) listaArce = new Vector<Arc>();
		else listaArceOrientate=new Vector<ArcOrientat>();
		
		
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
					if(orient==false)
					if(start==true&&finish==true&&pozStart!=pozFinish)
					{
						listaAdiacenta.get(pozStart).add(pozFinish+1);
						listaAdiacenta.get(pozFinish).add(pozStart+1);
					Arc arc = new Arc(pointStart, pointEnd);
					listaArce.add(arc);	
					paint=true;
					
					}
					else
						pointStart=null;
					else
						if(start==true&&finish==true&&pozStart!=pozFinish)
						{
							listaAdiacenta.get(pozStart).add(pozFinish+1);
							
						ArcOrientat arcOrientat = new ArcOrientat(pointStart, pointEnd);
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
				if(start==true&&finish==false&&orient==false)
							{
					for(int i=0;i<listaArce.size();i++)
					{
					if(listaArce.get(i).getEnd().x - listaNoduri.get(pozStart).getCoordX()<30&&listaArce.get(i).getEnd().y-listaNoduri.get(pozStart).getCoordY()<30)
						if(listaArce.get(i).getEnd().x - listaNoduri.get(pozStart).getCoordX()>0&&listaArce.get(i).getEnd().y-listaNoduri.get(pozStart).getCoordY()>0)
							listaArce.get(i).setEnd(pointEnd);
					
					if(listaArce.get(i).getStart().x - listaNoduri.get(pozStart).getCoordX()<30&&listaArce.get(i).getStart().y-listaNoduri.get(pozStart).getCoordY()<30)
						if(listaArce.get(i).getStart().x - listaNoduri.get(pozStart).getCoordX()>0&&listaArce.get(i).getStart().y-listaNoduri.get(pozStart).getCoordY()>0)
					
							listaArce.get(i).setStart(pointEnd);
					}
							listaNoduri.get(pozStart).setCoordX(pointEnd.x);
							listaNoduri.get(pozStart).setCoordY(pointEnd.y);
							repaint();
							}
				
				if(start==true&&finish==false&&orient==true)	
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
		Node node = new Node(x, y, nodeNr);
		listaNoduri.add(node);
		listaAdiacenta.add(new ArrayList<Integer>());
		nodeNr++;
		repaint();
	}
	
	//se executa atunci cand apelam repaint()
	protected void paintComponent(Graphics g)
	{    
		super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
		g.drawString("Right Click to drag a node!", 10, 20);
		//deseneaza arcele existente in lista
		/*for(int i=0;i<listaArce.size();i++)
		{
			listaArce.elementAt(i).drawArc(g);
		}*/
		if(orient2==false)
		for (Arc a : listaArce)
		{
			a.drawArc(g);
		}
		else
			for(ArcOrientat b : listaArceOrientate)
			{
				b.drawArrowLine(g,20,20);
			}
		//deseneaza arcul curent; cel care e in curs de desenare
		if(orient2==false)
		{if (pointStart != null)
		{
			g.setColor(Color.RED);
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
		}
		}
		else
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
		//deseneaza lista de noduri
		for(int i=0; i<listaNoduri.size(); i++)
		{
			listaNoduri.elementAt(i).drawNode(g, node_diam);
		}
		
		try
		{
		FileWriter fisier = new FileWriter("Matrice");
		fisier.write("Sunt " +listaAdiacenta.size()+ " noduri!");
		fisier.write(System.getProperty( "line.separator" ));
		for (int i = 0; i < listaAdiacenta.size(); i++) 
			{
			fisier.write("Nodul " + (i+1) + "  ");
			for(int j=0;j<listaAdiacenta.get(i).size();j++)
			
				 fisier.write(listaAdiacenta.get(i).get(j) + " "+ " ");
			fisier.write(System.getProperty( "line.separator" ));
			}
		fisier.close();
		}
		catch(IOException ie)  {
            ie.printStackTrace();
        } 
		/*for (Node nod : listaNoduri)
		{
			nod.drawNode(g, node_diam, node_Diam);
		}*/
	}
}
