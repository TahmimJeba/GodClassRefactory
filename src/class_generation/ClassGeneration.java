package class_generation;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import model.Method;

public class ClassGeneration {
	private List<Method> methods;
	private List<String> properties;
	public void createNewClass(List<Method> methods, int serial) {
		this.methods = methods;
		List<String> props = new ArrayList<String>();
		for(Method m: methods){
			props.addAll(m.getInstanceVarList());
		}
		HashSet<String> p = new HashSet<String>(props);
		properties = new ArrayList<String>(p);
		printClass(serial);
	}
	
	private void printClass(int serial) {
		System.out.println("public class Class"+serial+" {");
		for(String p: properties){
			System.out.println("\tprivate Object "+p+";");
		}
		System.out.println();
		System.out.print("\tpublic Class"+serial+"(");
		for(int i=0;i<properties.size();i++){
			System.out.print("Object "+properties.get(i));
			if(i+1!=properties.size()){
				System.out.print(", ");
			}
		}
		System.out.println("){");
		
		for(String p: properties){
			System.out.println("\t\tthis."+p+" = "+p+";");
		}
		
		System.out.println("\t}");
		for(Method m: methods){
			System.out.println("\t"+m.getMethodCode());
		}
		
		System.out.println("}");

	}
}
