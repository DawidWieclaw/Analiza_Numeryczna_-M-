package geometria;

public class Odcinek 
{
	private Punkt a,b;
	public Odcinek(Punkt a, Punkt b)
	{
		this.a=a;
		this.b=b;
	}
	public Odcinek()
	{
		this.a=new Punkt();
		this.b=new Punkt(1.0,1.0);
	}

	public void przesun(Wektor v)
	{
		a.przesun(v);
		b.przesun(v);
	}
	
	public void obroc(Punkt p, double kat)
	{
		a.obroc(p, kat);
		b.obroc(p, kat);
	}
	
	public void odbij(Prosta p)
	{
		a.odbij(p);
		b.odbij(p);
	}
}
