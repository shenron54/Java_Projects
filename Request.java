public class Request {
    private Item reqItem;
    private Member reqMember;
    public Request(Item i, Member m)
    {
        this.reqItem = i;
        this.reqMember = m;
    }
    public Member getReqMember() {
        return reqMember;
    }
    
    public Item getReqItem() {
        return reqItem;
    }

    
}
