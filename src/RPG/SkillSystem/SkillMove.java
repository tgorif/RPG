package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class SkillMove extends StrategySkill {
    Position target;
    Position currentPosition;
    Logger LOGGER =Logger.getLogger(SkillMove.class.getName());

    public SkillMove(String name) {
        super(name);
    }
    @Override
    public StrategySkill getNewInstance() {
        return new SkillMove(skillName);
    }
    @Override
    public void simulate(CombatCharacter combatCharacter) {
        combatCharacter.position=target;
        LOGGER.log(java.util.logging.Level.FINE,"Simulated Move fpr " +  combatCharacter.getClass().toString()
                + " moved to " + combatCharacter.position.toString());
    }
    @Override
    public void useSkill() {
        LOGGER.log(java.util.logging.Level.FINE,"Using Move for " +
                caster.name + " moving from " +  caster.position.toString() + " to " + target.toString());
        gameState.changeCharacterPosition(caster,target);
        gameState.reduceAP(caster,AP);
    }
    @Override
    public void setValues(CombatCharacter combatCharacter) {
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
        return true;
    }
}
