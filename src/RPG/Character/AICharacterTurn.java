package RPG.Character;

import RPG.Main.GameState;
import RPG.SkillSystem.StrategySkill;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AICharacterTurn implements StrategyCharacterTurn{
    CombatCharacter agent;
    int base;
    //todo create tests
    private final Logger LOGGER = Logger.getLogger(AICharacterTurn.class.getName());

    /**
     * find best skill using greedy approach
     * todo use DFS for better results instead
     * @param agent Character which should use skills
     */
    @Override
    public void takeTurn(CombatCharacter agent) {
        this.agent=agent;
        base=evaluate(GameState.getInstance());
        while(agent.attributes.getAP()>0){
            getBestMove().useSkill();
        }
    }

    /**
     * @return StrategySkill - Skill with highest rating
     */
    private StrategySkill getBestMove(){
        int best=Integer.MIN_VALUE;
        StrategySkill result=null;
        for(StrategySkill s: agent.characterSkillManager.skillList){
            List<GameState> list= s.simulate();
            if(list==null) continue;
            for(GameState g : list){
                int tmp=evaluate(g);
                if(tmp>best){
                    best=tmp;
                    result=s;
                }
            }
        }
        if(agent.characterSkillManager.skillList.size()==0) LOGGER.log(Level.SEVERE,agent.characterInfo.getName()
        + "SkillList is empty");
        if(result==null) LOGGER.log(Level.SEVERE,"returned Skill ==null");
        return result;
    }

    /**
     * Evaluate how good a gameStats is for a character
     * @param gameState - the gameState to be evaluated
     * @return int - value of goodness
     */
    private int evaluate(GameState gameState){
        int result=0;
        for(CombatCharacter c : gameState.combatCharacterList){
            int tmp=0;
            tmp+=Math.max(0,c.attributes.getHP());
            if(agent.characterInfo.isBlueTeam()==c.characterInfo.isBlueTeam()) result+=tmp;
            else result-=tmp;
        }
        return result;
    }
}
