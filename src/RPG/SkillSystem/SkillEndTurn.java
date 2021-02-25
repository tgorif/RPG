package RPG.SkillSystem;

import RPG.Character.ConcreteCharacter;
import RPG.Main.GameState;

public class SkillEndTurn extends StrategySkill{

    public SkillEndTurn(String name) {
        super(name);
    }
    @Override
    public SkillEndTurn getNewInstance() {
        return new SkillEndTurn(skillName);
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
        AP=10;
    }
    @Override
    public void prepareAction() {

    }

    @Override
    public void validate() {
        isValid=true;
    }
}
