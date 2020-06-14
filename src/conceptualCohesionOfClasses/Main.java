package conceptualCohesionOfClasses;

import extractor.MethodExtractor;
import model.Method;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args){
 //	    String classPath = "D:/Code Smell/extract class/implementation/xerces2-j-Xerces-J_2_7_0/src/org/apache/xerces/xinclude/XIncludeHandler.java";
    	String classPath = "new/Problem/NonValidatingConfiguration_Class1.java";
   //  	String classPath = "D:/Code Smell/extract class/implementation/ganttproject-1.10.2/src/net/sourceforge/ganttproject/GanttGraphicArea.java";
      	File file = new File(classPath);
        MethodExtractor methodEX = new MethodExtractor();
        List<Method> methods = methodEX.extractMethodsFromSourceFile(file);
        CohesionCalculation cohesionCalculation = new CohesionCalculation(methods);
        cohesionCalculation.calculateC3FromLsi();
        System.out.println(classPath);
        System.out.println(cohesionCalculation.getC3());
    }
}