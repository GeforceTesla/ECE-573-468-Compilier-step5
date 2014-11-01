import java.util.*;

public class GenerateCode{

	ArrayList<IrNode> irNode;
	ArrayList<TinyNode> tinyNode;
	HashMap<String, String> table;
	private int currentRegister;
	SymbolTableTree tree;
	boolean newFc = true;

	public GenerateCode(SymbolTableTree symbolTree){
		currentRegister = -1;
		irNode = new ArrayList<IrNode>();
		tinyNode = new ArrayList<TinyNode>();
		table = new HashMap<String, String>();
		tree = symbolTree;
	
	}

	private String getNewRegister() {
		currentRegister += 1;
		String registerName = new String("r" + currentRegister);
		return registerName;
	}

	public void add_irNode(String opCode, String op1, String op2, String op3){
		IrNode node = new IrNode(opCode, op1, op2, op3);
		irNode.add(node);
	}

	public void print_all_irNode(){
		for (IrNode node: irNode){
			System.out.println(node);
		}
	}

	public void add_tinyNode(String opCode, String op1, String op2){
		TinyNode node = new TinyNode(opCode, op1, op2);

		tinyNode.add(node);
	}

	public void print_all_tinyNode(){
		for (TinyNode node: tinyNode){
			System.out.println(node);
		}
	}

	public void convertCode(){
		LinkedHashMap<String, Symbol> temp = tree.get_symbol_table();
		Set<String> keys = temp.keySet();
		for (String key : keys) {
			if (!key.equals("newline")){
				add_tinyNode("var", key, null);
			}else{
				add_tinyNode("str", key, "\"\\n\"");
			}
		}
		for (IrNode node: irNode){
			if (node.opCode.equals("LABEL")){
				convert_Label(node.op1);
			}else if (node.opCode.equals("JUMP")){
				convert_jmp(node.op1);
			}else if(node.opCode.equals("STOREI") || node.opCode.equals("STOREF")){
				convert_Store(node.op1, node.op2);
			}else if(node.opCode.equals("READI") || node.opCode.equals("READF")){
				convert_Read(node.op1);
			}else if(node.opCode.equals("DIVI") || node.opCode.equals("DIVF")){
				convert_Div(node.opCode, node.op1, node.op2, node.op3);
			}else if(node.opCode.equals("MULTI") || node.opCode.equals("MULTF")){
				convert_Mult(node.opCode, node.op1, node.op2, node.op3);
			}else if(node.opCode.equals("ADDI") || node.opCode.equals("ADDF")){
				convert_Add(node.opCode, node.op1, node.op2, node.op3);
			}else if(node.opCode.equals("SUBI") || node.opCode.equals("SUBF")){
				convert_Sub(node.opCode, node.op1, node.op2, node.op3);
			}else if(node.opCode.equals("WRITEI") || node.opCode.equals("WRITEF") || node.opCode.equals("WRITES")){
				convert_Write(node.opCode, node.op1);
			}else if(node.opCode.equals("RET")){
				convert_Ret();
			}else if (node.opCode.length() == 3){
				convert_Branch(node.opCode, node.op1, node.op2, node.op3);
			}
			
		}
	}


	public void convert_Branch(String opCode, String op1, String op2, String op3){
		//System.out.println(opCode + " " + op1 + " " + op2 + " " + op3);
		boolean reg1 = false;
		boolean reg2 = false;
		char postfix;

		if (op1.charAt(0) == '$'){
			op1 = table.get(op1);
			reg1 = true;
		}

		if (op2.charAt(0) == '$'){
			op2 = table.get(op2);
			reg2 = true;
		}

		if (reg1 == false && reg2 == false){
			String text = getNewRegister();
			//table.put(op1, text);
			add_tinyNode("move", op2, text);
			op2 = text;
		}
		
		if (opCode.charAt(2) == 'I'){
			postfix = 'i';
		}else{
			postfix = 'r';
		}		

		if (opCode.startsWith("LE")){
			add_tinyNode("cmp" + postfix, op1, op2);
			add_tinyNode("jle", op3, null);
		}else if (opCode.startsWith("GE")){
			add_tinyNode("cmp" + postfix, op1, op2);
			add_tinyNode("jge", op3, null);
		}else if (opCode.startsWith("LT")){
			add_tinyNode("cmp" + postfix, op1, op2);
			add_tinyNode("jlt", op3, null);
		}else if (opCode.startsWith("GT")){
			add_tinyNode("cmp" + postfix, op1, op2);
			add_tinyNode("jgt", op3, null);
		}else if (opCode.startsWith("EQ")){
			add_tinyNode("cmp" + postfix, op1, op2);
			add_tinyNode("jeq", op3, null);
		}else if (opCode.startsWith("NE")){
			add_tinyNode("cmp" + postfix, op1, op2);
			add_tinyNode("jne", op3, null);
		}
	}

	public void convert_Ret(){
			add_tinyNode("sys", "halt", null);
	}

	public void convert_Write(String opCode, String op1){

		if(op1.equals("newline")){
			add_tinyNode("sys", "writes", op1);
		}else if (opCode.equals("WRITEI")){
			add_tinyNode("sys", "writei", op1);
		}else {
			add_tinyNode("sys", "writer", op1);
		}
	}

	public void convert_Div(String opCode, String op1, String op2, String op3){
		String ops1 = op1;
		String ops2 = op2;
		String operation = "divi";

		if (opCode.equals("DIVF")){
			operation = "divr";
		}
		String result = getNewRegister();

		table.put(op3, result);
		if (ops1.charAt(0) == '$'){
			ops1 = table.get(op1);
		}
		if (ops2.charAt(0) == '$'){
			ops2 = table.get(op2);
		}
		add_tinyNode("move", ops1, result);
		add_tinyNode(operation, ops2, result);	
	}

	public void convert_Sub(String opCode, String op1, String op2, String op3){
		String ops1 = op1;
		String ops2 = op2;
		String operation = "subi";

		if (opCode.equals("SUBF")){
			operation = "subr";
		}
		String result = getNewRegister();

		table.put(op3, result);
		if (ops1.charAt(0) == '$'){
			ops1 = table.get(op1);
		}
		if (ops2.charAt(0) == '$'){
			ops2 = table.get(op2);
		}
		add_tinyNode("move", ops1, result);
		add_tinyNode(operation, ops2, result);	
	}

	public void convert_Add(String opCode, String op1, String op2, String op3){
		String ops1 = op1;
		String ops2 = op2;
		String operation = "addi";

		if (opCode.equals("ADDF")){
			operation = "addr";
		}

		String result = getNewRegister();

		table.put(op3, result);
		if (ops1.charAt(0) == '$'){
			ops1 = table.get(op1);
		}
		if (ops2.charAt(0) == '$'){
			ops2 = table.get(op2);
		}
		add_tinyNode("move", ops1, result);
		add_tinyNode(operation, ops2, result);	
	}

	public void convert_Mult(String opCode, String op1, String op2, String op3){
		String ops1 = op1;
		String ops2 = op2;
		String operation = "muli";

		if (opCode.equals("MULTF")){
			operation = "mulr";
		}
		String result = getNewRegister();

		table.put(op3, result);
		if (ops1.charAt(0) == '$'){
			ops1 = table.get(op1);
		}
		if (ops2.charAt(0) == '$'){
			ops2 = table.get(op2);
		}
		add_tinyNode("move", ops1, result);
		add_tinyNode(operation, ops2, result);	
	}

	public void convert_Read(String op1){
		if (!Character.isDigit(op1.charAt(0))){
			add_tinyNode("sys", "readi", op1);
		}else{
			add_tinyNode("sys", "readr", op1);
		}
	}

	public void convert_Label(String op1){
		if (newFc == true){
			//add_tinyNode("str", "newline", "\"\\n\"");
			newFc = false;
		}
		add_tinyNode("label", op1, null);
	}

	public void convert_jmp(String op1){
		add_tinyNode("jmp", op1, null);
	}

	public void convert_Store(String op1, String op2){
		String text = op2;
		if (op2.charAt(0) == '$'){
			text = getNewRegister();
			table.put(op2, text);
			add_tinyNode("move", op1, text);
		}else if (table.get(op1) != null){
			text = table.get(op1);
			add_tinyNode("move", text, op2);
		}else{
			text = getNewRegister();
			//table.put(op1, text);
			add_tinyNode("move", op1, text);
			add_tinyNode("move", text, op2);
		}
	}

}
