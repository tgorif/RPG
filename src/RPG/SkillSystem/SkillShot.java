package RPG.SkillSystem;

import RPG.Character.ConcreteCharacter;
import RPG.Main.*;
import RPG.Projectiles.ProjectileArrow;
import RPG.Projectiles.StrategyProjectile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillShot extends StrategySkill{
    ConcreteCharacter target;
    StrategyProjectile strategyProjectile;
    public SkillShot(String name) {
        super(name);
    }

    @Override
    public StrategySkill getNewInstance() {
        return new SkillShot(skillName);
    }
    @Override
    public void simulate() {

    }
    @Override
    public void useSkill() {
        gameState.createNewProjectile(caster,target,new ProjectileArrow(10),this);
    }
    @Override
    public void setValues(GameState gameState, ConcreteCharacter concreteCharacter) {
        this.gameState=gameState;
        caster=concreteCharacter;
    }
    @Override
    public void prepareAction() {
        List<ConcreteCharacter> targets=new ArrayList<>();
        for(ConcreteCharacter c : gameState.concreteCharacterList){
            if(c.isBlueTeam!=caster.isBlueTeam) targets.add(c);
        }
        Collections.shuffle(targets);
        if(targets.size()>0) target=targets.get(0);
    }

    @Override
    public void validate() {
        if(target==null) isValid=false;
        else isValid=true;
    }
}
