import java.util.*;

public class ParalellPi {
    private static int digitsCompleted;

    public static void main(String[] args){
	TaskQueue myTaskQueue = new TaskQueue();
	ResultTable myResultTable = new ResultTable();

	int processorCount = Runtime.getRuntime().availableProcessors();
	Thread[] threads = new Thread[processorCount];
	long begin = System.currentTimeMillis();

	try{
	    for (int i = 0; i < processorCount; i++){
	        threads[i] = new Thread(new workerThread(myTaskQueue, myResultTable));
	        threads[i].start();
	        threads[i].join();
	    }
	}catch(Exception e){
	    System.out.println("Something went wrong :( \n" + e);
	}

	long time = System.currentTimeMillis() - begin;
	myResultTable.print();
	System.out.println("\nPi computation took " + time + " ms");
    }

    /**
    * increments the digits completed counter
    */
    public static synchronized void incrementDigitsCompleted(){
	digitsCompleted++;
    }

    /**
    * returns the digits completed count
    */
    public static synchronized int getDigitsCompleted(){
	return digitsCompleted;
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

	public void run(){
	    Integer digit;
	    Integer result;
	    Bpp bpp = new Bpp();

	    while(tasks.getSize() != 0){
		digit = tasks.getTask();
		result = bpp.getDecimal(digit.intValue());
		results.setResult(digit, result);
		incrementDigitsCompleted();

		if(getDigitsCompleted() % 10 == 0){
                    System.out.print(".");
                    System.out.flush();
		}
	    }
	}
    }
}
