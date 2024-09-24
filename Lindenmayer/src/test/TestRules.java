package test;

import system.rules.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestRules{

  public TestRules(){}

  //StochasticRules
  StochasticRules rule = new StochasticRules();
  Pair<Float,String> rules1 = new Pair<>(0.06f,"ta");
  Pair<Float,String> rules2 = new Pair<>(0.04f,"8/*");
  Pair<Float,String> rules3 = new Pair<>(0.8f,"t");
  Pair<Float,String> rules4 = new Pair<>(0.1f,"tata8/*");

  //ContextSensitiveRule
  ContextSensitiveRule contextRule = new ContextSensitiveRule("87");

  //ConstructRules
  String[] stochastic = {"t","t:0.5:to","t:0.3:ta","t:0.2:t"};
  String[] context = {
    "F1F1F1",
    "0 < 0 > 0: 0",
    "0 < 0 > 1: 1[-F1F1]",
    "0 < 1 > 0: 1",
    "0 < 1 > 1: 1",
    "1 < 0 > 0: 0",
    "1 < 0 > 1: 1F1",
    "1 < 1 > 0: 1",
    "1 < 1 > 1: 0",
    "* < + > *: -",
    "* < - > *: +"
  };
  String[] simple = {"P","P: I + [ P + r] − − // [ − − l ] I [++ l ] − [ P r ]++ P r","I: F s [// & & l ] [// ∧ ∧ l ] F s","s: s F s","l: [’ { +f−ff−f+ | +f−ff−f } ]","r: [&&& p ‘ / w //// w //// w //// w //// w ] ","p: FF","w: [‘ ∧ F][ { &&&& −f+f | −f+f } ]"};
  String ignore = "#ignore: +-F";

  /**
  * determine whether two numbers are "approximately equal" by seeing if they
  * are within a certain "tolerance percentage," with `tolerancePercentage` given
  * as a percentage (such as 10.0 meaning "10%").
  *
  * @param tolerancePercentage 1 = 1%, 2.5 = 2.5%, etc.
  * code pris sur internet
  * https://alvinalexander.com/source-code/java-approximately-equals-method-function
  */
  public static boolean approximatelyEqual(float desiredValue, float actualValue, float tolerancePercentage) {
    float diff = Math.abs(desiredValue - actualValue);         //  1000 - 950  = 50
    float tolerance = tolerancePercentage/100 * desiredValue;  //  20/100*1000 = 200
    return diff < tolerance;                                   //  50<200      = true
  }

  /**
  *test sur les StochasticRules Put
  */
  @Test
  private void test_StochasticRulesPut(){
    rule.put('t',0.8f,"tata8/*");
    rule.put('t',0.2f,"t");

    rule.put('a',rules1);
    rule.put('a',rules2);
    rule.put('a',rules3);
    rule.put('a',rules4);

    rule.put('8',rules3);
    rule.put('8',rules4);
    rule.put('8',rules4);

    rule.put('/',rules3);
    rule.put('/',0.2f,"t");
    rule.put('*',1f,"*");

    assertTrue(rule.containsKey('t'));
    assertTrue(rule.containsKey('8'));
    assertTrue(rule.containsKey('/'));
    assertTrue(rule.containsKey('*'));
    assertTrue(rule.containsKey('a'));
    assertFalse(rule.containsKey('µ'));
    assertFalse(rule.containsKey('o'));
    assertFalse(rule.containsKey('9'));
    assertFalse(rule.containsKey('@'));
    assertFalse(rule.containsKey('='));
  }

  /**
  *test sur les StochasticRules Get
  */
  @Test
  private void test_StochasticRulesGet(){
    assertNull(rule.get(0,"g"));
    assertNull(rule.get(-1));
    assertNull(rule.get(6));

    float probat1 = 0;
    float probat2 = 0;
    float probaa1 = 0;
    float probaa2 = 0;
    float probaa3 = 0;
    float probaa4 = 0;
    float proba8 = 0;
    float probaEt = 0;
    for(int i=0;i<10000;i++){
      if("tata8/*".equals(rule.get(0,"tatatdvfhata8/*µ")))
        probat1 ++;
      if("t".equals(rule.get(2,"tatatdvfhata8/*µ")))
        probat2 ++;
      if("ta".equals(rule.get(1,"tatatdvfhata8/*µ")))
        probaa1 ++;
      if("8/*".equals(rule.get(1,"tatatdvfhata8/*µ")))
        probaa2 ++;
      if("t".equals(rule.get(1,"tatatdvfhata8/*µ")))
        probaa3 ++;
      if("tata8/*".equals(rule.get(1,"tatatdvfhata8/*µ")))
        probaa4 ++;
      if("tata8/*".equals(rule.get(3,"taa8/*µ")))
        proba8 ++;
      if("*".equals(rule.get(4,"tata*µ")))
        probaEt ++;
    }
    assertTrue(approximatelyEqual(8000f,probat1,10f));
    assertTrue(approximatelyEqual(2000f,probat2,10f));
    assertTrue(approximatelyEqual(600f,probaa1,10f));
    assertTrue(approximatelyEqual(400f,probaa2,10f));
    assertTrue(approximatelyEqual(8000f,probaa3,10f));
    assertTrue(approximatelyEqual(1000f,probaa4,10f));
    assertTrue(approximatelyEqual(2000f,proba8,10f));
    assertTrue(approximatelyEqual(10000f,probaEt,10f));
  }


  /**
  *test sur les ContextSensitiveRule put
  */
  @Test
  private void test_ContextSensitiveRule_Put(){
    contextRule.put('t','a','t',"ta");
    contextRule.put('t','a','8',"t8");
    contextRule.put('a','t','a',"t");
    contextRule.put('t','8','t',"8");
    contextRule.put('t','/','t',"8/*");
    contextRule.put('/','*',' ',"ta");
    contextRule.put('/','c','a',"ta");

    assertTrue(contextRule.containsKey('t'));
    assertTrue(contextRule.containsKey('8'));
    assertTrue(contextRule.containsKey('/'));
    assertTrue(contextRule.containsKey('*'));
    assertTrue(contextRule.containsKey('a'));
    assertFalse(contextRule.containsKey('µ'));
    assertFalse(contextRule.containsKey('o'));
    assertFalse(contextRule.containsKey('9'));
    assertFalse(contextRule.containsKey('@'));
    assertFalse(contextRule.containsKey('='));
  }

  /**
  *test sur les ContextSensitiveRule get
  */
  @Test
  private void test_ContextSensitiveRule_Get(){
    assertNull(contextRule.get(0));
    assertNull(contextRule.get(-1));
    assertNull(contextRule.get(6));
    assertEquals("t",contextRule.get(0,"tac vbd"));
    assertEquals("a",contextRule.get(1,"tac vbd"));
    assertEquals("c",contextRule.get(2,"tac8/*"));
    assertEquals("8",contextRule.get(3,"tac8/*"));
    assertEquals("/",contextRule.get(4,"tac8/*"));
    assertEquals("*",contextRule.get(5,"tac8/*"));
  }

  /**
  * test create Rules
  */
  @Test
  private void test_createRules(){
    assertEquals("StochasticRules",ConstructRules.createRulesStochastic(stochastic[3]).getClass().getSimpleName());
    assertEquals("ContextSensitiveRule",ConstructRules.createRulesContext(ignore, context[10]).getClass().getSimpleName());
    //assertEquals("classicRules",ConstructRules.createRules(simple[7]).getClass().getSimpleName());
  }




  /**
  *test appelent tout les tests de la class
  */
  @Test
  public void toutTestRules(){
    test_StochasticRulesPut();
    test_StochasticRulesGet();
    test_ContextSensitiveRule_Put();
    test_ContextSensitiveRule_Get();
    test_createRules();
    System.out.println(" A terminer plus tard test_createRules");
  }

}
