package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.StatusEffects.FactoryStatusEffect;
import RPG.StatusEffects.StatusBleed;

import java.util.ArrayList;
import java.util.List;

public class SkillHealOverTime extends StrategySkill implements targetsCharacter {
    CombatCharacter target;
    String status;
    public SkillHealOverTime(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
        status=skillData.statusEffect;
    }

    @Override
    public void useSkill() {
        target.statusEffects.put(status, FactoryStatusEffect.getStatus(status,target));
    }

    @Override
    public boolean isValid() {
        return target!=null
                &&!caster.statusEffects.containsKey("Dead")
                &&!target.statusEffects.containsKey("Dead")
                &&GameState.getInstance().turnCounter-lastUsed<cooldown;
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
