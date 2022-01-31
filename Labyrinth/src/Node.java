import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

public class Node {
	private int number;
	private Vector<Integer> neighboors;
	private boolean start;
	private boolean finish;
	private boolean visited;
	private int Parent;
	private boolean colored;
	Node(int number)
	{
	this.number=number;	
	start=false;
	finish=false;
	visited=false;
	Parent=-1;
	colored=false;
	neighboors=new Vector<Integer>();

	}
	
	Node(int number,boolean finish)
	{
	this.number=number;	
	this.start=false;
	this.finish=true;
	this.visited=false;
	this.Parent=-1;
	this.colored=false;
	neighboors=new Vector<Integer>();
	}
	
	Node(Node nod)
	{
		this.number=nod.number;
		this.start=nod.start;
		this.finish=nod.finish;
		this.visited=nod.visited;
		this.neighboors=nod.neighboors;
		this.colored=nod.colored;
	}
	
	public void setNeighboors(int number)
	{
	neighboors.add(number);
	}
	
	public void setStart(boolean aux)
	{
	this.start=aux;
	}
	
	public void setFinish(boolean aux)
	{
	this.finish=aux;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public Vector<Integer> getNeighboors()
	{
		return neighboors;
	}
	
	public boolean getVisited()
	{
		return visited;
	}
	public boolean getFinish()
	{
		return finish;
	}
	
	public boolean getStart()
	{
		return start;
	}
	
	public void setVisited(boolean aux)
	{
		this.visited=aux;
	}
	
	public void setParent(int parent)
	{
		this.Parent=parent;
	}
	public void setColored(boolean aux)
	{
		this.colored=aux;
	}
	
	public boolean getColored()
	{
		return colored;
	}
	public int getParent()
	{
		return Parent;
	}
	
	public void drawNode(Graphics g, int node_diam,int x,int y)
	{
        g.fillOval(x, y, node_diam, node_diam);
   
        g.drawOval(x, y, node_diam, node_diam);
        
	}
}
