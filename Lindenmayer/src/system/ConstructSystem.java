package system;


import system.rules.ConstructRules;

import java.util.Arrays;


/**
 * Classe permettant la construction de L-System
 * @author dubuiss212
 */
public class ConstructSystem {

	
	/**
	 * Permet la construction de SystemL (Simple, Stochastique ou ContextSensitive)
	 * @param angle l'angle de base de rotation du LSystem
	 * @param axiom -> état 0
  	 * @param allRules les règles à suivre
  	 * @return Le System créé, ou null si les règles ne correspondent pas aux règles Simples/Stochastiques/ContextSensitives
	 */
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


    /**
     * Permet la construction simple de SystemL
	 * @param angle l'angle de base de rotation du LSystem
     * @param axiom -> état 0
  	 * @param allRules les règles à suivre
  	 * @return Le System créé
     */
     public static SystemL createSystemSimple(double angle, String axiom, String... allRules){
		return new SystemL(angle, axiom, ConstructRules.createRules(allRules));
     }	 



	/**
     * Permet la construction stochastique de SystemL
	 * @param angle l'angle de base de rotation du LSystem
     * @param axiom -> état 0
  	 * @param allRules les règles à suivre
  	 * @return Le System créé
     */
	 public static SystemL createSystemStochastic(double angle, String axiom, String... allRules) {
		return new SystemL(angle, axiom, ConstructRules.createRulesStochastic(allRules));
	 }
	 
	 
	 /**
      * Permet la construction stochastique de SystemL
	  * @param angle l'angle de base de rotation du LSystem
      * @param axiom -> état 0
      * @param ignore les paramètres à ignorer
  	  * @param allRules les règles à suivre
  	  * @return Le System créé
      */
	 public static SystemL createSystemContext(double angle, String axiom, String ignore, String... allRules){
	 	return new SystemL(angle, axiom, ConstructRules.createRulesContext(ignore, allRules));
	 }
	

	/**
	 * Formate les règles (allRules) pour que l'algorithme puisse au maximum les comprendre, et permet la traduction des FASS System
	 * @param allRules les règles à suivre
	 */
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
