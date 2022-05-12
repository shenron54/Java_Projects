

public class ExQuotaExceeded extends Exception{
    public ExQuotaExceeded()
    {
        super("Loan quota exceeded.");
    }
    public ExQuotaExceeded(String msg)
    {
        super(msg);
    }
}
