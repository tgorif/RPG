package RPG.Main;

import RPG.Character.CombatCharacter;

public class Domination implements StrategyEndCondition{
    @Override
    public boolean checkEndCondition() {
        int blue=0;
        int red=0;
       for(CombatCharacter combatCharacter : GameState.getInstance().combatCharacterList){
           if(!combatCharacter.statusEffects.containsKey("dead")) {
               if (combatCharacter.isBlueTeam) blue++;
               else red++;
           }
       }
       if(blue==0){
           GameState.getInstance().output.endCondition("Red");
           return true;
       }
       else if(red==0){
           GameState.getInstance().output.endCondition("Blue");
           return true;
       }
       else return false;
    }
}
