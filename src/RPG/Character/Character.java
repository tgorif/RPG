package RPG.Character;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.PerkTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Character {
    Logger LOGGER =Logger.getLogger(Character.class.getName());
    public Set<Perk> perks=new HashSet<>();
    public List<PerkTree> classes = new ArrayList<>();
    String name;
    public boolean isBlueTeam;
    public Character(String name){
        this.name=name;
    }
    public Character(CharacterPool.CharacterData characterData){
        this.name=characterData.getName();
        for (String s : characterData.getPerkTrees()){
            classes.add(PerkTree.getPerkTree(s));
        }
        for (String s : characterData.getPerks()){
            perks.add(Perk.getPerk(s));
        }
    }
    public void learn(PerkTree p){
        if(p==null) LOGGER.log(Level.SEVERE,"Character "  + name + " learned PerkTree with value null ");
        classes.add(p);
    }
    public void learn(Perk p){
        if(p==null) LOGGER.log(Level.SEVERE,"Character "  + name + " learned Perk with value null ");
        if(!getLearnablePerks().contains(p)) LOGGER.log(Level.SEVERE,"Character " + name + " learned unlearnable Perk");
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
