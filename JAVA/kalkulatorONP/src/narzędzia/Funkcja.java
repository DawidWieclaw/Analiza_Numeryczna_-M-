package narzędzia;
/**
 * <p>Abstrakcyjna klasa reprezentująca funkcję</p>
 */
public abstract class Funkcja extends Symbol implements Funkcyjny
{
    Stos stos;
    double arg1;
    double arg2;
    int liczba_argumentow=0;

    /**
     *
     * @param stos stos przekazywany do kazdej funkcji
     */
    public Funkcja(Stos stos) { this.stos=stos; }

    @Override
    public void dodajArgument(double x)  throws WyjątekONP
    {
        if(brakująceArgumenty()==0) throw new ONP_BłędneWyrażenie();
        else if(brakująceArgumenty()==1) arg2=x;
        else arg1=x;
        liczba_argumentow++;
    }

    @Override
    public int brakująceArgumenty() { return arność()-liczba_argumentow; }
}
