public class CmdArrive extends RecordedCommand{

    Item i;
    @Override
    public void execute(String[] cmdParts) 
    {
        try {
            if(cmdParts.length<3)
                throw new ExInsuffArgs();
            Club c = Club.getInstance();
            i = new Item(cmdParts[1], cmdParts[2]);
            c.addItem(i);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done."); 
        } catch (ExItemIdTaken e) {
            System.out.println(e.getMessage()+" "+e.getPresentiID()+" "+e.getPresentiName());
        } catch (ExInsuffArgs e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        Club c = Club.getInstance();
        c.removeItem(i);    
        addRedoCommand(this);    
    }

    @Override
    public void redoMe() {
        Club c = Club.getInstance();
        try {
            c.addItem(i);
        } catch (ExItemIdTaken e) {
            e.getMessage();
        }
        addUndoCommand(this);
    }

}
