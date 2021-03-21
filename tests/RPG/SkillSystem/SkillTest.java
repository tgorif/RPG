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
import java.util.List;

public class SkillTest {

    public List<Character> setup(){
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
        return list;
    }
    @Test
    public void testMove(){
        List<Character> list= setup();
        list.get(0).learn(Perk.getPerk("Move"));
        GameState gameState=new GameState(list,new PreView());
        for (CombatCharacter c : gameState.combatCharacterList){
            if(c.characterSkillManager.skillList.size()>0) {
                Level.Tile t = c.characterInfo.getTile();
                int startAP=c.attributes.getAP();
                c.characterSkillManager.skillList.get(0).useSkill();
                Assert.assertTrue(!t.equals(c.characterInfo.getTile()));
                Assert.assertTrue(c.attributes.getAP()!=startAP);
                Assert.assertTrue(Level.getCurrentLevel().getDistance(t,c.characterInfo.getTile())<=c.attributes.getMovement());
            }
        }
    }
    @Test
    public void testshotAttow(){
        List<Character> list= setup();
        list.get(0).learn(Perk.getPerk("Shot Arrow"));
        GameState gameState=new GameState(list,new PreView());
        for (CombatCharacter c : gameState.combatCharacterList){
            if(c.characterSkillManager.skillList.size()>0) {
                int startAP=c.attributes.getAP();
                int enemyHP=gameState.getHostiles(c).get(0).attributes.getHP();
                c.characterSkillManager.skillList.get(0).useSkill();
                Assert.assertTrue(c.attributes.getAP()!=startAP);
                Assert.assertTrue(enemyHP!=gameState.getHostiles(c).get(0).attributes.getHP());
            }
        }
    }
}
