package struktury;
/**
 * klasa reprezentujaca pare
 * <p>
 */
public class Para 
{
	public final String klucz;
	private double wartosc;
	/**
	 * konstruktor
	 * @param klucz klucz Pary
	 * @param wartosc wartość Pary
	 * <p>
	 */
	public Para(String klucz, double wartosc)
	{
		this.klucz=klucz;
		this.wartosc=wartosc;
	}
	/**
	 * funkcja zwracajaca wartosc pary
	 * @return wartość
	 */
	public double get() { return wartosc; }
	/**
	 * funkcja ustawiajaca nowa wartosc pary
	 * @param wartosc przypisanie nowej wartosci
	 */
	public void set(double wartosc) { this.wartosc=wartosc; }
	/**
	 * porownywanie par
	 * @param p sprawdzana para
	 * @return czy sie rownaja
	 */
	public boolean equals(Para p) { return klucz.equals(p.klucz); }
	/**
	 * konwertowanie do postaci string
	 */
	public String toString() { return "klucz: " + klucz + "wartosc: " + klucz.toString(); }
}
