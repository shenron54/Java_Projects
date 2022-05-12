public class CmdCheckin extends RecordedCommand{
    private Member m, queuedMember;
    private Item i;
    private int pass=0;
    @Override
    public void execute(String[] cmdParts) {
        try {
            if(cmdParts.length<3)
                throw new ExInsuffArgs();
            Club c = Club.getInstance();
            m = c.getMember(cmdParts[1]);
            i = m.getBorrowedItem(cmdParts[2]);
            m.removeBorrowedItem(i);
            m.decBorrowed();
            if(i.getRequestNumber()>0)
            {
                pass=1;
                queuedMember = i.popRequestingMember();
                i.setStatus("Onhold", queuedMember);
                queuedMember.decRequested();
                ItemStatus status = i.getStatus();
                System.out.println("Item ["+i.getItemID()+" "+i.getItemName()
                +"] is ready for pick up by ["+queuedMember.getmID()+" "+queuedMember.getmName()+
                "]. On hold due on "+ ((ItemStatusOnhold) status).getOnholdDay()+".");
            }
            else
                i.setStatus("Available", m);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");

        } catch(ExItemNotBorrowed | ExMemberNotFound | ExInsuffArgs e) {
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public void undoMe() {
        try {
            m.addBorrowedItem(i);
            m.incBorrowed();
            i.setStatus("Borrowed", m);
            if(pass==1)
            {
                i.addRequestingMember(queuedMember, 0);
                queuedMember.incRequested();
                System.out.print("Sorry. "+queuedMember.getmID()+" "+
                queuedMember.getmName()+" please ignore the pick up notice for "+
                i.getItemID()+" "+i.getItemName()+".");
            }
            addRedoCommand(this);
        } catch (ExQuotaExceeded e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void redoMe() {
        m.removeBorrowedItem(i);
        m.decBorrowed();
        if(pass==1)
        {
            queuedMember = i.popRequestingMember();
            i.setStatus("Onhold", queuedMember);
            queuedMember.decRequested();
            ItemStatus status = i.getStatus();
            System.out.println("Item ["+i.getItemID()+" "+i.getItemName()
            +"] is ready for pick up by ["+queuedMember.getmID()+" "+queuedMember.getmName()+
            "]. On hold due on "+ ((ItemStatusOnhold) status).getOnholdDay()+".");    
        }
        else    
            i.setStatus("Available", m);
        addUndoCommand(this);
    }
    
}
