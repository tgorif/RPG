package RPG.SkillSystem;

import RPG.Character.CombatCharacter;

import java.util.List;

public interface targetsCharacter {
    boolean setTarget(CombatCharacter combatCharacter);
    List<CombatCharacter> getTargets();
}
