public class ExItemNotRequested extends Exception {
    public ExItemNotRequested()
    {
        super("Request record is not found.");
    }
    public ExItemNotRequested(String msg)
    {
        super(msg);
    }
}
