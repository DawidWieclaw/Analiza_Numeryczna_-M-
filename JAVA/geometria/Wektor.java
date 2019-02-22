package geometria;

public class Wektor 
{
	public final double dx;
	public final double dy;
	
	Wektor(double dx,double dy)
	{
		this.dx=dx;
		this.dy=dy;
	}
	Wektor()
	{
		this.dx=1;
		this.dy=1;
	}
	
	static Wektor zloz(Wektor v, Wektor w)//chyba trezba bedzei zwrocic nowy wektor
	{
		return new Wektor(w.dx+v.dx, w.dy+v.dy);
	}
}
