package RPG.Projectiles;

import RPG.Character.CombatCharacter;
import RPG.StatusEffects.StrategyStatus;
import RPG.StatusEffects.Transferable;

public class ProjectileArrow extends StrategyProjectile {

    public ProjectileArrow(int damage){
        this.damage=damage;
    }
    public ProjectileArrow(int damage, StrategyStatus status){
        this.damage=damage;
        this.status=status;
    }

    @Override
    public void setDamage() {
     damage=1;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void resolveImpact(CombatCharacter combatCharacter) {
        combatCharacter.changeHP(-damage);
        if(status!=null && status instanceof Transferable){
            ((Transferable) status).transfer(combatCharacter);
        }
    }
}
