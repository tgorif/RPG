package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SkillMove extends StrategySkill implements targetsTile {
    Level.Tile target;
    Logger LOGGER =Logger.getLogger(SkillMove.class.getName());

    public SkillMove(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
    }

    @Override
    public void useSkill() {
        if(!isValid()) return;
        LOGGER.log(java.util.logging.Level.FINE,"Using Move for " +
                caster.characterInfo.getName() + " moving from "
                + caster.characterInfo.getTile().toString() + " to "
                + target.toString());
        caster.attributes.changeAP(-cost);
        caster.characterInfo.setTile(target);
    }
    @Override
    public boolean isValid() {
        return target != null
                && caster != null
                && caster.attributes.getAP()>0
                && Level.getCurrentLevel().canEnter(target)
                && Level.getCurrentLevel().getDistance(target, caster.characterInfo.getTile())
                <= caster.attributes.getMovement()
                &&!caster.statusEffects.containsKey("dead");
    }

    @Override
    public boolean setTarget(Level.Tile tile) {
        target=tile;
        return isValid();
    }

    @Override
    public List<Level.Tile> getTargets() {
        return Level.getCurrentLevel().getTilesInRange(caster.characterInfo.getTile(),caster.attributes.getMovement());
    }
}
