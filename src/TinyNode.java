import java.io.*;

class TinyNode{
	private String opCode;
	private String op1;
	private String op2;

	public TinyNode(String Operation, String oprand1, String oprand2) { 
		this.opCode = Operation;
		this.op1 = oprand1;
		this.op2 = oprand2;
	}

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (opCode != null){
			str.append(opCode);
		}
		if(op1 != null){
			str.append(" " + op1);
		}
		if(op2 != null){
			str.append(" " + op2);
		}
		return str.toString();
	}


}
