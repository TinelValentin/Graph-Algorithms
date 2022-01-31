package def;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Arc
{
	private Point start;
	private Point end;
	private int cost;
	private Point nrNoduri;
	
	public Arc(Point start, Point end, int cost)
	{
		this.start = start;
		this.cost =	cost;	
		this.end = end;
	}
	public Arc(Point start, Point end, int cost,Point nrNoduri)
	{
		this.start = start;
		this.cost =	cost;	
		this.end = end;
		this.nrNoduri=nrNoduri;
	}
	
	public void drawArc(Graphics g)
	{
		if (start != null)
		{
            g.setColor(Color.RED);
            g.drawLine(start.x, start.y, end.x, end.y);
            g.setColor(Color.BLACK);
    	    g.drawString(((Integer)cost).toString(), (start.x+end.x)/2-5, (start.y+end.y)/2-5);
        }
	}
	
	
	

	
	public Point getStart()
	{
		return start;
	}
	public Point getEnd()
	{
		return end;
	}
	
	
	public Integer getCost()
	{
		return cost;
	}
	public void setEnd(Point aux)
	{
		this.end.x=aux.x+15;
		this.end.y=aux.y+15;
	}
	
	public void setNrNoduri(Point aux)
	{
		this.nrNoduri =aux;
	}
	public Point getNrNoduri()
	{
		return nrNoduri;
	}
	
	public void setStart(Point aux)
	{
		this.start.x=aux.x+15;
		this.start.y=aux.y+15;
	}
	
	public boolean comparator(Arc a,Arc b)
    {
        return  a.getCost() > b.getCost();
    }
	
}