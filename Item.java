import java.util.ArrayList;

public class Item implements Comparable<Item>{

    private String id;
    private String name;
    private Day arrDate;
    private ArrayList<Member> requestingMembers= new ArrayList<>();
    private ItemStatus status;

    public Item(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.status = new ItemStatusAvailable();
        this.arrDate = SystemDate.getInstance().clone();
    }

    public static String getListingHeader() 
    {
        return String.format("%-5s%-17s%11s   %s", "ID", "Name", "  Arrival  ", "Status");
    }

    @Override
    public String toString() 
    {
        //Learn: "-" means left-aligned

        return String.format("%-5s%-17s%11s   %s", id, name, 
        arrDate, status.getStatus(this));
    }

    @Override
    public int compareTo(Item another) 
    {
        if(this.id.equals(another.id)) return 0;
        else if(this.id.compareTo(another.id)>0) return 1;
        else return 0;        
    }

    //Accessor Methods
    public String getItemID() {return id;}

    public String getItemName() {return name;}

    public ItemStatus getStatus() {return this.status;}

    public void setStatus(String state, Member m) 
    {
        if(state.equals("Available"))
            this.status = new ItemStatusAvailable();
        else if (state.equals("Borrowed"))
            this.status = new ItemStatusBorrowed(m);
        else if (state.equals("Onhold"))
            this.status = new ItemStatusOnhold(m);
    }

    public void checkItemAvailability() throws ExItemUnavailable
    {
        if(this.getStatus() instanceof ItemStatusBorrowed)
            throw new ExItemUnavailable();
    }

	public void addRequestingMember(Member reqM) throws ExReqQuotaExceeded, ExSameMemberRequest{
        if(requestingMembers.contains(reqM))
            throw new ExSameMemberRequest();
        if(reqM.getRequestNumber()==3) 
            throw new ExReqQuotaExceeded();
        else 
            requestingMembers.add(reqM); 
	}


    public void removeRequestingMember(Member m) {
        requestingMembers.remove(m);
    }

    public int getRequestNumber()
    {
        return requestingMembers.size();
    }

    public String printReqMembers() {
        String ret = "";
        for(Member m: requestingMembers)
            ret+= " "+m.getmID();
        return ret;
    }

    public Member popRequestingMember() {
        Member reqMem = requestingMembers.get(0);
        requestingMembers.remove(0);
        return reqMem;
    }

    public void checkRequested(Member m) throws ExItemNotRequested {
        if(!(requestingMembers.contains(m)))
                throw new ExItemNotRequested();
    }

    public int getIndexof(Member m) {
        return requestingMembers.indexOf(m);
    }

    public void addRequestingMember(Member m, int index) {
        requestingMembers.add(index, m);
    }

    public void removeRequestingMember(int index) {
        requestingMembers.remove(index);
    }

}