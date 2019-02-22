package narzędzia;
/**
 * <p>błąd kiedy pusty stos</p>
 */
public class ONP_PustyStos extends WyjątekONP
{
    public ONP_PustyStos(){super();}

    @Override
    public String getLocalizedMessage() { return "Pusty stos"; }
}
