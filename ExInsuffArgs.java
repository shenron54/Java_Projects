public class ExInsuffArgs extends Exception{
    public ExInsuffArgs()
    {
        super("Insufficient command arguments.");
    }    
    public ExInsuffArgs(String msg)
    {
        super(msg);
    }
}
