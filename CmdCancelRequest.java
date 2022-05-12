public class CmdCancelRequest extends RecordedCommand {
    private Item i;
    private Member m;
    private int index;
    @Override
    public void execute(String[] cmdParts) {
        try{
            if(cmdParts.length<3)
                throw new ExInsuffArgs();
            Club c = Club.getInstance();
            m = c.getMember(cmdParts[1]);
            i = c.getRequestedItem(cmdParts[2]);
            i.checkRequested(m);
            index = i.getIndexof(m);
            i.removeRequestingMember(m);
            m.decRequested();
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch(ExMemberNotFound | ExItemNotRequested | ExItemUnavailable | ExInsuffArgs e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        i.addRequestingMember(m, index);
        m.incRequested();
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        i.removeRequestingMember(index);
        m.decRequested();
        addUndoCommand(this);
    }
    
    
}
