

public class ExSameMemberBorrowed extends Exception {
    public ExSameMemberBorrowed()
    {
        super("The item is already borrowed by the same member.");
    }
    public ExSameMemberBorrowed(String msg)
    {
        super(msg);
    }
}
