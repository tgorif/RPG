package RPG.Projectiles;

import RPG.Character.CombatCharacter;
import RPG.StatusEffects.StrategyStatus;

import java.util.List;

public abstract class StrategyProjectile {
    int damage;
    StrategyStatus status;
    abstract void setDamage();
    public abstract int getDamage();
    public abstract void resolveImpact(CombatCharacter combatCharacter);
}
