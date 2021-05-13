import java.io.Serializable;

public class OutroMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	String s;
	
	public OutroMessage() {
		
		s = "";
		
	}
	
	public OutroMessage(String s) {
		
		
		this.s= s;
		
	}
	
	public void setMessage(String s) {
		
		this.s = s;
		
	}

	@Override
	public String toString() {
		
		return s;
		
	}
	
	
	
}
