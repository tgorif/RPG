package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorySkill {
    private static final Logger LOGGER = Logger.getLogger(FactorySkill.class.getName());

    public static StrategySkill getSkill(String name, CombatCharacter character){
        try {
            switch (name.toLowerCase()) {
                case ("wait"):
                    return new SkillWait("wait",1,character);
                case ("skipturn"):
                    return new SkillWait("skipturn",10,character);
                case ("move"):
                    return new SkillMove("move",1,character);
                case ("shotarrow"):
                    return new SkillShot("shotarrow",
                            3, character,50,1, "Arrow");
                case ("quickshot"):
                    return new SkillShot("quickshot",
                            2,character,20,1, "Arrow");
                case ("preparation"):
                    return new SkillShot("preparation",2,character,0,1,"Arrow");
            }
            throw new Exception();
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,"Skill: " +  name  + " does not exist" );
        }
        return null;
    }
}
