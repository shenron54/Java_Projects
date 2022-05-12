public class SystemDate extends Day{

    private static SystemDate instance;

    private SystemDate(String sDay) {super(sDay);}
    
    public static void createTheInstance(String sDay){
        if(instance==null)
            instance = new SystemDate(sDay);
        else
            System.out.println("Cannot create more than one system date instance.");
    }

    public static SystemDate getInstance() {
        return instance;
    }
}
