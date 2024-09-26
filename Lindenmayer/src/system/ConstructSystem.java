package system;


import system.rules.ConstructRules;

import java.util.Arrays;


/**
 * Construction of Lindenmayer Systems
 * @author Wester
 */
public class ConstructSystem {

	public static SystemL createSystem(double angle, String axiom, String... allRules){
		replaceAll(allRules);	
		if (allRules[0].contains("ignore")){
			return createSystemContext(angle, axiom, allRules[0], Arrays.copyOfRange(allRules, 1, allRules.length));
		} 
		else if (allRules[0].split(":").length == 2) {
			return createSystemSimple(angle, axiom, allRules);
		} 
		else if (allRules[0].split(":").length == 3) {
			return createSystemStochastic(angle, axiom, allRules);
		}
		return null;
	}

     public static SystemL createSystemSimple(double angle, String axiom, String... allRules){
		return new SystemL(angle, axiom, ConstructRules.createRules(allRules));
     }	 

	 public static SystemL createSystemStochastic(double angle, String axiom, String... allRules) {
		return new SystemL(angle, axiom, ConstructRules.createRulesStochastic(allRules));
	 }
	 
	 public static SystemL createSystemContext(double angle, String axiom, String ignore, String... allRules){
	 	return new SystemL(angle, axiom, ConstructRules.createRulesContext(ignore, allRules));
	 }
	
	/** Format the grammar of the rules */
	public static void replaceAll(String... allRules){
		for (int i=0; i<allRules.length; i++){
			allRules[i] = allRules[i].replaceAll(" ", "").replaceAll("∧", "^").replaceAll("'", "’").replaceAll("−", "-").replaceAll("→", ":");
			if (allRules[i].split(":")[0].equals("Fl")){
				allRules[i] = allRules[i].replaceAll("Fl", "l").replaceAll("Fr", "rF");
			}
			else if (allRules[i].split(":")[0].equals("Fr")){
				allRules[i] = allRules[i].replaceAll("Fr", "r");
			}
		}		
	}

}

