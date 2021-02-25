package RPG.Output;

import RPG.Character.ConcreteCharacter;
import RPG.Main.Position;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;

public interface StrategyOutput {
     void CharacterMoved(ConcreteCharacter concreteCharacter, Position from, Position to);
     void resolveingTurn(int turnTimer);
     void currentCharacterActions(ConcreteCharacter character,int listSize);
     void CharacterRangedAttack(ConcreteCharacter caster, ConcreteCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill);
     void CharacterTookDamage(ConcreteCharacter target,int damage);
}
