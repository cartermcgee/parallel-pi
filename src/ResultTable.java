import java.util.*;

public class ResultTable {
    private Map results;

    /**
    * initializes result map
    */
    public ResultTable() {
	this.results = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    }

    /**
    * returns a digit of pi given its digit index
    */
    public Integer getResult(Integer key){
	return (Integer) results.get(key);
    }

    /**
    * adds a digit of pi to the result table, key is the digit's index
    */
    public void setResult(Integer key, Integer value){
	results.put(key, value);
    }

    /**
    * prints out the contents of the result table in order by index
    */
    public void print(){
	System.out.print("\n3.");

	for(int i = 1; i <= 1000; i++){
	    System.out.print(results.get(new Integer(i)));
	}
    }
}
