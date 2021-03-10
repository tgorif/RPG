package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FactorySkillTest {
    static CombatCharacter dummy;
    @BeforeClass
    public static void setup(){
        dummy=new CombatCharacter();
    }

    @Test
    public void testInvalidInput(){
        Assert.assertNull(FactorySkill.getSkill("dsada",dummy));
    }
    @Test
    public void diffrentInstances(){
        Assert.assertTrue(!FactorySkill.getSkill("wait",dummy).equals(FactorySkill.getSkill("wait",dummy)));
    }
    @Test
    public void testWait(){
        Assert.assertTrue(FactorySkill.getSkill("wait",dummy) instanceof SkillWait);
    }
    @Test
    public void testSkipTurn(){
        Assert.assertTrue(FactorySkill.getSkill("skipturn",dummy) instanceof SkillWait);
    }
    @Test
    public void testmove(){
        Assert.assertTrue(FactorySkill.getSkill("move",dummy) instanceof SkillMove);
    }
    @Test
    public void testshotarrow(){
        Assert.assertTrue(FactorySkill.getSkill("shotarrow",dummy) instanceof SkillShot);
    }
    @Test
    public void testpreparation(){
        Assert.assertTrue(FactorySkill.getSkill("preparation",dummy) instanceof SkillShot);
    }
    @Test
    public void testquickshot(){
        Assert.assertTrue(FactorySkill.getSkill("quickshot",dummy) instanceof SkillShot);
    }



}