package RPG.SkillSystem;

import RPG.Character.Character;
import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Output.PreView;
import RPG.PerkSystem.Perk;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShotTest {
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
        list.get(0).learn(Perk.getPerk("Divine Healing"));
        GameState gameState=new GameState(list,new PreView());
        testObject=gameState.combatCharacterList.get(0);
        skill=testObject.characterSkillManager.skillList.get(0);
        targetSkill= ((targetsCharacter) skill);
        ally= gameState.getCombatCharacter("dummy3");
        enemy=gameState.getCombatCharacter("dummy2");
    }
    @Test
    public void hasHeal(){
        boolean contains=false;
        for(StrategySkill s : testObject.characterSkillManager.skillList){
            if(s instanceof SkillShot) contains=true;
        }
        Assert.assertTrue(contains);
    }
}
