package RPG.SkillSystem;

import org.junit.Assert;
import org.junit.Test;

public class FactorySkillTest {

    @Test
    public void testInvalidInput(){
        Assert.assertNull(FactorySkill.getSkill("dsada",null));
    }
    @Test
    public void diffrentInstances(){
        Assert.assertTrue(!FactorySkill.getSkill("wait").equals(FactorySkill.getSkill("wait")));
    }
    @Test
    public void testWait(){
        Assert.assertTrue(FactorySkill.getSkill("wait") instanceof SkillWait);
    }
    @Test
    public void testSkipTurn(){
        Assert.assertTrue(FactorySkill.getSkill("skipturn") instanceof SkillWait);
    }
    @Test
    public void testmove(){
        Assert.assertTrue(FactorySkill.getSkill("move") instanceof SkillMove);
    }
    @Test
    public void testshotarrow(){
        Assert.assertTrue(FactorySkill.getSkill("shotarrow") instanceof SkillShot);
    }
    @Test
    public void testpreparation(){
        Assert.assertTrue(FactorySkill.getSkill("preparation") instanceof SkillShot);
    }
    @Test
    public void testquickshot(){
        Assert.assertTrue(FactorySkill.getSkill("quickshot") instanceof SkillShot);
    }



}