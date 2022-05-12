public class CmdRequest extends RecordedCommand{
    private Item i;
    private Member m;
    @Override
    public void execute(String[] cmdParts) 
    {
        try {
            if(cmdParts.length<3)
                throw new ExInsuffArgs();
            Club c = Club.getInstance();
            m = c.getMember(cmdParts[1]);
            i = c.getBorrowedItem(cmdParts[2]);
            
            ItemStatus s = i.getStatus();
            if(s instanceof ItemStatusOnhold && ((ItemStatusOnhold) s).getOnholdMember()==m)
                throw new ExAvItemRequested();
            else
            {
                m.checkBorrrowed(i);
                i.addRequestingMember(m);
                m.incRequested();
                addUndoCommand(this);
                 clearRedoList();
                System.out.println("Done. This request is no. "+i.getRequestNumber()
                 +" in the queue.");
            }
        } catch (ExMemberNotFound | ExSameMemberRequest | ExReqQuotaExceeded |
         ExSameMemberBorrowed | ExAvItemRequested | ExItemUnavailable | ExInsuffArgs e) {
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public void undoMe() {
        i.removeRequestingMember(m);
        m.decRequested();
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        try {
            i.addRequestingMember(m);
            addUndoCommand(this);
            m.incRequested();
        } catch (ExReqQuotaExceeded | ExSameMemberRequest e) {
            e.printStackTrace();
        }
    }

}
