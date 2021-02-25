package RPG.Output;

import RPG.Character.ConcreteCharacter;
import RPG.Main.Position;
import RPG.Output.StrategyOutput;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;

public class Console implements StrategyOutput {
    @Override
    public void CharacterMoved(ConcreteCharacter concreteCharacter, Position from, Position to) {
        System.out.println(concreteCharacter.name + " moved from " + from.toString() + " to " + to.toString());
    }

    @Override
    public void resolveingTurn(int turnTimer) {
        System.out.println("resolveing Turn "  + turnTimer);
    }

    @Override
    public void currentCharacterActions(ConcreteCharacter character, int listSize) {
        System.out.println(character.name +" " + listSize);
    }

    @Override
    public void CharacterRangedAttack(ConcreteCharacter caster, ConcreteCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill) {
        System.out.println(caster.name + " used " + skill.skillName + " against " + target.name);
    }

    @Override
    public void CharacterTookDamage(ConcreteCharacter target,int damage) {
        System.out.println(target + " took "  + damage);
    }
}
