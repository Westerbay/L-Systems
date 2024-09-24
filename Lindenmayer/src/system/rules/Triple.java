package system.rules;


/**
 * Classe représentant une structure de données de pairs
 * @author dubuiss212
 */
public class Triple<A, B, C> {

    private final A firstValue;
    private final B secondValue;
    private final C thirdValue;

    /**
     * Constructeur
     * @param a firstValue
     * @param b secondValue
     * @param c thirdValue
     */
    public Triple(A a, B b, C c){
        firstValue = a;
        secondValue = b;
        thirdValue = c;
    }

    /**
     * Accesseur firstValue
     * @return l'attribut firstValue
     */
    public A getFirstValue(){
        return firstValue;
    }

    /**
     * Accesseur secondValue
     * @return l'attribut secondValue
     */
    public B getSecondValue(){
        return secondValue;
    }
    
    /**
     * Accesseur thirdValue
     * @return l'attribut thirdValue
     */
    public C getThirdValue(){
        return thirdValue;
    }

}

