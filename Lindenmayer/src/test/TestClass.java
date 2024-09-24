package test;


public class TestClass {

    public static void main(String... args){

        TestSystem systemTest = new TestSystem();
        TestPile pileTest = new  TestPile();
        TestRules rulesTest = new  TestRules();
        TestSpace spaceTest = new TestSpace();

        systemTest.toutTestSystem();
        System.out.println("All tests System -> OK");
        pileTest.pileIsWorking();
        System.out.println("All tests Pile -> OK");
        rulesTest.toutTestRules();
        System.out.println("All tests Rules -> OK");
        spaceTest.toutTestSpace();
        System.out.println("All tests Space -> OK");


        System.out.println("All tests OK");

    }

}
