package RPG.SkillSystem;

import RPG.Character.Character;
import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import RPG.Output.PreView;
import RPG.PerkSystem.Perk;
import RPG.StatusEffects.StrategyStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuffTest {
    CombatCharacter testObject;
    StrategySkill skill;
    targetsCharacter targetSkill;
    CombatCharacter ally;
    CombatCharacter enemy;
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
        list.get(0).learn(Perk.getPerk("Sealed Fate"));
        GameState gameState=new GameState(list,new PreView());
        testObject=gameState.combatCharacterList.get(0);
        skill=testObject.characterSkillManager.skillList.get(0);
        targetSkill= ((targetsCharacter) skill);
        ally= gameState.getCombatCharacter("dummy3");
        enemy=gameState.getCombatCharacter("dummy2");
    }
    @Test
    public void hasSkill(){
        boolean contains=false;
        for(StrategySkill s : testObject.characterSkillManager.skillList){
            if(s instanceof SkillBuff) contains=true;
        }
        Assert.assertTrue(contains);
    }
    @Test
    public void cannotUseOnCooldown(){
        reset(testObject);
        reset(enemy);
        targetSkill.setTarget(enemy);
        skill.useSkill();
        Assert.assertFalse(targetSkill.setTarget(testObject));
    }
    @Test
    public void cannottUseWhenOutofRange(){
        reset(testObject);
        reset(ally);
        ally.characterInfo.setTile(Level.getCurrentLevel().tiles.get(List.of(33,-33,0,0)));
        Assert.assertFalse(targetSkill.setTarget(ally));
    }
    @Test
    public void cannotUseWithoutAP(){
        reset(testObject);
        testObject.attributes.setAP(0);
        Assert.assertFalse(targetSkill.setTarget(testObject));
    }
    @Test
    public void canTargetSelf(){
        reset(testObject);
        Assert.assertTrue(targetSkill.setTarget(testObject));
    }
    @Test
    public void canTargetEnemy(){
        reset(testObject);
        reset(enemy);
        Assert.assertTrue(targetSkill.setTarget(enemy));
    }
    @Test
    public void canTargetAlly(){
        reset(testObject);
        reset(ally);
        Assert.assertTrue(targetSkill.setTarget(ally));
    }
    @Test
    public void useSkill(){
        reset(testObject);
        reset(ally);
        targetSkill.setTarget(testObject);
        skill.useSkill();
        Assert.assertTrue(testObject.statusEffects.size()==1);
    }
    public void reset(CombatCharacter c){
        c.attributes.turnStart();
        c.attributes.setHP(testObject.attributes.getMaxHP());
        List<String> r=new ArrayList<>();
        for(Map.Entry<String, StrategyStatus> e : c.statusEffects.entrySet()){
            r.add(e.getKey());
        }
        for (String s : r) c.statusEffects.remove(s);
        skill.lastUsed=Integer.MIN_VALUE;
        c.characterInfo.setTile(Level.getCurrentLevel().tiles.get(List.of(0,0,0,0)));
    }
}
