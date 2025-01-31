package RPG.SkillSystem;

import RPG.Character.CombatCharacter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorySkill {
    private static final Logger LOGGER = Logger.getLogger(FactorySkill.class.getName());

    public static StrategySkill getSkill(String name, CombatCharacter character){
        try {
            SkillData skillData =SkillData.get(name);
            if(skillData==null) LOGGER.log(Level.SEVERE," could not find Data for " + name);
            switch (skillData.template) {
                case ("SkillWait"):
                    return new SkillWait(skillData,character);
                case ("SkillMove"):
                    return new SkillMove(skillData,character);
                case ("SkillShot"):
                    return new SkillShot(skillData,character);
                case ("SkillRevive"):
                    return new SkillRevive(skillData,character);
                case ("SkillBuff"):
                    return new SkillBuff(skillData,character);
                case ("SkillHeal"):
                    return new SkillHeal(skillData,character);
            }
            throw new Exception();
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,"Skill: " +  name +" // " + " does not exist" );
        }
        return null;
    }
}
