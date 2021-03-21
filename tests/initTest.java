import RPG.Main.init;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.PerkTree;
import RPG.SkillSystem.SkillData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class initTest {


    @Before
    public void before(){
        init.start();
    }
    @Test
    public void testClassImports(){
        Assert.assertNotNull(PerkTree.getPerkTree("Base"));
        Assert.assertNotNull(PerkTree.getPerkTree("Archer"));
        Assert.assertNotNull(PerkTree.getPerkTree("Priest"));
        Assert.assertNotNull(PerkTree.getPerkTree("Fighter"));
    }
    @Test
    public void testPerkImports() {
        Assert.assertNotNull(Perk.getPerk("Base Stats"));
        Assert.assertNotNull(Perk.getPerk("Divine Healing"));
        Assert.assertNotNull(Perk.getPerk("Divine Intervention"));
        Assert.assertNotNull(Perk.getPerk("Holy Fire"));
        Assert.assertNotNull(Perk.getPerk("Move"));
        Assert.assertNotNull(Perk.getPerk("Prepared Shot"));
        Assert.assertNotNull(Perk.getPerk("Quickshot"));
        Assert.assertNotNull(Perk.getPerk("Revitalising Spirits"));
        Assert.assertNotNull(Perk.getPerk("Sealed Fate"));
        Assert.assertNotNull(Perk.getPerk("Shot Arrow"));
        Assert.assertNotNull(Perk.getPerk("Skip Turn"));
        Assert.assertNotNull(Perk.getPerk("Wait"));
        Assert.assertNotNull(Perk.getPerk("BonusSPD10"));
        Assert.assertNotNull(Perk.getPerk("Basic Attack"));
        Assert.assertNotNull(Perk.getPerk("Sword Attack"));
        Assert.assertNotNull(Perk.getPerk("Shield Bash"));
        Assert.assertNotNull(Perk.getPerk("Provoke"));
        Assert.assertNotNull(Perk.getPerk("Platemail"));
        Assert.assertNotNull(Perk.getPerk("Tower Shield"));
    }
    @Test
    public void testSkillImports(){
        Assert.assertNotNull(SkillData.get("Divine Healing"));
        Assert.assertNotNull(SkillData.get("Divine Intervention"));
        Assert.assertNotNull(SkillData.get("Holy Fire"));
        Assert.assertNotNull(SkillData.get("Move"));
        Assert.assertNotNull(SkillData.get("Prepared Shot"));
        Assert.assertNotNull(SkillData.get("Quickshot"));
        Assert.assertNotNull(SkillData.get("Revitalising Spirits"));
        Assert.assertNotNull(SkillData.get("Sealed Fate"));
        Assert.assertNotNull(SkillData.get("Shot Arrow"));
        Assert.assertNotNull(SkillData.get("Skip Turn"));
        Assert.assertNotNull(SkillData.get("Wait"));
        Assert.assertNotNull(SkillData.get("Basic Attack"));
        Assert.assertNotNull(SkillData.get("Sword Attack"));
        Assert.assertNotNull(SkillData.get("Shield Bash"));
        Assert.assertNotNull(SkillData.get("Provoke"));
    }
}