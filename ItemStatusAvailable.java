public class ItemStatusAvailable implements ItemStatus
{
    @Override
    public String getStatus(Item i) {
        return "Available";
    }
    
}
