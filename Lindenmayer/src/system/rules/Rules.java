package system.rules;


/**
 * Concept of L-System Rules
 * @author Wester
 */
public interface Rules {

    /**
     * Computes the mutation of a character into a String
     * @param index position in the string
     * @param string actual string
     * @return the new mutation
     */
    String get(int index, String string);

    boolean containsKey(char c);
    
    String toString();

}

