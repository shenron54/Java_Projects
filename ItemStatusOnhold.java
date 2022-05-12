public class ItemStatusOnhold implements ItemStatus
{
    private Member onholdMember;
    private Day onholdDay;
    private String sDay;
    private String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
    public ItemStatusOnhold(Member m)
    {
        onholdMember = m;
        SystemDate sysd = SystemDate.getInstance();
        sDay = sysd.toString();
        String[] sDayParts = sDay.split("-");
        int year = Integer.parseInt(sDayParts[2]); //Apply Integer.parseInt for sDayParts[2]; 
        int day = Integer.parseInt(sDayParts[0])+3; 
        int month = MonthNames.indexOf(sDayParts[1])/3+1;
        final String[] MonthNames = {
            "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"};
        sDay = day+"-"+ MonthNames[month-1] + "-"+ year;
        onholdDay = new Day(sDay);
        
    }
    public Day getOnholdDay(){return onholdDay;}
    public Member getOnholdMember(){return onholdMember;}
    public String getStatus(Item i)
    {
        if(i.getRequestNumber()>0)
            return "On holdshelf for "+ onholdMember.getmID()+" "
            +onholdMember.getmName() +" until "+ onholdDay +
             " + "+ i.getRequestNumber()+" request(s): "+ i.printReqMembers();
        else
            return "On holdshelf for "+ onholdMember.getmID()+" "
            +onholdMember.getmName() +" until "+ onholdDay;
    }

}