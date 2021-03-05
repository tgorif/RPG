package RPG.Output;

import RPG.Character.CombatCharacter;
import RPG.Main.Position;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;

public class Console implements StrategyOutput {
    @Override
    public void CharacterMoved(CombatCharacter combatCharacter, Position from, Position to) {
        OutputMethod();
        System.out.println(combatCharacter.name + " moved from " + from.toString() + " to " + to.toString());
    }
    @Override
    public void resolveingTurn(int turnTimer) {
        OutputMethod();
        System.out.println("resolveing Turn "  + turnTimer);
    }
    @Override
    public void CharacterRangedAttack(CombatCharacter caster, CombatCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill) {
        OutputMethod();
        System.out.println(caster.name + " used " + skill.skillName + " against " + target.name);
    }
    @Override
    public void CharacterTookDamage(CombatCharacter target, int damage) {
        OutputMethod();
        System.out.println(target.name + " took "  + damage);
    }
    private void OutputMethod(){
        System.out.print("Console: ");
    }
}
