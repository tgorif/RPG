package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
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
        combatCharacter.AP-=AP;
    }
    @Override
    public void useSkill() {
        GameState.getInstance().createNewProjectile(caster,target,new ProjectileArrow(10),this);
    }
    @Override
    public void setValues(CombatCharacter combatCharacter) {
        caster= combatCharacter;
        AP=3;
        List<CombatCharacter> targets=new ArrayList<>();
        for(CombatCharacter c : GameState.getInstance().combatCharacterList){
            if(c.isBlueTeam!=caster.isBlueTeam) targets.add(c);
        }
        Collections.shuffle(targets);
        if(targets.size()>0) target=targets.get(0);
    }
    @Override
    public void prepareAction() {
    }
    @Override
    public boolean isValid() {
        return target!=null
        || caster!=null;
    }
}
