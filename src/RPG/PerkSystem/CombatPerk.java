package RPG.PerkSystem;

import RPG.PerkSystem.Perk;
import RPG.SkillSystem.StrategySkill;

public class CombatPerk extends Perk {
    StrategySkill skill;

    public CombatPerk(String name, StrategySkill skill){
        super(name);
        this.skill=skill;
    }
    public StrategySkill getSkill(){
        return skill;
    }
}
