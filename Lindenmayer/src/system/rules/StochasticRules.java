package system.rules;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * Stochastic L-System rules
 * @author Wester
 */
public class StochasticRules extends HashMap<Character, ArrayList<Pair<Float, String>>> implements Rules {
   
    public void put(char key, float proba, String replace) {
        put(key, new Pair<>(proba, replace));
    }
    
    public void put(char key, Pair<Float, String> rule) {
        if (!containsKey(key)){
            ArrayList<Pair<Float, String>> listOfRules = new ArrayList<>();
            listOfRules.add(rule);
            super.put(key, listOfRules);
        }
        else {
            super.get(key).add(rule);
        }
    }
    
    

    @Override
    public String get(int index, String ch) {
    
    	char key = ch.charAt(index);
        if (containsKey(key)){
            double tirage = Math.random();
            for (Pair<Float, String> rule: super.get(key)){
                float p = rule.getKey();
                if (tirage < p)
                    return rule.getValue();
                tirage -= p;
            }
        }

        return null;
    }

    @Override
    public boolean containsKey(char c) {
        return super.containsKey(c);
    } 
    
    @Override
    public String toString() {
    	String repr = "";
    	for (char c: keySet()){
    		for (Pair<Float, String> pair: super.get(c)){
    			float p = pair.getKey();
    			String res = pair.getValue();
    			repr += String.valueOf(c) + ": " + p + ": " + res + "\n";
    		} 
    	}
    	return repr;
    }

}

