

public class ExSameMemberRequest extends Exception {
    public ExSameMemberRequest()
    {
        super("The same member has already requested the item.");
    }
    public ExSameMemberRequest(String msg)
    {
        super(msg);
    }
}
