package RPG.SkillSystem;


import RPG.Main.Level;
import java.util.List;

public interface targetsTile {
    boolean setTarget(Level.Tile tile);
    List<Level.Tile> getTargets();
}
