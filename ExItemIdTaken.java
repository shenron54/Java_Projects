public class ExItemIdTaken extends Exception
{
    private String presentiID, presentiName;

    public String getPresentiID() {return presentiID;}
    public String getPresentiName() {return presentiName;}

    public ExItemIdTaken()
    {
        super("Item ID already in use:");
    }
    public ExItemIdTaken(String msg)
    {
        super(msg);
    }
    public ExItemIdTaken(String msg, String iID, String iName)
    {
        super(msg);
        presentiID = iID;
        presentiName = iName;
    }
}
