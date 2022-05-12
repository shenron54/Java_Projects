

public class ExItemUnavailable extends Exception{
    public ExItemUnavailable()
    {
        super("Item not found.");
    }
    public ExItemUnavailable(String msg)
    {
        super(msg);
    }
}
