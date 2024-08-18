import java.util.*;

public class ShopGraph 
{
    private DSALinkedList shopList;
    private HashTable hashTable;


    
    /**
     * Default constructor
     */
    public ShopGraph()
    {
        this.shopList = new DSALinkedList();
        this.hashTable = new HashTable(10);
        //this.edgesList = new DSALinkedList();
    }


    public DSALinkedList getAdjacent(String label)
    {
      return shopList;
    }



    /**
     * @param num
     * @param name
     * @param category
     * @param location
     * @param rating
     * adds shop to a end of a linkedList with passed parameters as attributes
     */
    public void addShop(int num, String name, String category, String location, int rating)
    {
        if(this.hasShop(name))// if shop exists
        { 
            System.out.println("\nCannot add as that shop already exists"); 
        }
        else
        {
            Shop newShop = new Shop(num, name, category, location, rating);
            shopList.insertLast(newShop);
            hashTable.put(category, newShop);
            System.out.println("Shop " + name + " added successfully");
        } 
    }


    /**
     * @param name
     * compares name with shops in the linkedlist to see if it exists and removes it if so
     */
    public void removeShop(String name)
    {
        Shop shop = null;
        if(this.hasShop(name))//if shop exists
        { 
            shop = getShop(name);
            System.out.println("Name of shop found is " + shop.getName());
            shopList.remove(shop);
            hashTable.remove(shop.getName(), shop.getCategory());
            System.out.println("Shop " + name + " removed successfully");
        }
        else
        {
            System.out.println("Shop with name: " + name + " doesnt exist");
        } 
    }

    
    /**
     * @param name
     * @param num
     * @param kb
     * finds shop in the linkedlist with the passed in name. If it exists then one of the shops 
     * attributes are changed according to number passed in and the user is asked for the new value
     */
    public void updateShop(String name, int num, Scanner kb)
    {
        String newName, newCategory, newLocation;
        int newRating;
        Shop shop = null;

        if(this.hasShop(name)) // Checks if shop exists
        {
            shop = this.getShop(name);

            switch(num)
            {
            case 1: 
            System.out.println("Enter the new shop name");
            newName = kb.nextLine();
            shop.setName(newName);
            System.out.println("Shop name succesfully changed to " + shop.getName());
            break;

            case 2: 
            System.out.println("Enter the new shop category");
            newCategory = kb.nextLine();
            shop.setCategory(newCategory);
            System.out.println("Shop name succesfully changed to " + shop.getCategory());
            break;

            case 3: 
            System.out.println("Enter the new shop location");
            newLocation = kb.nextLine();
            shop.setLocation(newLocation);
            System.out.println("Shop location successfully changed to " + shop.getLocation());
            break;

            case 4:
            System.out.println("Enter the new shop rating between 1-5");
            newRating = kb.nextInt();
            newRating = ShopGraph.valRating(newRating, kb);
            kb.nextLine(); // Collects nextLine character
            shop.setRating(newRating);
            System.out.println("shop rating successfuly changed to " + shop.getRating());
            break;

            }
        }
        else
        {
            System.out.println("Shop with name: " + name + " doesnt exist");
        }    
    }


    /**
     * @param choice
     * @param kb
     * @return
     * takes in a number representing a rating and checks to make sure its between 1 and 5.
     * Number returned will always be between 1 and 5
     */
    public static int valRating(int choice, Scanner kb)
    {
        boolean validInput = false;

        while (!validInput)
        {  
            if((choice < 1) || (choice > 5)) 
            {
                System.out.println("Invalid input please enter a number between 1 and 5: ");
                choice = kb.nextInt();        
            }
            else
            {
                validInput = true;
            }
        } 
    return choice;
    }

    
    /**
     * @param name
     * @return
     * finds shop in the linkedList that contains the name passed in as parameter. 
     * Returns true if it exists and false otherwise
     */
    public boolean hasShop(String name)
    {
        boolean hasShop = false;
        try
        {
            Iterator<Object> iter = shopList.iterator();
            while(iter.hasNext() && hasShop == false)
            {
                Shop currShop = (Shop)iter.next();
                if(name.equals(currShop.getName())){
                hasShop = true;    
                }
            }
        } catch(IllegalStateException e){
            System.out.println(e);  
        }
        return hasShop;
    }
    


    /**
     * @param name
     * @return
     * finds shop in the linkedList that contains the name passed in as parameter. 
     * Returns the shop if it exists otherwise returns null
     */
    public Shop getShop(String name)
    {
         Shop foundShop = null;
         Iterator<Object> iter = shopList.iterator();
         while(iter.hasNext() && foundShop == null)
         {
            Shop currShop = (Shop)iter.next();
            if(name.equals(currShop.getName()))
            {
            foundShop = currShop;
            }
         }
         return foundShop;
    }
  
      
    /**
     * @param label1
     * @param label2
     * checks to see if shops labbel1 and label2 exist.
     * adds a connection between shop with label 1 and label 2 both ways by adding each other to their own linkedlist
     */
    public void addPathway(String label1, String label2)
    {
        boolean hasSrcShop = false;
        boolean hasDstShop = false;
        Shop vertex1, vertex2;
      
        hasSrcShop = hasShop(label1);
        hasDstShop = hasShop(label2);

        if(!hasSrcShop && !hasDstShop)
        { 
            System.out.println("Error, shop " + label1 + " and shop " + label2 + " both dont exist");
        }
        else if(!hasSrcShop && hasDstShop)
        {
            System.out.println("Error, shop " + label1 + " does not exist");
        }
        else if(hasSrcShop && !hasDstShop)
        {
            System.out.println("Error, shop " + label2 + " does not exist");
        }
        else // both shops exist 
        { 
            if(isAdjacent(label1, label2)) // if edge already exists between them
            {
                System.out.println("Error, a pathway already exists between " + label1 + " and " + label2);
            }
            else
            {
                vertex1 = getShop(label1);
                vertex2 = getShop(label2);
                vertex1.addPathway(vertex2);
                vertex2.addPathway(vertex1);
            }
        }
    }


    /**
     * @param label1
     * @param label2
     * checks to see if shops labbel1 and label2 exist.
     * removes a connection between shop with label 1 and label 2 both ways by removing each other from their own linkedlist
     */
    public void removePathway(String label1, String label2)
    {
      boolean hasSrcShop = false;
      boolean hasDstShop = false;
      Shop vertex1, vertex2;

      hasSrcShop = hasShop(label1);
      hasDstShop = hasShop(label2);

      if(!hasSrcShop && !hasDstShop)
      { 
        System.out.println("Error, shop " + label1 + " and shop " + label2 + " both dont exist");
      }
      else if(!hasSrcShop && hasDstShop)
      {
        System.out.println("Error, shop " + label1 + " does not exist");
      }
      else if(hasSrcShop && !hasDstShop)
      {
        System.out.println("Error, shop " + label2 + " does not exist");
      }
      else // both shops exist 
      { 
        if(isAdjacent(label1, label2)) // if theres already an edge between them
        {
            vertex1 = getShop(label1);
            vertex2 = getShop(label2);
            vertex1.removePathway(vertex2);
            vertex2.removePathway(vertex1);
        }else
        {
            System.out.println("Error, theres no pathway between " + label1 + " and " + label2);
        }
      }
    }


    /**
     * @param startShopName
     * @param endShopName
     * Performs DFS and BFS and finds shortest path from starting and end shop
     */
    public void searchPathway(String startShopName, String endShopName)
    {

        boolean hasSrcShop = false;
        boolean hasDstShop = false;

        DSALinkedList pathDFS = null;
        DSALinkedList pathBFS = null;

        hasSrcShop = hasShop(startShopName);
        hasDstShop = hasShop(endShopName);

        if(!hasSrcShop && !hasDstShop)
        { 
            System.out.println("Error, shop " + startShopName + " and shop " + endShopName + " both dont exist");
        }
        else if(!hasSrcShop && hasDstShop)
        {
            System.out.println("Error, shop " + startShopName + " does not exist");
        }
        else if(hasSrcShop && !hasDstShop)
        {
            System.out.println("Error, shop " + endShopName + " does not exist");
        }
        else
        {
            pathDFS = this.depthFirstSearch(startShopName, endShopName);
            pathBFS = this.breadthFirstSearch(startShopName, endShopName);

            System.out.print("\nPath for DFS is: ");
            dispPathwayDFS(pathDFS);
            System.out.print("\nPath for BFS is: ");
            dispPathwayBFS(pathBFS);

            if(pathDFS.getNodeCount() > pathBFS.getNodeCount())
            {
                System.out.println("\nDFS path is shorter!");
            }
            else if(pathDFS.getNodeCount() > pathBFS.getNodeCount())
            {
                System.out.println("\nBFS path is shorter!");
            }
            else
            {
                System.out.println("\nThey are the same length");
            }
        }
    }


    /**
     * @param startShopName
     * @param endShopName
     * @return
     * Finds path from starting and end shop by DFS
     */
    public DSALinkedList depthFirstSearch(String startShopName, String endShopName) 
    {
        Shop v;
        Shop w;
        
        DSALinkedList path = new DSALinkedList();

        Stack stack = new Stack();
        Stack pathStack = new Stack();
        
        // Find the starting and ending shops in the shopList
        Shop startShop = getShop(startShopName);
        Shop endShop = getShop(endShopName);
    
        for (Object myVar : shopList) {
            Shop ver;
            ver = (Shop) myVar;
            ver.clearVisited();
        }
    
        v = startShop;
        v.setVisited();
        stack.push(v);
        pathStack.push(v);

        while (!stack.isEmpty()) 
        {
            w = getUnvisitedNeighbor(v);

            if (w == endShop) 
            {
                // reached the destination shop, so stop the traversal
                break;
            }

            if (w != null) 
            {
                w.setVisited();
                stack.push(w);
                pathStack.push(w);
                v = w;
            } else 
            {
                v = (Shop) stack.pop();
                pathStack.pop();
            }
        }

        while (!pathStack.isEmpty()) 
        {
            path.insertFirst(pathStack.pop());
        }

    return path;
    }


    /**
     * @param startShopName
     * @param endShopName
     * @return
     * Finds pathway from starting to end shop by BFS
     */
    public DSALinkedList breadthFirstSearch(String startShopName, String endShopName) 
    {
        Shop v;
        Shop w;
        
        DSALinkedList path = new DSALinkedList();

        Queue queue = new Queue();
        
        Queue pathQueue = new Queue();

        // Find the starting and ending shops in the shopList
        Shop startShop = getShop(startShopName);
        Shop endShop = getShop(endShopName);
    
        for (Object myVar : shopList) 
        {
            Shop ver;
            ver = (Shop) myVar;
            ver.clearVisited();
        }
    
        v = startShop;
        v.setVisited();
        queue.enqueue(v);
        pathQueue.enqueue(v);

        while (!queue.isEmpty()) 
        {
            w = getUnvisitedNeighbor(v);

            if (w == endShop) {
            // reached the destination shop, so stop the traversal
            break;
            }

            if (w != null) 
            {
                w.setVisited();
                queue.enqueue(w);
                pathQueue.enqueue(w);
                v = w;
            } else 
            {
                v = (Shop) queue.dequeue();
                pathQueue.dequeue();
            }
        }
        while (!pathQueue.isEmpty()) 
        {
            path.insertFirst(pathQueue.dequeue());
        }
    return path;
    }


    /**
     * @param v
     * @return
     * Gets every shop adjacent to the shop v passed in and returns it if it hasnt been visited yet
     */
    private Shop getUnvisitedNeighbor(Shop v) {
        Iterator<Object> iter = v.getLinks().iterator();
        while (iter.hasNext()) {
            Shop neighbor = (Shop) iter.next();
            if (!neighbor.getVisited()) {
            return neighbor;
            }
        }
    return null;
    }


    /**
     * @param pathway
     * Takes pathway calculated from Depth first search and displays it by iterating through
     */
    public static void dispPathwayDFS(DSALinkedList pathway)
    {
        Iterator<Object> iter = pathway.iterator();
        while(iter.hasNext())
        {
            Shop currShop = (Shop)iter.next();
            System.out.print(currShop.getName() + ", ");
        }
    }


    /**
     * @param pathway
     * takes pathway from Breadth first search and reverses it then displays is by iterating through
     */
    public static void dispPathwayBFS(DSALinkedList pathway)
    {
        Iterator<Object> iter = pathway.iterator();
        DSALinkedList reversedPath = new DSALinkedList(); // Create a new list to store the reversed path.

        while (iter.hasNext()) 
        {
            Shop currShop = (Shop) iter.next();
            reversedPath.insertFirst(currShop); // Insert the shop at the beginning of the reversed path.
        }

        // display the reversed path in the correct order.
        Iterator<Object> reversedIter = reversedPath.iterator();
        while (reversedIter.hasNext()) 
        {
            Shop shop = (Shop) reversedIter.next();
            System.out.print(shop.getName() + ", ");
        }
    }




    /**
     * @param category
     * Displays all the shops under a chosen category in descending order according to rating.
     * Shops number, name, catgegory, location and rating are all displayed
     */
    public void searchByCategory(String category){
        DSALinkedList list = null;
        Entry[] sortedArray;

        list = hashTable.getIdxList(category);
        sortedArray = this.sortByRating(list, category);
        
        if(sortedArray.length == 0){
            System.out.println("Error, there are no shops under " + category);
        }
        else{
            System.out.println("Shops under: " + category);
        
            for(int i=sortedArray.length-1; i>=0; i--) // Iterate through the Entry[] array list of shops and print their details
            {
                if((category.equals(((Shop) sortedArray[i].getValue()).getCategory()))){ //Only display shops from linkedlist under category
                System.out.println("\nShop Number: " + ((Shop) sortedArray[i].getValue()).getNum() +
                    "\nShop Name: " + ((Shop) sortedArray[i].getValue()).getName() +
                    "\nCategory: " + ((Shop) sortedArray[i].getValue()).getCategory() +
                    "\nLocation: " + ((Shop) sortedArray[i].getValue()).getLocation() +
                    "\nRating: " + ((Shop) sortedArray[i].getValue()).getRating());   
                }
            }
        }
    }


    /**
     * @param unsortedList
     * @param category
     * @return
     * linkedlist sometimes contains multiple categories so this will count number of shops 
     * that are under a certain category and return that number
     */
    public int getCategoryCountLinkedList(DSALinkedList unsortedList, String category){
        int ctr = 0;

        Iterator<Object> iter = unsortedList.iterator();
        while (iter.hasNext()) { //iterates through linkedlist 
            Shop currShop = (Shop) iter.next();
            if(category.equals(currShop.getCategory())){ // increases count only if shop is under the category
                ctr++;
            }
        }
        return ctr;
    }


    /**
     * @param unsortedList
     * @return
     * Puts all values of a linkedlist into an array of Entry where priority is the rating of the shop
     * and the value is of type Shop.
     * The array is then sorted according to rating in deescending order and returned
     */
    public Entry[] sortByRating(DSALinkedList unsortedList, String category)
    {
        int count = 0;
        int ctr = 0;

        count = getCategoryCountLinkedList(unsortedList, category); // calculate number of shops in linkedList under category 
        Entry[] unsortedArray = new Entry[count]; 

        Iterator<Object> iter = unsortedList.iterator();

        while (iter.hasNext()) { //iterates through linkedList storing only shops under the category
            Shop currShop = (Shop) iter.next();
            if(category.equals(currShop.getCategory())){ //store shops only from the same category in linkedlist 
                unsortedArray[ctr] = new Entry(currShop.getRating(), (Shop)currShop);
                ctr++;
            }
        }

        Heap.heapSorts(unsortedArray, unsortedArray.length); // sort the array containing shops in category by rating

        return unsortedArray;
    }



    /**
     * @param name
     * @param name2
     * @return
     * Checks to see if theres already a connection between shop name and name2.
     * Returns true if so and false otherwise
     */
    public boolean isAdjacent(String name, String name2)
    {
        boolean isAdjacent = false;
        DSALinkedList adjacentList = null;

        adjacentList = getAdjacentList(name);

        if(adjacentList.findNode(getShop(name2))){
            isAdjacent = true;
        }
        return isAdjacent;
    }


    /**
     * @param name
     * @return
     * Gets linkedlist of all shops that are connected to shop name 
     */
    public DSALinkedList getAdjacentList(String name)
    {
        return getShop(name).getLinks(); //get shop with name then return its linkedList
    } 
}
