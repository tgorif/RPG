package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Main.GameState;
import RPG.Main.Level;
import RPG.Projectiles.FactoryProjectile;
import java.util.ArrayList;
import java.util.List;

public class SkillShot extends StrategySkill implements targetsCharacter{
    String projectileName;
    int range;
    int damage;
    CombatCharacter target;
    public SkillShot(SkillData skillData,CombatCharacter combatCharacter) {
        super(skillData,combatCharacter);
        this.range=skillData.range;
        this.damage=skillData.damage;
        this.projectileName= skillData.projectile;
    }
    @Override
    public void useSkill() {
        GameState.getInstance().output.SkillUsed(caster,skillName);
        if(projectileName.length()!=0){
            FactoryProjectile.getProjectile(projectileName).resolveImpact(target);
        }
        caster.attributes.changeAP(-cost);
    }

    @Override
    public boolean isValid() {
        return target!=null
                &&!caster.statusEffects.containsKey("Dead")
                &&!target.statusEffects.containsKey("Dead")
                && Level.getCurrentLevel().getDistance(caster.characterInfo.getPosition(),
                target.characterInfo.getPosition())<=range
                && GameState.getInstance().turnCounter-lastUsed<cooldown;
    }

    @Override
    public boolean setTarget(CombatCharacter combatCharacter) {
        target=combatCharacter;
        return isValid();
    }

    @Override
    public List<CombatCharacter> getTargets() {
        List<CombatCharacter> result=new ArrayList<>();
        for (CombatCharacter i : GameState.getInstance().getHostiles(caster)){
            target=i;
            if(isValid()) result.add(i);
        }
        return result;
    }

}
