package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.*;

import java.util.Collections;
import java.util.List;

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
    public void simulate(CombatCharacter combatCharacter) {

    }
    @Override
    public void useSkill() {
        gameState.changeCharacterPosition(caster,target);
        gameState.reduceAP(caster,AP);
    }
    @Override
    public void setValues(CombatCharacter combatCharacter) {
       this.gameState=gameState;
        caster= combatCharacter;
        currentPosition= combatCharacter.getPosition();
        AP=1;
        List<Position> list = Level.getCurrentLevel().getPositionsInRange(currentPosition,caster.movement);
        if(list.size()>0) {
           Collections.shuffle(list);
           target=list.get(0);
        }
        else{
            target=currentPosition;
        }
    }
    @Override
    public void prepareAction() {

    }
    @Override
    public boolean isValid() {
        return false;
    }
}
