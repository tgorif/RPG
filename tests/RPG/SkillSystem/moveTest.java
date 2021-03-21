package RPG.SkillSystem;

import RPG.Character.Character;
import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import RPG.Output.PreView;
import RPG.PerkSystem.Perk;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class moveTest {
    CombatCharacter testObject;
    StrategySkill skill;
    targetsTile tileSkill;
    @Before
    public void setup(){
        RPG.Main.init.start();
        List<Character> list = new ArrayList<>();
        Character dummy= new Character("dummy");
        Character dummy2= new Character("dummy2");
        Character dummy3 =new Character("dummy3");
        dummy.isBlueTeam=true;
        dummy2.isBlueTeam=false;
        dummy3.isBlueTeam=true;
        dummy.learn(Perk.getPerk("Base Stats"));
        dummy2.learn(Perk.getPerk("Base Stats"));
        dummy3.learn(Perk.getPerk("Base Stats"));
        list.add(dummy);
        list.add(dummy2);
        list.add(dummy3);
        list.get(0).learn(Perk.getPerk("Move"));
        GameState gameState=new GameState(list,new PreView());
        testObject=gameState.combatCharacterList.get(0);
        skill=testObject.characterSkillManager.skillList.get(0);
        tileSkill= ((targetsTile) skill);
    }
    @Test
    public void hasMove(){
        boolean containsMove=false;
        for(StrategySkill s : testObject.characterSkillManager.skillList){
            if(s instanceof SkillMove) containsMove=true;
        }
        Assert.assertTrue(containsMove);
    }
    @Test
    public void hasTilesInRange(){
        Assert.assertTrue( ((targetsTile) testObject.characterSkillManager.skillList.get(0)).getTargets().size()>0);
    }
    @Test
    public void AllTilesinRangeareInRange(){
        List<Level.Tile> list =((targetsTile) testObject.characterSkillManager.skillList.get(0)).getTargets();
        testObject.attributes.turnStart();
        for (Level.Tile tile : list){
            Assert.assertTrue(tileSkill.setTarget(tile));
        }
    }
    @Test
    public void containsDifferentTiles(){
        int count=0;
        for (Level.Tile testTile : tileSkill.getTargets()){
            if(!testTile.equals(testObject.characterInfo.getTile())) count++;
        }
        Assert.assertTrue(count>0);
    }
    @Test
    public void cantMoveWithoutAP(){
        testObject.attributes.changeAP(-testObject.attributes.getAP());
        for (Level.Tile testTile :tileSkill.getTargets()){
            Assert.assertFalse(tileSkill.setTarget(testTile));
        }
    }
    @Test
    public void cantMovetoTilesOutOfRange(){
        testObject.attributes.turnStart();
        Level.Tile tofar=Level.getCurrentLevel().tiles.get(List.of(33,-33,0,0));
        System.out.println(Level.getCurrentLevel().getDistance(testObject.characterInfo.getTile(),tofar));
        Assert.assertFalse(tileSkill.setTarget(tofar));
    }
    @Test
    public void MoveTest(){
        testObject.attributes.turnStart();
        while (testObject.attributes.getAP()>0){
            List<Level.Tile> targets=tileSkill.getTargets();
            Collections.shuffle(targets);
            tileSkill.setTarget(targets.get(0));
            int currentAp=testObject.attributes.getAP();
            Level.Tile currentTile = testObject.characterInfo.getTile();
            skill.useSkill();
            Assert.assertTrue(currentAp>testObject.attributes.getAP());
            Assert.assertTrue(!currentTile.equals(testObject.characterInfo.getTile()));
        }
    }
}
