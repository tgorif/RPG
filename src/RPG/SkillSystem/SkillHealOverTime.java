package RPG.SkillSystem;

import RPG.Character.CombatCharacter;

public class SkillHealOverTime extends StrategySkill {
    public SkillHealOverTime(String name, int cost) {
        super(name, cost);
    }

    @Override
    public int simulate(CombatCharacter combatCharacter) {
        return 0;
    }

    @Override
    public void useSkill() {

    }

    @Override
    public void setValues(CombatCharacter combatCharacter) {

    }

    @Override
    public void prepareAction() {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
