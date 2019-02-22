package narzędzia;

public class Phi extends Funkcja
{
    public Phi(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception { return (Math.sqrt(5)+1)/2; }

    @Override
    public int arność() { return 0; }
}
