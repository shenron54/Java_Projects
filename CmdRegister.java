public class CmdRegister extends RecordedCommand {
    private Member m;

    @Override
    public void execute(String[] cmdParts)  {
        try {
            if(cmdParts.length<3)
                throw new ExInsuffArgs();
            Club c = Club.getInstance();
            String memberID = cmdParts[1];
            String memberName = cmdParts[2];
            m = new Member(memberID, memberName);
            c.addMember(m);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExMemberIdTaken e) {
            System.out.println(e.getMessage()+" "+e.getPresentmID()+" "+e.getPresentmName());
        } catch (ExInsuffArgs e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() 
    {
        Club c = Club.getInstance();
        c.removeMember(m);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() 
    {
        Club c = Club.getInstance();
        try {
            c.addMember(m);
        } catch (ExMemberIdTaken e) {
            
        }
        addUndoCommand(this);
    }
}
