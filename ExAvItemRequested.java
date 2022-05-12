

public class ExAvItemRequested extends Exception {
    public ExAvItemRequested()
    {
        super("The item is currently available.");
    }
    public ExAvItemRequested(String msg)
    {
        super(msg);
    }
}
