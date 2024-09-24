//TODO: Add your code here
public enum ChadCmd {
    list, mark, unmark,delete;
    
    public static boolean contains(String tocheck){
        //Enum.GetNames(typeof(ChadCmd)).Contains(tocheck)
        for (ChadCmd c : ChadCmd.values()) {
            if (c.name().equals(tocheck)) {
                return true;
            }
        }
    
        return false;
    }
}