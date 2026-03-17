package symboltable;

import ast.definitions.Definition;

import java.util.*;

public class SymbolTable {
	
	private int scope = 0;
	private List<Map<String, Definition>> table;


	public SymbolTable()  {
		table = new ArrayList<>();
		// Añadimos los dos ámbitos del sistema: global y local
		table.add(new HashMap<String, Definition>());
	}

	// Se llama cuando entramos en un nuevo ámbito
	public void set() {
		table.add(new HashMap<String, Definition>());
		scope++;
	}

	// Se llama cuando salimos de un nuevo ámbito
	public void reset() {
		table.remove(scope);
		scope--;
	}
	
	public boolean insert(Definition definition) {
		if(findInCurrentScope(definition.getName())) return false;

		definition.setScope(scope);
		table.get(scope).put(definition.getName(), definition);
		return true;
	}

	public Definition find(String id) {
		for(int s = scope; s>=0; s--) {
			Definition d = table.get(s).get(id);
			if(d!=null) {
				return d;
			}
		}
		return null;
	}

	//package-protected for testing pourposes
	boolean findInCurrentScope(String id) {
		return table.get(scope).containsKey(id);
	}
}
