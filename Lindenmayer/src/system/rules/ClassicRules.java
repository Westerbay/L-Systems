package system.rules;


import java.util.HashMap;


/**
 * Classe représentant des règles de L-System classiques
 * @author dubuiss212
 */
public class ClassicRules extends HashMap<Character, String> implements Rules {

    /**
     * Constructeur
     */
    public ClassicRules(){
        super();
    }

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

