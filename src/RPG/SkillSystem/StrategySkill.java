package RPG.SkillSystem;
import RPG.Character.*;
import RPG.Main.GameState;

import java.util.List;

public abstract class StrategySkill {
     public String skillName;
     public final CombatCharacter caster;
     public int cost;
     public StrategySkill(String name,int cost,CombatCharacter combatCharacter){
          this.skillName=name;
          this.cost=cost;
          caster=combatCharacter;
     }
     public abstract List<GameState> simulate();
     public abstract void useSkill();
     public abstract boolean isValid();

}
