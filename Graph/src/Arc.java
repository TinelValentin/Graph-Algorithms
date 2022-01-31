import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Arc
{
	private Point start;
	private Point end;
	
	public Arc(Point start, Point end)
	{
		this.start = start;
		this.end = end;
	}
	
	public void drawArc(Graphics g)
	{
		if (start != null)
		{
            g.setColor(Color.RED);
            g.drawLine(start.x, start.y, end.x, end.y);
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
	public void setEnd(Point aux)
	{
		this.end.x=aux.x+15;
		this.end.y=aux.y+15;
	}
	
	public void setStart(Point aux)
	{
		this.start.x=aux.x+15;
		this.start.y=aux.y+15;
	}
}
