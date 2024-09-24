package system.rules;


/**
 * Classe permettant la construction de règles pour L-System
 * @author dubuiss212
 */
public class ConstructRules {


	/**
     * Permet la construction de règles avec contextes pour l-system
     * @param ignore les caractères à ignorer
  	 * @param allRules les règles à suivre
  	 * @return Un dictionnaire de règles
     */
	public static Rules createRulesContext(String ignore, String... allRules){
	 
	 	ContextSensitiveRule rules = new ContextSensitiveRule(ignore.split("#ignore:")[1]);
	 	for (String line: allRules){
	 		if (!line.equals("")){
	 			String[] rule = line.split(":"); //Parser
	 			rules.put(rule[0].charAt(0), rule[0].charAt(2), rule[0].charAt(4), rule[1]);
	 		}
	 	}
	 	return rules;
	 	
	 }	


	/**
     * Permet la construction simple de règles pour l-system
  	 * @param allRules les règles à suivre
  	 * @return Un dictionnaire de règles
     */
	 public static Rules createRules(String... allRules){

		ClassicRules rules = new ClassicRules();
       	for (String line: allRules){
       		if (!line.equals("")){
		   		String[] rule = line.split(":"); //Parser
		   		rules.put(rule[0].charAt(0), rule[1]);
		   	}
       	} 
		return rules;

	}



	/**
     * Permet la construction stochastique de règles pour l-system
  	 * @param allRules les règles à suivre
  	 * @return Un dictionnaire de règles 
     */
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

