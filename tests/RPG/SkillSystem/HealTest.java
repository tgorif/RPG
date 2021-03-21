package RPG.SkillSystem;

import RPG.Character.Character;
import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Output.PreView;
import RPG.PerkSystem.Perk;
import RPG.StatusEffects.FactoryStatusEffect;
import RPG.StatusEffects.StatusDead;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HealTest {
    CombatCharacter testObject;
    StrategySkill skill;
    targetsCharacter targetSkill;
    CombatCharacter ally;
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
        list.get(0).learn(Perk.getPerk("Divine Healing"));
        GameState gameState=new GameState(list,new PreView());
        testObject=gameState.combatCharacterList.get(0);
        skill=testObject.characterSkillManager.skillList.get(0);
        targetSkill= ((targetsCharacter) skill);
        ally= gameState.getCombatCharacter("dummy3");
    }
    @Test
    public void hasHeal(){
        boolean contains=false;
        for(StrategySkill s : testObject.characterSkillManager.skillList){
            if(s instanceof SkillHeal) contains=true;
        }
        Assert.assertTrue(contains);
    }
    @Test
    public void canTargetAllies(){
        reset(testObject);
        reset(ally);
        for(CombatCharacter c :GameState.getInstance().getAllies(testObject)){
              if(!c.equals(testObject) &&c.characterInfo.isBlueTeam()==testObject.characterInfo.isBlueTeam()){
                  Assert.assertTrue(targetSkill.setTarget(c));
              }
        }
    }
    @Test
    public void canTargetSelf(){
        reset(testObject);
        Assert.assertTrue(targetSkill.setTarget(testObject));
    }
    @Test
    public void HealsHP(){
        reset(testObject);
        testObject.attributes.setHP(1);
        targetSkill.setTarget(testObject);
        skill.useSkill();
        Assert.assertTrue(testObject.attributes.getHP()>1);
    }
    @Test
    public void cannotTargetDead(){
        reset(testObject);
        reset(ally);
        ally.statusEffects.put("Dead", FactoryStatusEffect.getStatus("Dead",ally));
        Assert.assertFalse(targetSkill.setTarget(ally));
    }
    @Test
    public void cannotHealOverMaxHP(){
        reset(testObject);
        targetSkill.setTarget(testObject);
        skill.useSkill();
        Assert.assertTrue(testObject.attributes.getHP()==testObject.attributes.getMaxHP());
    }
    @Test
    public void cannotUseWhenOnCoolDown(){
        reset(testObject);
        targetSkill.setTarget(testObject);
        skill.useSkill();
        Assert.assertFalse(targetSkill.setTarget(testObject));
    }
    @Test
    public void cannotUseWithoutAP(){
        reset(testObject);
        testObject.attributes.setAP(0);
        Assert.assertFalse(targetSkill.setTarget(testObject));
    }
    public void reset(CombatCharacter c){
        c.attributes.turnStart();
        c.attributes.setHP(testObject.attributes.getMaxHP());
        c.statusEffects.remove("Dead");
        skill.lastUsed=Integer.MIN_VALUE;
    }
}
