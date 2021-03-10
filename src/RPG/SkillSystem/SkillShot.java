package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import RPG.Projectiles.FactoryProjectile;
import RPG.Projectiles.StrategyProjectile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillShot extends StrategySkill{
    String projectileName;
    int range;
    int damage;
    CombatCharacter target;
    public SkillShot(String name,int cost,CombatCharacter caster) {
        super(name,cost,caster);
    }
    public SkillShot(String name, int cost,CombatCharacter caster, int range, int damage, String projectileName){
        super(name,cost,caster);
        this.range=range;
        this.projectileName=projectileName;
        this.damage=damage;
    }

    @Override
    public List<GameState> simulate() {
        List<GameState> result = new ArrayList<>();
        for(CombatCharacter hostile : GameState.getInstance().getHostiles(caster)){
            target=hostile;
            if(isValid()){
                GameState clone =GameState.getInstance().clone();
                useSkill(clone);
                result.add(clone);
            }
        }
        return result;
    }
    private void useSkill(GameState g){
        FactoryProjectile.getProjectile(projectileName).resolveImpact(
                g.getCombatCharacter(target.characterInfo.getName()));
    }

    @Override
    public void useSkill() {
        useSkill(GameState.getInstance());

    }

    @Override
    public boolean isValid() {
        return target!=null
                && caster!=null
                &&!caster.statusEffects.containsKey("dead")
                &&!target.statusEffects.containsKey("dead")
                && Level.getCurrentLevel().getDistance(caster.characterInfo.getPosition(),
                target.characterInfo.getPosition())<=range;
    }
}
