package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;

import java.util.ArrayList;
import java.util.List;

public class SkillRevive extends StrategySkill implements targetsCharacter{
    int reviveHP;
    CombatCharacter target;
    public SkillRevive(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData, combatCharacter);
        this.reviveHP=skillData.reviveHP;
    }

    @Override
    public void useSkill() {
        target.statusEffects.remove("Dead");
        target.attributes.setHP(target.attributes.getMaxHP()*reviveHP/100);
        caster.attributes.changeAP(-cost);
    }

    @Override
    public boolean isValid() {
        return target!=null
                &&!caster.statusEffects.containsKey("Dead")
                &&target.statusEffects.containsKey("Dead")
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
