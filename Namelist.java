import java.io.Serializable;
import java.util.ArrayList;

public class Namelist implements Serializable{

	private static final long serialVersionUID = 1L;
	ArrayList<String> names;
	
	public Namelist() {
		
		
		names = new ArrayList<String>();
		
		
		
	}
	
	public void addName(String s) {
		
		names.add(s);
		
	}
	
	public ArrayList<String> getArrayList() {
		
		return names;
		
	}
	
	public void removeName(String s) {
		for(int i=0;i<names.size();i++)
		if(names.get(i).equals(s))
		names.remove(i);
	}
	
	public int size() {
		
		return names.size();
	}
	
	
	

}
