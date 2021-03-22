package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import RPG.StatusEffects.FactoryStatusEffect;
import RPG.StatusEffects.StatusBleed;

import java.util.ArrayList;
import java.util.List;

public class SkillBuff extends StrategySkill implements targetsCharacter {
    CombatCharacter target;
    String status;
    int range;
    public SkillBuff(SkillData skillData, CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
        status=skillData.statusEffect;
        range=skillData.range;
    }

    @Override
    public void useSkill() {
        if(isValid()){
            target.statusEffects.put(status, FactoryStatusEffect.getStatus(status,target));
            usedSkill();
        }
    }

    @Override
    public boolean isValid() {
        return canUse()
                &&target!=null
                && !target.statusEffects.containsKey("Dead")
                && Level.getCurrentLevel().getDistance(caster.characterInfo.getTile(),
                target.characterInfo.getTile())<=range;
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
