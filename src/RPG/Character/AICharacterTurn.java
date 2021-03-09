package RPG.Character;

import RPG.SkillSystem.StrategySkill;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AICharacterTurn implements StrategyCharacterTurn{
    CombatCharacter agent;
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
        while(agent.attributes.getAP()>0){
            getBestMove().useSkill();
        }
    }

    /**
     * todo move Goodness evaluationLogic from Skills
     * @return StrategySkill - Skill with highest rating
     */
    private StrategySkill getBestMove(){
        int best=Integer.MIN_VALUE;
        StrategySkill result=null;
        for(StrategySkill s: agent.characterSkillManager.skillList){
            int val=s.simulate();
            if(val>best){
                result=s;
                best=val;
            }
        }
        if(agent.characterSkillManager.skillList.size()==0) LOGGER.log(Level.SEVERE,agent.characterInfo.getName()
        + "SkillList is empty");
        if(result==null) LOGGER.log(Level.SEVERE,"returned Skill ==null");
        return result;
    }
}
