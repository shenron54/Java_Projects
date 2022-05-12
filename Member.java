import java.util.ArrayList;

public class Member implements Comparable<Member>
{
    private String id;
    private String name;
    private int borrowed, requested;
    private Day joinDate;
    private ArrayList<Item> borrowedItems = new ArrayList<>();
    public Member(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.joinDate = SystemDate.getInstance().clone();
    }

    @Override
    public String toString() 
    {
        //Learn: "-" means left-aligned
        return String.format("%-5s%-9s%11s%7d%13d", id, name, joinDate, borrowed, requested);
    }

    public static String getListingHeader() {
        return String.format("%-5s%-9s%11s%11s%13s", "ID", "Name", "Join Date ", "#Borrowed", "#Requested");
    }

    @Override
    public int compareTo(Member another) {
        if(this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0) return 1;
        else return -1;
    }

    public String getmID() {return id;}
    public String getmName() {return name;}

	public void addBorrowedItem(Item i) throws ExQuotaExceeded
    {
        if(borrowedItems.size()==6)
            throw new ExQuotaExceeded();
        else 
            borrowedItems.add(i);
	}

    public void removeBorrowedItem(Item i) 
    {
        borrowedItems.remove(i);
    }

    public Item getBorrowedItem(String iID) throws ExItemNotBorrowed 
    {
        for(Item i: borrowedItems)
            if(i.getItemID().equals(iID))
                return i;
        throw new ExItemNotBorrowed();
    }

    public void incBorrowed(){this.borrowed++;}
    public void incRequested(){this.requested++;}

    public void checkBorrrowed(Item reqI) throws ExSameMemberBorrowed {
        for(Item i: borrowedItems)
            if(i==reqI)
                throw new ExSameMemberBorrowed();
    }

    public void decBorrowed() {this.borrowed--;}
    public void decRequested() {this.requested--;}

    public int getRequestNumber() {
        return requested;
    }
    

}
