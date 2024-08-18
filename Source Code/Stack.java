public class Stack {
    
    private DSALinkedList stackList;

    /**
     * Default constructor
     */
    public Stack() {
        stackList = new DSALinkedList();
    }

    /**
     * @param value
     * inserts value at top of the stack
     */
    public void push(Object value) {
        stackList.insertFirst(value);
    }

    /**
     * @return
     * Removes value from top of the stack
     */
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackList.removeFirst();
    }

    /**
     * @return
     * Returns value at top of the stack
     */
    public Object top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackList.peekFirst();
    }

    /**
     * @return
     * Checks to see if the stack is empty and returns true if so and false otherwise
     */
    public boolean isEmpty() {
        return stackList.isEmpty();
    }
}

