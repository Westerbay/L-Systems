package system.rules;


import java.util.HashMap;
import java.util.ArrayList;


/**
 * Classe permettant l'interprétention de règles L-System avec contexte
 * @author dubuiss212
 */
public class ContextSensitiveRule extends HashMap<Character, ArrayList<Triple<Character, Character, String>>> implements Rules {

	private final String ignore;
	private String defaultIgnore = "[]";
	
	private int countBracketed;
	
	/**
	 * Constructeur
	 * @param ignore les caractères à ignorer
	 */
	public ContextSensitiveRule(String ignore){
		super();
		this.ignore = ignore;
		defaultIgnore += ignore;
		countBracketed = 0;
	}

	private char voisinGauche(int index, String ch){

		if (index == 0) { return '*'; }

		index --;
		char car = ch.charAt(index);

		while (countBracketed > 0 || defaultIgnore.contains(String.valueOf(car))) {

			if (index == 0) {
				return '*';
			} else if (car == ']'){
				countBracketed ++;
			} else if (car == '['){
				if (countBracketed > 0)
					countBracketed--;
			}

			index --;
			car = ch.charAt(index);

		}
		
		countBracketed = 0;
		return car;

	}
	
	
	private char voisinDroit(int index, String ch){

		if (index == ch.length() - 1) { return '*'; }

		index ++;
		char car = ch.charAt(index);

		while (countBracketed > 0 || defaultIgnore.contains(String.valueOf(car))) {

			if (index == ch.length() - 1) {
				return '*';
			} else if (car == '['){
				countBracketed ++;
			} else if (car == ']'){
				if (countBracketed > 0)
					countBracketed--;
				else return '*';
			}

			index ++;
			car = ch.charAt(index);

		}
		
		countBracketed = 0;
		return car;
	}
	
	
	private boolean isLeft(int index, String ch, char left){
		return left == '*' || voisinGauche(index, ch) == left;
	}
	
	
	
	private boolean isRight(int index, String ch, char right){
		return right == '*' || voisinDroit(index, ch) == right;
	}
	
	
	
	private boolean between(int index, String ch, char left, char right){
		return isLeft(index, ch, left) && isRight(index, ch, right);
	}
	
	
	
	
	/**
	 * Ajoute une pair de valeur aux règles
	 * @param left le caractère gauche des contextSensitivesRules
	 * @param key le caractère à changer des contextSensitivesRules
	 * @param droit le caractère droit des contextSensitivesRules
	 * @param replace le resultat de la transformation
	 */
	public void put(char left, char key, char right, String replace){
		Triple<Character, Character, String> rule = new Triple<>(left, right, replace);
		if (!containsKey(key)){
			ArrayList<Triple<Character, Character, String>> listOfRules = new ArrayList<>();
			listOfRules.add(rule);
			super.put(key, listOfRules);
		}
		else {
			super.get(key).add(rule);
		}
	}
	
	
	
	@Override
	public String get(int index, String ch){
		char car = ch.charAt(index);
		for (Triple<Character, Character, String> rule: super.get(car)){
			char left = rule.getFirstValue(), right = rule.getSecondValue();
			if (between(index, ch, left, right))
				return rule.getThirdValue();
		}
		return String.valueOf(car);
	}
	
	@Override
	public boolean containsKey(char c){
		return super.containsKey(c);
	} 
    
    @Override
	public String toString(){
		String repr = "#ignore: " + ignore + "\n";
		for (char key: keySet()){
			for (Triple<Character, Character, String> values: super.get(key)){
				repr += values.getFirstValue() + " < " + key + " > " + values.getSecondValue() + ": " + values.getThirdValue() + "\n";
			}
		}
		return repr;
	}

}

