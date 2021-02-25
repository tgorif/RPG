package RPG.SkillSystem;

import RPG.Character.ConcreteCharacter;
import RPG.Main.*;

public class SkillMove extends StrategySkill {
    Position target;
    Position currentPosition;

    public SkillMove(String name) {
        super(name);
    }
    @Override
    public StrategySkill getNewInstance() {
        return new SkillMove(skillName);
    }
    @Override
    public void simulate() {

    }
    @Override
    public void useSkill() {
        gameState.changeCharacterPosition(caster,target);
        gameState.reduceAP(caster,AP);
    }
    @Override
    public void setValues(GameState gameState, ConcreteCharacter concreteCharacter) {
       this.gameState=gameState;
        caster=concreteCharacter;
        currentPosition=concreteCharacter.getPosition();
        AP=1;
        target=new Position(currentPosition.x+caster.movement,currentPosition.y,currentPosition.z);
    }
    @Override
    public void prepareAction() {

    }

    @Override
    public void validate() {
        isValid=true;
    }
}
