import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class OrientedArc
{
	private Point start;
	private Point end;
	
	public OrientedArc(Point start, Point end)
	{
		this.start = start;
		this.end = end;
	}
	
	public void drawArrowLine(Graphics g,int d, int h) {
	    int dx = end.x - start.x, dy = end.y - start.y;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + start.x;
	    ym = xm*sin + ym*cos + start.y;
	    xm = x;

	    x = xn*cos - yn*sin + start.x;
	    yn = xn*sin + yn*cos + start.y;
	    xn = x;

	    int[] xpoints = {end.x-1, (int) xm, (int) xn};
	    int[] ypoints = {end.y-1, (int) ym, (int) yn};

	    g.setColor(Color.RED);
	    g.drawLine(start.x, start.y, end.x, end.y);
	    g.fillPolygon(xpoints, ypoints, 3);
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
