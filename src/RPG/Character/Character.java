package RPG.Character;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.PerkTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Character {
    Set<Perk> perks=new HashSet<>();
    List<PerkTree> classes = new ArrayList<>();
    String name;
    public boolean isBlueTeam;
    public Character(String name){
        this.name=name;
    }
    public void learn(PerkTree p){
        classes.add(p);
    }
    public void learn(Perk p){
        perks.add(p);
    }
    public List<Perk> getLearnablePerks(){
        List<Perk> result=new ArrayList<>();
        for (PerkTree tree :classes){
            for (Perk p : tree.getRoot()){
                if(!perks.contains(p)) result.add(p);
            }
            for(Perk perk : perks){
                for (Perk p : tree.getDescendants(perk)){
                    if(!perks.contains(p)) result.add(p);
                }
            }
        }
        return result;
    }
}
