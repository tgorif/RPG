package RPG.Character;
import RPG.Main.Position;
import RPG.StatusEffects.StrategyStatus;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CombatCharacter {
    public final CharacterSkillManager characterSkillManager;
    public final Map<String,StrategyStatus> statusEffects=new HashMap<>();
    public final AttributeManager attributes;
    public final InfoContainer characterInfo;
    private final Logger LOGGER = Logger.getLogger(CombatCharacter.class.getName());
    private final StrategyCharacterTurn controller;

    public CombatCharacter(Character c, Position position) {
        LOGGER.log(Level.FINE,"Creating CombatCharacter");
        characterInfo= new InfoContainer(c.name,c,position,c.isBlueTeam);
        LOGGER.log(Level.FINE,"Setting Skills");
        characterSkillManager =new CharacterSkillManager();
        characterSkillManager.setSkills(c.perks);
        LOGGER.log(Level.FINE,"Setting Stats");
        attributes=new AttributeManager.AttributeBuilder()
                .addStatsPerk(characterSkillManager.preCombatPerks)
                .setOwner(this)
                .build();
        LOGGER.log(Level.INFO,"Created Character " + characterInfo.getName() + " with Stats HP "
                + attributes.getHP() + " SPD "
                + attributes.getSPD() + " movement "
                + attributes.getMovement() + " AP "
                + attributes.getAP());
        controller=new AICharacterTurn();
    }
    public void takeTurn(){
        LOGGER.log(Level.FINE,characterInfo.getName() + " taking turn");
        attributes.turnStart();
        controller.takeTurn(this);
    }
}
