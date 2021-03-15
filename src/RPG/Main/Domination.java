package RPG.Main;

import RPG.Character.CombatCharacter;

public class Domination implements StrategyEndCondition{
    @Override
    public boolean checkEndCondition() {
        int blue=0;
        int red=0;
       for(CombatCharacter combatCharacter : GameState.getInstance().combatCharacterList){
           if(!combatCharacter.statusEffects.containsKey("Dead")) {
               if (combatCharacter.characterInfo.isBlueTeam()) blue++;
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
       else if(GameState.getInstance().turnCounter>9){
           GameState.getInstance().output.endCondition("Draw");
           return true;
       }
       else return false;
    }
}
