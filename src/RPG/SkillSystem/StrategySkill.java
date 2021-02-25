package RPG.SkillSystem;
import RPG.Character.*;
import RPG.Main.*;

public abstract class StrategySkill {
     public String skillName;
     ConcreteCharacter caster;
     GameState gameState;
     public int AP;
     public boolean isValid;
     public StrategySkill(String name){
          this.skillName=name;
     }
     public abstract StrategySkill getNewInstance();
     public abstract void simulate();
     public abstract void useSkill();
     public abstract void setValues(GameState gameState,ConcreteCharacter concreteCharacter);
     public abstract void prepareAction();
     public abstract void validate();
}
