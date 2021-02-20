import java.util.ArrayList;
import java.util.List;

public class Class {
        List<Perk> perks;
        final PerkTree perkTree;
        String name;
        public Class(String name){
            perks=new ArrayList<>();
            this.name=name;
            perkTree =PerkTree.getPerkTree(name);
        }
}
