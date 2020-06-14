package conceptualCohesionOfClasses;

import IR.MethodProcessing;
import IR.TfIdf;
import lsi.LSI;
import manage.files.FileManipulation;
import model.Method;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CohesionCalculation {

	private FileManipulation fileManipulation;
	private List<List<String>> arrayList;
	
    private List<Method> methods;
    private int numMethod;
    private double C3;

    public CohesionCalculation(List<Method> methods){
        this.methods = methods;
        this.numMethod = methods.size();
        this.calculateC3();
        fileManipulation = new FileManipulation();
        arrayList = new ArrayList<>();
    }

    public double getC3() {
        return this.C3;
    }

    public void calculateC3FromLsi(){
    	List<List<String>> processed = new MethodProcessing().processWithStatements(methods);
		createFolders();
    	fileManipulation.manageStopWords();
    	makeNecessaryFiles(processed);
    	this.C3 = getLsiValue(processed);
    }
    
    private double getLsiValue(List<List<String>> processed) {
		try {
			LSI lsi = new LSI(FileManipulation.SOURCE_FOLDER_NAME, FileManipulation.STOP_WORD_FOLDER_NAME
					+ "\\" + FileManipulation.STOP_WORD_FILE_NAME);
			lsi.createTermDocumentMatrix();
			lsi.performSingularValueDecomposition();
			Double[] avg = new Double[arrayList.size()];
			for(int i = 0; i < arrayList.size(); i++) {
				Double subTotal = 0.0;
				String queryString = "";
				for(String line : arrayList.get(i)){
					queryString += line;
				}
				List<Double> query = lsi.handleQuery(queryString);
				for(int j = 0; j < query.size(); j++) {
					subTotal += query.get(j);
				}
				if(query.size() != 0 && query.size() != 1)
					avg[i] = subTotal / (query.size() - 1);
				else avg[i] = 1.0;
			}
			Double total = 0.0;
			for(Double num : avg) {
				total += num;
			}
			if(avg.length > 0)
				return total / avg.length;
			else return 1.0;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1.0;
	}

	private void makeNecessaryFiles(List<List<String>> processed) {
//		for(Method method : methods){
//			ArrayList<String> temp = new ArrayList<>();
//			for(String statement : method.getStatementList()){
//				temp.add(statement);
//			}
//			arrayList.add(temp);
//		}
		arrayList = processed;
		ArrayList<File> files = new ArrayList<>();
		try {
			for(Integer i = 0; i < arrayList.size(); i++) {
				files.add(new File(FileManipulation.SOURCE_FOLDER_NAME + "\\" +  i.toString() + ".txt"));
				try {
					files.get(i).createNewFile();
					try(BufferedWriter br = new BufferedWriter(new FileWriter(files.get(i).getAbsolutePath()))){
						for(String line : arrayList.get(i)){
							br.write(line);
						}
					} catch(Exception e2) {
						e2.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createFolders() {
		fileManipulation.createSourceDirectory();
		fileManipulation.createStopWordDirectory();
	}

	public void calculateC3(){
        TfIdf tfIdf = new TfIdf();
        tfIdf.calcWithStatements(this.methods);
        double[][] tfIdfVectors = tfIdf.getTfIdfVectors();
        double[] euclideanNorm = tfIdf.getEuclideanNorm();

        double numEdges = (this.numMethod*(this.numMethod-1))/2;
        double acsm = 0;

        for(int i=0; i<this.numMethod; i++) {
            for (int j = i + 1; j < this.numMethod; j++) {
                if (euclideanNorm[i] != 0 && euclideanNorm[i] != 0) {
                    double csm_ij = this.vecProduct(tfIdfVectors[i], tfIdfVectors[j]) / (euclideanNorm[i] * euclideanNorm[j]);
                    acsm += csm_ij;
                }
            }
        }
        acsm /= numEdges;
        this.C3 = (acsm>0)? acsm: 0;
    }

    private double vecProduct(double[] vector1, double[] vector2){
        double prod = 0;
        for(int i=0; i<vector1.length; i++){
            prod += vector1[i]*vector2[i];
        }
        return prod;
    }

}