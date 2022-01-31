import java.util.Stack;
import java.util.Vector;
import java.util.Queue;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Line2D;


public class Window extends JPanel {
	Vector<Queue<Node>> paths;
	Node[][] matrix;
	int collumns,rows;
	 //400 300 start o celula sa aiba 50
	@SuppressWarnings("rawtypes")
	public Window(Vector<Queue<Node>> pathsA,Node[][] matrixA,int collumnsA,int rowsA)
	{
		paths=pathsA;
		matrix=matrixA;
		collumns=collumnsA;
		rows=rowsA;
		
		
	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    int xStart=400;
	    int yStart=300;
	    g2.setStroke(new BasicStroke(5));
	    for(int i=0;i<rows;i++)
		{
	    	xStart=400;
			for(int j=0;j<collumns;j++)
			{
				
				if(matrix[i][j].getFinish()==true)
				{
					g2.setColor(Color.RED);
					matrix[i][j].setColored(true);
					matrix[i][j].drawNode(g2, 20, xStart+10, yStart+10);
				
					
				}
				if(matrix[i][j].getStart()==true)
				{
					g2.setColor(Color.BLUE);
					matrix[i][j].setColored(true);
					matrix[i][j].drawNode(g2, 20, xStart+10, yStart+10);
				}
				g2.setColor(Color.BLACK);
				if(i-1>=0)
					{
					if(matrix[i-1][j].getNumber()==0&&matrix[i][j].getNumber()!=0) 
							g2.drawLine(xStart,yStart,xStart+40,yStart);
					}
				else
					if(matrix[i][j].getFinish()==false||matrix[i][j].getStart()==false)
						g2.drawLine(xStart,yStart,xStart+40,yStart);
				
				if(i+1<rows)
					{
					if(matrix[i+1][j].getNumber()==0&&matrix[i][j].getNumber()!=0) 
						g2.drawLine(xStart,yStart+40,xStart+40,yStart+40);
					}
				else
					if(matrix[i][j].getFinish()==false||matrix[i][j].getStart()==false)
						g2.drawLine(xStart,yStart+40,xStart+40,yStart+40);
				
				if(j+1<collumns)
					{
					if(matrix[i][j+1].getNumber()==0&&matrix[i][j].getNumber()!=0) 
						g2.drawLine(xStart+40,yStart,xStart+40,yStart+40);
					}
				else
					if(matrix[i][j].getFinish()==false||matrix[i][j].getStart()==false)
						g2.drawLine(xStart+40,yStart,xStart+40,yStart+40);
				
				if(j-1>=0)
					{
					if(matrix[i][j-1].getNumber()==0&&matrix[i][j].getNumber()!=0) 
						g2.drawLine(xStart,yStart,xStart,yStart+40);
					}
				else
					if(matrix[i][j].getFinish()==false||matrix[i][j].getStart()==false)
						g2.drawLine(xStart,yStart,xStart,yStart+40);
				xStart+=40;
			}
			yStart+=40;
		}
	    
	    
	    xStart=400;
	    yStart=300;
    for(int k=0;k<paths.size();k++)
    {   
    	yStart=300;
	for(int i=0;i<rows;i++) 
	{
		xStart=400;
		for(int j=0;j<collumns;j++)
		{
			
			if(paths.get(k).contains(matrix[i][j])&&matrix[i][j].getFinish()==false&&matrix[i][j].getStart()==false)	
			{
				g2.setColor(Color.GREEN);
				matrix[i][j].drawNode(g2, 20, xStart+10, yStart+10);
				matrix[i][j].setColored(true);
				}
			xStart+=40;
		}
		yStart+=40;
	}
	    
    }			
    yStart=300;
    for(int i=0;i<rows;i++) 
	{
		xStart=400;
		for(int j=0;j<collumns;j++)
		{
			
			if(matrix[i][j].getColored()==false)
				if(matrix[i][j].getNumber()==0)
				{
					g2.setColor(Color.BLACK);
					matrix[i][j].drawNode(g2, 20, xStart+10, yStart+10);
					matrix[i][j].setColored(true);
					}	
				else
			{
				g2.setColor(Color.WHITE);
				matrix[i][j].drawNode(g2, 20, xStart+10, yStart+10);
				matrix[i][j].setColored(true);
				}
		
				
			
			xStart+=40;
		}
		yStart+=40;
	}
	   
    }							
	
	}
	
	

