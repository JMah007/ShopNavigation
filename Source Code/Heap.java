public class Heap {

    private Entry[] heap;
    private int count;
    public int length;


    /**
     * @param capacity
     * Constructor with parameters
     */
    public Heap(int capacity) {
        this.heap = new Entry[capacity];        
        this.count = 0;
    }

    public int getCount() {
        return count;
    }
    

    /**
     * @param inPriority
     * @param inValue
     * creates and adds an Entry to the heap and sorts it in the heap according to priority associated with that Entry
     */
    public void add(int inPriority, Object inValue){
        Entry newEntry = new Entry(inPriority, inValue);
        heap[count] = newEntry;
        
        //trickle up until a parent value equal to or larger is found
        trickleUp(count, heap.length);
        count++;  
    }


    /**
     * @return
     * removes the first Entry in the heap and rearranges heap
     */
    public Entry remove(){
        Entry temp = new Entry();

        temp = heap[0];
        heap[0] = heap[count-1];
        count--;
        trickleDown(0, count);
        

        return temp;

    }


    /**
     * @param idx
     * @param numItems
     * Rearranges node at idx in the heap up to its proper position 
     */
    public void trickleUp(int idx, int numItems){
        int parentIdx;
        Entry temp = new Entry();

        parentIdx = (idx-1)/2;

        while(idx > 0 && heap[idx].getPriority() > heap[parentIdx].getPriority()){
            temp = heap[parentIdx];
            heap[parentIdx] = heap[idx];
            heap[idx] = temp;
            idx = parentIdx;
            parentIdx = (idx-1)/2;
  
        }
    
    
    }


    /**
     * @param idx
     * @param numItems
     * Rearranges node at idx in the heap down to its proper position
     */
    public void trickleDown(int idx, int numItems){
        int lChildIdx, rChildIdx, largeIdx;
        boolean keepGoing = true;
        Entry temp;
        
        lChildIdx = idx * 2 + 1;
        rChildIdx = lChildIdx + 1;
        while(keepGoing && lChildIdx < numItems){ 
        keepGoing = false;
        largeIdx = lChildIdx;
            if(rChildIdx < numItems){ // is a right child
                if(heap[lChildIdx].getPriority() < heap[rChildIdx].getPriority()){
                    largeIdx = rChildIdx; //find largest child
                }
            }
            if(heap[largeIdx].getPriority() > heap[idx].getPriority()){

                temp = heap[largeIdx];
                heap[largeIdx] = heap[idx];
                heap[idx] = temp;

                keepGoing = true;
            }

            idx = largeIdx;
            lChildIdx = idx * 2 + 1;
            rChildIdx = lChildIdx + 1; 
        }
    }
    

    /**
     * @param arrUnsorted
     * @param numItems
     * Rearranges an unsorted array by putting it in a heap structure and sort according to priority 
     */
    public static void heapSorts(Entry[] arrUnsorted, int numItems){
        Heap heap = new Heap(numItems);

        
        for(int i=0; i<numItems; i++){ //puts items into heap
            heap.add(arrUnsorted[i].getPriority(), arrUnsorted[i].getValue());
        }
       
        for (int ii = numItems-1; ii>=0; ii--){ // 0th item is sorted
            
            arrUnsorted[ii] = heap.remove();
            
            
            //0th item is sorted in heap
            heap.trickleDown(0, ii);
        }
    }
        

    /**
     * Display the structure of the heap 
     */
    public void display(){
        for(int i=0; i<count/2; i++){
            System.out.println("Parent node is: " + heap[i].getPriority());

            if(((i * 2) + 1) < count){ // calculate index of left child of parent i and make sure its in bounds
                System.out.println("Left child node is: " + heap[(i * 2) + 1].getPriority());
            }
            if(((i * 2) + 2) < count){ // calculate index of right child of parent i and make sure its in bounds
                System.out.println("Right child node is: " + heap[(i * 2) + 2].getPriority());
            }

            System.out.println();

        }
    }
    
}
