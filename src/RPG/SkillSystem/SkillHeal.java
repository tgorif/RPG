package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;

import java.util.ArrayList;
import java.util.List;

public class SkillHeal extends StrategySkill implements targetsCharacter{
    CombatCharacter target;
    int restoration;
    public SkillHeal(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData, combatCharacter);
        restoration=skillData.damage;
    }

    @Override
    public void useSkill() {
        target.attributes.changeHP(restoration);
        usedSkill();
    }
    @Override
    public boolean isValid() {
        return canUse()
                &&target!=null
                && !target.statusEffects.containsKey("Dead");
    }
    @Override
    public boolean setTarget(CombatCharacter combatCharacter) {
        target=combatCharacter;
        return isValid();
    }
    @Override
    public List<CombatCharacter> getTargets() {
        List<CombatCharacter> result=new ArrayList<>();
        for(CombatCharacter c : GameState.getInstance().getAllies(caster)){
            target=c;
            if(isValid()) result.add(c);
        }
        return result;
    }
}
