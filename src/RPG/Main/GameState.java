package RPG.Main;

import RPG.Character.Character;
import RPG.Character.CombatCharacter;
import RPG.Output.StrategyOutput;
import java.util.*;
import java.util.logging.Logger;

public class GameState {
    static Logger LOGGER =Logger.getLogger(GameState.class.getName());
    public List<CombatCharacter> combatCharacterList = new ArrayList<>();
    private final StrategyEndCondition endCondition=new Domination();
    public Level level;
    final List<Character> characterList;
    public StrategyOutput output;
    private static GameState gameState;
    public int turnCounter=0;

    public GameState(List<Character> characterList,StrategyOutput strategyOutput){
        output=strategyOutput;
        level=new Level(101,1);
        this.characterList=characterList;
        gameState=this;
        createCharacters();
        LOGGER.log(java.util.logging.Level.FINE,"Created gameState " + GameState.getInstance().toString());
    }
    /*
    deprecated
     */
    private GameState(GameState original){
        this.output=original.output;
        this.level=new Level(101,1);
        this.characterList=original.characterList;
        this.turnCounter=original.turnCounter;
        for(CombatCharacter c : original.combatCharacterList){
            this.combatCharacterList.add(c.clone());
        }
    }

    /**
     * calls setTurnOrder and resolveTurn until endCondition is fulfilled
     */
    public void start(){
        while(!endCondition.checkEndCondition()) {
            setTurnOrder();
            resolveTurn();
        }
    }

    /**
     *
     * @return current gameState
     */
    public static GameState getInstance(){
        if(gameState==null) LOGGER.log(java.util.logging.Level.SEVERE,"returned null gameState");
        return gameState;
    }
    public GameState clone(){
        if(gameState==null) LOGGER.log(java.util.logging.Level.SEVERE,"returned null gameState");
        return new GameState(GameState.getInstance());
    }
    public CombatCharacter getCombatCharacter(String name){
        for (CombatCharacter c : combatCharacterList){
            if(name.equals(c.characterInfo.getName())) return c;
        }
        return null;
    }

    /**
     * created combatCharacter from characters in characterList and assigns a Position to them
     * TODO set actually sensible startingPositions
     */
    private void createCharacters(){
        for(Character c : characterList){
            combatCharacterList.add(new CombatCharacter(c,Level.getCurrentLevel()
                    .tiles.get(List.of(0,0,0,0)),output));
        }
    }

    /**
     * Calls resolveTurn Method for each character in combatCharacterList
     * incrementsTurnCounter
     */
    private void resolveTurn() {
        output.resolveingTurn(turnCounter);
        for (CombatCharacter combatCharacter : combatCharacterList){
            combatCharacter.takeTurn();
        }
        turnCounter++;
    }
    /**
     * Orders combatCharacter list according to SPD
     */
    private void setTurnOrder(){
        combatCharacterList.sort((combatCharacter, t1) -> Integer.compare(t1.attributes.getSPD(),
                combatCharacter.attributes.getSPD()));
    }

    /**
     * @param character x
     * @return List of Characters with different team than x
     */
    public List<CombatCharacter> getHostiles(CombatCharacter character){
        List<CombatCharacter> result = new ArrayList<>();
        if(character==null || combatCharacterList==null || !combatCharacterList.contains(character)){
            LOGGER.log(java.util.logging.Level.SEVERE,"character==null || list==null || character not in list");
            return result;
        }
        for(CombatCharacter c : combatCharacterList){
            if(character.characterInfo.isBlueTeam()!=c.characterInfo.isBlueTeam()) result.add(c);
        }
        return result;
    }
    public List<CombatCharacter> getAllies(CombatCharacter character){
        List<CombatCharacter> result = new ArrayList<>();
        if(character==null || combatCharacterList==null || !combatCharacterList.contains(character)){
            LOGGER.log(java.util.logging.Level.SEVERE,"character==null || list==null || character not in list");
            return result;
        }
        for(CombatCharacter c : combatCharacterList){
            if(character.characterInfo.isBlueTeam()==c.characterInfo.isBlueTeam()) result.add(c);
        }
        return result;
    }
    /*
    public void changeCharacterPosition(CombatCharacter combatCharacter, Position target){
        output.CharacterMoved(combatCharacter, combatCharacter.getPosition(),target);
        if(Level.currentLevel.isEqualPosition(target,combatCharacter.getPosition())) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Character Position & target are the same");
        }
        else if(Level.currentLevel.getDistance(target,combatCharacter.getPosition())>combatCharacter.movement){
            LOGGER.log(java.util.logging.Level.SEVERE, "Character cant reach target");
        }
        combatCharacter.setPosition(target);
    }
    public void reduceAP(CombatCharacter combatCharacter, int AP){
        combatCharacter.setAP(combatCharacter.getAP()-AP);
    }
    public void createNewProjectile(CombatCharacter caster, CombatCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill) {
        output.CharacterRangedAttack(caster,target,strategyProjectile,skill);
        int d=target.resolveHit(strategyProjectile);
        output.CharacterTookDamage(target,d);
    }
    */
}
