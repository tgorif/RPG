package RPG.Output;

import RPG.Character.CombatCharacter;
import RPG.Main.Position;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;

public class PreView implements StrategyOutput{
    @Override
    public void CharacterMoved(CombatCharacter combatCharacter, Position from, Position to) {

    }

    @Override
    public void resolveingTurn(int turnTimer) {

    }

    @Override
    public void CharacterRangedAttack(CombatCharacter caster, CombatCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill) {

    }

    @Override
    public void CharacterTookDamage(CombatCharacter target, int damage) {

    }

    @Override
    public void endCondition(String winner) {

    }

    @Override
    public void characterDied(CombatCharacter combatCharacter) {

    }

    @Override
    public void notify(String message) {

    }

    @Override
    public void SkillUsed(CombatCharacter combatCharacter, String skill) {

    }
}
