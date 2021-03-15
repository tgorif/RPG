package RPG.Character;
import RPG.Main.Position;
import RPG.Output.PreView;
import RPG.Output.StrategyOutput;
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
    public final StrategyOutput output;

    public CombatCharacter(Character c, Position position,StrategyOutput output) {
        LOGGER.log(Level.FINE,"Creating CombatCharacter");
        characterInfo= new InfoContainer(c.name,c,position,c.isBlueTeam);
        LOGGER.log(Level.FINE,"Setting Skills");
        characterSkillManager =new CharacterSkillManager();
        characterSkillManager.setSkills(c.perks,this);
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
        this.output=output;
    }

    /**
     * Test Instance
     */
    public CombatCharacter() {
        characterSkillManager = null;
        attributes = null;
        characterInfo = null;
        controller = null;
        output=null;
    }

    private CombatCharacter(CombatCharacter original){
        this.characterInfo=original.characterInfo.clone();
        this.characterSkillManager= original.characterSkillManager.clone();
        this.attributes=original.attributes.clone();
        this.controller=original.controller;
        this.output=new PreView();
    }
    public void takeTurn(){
        LOGGER.log(Level.FINE,characterInfo.getName() + " taking turn");
        attributes.turnStart();
        controller.takeTurn(this);
    }
    public CombatCharacter clone(){
        return new CombatCharacter(this);
    }
}
