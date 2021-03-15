package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;

import java.util.List;

public class SkillEndTurn extends StrategySkill{


    public SkillEndTurn(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
    }

    @Override
    public void useSkill() {
        caster.attributes.changeAP(-caster.attributes.getAP());
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
