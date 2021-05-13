import java.io.Serializable;

public class IntroMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	String s;
	
	public IntroMessage() {
		
		s = "";
		
	}
	
	public IntroMessage(String s) {
		
		
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
