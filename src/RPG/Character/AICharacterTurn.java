package RPG.Character;

import RPG.Main.GameState;
import RPG.SkillSystem.StrategySkill;
import RPG.SkillSystem.targetsCharacter;

import java.util.ArrayList;
import java.util.Collections;
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
        while(agent.attributes.getAP()>0){
            StrategySkill s =getBestMove();
            if(s!=null) s.useSkill();
            else return;
        }
    }
    /**
     * @return StrategySkill -random valid Skill, null if none are valid
     */
    private StrategySkill getBestMove(){
        StrategySkill result=null;
        List<StrategySkill> list = new ArrayList<>();
        list.addAll(agent.characterSkillManager.skillList);
        Collections.shuffle(list);
        if(agent.characterSkillManager.skillList.size()==0)
            LOGGER.log(Level.SEVERE, agent.characterInfo.getName()
                    + "SkillList is empty");
        for(StrategySkill s : list){
            if(s instanceof targetsCharacter){
                if(((targetsCharacter) s).getTargets().size()>0)
                ((targetsCharacter) s).setTarget(((targetsCharacter) s).getTargets().get(0));
                if(s.isValid()){
                    result=s;
                    break;
                }
            }
        }
        return result;
    }
}
