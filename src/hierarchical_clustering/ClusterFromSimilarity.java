package hierarchical_clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.*;

public class ClusterFromSimilarity {
	List<List<Method>> clusters;
	List<Method> methods;
	public ClusterFromSimilarity(List<Similarity> similarities, List<Method> methods) {
		clusters = new ArrayList<List<Method>>();
		this.methods = methods;
		generateCluster(similarities);
		
	}
	
	private double getTheMedianValue(List<Similarity> similarities) {
		// TODO Auto-generated method stub
		double median = similarities.get((int) Math.ceil(similarities.size()/3)).getSimilarity();
		
		return median;
	}
	
	private void generateCluster(List<Similarity> similarities) {
		Collections.sort(similarities, (a, b) -> b.getSimilarity()>a.getSimilarity()?1:-1);
		double median = getTheMedianValue(similarities);
		
		for(Similarity s: similarities){
			if(s.getSimilarity() >= median){
				mergeClusters(s);
			}
			else {
				break;
			}
		}
		
		for(Method m: this.methods){
			int index = indexInCluster(m);
			if(index==-1){
				List<Method> ms = new ArrayList<Method>();
				ms.add(m);
				clusters.add(ms);
			}
		}
//		System.out.println("From hierarchical clustering");
//		for(List<Method> ms: clusters){
//			for(Method m: ms){
//				System.out.print(m.getMethodName()+" ");
//			}
//			System.out.println();
//			System.out.println();
//		}
	}
	
	private void mergeClusters(Similarity s) {
		int indexOfM1 = indexInCluster(s.getMethod1());
		int indexOfM2 = indexInCluster(s.getMethod2());
		if(indexOfM1>-1 && indexOfM2>-1){
			if(indexOfM1!=indexOfM2){
				List<Method> list2 = clusters.get(indexOfM2);
				clusters.get(indexOfM1).addAll(list2);
				clusters.remove(indexOfM2);
			}
		}
		else if(indexOfM1>-1){
			clusters.get(indexOfM1).add(s.getMethod2());
		}
		else if(indexOfM2>-1){
			clusters.get(indexOfM2).add(s.getMethod1());
		}
		else {
			List<Method> methods = new ArrayList<Method>();
			methods.add(s.getMethod1());
			methods.add(s.getMethod2());
			clusters.add(methods);
		}
	}
	
	private int indexInCluster(Method m) {
		for(List<Method> methods: clusters){
			if(methods.indexOf(m)>-1){
				return clusters.indexOf(methods);
			}
		}
		
		return -1;
	}
	
	public List<List<Method>> getClusters() {
		return clusters;
	}
}
