package def;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Node
{
	private int coordX;
	private int coordY;
	int number;
	private boolean visited;
	
	public Node(int coordX, int coordY)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.visited=false;
	
	}
	public Node(int coordX, int coordY,int number)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.number=number;
		this.visited=false;
	
	}
	
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	int getNumber()
	{
		return number;
	}
	public void setVisited(boolean visit) {
		this.visited = visit;
	}
	public boolean getVisited()
	{
		return visited;
	}
	

	public void drawNode(Graphics g, int node_diam)
	{
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX, coordY, node_diam, node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(coordX, coordY, node_diam, node_diam);
        if(number < 10)
        	g.drawString(((Integer)number).toString(), coordX+13, coordY+20);
        else
        	g.drawString(((Integer)number).toString(), coordX+8, coordY+20);	
       	
	}
	
	public void drawNodeManual(Graphics g, int node_diam)
	{
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX, coordY, node_diam, node_diam);
        
        g.drawOval(coordX, coordY, node_diam, node_diam);
        
        if(number < 10)
        	g.drawString(((Integer)number).toString(), coordX+13, coordY+20);
        else
        	g.drawString(((Integer)number).toString(), coordX+8, coordY+20);	
	}
}
