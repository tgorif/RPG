package RPG.Character;

import RPG.PerkSystem.CombatPerk;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.StatPerk;
import RPG.SkillSystem.FactorySkill;
import RPG.SkillSystem.StrategySkill;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CharacterSkillManager {
    private final Logger LOGGER=Logger.getLogger(CharacterSkillManager.class.getName());
    public List<StrategySkill> skillList=new ArrayList<>();
    public List<StatPerk> preCombatPerks=new ArrayList<>();

    public CharacterSkillManager(){

    }
    private CharacterSkillManager(CharacterSkillManager original){
        for(StrategySkill s : original.skillList){
            this.skillList.add(FactorySkill.getSkill(s.skillName));
        }
        for(StatPerk s : original.preCombatPerks){
            this.preCombatPerks.add(s);
        }
    }
    public CharacterSkillManager clone(){
        return new CharacterSkillManager(this);
    }
    public void setSkills(Set<Perk> perks){
        LOGGER.log(Level.FINE,"setting Perks with list of size " +perks.size());
        for (Perk perk : perks){
            if(perk==null) LOGGER.log(Level.SEVERE,"Perk==NUll");
            else if(perk.name==null) LOGGER.log(Level.WARNING,"PerkName==null");
            if(perk instanceof StatPerk){
                preCombatPerks.add(((StatPerk) perk));
            }
            else if(perk instanceof CombatPerk){
                skillList.add(FactorySkill.getSkill(perk.name));
                LOGGER.log(Level.FINE,"Added Perk " + perk.name);
            }
        }
    }

}
