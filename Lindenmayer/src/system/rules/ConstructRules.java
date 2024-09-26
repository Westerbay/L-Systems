package system.rules;


/**
 * Construction of L-System rules
 * @author Wester
 */
public class ConstructRules {

	public static Rules createRulesContext(String ignore, String... allRules){
	 
	 	ContextSensitiveRule rules = new ContextSensitiveRule(ignore.split("#ignore:")[1]);
	 	for (String line: allRules){
	 		if (!line.equals("")){
	 			String[] rule = line.split(":"); 
	 			rules.put(rule[0].charAt(0), rule[0].charAt(2), rule[0].charAt(4), rule[1]);
	 		}
	 	}
	 	return rules;
	 	
	 }	

	 public static Rules createRules(String... allRules){

		ClassicRules rules = new ClassicRules();
       	for (String line: allRules){
       		if (!line.equals("")){
		   		String[] rule = line.split(":"); 
		   		rules.put(rule[0].charAt(0), rule[1]);
		   	}
       	} 
		return rules;

	}
	
	public static Rules createRulesStochastic(String... allRules) {

		StochasticRules rules = new StochasticRules();
		for (String line: allRules){
			if (!line.equals("")){
				String[] rule = line.split(":");
				rules.put(rule[0].charAt(0), Float.parseFloat(rule[1]), rule[2]);
			}
		}
		return rules;			

	}
	
}

