package RPG.SkillSystem;
import RPG.Character.CombatCharacter;
import RPG.Main.GameState;

import java.util.List;

public class SkillWait extends StrategySkill {


    public SkillWait(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
    }


    @Override
    public void useSkill() {
        caster.attributes.changeAP(-cost);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
