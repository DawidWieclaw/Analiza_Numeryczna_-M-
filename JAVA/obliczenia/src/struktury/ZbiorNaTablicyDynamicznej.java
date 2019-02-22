package struktury;
/**
 * klasa reprezentujaca zbior na tablicy dynamicznej
 * <p>
 */
public class ZbiorNaTablicyDynamicznej extends ZbiorNaTablicy
{
	/**
	 * 
	 * konstruktor
	 */
	public ZbiorNaTablicyDynamicznej()
	{
		super();
	}
	/**
	 * wstawianie nowej pary
	 * @param p wstawiana para
	 * @throws Exception gdy dana para jest juz w zbiorze
	 */
	public void wstaw(Para p) throws Exception
	{
		if(this.szukaj(p.klucz)!=null) throw new Exception("juzj jest");
		if(k==n)
		{
			Para[] tablica2=new Para[2*n];
			System.arraycopy(tablica, 0, tablica2, 0, n);
			n*=2;
			tablica=tablica2;
		}
		tablica[k]=p;
		k++;
	}
	/**
	 * ustawianie nowej pary
	 * @param p ustawiana para
	 * @throws Exception gdy dana para juz jest
	 */
	public void ustaw(Para p) throws Exception
	{
		if(this.szukaj(p.klucz)!=null) throw new Exception("juzj jest");
		if(k==n)
		{
			Para[] tablica2=new Para[2*n];
			System.arraycopy(tablica, 0, tablica2, 0, n);
			n*=2;
			tablica=tablica2;
		}
		tablica[k]=p;
		k++;
	}

}