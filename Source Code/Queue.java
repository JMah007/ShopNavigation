public class Queue 
{
    private DSALinkedList que;
    private int length;

    

    Queue(){
        que = new DSALinkedList();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Boolean isEmpty(){
        return que.isEmpty();
    }


    public Object peek(){
        if(isEmpty()){
            throw new IllegalStateException("\nQueue is Empty\n");
        }else{
           return que.peekFirst();
        }
    }


    public void enqueue(Object data){
        que.insertLast(data);
    }

    public Object dequeue()throws IllegalStateException{
        if(isEmpty()){
            throw new IllegalStateException("\nQueue is Empty\n");
        }
        que.peekFirst();
        return que.removeFirst();
    }


}
