package RPG.Character;

import RPG.PerkSystem.Perk;
import RPG.PerkSystem.StatPerk;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CharacterSkillManager {
    public List<String> skillList=new ArrayList<>();
    public List<Perk> preCombatPerks=new ArrayList<>();

    public CharacterSkillManager(){

    }
    public void setSkills(Set<Perk> perks){
        for (Perk perk : perks){
            if(perk instanceof StatPerk){
                preCombatPerks.add(perk);
            }
            else{
                skillList.add(perk.name);
            }
        }
    }
}
