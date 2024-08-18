import java.util.Iterator;

public class HashTable {

    private DSALinkedList[] hashArray;
    private int count;


    public int getCount(){
        return count;
    }

    public int getSize(){
        return hashArray.length;
    }
    

    /**
     * @param capacity
     * Constructor with parameters
     */
    public HashTable(int capacity) {
        int actualSize = nextPrime(capacity);
        hashArray = new DSALinkedList[actualSize];
        for(int i=0; i<actualSize; i++){
            hashArray[i] = new DSALinkedList();
        }
        this.count = 0;
    }


    /**
     * @param key
     * @return
     * takes a key and converts it into an idx within the hashTable
     */
    private int hash(String key){
        int hashIdx = 0;
        for (int ii = 0; ii < key.length(); ii++) {
        hashIdx = (33 * hashIdx) + key.charAt(ii);
        
        }
        return Math.abs(hashIdx % hashArray.length);
    }



    /**
     * @param key
     * @param value
     * inserts object value at the start of the linkedlist at the corresponding idx of the key
     */
    public void put(String key, Object value){
        int hashIdx;
    
        hashIdx = hash(key);

        DSALinkedList list = hashArray[hashIdx];

        if(list.findNode(key))
        {
            System.out.println("Key already exists");
        }
        else
        {
            list.insertFirst(value);
            count++;
        } 
    }


    /**
     * @param key
     * @return
     * Retrieves the object stored in the linked list idx in hashTable corresponding to the key 
     */
    public Object get(String key){
       
        int hashIdx, origIdx;
        boolean found = false, giveUp = false;
    
        hashIdx = hash(key);
        origIdx = hashIdx;
        Object retValue = null;

        while (!found && !giveUp) {
            // Retrieve the linked list at the specified index
            DSALinkedList linkedList = hashArray[hashIdx];

            // Check if the key exists in the linked list and get the associated value
            if (linkedList.findNode(key)) {
                retValue = linkedList.getNode(key);
                found = true;
            } else {
                hashIdx = (hashIdx + stepHash(key)) % hashArray.length; // Move to the next index

                if (hashIdx == origIdx) {
                giveUp = true;
                }
            }
        }
    return retValue;
    }

    
    /**
     * @param key
     * removes object value at the corresponding idx of the key
     */
    public void remove(String name, String key)
    {
        boolean removed = false;
        int hashIdx = hash(key); // Calculate the index based on the key
        Iterator<Object> iter = hashArray[hashIdx].iterator();

        while (iter.hasNext() && !removed) {
            Shop currShop = (Shop) iter.next();
            if (name.equals(currShop.getName())) {
                // Found a shop with the matching name in the specified key
                hashArray[hashIdx].remove(currShop);
                count--; // Decrement the count of elements in the hash table
                removed = true;
                // You may need to handle reinserting displaced items or resizing the table here if necessary
            return; // Exit the loop after removing the shop
            }
        }
        if(!removed){
        // If the loop completes and no shop is found with the given name in the key, you can print a message or handle it accordingly.
        System.out.println("Shop with name: " + name + " doesn't exist in the specified key: " + key);
        }
    }

/* 
    
    private void reinsertDisplacedItems(int originalIndex) {
        int currentIndex = (originalIndex + 1) % hashArray.length;

        // Retrieve the linked list at the original index
        DSALinkedList originalList = hashArray[originalIndex];
    
        while (currentIndex != originalIndex) {
            // Retrieve the linked list at the current index
            DSALinkedList currentList = hashArray[currentIndex];
    
            if (currentList != null) {
                // iterate through the original list
                Iterator<Object> iterator = originalList.iterator();
    
                while (iterator.hasNext()) {
                    Object value = iterator.next();
                    String key = (String) value;
                    Object secondaryValue = originalList.getNode(key);
    
                    currentList.insertLast(secondaryValue);
                }
            }
    
            currentIndex = (currentIndex + 1) % hashArray.length;
        }
    }*/


    /**
     * @param key
     * @return
     * Returns the linkedlist at the idx in the hasTable corresponding to key
     */
    public DSALinkedList getIdxList(String key){
        int hashIdx;
        DSALinkedList list = null;

        hashIdx = hash(key);
        list = hashArray[hashIdx];
        
        return list;
    }


    /**
     * @return
     * Return the load factor of the hashTable
     */
    public double getLoadFactor(){
        double lf;

        lf = (double) count / hashArray.length;

        return lf;
    }


    /**
     * @param key
     * @return
     * determines number of idexes to move in hashTable when collision occurs or searching for keys
     */
    private int stepHash(String key) {
        int hashStep;

        hashStep = 5 - (hash(key) % 5);
        
        return hashStep;
    }


    /**
     * @param num
     * @return
     * Calculates the next closest prime number
     */
    private int nextPrime(int num) {
       
            while (true) {
                num++; // Increment num to check the next number
        
                if (num <= 1) {
                    continue; // Skip numbers less than or equal to 1
                }
        
                if (num <= 3) {
                    return num; // 2 and 3 are prime
                }
        
                boolean isPrime = true;
        
                if (num % 2 == 0 || num % 3 == 0) {
                    isPrime = false;
                }
        
                for (int i = 5; i * i <= num; i += 6) {
                    if (num % i == 0 || num % (i + 2) == 0) {
                        isPrime = false;
                        break;
                    }
                }
        
                if (isPrime) {
                    return num; // If it's prime, return it
                }
            }
        }
    
}
