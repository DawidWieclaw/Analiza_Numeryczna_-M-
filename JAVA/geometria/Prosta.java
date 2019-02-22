package geometria;

public class Prosta 
{
	public final double a,b,c;
	
	Prosta(double a, double b, double c)
	{
		this.a=a;
		this.b=b;
		this.c=c;
	}
	Prosta() //y=x
	{
		this.a=1;
		this.b=-1;
		this.c=0;
	}
	
	public static Prosta przesun(Wektor v, Prosta p)//wez jeszce sprawdz
	{
		return new Prosta(p.a-v.dx, p.b-v.dy, p.c);
	}
	
	public static boolean czyRownolegle(Prosta p, Prosta q) //dopracuj dla poziomych i pionowych
	{
		if(p.a/p.b==q.a/q.b) return true;
		return false;
	}
	
	public static boolean czyProstopadle(Prosta p, Prosta q)
	{
		return true;
	}	
	
	public static Punkt przeciecie(Prosta p, Prosta q)
	{
		return new Punkt();
	}
}
