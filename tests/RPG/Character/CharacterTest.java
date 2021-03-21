package RPG.Character;

import RPG.PerkSystem.Perk;
import RPG.PerkSystem.PerkTree;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import RPG.Main.init;

public class CharacterTest {

    @Test
    public void learnClass(){
        init.start();
        List<PerkTree> list = new ArrayList<>();
        list.add(PerkTree.getPerkTree("Base"));
        list.add(PerkTree.getPerkTree("Archer"));
        list.add(PerkTree.getPerkTree("Priest"));
        list.add(PerkTree.getPerkTree("Fighter"));
        for(PerkTree p : list){
            Character dummy=new Character("dummy");
            dummy.learn(p);
            Assert.assertTrue(dummy.classes.size()==1);
            Assert.assertTrue(dummy.perks.size()==0);
            Assert.assertTrue(dummy.classes.get(0).name.equals(p.name));
            Assert.assertTrue(dummy.getLearnablePerks().size()==p.getRoot().length);
            for (Perk perk : p.getRoot()){
                dummy.learn(perk);
            }
            Assert.assertTrue(dummy.perks.size()==p.getRoot().length);
            while (dummy.getLearnablePerks().size()>0){
                dummy.learn(dummy.getLearnablePerks().get(0));
            }
            Assert.assertTrue(dummy.perks.size()==p.getAll().size());
        }
    }
}
