

public class ExItemNotBorrowed extends Exception{
    public ExItemNotBorrowed()
    {
        super("The item is not borrowed by this member.");
    }
    public ExItemNotBorrowed(String msg)
    {
        super(msg);
    }
}
