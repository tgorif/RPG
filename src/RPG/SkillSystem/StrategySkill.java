package RPG.SkillSystem;
import RPG.Character.*;
import RPG.Main.*;

public abstract class StrategySkill {
     public String skillName;
     CombatCharacter caster;
     GameState gameState;
     public int AP;
     public StrategySkill(String name){
          this.skillName=name;
     }
     public abstract StrategySkill getNewInstance();
     public abstract void simulate(CombatCharacter combatCharacter);
     public abstract void useSkill();
     public abstract void setValues(CombatCharacter combatCharacter);
     public abstract void prepareAction();
     public abstract boolean isValid();
     public void setCaster(CombatCharacter combatCharacter){
          this.caster=combatCharacter;
     }
}
