package RPG.SkillSystem;
import RPG.Character.CombatCharacter;

public class SkillWait extends StrategySkill {

    public SkillWait(String name) {
        super(name);
    }

    @Override
    public SkillWait getNewInstance() {
        return new SkillWait(skillName);
    }

    @Override
    public void simulate(CombatCharacter combatCharacter) {

    }

    @Override
    public void useSkill() {
        gameState.reduceAP(caster,AP);
    }

    @Override
    public void setValues(CombatCharacter combatCharacter) {
        this.gameState=gameState;
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
