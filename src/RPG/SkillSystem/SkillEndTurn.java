package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;

import java.util.List;

public class SkillEndTurn extends StrategySkill{


    public SkillEndTurn(String name, int cost, CombatCharacter combatCharacter) {
        super(name, cost, combatCharacter);
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
