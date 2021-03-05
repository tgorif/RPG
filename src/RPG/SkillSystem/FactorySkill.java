package RPG.SkillSystem;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorySkill {
    private static final Logger LOGGER = Logger.getLogger( FactorySkill.class.getName() );

    public static StrategySkill getSkill(String name){
        try {
            switch (name.toLowerCase()) {
                case ("wait"):
                    return new SkillWait("wait");
                case ("skipturn"):
                    return new SkillWait("skipturn");
                case ("move"):
                    return new SkillMove("move");
                case ("shotarrow"):
                    return new SkillShot("shotarrow");
                case ("quickshot"):
                    return new SkillShot("quickshot");
                case ("preparation"):
                    return new SkillShot("preparation");
            }
            throw new Exception();
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,"Skill: " +  name  + " does not exist" );
        }
        return null;
    }
}
