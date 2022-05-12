public interface Command 
{
    void execute(String[] cmdParts) throws ExMemberIdTaken, ExItemIdTaken, 
    ExItemUnavailable, ExItemNotBorrowed, ExInsuffArgs;
}
