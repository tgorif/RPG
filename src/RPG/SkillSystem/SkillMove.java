package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SkillMove extends StrategySkill {
    Position target;
    List<Position> targets=new ArrayList<>();
    Logger LOGGER =Logger.getLogger(SkillMove.class.getName());

    public SkillMove(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
    }

    private void useSkill(GameState g){
        CombatCharacter c= g.getCombatCharacter(caster.characterInfo.getName());
        c.attributes.changeAP(-cost);
        c.characterInfo.setPosition(target);
    }

    @Override
    public void useSkill() {
        setTargets();
        if(targets.size()==0) return;
        target=targets.get(0);
        if(!isValid()) return;
        LOGGER.log(java.util.logging.Level.FINE,"Using Move for " +
                caster.characterInfo.getName() + " moving from "
                + caster.characterInfo.getPosition().toString() + " to "
                + target.toString());
        useSkill(GameState.getInstance());
    }
    @Override
    public boolean isValid() {
        return target != null
                && caster != null
                && Level.getCurrentLevel().isValid(target)
                && Level.getCurrentLevel().getDistance(target, caster.characterInfo.getPosition())
                <= caster.attributes.getMovement()
                &&!caster.statusEffects.containsKey("dead");
    }
    private void setTargets(){
        targets=GameState.getInstance().level.getPositionsInRange(caster.characterInfo.getPosition(),caster.attributes.getMovement());
    }
}
