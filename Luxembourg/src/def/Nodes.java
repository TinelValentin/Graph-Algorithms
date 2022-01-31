package def;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Point;

public class Nodes
{
	private int coordX;
	private int coordY;
	private Vector<Point> neighboors;
	private int value;
	private boolean visited;
	private int parent;
	private int number;
	
	public Nodes(int coordX, int coordY,int number)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.visited=false;
		this.number= number;
		parent = -1;
		neighboors = new Vector<Point>();
		value=0;
	
	}
	
	public Nodes(int coordX, int coordY,Vector<Point> neighboors,int value,boolean visited,int parent)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.visited=visited;
		this.parent = parent;
		this.neighboors = neighboors;
		this.value=value;
	
	}
	
	
	public Nodes() {
		// TODO Auto-generated constructor stub
	}
	public int getCoordX() {
		return coordX;
	}
	public int getValue() {
		return value;
	}
	public int getNumber() {
		return number;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	
	public Vector<Point> getNeighboors() {
		return neighboors;
	}
	
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public void setVisited(boolean visit) {
		this.visited = visit;
	}
	public boolean getVisited()
	{
		return visited;
	}
	
	public void setParent(int uax)
	{
		parent = uax;
	}
	
	public Integer getParent()
	{
		return parent;
	}
	public void addNeighboor(Point node)
	{
		neighboors.add(node);
	}
	public void drawNode(Graphics g, int node_diam)
	{
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX, coordY, node_diam, node_diam);
    
      
       	
	}
	
	
	
}
