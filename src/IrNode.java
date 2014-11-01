import java.io.*;

class IrNode{
	public String opCode;
	public String op1;
	public String op2;
	public String op3;

	public IrNode(String Operation, String oprand1, String oprand2, String oprand3) { 
		this.opCode = Operation;
		this.op1 = oprand1;
		this.op2 = oprand2;
		this.op3 = oprand3;
	}

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (opCode != null){
			str.append(";" + opCode + " ");
		}
		if(op1 != null){
			str.append(op1);
		}
		if(op2 != null){
			str.append(" " + op2);
		}
		if(op3 != null){
			str.append(" " + op3);
		}
		return str.toString();
	}


}
