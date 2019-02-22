package geometria;
import java.math.*;

public class Punkt 
{
	private double x;
	private double y;
	
	public double x() {return x;}
	public double y() {return x;}
	
	
	public Punkt(double x, double y)
	{
		this.x=x;
		this.y=y;
	}
	
	public Punkt()
	{
		this.x=0;
		this.y=0;
	}
	
	public void obroc(Punkt p, double kat)
	{
		kat=Math.toRadians(kat);
		double dx=this.x-p.x();
		double dy=this.y-p.y();
		x=dx*Math.cos(kat)-dy*Math.sin(kat)+p.x();
		y=dx*Math.sin(kat)+dy*Math.cos(kat)+p.y();
	}
	
	public void przesun(Wektor v)
	{
		x+=v.dx;
		y+=v.dy;
	}
	
	public void odbij(Prosta p)
	{
		double d = ((p.a * p.a * y) - (2*p.a * p.b * x) - (2*p.c * p.b) - (p.b*p.b*y)) / (p.a * p.a + p.b * p.b);
        x =(p.a * (d - y)) / p.b + x;
        y = d;
	}
	
	public String toString()
	{
		return "x: " +String.valueOf(x)+ "  y: " + String.valueOf(y);
	}
}
