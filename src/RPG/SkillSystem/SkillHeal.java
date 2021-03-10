package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;

import java.util.List;

public class SkillHeal extends StrategySkill{
    public SkillHeal(String name, int cost,CombatCharacter caster) {
        super(name, cost,caster);
    }

    @Override
    public List<GameState> simulate() {
        return null;
    }

    @Override
    public void useSkill() {

    }

    @Override
    public boolean isValid() {
        return false;
    }


}
