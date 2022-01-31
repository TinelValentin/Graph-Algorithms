package def;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Arc
{
	private Point start;
	private Point end;
	private int capacitate;
	private int flux;
	private Point nrNoduri;
	
	public Arc(Point start, Point end, int capacitate)
	{
		this.flux=0;
		this.start = start;
		this.capacitate =capacitate;	
		this.end = end;
	}
	public Arc(Point start, Point end, int capacitate,int flux,Point nodes)
	{
		this.start = start;
		this.capacitate =capacitate;	
		this.flux =flux;	
		this.end = end;
		this.nrNoduri=nodes;
	}
	public Arc(Point start, Point end, int capacitate,Point nrNoduri)
	{
		this.start = start;
		this.capacitate =	capacitate;	
		this.end = end;
		this.nrNoduri=nrNoduri;
	}
	
	public void drawArc(Graphics g)
	{
		 
		int d=10;
		int h=10;
		if (start != null)
		{
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

		    
		   
		    
		    g.drawLine(start.x, start.y, end.x, end.y);
		    g.fillPolygon(xpoints, ypoints, 3);
            g.setColor(Color.BLACK);
    	    g.drawString(((Integer)flux).toString()+" / "+((Integer)capacitate).toString(), (start.x+end.x)/2-5, (start.y+end.y)/2-5);
        }
	}
	
	
	

	public int getFlux()
	{
		return flux;
	}
	public Point getStart()
	{
		return start;
	}
	public Point getEnd()
	{
		return end;
	}
	
	
	
	public Integer getCapacitate()
	{
		return capacitate;
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
	
	public void setFlux(int flux)
	{
		this.flux=flux;
	}
	
	
}