import java.util.ArrayList;
import java.util.Collections;

public class Club 
{
    private ArrayList<Member> allMembers;
    private ArrayList<Item> allItems;
    private static Club instance = new Club();

	private Club() 
    { 
        allMembers = new ArrayList<>();
        allItems = new ArrayList<>();
    }

	public static Club getInstance() { return instance;}

	public void addMember(Member m) throws ExMemberIdTaken
    {
        for(Member a: allMembers)
            if(a.getmID().equals(m.getmID()))
                throw new ExMemberIdTaken("Member ID already in use:", a.getmID(), a.getmName());
		allMembers.add(m);
		Collections.sort(allMembers);
	}

    public void addItem(Item i) throws ExItemIdTaken {
        for(Item a: allItems)
            if(a.getItemID().equals(i.getItemID()))
                throw new ExItemIdTaken("Item ID already in use:", a.getItemID(), a.getItemName());
        allItems.add(i);
        Collections.sort(allItems);
    }
	
	public void listClubMembers() 
    {
		System.out.println(Member.getListingHeader()); // Member
		for (Member m: allMembers)
			System.out.println(m); // m or m.toString()
	}

    public void listItems() {
        System.out.println(Item.getListingHeader());
        for(Item i: allItems)
            System.out.println(i);
    }

    public void removeMember(Member m) {allMembers.remove(m);}

    public void removeItem(Item i) {allItems.remove(i);}

    public Item getItem(String item) throws ExItemUnavailable
    {
        for(Item i: allItems)
            if(i.getItemID().equals(item))
                if(i.getStatus() instanceof ItemStatusBorrowed)
                    throw new ExItemUnavailable("Item not available.");
                else 
                    return i;
        throw new ExItemUnavailable("Item not found.");
        
    }

    public Member getMember(String mID) throws ExMemberNotFound
    {
        for(Member m: allMembers)
            if(m.getmID().equals(mID))
                return m;
        throw new ExMemberNotFound();
    }

    public Item getBorrowedItem(String iID) throws ExAvItemRequested, ExItemUnavailable  {
        int flag=0;
        for(Item i: allItems)
            if(i.getItemID().equals(iID))
            {
                flag = 1;
                if(i.getStatus() instanceof ItemStatusBorrowed 
                |i.getStatus() instanceof ItemStatusOnhold)
                    return i;
            }
        if(flag==0)
            throw new ExItemUnavailable();
        throw new ExAvItemRequested();
    }

    public Item getRequestedItem(String iID) throws ExItemUnavailable {
        for(Item i: allItems)
            if(i.getItemID().equals(iID))
                return i;
        throw new ExItemUnavailable("Item not found.");
    }

    public void checkOnholdItems(SystemDate sysd) 
    {
        for(Item i: allItems)
        {
            if(i.getStatus() instanceof ItemStatusOnhold)
            {
                ItemStatusOnhold s = (ItemStatusOnhold) i.getStatus();
                int onholdDay = s.getOnholdDay().getDay();
                int currentDay = sysd.getDay();
                if(currentDay-onholdDay>=1)
                {
                    System.out.println("On hold period is over for "+
                    i.getItemID()+" "+i.getItemName()+".");
                    if(i.getRequestNumber()>0)
                    {
                        Member queuedMember = i.popRequestingMember();
                        i.setStatus("Onhold", queuedMember);
                        queuedMember.decRequested();
                        ItemStatus status = i.getStatus();
                            
                        System.out.println("Item ["+i.getItemID()+" "+i.getItemName()
                        +"] is ready for pick up by ["+queuedMember.getmID()+" "
                        +queuedMember.getmName()+"]. On hold due on "+
                        ((ItemStatusOnhold) status).getOnholdDay()+".");
                            
                    }
                    else 
                        i.setStatus("Available", null);  
                } 
            }
        }
    }
}

