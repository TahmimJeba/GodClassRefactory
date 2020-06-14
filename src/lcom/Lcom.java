/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcom;

import java.util.List;
import model.Method;

/**
 *
 * @author sabir
 */
public class Lcom {
    
    private List<Method> methods;

    public Lcom(List<Method> methods) {
        this.methods = methods;
    }
    
    private Boolean checkIntersection(Method first, Method second){
        if(first != null && first.getInstanceVarList() != null 
                && !first.getInstanceVarList().isEmpty() 
                && second != null && second.getInstanceVarList() != null 
                && !second.getInstanceVarList().isEmpty()){
            for(String firstIntanceVar: first.getInstanceVarList()){
                for(String secondInstanceVar: second.getInstanceVarList()){
                    if(firstIntanceVar.equalsIgnoreCase(secondInstanceVar)){
                        return true;
                    }
                }
            }
            return false;
        }
        return null;
    }

    public int calculateLcom() {
        int matched = 0;
        int unmatched = 0;
        if(this.methods != null && this.methods.size() > 1){
            for(int i = 0; i < this.methods.size() - 1; i++){
                for(int j = i + 1; j < this.methods.size(); j++){
                    Boolean intersection = checkIntersection(this.methods.get(i), this.methods.get(j));
                    if(Boolean.TRUE.equals(intersection)){
                        matched++;
                    } else if(Boolean.FALSE.equals(intersection)){
                        unmatched++;
                    }
                }
            }
        }
        return unmatched - matched >= 0 ? unmatched - matched : 0;
    }
    
}
