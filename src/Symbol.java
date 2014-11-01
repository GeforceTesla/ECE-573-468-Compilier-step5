public class Symbol{

	public String type;
	private String name;
	private String value;
	
	//constructor for variable other than String
	public Symbol(String name, String type){
		this.name = name;		
		this.type = type;
		this.value = null;
	}

	//constructor for String variable
	public Symbol(String name, String type, String value){
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public boolean isString() {
		return type.equals("STRING");
	}

	private int sizeof(String type) {
		if (type.equals("INT")) {
			return 8;
		}else if (type.equals("FLOAT")) {
			return 32;
		}else if (type.equals("STRING")) {
			return 8;
		}else {
			return -1;
		}
	}

    	@Override
    	public String toString() {
        	if (type.equals("STRING")) {
			String s =  "name " + name + " type " + type + " value " + value;
			return s;
        	} 
		else {
			String s =  "name " + name + " type " + type;
			return s;
        	}
    	}	
	
}
