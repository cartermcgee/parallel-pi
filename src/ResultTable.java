import java.util.*;

public class ResultTable {
    private Map results;

    public ResultTable() {
	this.results = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    }

    public Integer getResult(Integer key){
	return (Integer) results.get(key);
    }

    public void setResult(Integer key, Integer value){
	results.put(key, value);
    }

    public void print(){
	System.out.print("\n3.");

	for(int i = 1; i <= 1000; i++){
	    System.out.print(results.get(new Integer(i)));
	}
    }
}
