package RPG.Main;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LevelTest{
    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void testnotnull() {
        Level level=new Level(1,1);
        Assert.assertNotNull(level);
    }
    @Test
    public void testTileAmount(){
        int t=1;
        int outer=6;
        for(int i=1;i<201;i++) {
            if(i%2==1) {
                Level level = new Level(i, 1);
                Assert.assertEquals(t, level.tiles.size());
                t=t+outer;
                outer+=6;
            }
        }
    }
    @Test
    public void testTiles(){
        Level level = new Level(5, 1);
        for(int i=5/-2;i<6/2;i++){
            for(int j=5/-2;j<6/2;j++){
                for(int k=5/-2;k<6/2;k++){
                    if(i+j+k==0){
                        Assert.assertTrue(level.tiles.containsKey(List.of(i,j,k,0)));
                    }
                }
            }
        }
    }
    @Test
    public void testTilesinRange(){
        Level level = new Level(13, 1);
        Level.Tile base = level.tiles.get(List.of(-1,2,-1,0));
        Assert.assertEquals(1,level.getTilesInRange(base,0).size());
        Assert.assertEquals(7,level.getTilesInRange(base,1).size());
        Assert.assertEquals(19,level.getTilesInRange(base,2).size());
        Level level2 = new Level(3, 1);
        Level.Tile base2 = level.tiles.get(List.of(0,0,0,0));
        Assert.assertEquals(1,level2.getTilesInRange(base2,0).size());
        Assert.assertEquals(7,level2.getTilesInRange(base2,1).size());
        Assert.assertEquals(7,level2.getTilesInRange(base2,2).size());
    }
    @Test
    public void testDistance(){
        Level level = new Level(3, 1);
        Level.Tile base = level.tiles.get(List.of(0,0,0,0));
        Level.Tile other = level.tiles.get(List.of(1,-1,0,0));
        Level.Tile other2 = level.tiles.get(List.of(-1,1,0,0));
        Assert.assertEquals(1,level.getDistance(base,other));
        Assert.assertEquals(2,level.getDistance(other,other2));
    }
    @Test
    public void testPath(){
        Level level = new Level(3, 1);
        Level.Tile base = level.tiles.get(List.of(0,0,0,0));
        Level.Tile other = level.tiles.get(List.of(1,-1,0,0));
        Level.Tile other2 = level.tiles.get(List.of(-1,1,0,0));
        Assert.assertEquals(2,level.getPath(base,other).size());
        Assert.assertEquals(2,level.getPath(base,other2).size());
        Assert.assertEquals(3,level.getPath(other,other2).size());
    }
}