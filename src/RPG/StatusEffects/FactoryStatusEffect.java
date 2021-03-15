package RPG.StatusEffects;

import RPG.Character.CombatCharacter;
import RPG.Projectiles.FactoryProjectile;
import RPG.Projectiles.ProjectileArrow;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.SkillWait;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryStatusEffect {

    private static final Logger LOGGER = Logger.getLogger(FactoryStatusEffect.class.getName());

    public static StrategyStatus getStatus(String name, CombatCharacter combatCharacter) {
        try {
            switch (name) {
                case ("Dead"):
                    return new StatusDead(combatCharacter);
                case ("Bleed"):
                    return new StatusBleed(combatCharacter,5,2);
            }
            throw new Exception();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Status: " + name + " does not exist");
        }
        return null;
    }
}
