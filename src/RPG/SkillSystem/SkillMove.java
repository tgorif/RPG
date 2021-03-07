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
        if(!combatCharacter.getClass().toString().equals("class RPG.Character.CombatCharacter$SimulatedCharacter")){
            LOGGER.log(java.util.logging.Level.SEVERE,"simulating on an instance of "
                    + combatCharacter.getClass().toString());
        }
        combatCharacter.position=target;
        combatCharacter.AP-=AP;
        LOGGER.log(java.util.logging.Level.FINE,"Simulated Move fpr " +  combatCharacter.getClass().toString()
                + " moved to " + combatCharacter.position.toString());
    }
    @Override
    public void useSkill() {
        LOGGER.log(java.util.logging.Level.FINE,"Using Move for " +
                caster.name + " moving from " +  caster.position.toString() + " to " + target.toString());
        GameState.getInstance().changeCharacterPosition(caster,target);
        GameState.getInstance().reduceAP(caster,AP);
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
        LOGGER.log(java.util.logging.Level.FINE,"Setting Values for "
                + caster.name + " " + caster.getClass().toString()
                + "current Position" + caster.position.toString()
                +" moving to " + target.toString());
    }
    @Override
    public void prepareAction() {

    }
    @Override
    public boolean isValid() {
        return target != null
                && caster != null
                && Level.getCurrentLevel().isValid(target)
                && Level.getCurrentLevel().getDistance(target, caster.getPosition()) <= caster.movement;
    }
}
