public class ExReqQuotaExceeded extends Exception
{
    public ExReqQuotaExceeded()
    {
        super("Item request quota exceeded.");
    }
    public ExReqQuotaExceeded(String msg)
    {
        super(msg);
    }
}
