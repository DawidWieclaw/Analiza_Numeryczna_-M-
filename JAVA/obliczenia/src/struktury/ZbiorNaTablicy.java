
package struktury;
/**
 * klasa reprezentujaca zbior na tablicy
 * <p>
 */
public class ZbiorNaTablicy extends Zbior
{
	protected Para[] tablica;
	protected int n;
	protected int k=0;
	/**
	 * konstruktor z definiowaniem wielkosci tablicy
	 * @param n wielkosc tablicy
	 * @throws Exception kiedy wielkosc tablicy jest zbyt mala
	 */
	public ZbiorNaTablicy(int n) throws Exception
	{
		if(n<2) throw new Exception("za mało");
		tablica=new Para[n];
		this.n=n;
	}
	public ZbiorNaTablicy()
	{
		tablica=new Para[2];
		this.n=2;
	}
	/**
	 * wyszukiwanie pary z danym kluczem
	 * @param s szukany klucz
	 * @throws Exception kiedy nie znaleziono
	 */
	public Para szukaj (String s)
	{
		for(int i=0; i<this.k; i++)
		{
			if( tablica[i].klucz.equals(s) ) return tablica[i];
		}
		return null;
	}
	/**
	 * wstawia nowa pare
	 * @param p wstawiana para
	 * @throws Exception kiedy element juz jest w tablicy lub tablica jest pelna 
	 */
	public void wstaw(Para p) throws Exception
	{
		if(this.szukaj(p.klucz)!=null) throw new Exception("juzj jest");
		tablica[k]=p;
		k++;
	}
	/**
	 * czytanie wartosci pary z klcuzem k
	 * @param k czytanie wartosci pary z kluczem k
	 */
	public double czytaj(String k) throws Exception
	{
		for(int i=0; i<this.k; i++)
		{
			if(tablica[i].klucz.equals(k)) return tablica[i].get();
		}
		throw new Exception("nie ma");
	}
	/**
	 * ustawianie nowej pary
	 * @param p ustawiana wartosc
	 */
	public void ustaw(Para p) throws Exception
	{
		for(int i=0; i<k; i++)
		{
			if(tablica[i].equals(p))
			{
				tablica[i]=p;
				return;
			}
		}
		if(k==n) throw new Exception("nie zmeisci sie");
		tablica[k]=p;
		k++;
	}
	/**
	 * funkcja czyszczaca tablice
	 */
	public void czysc()
	{
		tablica=new Para[n];
		k=0;
	}
	/**
	 * funkcja zwracajaca informacje o ilosci elementow w tablicy
	 * @return liczba elementow w tablicy
	 */
	public int ile() {return this.k;}
}
