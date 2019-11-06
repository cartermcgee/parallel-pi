import java.util.*;

public class ResultTable {
    private  Map results;
    public final int MAP_SIZE = 1000;
    /**
    * initializes result map
    */
    public ResultTable() {
	this.results = new HashMap<String, Integer>(MAP_SIZE);
    }

    /**
    * adds a digit of pi to the result table, key is the digit's index
    */
    public synchronized void setResult(String key, Integer value){
	results.put(key, value);
    }

    /**
    * prints out the contents of the result table in order by index
    */
    public void print(){
	System.out.print("\n\n3.");

	for(int i = 1; i <= 1000; i++){
	    System.out.print(results.get(new Integer(i).toString()));
	}
    }
}
