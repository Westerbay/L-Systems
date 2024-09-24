package test;


import org.junit.Test;
import system.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;



public class TestSystem {

    public TestSystem(){}


    /**
    * test les getGenerations
    */
    @Test
    private void test_getGeneration(){
        //Test 1
        SystemL systemtestDeux = ConstructSystem.createSystem(
            10,
            "A",
            "A: AC",
            "C: A"
        );
        assertEquals("A",systemtestDeux.getGeneration(0));
        assertEquals("ACA",systemtestDeux.getGeneration(2));
        assertEquals("ACAACACAACAAC",systemtestDeux.getGeneration(5));
        //System.out.println(systemtestDeux.getGeneration());

        //Test 2
        Map<Integer, String> testgenerationDeux = new HashMap<>();
        testgenerationDeux.put(0,"A");
        SystemL systemtest = ConstructSystem.createSystem(
            10,
            "A",
            "A: ABA",
            "B: CA"
        );

        assertEquals(testgenerationDeux.get(0),systemtest.getGeneration(0));
    }


  @Test
  public void toutTestSystem(){
    test_getGeneration();
  }

}
