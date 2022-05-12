public class CmdCheckout extends RecordedCommand{
    private Item i;
    private Member m;
    private int pass;
    @Override
    public void execute(String[] cmdParts) {
        try {
            if(cmdParts.length<3)
                throw new ExInsuffArgs();
            Club c = Club.getInstance();
            m = c.getMember(cmdParts[1]);
            i = c.getItem(cmdParts[2]);
            ItemStatus s = i.getStatus();
            if(s instanceof ItemStatusAvailable 
            | (s instanceof ItemStatusOnhold && ((ItemStatusOnhold) s).getOnholdMember()==m))
            {
                if((s instanceof ItemStatusOnhold && ((ItemStatusOnhold) s).getOnholdMember()==m))
                    pass=1;
                m.addBorrowedItem(i);
                m.incBorrowed();
                i.setStatus("Borrowed", m);
                addUndoCommand(this);
                clearRedoList();
    
                System.out.println("Done.");                
            }
            else 
                throw new ExItemUnavailable("Item not available.");
        } catch (ExItemUnavailable | ExMemberNotFound | ExQuotaExceeded | ExInsuffArgs e) {
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public void undoMe() {
        m.removeBorrowedItem(i);
        m.decBorrowed();
        if(pass==1)
            i.setStatus("Onhold", m);
        else
            i.setStatus("Available", null);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        try {
            m.addBorrowedItem(i);
            m.incBorrowed();
            i.setStatus("Borrowed", m);
            addUndoCommand(this);
        }
        catch (ExQuotaExceeded e) {
            e.printStackTrace();
        }
    }
}
