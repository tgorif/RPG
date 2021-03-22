package RPG.SkillSystem;

import RPG.Character.CombatCharacter;

import java.util.List;

public interface targetsCharacter {
    CombatCharacter target = null;
    boolean setTarget(CombatCharacter combatCharacter);
    List<CombatCharacter> getTargets();
}
