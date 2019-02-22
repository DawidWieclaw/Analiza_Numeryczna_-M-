package narzędzia;
/**
 * <p>Klasa reprezentująca zmienna</p>
 */
public class Zmienna extends Operand
{
    String nazwa; //dopisz regexpa
    String pattern = "\\p{Alpha}\\p{Alnum}*";
    Zbior zmienne;

    /**
     *
     * @param s nazwa
     * @param zmienne zmienne do ktorych ma sie dodac
     */
    public Zmienna(String s, Zbior zmienne) throws WyjątekONP
    {
        if(!s.matches(pattern)) throw new ONP_NieznanySymbol();
        this.nazwa=s;
        this.zmienne=zmienne;
    }
    @Override
    public double oblicz()
    {
        return zmienne.get(nazwa);
    }
}
