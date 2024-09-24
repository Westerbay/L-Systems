package test; 

import org.junit.Test;
import turtle.space.*;

import static org.junit.Assert.*;

public class TestPile{

    public TestPile(){}

    @Test
    public void pileIsWorking(){
        //Test1
        Pile pileUn = new Pile();
        assertTrue(pileUn.isEmpty());
        Position positionUn = new Position(new double[] {1, 1},10.0);
        pileUn.empile(positionUn);
        assertFalse(pileUn.isEmpty());
        assertEquals(positionUn,pileUn.depile());
        assertTrue(pileUn.isEmpty());

        //Test2
        Pile pileDeux = new Pile();
        Position positionDeux = new Position(new double[] {23,76},98.5);
        pileDeux.empile(positionUn);
        pileDeux.empile(positionDeux);
        assertEquals(positionDeux,pileDeux.depile());
        assertFalse(pileDeux.isEmpty());
        
    }
}
