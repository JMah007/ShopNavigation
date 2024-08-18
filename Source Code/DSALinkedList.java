import java.io.Serializable;
import java.util.Iterator;

public class DSALinkedList implements Serializable, Iterable<Object>
{

    //Start of ListNode class
    private class ListNode
    {

    private Object value;
    private ListNode next;
    private ListNode prev;

    
    /**
     * @param inValue
     * Constructor with parameters
     */
    public ListNode(Object inValue){
        value = inValue;
        next = null;
        prev = null;
    }

     
    public Object getValue(){
        return value;
    }

    public ListNode getNext(){
        return next;
    }

    public void setNext(ListNode newNext){
        next = newNext;
    }   

    public ListNode getPrev(){
        return prev;
    }

    public void setPrev(ListNode newPrev){
        prev = newPrev;
    }   
    }
    //End of class ListNode

    
    private ListNode head = null;
    private ListNode tail = null;

    
    public ListNode getTail() {
        return tail;
    }

    public void setTail(ListNode tail) {
        this.tail = tail;
    }

    public ListNode getHead() {
        return head;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }


    /**
     * Default constructor
     */
    public DSALinkedList(){
        head = null;
        tail = null;
    }


    /**
     * @param newValue
     * inserts a node to the beginning of the list
     */
    public void insertFirst(Object newValue) 
    {
        ListNode newNd = new ListNode(newValue);

        if (isEmpty())  //if no other nodes
        {
            head = newNd;
            tail = newNd;
            head.setPrev(null);
            tail.setNext(null);
        } 
        else 
        {
            head.setPrev(newNd);//head's previous points to newNd
            newNd.setNext(head); //newNd's next points to original head  
            head = newNd; //newNd becomes the new head
            head.setPrev(null); //newNd's previous points to null
        }
    }


    /**
     * @param newValue
     * inserts a node to the end of the list
     */
    public void insertLast(Object newValue) 
    {
        ListNode newNd = new ListNode(newValue);

        if (isEmpty()) { //if no other nodes
            head = newNd;
            tail = newNd;
            head.setPrev(null);
            tail.setNext(null);
        } 
        else 
        {
            tail.setNext(newNd); //original tail's next points to newNd
            newNd.setPrev(tail); //newNd's previous points to original tail
            tail = newNd; //newNd becomes the new tail node
            tail.setNext(null); //newNd's next points to null
        }
    }


    /**
     * @return 
     * checks to see if list is empty by accessing the head node and returning true or false
     */
    public boolean isEmpty()
    {
        return head == null;
    }


    /**
     * @return
     * checks if list is empty, otherwise it returns the first node in the list
     */
    public Object peekFirst() 
    {
    if (isEmpty()) 
    {
        throw new IllegalStateException("The list is empty.");
    } 
    return head.getValue();
    }


    /**
     * @return
     * checks if list is empty, otherwise it returns the last node in the list
     */
    public Object peekLast() 
    {
    if (isEmpty()) 
    {
       throw new IllegalStateException("The list is empty.");
    }
    return tail.getValue();
    }


    /**
     * @return
     * removes the first node in the list if its not empty and returns that removed data
     */
    public Object removeFirst()
    {
        Object removedData;
        if(isEmpty())//list is empty
        {
        throw new IllegalStateException("The list is empty.");
        }
        else if(head.getNext() == null)//only one node in list
        {
            removedData = head.getValue();
            head = null;
        }
        else //Multiple nodes in list 
        { 
            ListNode currNd = head.getNext(); 
            currNd.setPrev(null);
            removedData = head.getValue();//store data of node being removed
            head.setNext(null);
            head = currNd;
        }
        return removedData;
    }


    /**
     * @param value
     * @return
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * takes value and looks for it in the list. If it exists then it removes the node with that value and returns it
     */
    public Object remove(Object value) throws IllegalStateException, IllegalArgumentException
    {
        Object removedData;
        
        if(isEmpty()){//list is empty
        throw new IllegalStateException("The list is empty.");
        }
        
        else{ //if nodes in the list
            ListNode currNd = getNode(value); 
            if(currNd == null){ // if cant find node
                throw new IllegalArgumentException();
            }
            else if(head.getNext() == null){ //if foun d node is the only node 
            removedData = head.getValue();
            head = null;
            }
            else if(currNd.getPrev() == null){ // if found node has no node on its left
                currNd = head.getNext(); 
                currNd.setPrev(null);
                removedData = head.getValue();
                head.setNext(null);
                head = currNd;
            }
            else if(currNd.getNext() == null){ // if found node has no node on right
                currNd = tail.getPrev();
                currNd.setNext(null);
                removedData = tail.getValue();
                tail.setNext(null);
                tail.setPrev(null);
                tail = currNd;
            }
            else{ //if node found has nodes on left and right of it 
            removedData = currNd.getValue();

            //linking node before node being removed to node after node being removed
            currNd.getPrev().setNext(currNd.getNext()); 
            currNd.getNext().setPrev(currNd.getPrev());

            currNd.setPrev(null);
            currNd.setNext(null);
            }
        }
        return removedData;
    }


    /**
     * @return
     * if nodes exist in the list then the last node is removed and is returned 
     */
    public Object removeLast() 
    {
        Object removedData;
    if (isEmpty()) {
        throw new IllegalStateException("The list is empty.");
    } else if (head.getNext() == null) { //checks if item is the only one in the list
        removedData = head.getValue(); // Obtain the node value for printing purposes 
        head = null; //removes the only element 
    } else{//Multiple nodes in list
        ListNode currNd = tail.getPrev();
        currNd.setNext(null);
        removedData = tail.getValue();
        tail.setNext(null);
        tail.setPrev(null);
        tail = currNd;
    }
    return removedData;
    }


    /**
     * @param value
     * @return
     * fined node in the list that has the value passed in as parameter. If found
     * then return true otherwise, false
     */
    public boolean findNode(Object value) 
    {
        boolean foundNode = false;
        ListNode current = head;

        while (current != null && foundNode == false) {
            if (current.getValue().equals(value)) {
                foundNode = true;
            }
            else{
                current = current.next;
            }
        }
        return foundNode;
    }


    /**
     * @param value
     * @return
     * get node in the list and return it
     */
    public ListNode getNode(Object value)
    {
        boolean foundNode = false;
        ListNode current = head;

        while (current != null && foundNode == false) {
            if (current.getValue().equals(value)) { // if found
                foundNode = true;
            }
            else{
                current = current.next;
            }
        }
        return current;
    }


    /**
     * @return
     * return the number of nodes in the list by iterating through the list
     */
    public int getNodeCount()
    {
        int count = 0;
        ListNode current = getHead();

        while (current != null) {
            count++;
            current = current.next;  
        }
        return count;   
    }


    /**
     * displays the value in each node of the list
     */
    public void display() 
    {
        if (isEmpty()) {
            System.out.println("The list is empty.");
        }
        else
        {
        ListNode current = head;
        while (current != null) 
        {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
        System.out.println();
        }
    }


    public MyLinkedListIterator iterator(){

        return new MyLinkedListIterator(this);
    }


    //Start of MyLinkedListIterator class
    public class MyLinkedListIterator implements Iterator<Object>{

        private ListNode iterNext;
        
        public MyLinkedListIterator(DSALinkedList dsaLinkedList)
        {
            iterNext = dsaLinkedList.head;
        }

        // Iterator interface implementation
        @Override
        public boolean hasNext() {
            return (iterNext != null);
        }

        @Override
        public Object next() {
            Object value = null;

            if(iterNext == null){
                value = null;
            }
            else{
                try{
                    value = iterNext.getValue();
                    iterNext = iterNext.getNext();

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            return value;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("Not supported");
        }
        }
}