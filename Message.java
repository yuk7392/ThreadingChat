import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	String s;
	
	public Message() {
		
		s = "";
		
	}
	
	public Message(String s) {
		
		
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
