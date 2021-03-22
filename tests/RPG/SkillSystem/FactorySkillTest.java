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
        RPG.Main.init.start();
    }

    @Test
    public void testInvalidInput(){
        Assert.assertNull(FactorySkill.getSkill("dsada",dummy));
    }
    @Test
    public void diffrentInstances(){
        Assert.assertTrue(!FactorySkill.getSkill("Wait",dummy).equals(FactorySkill.getSkill("Wait",dummy)));
    }
    @Test
    public void testWait(){
        Assert.assertTrue(FactorySkill.getSkill("Wait",dummy) instanceof SkillWait);
    }
    @Test
    public void testmove(){
        Assert.assertTrue(FactorySkill.getSkill("Move",dummy) instanceof SkillMove);
    }
    @Test
    public void testshotarrow(){
        Assert.assertTrue(FactorySkill.getSkill("Shot Arrow",dummy) instanceof SkillShot);
    }
    @Test
    public void testpreparation(){
        Assert.assertTrue(FactorySkill.getSkill("Prepared Shot",dummy) instanceof SkillShot);
    }
    @Test
    public void testquickshot(){
        Assert.assertTrue(FactorySkill.getSkill("Quickshot",dummy) instanceof SkillShot);
    }



}