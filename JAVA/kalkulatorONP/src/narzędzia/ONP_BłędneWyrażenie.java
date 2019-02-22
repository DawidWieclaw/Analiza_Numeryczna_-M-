package narzędzia;
/**
 * <p>Klasa reprezentująca błąd związany z błędnym wyrażeniem</p>
 */
public class ONP_BłędneWyrażenie extends WyjątekONP
{
    public ONP_BłędneWyrażenie() { super(); }

    @Override
    public String getMessage() { return "błędne wyrażenie"; }
}
