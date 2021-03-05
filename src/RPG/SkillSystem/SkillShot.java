package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Projectiles.ProjectileArrow;
import RPG.Projectiles.StrategyProjectile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillShot extends StrategySkill{
    CombatCharacter target;
    StrategyProjectile strategyProjectile;
    public SkillShot(String name) {
        super(name);
    }

    @Override
    public StrategySkill getNewInstance() {
        return new SkillShot(skillName);
    }
    @Override
    public void simulate(CombatCharacter combatCharacter) {

    }
    @Override
    public void useSkill() {
        gameState.createNewProjectile(caster,target,new ProjectileArrow(10),this);
    }
    @Override
    public void setValues(CombatCharacter combatCharacter) {
        this.gameState=gameState;
        caster= combatCharacter;
    }
    @Override
    public void prepareAction() {
        List<CombatCharacter> targets=new ArrayList<>();
        for(CombatCharacter c : gameState.combatCharacterList){
            if(c.isBlueTeam!=caster.isBlueTeam) targets.add(c);
        }
        Collections.shuffle(targets);
        if(targets.size()>0) target=targets.get(0);
    }
    @Override
    public boolean isValid() {
        return false;
    }
}
