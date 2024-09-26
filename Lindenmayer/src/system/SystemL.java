package system;


import java.util.HashMap;
import java.util.Map;
import system.rules.Rules;


/**
 * Class for SystemL
 * @author Westers
 */
public class SystemL {
	
	
	private final Map<Integer, String> _generations = new HashMap<>();
	private final Rules _rules;
	private final double _angle;

  	public SystemL(double angle, String axiom, Rules rules){
	    addGeneration(0, axiom);
		_rules = rules;
		_angle = angle;
  	}
  	
  	public String getRules(){
  		return _rules.toString();
  	}
  	
	public double getAngle(){
		return _angle;
	}
	
  	public void addGeneration(int gen, String sentence){
  		_generations.put(gen, sentence);
  	}

  	public String getGeneration(int gen){
  		if (_generations.containsKey(gen))
  			return _generations.get(gen);
  		addGeneration(gen, generate(gen-1));
  		return _generations.get(gen);
  	}

  	public String generate(int gen){
  			
		StringBuilder newSentence = new StringBuilder();
		String actualGen = getGeneration(gen);
		
		for (int i = 0; i < actualGen.length(); i++){
			char rule = actualGen.charAt(i);
			if (_rules.containsKey(rule))
				newSentence.append(_rules.get(i, actualGen));
			else
				newSentence.append(rule);
		}
		
		return newSentence.toString();	
			
	}

}

