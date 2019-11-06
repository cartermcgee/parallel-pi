import java.util.*;

public class ParalellPi {
    public static void main(String[] args){
	TaskQueue myTaskQueue = new TaskQueue();
	ResultTable myResultTable = new ResultTable();

	int processorCount = Runtime.getRuntime().availableProcessors();
	Thread[] threads = new Thread[processorCount];
	long begin = System.currentTimeMillis();
	System.out.print("\nCalculating Pi");

	try{
	    for (int i = 0; i < processorCount; i++){
	        threads[i] = new Thread(new workerThread(myTaskQueue, myResultTable));
	        threads[i].start();
	    }

	    for (int i = 0; i < processorCount; i++){
                threads[i].join();
            }
	}catch(Exception e){
	    System.out.println("Something went wrong :( \n" + e);
	}

	long time = System.currentTimeMillis() - begin;
	myResultTable.print();
	System.out.println("\n\nPi computation took " + time + " ms");
    }

    /**
    * Worker thread which takes a task queue and a result table to compute digits of pi and store the results
    */
    private static class workerThread implements Runnable{
	private TaskQueue tasks;
	private ResultTable results;

	public workerThread(TaskQueue taskQueue, ResultTable resultTable){
	    this.tasks = taskQueue;
	    this.results = resultTable;
	}

	/**
	* gets a digit of pi to compute, computes it, and stores it in a hash map until there are no more digits to be completed
	*/
	public void run(){
	    Integer digit;
	    Integer result;
	    Bpp bpp = new Bpp();
	    int c = tasks.getSize();

	    while(c != 0){
		digit = tasks.getTask();
		result = bpp.getDecimal(digit);
		results.setResult(digit.toString(), result);
		c = tasks.getSize();

		if(c % 10 == 0){
		    System.out.print(".");
                    System.out.flush();
		}
	    }
	}
    }
}
