/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcom;

import extractor.MethodExtractor;
import model.Method;

import java.io.File;
import java.util.List;

/**
 *
 * @author sabir
 */
public class Main {

    public static void main(String[] args){
     	String classPath = "D:/GodClassPartition/new/DI_Class1.java";
    //	String classPath = "D:/Code Smell/extract class/implementation/xerces2-j-Xerces-J_2_7_0/src/org/apache/xerces/xinclude/XIncludeHandler.java";
//     	String classPath = "D:/Code Smell/extract class/xerces2-j-Xerces-J_2_7_0/src/org/apache/xerces/parsers/AbstractDOMParser.java";
      	File file = new File(classPath);
        MethodExtractor methodEX = new MethodExtractor();
        List<Method> methods = methodEX.extractMethodsFromSourceFile(file);
        Lcom lcom = new Lcom(methods);
        System.out.println(lcom.calculateLcom());
    }
}