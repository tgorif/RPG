package RPG.Main;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LevelTest extends TestCase {
    Level level;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        level=new Level();
        testValidLevelPosition();
        checkSamePosition();
        getPositionsInRange();
    }
    @Test
    public void testValidLevelPosition(){
        System.out.println("ValidPositionTest");
        for (int i=-100;i<2000;i++){
            if(i<0 || i>1000) Assert.assertFalse(level.isValid(i,i,i));
            else Assert.assertTrue(level.isValid(i,i,i));
        }
        Position position=new Position(999,999,999);
        Assert.assertTrue(level.isValid(position,1,1,1));
        Assert.assertFalse(level.isValid(position,2,0,0));
        Assert.assertFalse(level.isValid(position,0,2,0));
        Assert.assertFalse(level.isValid(position,0,0,2));
        Assert.assertTrue(level.isValid(position,-999,0,0));
        Assert.assertTrue(level.isValid(position,0,-999,0));
        Assert.assertTrue(level.isValid(position,0,0,-999));
        Assert.assertTrue(level.isValid(position,-999,-999,-999));
        Position invalid = new Position(-5,-5,-5);
        Position invalid2 = new Position(1001,1001,1001);
        Assert.assertFalse(level.isValid(invalid));
        Assert.assertFalse(level.isValid(invalid2));
        Assert.assertTrue(level.isValid(position));

    }
    @Test
    public void checkSamePosition(){
        System.out.println("SamePositionTest");
        Position a= new Position(0,0,0);
        Position b= new Position(1,1,1);
        Position c= new Position(0,0,0);
        Position d=new Position(0,1,0);
        Assert.assertFalse(level.isEqualPosition(a,b));
        Assert.assertFalse(level.isEqualPosition(b,c));
        Assert.assertTrue(level.isEqualPosition(a,c));
        Assert.assertFalse(level.isEqualPosition(a,d));
        Assert.assertFalse(level.isEqualPosition(b,d));
        Assert.assertFalse(level.isEqualPosition(c,d));
    }
    @Test
    public void getPositionsInRange(){
        System.out.println("PositionInRange: ");
        Position minCorner= new Position(0,0,0);
        Position middle= new Position(200,200,200);
        Position maxCorner= new Position(1000,1000,1000);
        Position edge=new Position(0,200,200);
        Assert.assertTrue(level.getPositionsInRange(middle,10).size()==6);
        Assert.assertTrue(level.getPositionsInRange(minCorner,10).size()==3);
        Assert.assertTrue(level.getPositionsInRange(maxCorner,10).size()==3);
        Assert.assertTrue(level.getPositionsInRange(edge,10).size()==5);
        for (Position cur : level.getPositionsInRange(middle,10)){
            Assert.assertFalse(level.isEqualPosition(middle,cur));
        }
    }


}