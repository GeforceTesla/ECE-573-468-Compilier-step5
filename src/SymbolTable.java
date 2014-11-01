import java.util.*;

public class SymbolTable {

        String scope_name;
        SymbolTable scope_parent;
        ArrayList<SymbolTable> scope_children;
        LinkedHashMap<String, Symbol> scope_table;

        public SymbolTable (String scope_name) {
            this.scope_name = scope_name;
            scope_parent = null;
            scope_children = new ArrayList<SymbolTable>();
            scope_table = new LinkedHashMap<String, Symbol>();
        }

        public void print_table() {
            System.out.println("Symbol table " + scope_name);
            Set<String> keys = scope_table.keySet();
            for (String key : keys) {
                System.out.println(scope_table.get(key));
            }
        }


    }
