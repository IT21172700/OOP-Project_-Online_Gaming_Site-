package pack2;

public class Glist {
	
	private String name;
	private String details;
	private String type;
	private String devName;
	private String filepath; 
	private String gameimage; 

	
	//creating the constructor for variables
	public Glist(String name, String details, String type, String devName, String filepath, String gameimage) {
		this.name = name;
		this.details = details;
		this.type = type;
		this.devName = devName;
		this.filepath = filepath;
		this.gameimage = gameimage;
	}
	

	public String getname() {
		return name;
	}

	public String getdetails() {
		return details;
	}

	public String gettype() {
		return type;
	}	

	public String getdevName() {
		return devName;
	}
	
	public String getfilepath() {
		return filepath;
	}
	public String getgameimage() {
		return gameimage;
	}


}
