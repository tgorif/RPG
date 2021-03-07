package RPG.Character;

import RPG.PerkSystem.Perk;
import RPG.PerkSystem.StatPerk;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CharacterSkillManager {
    private Logger LOGGER=Logger.getLogger(CharacterSkillManager.class.getName());
    public List<String> skillList=new ArrayList<>();
    public List<Perk> preCombatPerks=new ArrayList<>();

    public CharacterSkillManager(){

    }
    public void setSkills(Set<Perk> perks){
        LOGGER.log(Level.FINE,"setting Perks with list of size " +perks.size());
        for (Perk perk : perks){
            if(perk instanceof StatPerk){
                preCombatPerks.add(perk);
            }
            else{
                if(perk==null) LOGGER.log(Level.WARNING,"Perk==NUll");
                else if(perk.name==null) LOGGER.log(Level.WARNING,"PerkName==null");
                else{
                    skillList.add(perk.name);
                    LOGGER.log(Level.FINE,"Added Perk " + perk.name);
                }
            }
        }
    }
}
