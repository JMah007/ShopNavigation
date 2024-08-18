public class Shop {
    private int num;
    private String name;
    private String category;
    private String location;
    private int rating;
    private boolean visited;
    private DSALinkedList pathways;
    
    /**
     * Default constructor
     */
    public Shop() {
        this.num = 0;
        this.name = null;
        this.category = null;
        this.location = null;
        this.rating = 0;
    }

    
    /**
     * @param num
     * @param name
     * @param category
     * @param location
     * @param rating
     * Constructor with parameters
     */
    public Shop(int num, String name, String category, String location, int rating) {
        this.num = num;
        this.name = name;
        this.category = category;
        this.location = location;
        this.rating = rating;
        this.visited = false;
        this.pathways = new DSALinkedList();
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public boolean getVisited() {
        return visited;
    }

    public void clearVisited(){
        this.visited = false;
    }

    public void setVisited() {
        this.visited = true;
    }

    public DSALinkedList getLinks() {
        return pathways;
    }

    public void setLinks(Shop adjacentVertex) {
        this.pathways.insertLast(adjacentVertex);
    }


    /**
     * @param dstNode
     * Adds Shop dst to end of pathways 
     */
    public void addPathway(Shop dstNode){ 
        pathways.insertLast(dstNode);
    }

    /**
     * @param dstNode
     * Removes Shop dst from linkedList
     */
    public void removePathway(Shop dstNode)
    {
        pathways.remove(dstNode);
    }

    

        
}


