package system;


import java.util.HashMap;
import java.util.Map;
import system.rules.Rules;


/**
 * Classe représentant un L-System
 * @author dubuiss212
 */
public class SystemL {
	
	
	private final Map<Integer, String> generations = new HashMap<>();
	private final Rules rules;
	private final double angle;

	/**
  	 * Constructeur
  	 * @param angle L'angle de rotation par défaut du système
  	 * @param axiom L'état 0
  	 * @param rules Les règles du L-System
  	 */
  	public SystemL(double angle, String axiom, Rules rules){
	    addGeneration(0, axiom);
		this.rules = rules;
		this.angle = angle;
  	}
  	
  	/**
  	 * Renvoie une représentation graphique des règles
  	 * @return Une représentation graphique des règles
  	 */
  	public String getRules(){
  		return rules.toString();
  	}


	/**
	 * Accesseur angle
	 * @return L'attribut angle
	 */
	public double getAngle(){
		return angle;
	}


  	/**
  	 * Ajoute une génération au dictionnaire
  	 * @param gen Le numéro de la génération
  	 * @param sentence L'état du System à la gen-ième génération
  	 */
  	public void addGeneration(int gen, String sentence){
  		generations.put(gen, sentence);
  	}


  	/**
     * Fonction renvoyant la phrase à la gen-ième génération
     * @param gen Le numéro de la génération
     * @return La phrase correspondante à la gen-ième génération
     */
  	public String getGeneration(int gen){
  		if (generations.containsKey(gen))
  			return generations.get(gen);
  		addGeneration(gen, generate(gen-1));
  		return generations.get(gen);
  	}


  	/**
  	 * Génère l'état du system à la gen-ième génération + 1
  	 * @param gen Le numéro de la génération
  	 * @return L'état du system à la gen-ième génération + 1
  	 */
  	public String generate(int gen){
  			
		StringBuilder newSentence = new StringBuilder();
		String actualGen = getGeneration(gen);
		
		for (int i = 0; i < actualGen.length(); i++){
			char rule = actualGen.charAt(i);
			if (rules.containsKey(rule))
				newSentence.append(rules.get(i, actualGen));
			else
				newSentence.append(rule);
		}
		
		return newSentence.toString();	
			
	}

}
