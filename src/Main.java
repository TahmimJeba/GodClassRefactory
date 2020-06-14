import hierarchical_clustering.ClusterFromSimilarity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import class_generation.ClassGeneration;
import extractor.MethodCallExtractor;
import extractor.MethodExtractor;
import matrix_from_comments.MatrixConstructionWithComment;
import method_chains_extraction.ChainExtraction;
import model.Method;
import model.Similarity;

public class Main {
	static List<List<Method>> clusterFromCode;
	static List<List<Method>> clusterFromComment;
	static List<Similarity> cosineSimilarities;
	static List<Similarity> cosineSimilaritiesFromCOde;
	public static void main(String[] args) {
	//	String projectPath = "D:/Code Smell/extract class/implementation/xerces2-j-Xerces-J_2_7_0/src/org/apache/xerces/xinclude/XIncludeHandler.java";
		String projectPath = "G:/DurationImpl.java";
	//	String projectPath = "UserManagement.java";
//		FileHandler fileHandler = new FileHandler();
//		List<File>  files = fileHandler.getJavaFiles(projectPath);
		
		List<File>  files = new ArrayList<File>();
		files.add(new File(projectPath));
		for (File file: files){
 			System.out.println(file.getAbsolutePath());
			proceed(file);
			System.out.println();
		}

		MatWithComments(files);
		
		List<List<Method>> methodClusters = mergeResults();
		
		ClassGeneration cg = new ClassGeneration();
		
		for(int i=0;i<methodClusters.size();i++){
			System.out.println();
			System.out.println();
			cg.createNewClass(methodClusters.get(i), i);
		}
	}

	public static void proceed(File file){
		//Extract all methods
		MethodExtractor methodEX = new MethodExtractor();
		List<Method> methods = methodEX.extractMethodsFromSourceFile(file);

		for (int i = 0; i < methods.size(); i++) {
			//set ID for each methods
			Method m = methods.get(i);
			m.setMethodID(i);

			//Extract and set method calls
			MethodCallExtractor methodCallEX = new MethodCallExtractor();
			methodCallEX.extractMethodCallsInsideAMethod(m.getMethodCode());
			List<String> methodCallList = methodCallEX.getMethodCallsList();
			m.setMethodCallList(methodCallList);
		}

		ChainExtraction chainExtraction = new ChainExtraction(methods);
		List<List<Integer>> candidateChains = chainExtraction.getCandidateChains();
		cosineSimilaritiesFromCOde = chainExtraction.getMatrix();
		clusterFromCode = new ArrayList<List<Method>>();
		for(List<Integer> cChain: candidateChains){
			List<Method> ms = new ArrayList<Method>();
			for (Integer mIndex: cChain){
				ms.add(methods.get(mIndex));
			}
			
			clusterFromCode.add(ms);
		}
		
		System.out.println("From Code");
		for(List<Method> ms: clusterFromCode){
			for(Method m: ms){
				System.out.print(m.getMethodName()+", ");
			}
			System.out.println();
			System.out.println();
		}
	}

	public static void MatWithComments(List<File> files){
		for (File file: files) {
			MethodExtractor methodEX = new MethodExtractor();
			List<Method> methods = methodEX.extractMethodsWithComments(file);
			MatrixConstructionWithComment construction = new MatrixConstructionWithComment(methods);
			//construction.printMat();
			double[][] matrix = construction.getMatrix();
			cosineSimilarities = new ArrayList<Similarity>();
			int numMethod = construction.methods.size();
			for(int i=0; i<numMethod; i++) {
	            for (int j = 0; j < i; j++) {
	            	if(matrix[i][j]!=0){
		                Similarity s = new Similarity(construction.methods.get(i), construction.methods.get(j), matrix[i][j]);
		                cosineSimilarities.add(s);
	            	}
	            }
	        }
			
			ClusterFromSimilarity cfs = new ClusterFromSimilarity(cosineSimilarities, construction.methods);
			clusterFromComment = cfs.getClusters();
			System.out.println("From comments");
			for(List<Method> ms: clusterFromComment){
				for(Method m: ms){
					System.out.print(m.getMethodName()+", ");
				}
				System.out.println();
				System.out.println();
			}
		}
	}
	
	private static List<List<Method>> mergeResults() {
		List<List<Method>> MergedCluster = new ArrayList<List<Method>>();
		for(List<Method> fc: clusterFromCode){
			for(List<Method> fcm: clusterFromComment){
				List<Method> matches = checkMatches(fc, fcm);
				if(matches.size()>0)
					MergedCluster.add(matches);
			}
		}
		List<Integer> toRemove = new ArrayList<Integer>();
		for(List<Method> ms: MergedCluster){
			if(ms.size()<2){
				Method m = ms.get(0);
				//MergedCluster.remove(ms);
				double maxMatch = 0.0;
				int maxMatchIndex = 0;
				for(List<Method> methods: MergedCluster){
					double sumOfSimilarity = 0.0;
					for(Method method: methods){
						Similarity s1 = getSimilarityCode(m, method);
						Similarity s2 = getSimilarityComment(m, method);
						
						sumOfSimilarity+=s1.getSimilarity();
						sumOfSimilarity+=s2.getSimilarity();
					}
					
					double avgOfSimilarity = sumOfSimilarity/(2*methods.size());
					
					if(avgOfSimilarity>maxMatch){
						int index = MergedCluster.indexOf(methods);
						if(!toRemove.contains(index)){
							maxMatch = avgOfSimilarity;
							maxMatchIndex = index;
						}
						
					}
				}
				MergedCluster.get(maxMatchIndex).add(m);
				toRemove.add(MergedCluster.indexOf(ms));
			}
		}
		
		List<List<Method>> FinalClasses = new ArrayList<List<Method>>();
		for(int i=0;i<MergedCluster.size();i++){
			if(!toRemove.contains(i)){
				FinalClasses.add(MergedCluster.get(i));
			}
		}
		
		System.out.println("From merged");
		for(List<Method> ms: FinalClasses){
			for(Method m: ms){
				System.out.print(m.getMethodName()+", ");
			}
			System.out.println();
			System.out.println();
		}
		
		return FinalClasses;
	}
	
	private static List<Method> checkMatches(List<Method> m1, List<Method> m2){
		List<Method> matchedList = new ArrayList<Method>();
		//System.out.println(m1.size());
		for(Method m:m1){
			for(Method ma:m2){
				if(m.getMethodName().equals(ma.getMethodName())){
					matchedList.add(m);
				}
			}
		}
		//System.out.println(matchedList.size());
		return matchedList;
	}
	
	private int indexInCodeCluster(Method m) {
		for(List<Method> methods: clusterFromCode){
			if(methods.indexOf(m)>-1){
				return clusterFromCode.indexOf(methods);
			}
		}
		
		return -1;
	}
	
	private int indexInCommentsCluster(Method m) {
		for(List<Method> methods: clusterFromComment){
			if(methods.indexOf(m)>-1){
				return clusterFromComment.indexOf(methods);
			}
		}
		
		return -1;
	}
	
	private static Similarity getSimilarityCode(Method m1, Method m2) {
		for(Similarity s: cosineSimilaritiesFromCOde){
			if((m1.equals(s.getMethod1()) && m2.equals(s.getMethod2())) || (m1.equals(s.getMethod2()) && m2.equals(s.getMethod1()))){
				return s;
			}
		}
		
		return new Similarity(m1, m2, 0);
	}
	
	private static Similarity getSimilarityComment(Method m1, Method m2) {
		for(Similarity s: cosineSimilarities){
			if((m1.equals(s.getMethod1()) && m2.equals(s.getMethod2())) || (m1.equals(s.getMethod2()) && m2.equals(s.getMethod1()))){
				return s;
			}
		}
		
		return new Similarity(m1, m2, 0);
	}
}
