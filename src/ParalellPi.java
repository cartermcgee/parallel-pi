import java.util.*;

public class ParalellPi {
    public static void main(String[] args){
	TaskQueue myTaskQueue = new TaskQueue();
	ResultTable myResultTable = new ResultTable();

	int processorCount = Runtime.getRuntime().availableProcessors();
	Thread[] threads = new Thread[processorCount];

	for (int i = 0; i < processorCount; i++){
	    threads[i] = new Thread(new workerThread(myTaskQueue, myResultTable));
	    threads[i].start();
	}
    }

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
		result = bpp.getDecimal(digit);
		results.setResult(digit, result);
	    }
	}

    }
}
