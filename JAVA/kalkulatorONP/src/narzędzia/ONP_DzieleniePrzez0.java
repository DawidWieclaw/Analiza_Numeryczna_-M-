package narzędzia;
/**
 * <p>Klasa reprezentująca mdzielenie przez zero/p>
 */
public class ONP_DzieleniePrzez0 extends WyjątekONP
{
    public ONP_DzieleniePrzez0() { super(); }

    @Override
    public String getMessage() { return "dzielenie przez 0"; }
}
