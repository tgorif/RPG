package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import java.util.ArrayList;
import java.util.List;

public class SkillShot extends StrategySkill implements targetsCharacter{
    int range;
    int damage;
    CombatCharacter target;
    public SkillShot(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
        this.range=skillData.range;
        this.damage=skillData.damage;
    }
    @Override
    public void useSkill() {
        if(isValid()) {
            target.actionManager.resolveHit(damage);
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
        for (CombatCharacter i : GameState.getInstance().getHostiles(caster)){
            target=i;
            if(isValid()) result.add(i);
        }
        return result;
    }

}
