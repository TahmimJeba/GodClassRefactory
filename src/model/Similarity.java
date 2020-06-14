package model;

import java.util.Comparator;

public class Similarity {
	private Method method1;
	private Method method2;
	private double similarity;
	public Similarity(Method method1, Method method2, double similarity) {
		super();
		this.method1 = method1;
		this.method2 = method2;
		this.similarity = similarity;
	}
	public Method getMethod1() {
		return method1;
	}
	public void setMethod1(Method method1) {
		this.method1 = method1;
	}
	public Method getMethod2() {
		return method2;
	}
	public void setMethod2(Method method2) {
		this.method2 = method2;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	
	Comparator<Double> comp = (Double a, Double b) -> {
	    return b.compareTo(a);
	};
}
