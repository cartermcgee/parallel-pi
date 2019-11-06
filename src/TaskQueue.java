import java.util.*;

public class TaskQueue {
    private List queue;

    /**
    * TaskQueue constructor. Initializes random linked list with all values 1-1000
    */
    public TaskQueue(){
	ArrayList<Integer> digitList = new ArrayList<Integer>();

	for(int i = 1; i <= 1000; i++){
	    digitList.add(new Integer(i));
	}

	java.util.Collections.shuffle(digitList);
	this.queue = new LinkedList(digitList);
    }

    /**
    * Returns and removes first element of the TaskQueue list.
    */
    public synchronized int getTask(){
	int value = (Integer) queue.get(0);
	queue.remove(0);
	return value;
    }

    /**
    * returns the size of the task queue
    */
    public synchronized int getSize(){
	return queue.size();
    }
}
