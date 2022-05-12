

public class ExMemberIdTaken extends Exception 
{
    private String presentmID, presentName;
    public String getPresentmID(){return presentmID;}
    public String getPresentmName(){return presentName;}
    public ExMemberIdTaken()
    {
        super("Member ID already in use:");
    }
    public ExMemberIdTaken(String message)
    {
        super(message);
    }
    public ExMemberIdTaken(String message, String id, String name)
    {
        super(message);
        presentmID = id;
        presentName = name;
    }
}
