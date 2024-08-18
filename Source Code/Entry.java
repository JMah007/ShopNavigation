public class Entry {

    private int priority;
    private Object value;


    /**
     * Default constructor 
     */
    public Entry() {
        this.priority = 0;
        this.value = null;
    }


    /**
     * @param inPriority
     * @param inValue
     * Constructor with parameters
     */
    public Entry(int inPriority, Object inValue) {
        this.priority = inPriority;
        this.value = inValue;
    }
    

    public int getPriority() {
        return priority;
    }

    public void setPriority(int inPriority) {
        this.priority = inPriority;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object inValue) {
        this.value = inValue;
    }
    
}
