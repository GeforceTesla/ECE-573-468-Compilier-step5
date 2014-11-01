import java.util.*;

public class SymbolTableTree {

    private SymbolTable global_scope; 
    private SymbolTable current_scope;
    private int block_num;

    //constructor sets the global scope as the root scope and the current scope
    public SymbolTableTree () { 
        global_scope = new SymbolTable("GLOBAL");
        current_scope = global_scope;
	block_num = 0;
    }

    //"BLOCK" + block_num scope begins
    public void scope_begin() {
	block_num = block_num + 1;
        scope_begin("BLOCK " + block_num);     
    }

    public String get_scope(){
        return "LABEL " + block_num;
    }


    public LinkedHashMap<String, Symbol> get_symbol_table(){
        return global_scope.scope_table;
    }

    public void scope_begin(String scope_name) {
	SymbolTable block_scope = new SymbolTable(scope_name);
	block_scope.scope_parent = current_scope;
	current_scope.scope_children.add(block_scope);
	current_scope = block_scope;
    }

    //go the parent scope
    public void scope_end() {
        current_scope = current_scope.scope_parent;
    }

    public void add_mult_variables(ArrayList<String> names, String type) {
        for (String name : names) {
            add_one_variable(name, type);
        }
    }

    public void add_one_variable(String name, String type) {
        // check if the variable exists in its parent scopes
        find_SHADOW(current_scope.scope_parent, name);

	// check if there are two declarations with the same name in the same scope
        if (current_scope.scope_table.containsKey(name)) {
            System.out.println("DECLARATION ERROR " + name);
            System.exit(0);
        }

        // add the varible to the current scope table.
        current_scope.scope_table.put(name, new Symbol(name, type));
    }

    //do the same thing as variable
    public void add_string(String name, String value) {
	find_SHADOW(current_scope.scope_parent, name);
        if (current_scope.scope_table.containsKey(name)) {
            System.out.println("DECLARATION ERROR " + name);
            System.exit(0);
        }
        current_scope.scope_table.put(name, new Symbol(name, "STRING", value));
    }

    private void find_SHADOW(SymbolTable scope, String name) {
        if (scope == null) {
            return;
        }

	//print "SHADOW WARNING" if the variable in the scope
        if (scope.scope_table.containsKey(name)) {
            System.out.println("SHADOW WARNING " + name);
        }

	//go to the older parent to check SHADOW.
        find_SHADOW(scope.scope_parent, name);
    }

	public Boolean contains_value(String varName){
		return _contains_value(current_scope, varName);
	}

	private Boolean _contains_value(SymbolTable scope, String varName){
		if (scope == null){
			return false;
		}

		if (scope.scope_table.containsKey(varName)){
			return true;
		} else{
			return _contains_value(scope.scope_parent, varName);
		}
	} 

	public Symbol lookup(String varName) {
		return _lookup(current_scope, varName);
	}

	private Symbol _lookup(SymbolTable scope, String varName) {
		if (scope == null){
			return null;
		}

		if (scope.scope_table.containsKey(varName)) {
			//System.out.println("looking up " + varName);
			return scope.scope_table.get(varName);
		} else {
			// recurse up to find it
			return _lookup(scope.scope_parent, varName);
		}
	}


	public void get_all_entries(){

	}

	public void print_entire_tree() {
		print_tree(global_scope);
	}

    // recurse the function to get the whole tree.
	private void print_tree(SymbolTable scope) {
		scope.print_table();
		System.out.println();
		for (SymbolTable children : scope.scope_children) {
			print_tree(children);
		}
	}
}
