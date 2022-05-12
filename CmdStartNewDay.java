public class CmdStartNewDay extends RecordedCommand{
    private String sDay1;
    private String sDay2;
    public void execute(String[] cmdParts)  
    {
        if(cmdParts.length<2)
            try {
                throw new ExInsuffArgs();
            } catch (ExInsuffArgs e) {
                System.out.println(e.getMessage());
            }
        SystemDate sysd=SystemDate.getInstance();
        sDay1 = sysd.toString();
        sDay2=cmdParts[1];
        sysd.set(cmdParts[1]);
        Club c = Club.getInstance();
        c.checkOnholdItems(sysd);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() 
    {
        SystemDate sysd=SystemDate.getInstance();
        sysd.set(sDay1);
        addRedoCommand(this);
        
    }

    @Override
    public void redoMe() 
    {
        SystemDate sysd=SystemDate.getInstance();
        sysd.set(sDay2);
        addUndoCommand(this);        
    }
}
