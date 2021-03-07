package RPG.SkillSystem;
import RPG.Character.CombatCharacter;

public class SkillWait extends StrategySkill {

    public SkillWait(String name,int cost) {
        super(name,cost);
    }

    @Override
    public void simulate(CombatCharacter combatCharacter) {
        combatCharacter.AP-=AP;
    }

    @Override
    public void useSkill() {
        gameState.reduceAP(caster,AP);
    }

    @Override
    public void setValues(CombatCharacter combatCharacter) {
        AP=1;
        caster= combatCharacter;
    }

    @Override
    public void prepareAction() {
        AP=1;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
