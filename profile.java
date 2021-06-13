
public class profile {
	
	private String name,ip,port;
	
	public profile(String a,String b,String c) {
		
		this.name = a;
		this.ip = b;
		this.port = c;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	

}
