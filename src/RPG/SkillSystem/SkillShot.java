package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import RPG.Projectiles.FactoryProjectile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillShot extends StrategySkill{
    CombatCharacter target;
    String projectileName;
    int range;
    int damage;
    public SkillShot(String name,int cost) {
        super(name,cost);
    }
    public SkillShot(String name, int range, int cost, int damage, String projectileName){
        super(name,cost);
        this.range=range;
        this.projectileName=projectileName;
        this.damage=damage;
    }
    @Override
    public int simulate(CombatCharacter combatCharacter) {
        combatCharacter.AP-=AP;
        if(target.HP-damage<=0) return  Integer.MAX_VALUE;
        else{
            return damage;
        }
    }
    @Override
    public void useSkill() {
            GameState.getInstance().createNewProjectile(caster, target, FactoryProjectile.getProjectile(projectileName), this);
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
