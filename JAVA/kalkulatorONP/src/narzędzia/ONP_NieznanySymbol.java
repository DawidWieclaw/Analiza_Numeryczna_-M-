package narzędzia;
/**
 * <p>Klasa reprezentująca wyjatek od nieznanego symbolu</p>
 */
public class ONP_NieznanySymbol extends WyjątekONP
{
    public ONP_NieznanySymbol() { super(); }

    @Override
    public String getMessage() { return "nieznany symbol"; }
}
