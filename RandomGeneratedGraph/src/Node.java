import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Node
{
	private int coordX;
	private int coordY;
	
	public Node(int coordX, int coordY)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		
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
	
	public void drawNode(Graphics g, int node_diam)
	{
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX, coordY, node_diam, node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(coordX, coordY, node_diam, node_diam);
        
	}
}
