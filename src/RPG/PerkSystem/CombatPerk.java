package RPG.PerkSystem;

import RPG.PerkSystem.Perk;
import RPG.SkillSystem.FactorySkill;
import RPG.SkillSystem.StrategySkill;

public class CombatPerk extends Perk {
    String skill;
    public CombatPerk(String name, String skill){
        super(name);
        this.skill=skill;
    }
}
