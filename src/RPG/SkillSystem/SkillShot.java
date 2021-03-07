package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import RPG.Projectiles.ProjectileArrow;
import RPG.Projectiles.StrategyProjectile;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillShot extends StrategySkill{
    CombatCharacter target;
    Method projectile;
    int range;
    int damage;
    public SkillShot(String name,int cost) {
        super(name,cost);
    }
    public SkillShot(String name,int range,int cost,int damage,Method method){
        super(name,cost);
        this.range=range;
        projectile=method;
        this.damage=damage;
    }
    @Override
    public void simulate(CombatCharacter combatCharacter) {
        combatCharacter.AP-=AP;
    }
    @Override
    public void useSkill() {
        try {
            GameState.getInstance().createNewProjectile(caster, target,
                    ((StrategyProjectile) projectile.invoke(this,damage)), this);
        }
        catch (Exception e){

        }
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
                && caster!=null
                &&!caster.statusEffects.containsKey("dead")
                && Level.getCurrentLevel().getDistance(caster.getPosition(), target.getPosition())<=range;
    }
}
