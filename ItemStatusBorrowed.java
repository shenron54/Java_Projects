public class ItemStatusBorrowed implements ItemStatus
{
    private Member borrowingMember;
    private Day borrowDate;

    public ItemStatusBorrowed(Member m)
    {
        this.borrowingMember = m;
        this.borrowDate = SystemDate.getInstance().clone();
    }
    
    @Override
    public String getStatus(Item i) {
        if(i.getRequestNumber()>0)
            return "Borrowed by "+borrowingMember.getmID()+" "
            +borrowingMember.getmName()+" on "+ borrowDate + " + "
            +i.getRequestNumber()+" request(s): "+ i.printReqMembers();
        else 
            return "Borrowed by "+borrowingMember.getmID()+" "
            +borrowingMember.getmName()+" on "+ borrowDate;
    }
}
