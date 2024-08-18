public class HashEntry {
    private String key;
    private Object value;
    private int state;


    /**
     * Default constructor
     */
    public HashEntry(){
        this.key = null;
        this.value = null;
        this.state = 0;
    }

    /**
     * @param key
     * @param value
     * Constructor with parameters
     */
    public HashEntry(String key, Object value){
        this.key = key;
        this.value = value;
        this.state = 1;
    }

    public String getKey(){
        return key;
    }

    public Object getValue(){
        return value;
    }

    public int getState(){
        return state;
    }

    public void setKey(String key){
        this.key = key;
    }

    public void setValues(Object value){
        this.value = value;
    }

    public void setState(int state){
        this.state = state;
    }
}
