package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorySkill {
    private static final Logger LOGGER = Logger.getLogger(FactorySkill.class.getName());

    public static StrategySkill getSkill(String name, CombatCharacter character){
        try {
            SkillData skillData =SkillData.get(name);
            if(skillData==null) throw new Exception();
            switch (skillData.name) {
                case ("Wait"):
                    return new SkillWait("wait",skillData);
                case ("SkipTurn"):
                    return new SkillWait("skipturn",skillData);
                case ("Move"):
                    return new SkillMove("move",skillData);
                case ("SkillShot"):
                    return new SkillShot("shotarrow", skillData);
                case ("SkillRevive"):
                    return new SkillShot("quickshot", skillData);
);
            }
            throw new Exception();
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,"Skill: " +  name  + " does not exist" );
        }
        return null;
    }
}
