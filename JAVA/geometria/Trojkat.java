package geometria;

public class Trojkat 
{
	private Punkt a,b,c;
	public Trojkat(Punkt a, Punkt b, Punkt c)
	{
		this.a=c;
		this.b=b;
		this.c=c;
	}
	public Trojkat()
	{
		this.a=new Punkt();
		this.b=new Punkt(0.0, 1.0);
		this.c=new Punkt(1.0, 0.0);
	}
	
	public void przesun(Wektor v)
	{
		a.przesun(v);
		b.przesun(v);
		c.przesun(v);
	}
	
	public void obroc(Punkt p, double kat)
	{
		a.obroc(p, kat);
		b.obroc(p, kat);
		c.obroc(p, kat);
	}
	
	public void odbij(Prosta p)
	{
		a.odbij(p);
		b.odbij(p);
		c.odbij(p);
	}
}
