import java.util.ArrayList;
import java.util.List;

public class ConcretePerkTree {
    final PerkTree perkTree;
    List<Perk> learnedPerks=new ArrayList<>();
    public ConcretePerkTree(PerkTree perkTree) {
        this.perkTree = perkTree;
    }
    public void learnAll(){
        learnedPerks.addAll(perkTree.getAll());
    }
}
