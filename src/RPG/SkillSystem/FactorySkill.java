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
            switch (skillData.template) {
                case ("Wait"):
                    return new SkillWait(skillData,character);
                case ("Move"):
                    return new SkillMove(skillData,character);
                case ("SkillShot"):
                    return new SkillShot(skillData,character);
                case ("SkillRevive"):
                    return new SkillRevive(skillData,character);
            }
            throw new Exception();
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,"Skill: " +  name +" // " +   + " does not exist" );
        }
        return null;
    }
}
