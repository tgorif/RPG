package RPG.Output;

import RPG.Character.CombatCharacter;
import RPG.Main.Position;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;

public class Console implements StrategyOutput {
    @Override
    public void CharacterMoved(CombatCharacter combatCharacter, Position from, Position to) {
        OutputMethod();
        System.out.println(combatCharacter.characterInfo.getName() + " moved from " + from.toString() + " to " + to.toString());
    }
    @Override
    public void resolveingTurn(int turnTimer) {
        OutputMethod();
        System.out.println("resolveing Turn "  + turnTimer);
    }
    @Override
    public void CharacterRangedAttack(CombatCharacter caster, CombatCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill) {
        OutputMethod();
        System.out.println(caster.characterInfo.getName() + " used "
                + skill.skillName + " against "
                + target.characterInfo.getName());
    }
    @Override
    public void CharacterTookDamage(CombatCharacter target, int damage) {
        OutputMethod();
        System.out.println(target.characterInfo.getName() + " took "  + damage);
    }
    public void endCondition(String winner){
        OutputMethod();
        System.out.println(winner + " won");
    }

    @Override
    public void characterDied(CombatCharacter combatCharacter) {
        OutputMethod();
        System.out.println(combatCharacter.characterInfo.getName() + " died");
    }
    @Override
    public void notify(String message) {
        OutputMethod();
        System.out.println(message);
    }

    @Override
    public void SkillUsed(CombatCharacter combatCharacter, String skill) {
        OutputMethod();
        System.out.println(combatCharacter.characterInfo.getName() + " used " + skill);
    }

    private void OutputMethod(){
        System.out.print("Console: ");
    }
}
