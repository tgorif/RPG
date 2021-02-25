package RPG.SkillSystem;
import RPG.Character.ConcreteCharacter;
import RPG.Main.GameState;

public class SkillWait extends StrategySkill {

    public SkillWait(String name) {
        super(name);
    }

    @Override
    public SkillWait getNewInstance() {
        return new SkillWait(skillName);
    }

    @Override
    public void simulate() {

    }

    @Override
    public void useSkill() {
        gameState.reduceAP(caster,AP);
    }

    @Override
    public void setValues(GameState gameState, ConcreteCharacter concreteCharacter) {
        this.gameState=gameState;
        caster=concreteCharacter;
    }

    @Override
    public void prepareAction() {
        AP=1;
    }

    @Override
    public void validate() {
        isValid=true;
    }
}
