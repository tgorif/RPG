package RPG.Output;

import RPG.Character.CombatCharacter;
import RPG.Main.Position;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;

public interface StrategyOutput {
     void CharacterMoved(CombatCharacter combatCharacter, Position from, Position to);
     void resolveingTurn(int turnTimer);
     void CharacterRangedAttack(CombatCharacter caster, CombatCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill);
     void CharacterTookDamage(CombatCharacter target, int damage);
     void endCondition(String winner);
     void characterDied(CombatCharacter combatCharacter);
     void notify(String message);
     void SkillUsed(CombatCharacter combatCharacter,String skill);
}
