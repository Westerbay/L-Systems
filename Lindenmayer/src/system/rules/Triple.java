package system.rules;


/**
 * Triple 
 * @author Wester
 */
public class Triple<A, B, C> {

    private final A _firstValue;
    private final B _secondValue;
    private final C _thirdValue;
    
    public Triple(A firstValue, B secondValue, C thirdValue) {
        _firstValue = firstValue;
        _secondValue = secondValue;
        _thirdValue = thirdValue;
    }

    public A getFirstValue() {
        return _firstValue;
    }

    public B getSecondValue() {
        return _secondValue;
    }
    
    public C getThirdValue() {
        return _thirdValue;
    }

}

