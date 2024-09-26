package system.rules;


import java.util.HashMap;


/**
 * Classic L-System rules
 * @author Wester
 */
public class ClassicRules extends HashMap<Character, String> implements Rules {

    @Override
    public boolean containsKey(char c){
        return super.containsKey(c);
    } 

    @Override
    public String get(int index, String ch){
        return super.get(ch.charAt(index));
    } 
    
    @Override
    public String toString(){
    	String repr = "";
    	for (char c: keySet()){
    		repr += String.valueOf(c) + ": " + super.get(c) + "\n";
    	}
    	return repr;
    }

}

