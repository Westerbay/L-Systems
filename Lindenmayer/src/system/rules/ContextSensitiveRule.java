package system.rules;


import java.util.HashMap;
import java.util.ArrayList;


/**
 * Context Sensitive L-System rules
 * @author Wester
 */
public class ContextSensitiveRule extends HashMap<Character, ArrayList<Triple<Character, Character, String>>> implements Rules {

	private final String _ignore;
	private String _defaultIgnore = "[]";
	private int _countBracketed;
	
	public ContextSensitiveRule(String ignore) {
		_ignore = ignore;
		_defaultIgnore += ignore;
		_countBracketed = 0;
	}

	private char leftNeighbor(int index, String string) {

		if (index == 0) { return '*'; }

		index --;
		char car = string.charAt(index);

		while (_countBracketed > 0 || _defaultIgnore.contains(String.valueOf(car))) {

			if (index == 0) {
				return '*';
			} else if (car == ']'){
				_countBracketed ++;
			} else if (car == '['){
				if (_countBracketed > 0)
					_countBracketed--;
			}

			index --;
			car = string.charAt(index);

		}
		
		_countBracketed = 0;
		return car;

	}
	
	
	private char rightNeighbor(int index, String string) {

		if (index == string.length() - 1) { return '*'; }

		index ++;
		char car = string.charAt(index);

		while (_countBracketed > 0 || _defaultIgnore.contains(String.valueOf(car))) {

			if (index == string.length() - 1) {
				return '*';
			} else if (car == '['){
				_countBracketed ++;
			} else if (car == ']'){
				if (_countBracketed > 0)
					_countBracketed--;
				else return '*';
			}

			index ++;
			car = string.charAt(index);

		}
		
		_countBracketed = 0;
		return car;
	}
	
	
	private boolean isLeft(int index, String string, char left) {
		return left == '*' || leftNeighbor(index, string) == left;
	}
	
	
	
	private boolean isRight(int index, String string, char right) {
		return right == '*' || rightNeighbor(index, string) == right;
	}
	
	
	
	private boolean between(int index, String string, char left, char right){
		return isLeft(index, string, left) && isRight(index, string, right);
	}
	
	
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
	public String get(int index, String string){
		char car = string.charAt(index);
		for (Triple<Character, Character, String> rule: super.get(car)){
			char left = rule.getFirstValue(), right = rule.getSecondValue();
			if (between(index, string, left, right))
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
		String repr = "#ignore: " + _ignore + "\n";
		for (char key: keySet()){
			for (Triple<Character, Character, String> values: super.get(key)){
				repr += values.getFirstValue() + " < " + key + " > " + values.getSecondValue() + ": " + values.getThirdValue() + "\n";
			}
		}
		return repr;
	}

}

