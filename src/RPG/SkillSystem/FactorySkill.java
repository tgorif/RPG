package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import RPG.Projectiles.FactoryProjectile;
import RPG.Projectiles.ProjectileArrow;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorySkill {
    private static final Logger LOGGER = Logger.getLogger(FactorySkill.class.getName());

    public static StrategySkill getSkill(String name, CombatCharacter character){
        try {
            switch (name.toLowerCase()) {
                case ("wait"):
                    return new SkillWait("wait",1);
                case ("skipturn"):
                    return new SkillWait("skipturn",10);
                case ("move"):
                    return new SkillMove("move",1);
                case ("shotarrow"):
                    return new SkillShot("shotarrow",
                            50, 3,1, "Arrow");
                case ("quickshot"):
                    return new SkillShot("quickshot",
                            20,2,1, "Arrow");
                case ("preparation"):
                    return new SkillShot("preparation",0,2,1,"Arrow");
            }
            throw new Exception();
        }
        catch (NoSuchMethodException e){
            LOGGER.log(Level.WARNING,"Skill: " +  name  + " does not exist" );
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,"Skill: " +  name  + " does not exist" );
        }
        return null;
    }
}
