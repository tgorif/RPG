package RPG.Projectiles;

import RPG.SkillSystem.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryProjectile {
    private static final Logger LOGGER = Logger.getLogger( FactoryProjectile.class.getName() );

    public static StrategyProjectile getProjectile(String name){
        try {
            switch (name) {
                case ("Arrow"):
                    return new ProjectileArrow(1);
            }
            throw new Exception();
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,"Projectile: " +  name  + " does not exist" );
        }
        return null;
    }
}
